import os
from itertools import count
from math import ceil
from os.path import isdir, join, exists
import goslate  # py -3 -m pip install goslate
# for nltk run 0_1init.py file
from nltk import wordpunct_tokenize, defaultdict, OrderedDict
from nltk.corpus import stopwords
from nltk.sentiment.util import *
from nltk.sentiment.vader import SentimentIntensityAnalyzer
from numpy import mean, array, array_split
import datetime as dt

from win32timezone import now

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
                  "comment_comp_score": 3.0
                  }


def getCompoundScore(text):
    def detect_language(text):
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
        #print(atrib_name)

        if (atrib_name == "comment_comp_score"):
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
                value = 1 / int(value.strip("%"))
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
            datetime_object = dt.datetime.strptime(value, "%Y-%m-%d")
            value = 1 / (
                now().replace(tzinfo=None) - datetime_object).days  ##decimalke

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


def prepare_files():
    # 1. read csv
    # 2. remove attributes
    # 3. translate descriptions into compound score

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
    CITIES = ["Asheville"]

    header = []

    for city_name in CITIES:

        comments_dict = defaultdict(list)

        # 1. READ CITY
        for file in FILES:
            read_file = PATH + "\\" + city_name + "\\" + file
            if not exists(read_file):
                print(
                    "File: " + read_file + " Not Found! - Move Your Data To Apropriate Location.")
                exit(1)
            # opening read file
            with open(read_file, encoding="utf8") as f:
                reader = csv.reader(f)
                write_filename = WRITE_PATH + "\\" + city_name + "\\" + file

                print(write_filename)
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
                    for row_count, row in enumerate(reader):
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

                                    #print(vrstica)
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

                                        #print(comp_scores)

                                        ##sort
                                        dates, comp_scores = zip(*sorted(zip(dates, comp_scores)))

                                        ## reformat attributes
                                        reformat_attributes(comments_dict, vrstica, header)

                                        final_scores = []
                                        for comp_score in comp_scores:
                                            final_scores.append(
                                                getFinalScore(vrstica, header, comp_score))
                                        #print(final_scores)

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
                                            comments_scores_5[date_array[i]] = mean(chunks_arrays[i])
                                        #print(comments_scores_5)

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
                                            i+=1
                                        #print(comments_scores_through_time)


                                        """ SCORE"""
                                        SCORE = list(comments_scores_5.values())[-1]

                                    vrstica.append(avg_comment_score)
                                    vrstica.append(comments_scores_5)
                                    vrstica.append(comments_scores_through_time)
                                    vrstica.append(SCORE)


                            else:
                                print("""Error! - Not calculated comments score. 
                                        Run reviews.csv before listings.csv to fill comments_dict.""")
                                exit(1)

                        # write whole row in file
                        writer.writerow(vrstica)


prepare_files()
