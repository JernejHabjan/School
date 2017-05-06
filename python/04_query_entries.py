import csv
import os
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
			name_id = columns.index("property_type")
			lat_id = columns.index("latitude")
			lng_id = columns.index("longitude")
			description_id = columns.index("description")

			score_id = columns.index("review_scores_rating")
			reader = sorted(reader, key=lambda x: toFloat(x[score_id]), reverse=True)
			
			for row_count, row in enumerate(reader[1:]):
				self.entries_X[row[0]] = row[1:]
				print(
					row[name_id], 
					row[lat_id], 
					row[lng_id], 
					row[description_id], 
					toFloat(row[score_id]), 
					sep=","
				)
				#break

								
def send_files(city_name):
    city = City(city_name)	
	#print(city.city_name)
    ##todo with city


#TODO pass arguments
send_files("Asheville")
#print("OK")
