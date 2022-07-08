import math

def evaporator(content:float, evap_per_day:float, threshold:float):    
	return math.ceil(math.log(threshold/100)/math.log(1-evap_per_day/100))