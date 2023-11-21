from models.user import User
from models.paper import Paper

import pickle

class Author(User):
       def __init__(self, userId, userName, password, name, dob, nationality):
              super().__init__(userId, userName, password, name, dob, nationality)
       
       def submitPaper(self, title: str, abstract: str, keywords: list, coAuthorsNames: list, confCode: int, document: str):
              try:
                     with open("data/papers.csv", "rb") as file:
                               papers = pickle.load(file)
                     papers.append(Paper(self.userId, title, abstract, keywords, coAuthorsNames, confCode, document))
                     with open("../data/papers.csv") as file:
                            pickle.dump(papers, file)
                     print("Paper submitted successfully")
              
              except:
                     
                     papers = [Paper(self.userId, title, abstract, keywords, coAuthorsNames, confCode, document)]
                     with open("data/papers.csv", "wb") as file:
                            pickle.dump(papers, file)
                     print("Paper submitted successfully")
       
       
       
              

              
              
        