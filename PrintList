//Code created with the help of Stack Overflow question
// https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
//Question by user680406:
//https://stackoverflow.com/users/680406/user680406
//Answer by roflcoptrexception:
//https://stackoverflow.com/users/329637/roflcoptrexception

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PrintList {
	
	ArrayList<String> currentHabits;
	
	PrintList()
	{
		currentHabits = new ArrayList<String>();
		File folder = new File("Habits/");
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		        currentHabits.add(listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		    
		    currentHabits.remove(0);
			ArrayList<String> listOfHabits = new ArrayList<String>();
		    
		    for (String str : currentHabits)
		    {
		    	str = str.replace(".txt", " ");
		    	listOfHabits.add(str);
		    }
		    
		    
			JOptionPane.showMessageDialog(null, "Habits: "+listOfHabits, "List of Habit Names", JOptionPane.PLAIN_MESSAGE);
		    
	}

}

