import csv
import datetime as dt
import os
from os.path import isdir, join, exists
import goslate  # py -3 -m pip install goslate
# for nltk run 0_1init.py file
from nltk import wordpunct_tokenize, defaultdict
from nltk.corpus import stopwords
from nltk.sentiment.vader import SentimentIntensityAnalyzer
from numpy import mean, array, array_split
from win32timezone import now
import ast
import threading

atribs_weights = {"cancellation_policy": 0.1,
                  "description_score": 0.4,
                  "host_about": 0.3,
                  "host_acceptance_rate": 0.7,
                  "host_identity_verified": 0.1,
                  "host_response_time": 0.2,
                  "host_since": 0.1,
                  "host_total_listings_count": 0.5,
                  "host_verifications": 0.6,
                  "is_location_exact": 0.3,
                  "neighborhood_overview": 1.5,
                  "notes": 0.5,
                  "number_of_reviews": 1.5,
                  "review_scores_accuracy": 0.9,
                  "review_scores_checkin": 0.9,
                  "review_scores_cleanliness": 0.9,
                  "review_scores_communication": 0.7,
                  "review_scores_location": 0.7,
                  "review_scores_rating": 1.2,
                  "review_scores_value": 1.2,
                  "space": 0.4,
                  "summary": 0.5,
                  "transit": 0.8,
                  "comment_comp_score": 2.0  # prej je imelo prevelik poudarek
                  }


def getCompoundScore(text):
    def detect_language(text):
        try:
            languages_ratios = {}
            tokens = wordpunct_tokenize(text)
            words = [word.lower() for word in tokens]
            for language in stopwords.fileids():
                stopwords_set = set(stopwords.words(language))
                words_set = set(words)
                common_elements = words_set.intersection(stopwords_set)
                languages_ratios[language] = len(common_elements)  # language "score"
            most_rated_language = max(languages_ratios, key=languages_ratios.get)
            return most_rated_language
        except:
            return "english"

    ##CODE##
    vader = SentimentIntensityAnalyzer()
    language = detect_language(text)
    if language != "english":  ### translating foreign comments
        gs = goslate.Goslate()
        try:
            text = gs.translate(text, 'en')
        except:
            ## HTTP Error
            pass
    score = vader.polarity_scores(text)
    # website nlp -> http://nlpforhackers.io/sentiment-analysis-intro/
    # compound < 0 -> negative polarity
    # večja kot je compound - bolj poz je comment
    # {'neg': 0.0, 'neu': 0.759, 'pos': 0.241, 'compound': 0.9549}
    return score["compound"]


def reformat_attributes(comments_dict, vrstica, header):
    # this function reformats text-like attributes in numeric
    for atrib_name, weight in atribs_weights.items():

        if id not in comments_dict.keys() and atrib_name == "comments_scores_though_time":
            continue
        # print(atrib_name)

        if atrib_name == "comment_comp_score":
            continue

        value = vrstica[header.index(atrib_name)]

        if atrib_name == "cancellation_policy":
            atribs = ["strict", "moderate", "flexible"]
            try:
                value = atribs.index(value) / (len(atribs) - 1)
            except:
                value = 0

        elif atrib_name == "host_acceptance_rate":
            try:
                value = 1 / (int(value.strip("%")) / 10)
            except:
                value = 0

        elif atrib_name == "host_response_time":
            atribs = ["N/A", "a few days or more", "within a day",
                      "within a few hours", "within an hour"]
            try:
                value = atribs.index(value) / (len(atribs) - 1)
            except:
                value = 0

        elif atrib_name == "host_since":
            try:
                datetime_object = dt.datetime.strptime(value, "%Y-%m-%d")
                value = 1 - 1 / ((
                                     now().replace(tzinfo=None) - datetime_object).days / 100)  ##decimalke
            except:
                value = 0
        elif atrib_name == "host_verifications":
            atribs = ['email', 'phone', 'reviews', 'google', 'jumio', 'facebook',
                      'kba', 'amex', 'linkedin', 'manual_offline']
            value = len(value) / len(atribs)

        elif atrib_name in ["is_location_exact", "host_identity_verified"]:

            value = 1 if value == "t" else 0

        elif atrib_name in ["review_scores_accuracy", "review_scores_checkin",
                            "review_scores_cleanliness", "review_scores_communication",
                            "review_scores_location", "review_scores_value"]:
            try:
                value = float(value) / 10
            except:
                value = 0

        elif atrib_name == "review_scores_rating":
            try:
                value = float(value) / 100
            except:
                value = 0

        elif atrib_name == "host_total_listings_count":  ##TODO
            try:
                value = float(value) / 100
            except:
                value = 0

        elif atrib_name == "host_verifications":  ## TODO
            try:
                value = float(value) / 100
            except:
                value = 0

        elif atrib_name == "number_of_reviews":  ## TODO
            try:
                value = float(value) / 1000
            except:
                value = 0

        vrstica[header.index(atrib_name)] = value

    """ REFORMAT OTHER ATTRIBUTES """

    # host_response_rate
    try:
        vrstica[header.index("host_response_rate")] = 1 / (
            int(vrstica[header.index("host_response_rate")].strip("%")) * 10)
    except:
        vrstica[header.index("host_response_rate")] = 0
    # instant_bookable
    vrstica[header.index("instant_bookable")] = 1 if vrstica[header.index("instant_bookable")] == "t" else 0

    money_atribs = ["cleaning_fee", "extra_people", "price", "security_deposit"]
    for atrib in money_atribs:
        try:
            value = float(vrstica[header.index(atrib)].strip("$"))
        except:
            value = -1
        vrstica[header.index(atrib)] = value


