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

/**
 * Java swing Fenster Kaufen. In dem Fenster können transaktionen getätigt werden, die einfluss auf das Depot haben.
 */
public class KaufenGui extends JPanel
{
	/**
	 * Aktie kaufen input.
	 */
	JComboBox<String> aktieKaufenInput;
	/**
	 * Datum kaufen input.
	 */
	JComboBox<String> datumKaufenInput;
	/**
	 * Anzahl kaufen input.
	 */
	JComboBox<String> anzahlKaufenInput;
	/**
	 * Aktie verkaufen input.
	 */
	JComboBox<String> aktieVerkaufenInput;
	/**
	 * Datum verkaufen input.
	 */
	JComboBox<String> datumVerkaufenInput;
	/**
	 * Anzahl verkaufen input.
	 */
	JComboBox<String> anzahlVerkaufenInput;

	/**
	 * Konstruktor
	 *
	 * @param depot the depot
	 */
	public KaufenGui(Depot depot)
	{
		super();
		this.setLayout(new BorderLayout());

		this.add(createBothPanels(depot), BorderLayout.CENTER);
	}

	/**
	 * erzeugt beide panels. Links kaufen, rechts verkaufen
	 *
	 * @param depot the depot
	 * @return the j panel
	 */
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
