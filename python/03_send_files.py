import csv
import os

from os.path import isdir, join, exists



"""
creating objects and reading file in dictionary test
"""

class City:
    PATH = os.path.dirname(os.path.realpath(__file__)) + "\\..\\src\\City_Data_Attributes"
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






def send_files(city_name):
    city = City(city_name)

    ##todo with city


send_files("Ashenville")