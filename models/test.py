from user import User
from author import Author
from paper import Paper



# create a list of authors and add them to the file
# create a list of papers and add them to the file


import pickle

author1 = Author(1, "author1", "password1", "author1", "01/01/2001", "Indian")


authors = [
       author1,
       Author(2, "author2", "password2", "author2", "02/02/2002", "Egyptian"),
       Author(3, "author3", "password3", "author3", "03/03/2003", "Indian"),    
]

# with open("../data/authors.csv", "wb") as file:
#        pickle.dump(authors, file)
       
with open("../data/authors.csv", "rb") as file:
       authors = pickle.load(file)
       for author in authors:
              print(author)
              
author1.submitPaper("title1", "abstract1", ["keyword1"], ["coAuthor1"], 1, 1)

     



