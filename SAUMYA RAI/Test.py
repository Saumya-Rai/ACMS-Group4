import pickle
import time
import math
print(math.floor(time.time()))

saveData = {'epoch_time': 1490985000, 'flight_number': '6E456', 'current_time': 1528811317}
with open('Data/saveData.pickle', 'wb') as handle:
    pickle.dump(saveData, handle, protocol=pickle.HIGHEST_PROTOCOL)