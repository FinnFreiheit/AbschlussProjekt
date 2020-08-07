package helper;

import javax.swing.JComponent;

/**
 * dieses Interface ist https://stackoverflow.com/questions/342990/create-java-console-inside-a-gui-panel
 * entnommen, nicht selbst geschrieben
 * 
 * @author Stephan (https://stackoverflow.com/users/363573/stephan)
 *
 */
public interface JComponentHandler {
    void setText(JComponent swingComponent, String text);

    void replaceRange(JComponent swingComponent, String text, int start, int end);

    void append(JComponent swingComponent, String text);
}
