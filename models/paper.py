

class Paper:
       def __init__(self, authorId, title, abstract, keywords, coAuthorsNames, confCode, document):
              self.authorId = authorId
              self.title = title
              self.abstract = abstract
              self.keywords = keywords
              self.coAuthorsNames = coAuthorsNames
              self.confCode = confCode
              self.document = document
       
       def __str__ (self):
              return f"{self.authorId} {self.title} {self.abstract} {self.keywords} {self.coAuthorsNames} {self.confCode} {self.document}"