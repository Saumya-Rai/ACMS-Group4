import pickle
import time
import math
print(math.floor(time.time()))

saveData = {'epoch_time': 1490985000, 'flight_number': '6E758', 'current_time': 1490985000}
with open('Data/saveData.pickle', 'wb') as handle:
    pickle.dump(saveData, handle, protocol=pickle.HIGHEST_PROTOCOL)