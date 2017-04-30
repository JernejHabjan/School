import csv
import os

from os.path import isdir, join, exists


def send_files(city_name):

    PATH = os.path.dirname(os.path.realpath(__file__)) + "\\..\\..\\src\\City_Data_Attributes"
    FILES = ["reviews.csv", "listings.csv", "calendar.csv"]

    # 1. READ CITY
    for file in FILES:
        read_file = PATH + "\\" + city_name + "\\" + file
        if not exists(read_file):
            print(
                "File: " + read_file + " Not Found! - Move Your Data To Apropriate Location.")
            exit(1)

        with open(read_file, encoding="utf8") as f:
            reader = csv.reader(f)  ##READ CSV
            for row in reader:
                print(row)
                #TODO

send_files("Ashenville")