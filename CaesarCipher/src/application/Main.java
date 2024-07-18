
package application;
import javax.swing.SwingUtilities;


import gui.GuiManager;
//This will launch the GUI
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuiManager guiManager = new GuiManager();
            guiManager.createAndShowGui();
        });
    }
}
