package view;

import java.io.PrintStream;

import javax.swing.*;

import helper.JComponentOutputStream;
import helper.JComponentHandler;

/**
 * diese Klasse ist https://stackoverflow.com/questions/342990/create-java-console-inside-a-gui-panel
 * entnommen, nicht selbst geschrieben
 * Sie implementiert eine Console innerhalb einer Swing-GUI
 * verwendet JComponentOutputStream und JComponentHandler aus helper
 *
 * @author Stephan (https://stackoverflow.com/users/363573/stephan)
 *
 */
public class ConsoleGui extends JPanel
{
    public ConsoleGui()
    {
        super();
        this.add(createConsoleGui());
        System.out.println("Ausgabe aus dem Konstruktor von ConsoleGUI - ab jetzt landen alle System.out.print()-Ausgaben hier");;
    }

    public JPanel createConsoleGui()
    {
        JPanel panel =new JPanel();
        JLabel console = new JLabel();
        JComponentOutputStream consoleOutputStream = new JComponentOutputStream(console, new JComponentHandler() {
            private StringBuilder sb = new StringBuilder();

            @Override
            public void setText(JComponent swingComponent, String text) {
                sb.delete(0, sb.length());
                append(swingComponent, text);
            }

            @Override
            public void replaceRange(JComponent swingComponent, String text, int start, int end) {
                sb.replace(start, end, text);
                redrawTextOf(swingComponent);
            }

            @Override
            public void append(JComponent swingComponent, String text) {
                sb.append(text);
                redrawTextOf(swingComponent);
            }

            private void redrawTextOf(JComponent swingComponent) {
                ((JLabel)swingComponent).setText("<html><pre>" + sb.toString() + "</pre></html>");
            }
        });

        PrintStream con = new PrintStream(consoleOutputStream);
        System.setOut(con);
        System.setErr(con);

        // Optional: add a scrollpane around the console for having scrolling bars
        JScrollPane sp = new JScrollPane( //
                console, //
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, //
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED //
        );
        panel.add(sp);
        return panel;
    }

}