def getFinalScore(vrstica, header, comment_comp_score):
    SCORE = 0
    for atrib_name, weight in atribs_weights.items():
        if atrib_name == "comment_comp_score":  ##za usak comment comp score
            value = comment_comp_score
        else:
            value = vrstica[header.index(atrib_name)]

        if atrib_name != "comment_comp_score":
            vrstica[header.index(atrib_name)] = value
        try:
            SCORE += float(value) * weight
        except:
            pass
            # print(vrstica)
    return SCORE


def to_break(bool, count_writes, filename, row_count):
    if not bool:
        return False

    if filename == "reviews.csv":
        if count_writes >= 4000:
            return True

    elif filename == "listings.csv":
        if count_writes >= 200 or row_count > 500:
            return True

    elif filename == "calendar.csv":
        if count_writes >= 0:
            return True

    return False


def parse_file(FILES, PATH, WRITE_PATH, city_name, ATTRIB_ARRAY_LISTINGS, ATTRIB_ARRAY_REVIEWS, ATTRIB_ARRAY_CALENDAR,
               TO_BREAK, DO_NORMAL_RUN, DESC_ATTRIBS_REVIEWS, DESC_ATTRIBS_LISTINGS):
    comments_dict = defaultdict(list)
    # 1. READ CITY
    for file in FILES:
        read_file = PATH + "\\" + city_name + "\\" + file
        write_filename = WRITE_PATH + "\\" + city_name + "\\" + file
        if not exists(read_file):
            print(
                "File: " + read_file + " Not Found! - Move Your Data To Apropriate Location.")
            exit(1)
        print(write_filename)
        if (DO_NORMAL_RUN):
            # opening read file
            with open(read_file, encoding="utf8") as f:
                reader = csv.reader(f)
                # creating path
                if not os.path.exists(os.path.dirname(write_filename)):
                    try:
                        os.makedirs(os.path.dirname(write_filename))
                    except OSError as exc:  # Guard against race condition
                        print("ERROR CREATING DIR")

                # getting apropriate attrib array
                ATTRIB_ARR = []
                if file == "listings.csv":
                    ATTRIB_ARR = ATTRIB_ARRAY_LISTINGS
                elif file == "reviews.csv":
                    ATTRIB_ARR = ATTRIB_ARRAY_REVIEWS
                elif file == "calendar.csv":
                    ATTRIB_ARR = ATTRIB_ARRAY_CALENDAR

                # opening write file
                arr_LISTINGS = {}
                with open(write_filename, mode='w+', encoding="utf8", newline='') as f:
                    writer = csv.writer(f, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)

                    # loop through rows in read file
                    count_writes = 0
                    for row_count, row in enumerate(reader):
                        if (row_count % 10000 == 0 and row_count != 0):
                            print(row_count)
                        if to_break(TO_BREAK, count_writes, file, row_count):
                            break

                        # getting attribute indices to pull data from writer
                        if row_count == 0:  ##mormo dobit indekse
                            for atrib in ATTRIB_ARR:
                                for i, element in enumerate(row):
                                    if atrib == element:
                                        arr_LISTINGS[atrib] = i

                        # combining apropriate attributes and textmining data
                        vrstica = []
                        for atrib in arr_LISTINGS:
                            temp_row = row[arr_LISTINGS[atrib]]
                            ##TEXTMINE - writes compound score in attributes that are description
                            if file == "reviews.csv":
                                if atrib in DESC_ATTRIBS_REVIEWS and row_count > 0:  # compound score

                                    comp_score = getCompoundScore(temp_row)
                                    comments_dict[row[0]].append(
                                        [row[arr_LISTINGS["date"]],
                                         comp_score])  # appends to id -> date and comp. score

                                    temp_row = comp_score

                            elif file == "listings.csv" and atrib in DESC_ATTRIBS_LISTINGS and row_count > 0:
                                if (atrib != "description"):
                                    temp_row = getCompoundScore(temp_row)

                            vrstica.append(temp_row)

                        if file == "listings.csv":
                            # dodamo izračunan atribut -> povprečna vrednost komentarjev uteženi z številom komentarjev - the more the better
                            if len(comments_dict):
                                if row_count == 0:  # dodamo header
                                    vrstica.append("description_score")
                                    vrstica.append("avg_comment_score")
                                    vrstica.append("comments_scores_5")
                                    vrstica.append("comments_scores_though_time")
                                    vrstica.append("SCORE")

                                    # print(vrstica)
                                    header = vrstica
                                else:
                                    id = row[0]

                                    avg_comment_score = 0
                                    comments_scores_5 = {}
                                    comments_scores_through_time = 0
                                    SCORE = 0

                                    ## APPEND DESCRIPTION TEXT
                                    vrstica.append(getCompoundScore(vrstica[header.index("description")]))

                                    if id in comments_dict.keys():

                                        dates = array(comments_dict[id])[:, 0]
                                        comp_scores = array(array(comments_dict[id])[:, 1]).astype(float)

                                        # print(comp_scores)

                                        ##sort
                                        dates, comp_scores = zip(*sorted(zip(dates, comp_scores)))

                                        ## reformat attributes
                                        reformat_attributes(comments_dict, vrstica, header)

                                        final_scores = []
                                        for comp_score in comp_scores:
                                            final_scores.append(
                                                getFinalScore(vrstica, header, comp_score))
                                        # print(final_scores)

                                        """ 1 MEAN """
                                        avg_comment_score = mean(final_scores)  ##mean comp scora

                                        """ 2 FIVE DATES AND SCORES score through time 5 -> for graph """
                                        dni = 5

                                        date_array = []
                                        for i in range(dni):
                                            index = int((len(dates) - 1) * (i / (dni - 1)))
                                            date_array.append(dt.datetime.strptime(dates[index], '%Y-%m-%d').date())

                                        chunks_arrays = array_split(array(final_scores), dni)

                                        comments_scores_5 = {}
                                        for i in range(len(date_array)):
                                            date_array[i] = dt.datetime.strftime(date_array[i], '%Y-%m-%d')
                                            if len(chunks_arrays[i]) == 0:
                                                comments_scores_5[date_array[i]] = 0
                                            else:
                                                comments_scores_5[date_array[i]] = mean(chunks_arrays[i])
                                        # print(comments_scores_5)

                                        """3 SCORE THROUGH TIME """
                                        """ MOGOČE PREVEČ POUDARJENI NOVI KOMENTARJI ??? mogoče * (i/10) ??"""
                                        ##comments_scores_through_time is calculated from five grouped score means,
                                        # giving newer comment group biggest priority
                                        comments_scores_through_time = 0
                                        last_score = 0
                                        i = 0
                                        for date, score in comments_scores_5.items():
                                            # vecji kot je I, bližje je zdajšnemu scoru in večji vpliv ima
                                            comments_scores_through_time += (score - last_score) * i
                                            last_score = score
                                            i += 1

                                        """ SCORE"""
                                        SCORE = list(comments_scores_5.values())[-1]

                                    vrstica.append(avg_comment_score)
                                    vrstica.append(comments_scores_5)
                                    vrstica.append(round(comments_scores_through_time, 1))
                                    vrstica.append(round(SCORE, 1))
                                    if SCORE == 0:
                                        # print("zero")
                                        continue


                            else:
                                print("""Error! - Not calculated comments score. 
                                        Run reviews.csv before listings.csv to fill comments_dict.""")
                                exit(1)

                        # write whole row in file
                        if file == "listings.csv":
                            writer.writerow(vrstica)
                        count_writes += 1

        ## normalizing score:
        if file == "listings.csv":
            read_file = WRITE_PATH + "\\" + city_name + "\\" + file
            fileDict = {}

            try:
                input_file = csv.DictReader(open(read_file, encoding="utf8"))

                max_SCORE = None
                min_SCORE = None
                for row_count, row in enumerate(input_file):

                    fileDict[row_count] = row

                    temp_SCORE = float(row["SCORE"])
                    if max_SCORE == None or max_SCORE < temp_SCORE:
                        max_SCORE = temp_SCORE
                    if min_SCORE == None or temp_SCORE < min_SCORE:
                        min_SCORE = temp_SCORE
                if len(fileDict):
                    with open(read_file, 'w', encoding="utf8", newline='') as csvfile:

                        writer = csv.DictWriter(csvfile, fieldnames=fileDict[0].keys())
                        writer.writeheader()
                        for _, value in fileDict.items():
                            # SCORE:
                            tmp_SCORE = float(value["SCORE"])
                            value["SCORE"] = ((tmp_SCORE - min_SCORE) / (max_SCORE + 0.0001 - min_SCORE)) * 10.0

                            # comments_scores_5
                            tmp_comments_scores_5 = ast.literal_eval(value["comments_scores_5"])
                            for key, val in tmp_comments_scores_5.items():
                                tmp_comments_scores_5[key] = float(
                                    ((float(val) - min_SCORE) / (max_SCORE + 0.0001 - min_SCORE)) * 10.0)

                            value["comments_scores_5"] = tmp_comments_scores_5

                            # avg comment score
                            value["avg_comment_score"] = ((float(value["avg_comment_score"]) - min_SCORE) / (
                                max_SCORE + 0.0001 - min_SCORE)) * 10.0

                            # write
                            writer.writerow(value)

                else:
                    print("EMPTY FILE")
            except:
                print("SCORE CHANGE FILE WAS NOT FOUND: " + read_file)
    print("Finished: ", city_name, " ", file)

