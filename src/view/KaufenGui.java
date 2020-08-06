package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Map;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import model.Dax;

public class KaufenGui extends JPanel
{
	public KaufenGui()
	{
		super();
		this.setLayout(new BorderLayout());
		this.add(createFormular(), BorderLayout.CENTER);
	}
	
	JPanel createFormular()
	{
		JPanel formular = new JPanel();
		
		JLabel dateLabel = new JLabel("Kaufdatum:");
		JLabel aktieLabel = new JLabel("Aktie:");
		JLabel anzahlLabel = new JLabel("Anzahl:");
		
		// Datumseingabe
		JFormattedTextField dateInput = new JFormattedTextField("yyyy-mm-dd");
		
		// Anzahleingabe
		
		/*
		 * NumberFormat numberFormat = NumberFormat.getNumberInstance();
		 * 
		 * JFormattedTextField anzahlInput = new JFormattedTextField(new
		 * DefaultFormatterFactory( new NumberFormatter(numberFormat)));
		 * 
		 * anzahlInput.setValue(Integer.valueOf(0));
		 * anzahlInput.addPropertyChangeListener("value", new PropertyChangeListener() {
		 * 
		 * @Override public void propertyChange(PropertyChangeEvent e) {
		 * JFormattedTextField source = (JFormattedTextField)e.getSource();
		 * source.setValue(((Number)source.getValue()).intValue()); }
		 * 
		 * });
		 */
		JComboBox<Integer> anzahlInput = new JComboBox<>();
		anzahlInput.setEditable(true);
		
		// Aktienauswahl
		Map<String, String> dax = Dax.getDax();
		String[] aktien = new String[dax.size()];
		int index=0;
		for(String key : dax.keySet())
		{
			aktien[index++] = dax.get(key);
		}
		Arrays.parallelSort(aktien);
		JComboBox<String> selectAktie = new JComboBox<>(aktien);
		
		
		/*
		 * JPanel labels = new JPanel(new GridLayout(0,1, 10, 10));
		 * labels.add(dateLabel); labels.add(aktieLabel); labels.add(anzahlLabel);
		 * 
		 * JPanel textfields = new JPanel(new GridLayout(0,1, 10, 10));
		 * textfields.add(dateInput); textfields.add(selectAktie);
		 * textfields.add(anzahlInput);
		 */
		
		JPanel one = new JPanel(new FlowLayout(FlowLayout.LEFT));		one.add(dateLabel);
		JPanel two = new JPanel(new FlowLayout(FlowLayout.LEFT)); 		two.add(dateInput);
		JPanel three = new JPanel(new FlowLayout(FlowLayout.LEFT));	three.add(aktieLabel);
		JPanel four = new JPanel(new FlowLayout(FlowLayout.LEFT));		four.add(selectAktie);
		JPanel five = new JPanel(new FlowLayout(FlowLayout.LEFT));		five.add(anzahlLabel);
		JPanel six = new JPanel(new FlowLayout(FlowLayout.LEFT));		six.add(anzahlInput);
		
		/*
		 * formular.setLayout(new BorderLayout());
		 * formular.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		 * formular.add(labels, BorderLayout.CENTER); formular.add(textfields,
		 * BorderLayout.LINE_END);
		 */
		
		JPanel all = new JPanel(new GridLayout(3,2,10,10));
		
		all.add(one);
		all.add(two);
		all.add(three);
		all.add(four);
		all.add(five);
		all.add(six);
		
		formular.setLayout(new GridLayout(1,1,50,50));
		formular.add(all);
		
		return formular;
	}
}
