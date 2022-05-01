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
  
