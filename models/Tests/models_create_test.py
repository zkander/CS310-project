
from models.author import Author
from models.user import User
from models.paper import Paper
from models.confernceAuthorRegisteration import ConferenceAuthorRegisteration
from models.conference import Conference




class TestClass:
    
    def test_user_creation(self):
        user = User(1, 'user1', '123', 'User1', '1/1/2000', 'Egyptian')
        assert user.userId == 1
        assert user.userName == 'user1'
        assert user.password == '123'
        assert user.name == 'User1'
        assert user.dob == '1/1/2000'
    
    def test_author_creation(self):
        author = Author(1, 'user1', '123', 'User1', '1/1/2000', 'Egyptian')
        assert author.userId == 1
        assert author.userName == 'user1'
        assert author.password == '123'
        assert author.name == 'User1'
        assert author.dob == '1/1/2000'
    
    def test_paper_creation(self):
        paper = Paper('Conf1', 1, 1, 'Paper1', 'Abstract', ['keyword1', 'keyword2'], ['coAuthor1', 'coAuthor2'], False, None, 'document')
        assert paper.confName == 'Conf1'
        assert paper.authorId == 1
        assert paper.paperNo == 1
        assert paper.title == 'Paper1'
        assert paper.abstract == 'Abstract'
        assert paper.keywords == ['keyword1', 'keyword2']
        assert paper.coAuthorsNames == ['coAuthor1', 'coAuthor2']
        assert paper.reviewed == False
        assert paper.systemDecision == None
        assert paper.document == 'document'
    
    def test_conference_creation(self):
        conf = Conference('IEEE', ['1/1/2011', '1/1/2011'], '2/2/2022', 1)
        assert conf.confName == 'IEEE'
        assert conf.dates == ['1/1/2011', '1/1/2011']
        assert conf.submissionDeadline == '2/2/2022'
        assert conf.confCode == 1
    
    def test_conference_author_registeration_creation(self):
        confRegisteration = ConferenceAuthorRegisteration('IEEE', 1, 1,  'credit','meal', 'reciept')
        assert confRegisteration.confName == 'IEEE'
        assert confRegisteration.authorId == 1
        assert confRegisteration.acceptedPaperNo == 1
        assert confRegisteration.mealChoice == 'meal'
        assert confRegisteration.creditCardDetails == 'credit'
        assert confRegisteration.reciept == 'reciept'
    
    
        
    
    
    
    
    
    
    
        
    
    
    