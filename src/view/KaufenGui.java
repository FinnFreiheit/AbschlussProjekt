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
import model.Depot;

public class KaufenGui extends JPanel
{
	JComboBox<String> aktieKaufenInput;
	JComboBox<String> datumKaufenInput;
	JComboBox<String> anzahlKaufenInput;
	JComboBox<String> aktieVerkaufenInput;
	JComboBox<String> datumVerkaufenInput;
	JComboBox<String> anzahlVerkaufenInput;

	public KaufenGui(Depot depot)
	{
		super();
		this.setLayout(new BorderLayout());

		this.add(createBothPanels(depot), BorderLayout.CENTER);
	}

	JPanel createBothPanels(Depot depot)
	{
		JPanel both = new JPanel();

		JPanel left = new JPanel();
		left.add(new TradingPanelFactory("Kaufen", "Aktie kaufen", "Kaufdatum", depot));
		JPanel right = new JPanel();
		right.add(new TradingPanelFactory("Verkaufen", "Aktie verkaufen", "Verkaufdatum", depot));

		both.add(left);
		both.add(right);

		return both;
	}
}
