Test 1: User Log In     
  Steps:            
    1. Launch Application         
    2. User selects username textbox and types it in          
    3. User selects password textbox and types it in          
    4. User clicks login button         
  
  Expected Result: User is successfully logged in, menu is displayed          
  Test Status: Passed             
  
Test 2: User Log In - No Account          
  Steps:          
    1. Launch Application         
    2. User selects username textbox and types it in           
    3. User selects password textbox and types it in          
    4. User clicks login button         
              
  Expected Result: Error Message displayed saying the account does not exist          
  Test Status: Passed         
  
Test 3: Create Account          
  Steps:          
    1. Launch Application         
    2. User selects "Create Account" button         
    3. User taken to account creation page          
    4. User types in username and password
    5. User checks "Student" or "Teacher" box         
    6. User selects "Create Account" button         
    
  Expected Result: Message pops up displaying account successfully created, user taken back to login page         
  Test Status: Passed         
    
Test 4: Create Account - Error Account already exists         
  Steps:          
    1. Launch Application         
    2. User selects "Create Account" button         
    3. User taken to account creation page            
    4. User types in username and password            
    5. User checks "Student" or "Teacher" box           
    6. User selects "Create Account" button           
    
  Expected Result: Message Pops up saying Account already exists, tells user to try a different username            
  Test Status: Passed           
  
Test 5: Create Account - Invalid Role Selection           
  Steps:            
    1. Launch Application           
    2. User selects "Create Account" button           
    3. User taken to account creation page            
    4. User types in username and password              
    5. User checks "Student" and "Teacher" box              
    6. User selects "Create Account" button             
  
  Expected Result: Message Pops up saying "Invalid Role Selection"              
  Test Status: Passed             

Test 6: Create Account - Invalid Role Selection         
    Steps:          
    1. Launch Application         
    2. User selects "Create Account" button         
    3. User taken to account creation page          
    4. User types in username and password          
    5. User checks neithier "Student" or "Teacher" box          
    6. User selects "Create Account" button           
  
  Expected Result: Message Pops up saying "Invalid Role Selection"            
  Test Status: Passed             
  
Test 7: Delete Account          
  Steps:            
    1. Launch Application           
    2. User selects username textbox and types it in            
    3. User selects password textbox and types it in              
    4. User clicks login button             
    5. User clicks delete account button              
  
  Expected Result: Message Pops up saying "Account Deleted", User taken back to log in screen, when user attempts to log back in 
   a message pops up saying invalid credentials.              
  Test Status: Passed             

Test 8: Change Password         
  Steps:                  
    1. Launch Application                             
    2. User selects username textbox and types it in          
    3. User selects password textbox and types it in              
    4. User clicks login button             
    5. User clicks change username button             
    6. User prompted to enter new username              
    7. User enters new username             
  
  Expected Result: Message Pops up saying "Username Successfully Changed", username changed in welcome message, if user logs out
   the old username no longer works to log in                 
  Test Status: Passed                     
        
Test 9: Change Username             
  Steps:                    
    1. Launch Application                 
    2. User selects username textbox and types it in              
    3. User selects password textbox and types it in                
    4. User clicks login button                 
    5. User clicks change password button             
    6. User prompted to enter new password              
    7. User enters new password               
    
  Expected Result: Message pops up saying "Password Successfully Changed", if user logs out the old password no longer works
    to log in             
  Test Status: Passed             
  
Test 10: Proceed to Discussion Board              
  Steps:                    
    1. Launch Application                             
    2. User selects username textbox and types it in                  
    3. User selects password textbox and types it in                  
    4. User clicks login button                               
    5. User clicks "Proceed to Discussion Board" button                     
    
  Expected Result: Discussion Board is displayed                  
  Test Result: Passed               
  
