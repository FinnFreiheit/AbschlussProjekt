package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.JTextComponent;

import model.Dax;

/**
 * Java Swing JPanel, in der man die historischen Kursdaten einer Aktie aus dem DAX einsehen kann oder die
 * Tagesinformationen aller Aktien zu einem bestimmten Datum. Die Aktie kann aus einer Liste (JComboBox)
 * ausgewaehlt werden. Die Datumseingabe erfolgt ueber eine editierbare JComboBox.
 * Je nachdem, ob Aktie oder Datum ausgewaehlt wurde, wird eine JTable mit den entsprechenden Informationen
 * erstellt. Dazu wird die Klasse {@code MyTableModel} verwendet, die von {@code AbstractTableModel} erbt.
 * {@code AbstractTableModel} enthaelt Methoden zur Erstellung einer JTable. {@code MyTableModel} stellt den
 * Adapter zwischen der JTable und den eigentlichen Daten (und den Spaltenueberschriften) dar.
 *
 */
public class StatistikGui extends JPanel
{
	/**
	 * Die Spaltenueberschriften der Tabelle.
	 */
	String[] titles;
	/**
	 * Die Daten, die in der Tabelle aufgelistet werden.
	 */
	String[][] data;
	/**
	 * Key / Aktienkuerzel mit einem Anfangswert.
	 */
	String key = "SIE.DE";
	/**
	 * Datum.
	 */
	String date;
	/**
	 * Message.
	 */
	String message = "Sie haben eine Aktie ausgewählt! Aktuell: " + this.key;
	/**
	 * Message label, zeigt die {@code message} an.
	 */
	JLabel messageLabel;
	/**
	 * Eingabefeld zur Eingabe des Datums.
	 */
	JComboBox<String> dateList;
	/**
	 * Auswahlfeld zur Auswahl einer Aktie.
	 */
	JComboBox<String> selectAktie;
	/**
	 * Die Tabelle, die die Kursinformationen anzeigt.
	 */
	JTable table;
	/**
	 * Das JPanel, das die Tabelle enthaelt.
	 */
	JPanel tablePanel;
	/**
	 * Das Model, das die Verbindung zwischen den Daten und der Tabelle herstellt (abgeleitet
	 * von {@code AbstractTableModel}).
	 */
	MyTableModel model;

	/**
	 * Konstruktor
	 */
	public StatistikGui()
	{
		super();
		this.setLayout(new BorderLayout());

		this.data = this.getDataKey(this.key);
		this.titles = this.getTitlesKey(this.key);
		this.add(createSelectionPanel(), BorderLayout.NORTH);
		this.add(createTable(this.data, this.titles), BorderLayout.CENTER);
		
		// this.date = heute --> Voreintragung im Datumsfeld
		LocalDate today = LocalDate.now(); 
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.date = today.format(myFormat);
	}

