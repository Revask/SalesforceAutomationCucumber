Feature: login

Background: 
	Given user opens salesforce login page
	
  Scenario: User Login with valid username and valid password
  	  When user on "LoginPage"
  	  And user enters username from propertyfile
  	  And user enters password from propertyfile
  	  And user click on login button
  	 Then verify page title should be "Home Page ~ Salesforce - Developer Edition"
   
   Scenario: User Login with valid username and empty password
   When user on "LoginPage"
   And user enters username from propertyfile
   And user enters password as " "
    And user click on login button
    And user on "PwdErrorMsgLoginPage" 
    Then verify error message "Please enter your password." is displayed
    
    Scenario: User selects remember me checkbox 
     When user on "LoginPage"
  	  And user enters username from propertyfile
  	  And user enters password from propertyfile
  	  And user clicks on Remember me checkbox
  	  And user click on login button
  	  And user on "HomePage"
  	  And user click on user menu button
  	  And user click on logout button
  	  And user on "LoginPage"
  	  Then verify username is displayed and remember me checkbox is selected
  	  
  	  Scenario: User forgets password
  	  When user on "LoginPage"
  	  And clicks on forgot password link
  	 	And user on "ForgotPasswordPage"
  	 	And user enters "srebu@tekarch.com" in username field
  	 	And clicks on continue button
  	 	And user on "ReturnToLoginPage"
  	 	Then verify "We’ve sent you an email with a link to finish resetting your password." message is displayed 
  	 
  	 Scenario: User enters invalid username and invalid password
  	 When user on "LoginPage"
  	 And user enters "123" for username field
  	 And user enters password as "221331"
  	 And user click on login button
  	   	 And user on "InvalidUsernamePwdPage"
  	Then verify error message is present
		  