from models.conference import Conference
import pickle


conferences = [
       Conference("ICML",['15/11/2023','20/11/2023'], '10/11/2023', 123),
       Conference("ICLR",['15/11/2023','20/11/2023'], '10/11/2023', 124),
       Conference("ICCV",['15/11/2023','20/11/2023'], '10/11/2023', 125),
]


with open('data/conferences.csv', 'wb') as f:
       pickle.dump(conferences, f)