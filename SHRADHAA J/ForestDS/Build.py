#!/usr/bin/python
import json
import datetime
from datetime import datetime, date
import Forest as F
from firebase import firebase

firebase = firebase.FirebaseApplication('https://airplane-4848e.firebaseio.com/')
result = firebase.get('/AirlineDetails',None)
input = list(result.values()[1:20])

for i in range(0,len(input)):
	node = input[i]
	index = F.getIndexOfTree(node.get('callsign'))
	prop_del = 0
	count = 0
	if index != -1:
		tree = F.forest[index]
		prop_del = 10
		for n in range(1,len(tree)):
			nodeprev = tree[n-1]
			nodepres = tree[n]
			beginning = datetime.strptime(nodeprev.get('scheduled arrival'), '%H:%M')
			end = datetime.strptime(nodepres.get('scheduled departure'), '%H:%M')
			duration = datetime.combine(date.min, end) - datetime.combine(date.min, beginning)
			if duration < 20:
				prop_del = prop_del + 10
				count = count + 1
			else:
				prop_del = 0
				count = 0
				
	if count != 0:
		prop_del = prop_del / count
			
	temp = F.createNode(node.get('callsign'), node.get('flight number'), node.get('origin'), node.get('destination'), node.get('scheduled departure'), node.get('scheduled arrival'), 10, prop_del)
	if index == -1:
		tree=[]
		tree.append(temp)
		F.forest.append(tree)
	else:
		F.forest[index].append(temp)

def myconverter(o):
    if isinstance(o, datetime.datetime):
        return o.__str__()

with open('data.json', 'w') as outfile:
    sent = json.dump(F.forest, outfile, default = myconverter)

result = firebase.post("/AirlineDetails", sent)
	
#return;