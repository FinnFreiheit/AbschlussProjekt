package view;

import error.AktieNichtVorhanden;
import error.DatumFehler;
import error.FehlerCSVInhalt;
import error.TagesInformationenNichtVorhanden;
import model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

public class TradingPanelFactory extends JPanel
{
    JComboBox<String> datumInput;
    JComboBox<String> aktieInput;
    JComboBox<String> anzahlInput;
    Depot depot;
    String titleFormular;

    private final static boolean DEBUG = false;
    private final static String PATH = "database/";

    public TradingPanelFactory(String titleFormular, String headLineFormular, String dateLabelFormular, Depot depot)
    {
        super();
        this.depot = depot;
        this.titleFormular = titleFormular;
        TitledBorder title;
        title = BorderFactory.createTitledBorder(titleFormular);
        this.setBorder(title);

        JLabel dateLabel = new JLabel(dateLabelFormular);
        JLabel aktieLabel = new JLabel("Aktie:");
        JLabel anzahlLabel = new JLabel("Anzahl:");

        // Datumeingabe
        LocalDate today = LocalDate.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatToday = today.format(myFormat);
        String[] date = { formatToday };
        this.datumInput = new JComboBox<>(date);
        this.datumInput.setEditable(true);
        // Ereignisbehandlung Datumauswahl (für Auswahl)
        this.datumInput.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                Object src = e.getSource();
                if(e.getStateChange()==1) {
                    if (src instanceof JComboBox) {
                        JComboBox<String> cb = (JComboBox<String>) src;
                        String datum = (String) cb.getSelectedItem();
                        if (!eingabeDatumUeberpruefen(datum)) {
                            JOptionPane.showMessageDialog(TradingPanelFactory.this, "Das Datum muss im Format yyyy-mm-dd eingegeben werden!");
                            if (DEBUG) System.out.println(e.getItem() + " " + e.getStateChange());
                        } else {
                            if (DEBUG) System.out.println(datum);
                        }
                    }
                }
            }
        });

        // Anzahleingabe

        this.anzahlInput = new JComboBox<>();
        this.anzahlInput.setEditable(true);
        // Ereignisbehandlung Anzahleingabe
        this.anzahlInput.addItemListener(new ItemListener()  {

            @Override
            public void itemStateChanged(ItemEvent e) {
                Object src = e.getSource();
                if(e.getStateChange()==1) {
                    if (src instanceof JComboBox) {
                        JComboBox<String> cb = (JComboBox<String>) src;
                        String anzahl = (String) cb.getSelectedItem();
                        if (!eingabeAnzahlUeberpruefen(anzahl)) {
                            JOptionPane.showMessageDialog(TradingPanelFactory.this, "Die Anzahl muss eine Zahl sein!");
                            if (DEBUG) System.out.println(e.getItem() + " " + e.getStateChange());
                        } else {
                            if (DEBUG) System.out.println(anzahl);
                        }
                    }
                }
            }
        });
        // Aktienauswahl
        Map<String, String> dax = Dax.getDax();
        String[] aktien = new String[dax.size()];
        int index=0;
        for(String key : dax.keySet())
        {
            aktien[index++] = key;
        }
        Arrays.parallelSort(aktien);
        this.aktieInput = new JComboBox<>(aktien);

        // das ist nur für Layout
        JPanel one = new JPanel(new FlowLayout(FlowLayout.LEFT));		one.add(dateLabel);
        JPanel two = new JPanel(new FlowLayout(FlowLayout.LEFT)); 		two.add(this.datumInput);
        JPanel three = new JPanel(new FlowLayout(FlowLayout.LEFT));		three.add(aktieLabel);
        JPanel four = new JPanel(new FlowLayout(FlowLayout.LEFT));		four.add(this.aktieInput);
        JPanel five = new JPanel(new FlowLayout(FlowLayout.LEFT));		five.add(anzahlLabel);
        JPanel six = new JPanel(new FlowLayout(FlowLayout.LEFT));		six.add(this.anzahlInput);

        JPanel all = new JPanel(new GridLayout(3,2,10,10));

        all.add(one);
        all.add(two);
        all.add(three);
        all.add(four);
        all.add(five);
        all.add(six);

        JLabel verkaufenLabel = new JLabel(headLineFormular);
        Font kaufenLabelFont=new Font(verkaufenLabel.getFont().getName(),Font.ITALIC+Font.BOLD,verkaufenLabel.getFont().getSize());
        verkaufenLabel.setFont(kaufenLabelFont);
        verkaufenLabel.setHorizontalAlignment(JLabel.CENTER);
        //formular.setLayout(new GridLayout(1,1,50,50));
        JButton button = new JButton(titleFormular);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String datum = (String)TradingPanelFactory.this.datumInput.getSelectedItem();
                String anzahl = ((String)TradingPanelFactory.this.anzahlInput.getSelectedItem());
                int anz = Integer.parseInt(anzahl);
                String aktie = (String)TradingPanelFactory.this.aktieInput.getSelectedItem();

                if(DEBUG) {
                    System.out.println("DEBUG --- es soll gekauft/verkauft werden: ");
                    System.out.println("Aktie  : " + aktie);
                    System.out.println("Anzahl : " + anzahl);
                    System.out.println("Datum  : " + datum);
                    System.out.println();
                }
                // hier kann man die Werte nun in die Kaufhistorie eintragen (mit v)
                File file = new File(PATH + aktie + ".csv");
                try {
                    HistorischeDatenListe hdl = new HistorischeDatenListe(file);
                    switch(TradingPanelFactory.this.titleFormular)
                    {
                        case "Kaufen" :
                            try {
                                TradingPanelFactory.this.depot.kaufen(datum, anz, hdl);
                                TradingPanelFactory.this.depot.ausgabeDepot();
                            } catch (DatumFehler datumFehler) {
                                datumFehler.printStackTrace();
                            } catch (AktieNichtVorhanden aktieNichtVorhanden) {
                                aktieNichtVorhanden.printStackTrace();
                            } catch (TagesInformationenNichtVorhanden tagesInformationenNichtVorhanden) {
                                tagesInformationenNichtVorhanden.printStackTrace();
                            }
                            break;
                        case "Verkaufen" :
                            try {
                                double preis = 0;
                                try {
                                    preis = hdl.getPreis(datum);
                                } catch (DatumFehler datumFehler) {
                                    datumFehler.printStackTrace();
                                } catch (TagesInformationenNichtVorhanden tagesInformationenNichtVorhanden) {
                                    tagesInformationenNichtVorhanden.printStackTrace();
                                }
                                TradingPanelFactory.this.depot.verkaufen(datum, preis, anz, aktie);
                                TradingPanelFactory.this.depot.ausgabeDepot();
                            } catch (AktieNichtVorhanden aktieNichtVorhanden) {
                                aktieNichtVorhanden.printStackTrace();
                            }
                            break;
                    }
                    Database datenbasis = Database.generateDatabase();
                    File kaufHistorieFile = new File("database/KaufHistorie.csv");
                    KaufHistorie kaufHistorie = new KaufHistorie(kaufHistorieFile);
                    try {
                        TradingPanelFactory.this.depot = kaufHistorie.depotErstellen(datenbasis);
                    } catch (DatumFehler datumFehler) {
                        datumFehler.printStackTrace();
                    } catch (AktieNichtVorhanden aktieNichtVorhanden) {
                        aktieNichtVorhanden.printStackTrace();
                    } catch (TagesInformationenNichtVorhanden tagesInformationenNichtVorhanden) {
                        tagesInformationenNichtVorhanden.printStackTrace();
                    }
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
                catch (FehlerCSVInhalt fehlerCSVInhalt)
                {
                    fehlerCSVInhalt.printStackTrace();
                }
            }

        });
        this.setLayout(new BorderLayout());
        this.add(verkaufenLabel, BorderLayout.NORTH);
        this.add(all, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);
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

    /**
     * Ueberprueft, ob die Anzahl einer Zahl entspricht.
     *
     * @param anzahl zu ueberpruefende Anzahl
     * @return true wenn es einer Zahl entspricht, false wenn nicht
     */
    private static boolean eingabeAnzahlUeberpruefen(String anzahl)
    {
        boolean isNumber = true;
        try {
            int number = Integer.parseInt(anzahl);
        }
        catch(NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
    }
}
