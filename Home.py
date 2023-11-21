import streamlit as st
import pickle
import pandas as pd
from models.confernceAuthorRegisteration import ConferenceAuthorRegisteration


def main():
   
    
    
    with open('data/authors.dat', 'rb') as f:
              users = pickle.load(f)
    
    try:
        with open('data/loggedInUser.dat', 'rb') as f:
                loggedInUser = pickle.load(f)
    except FileNotFoundError:
        loggedInUser = None
    
    if loggedInUser != None:
        
        papers_table = []
        for paper in loggedInUser.getAuthorPapers():
            papers_table.append([paper.title,paper.paperNo, paper.abstract, paper.keywords, paper.coAuthorsNames, paper.confName,paper.reviewed, paper.document])
        columns = ['Title','Paper No.', 'Abstract', 'Keywords', 'Co-Authors Names', 'Conference Name','Reviewed', 'Document Size']
        
        st.title('Hey ' + loggedInUser.name + '👋!')
        
        st.subheader('Your Papers 📝')
        
        data = pd.DataFrame(papers_table, columns = columns)
        
        st.data_editor(data, disabled=True)
        
        
        st.subheader('Registerered Conferences 👥')
        
        conferences_table = []
        for conf in loggedInUser.getRegisteredConferences():
            print(conf.authorId)
            conferences_table.append([conf.confName,conf.authorId, conf.acceptedPaperNo, conf.creditCardDetails, conf.mealChoice, conf.reciept])
        columns = ['Conference Name', 'Author ID', 'Accepted Paper No.', 'Credit Card Details', 'Meal Choice', 'Reciept']
        
        data = pd.DataFrame(conferences_table, columns = columns)       
        st.data_editor(data, disabled=True) 
        
        
        
        if st.button('Logout'):
            loggedInUser = None
            with open('data/loggedInUser.dat', 'wb') as f:
                    pickle.dump(loggedInUser, f)
        
    else:
        st.subheader('Sign In')
    
        column = st.columns(1)

        username = column[0].text_input('Username')
        password = column[0].text_input('Password', type='password')
        flag = False
        if st.button('Sign In', type= 'primary'):
            for i in range (len(users)):
                if users[i].login(username, password):
                        flag = True
                        loggedInUser = users[i]
                        st.success('Logged in successfully!')
                        break
            if not flag:
                        st.error('Incorrect username/password')
        
            
            with open('data/loggedInUser.dat', 'wb') as f:
                    pickle.dump(loggedInUser, f)
    
       
              


if __name__ == '__main__':
    main()