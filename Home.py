import streamlit as st
import pickle
from models.author import Author


def main():
    st.subheader('Sign In')
    
    column = st.columns(1)
    

    username = column[0].text_input('Username')
    password = column[0].text_input('Password', type='password')
    
    
    with open('data/authors.dat', 'rb') as f:
              users = pickle.load(f)
    
    
    

    loggedInUser = None
    flag = False
    if st.button('Sign In', type= 'primary'):
       for i in range (len(users)):
           if users[i].login(username, password):
                  flag = True
                  loggedInUser = users[i]
                  st.success('Logged in successfully!')
                  # Redirect to main page or perform other actions after successful login
                  # For demonstration purposes, just printing a message and redirecting to main page
                  redirect_to_main_page()
                  break
       if not flag:
                 st.error('Incorrect username/password')
    
    
    with open('data/loggedInUser.dat', 'wb') as f:
              pickle.dump(loggedInUser, f)
    
       
              
def redirect_to_main_page():
    # Simulating redirection to the main page
    st.write('Redirecting to the main page...')
    # You can add code to redirect to the main page or perform actions after successful login

if __name__ == '__main__':
    main()