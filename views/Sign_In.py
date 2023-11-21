import streamlit as st
import pickle
from models.author import Author


def main():
    st.subheader('Sign In')
    
    

    username = st.text_input('Username')
    password = st.text_input('Password', type='password')
    
    
    with open('data/authors.dat', 'rb') as f:
              users = pickle.load(f)
    
    
    

   
    flag = False
    if st.button('Sign In'):
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
       
              
def redirect_to_main_page():
    # Simulating redirection to the main page
    st.write('Redirecting to the main page...')
    # You can add code to redirect to the main page or perform actions after successful login

if __name__ == '__main__':
    main()