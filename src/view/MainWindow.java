package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindow extends JFrame
{
	String message = "Wählen Sie eine Option:";
	
	public MainWindow()
	{
		super("Dax");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(createMessagePanel(), BorderLayout.NORTH);
		this.getContentPane().add(createTabPanel(), BorderLayout.CENTER);
		this.getContentPane().add(createEndePanel(), BorderLayout.SOUTH);
		this.setSize(800, 600);
		this.setVisible(true);
	}
	
	public JTabbedPane createTabPanel()
	{
		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel tab1 = new LoadCSVGui();

		JPanel tab2 = new KaufenGui();

		JPanel tab3 = new ConsoleGui();

		JPanel tab4 = new StatistikGui();

		tabbedPane.addTab("Aktieninfos laden", tab1);
		tabbedPane.addTab("Aktien kaufen/verkaufen", tab2);
		tabbedPane.addTab("Depot Ausgabe", tab3);
		tabbedPane.addTab("Statistik", tab4);

		return tabbedPane;
	}
	
	public JPanel createMessagePanel()
	{
		JPanel messagePanel = new JPanel();
		JLabel messageLabel = new JLabel(this.message);
		messagePanel.add(messageLabel);
		return messagePanel;
	}
	
	
	public JPanel createEndePanel()
	{
		JPanel endePanel = new JPanel();		
		JButton ende = new JButton("End");
		ende.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				int answer = JOptionPane.showConfirmDialog(MainWindow.this,
					    "Wollen Sie wirklich beenden?", "Programmende",
					    JOptionPane.YES_NO_OPTION,
					    JOptionPane.QUESTION_MESSAGE);
				if(answer == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				
			}
			
		});
		endePanel.add(ende);
		return endePanel;
	}
}
