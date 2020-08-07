package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import model.Dax;

public class KaufenGui extends JPanel
{
	JComboBox<String> aktieKaufenInput;
	JComboBox<String> datumKaufenInput;
	JComboBox<String> anzahlKaufenInput;
	JComboBox<String> aktieVerkaufenInput;
	JComboBox<String> datumVerkaufenInput;
	JComboBox<String> anzahlVerkaufenInput;

	public KaufenGui()
	{
		super();
		this.setLayout(new BorderLayout());

		this.add(createBothPanels(), BorderLayout.CENTER);
	}

	JPanel createBothPanels()
	{
		JPanel both = new JPanel();

		JPanel left = new JPanel();
		left.add(createKaufFormular());
		JPanel right = new JPanel();
		right.add(createVerkaufFormular());

		both.add(left);
		both.add(right);

		return both;
	}

	JPanel createKaufFormular()
	{
		JPanel formular = new JPanel();
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Kaufen");
		formular.setBorder(title);

		JLabel dateLabel = new JLabel("Kaufdatum:");
		JLabel aktieLabel = new JLabel("Aktie:");
		JLabel anzahlLabel = new JLabel("Anzahl:");

		// Datumeingabe
		LocalDate today = LocalDate.now();
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatToday = today.format(myFormat);
		String[] date = { formatToday };
		this.datumKaufenInput = new JComboBox<>(date);
		this.datumKaufenInput.setEditable(true);
		// Ereignisbehandlung Datumauswahl (für Auswahl)
		this.datumKaufenInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object src = e.getSource();
				if (src instanceof JComboBox) {
					JComboBox<String> cb = (JComboBox<String>) src;
					String datum = (String) cb.getSelectedItem();
					if (!eingabeDatumUeberpruefen(datum)) {
						JOptionPane.showMessageDialog(KaufenGui.this, "Das Datum muss im Format yyyy-mm-dd eingegeben werden!");
						cb.removeItemAt(0);
					} else {
						System.out.println(datum);
					}
				}
			}

		});

		// Anzahleingabe

		this.anzahlKaufenInput = new JComboBox<>();
		this.anzahlKaufenInput.setEditable(true);

		// Aktienauswahl
		Map<String, String> dax = Dax.getDax();
		String[] aktien = new String[dax.size()];
		int index=0;
		for(String key : dax.keySet())
		{
			aktien[index++] = key;
		}
		Arrays.parallelSort(aktien);
		this.aktieKaufenInput = new JComboBox<>(aktien);

		JPanel one = new JPanel(new FlowLayout(FlowLayout.LEFT));		one.add(dateLabel);
		JPanel two = new JPanel(new FlowLayout(FlowLayout.LEFT)); 		two.add(this.datumKaufenInput);
		JPanel three = new JPanel(new FlowLayout(FlowLayout.LEFT));		three.add(aktieLabel);
		JPanel four = new JPanel(new FlowLayout(FlowLayout.LEFT));		four.add(this.aktieKaufenInput);
		JPanel five = new JPanel(new FlowLayout(FlowLayout.LEFT));		five.add(anzahlLabel);
		JPanel six = new JPanel(new FlowLayout(FlowLayout.LEFT));		six.add(this.anzahlKaufenInput);

		JPanel all = new JPanel(new GridLayout(3,2,10,10));

		all.add(one);
		all.add(two);
		all.add(three);
		all.add(four);
		all.add(five);
		all.add(six);

		JLabel kaufenLabel = new JLabel("Aktie kaufen");
		Font kaufenLabelFont=new Font(kaufenLabel.getFont().getName(),Font.ITALIC+Font.BOLD,kaufenLabel.getFont().getSize());
		kaufenLabel.setFont(kaufenLabelFont);
		kaufenLabel.setHorizontalAlignment(JLabel.CENTER);
		//formular.setLayout(new GridLayout(1,1,50,50));
		JButton kaufenButton = new JButton("Kaufen");
		kaufenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String kaufDatum = (String)KaufenGui.this.datumKaufenInput.getSelectedItem();
				String kaufAnzahl = (String)KaufenGui.this.anzahlKaufenInput.getSelectedItem();
				String kaufAktie = (String)KaufenGui.this.aktieKaufenInput.getSelectedItem();

				System.out.println("DEBUG --- es soll gekauft werden: ");
				System.out.println("Aktie  : " + kaufAktie);
				System.out.println("Anzahl : " + kaufAnzahl);
				System.out.println("Datum  : " + kaufDatum);
				System.out.println();

				// hier kann man die Werte nun in die Kaufhistorie eintragen (mit k)
			}

		});
		formular.setLayout(new BorderLayout());
		formular.add(kaufenLabel, BorderLayout.NORTH);
		formular.add(all, BorderLayout.CENTER);
		formular.add(kaufenButton, BorderLayout.SOUTH);
		return formular;
	}

	JPanel createVerkaufFormular()
	{
		JPanel formular = new JPanel();
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Verkaufen");
		formular.setBorder(title);

		JLabel dateLabel = new JLabel("Verkaufdatum:");
		JLabel aktieLabel = new JLabel("Aktie:");
		JLabel anzahlLabel = new JLabel("Anzahl:");

		// Datumeingabe
		LocalDate today = LocalDate.now();
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatToday = today.format(myFormat);
		String[] date = { formatToday };
		this.datumVerkaufenInput = new JComboBox<>(date);
		this.datumVerkaufenInput.setEditable(true);
		// Ereignisbehandlung Datumauswahl (für Auswahl)
		this.datumVerkaufenInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object src = e.getSource();
				if (src instanceof JComboBox) {
					JComboBox<String> cb = (JComboBox<String>) src;
					String datum = (String) cb.getSelectedItem();
					if (!eingabeDatumUeberpruefen(datum)) {
						JOptionPane.showMessageDialog(KaufenGui.this, "Das Datum muss im Format yyyy-mm-dd eingegeben werden!");
						cb.removeItemAt(0);
					} else {
						System.out.println(datum);
					}
				}
			}
		});

		// Anzahleingabe

		this.anzahlVerkaufenInput = new JComboBox<>();
		this.anzahlVerkaufenInput.setEditable(true);

		// Aktienauswahl
		Map<String, String> dax = Dax.getDax();
		String[] aktien = new String[dax.size()];
		int index=0;
		for(String key : dax.keySet())
		{
			aktien[index++] = key;
		}
		Arrays.parallelSort(aktien);
		this.aktieVerkaufenInput = new JComboBox<>(aktien);

		JPanel one = new JPanel(new FlowLayout(FlowLayout.LEFT));		one.add(dateLabel);
		JPanel two = new JPanel(new FlowLayout(FlowLayout.LEFT)); 		two.add(this.datumVerkaufenInput);
		JPanel three = new JPanel(new FlowLayout(FlowLayout.LEFT));		three.add(aktieLabel);
		JPanel four = new JPanel(new FlowLayout(FlowLayout.LEFT));		four.add(this.aktieVerkaufenInput);
		JPanel five = new JPanel(new FlowLayout(FlowLayout.LEFT));		five.add(anzahlLabel);
		JPanel six = new JPanel(new FlowLayout(FlowLayout.LEFT));		six.add(this.anzahlVerkaufenInput);

		JPanel all = new JPanel(new GridLayout(3,2,10,10));

		all.add(one);
		all.add(two);
		all.add(three);
		all.add(four);
		all.add(five);
		all.add(six);

		JLabel verkaufenLabel = new JLabel("Aktie verkaufen");
		Font kaufenLabelFont=new Font(verkaufenLabel.getFont().getName(),Font.ITALIC+Font.BOLD,verkaufenLabel.getFont().getSize());
		verkaufenLabel.setFont(kaufenLabelFont);
		verkaufenLabel.setHorizontalAlignment(JLabel.CENTER);
		//formular.setLayout(new GridLayout(1,1,50,50));
		JButton verkaufenButton = new JButton("Verkaufen");
		verkaufenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String verkaufsDatum = (String)KaufenGui.this.datumVerkaufenInput.getSelectedItem();
				String verkaufsAnzahl = (String)KaufenGui.this.anzahlVerkaufenInput.getSelectedItem();
				String verkaufsAktie = (String)KaufenGui.this.aktieVerkaufenInput.getSelectedItem();

				System.out.println("DEBUG --- es soll verkauft werden: ");
				System.out.println("Aktie  : " + verkaufsAktie);
				System.out.println("Anzahl : " + verkaufsAnzahl);
				System.out.println("Datum  : " + verkaufsDatum);
				System.out.println();

				// hier kann man die Werte nun in die Kaufhistorie eintragen (mit v)
			}

		});
		formular.setLayout(new BorderLayout());
		formular.add(verkaufenLabel, BorderLayout.NORTH);
		formular.add(all, BorderLayout.CENTER);
		formular.add(verkaufenButton, BorderLayout.SOUTH);
		return formular;
	}

	/**
	 * Ueberprueft, ob das Datum dem Format JJJJ-MM-DD entspricht.
	 *
	 * @param datum zu ueberpruefendes Datum
	 * @return true wenn es dem Format entspricht, false wenn nicht
	 */
	private static boolean eingabeDatumUeberpruefen(String datum)
	{
		String[] datumSplit = datum.split("-");
		return datumSplit.length == 3 && datumSplit[0].length() == 4 && datumSplit[1].length() == 2 && datumSplit[2].length() == 2;

	}
}
