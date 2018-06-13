#depenencies:
# pip3 install python-firebase

# importing the package
from firebase import firebase

# link to the storage space
firebase=firebase.FirebaseApplication('https://airplane-4848e.firebaseio.com/')

# the actual data that needs to be inserted, has to be done iteratively
result=firebase.post('/AirlineDetails',{'flight number':flightCode, 'callsign':callSign, 'airline': airline,
                 'origin':source,'destination': destination,'date' : flightDate, 'scheduled departure':scheduledDeparture,
                 'scheduled arrival': scheduledArrival, 'actual departure':actualDeparture ,'status':status })

#result=firebase.post('/AirlineDetails',{'Airline':'Spicejet', 'FlightNo':'SG128'})