Test 11: Teacher Creates a Class and Forums    
  Steps: 
    1. Launch Application                             
    2. User selects username textbox and types it in                  
    3. User selects password textbox and types it in                  
    4. User clicks login button                               
    5. User clicks "Proceed to Discussion Board" button         
    6. User clicks "Create Class Button"
    
  Expected Result: Prompt for Course name pops up, user enters name, then is prompted to enter forum names until the user says they would no longer like 
    to. 
  Test Result: Passed

Test 12: Choose Class and Forum       
  Steps: 
    1. Launch Application                             
    2. User selects username textbox and types it in                  
    3. User selects password textbox and types it in                  
    4. User clicks login button                               
    5. User clicks "Proceed to Discussion Board" button         
    6. User clicks "Choose Class" button
    
  Expected Result: Prompt for choosing class pops up with list of classes, next a prompt for choosing a forum pops up with a list of forums, user is taken
    to discussion forum
  Test Result: Passed               
          
Test 13: Student Accesses class and forums, then exits              
  Steps: 
    1. Launch Application                             
    2. User selects username textbox and types it in                   
    3. User selects password textbox and types it in                  
    4. User clicks login button                               
    5. User clicks "Proceed to Discussion Board" button                    
    6. User clicks "Choose Class" button                    
    7. User chooses class from list                 
    8. User chooses forum from list                 
    9. User taken to Discussion Forum Page                  
    10. User clicks "Exit" button                 
    
  Expected Result: Confirmation Message pops up, User selects yes and is taken back to the Discussion Board menu        
  Test Result: Passed             

Test 14: Teacher Deletes Forum            
  Steps:                
    1. Launch Application                             
    2. User selects username textbox and types it in                   
    3. User selects password textbox and types it in                  
    4. User clicks login button  (Teacher)                             
    5. Teacher clicks "Proceed to Discussion Board" button                    
    6. Teacher clicks "Choose Class" button                    
    7. Teacher chooses class from list                 
    8. Teacher chooses forum from list                 
    9. Teacher taken to Discussion Forum Page       
    10. Teacher clicks "Delete Forum"
    
  Expected Result: Confirmation pops up, Teacher taken back to Discussion Board Menu Page, Forum no longer pops up in list of forums
  Test Result: Passed
  
Test 15: Student adds New Reply to Forum Topic          
  Steps:                    
    1. Launch Application                                                       
    2. User selects username textbox and types it in                                 
    3. User selects password textbox and types it in                        
    4. User clicks login button  (Student)                              
    5. Student clicks "Proceed to Discussion Board" button                    
    6. Student clicks "Choose Class" button                    
    7. Student chooses class from list                 
    8. Student chooses forum from list                 
    9. Student taken to Discussion Forum Page         
    10. Student clicks "New Reply" button                           
    11. Student asked if they want to import reply from a file                  
    12. Student selects "No"                        
    13. Student prompted to type out reply                      
    14. Student clicks "Ok"                         
   
  Expected Result: Reply displayed along with students' username and timestamp              
  Test Result: Passed                                     
  
Test 16: Student cancels New Reply on Discussion Forum                            
  Steps:                          
    1. Launch Application                             
    2. User selects username textbox and types it in                   
    3. User selects password textbox and types it in                  
    4. User clicks login button  (Student)                             
    5. Student clicks "Proceed to Discussion Board" button                    
    6. Student clicks "Choose Class" button                    
    7. Student chooses class from list                 
    8. Student chooses forum from list                 
    9. Student taken to Discussion Forum Page         
    10. Student clicks "New Reply" button                                       
    11. Student asked if they want to import reply from a file                                  
    12. Student selects "No"                                
    13. Student prompted to type out reply                        
    14. Student clicks "Cancel"                           
   
  Expected Result: Discussion Forum re-displayed as was previously                      
  Test Result: Passed                               
  
