Project 5 - Team 96            

Instructions for compiling and running:               
In order to run the program, use the DiscussionBoard class and run the main method. Everything compiles and runs, and this program utilizes user input and working with a user interface to run a discussion board.                   

Who submitted what:                                 
Luca Marinello - Submitted Vocareum Workspace                       
Alice Dinh - Submitted Project Report                               

Descriptions for classes:                                               
Account - This class is the superclass Account. It has subclasses student and teacher, in this class there are String elements username and password. It has functional getters and setters for both of these fields.                           
Student - This class is a subclass of Account. It will extend Account, and adds a boolean field hasVoted. This field will be used for voting for comments and replies.                                                
Teacher - This class is also a subclass of Account. It will extend Account, but it does not add any fields. The user can select whether they are a student or a teacher, which will give way to different capabilities based on their selection, which is accounted for in the DiscussionBoard class.                                                                     
Message - This is a class that is a superclass of Reply and Comment. It sets the basic framwork for writing and responding on the discussion board. It has String fields content and author, which are the actual message and who wrote it, and a long time, which is used for the timestamp of the message. The class then has appropriate getters and setters as needed.                    
Reply - This is a subclass of Message, it will extend Message. A reply object is the follow up message to a comment that has already been written. It adds the fields score and vote, which will be used for the grading and voting aspects. The toString method prints the reply, timestamp and author, and a vote comparator is also included to order the replies with the most votes. Comment - This is the other subclass of Message, and it will extend Message just as Reply. This object however will also have an arrayList field that is a list of all the replies to the singular comment on the discussion board. It has getters and setters, the toString method has been coded and tested to print the comment on one line, then each of the replies that apply to the comment object underneath it and tabbed over.                                      
Course - This is a class that applies to a given course that is chosen by a teacher. It contains a String object name, the name of the course, and an ArrayList of Forum objects, which is each discussion forum that falls under the course. In this class you can get and set both of those fields, as well as print with a toString method in a manner such that it will print the Course name, followed by every forum that falls underneath the course.                                            
Forum - This is a class that is each discussion forum that will fall under a course, and it will contain a String object topic, the topic of the discussion forum, and and ArrayList of Comments, each of the comments that are in the Discussion Forum. In this class there are getters and setters, as well as a toString method that prints Forum topic, followed by each comment of the Discussion forum on new lines beneath it. As an aside, the toString methods of Forum, Comment, and Reply all work cohesively to create the text visual discussion board.                                                    
DisussionBoard - This is the class that contains the main method and all the helping methods for game functionality. Every previous class and field is utilized in this final class. In this class, the Discussion Board is printed and interacts with the user, as well as it saves the data across runs to files utilizing serializable, and loads that data back in for future runs.

Fixed Errors in Project 4: 
Test Cases - Test cases for user interaction are documented in "Tests.md" and a test case testing the changes of an account's credentials is provided. 
Grading - Teachers are now allowed to assign grades based on the responses given which are visible to students. 
Replies - Users are now able to reply to a specific discussion forum reply. 
