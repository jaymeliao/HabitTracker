# HabitTracker
The Habit Tracker Desktop application is developed using Java, Java Swing Class
Habit Tracker (R) Version 1.0 17/11/24

Desciption
--------------------------------------------
-Habit tracker keep user's record in a table. User clicks "yes" after completing the activity of the day and "no" for incompletion. 

----------------------------------------------
Usage notes
1.Load Button -> load the table after typing existed Habit.  To new a Habit, press load habit to clear table 
  after looking at another habit, clicking save button 
2.Save Button -> save the data or save the new-created habits.
   
   To check if the habit (file) is existed or not ,enter a Habit name in Habit Name text area and click load
   -> load the table data in if yes, if no gives you a blank table which calls makeBlankTable(), create this file 
   until you click the save button (reference to note #1)
   
3. Press yes if you complete the habit in current date, if not then click no.
4.Delete Button -> delect the habit(file) after you enter the habit name, if the habit is not exist, it will gives you 
  an error
5.Exit-> exit the program (Don't forget to press the save button before exit)
6.View-> view the exited habit list

When you finished the habit, which means the table is filled, it will gives you the percentage of the complication:  

you’ll get one of 3 messages. Each contains a % - but if you completed the habit less than 70% of the time it will 
ask if you want to clear the habit and try again (it deletes the file and loads an 
empty table, but that file name won’t exist again until you press “save” or fill up the table completely)

If the date contains a space the program will have issues, so I added some code to make the program check if the date contains
a space - if it does then it won’t let you save the file

-----------------------------------------------
Contributors: Jayme Liao, Jennifer McLean Patrick Doyle
phone:+1　778-806-5727 (Canada)
E-mail:jaymeliao1226@gmail.com

Copyright 2017 © Program Corpration. All right reserved.
The Program is a subject to licence agreement, copyright,
trademark, patent and other laws. 
-----------------------------------------------
-1.0- First Release
