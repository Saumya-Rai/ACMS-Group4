import pandas as pd
from datetime import datetime, date, time
import re
import numpy as np

data = pd.read_csv("Data/FlightInformation.csv")
landed = "Landed"
sch = "Scheduled"
data['delay'] = 0
timeKaRegex = "(00:00|2[0-3]:[0-5][0-9]|[0-1][0-9]:[0-5][0-9])"
print("here1")

for index, row in data.iterrows():
    if row['status'] != landed and row['status'] != sch:
        
        status = re.search(timeKaRegex, row['status']).group(1)
        #print(status)
        status =  datetime.strptime(status,"%H:%M")
        scheduledArrival = re.search(timeKaRegex, str(row['scheduled arrival'])).group(1)
        #print(scheduledArrival)
        scheduledArrival = datetime.strptime(scheduledArrival, "%H:%M")
        #status = (status).total_seconds()
        data.loc[index,'delay'] = (datetime.combine(date.min,status.time()) - datetime.combine(date.min,scheduledArrival.time())).total_seconds()
        #print(row['delay'])
print("here")

flightList = data['flight number'].unique()
# print(type(flightList))
flightAverageDelay = pd.DataFrame(columns=['flight', 'average delay'])
for flight in flightList:
    
    flight_df = data.loc[data['flight number'] == flight]
    #print(flight_df['flight number'], flight_df['delay'])
    avr = np.mean(flight_df['delay'].tolist())
    callsign = flight_df['callsign'][0]
    #average in minutes to be stored
    if "+" in str(flight):
        modified="6E" + str(flight).split("+")[1]
        flightAverageDelay.loc[len(flightAverageDelay)] = [callsign, avr/60] 
    else:
        flightAverageDelay.loc[len(flightAverageDelay)] = [callsign, avr/60] 
print(flightAverageDelay)

flightAverageDelay.to_csv('Data/Flight Average Delay.csv',  sep=',', index=False)
