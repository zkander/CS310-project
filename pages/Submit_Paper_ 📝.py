import streamlit as st
import pickle
from models.paper import Paper




def submitPaper():
     try:
          with open('data/loggedInUser.dat', 'rb') as f:
               loggedInUser = pickle.load(f)
     except FileNotFoundError:
          st.error('You are not logged in!')
          return
     if loggedInUser == None:
          st.error('You are not logged in!')
          return
     
     st.subheader('Submit Paper 📝')
     st.title("Paper Submission Form")

     # Form inputs
     title = st.text_input("Title")
     abstract = st.text_area("Abstract")
     keywords = st.text_input("Keywords (comma-separated)")
     co_author_names = st.text_area("Co-Author Names (one per line)")
     doc = st.file_uploader("Upload Paper")
     

     if st.button('Submit'):
          keywords = keywords.split(',')
          co_author_names = co_author_names.split('\n')
          loggedInUser.submitPaper(title, abstract, keywords, co_author_names, None, str(doc.size)+" bytes")
          st.success('Paper submitted successfully!')
          # clear all fields
          title = ''
          abstract = ''
          keywords = ''
          co_author_names = ''

          
if __name__ == '__main__':
     submitPaper()    
          
