import csv
import os
from os.path import isdir, join
import nltk

def textmining():
    def comments():
        filename = "reviews.csv"
        PATH = os.path.dirname(os.path.realpath(__file__)) + "\\..\\..\\src\\City_Data_Attributes"
        CITIES = [f for f in os.listdir(PATH) if isdir(join(PATH, f))]
        CITIES = ["Ashenville"]

        for city_name in CITIES:
            # 1. READ CITY

            with open(PATH + "\\" + city_name + "\\" + filename, encoding="utf8") as f:
                reader = csv.reader(f)

                for row in reader:
                    #print(row)

                    sentence = row[1]

                    tokens = nltk.word_tokenize(sentence)
                    tagged = nltk.pos_tag(tokens)
                    entities = nltk.chunk.ne_chunk(tagged)

                    print(entities)
                    return
    comments()


def remove_attributes():
    # 1. read csv
    # 2. remove attributes
    ATTRIB_ARRAY_LISTINGS = ["id", "accommodates", "amenities", "bathrooms", "bed_type", "bedrooms", "beds",
                             "cancellation_policy", "cleaning_fee", "description", "extra_people", "first_review",
                             "guests_included", "host_about", "host_acceptance_rate", "host_identity_verified",
                             "host_response_rate", "host_response_time", "host_since", "host_total_listings_count",
                             "host_verifications", "instant_bookable", "is_location_exact", "latitude", "listing_url",
                             "longitude", "maximum_nights", "minimum_nights", "name", "neighborhood_overview", "notes",
                             "number_of_reviews", "price", "property_type", "review_scores_accuracy",
                             "review_scores_checkin",
                             "review_scores_cleanliness", "review_scores_communication", "review_scores_location",
                             "review_scores_rating", "review_scores_value", "reviews_per_month", "room_type",
                             "security_deposit",
                             "space", "square_feet", "summary", "transit"]
    ATTRIB_ARRAY_REVIEWS = ["id", "comments"]
    ATTRIB_ARRAY_CALENDAR = ["listing_id", "date", "available", "price"]

    PATH = os.path.dirname(os.path.realpath(__file__)) + "\\..\\..\\src\\City_Data"
    WRITE_PATH = os.path.dirname(os.path.realpath(__file__)) + "\\..\\..\\src\\City_Data_Attributes"

    CITIES = [f for f in os.listdir(PATH) if isdir(join(PATH, f))]
    FILES = ["listings.csv", "calendar.csv", "reviews.csv"]

    for city_name in CITIES:
        # 1. READ CITY
        for file in FILES:

            with open(PATH + "\\" + city_name + "\\" + file, encoding="utf8") as f:
                reader = csv.reader(f)  ##READ CSV

                write_filename = WRITE_PATH + "\\" + city_name + "\\" + file
                print(write_filename)
                if not os.path.exists(os.path.dirname(write_filename)):
                    try:
                        os.makedirs(os.path.dirname(write_filename))
                    except OSError as exc:  # Guard against race condition
                        print("ERROR CREATING DIR")

                ATTRIB_ARR = []
                arr_LISTINGS = []
                if file == "listings.csv":
                    ATTRIB_ARR = ATTRIB_ARRAY_LISTINGS
                    arr_LISTINGS = [] * len(ATTRIB_ARRAY_LISTINGS)  # narddimo tok velk arr
                elif file == "reviews.csv":
                    ATTRIB_ARR = ATTRIB_ARRAY_REVIEWS
                    arr_LISTINGS = [] * len(ATTRIB_ARRAY_REVIEWS)  # narddimo tok velk arr
                elif file == "calendar.csv":
                    ATTRIB_ARR = ATTRIB_ARRAY_CALENDAR
                    arr_LISTINGS = [] * len(ATTRIB_ARRAY_CALENDAR)  # narddimo tok velk arr

                with open(write_filename, mode='w+', encoding="utf8", newline='') as f:
                    writer = csv.writer(f, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)

                    is_header = True

                    for row in reader:

                        if (is_header):  ##mormo dobit indekse
                            for atrib in ATTRIB_ARR:
                                for i, element in enumerate(row):

                                    if (atrib == element):
                                        arr_LISTINGS.append(i)

                        vrstica = []

                        for i in range(len(arr_LISTINGS)):
                            vrstica.append(row[arr_LISTINGS[i]])

                        writer.writerow(vrstica)

                        if is_header:
                            is_header = False


def main():
    # remove_attributes()

    textmining()


main()