def prepare_files(DO_NORMAL_RUN=True):
    # 1. read csv
    # 2. remove attributes
    # 3. translate descriptions into compound score

    TO_BREAK = False

    ATTRIB_ARRAY_LISTINGS = ["id", "accommodates", "amenities", "bathrooms", "bed_type", "bedrooms", "beds",
                             "cancellation_policy", "cleaning_fee", "description", "extra_people", "first_review",
                             "guests_included", "host_about", "host_acceptance_rate", "host_identity_verified",
                             "host_response_rate", "host_response_time", "host_since", "host_total_listings_count",
                             "host_verifications", "instant_bookable", "is_location_exact", "latitude", "listing_url",
                             "longitude", "maximum_nights", "minimum_nights", "name", "neighborhood_overview", "notes",
                             "number_of_reviews", "price", "property_type", "review_scores_accuracy",
                             "review_scores_checkin", "review_scores_cleanliness", "review_scores_communication",
                             "review_scores_location", "review_scores_rating", "review_scores_value",
                             "reviews_per_month", "room_type", "security_deposit", "space", "square_feet", "summary",
                             "transit", "picture_url", "thumbnail_url"]
    ATTRIB_ARRAY_REVIEWS = ["id", "date", "comments"]
    ATTRIB_ARRAY_CALENDAR = ["listing_id", "date", "available", "price"]

    DESC_ATTRIBS_LISTINGS = ["description", "host_about", "neighborhood_overview", "notes", "space", "summary",
                             "transit"]
    DESC_ATTRIBS_REVIEWS = ["comments"]

    PATH = os.path.dirname(os.path.realpath(__file__)) + "\\..\\..\\src\\City_Data"
    WRITE_PATH = os.path.dirname(os.path.realpath(__file__)) + "\\..\\src\\City_Data_Attributes"

    CITIES = [f for f in os.listdir(PATH) if isdir(join(PATH, f))]
    FILES = ["reviews.csv", "listings.csv", "calendar.csv"]
    # CITIES = ["Asheville"]


    for city_name in CITIES:
        threading.Thread(target=parse_file, args=(
            FILES, PATH, WRITE_PATH, city_name, ATTRIB_ARRAY_LISTINGS, ATTRIB_ARRAY_REVIEWS, ATTRIB_ARRAY_CALENDAR,
            TO_BREAK, DO_NORMAL_RUN, DESC_ATTRIBS_REVIEWS, DESC_ATTRIBS_LISTINGS)).start()


prepare_files(True)
