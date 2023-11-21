import streamlit as st
import pickle
import pandas as pd


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
            papers_table.append([paper.title, paper.abstract, paper.keywords, paper.coAuthorsNames, paper.confCode, paper.document])
        columns = ['Title', 'Abstract', 'Keywords', 'Co-Authors Names', 'Conference Code', 'Document Size']
        
        st.title('Hey ' + loggedInUser.name + '👋!')
        
        st.subheader('Your Papers')
        
        data = pd.DataFrame(papers_table, columns = columns)
        
        st.table(data)
        
        
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