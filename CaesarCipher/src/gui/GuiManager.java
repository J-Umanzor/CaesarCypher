
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import logic.CaesarCipher;

public class GuiManager {
    private final CaesarCipher caesarCipher;

    public GuiManager() {
        this.caesarCipher = new CaesarCipher();
    }
    // Hold the contents of the GUI
    public void createAndShowGui() {
    	// The title of the program
        JFrame frame = new JFrame("Caesar Cipher Encrypter/Decrypter");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //This will create the text box and set the default key to 3
        JTextField keyField = new JTextField("3", 5); 
        
        // the button for encryption
        JButton encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//allows the user to choose a file from local machine
                String filePath = chooseFile();
                if (filePath != null) {
                    String encryptedText = caesarCipher.encryptFile(filePath, Integer.parseInt(keyField.getText()));
                    showOutput(encryptedText);
                    saveToFile(encryptedText);
                }
            }
        });
        // the button for decryption
        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new ActionListener() {
            @Override
            //allows the user to choose a file from local machine
            public void actionPerformed(ActionEvent e) {
                String filePath = chooseFile();
                if (filePath != null) {
                    String decryptedText = caesarCipher.decryptFile(filePath, Integer.parseInt(keyField.getText()));
                    showOutput(decryptedText);
                    saveToFile(decryptedText);
                }
            }
        });
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        //Creates text indicating where to type the key
        panel.add(new JLabel("Enter key:"));
       // This will be used to create the text box and encryption/decryption buttons
        panel.add(keyField);
        panel.add(encryptButton);
        panel.add(decryptButton);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    //This is what allows the user to view local files to pick a text file
    //Will return the file path of the selected file
    private String chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
    
    // After encrytion/decryption is done the output will be displayed
    private void showOutput(String output) {
        JTextArea outputTextArea = new JTextArea(output);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        JFrame outputFrame = new JFrame("Output");
        outputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        outputFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        outputFrame.setSize(400, 300);
        outputFrame.setLocationRelativeTo(null);
        outputFrame.setVisible(true);
    }
    
    // This is what prompts the user to save the result to a text file
    private void saveToFile(String content) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                // Using try-with-resources to automatically close the file writer
                try (java.io.FileWriter writer = new java.io.FileWriter(file)) {
                    writer.write(content);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