Test 17: Teacher Comments on Student Reply                              
  Steps: 
    1. Launch Application                             
    2. User selects username textbox and types it in                   
    3. User selects password textbox and types it in                  
    4. User clicks login button  (Teacher)                             
    5. Teacher clicks "Proceed to Discussion Board" button                    
    6. Teacher clicks "Choose Class" button                    
    7. Teacher chooses class from list                 
    8. Teacher chooses forum from list                 
    9. Teacher taken to Discussion Forum Page               
    10. Teacher clicks "Comment" button                         
    11. Teacher asked which Student Reply to Comment on, clicks the number of the Student Reply they want to comment on               
    12. Teacher prompted to enter comment                     
    
  Expected Result: Teacher Comment shows up on forum underneath the reply they commented on                 
  Test Result: Passed                       
  
Test 18: Different Student comments on Student Reply                
  Steps:
    1. Launch Application                             
    2. User selects username textbox and types it in                   
    3. User selects password textbox and types it in                  
    4. User clicks login button  (Student)                             
    5. Student clicks "Proceed to Discussion Board" button                    
    6. Student clicks "Choose Class" button                    
    7. Student chooses class from list                 
    8. Student chooses forum from list                 
    9. Student taken to Discussion Forum Page               
    10. Student clicks "Comment" button                         
    11. Student asked which Student Reply to Comment on, clicks the number of the Student Reply they want to comment on               
    12. Student prompted to enter comment                     
  Expected Result: Student Comment shows up underneath reply and any other comments                           
  Test Result: Passed                         
    
Test 19: Student adds New Reply via File Import 
  Steps:                    
    1. Launch Application                                                       
    2. User selects username textbox and types it in                                 
    3. User selects password textbox and types it in                        
    4. User clicks login button  (Student)                              
    5. Student clicks "Proceed to Discussion Board" button                    
    6. Student clicks "Choose Class" button                    
    7. Student chooses class from list                 
    8. Student chooses forum from list                 
    9. Student taken to Discussion Forum Page         
    10. Student clicks "New Reply" button                           
    11. Student asked if they want to import reply from a file                  
    12. Student selects "Yes"                        
    13. Student prompted to enter file name                        
  
  Expected Result: New Reply written in file printed onto forum. Printed in a manner such that it is underneath any comments to previous replies
  Test Result: Passed
  
Test 20: Teacher adds New Forum via File Import         
  Steps:
    1. Launch Application                             
    2. User selects username textbox and types it in                   
    3. User selects password textbox and types it in                  
    4. User clicks login button  (Teacher)                             
    5. Teacher clicks "Proceed to Discussion Board" button      
    6. Teacher selects "Create Forum" button
    7. Teacher given list of classes to choose from
    8. Teacher asked if they want to import a forum name from a file, selects "Yes"
    9. Teacher prompted to enter file name
    
  Expected Result: Teacher enters file name, if file extits the forum is added to the course, if file does not exist then an error message pops up
  Test Result: Passed

Test 21: Teacher Grades Student Replies       
  Steps: 
    1. Launch Application                             
    2. User selects username textbox and types it in                   
    3. User selects password textbox and types it in                  
    4. User clicks login button  (Teacher)                             
    5. Teacher clicks "Proceed to Discussion Board" button        
    6. Teacher clicks "Choose Class"                            
    7. Teacher chooses Class                      
    8. Teacher chooses Forum                      
    9. Teacher choses "Grade Student"                   
   
  Expected Result: Teacher prompted to choose Student from list of students, taken to page with all the students forum topic replies, clicks Assign Grade
    button, prompted to enter grade, then exits page to get back to forum.                  
  Test Result: Passed                 

Test 22: Student views Assigned Grade               
  Steps:
    1. Launch Application
    2. User selects username textbox and types it in                   
    3. User selects password textbox and types it in                  
    4. User clicks login button  (Student)                             
    5. Student clicks "Proceed to Discussion Board" button        
    6. Student clicks "Choose Class"                            
    7. Student chooses Class                      
    8. Student chooses Forum        
    9. Student clicks "See Grade" button
    
  Expected Result: Message pops up with grade for student, pops up with message saying there is no grade yet if there is no grade.
  Test Result: Passed
  
