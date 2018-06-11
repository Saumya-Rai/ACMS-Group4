from firebase import firebase

result={}

firebase = firebase.FirebaseApplication('https://airplane-4848e.firebaseio.com/')

# insert the table name that you're retrieving data from
result=firebase.get('/AirlineDetails',None)

print(list(result.values())[1])
