import pandas as pd
import json
import re
from urllib.request import Request,urlopen
import math
import time
import pickle
import os
import traceback

if not os.path.isfile("Data/saveData.pickle"):
    saveData = {'epoch_time': 1490985000, 'flight_number': '6E21', 'current_time': 1490985000}
    print("pickle created")
    with open('Data/saveData.pickle', 'wb') as handle:
        pickle.dump(saveData, handle, protocol=pickle.HIGHEST_PROTOCOL)

with open("Data/saveData.pickle","rb") as handle:
    loadedData = pickle.load(handle)
print(loadedData)
df1 = pd.read_csv('Data/flightNumbers.csv')
fList = list(sorted(set(df1['6E Flight Number'].tolist())))

#List of all the flight number
flightList = []
for flight in fList:
    flightList.append("6E"+ str(flight))
currentTime = math.floor(time.time())
prevTime = loadedData["current_time"]
print(flightList[0])
#If gap is greater than 2 hours, we start from the beginning (all flights)
if currentTime - prevTime < 43200:
    # Start from where we left off
    newFlightList = flightList[flightList.index(loadedData["flight_number"]):]
else:
    newFlightList = flightList
epoch_time = loadedData['epoch_time']

isCrashed = False

print("Original Flight List", flightList)
print("Our Flight List", newFlightList)
rowList=[]
for flight in newFlightList:
    eTime = epoch_time
    while (eTime <= currentTime):

        try:
            url = "https://www.radarbox24.com/data/load-more?search=fnia&filter="+flight+"&term=&time="+str(eTime)+"&order=%3E"
            req = Request(url, headers={'User-Agent':'Mozilla/5.0'})
            data = json.loads(urlopen(req).read().decode())        
            # print(data)
            if len(data)<1:
                eTime += 43200 # epooch time for 12 hours
            else:
                eTime = data[0]['date']
            print("Flight: " + flight + " Epoch Time " + str(eTime) + "  Current: " + str(currentTime))
            print(str((flightList.index(flight) / len(flightList)) * 100) + "% Done")
            
            for flightData in range(len(data)):
                flightCode = data[flightData]['fn']
                airline = re.sub("<.*?>", "", data[flightData]['airline'])
                callSign = re.sub("<.*?>", "", data[flightData]['act'])
                callSign = re.sub(r'.*V', 'V', callSign)
                callSign = callSign.replace(")","")
                #print(callSign)
                aircraft = re.sub("<.*?>", "", data[flightData]['act'])
                aircraft = aircraft[0:4]
                source = re.sub("<.*?>", "", data[flightData]['aporgia'])
                destination = re.sub("<.*?>", "", data[flightData]['apdstia'])
                flightDate = data[flightData]['date']
                scheduledDeparture= re.sub("<.*?>", "", data[flightData]['std'])
                scheduledDeparture = ''.join(scheduledDeparture.split())[:-3]
                scheduledArrival= re.sub("<.*?>", "", data[flightData]['sta'])
                scheduledArrival = ''.join(scheduledArrival.split())[:-3]
                actualDeparture= re.sub("<.*?>", "", data[flightData]['atd'])
                actualDeparture = ''.join(actualDeparture.split())[:-3]
                status = re.sub("<.*?>", "", data[flightData]['status'])
                if status != 'Landed' and status != 'Scheduled' :
                    status = status [7:-3] 

                flightDict={'flight number':flightCode, 'callsign':callSign, 'airline': airline,
                    'origin':source,'destination': destination,'date' : flightDate, 'scheduled departure':scheduledDeparture,
                    'scheduled arrival': scheduledArrival, 'actual departure':actualDeparture ,'status':status }
                rowList.append(flightDict)
               
        except:
            print("Error occured")
            traceback.print_exc()
            print("They probably blocked the IP or no internet")
            toSaveData = {'epoch_time': epoch_time, 'flight_number': flight, 'current_time': currentTime}
            with open('Data/saveData.pickle', 'wb') as handle:
                pickle.dump(toSaveData, handle, protocol=pickle.HIGHEST_PROTOCOL)
            rowListDataFrame = pd.DataFrame(rowList)
            rowListDataFrame = rowListDataFrame[rowListDataFrame['flight number'] != flight]
            if os.path.isfile("Data/FlightInformation.csv"):
                with open("Data/FlightInformation.csv", "a") as handle:
                    rowListDataFrame.to_csv(handle, index=False, header=False)
            else:
                rowListDataFrame.to_csv('Data/FlightInformation.csv',  sep=',', index=False)
            isCrashed = True
            break
    if isCrashed:
        break

if not isCrashed:           
    # If we run the entire thing perfectly, we can set the epoch time as current time
    toSaveData = {'epoch_time': currentTime, 'flight_number': flightList[0], 'current_time': currentTime}
    with open('Data/saveData.pickle', 'wb') as handle:
        pickle.dump(toSaveData, handle, protocol=pickle.HIGHEST_PROTOCOL)
    rowListDataFrame = pd.DataFrame(rowList)
    if os.path.isfile("Data/FlightInformation.csv"):
        with open("Data/FlightInformation.csv", "a") as handle:
            rowListDataFrame.to_csv(handle, index=False, header=False)
    else:
        rowListDataFrame.to_csv('Data/FlightInformation.csv',  sep=',', index=False)