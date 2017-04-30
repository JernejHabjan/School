import os
from os.path import isdir, join, exists
import goslate  # py -3 -m pip install goslate
# for nltk run 0_1init.py file
from nltk import wordpunct_tokenize, defaultdict
from nltk.corpus import stopwords
from nltk.sentiment.util import *
from nltk.sentiment.vader import SentimentIntensityAnalyzer
import numpy as np


def getCompoundScore(text):
    def detectLanguage(text):
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
    language = detectLanguage(text)
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
                             "transit"]
    ATTRIB_ARRAY_REVIEWS = ["id", "comments"]
    ATTRIB_ARRAY_CALENDAR = ["listing_id", "date", "available", "price"]

    DESC_ATTRIBS_LISTINGS = ["description", "host_about", "name", "neighborhood_overview", "notes", "space", "summary",
                             "transit"]
    DESC_ATTRIBS_REVIEWS = ["comments"]

    PATH = os.path.dirname(os.path.realpath(__file__)) + "\\..\\..\\src\\City_Data"
    WRITE_PATH = os.path.dirname(os.path.realpath(__file__)) + "\\..\\..\\src\\City_Data_Attributes"

    CITIES = [f for f in os.listdir(PATH) if isdir(join(PATH, f))]
    FILES = ["reviews.csv", "listings.csv", "calendar.csv"]
    FILES = ["reviews.csv", "listings.csv"]
    CITIES = ["Ashenville"]

    for city_name in CITIES:

        comments_dict = defaultdict(list)

        # 1. READ CITY
        for file in FILES:
            read_file = PATH + "\\" + city_name + "\\" + file
            if not exists(read_file):
                print(
                    "File: " + read_file + " Not Found! - Move Your Data To Apropriate Location.")
                exit(1)

            with open(read_file, encoding="utf8") as f:
                reader = csv.reader(f)  ##READ CSV

                write_filename = WRITE_PATH + "\\" + city_name + "\\" + file
                print(write_filename)
                if not os.path.exists(os.path.dirname(write_filename)):
                    try:
                        os.makedirs(os.path.dirname(write_filename))
                    except OSError as exc:  # Guard against race condition
                        print("ERROR CREATING DIR")

                ATTRIB_ARR = []
                arr_LISTINGS = {}
                if file == "listings.csv":
                    ATTRIB_ARR = ATTRIB_ARRAY_LISTINGS
                elif file == "reviews.csv":
                    ATTRIB_ARR = ATTRIB_ARRAY_REVIEWS
                elif file == "calendar.csv":
                    ATTRIB_ARR = ATTRIB_ARRAY_CALENDAR

                with open(write_filename, mode='w+', encoding="utf8", newline='') as f:
                    writer = csv.writer(f, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)

                    for row_count, row in enumerate(reader):
                        if row_count == 0:  ##mormo dobit indekse
                            for atrib in ATTRIB_ARR:

                                for i, element in enumerate(row):

                                    if (atrib == element):
                                        arr_LISTINGS[atrib] = i

                        vrstica = []

                        for atrib in arr_LISTINGS:
                            temp_row = row[arr_LISTINGS[atrib]]

                            ##TEXTMINE - writes compound score in attributes that are description
                            if file == "reviews.csv" and atrib in DESC_ATTRIBS_REVIEWS and row_count > 0:
                                text = temp_row
                                temp_row = getCompoundScore(text)
                                comments_dict[row[0]].append(temp_row)
                                # print(comments_dict)

                            elif file == "listings.csv" and atrib in DESC_ATTRIBS_LISTINGS and row_count > 0:
                                text = temp_row
                                temp_row = getCompoundScore(text)
                            ##END TEXTMINE

                            vrstica.append(temp_row)

                        if file == "listings.csv":
                            # dodamo izračunan atribut -> povprečna vrednost komentarjev uteženi z številom komentarjev - the more the better
                            if len(comments_dict):
                                if row_count == 0:  # dodamo header
                                    vrstica.append("avg_comment_score")
                                else:
                                    id = row[0]
                                    if id in comments_dict.keys():
                                        ####TODO -zaenkrat samo mean--- treba utežit s številom komentarjev
                                        vrstica.append(np.mean(comments_dict[id]))
                                    else:
                                        vrstica.append(0)
                            else:
                                print(
                                    "Error! - Not calculated comments score. Run reviews.csv before listings.csv to fill comments_dict.")
                                exit(1)
                        if file != "reviews.csv":
                            writer.writerow(vrstica)


prepare_files()

"""
creating objects and reading file in dictionary test
"""

class City:
    PATH = os.path.dirname(os.path.realpath(__file__)) + "\\..\\..\\src\\City_Data_Attributes"
    location = (0.0, 0.0)
    city_name = ""
    file = "listings.csv"  ##vedno bo pol samo ta???!?!?!??!
    entries_X = {}

    def __init__(self, city_name):
        self.city_name = city_name
        self.read_entries()

    def read_entries(self):
        # read city file and map it in dict with key for id
        read_file = self.PATH + "\\" + self.city_name + "\\" + self.file
        if not exists(read_file):
            print(
                "File: " + read_file + " Not Found! - Move Your Data To Apropriate Location.")
            exit(1)

        with open(read_file, encoding="utf8") as f:
            reader = csv.reader(f)  ##READ CSV
            for row_count, row in enumerate(reader):
                self.entries_X[row[0]] = row[1:]

                # TODO

# city = City("Ashenville")
