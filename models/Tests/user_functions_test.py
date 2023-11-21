from models.user import User

class TestClass:
    
    
    def test_login(self):
        user = User(1, 'user1', '123', 'User1', '1/1/2000', 'Egyptian')
        
        assert user.login('user1', '123') == True
        assert user.login('user1', '1234') == False