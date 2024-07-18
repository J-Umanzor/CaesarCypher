
package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class CaesarCipher {
	//This is called when encryption is done
    public String encryptFile(String filePath, int key) {
        return processFile(filePath , key, true);
    }
    //This is called when decryption is done
    public String decryptFile(String filePath, int key) {
        return processFile(filePath , key, false);
    }
    
    //This is used to view the text file and call processFile line by line to apply the Caesar algorithm
    private String processFile(String filePath, int key, boolean encrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(line -> processLine(line, key, encrypt))
                    .collect(Collectors.joining("\n"));
        } 
        catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    //This is what applies the Caesar Cipher algorithm to the text 
    private String processLine(String line, int key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <line.length(); i++) {
            char ch = line.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                char encryptedChar = (char) ((ch-base + (encrypt ? key : -key)+ 26) % 26 + base);
                result.append(encryptedChar);
            } 
            else {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
}
