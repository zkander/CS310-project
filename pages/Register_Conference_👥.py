import streamlit as st
import pickle
import pandas as pd
from models.confernceAuthorRegisteration import ConferenceAuthorRegisteration




def registerConference():
    
    try:
        with open ('data/loggedInUser.dat', 'rb') as f:
            loggedInUser = pickle.load(f)
    except FileNotFoundError:
        loggedInUser = None
        
    if loggedInUser == None:
        st.error('You are not logged in!')
        return

    
    st.title('Register Conference Form 👥 ')
    
    # Get All Conferences 
    
    try:
        with open('data/conferences.csv', 'rb') as f:
            conferences = pickle.load(f)
    
    except FileNotFoundError:
        st.error('No conferences found!')
        return

    

    
    confs = []
    columns = ['Name', 'Start Date', 'End Date', 'Submission Deadline', 'Conf Code']
    for conf in conferences:
        confs.append([conf.confName, *conf.dates, conf.submissionDeadline, conf.confCode])
    
    st.subheader('Available Conferences')
    st.data_editor(pd.DataFrame(confs, columns = columns), disabled=True)
     
    
        
        
    
    conf_names = []
    for conf in conferences:
        conf_names.append(conf.confName)
    
    conf_name = st.selectbox('Conference Name', conf_names)

    papers = []
    for paper in loggedInUser.getAuthorPapers():
        if paper.confName != conf_name:
            papers.append(paper.title)
    
    
    
    paper_title = st.selectbox('Paper Title', papers)
    
    
    meal_choice = st.selectbox('Meal Choice', ['Vegan', 'Non-Vegan'])
    
    credit = st.text_input('Credit Card Details')
    if st.button('Validate Credit Card'):
        if len(credit) == 16:
            st.success('Credit Card Validated!')
        else:
            st.error('Invalid Credit Card!')
    
           
    if st.button('Submit'):
        
        loggedInUser.registerInConference(conf_name, paper_title, credit, meal_choice)
        
        st.success('Conference Registered Successfully!')
        st.success('Reciept of fixed 50$ Sent to Your Email!!!')
            
    

if __name__ == '__main__':
    registerConference()
    
    
    
    
    
    






