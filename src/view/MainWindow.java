package view;

import io.csv.StatistikGui;
import model.Depot;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Die Klasse {@code MainWindow} implementiert das Hauptfenster. Es handelt sich um ein
 * JFrame mit tabbed JPanels darin. Es sind 2 JPanels implementiert, eines für den Kauf
 * und den Verkauf von Aktien und eines für die Anzeige der statistischen Kursdaten der
 * Aktien aus dem DAX.
 * Ein JButton laesst das Programm beenden - mit Nachfrage.
 * Das aktuelle Depot wird dem Konstruktor injiziert. Eine Nachricht im Fenster ist eine
 * Objektvariable und wird entsprechend geaendert.
 */
public class MainWindow extends JFrame
{
	/**
	 * Nachricht im MessgaePanel
	 */
	String message = "Wählen Sie eine Option:";

	/**
	 * Konstruktor
	 *
	 * @param depot - das aktuelle Depot; wird zum Kaufen/Verkaufen weitergereicht
	 */
	public MainWindow(Depot depot)
	{
		super("Dax");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(createMessagePanel(), BorderLayout.NORTH);
		this.getContentPane().add(createTabPanel(depot), BorderLayout.CENTER);
		this.getContentPane().add(createEndePanel(), BorderLayout.SOUTH);
		this.setSize(800, 600);
		this.setVisible(true);
	}

	/**
	 * Erzeuge das tabbed Panel bestehend aus 2 Panels.
	 *
	 * @param depot - das aktuelle Depot
	 * @return das tabbed Panel (JTabbedPane)
	 */
	public JTabbedPane createTabPanel(Depot depot)
	{
		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel tab1 = new KaufenGui(depot);
		JPanel tab2 = new StatistikGui();

		tabbedPane.addTab("Aktien kaufen/verkaufen", tab1);
		tabbedPane.addTab("Statistik", tab2);

		return tabbedPane;
	}

	/**
	 * Erzeuge das MessagePanel - Wert der Objektvariablen {@code message} wird angezeigt.
	 *
	 * @return das MessagePanel (JPanel)
	 */
	public JPanel createMessagePanel()
	{
		JPanel messagePanel = new JPanel();
		JLabel messageLabel = new JLabel(this.message);
		messagePanel.add(messageLabel);
		return messagePanel;
	}

	/**
	 * Erzeuge das Panel mit dem Ende-Button. Enthält den JButton und dessen Ereignisbehandlung fuer
	 * Klick. Nachfrage, ob wirklich beendet (JOptionPane).
	 *
	 * @return das JPanel mit dem JButton "End"
	 */
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
