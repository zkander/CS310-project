

class User:
       
       def __init__(self, userId, userName, password, name, dob, nationality):
              self.userId = userId
              self.userName = userName
              self.password = password
              self.name = name
              self.dob = dob
              self.nationality = nationality
       
       def __str__(self):
              return f"UserID: {self.userId} UserName: {self.userName} Password: {self.password} Name: {self.name} DOB: {self.dob} Nationality: {self.nationality}"
              

       def login(self, userName, password):
              if self.userName == userName and self.password == password:
                     return True
              else:
                     return False
       