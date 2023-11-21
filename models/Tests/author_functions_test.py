from models.author import Author



class TestClass:
    
    
    
    def test_submitPaper_getAuthorPapers(self):
        author = Author(1, 'user1', '123', 'User1', '1/1/2000', 'Egyptian')
        author.submitPaper('Paper1', 1, 'Abstract', ['keyword1', 'keyword2'], ['coAuthor1', 'coAuthor2'], None, 'document')
        papers = author.getAuthorPapers()
        assert papers[len(papers)-1].title == 'Paper1'
        assert papers[len(papers)-1].paperNo == 1
        assert papers[len(papers)-1].abstract == 'Abstract'
        assert papers[len(papers)-1].keywords == ['keyword1', 'keyword2']
        assert papers[len(papers)-1].coAuthorsNames == ['coAuthor1', 'coAuthor2']
        assert papers[len(papers)-1].confName == None
        assert papers[len(papers)-1].reviewed == False
        assert papers[len(papers)-1].systemDecision == None
        assert papers[len(papers)-1].document == 'document'
    
    def test_registerInConference_getRegisteredConferences(self):
        author = Author(1, 'user1', '123', 'User1', '1/1/2000', 'Egyptian')
        author.submitPaper('Paper1', 1, 'Abstract', ['keyword1', 'keyword2'], ['coAuthor1', 'coAuthor2'], None, 'document')
        author.registerInConference('IEEE', 'Paper1', 'credit', 'meal')
        conferences = author.getRegisteredConferences()
        assert conferences[len(conferences)-1].confName == 'IEEE'
        assert conferences[len(conferences)-1].authorId == 1
        assert conferences[len(conferences)-1].acceptedPaperNo == 1
        assert conferences[len(conferences)-1].mealChoice == 'meal'
        assert conferences[len(conferences)-1].creditCardDetails == 'credit'
        assert conferences[len(conferences)-1].reciept == 'paid: 50$'
    