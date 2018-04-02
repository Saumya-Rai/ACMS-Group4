import pandas as pd
import json
import re
from urllib.request import Request,urlopen
import math
import time


df1 = pd.read_csv('Data/flightNumbers1.csv')
fList = df1['6E Flight Number'].tolist()
flightList = []
for flight in fList:
    flightList.append("6E"+ str(flight))
epoch_time = 1490985000
currentTime = math.floor(time.time()) 
flightList = list(set(flightList))
rowList=[]
for flight in flightList:
    eTime = epoch_time
    print(flight)
    while (eTime <= currentTime):

        url = "https://www.radarbox24.com/data/load-more?search=fnia&filter="+flight+"&term=&time="+str(eTime)+"&order=%3E"
        req = Request(url, headers={'User-Agent':'Mozilla/5.0'})
        data = json.loads(urlopen(req).read().decode())
        eTime = data[1]['date']
        for flightData in range(len(data)):
            flightCode = data[flightData]['fn']
            airline = re.sub("<.*?>", "", data[flightData]['airline'])
            callSign = re.sub("<.*?>", "", data[flightData]['act'])
            callSign = re.sub(r'.*V', 'V', callSign)
            callSign = callSign.replace(")","")
            print(callSign)
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
             
flightDetails1= pd.DataFrame(rowList)
flightDetails1.to_csv('Data/FlightInformation.csv',  sep=',')















