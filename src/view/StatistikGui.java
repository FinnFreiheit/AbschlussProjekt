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

public class StatistikGui extends JPanel
{
	String[] titles;
	String[][] data;
	String key = "SIE.DE";
	String date;
	String message = "Sie haben eine Aktie ausgewählt! Aktuell: " + this.key;
	JLabel messageLabel;
	JComboBox<String> dateList;
	JComboBox<String> selectAktie;
	JRadioButton aktie; 
	JRadioButton datum;
	JTable table;
	JPanel tablePanel;
	MyTableModel model;
	
	public StatistikGui()
	{
		super();
		this.setLayout(new BorderLayout());
		//String[][] data = this.listToArray(this.fileToArray(this.key));
		this.data = this.getDataKey(this.key);
		this.titles = this.getTitlesKey(this.key);
		this.add(createSelectionPanel(), BorderLayout.NORTH);
		this.add(createTable(this.data, this.titles), BorderLayout.CENTER);
		
		// this.date = heute
		LocalDate today = LocalDate.now(); 
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.date = today.format(myFormat);
	}
	public JPanel createSelectionPanel()
	{
		JPanel panel = new JPanel(new BorderLayout());
		
		// linkes Panel mit 2 RadioButtons
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
	
	// alle Daten aus einer Datei - key ist Aktienkuerzel
	public String[][] getDataKey(String key)
	{
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
	
	// Daten aus allen Dateien (jeweils Zeile des Datums) - date ist Datum
	public String[][] getDataDate(String date)
	{
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
	
	public String[] getTitlesDate()
	{
		String[] titles = getTitlesKey(this.key);
		titles[0] = "Share";
		this.titles = titles;
		return this.titles;
	}
	
	private String[][] listToArray(List<List<String>> records)
	{
		String[][] data = records.stream()
	            .map(arr -> arr.toArray(String[]::new))
	            .toArray(String[][]::new);
		return data;
	}
	
	class MyTableModel extends AbstractTableModel
	{
	    private String[][] data;
	    private String[] titles;
	    
	    public MyTableModel(String[][] data, String[] titles) 
	    {
	        this.data = data;
	        this.titles = titles;
	    }
	    
	    public void update(String[][] data, String[] titles)
	    {
	    	this.data = data;
	        this.titles = titles;
	        fireTableStructureChanged();
	    }
	    
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