	/**
	 * Erzeugt ein Auswahlpanel mit Nachrichten und mit den beiden Auswahlfeldern fuer
	 * die Aktie bzw. das Datum.
	 *
	 * @return das JPanel mit den Auswahlfeldern und den Nachrichten.
	 */
	public JPanel createSelectionPanel()
	{
		JPanel panel = new JPanel(new BorderLayout());
		
		// linkes Panel mit 2 Labels fuer die Informationen an den Nutzer
		JPanel left = new JPanel(new GridLayout(0,1));
		JLabel label = new JLabel("Wählen Sie eine Aktie oder wählen Sie ein Datum :");
		this.messageLabel = new JLabel(message);
		this.messageLabel.setForeground(Color.red);
		left.add(label);
		left.add(this.messageLabel);
		
		// rechtes Panel mit Aktienwahl und Datumseingabe
		JPanel right = new JPanel(new GridLayout(0,1));
		// Aktienauswahl
		Map<String, String> dax = Dax.getDax();
		String[] aktien = new String[dax.size()];
		int index=0;
		for(String key : dax.keySet())
		{
			aktien[index++] = key;
		}
		Arrays.parallelSort(aktien);
		this.selectAktie = new JComboBox<>(aktien);
		this.selectAktie.setSelectedItem(this.key);
		// Ereignisbehandlung Aktienauswahl
		this.selectAktie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
		        String aktie = (String)cb.getSelectedItem();
		        StatistikGui.this.key = aktie;
		        StatistikGui.this.message = "Sie haben eine Aktie ausgewählt! Aktuell: " + StatistikGui.this.key;
		        StatistikGui.this.messageLabel.setText(StatistikGui.this.message);
		        StatistikGui.this.data = StatistikGui.this.getDataKey(StatistikGui.this.key);
		        StatistikGui.this.titles = StatistikGui.this.getTitlesKey(StatistikGui.this.key);
		        StatistikGui.this.model.update(StatistikGui.this.data, StatistikGui.this.titles);
			}
			
		});
		
		// Datumeingabe
		LocalDate today = LocalDate.now(); 
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatToday = today.format(myFormat);
	    String[] date = { formatToday };
	    this.dateList = new JComboBox<>(date);
	    this.dateList.setEditable(true);
		// Ereignisbehandlung Datumauswahl (für Auswahl)
		this.dateList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
		        String datum = (String)cb.getSelectedItem();
		        StatistikGui.this.date = datum;
		        StatistikGui.this.message = "Sie haben ein Datum ausgewählt! Aktuell: " + StatistikGui.this.date;
		        StatistikGui.this.messageLabel.setText(StatistikGui.this.message);
		        StatistikGui.this.data = StatistikGui.this.getDataDate(StatistikGui.this.date);
		        StatistikGui.this.titles = StatistikGui.this.getTitlesDate();
		        StatistikGui.this.model.update(StatistikGui.this.data, StatistikGui.this.titles);
			}
			
		});
	    
		right.add(this.selectAktie);
		right.add(this.dateList);
		
		panel.add(left, BorderLayout.WEST);
		panel.add(right, BorderLayout.EAST);
		return panel;
	}

	/**
	 * Erzeugt scrollbare Tabelle, um die historischen Datenlisten und Tagesinformationen auszugeben.
	 *
	 * @param data   die Daten, die in der Tabelle angezeigt werden
	 * @param titles die Spaltenueberschriften in der Tabelle
	 * @return das JPanel, das die Tabelle enthaelt
	 */
	public JPanel createTable(String[][] data, String[] titles)
	{
		this.tablePanel = new JPanel();
		this.model = new MyTableModel(data, titles);
		this.table = new JTable( this.model );
		this.table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(this.table);
		scrollPane.setPreferredSize(new Dimension(760, 360));
		this.tablePanel.add(scrollPane);
		return this.tablePanel;
	}

	/**
	 * diese Methode liest alle Daten aus der Datei <Aktienkuerzel>.de aus und speichert
	 * sie in einem zweidimensionalen Array (zeilen x spalten). Das Array wird zurueckgegeben.
	 *
	 * @param key - das Aktienkuerzel
	 * @return die Kursdaten der Aktie als String[][]
	 */
	public String[][] getDataKey(String key)
	{
		// alle Daten aus einer Datei - key ist Aktienkuerzel
		String fileName = "./database/" + key + ".csv";
		List<List<String>> records = new ArrayList<List<String>>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String line;
		    line = br.readLine();
		    this.titles = line.split(",");
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        records.add(Arrays.asList(values));
		    }
		}
		catch(IOException e){
			System.err.println("Fehler beim Öffnen/Lesen der Datei.");
		}
		String[][] data = listToArray(records);
		return data;
	}

	/**
	 * diese Methode liest die Daten aus allen Dateien <Aktienkuerzel>.de zu dem bestimmten
	 * Datum {@code date} aus und speichert sie in einem zweidimensionalen Array
	 * (Aktien x spalten). Das Array wird zurueckgegeben.
	 *
	 * @param date - das Datum
	 * @return die Kursdaten aller Aktien zum Datum {@code date} als String[][]
	 */
	public String[][] getDataDate(String date)
	{
		// Daten aus allen Dateien (jeweils Zeile des Datums) - date ist Datum
		Map<String, String> dax = Dax.getDax(); // alle Aktien (Kuerzel + Name)
		Set<String> keys = dax.keySet();		// alle Kuerzel

		List<List<String>> records = new ArrayList<List<String>>();
		
		for(String key : keys)
		{
			String fileName = "./database/" + key + ".csv";
			try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			    String line;
			    line = br.readLine();
			    while ((line = br.readLine()) != null) {
			        String[] values = line.split(",");
			        if(values[0].equals(date))
					{
						values[0] = key;
						records.add(Arrays.asList(values));
					}
			    }
			}
			catch(IOException e){
				System.err.println("Fehler beim Öffnen/Lesen der Datei.");
			}
		}
		String[][] data = listToArray(records);
		return data;
	}

	/**
	 * Diese Methode liest die Spaltenueberschriften aus der Datei <Aktienkuerzel>.de
	 * aus. Es handelt sich um die jeweils erste Zeile in der CSV-Datei. Die Spalten-
	 * ueberschriften werden als String-Array zurueckgegeben.
	 *
	 * @param key - das Aktienkuerzel
	 * @return die Spaltenueberschriften als String[]
	 */
	public String[] getTitlesKey(String key)
	{
		String fileName = "database/" + key + ".csv";
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String line;
		    line = br.readLine();
		    this.titles = line.split(",");
		}
		catch(IOException e){
			System.err.println("Fehler beim Öffnen/Lesen der Datei.");
		}
		return this.titles;
	}

	/**
	 * Diese Methode liest die Spaltenueberschriften aus der Datei <Aktienkuerzel>.de
	 * aus und aendert die erste Spaltenueberschrift von {@code Date} nach {@code Share}.
	 * Es handelt sich um die jeweils erste Zeile in der CSV-Datei, die dann fuer den
	 * ersten Eintrag angepasst wird. Die Spaltenueberschriften werden als String-Array
	 * zurueckgegeben.
	 *
	 * @return die Spaltenueberschriften als String[]
	 */
	public String[] getTitlesDate()
	{
		String[] titles = getTitlesKey(this.key);
		titles[0] = "Share";
		this.titles = titles;
		return this.titles;
	}

	/**
	 * Hilfsmethode, um aus einer {@code List<List<String>>} ein {@code String[][]} zu
	 * erzeugen.
	 *
	 * @param records - die Daten im Format {@code List<List<String>>}
	 * @return die Daten im Format {@code String[][]}
	 */
	private String[][] listToArray(List<List<String>> records)
	{
		String[][] data = records.stream()
	            .map(arr -> arr.toArray(String[]::new))
	            .toArray(String[][]::new);
		return data;
	}

	/**
	 * Diese Klasse stellt den Adapter (die Verbindung) zwischen den Daten und den Ueberschriften
	 * und der Tabelle dar. Die Klasse {@code MyTableModel} erbt von {@code AbstractTableModel},
	 * welche das Interface {@code TableModel} implementiert. Die Methoden des Interfaces helfen
	 * einer JTable sich zu erstellen, indem Informationen über die Anzahl der Spalten, Anzahl der
	 * Zeilen, Spaltenueberschriften usw. auf Basis der uebergebenen Daten ({@code data}) und
	 * Spaltenueberschriften ({@code titles}) erzeugt werden.
	 * Ein Objekt von {@code MyTableModel} mit konkreten Daten ({@code data}) und konkreten
	 * Spaltenueberschriften ({@code titles}) wird der zu erstellenden {@code JTable} uebergeben.
	 */
	class MyTableModel extends AbstractTableModel
	{
	    private String[][] data;
	    private String[] titles;

		/**
		 * Konstruktor zur Erzeugung eines Objektes von {@code MyTableModel}. Die konkreten Daten
		 *  ({@code data}) und konkreten Spaltenueberschriften ({@code titles}) werden iniziiert.
		 *
		 * @param data   - die Dateneintraege in die zu erstellende Tabelle
		 * @param titles - die Spaltenueberschriften der zu erstellenden Tabelle
		 */
		public MyTableModel(String[][] data, String[] titles)
	    {
	        this.data = data;
	        this.titles = titles;
	    }

		/**
		 * Update. Sobald sich etwas an den Daten oder Spaltenueberschriften geaendert hat, werden
		 * die Objektvariablen mit neuen Werten belegt und die Methode aus {@code AbstractTableModel}
		 * {@code fireTableStructureChanged();} wird aufgerufen, um das Model und somit die Tabelle
		 * zu aktualisieren. Wuerden nur die Daten geaendert, also nicht die Struktur der Tabelle,
		 * wuerde der Aufruf von {@code fireTableDataChanged()} genuegen.
		 *
		 * @param data   - die neuen daten fuer die Tabelle
		 * @param titles - die neuen Ueberschriften fuer die Tabelle
		 */
		public void update(String[][] data, String[] titles)
	    {
	    	this.data = data;
	        this.titles = titles;
	        fireTableStructureChanged();
	    }

	    /*
	     * nachfolgend alles aus {@code TableModel} bzw. {@code AbstractTableModel}
	     */
	    @Override
	    public int getRowCount() 
	    {
	        return data.length;
	    }
	    
	    @Override
	    public int getColumnCount() 
	    {
	        return data[0].length;
	    }
	    
	    @Override
	    public String getColumnName(int col) 
	    {
	        return this.titles[col];
	    }
	    
	    @Override
	    public Object getValueAt(int row, int col) 
	    {
	        return data[row][col];
	    }
	    
	    @Override
	    public void setValueAt(Object value, int row, int col) 
	    {
	    	if(value instanceof String)
	    	{
	    		String v = (String)value;
	    		data[row][col] =  v;
	    		fireTableCellUpdated(row, col);
	    	}
	    }
	    
	    @Override
	    public Class getColumnClass(int col) {
            return getValueAt(0, col).getClass();
        }
	    
	}
}
