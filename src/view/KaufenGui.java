package view;

import java.awt.BorderLayout;
import javax.swing.*;
import model.Depot;

/**
 * Die Klasse {@code KaufenGui} erzeugt ein JPanel mit 2 Formularen. Das eine Formular ist zum
 * Kaufen von Aktien, das andere zum Verkaufen.
 * Die Formulare werden mithilfe der Klasse {@code TradingPanelFactory} erzeugt.
 */
public class KaufenGui extends JPanel
{
	/**
	 * Konstruktor
	 *
	 * @param depot - das aktuelle Depot
	 */
	public KaufenGui(Depot depot)
	{
		super();
		this.setLayout(new BorderLayout());
		this.add(createBothPanels(depot), BorderLayout.CENTER);
	}

	/**
	 * Erzeugt beide Formulare in einem JPanel. Links Kaufen, rechts Verkaufen
	 *
	 * @param depot - das aktuelle Depot
	 * @return das JPanel mit den Formularen
	 */
	JPanel createBothPanels(Depot depot)
	{
		JPanel both = new JPanel();

		both.add(new TradingPanelFactory("Kaufen", "Aktie kaufen", "Kaufdatum", depot));
		both.add(new TradingPanelFactory("Verkaufen", "Aktie verkaufen", "Verkaufdatum", depot));

		return both;
	}
}
