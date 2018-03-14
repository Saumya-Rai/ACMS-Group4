from pyflightdata import FlightData
import flightradar24
import pandas as pd
import config
fr=flightradar24.Api()

f=FlightData()
f.login(config.username,config.password)


df1 = pd.read_csv('Data/flightNumbers.csv')
fList = df1['6E Flight Number'].tolist()


flightList = []
for flight in fList:
    flightList.append("6E"+ str(flight))

flightList = list(set(flightList))
print(len(flightList))
rowList=[]
"""
I had to make a list of flights to skip because, flightradar24 has an error on their side,
which keeps loading infinitely, thus preventing me from going to the next flight.
"""
skipFlights = ["6E231"]

for flight in flightList:
    print(flight)
    if flight in skipFlights:
        continue
    info = f.get_history_by_flight_number(flight)
    while len(info) is 0:
        info = f.get_history_by_flight_number(flight)
    print("length", len(info) ,flight)
    for i in range(len(info)):
        
        print(i)
        print(flight)
        r1 = info[i]['identification']
        r2 = info[i]['airline']
        r3 = info[i]['time']
        fnum = r1['number']['default']
        callsign= r1['callsign']
        status = info[i]['status']['live']
        model = info[i]['aircraft']['model']['code']
        nameAirline = r2['name']
        code = r2['code']['icao']
        try:
            origin = info[i]['airport']['origin']['position']['region']['city']
        except Exception as identifier:
            origin = "Not Defined"
            pass
        try:
            destination = info[i]['airport']['destination']['position']['region']['city']
        except Exception as identifier:
            destination = "Not Defined"
            pass
        #print(flight)
        scheduledDep = r3['scheduled']['departure']
        scheduledArr = r3['scheduled']['arrival']
        realDep = r3['real']['departure']
        realArr = r3['real']['arrival']
        dict1={'flight number':fnum, 'callsign':callsign, 'status':status, 'airline': nameAirline,
               'code': code, 'origin':origin,'destination': destination,  'scheduled departure':scheduledDep,
               'scheduled arrival': scheduledArr, 'real departure':realDep, 'real arrival': realArr }
        rowList.append(dict1)
print(rowList)
flightDetails= pd.DataFrame(rowList)
print(flightDetails)
flightDetails.to_csv('Data/FlightDetails.csv')















