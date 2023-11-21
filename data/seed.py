from models.conference import Conference
from models.paper import Paper
from models.author import Author
from models.confernceAuthorRegisteration import ConferenceAuthorRegisteration
import pickle


authors = [
       
       Author(1, 'alysoliman', 'pass123', 'Aly Soliman', '1/1/2000', 'Egyptian'),
       Author(2, 'mohamed', 'pass123', 'Mohamed', '1/1/2000', 'Egyptian'),
       Author(3, 'ahmed', 'pass123', 'Ahmed', '1/1/2000', 'Egyptian'),     
]

with open('data/authors.dat', 'wb') as f:
       pickle.dump(authors, f)


conferences = [
       Conference('ICSE', ['11/11/2023', '20/11/2023'], '06/11/2023', 1),
       Conference('IEEE', ['11/11/2023', '20/11/2023'], '06/11/2023', 2),
       Conference('ACM', ['11/11/2023', '20/11/2023'], '06/11/2023', 3),
]

with open('data/conferences.csv', 'wb') as f:
       pickle.dump(conferences, f)
