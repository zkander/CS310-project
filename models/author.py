from models.user import User
from models.paper import Paper
from models.confernceAuthorRegisteration import ConferenceAuthorRegisteration

import pickle

class Author(User):
       def __init__(self, userId: int, userName: str, password: str, name: str, dob: str, nationality: str):
              super().__init__(userId, userName, password, name, dob, nationality)
       
       def submitPaper(self, title: str,paperNo: int, abstract: str, keywords: list, coAuthorsNames: list, confCode: int, document: str):
              try:
                     with open("data/papers.csv", "rb") as file:
                               papers = pickle.load(file)
                     papers.append(Paper(None, self.userId, paperNo, title, abstract, keywords, coAuthorsNames, False, None, document ))
                     with open("data/papers.csv", "wb") as file:
                            pickle.dump(papers, file)
                     print("Paper submitted successfully")
              
              except:
                     
                     papers = [Paper(None, self.userId, paperNo, title, abstract, keywords, coAuthorsNames, False, None, document )]
                     with open("data/papers.csv", "wb") as file:
                            pickle.dump(papers, file)
                     print("Paper submitted successfully")
       
       def registerInConference(self, confName: str, paperTitle: str, creditCardDetails: str, mealChoice: str):
              
              papers = self.getAuthorPapers()
    
              for paper in papers:
                     if paper.title == paperTitle:
                            selectedPaper = paper
                            paper.confName = confName
                            paper.reviewed = True
                            paper.systemDecision = 'Reviewed'
                            break
              
              with open('data/papers.csv', 'wb') as f:
                     pickle.dump(papers, f)
              
              
              try:
                     with open('data/registeredConferences.csv', 'rb') as f:
                            conferences = pickle.load(f)
                     conferences.append(ConferenceAuthorRegisteration(confName, self.userId,selectedPaper.paperNo, mealChoice, creditCardDetails, 'paid: 50$'))
              except FileNotFoundError:
                     conferences = []
                     conferences.append(ConferenceAuthorRegisteration(confName, self.userId,selectedPaper.paperNo, mealChoice, creditCardDetails, 'paid: 50$'))
              
              with open('data/registeredConferences.csv', 'wb') as f:
                     pickle.dump(conferences, f)
                     
              
              
       
       def getAuthorPapers(self):
              try:
                     with open("data/papers.csv", "rb") as file:
                            papers = pickle.load(file)
                     authorPapers = []
                     for paper in papers:
                            if paper.authorId == self.userId:
                                   authorPapers.append(paper)
                     return authorPapers
              except:
                     return []

       def getRegisteredConferences(self):
              try:
                     with open('data/registeredConferences.csv', 'rb') as f:
                            conferences = pickle.load(f)
                     registeredConferences = []
                     for conference in conferences:
                            if conference.authorId == self.userId:
                                   registeredConferences.append(conference)
                     return registeredConferences
              except FileNotFoundError:
                     return []
       
       
       
       
              

              
              
        