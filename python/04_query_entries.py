import csv
import os
import sys
from os.path import isdir, join, exists

def toFloat(value):
	try: return float(value)
	except: return 0

class City:
	def __init__(self, city_name):
		self.PATH = "..\\src\\City_Data_Attributes"
		self.location = (0.0, 0.0)
		self.entries_X = dict()
		self.file = "listings.csv"
		self.city_name = city_name
		self.read_entries()
		
	def read_entries(self):
		read_file = self.PATH + "\\" + self.city_name + "\\" + self.file
		if not exists(read_file):
			print("File: " + read_file + " Not Found! - Move Your Data To Apropriate Location.")
			exit(1)
			
		with open(read_file, encoding="utf8") as f:
			reader = list(csv.reader(f))  ##READ CSV
			
			columns = reader[0]
			#print(columns, "<br><br>")
			type_id = columns.index("property_type")
			lat_id = columns.index("latitude")
			lng_id = columns.index("longitude")
			description_id = columns.index("description")
			score_id = columns.index("SCORE")
			amenities_id = columns.index("amenities")

			#MOSTLY EMPTY VALUES
			#room_size_id = columns.index("square_feet")

			reader = sorted(reader, key=lambda x: toFloat(x[score_id]), reverse=True)

			# arguments
			#print(sys.argv)
			allow_pets = True if (sys.argv[2] if len(sys.argv) > 2 else "true") == "true" else False
			require_heating = True if (sys.argv[3] if len(sys.argv) > 3 else "true") == "true" else False
			require_house = True if (sys.argv[4] if len(sys.argv) > 4 else "true") == "true" else False
			require_breakfast = True if (sys.argv[5] if len(sys.argv) > 5 else "true") == "true" else False
			require_family_friendly = True if (sys.argv[6] if len(sys.argv) > 6 else "true") == "true" else False

			#print(allow_pets)
			
			for row_count, row in enumerate(reader[1:]):
				self.entries_X[row[0]] = row[1:]

				amenities = row[amenities_id].split(",")

				has_pets = False
				has_breakfast = False
				has_heating = False
				is_family_friendly = False

				for a in amenities:
					if "Pets live" in a or "Pets Allowed" in a:
						has_pets = True
					elif "Breakfast" in a:
						has_breakfast = True
					elif "Heating" in a:
						has_heating = True
					elif "Family" in a:
						is_family_friendly = True


				valid_pets = allow_pets or (not has_pets)
				valid_heating = (not require_heating) or has_heating
				valid_breakfast = (not require_breakfast) or has_breakfast
				valid_house = (not require_house) or row[type_id] == "House"
				valid_family = (not require_family_friendly) or is_family_friendly

				valid = valid_pets and valid_heating and valid_breakfast and valid_house and valid_family

				if valid:
					print(
						row[type_id], 
						row[lat_id], 
						row[lng_id], 
						row[description_id], 
						"%.2f" % round(toFloat(row[score_id]), 2),
						amenities,
						sep=","
					)

								
def send_files(city_name):
    city = City(city_name)


#TODO pass arguments
city_name = sys.argv[1] if len(sys.argv) > 1 else "Asheville" 
send_files(city_name)
#print("OK")
