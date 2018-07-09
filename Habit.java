# HabitTracker
//The Habit Tracker Desktop application is developed by Java, Java Swing Class


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Habit extends JFrame {

	DefaultTableModel model;
	JTextField enterDate;
	JTextField enterHabitName;
	JTable table;
	int curX, curY;

	void startFresh() {
		File file = new File("Habits/" + enterHabitName.getText() + ".txt");
		file.delete();
		makeBlankTable();
	}

	void writeToTable(Object x) {
		if (curY < 7)
			table.setValueAt(x, curY, curX);
		// update curX and curY
		if ((curX < 6) && (curY < 7)) {
			curX++;
		} else {
			if (curY < 6) {
				curX = 0;
				curY++;
			} else {
				curY = 7;
				curX = 0;

				int checkedCount = 0;
				int otherCount = 0;
				try (LineNumberReader countCompleted = new LineNumberReader(
						new FileReader("Habits/" + enterHabitName.getText() + ".txt"))) {
					String currentLine;
					while ((currentLine = countCompleted.readLine()) != null) {
						for (String element : currentLine.split(" ")) {
							if (element.equals("checked")) {
								checkedCount++;
							} else if (element.equals("crossed") || element.equals("empty")) {
								otherCount++;
							}
						}
					}
				} catch (FileNotFoundException e) {
					writeTable();
				} catch (IOException e) {
					System.out.println("IO Exception.");
				}
				if (checkedCount >= 0 && otherCount >= 0) {
					TheMath math = new TheMath(otherCount, checkedCount);
					String congratsLine = "You completed the habit " + math.number + "% of the time.";

					if (math.number.intValueExact() < 50) {
						int result = JOptionPane.showConfirmDialog(null,
								congratsLine + "\nWould you like to clear this habit and start again?",
								"There was an attempt!", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							startFresh();

						}

					} else if (math.number.intValueExact() < 70) {
						int result = JOptionPane.showConfirmDialog(null, "Solid effort! " + congratsLine
								+ "\nTo perfect this habit, you could clear the page and start again. Would you like to do that?",
								"Nice try!", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							startFresh();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Fantastic! " + congratsLine, "Congrats!",
								JOptionPane.PLAIN_MESSAGE);
					}

				} 
				else {
					JOptionPane.showMessageDialog(null,
							"Something went wrong! Try hitting that last yes/no button one more time.", "Oops!",
							JOptionPane.PLAIN_MESSAGE);
				}

			}
		}
	}

	void writeTable() {
		try {
			String name = enterHabitName.getText();

			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Name cannot be empty!");
				return;
			}

			String date = enterDate.getText();

			if (date.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Date cannot be empty!");
				return;
			} else if (date.contains(" ")) {
				JOptionPane.showMessageDialog(this, "Date cannot contain spaces!");
				return;
			}

			FileWriter f = new FileWriter("Habits/" + name + ".txt");
			BufferedWriter bf = new BufferedWriter(f);
			bf.write(date + " " + curX + " " + curY + " ");

			// iterate over the table and store the image description of each cell

			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 7; j++) {
					ImageIcon temp = (ImageIcon) table.getValueAt(i, j);
					String desc = temp.getDescription();
					bf.write(desc + " ");
				}
			}

			bf.close();
			f.close();
			JOptionPane.showMessageDialog(this, "Habit saved successfully");

		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, "Cannot save!");
		}
	}

	void makeBlankTable() {

		LocalDate todaysDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String theDate = todaysDate.format(formatter);
		enterDate.setText(theDate);

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				String imageF = "";

				imageF = "qi.png"; // blank white image

				ImageIcon icon = new ImageIcon(imageF, "empty");
				table.setValueAt(icon, i, j);
			}
		}

		curX = 0;
		curY = 0;
	}

	void loadTable() {
		try {
			String name = enterHabitName.getText();
			name = name.trim();

			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Name cannot be empty!");
				return;
			}

			File f = new File("Habits/" + name + ".txt");
			if (f.exists() && !f.isDirectory()) {
				Scanner sc = new Scanner(f);
				// loading date
				enterDate.setText(sc.next());

				// loading curX and curY
				curX = sc.nextInt();
				curY = sc.nextInt();

				// loading the table
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 7; j++) {
						String desc = sc.next();
						String imageF = "";

						if (desc.equals("empty"))
							imageF = "qi.png"; // blank white image
						if (desc.equals("checked"))
							imageF = "ci.png"; // image from: "Symbols Great Job",
												// https://www.freeiconspng.com/img/31177
						if (desc.equals("crossed"))
							imageF = "xi.jpg"; // image from: http://www.yim778.com/group/red-sad-face/

						ImageIcon icon = new ImageIcon(imageF, desc);
						table.setValueAt(icon, i, j);
					}
				}
			} else // if the file doesn't exist then just make it blank
			{
				makeBlankTable();
			}
		} // end of try
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Cannot load the file!");
			makeBlankTable();
		}
	}

	Habit() {
		curX = 0;
		curY = 0;

		JPanel backgroundPanel = new BackgroundPanel();
		backgroundPanel.setLayout(new BorderLayout());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Welcome to Habit Tracker!");
		this.setSize(900, 1000);
		this.setLayout(new BorderLayout());
		this.setContentPane(backgroundPanel);

		// HABIT TABLE //

		JPanel habitTable = new JPanel();
		habitTable.setOpaque(false);
		habitTable.setLayout(new BorderLayout());

		ImageIcon qIcon = new ImageIcon("qi.png", "empty");

		ImageIcon[][] data = new ImageIcon[7][];

		for (int i = 0; i < 7; i++) {
			data[i] = new ImageIcon[7];// initialize
			// initialize each cell the row data[i]
			for (int j = 0; j < 7; j++) {
				data[i][j] = qIcon;
			}
		}

		DefaultTableModel model = new DefaultTableModel(data,
				new Object[] { null, null, null, null, null, null, null }) {

			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			public boolean isCellEditable(int r, int c) {
				return false;
			}

		};

		table = new JTable(model);
		table.setShowGrid(true);
		table.setTableHeader(null);
		table.setGridColor(Color.black);
		table.setRowHeight(74);
		table.setOpaque(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane pane = new JScrollPane(table);
		////////////////////////

		// FIELD + TEXT AREA TO ADD HABIT NAME //

		JPanel nameField = new JPanel();
		nameField.setOpaque(false);
		JLabel habitName = new JLabel("    Habit name:");
		habitName.setFont(new Font("CMON NEAR", Font.BOLD, 18));

		habitName.setBounds(0, 0, 5, 5);
		nameField.add(habitName, BorderLayout.NORTH);
		habitName.setVisible(true);

		enterHabitName = new JTextField(10);
		nameField.add(enterHabitName, BorderLayout.NORTH);
		enterHabitName.setEditable(true);
		nameField.setAlignmentX(RIGHT_ALIGNMENT);

		JButton loadB = new JButton("Load Habit");
		nameField.add(loadB);
		loadB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTable();
			}
		});

		// FIELD + TEXT AREA TO ADD START DATE OF HABIT //

		JPanel dateField = new JPanel();
		dateField.setOpaque(false);
		JLabel startDate = new JLabel("Start date:");
		startDate.setFont(new Font("CMON NEAR", Font.BOLD, 18));

		startDate.setBounds(0, 0, 5, 5);
		dateField.add(startDate, BorderLayout.NORTH);
		startDate.setVisible(true);
		JLabel dateFormat = new JLabel("dd/mm/yyyy");
		dateFormat.setFont(new Font("Arial", Font.ITALIC, 14));

		enterDate = new JTextField(10);
		dateField.add(enterDate, BorderLayout.WEST);
		dateField.add(dateFormat, BorderLayout.WEST);
		enterDate.setEditable(true);

		dateField.setAlignmentX(RIGHT_ALIGNMENT);

		// SAVE / CANCEL BUTTON //

		JToolBar buttons = new JToolBar("Still draggable");
		buttons.setFloatable(false);
		setPreferredSize(new Dimension(900, 500));
		buttons.setOpaque(false);
		JButton saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(100, 50));
		JButton cancelButton = new JButton("Exit");
		buttons.add(saveButton, BorderLayout.SOUTH);
		JButton viewButton = new JButton("View");

		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				PrintList print = new PrintList(); 
			}
		});

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startFresh();
			}
		});

		buttons.addSeparator();
		buttons.add(deleteButton, BorderLayout.SOUTH);
		buttons.addSeparator();
		buttons.add(cancelButton, BorderLayout.SOUTH);
		buttons.addSeparator();
		buttons.add(viewButton, BorderLayout.SOUTH);
		saveButton.setAlignmentX(CENTER_ALIGNMENT);
		cancelButton.setAlignmentX(CENTER_ALIGNMENT);

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeTable();
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// CREATES "FIELDS" PANEL WITH BOTH FIELDS //

		JPanel fields = new JPanel();
		fields.setOpaque(false);
		fields.setAlignmentX(RIGHT_ALIGNMENT);

		BoxLayout boxLayout = new BoxLayout(fields, BoxLayout.PAGE_AXIS);
		fields.setLayout(boxLayout);
		fields.add(nameField);
		fields.add(dateField);
		this.add(fields, BorderLayout.NORTH);
		this.setVisible(true);

		// Jayme's panel

		buttons.setAlignmentX(CENTER_ALIGNMENT);

		JPanel yesNo = new JPanel();
		yesNo.setOpaque(false);
		JLabel COM = new JLabel("Completed? :");
		COM.setFont(new Font("CMON NEAR", Font.BOLD, 18));

		COM.setBounds(0, 0, 5, 5);
		yesNo.add(COM, BorderLayout.SOUTH);
		COM.setVisible(true);

		JButton b1 = new JButton("Yes");

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ImageIcon cIcon = new ImageIcon("ci.png", "checked");
				writeToTable(cIcon);
			}
		});

		JButton b2 = new JButton("No");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae_b2) {
				ImageIcon xIcon = new ImageIcon("xi.jpg", "crossed");
				writeToTable(xIcon);

			}
		});

		yesNo.add(b1, BorderLayout.CENTER);
		b1.setVisible(true);
		yesNo.add(b2, BorderLayout.CENTER);
		b2.setVisible(true);

		JPanel bothButtons = new JPanel();
		bothButtons.setOpaque(false);
		BoxLayout bottomButtons = new BoxLayout(bothButtons, BoxLayout.PAGE_AXIS);
		bothButtons.setLayout(bottomButtons);
		bothButtons.add(yesNo);
		bothButtons.add(buttons);

		this.add(pane, BorderLayout.CENTER);
		this.add(bothButtons, BorderLayout.SOUTH);
		this.setVisible(true);
	}

}


