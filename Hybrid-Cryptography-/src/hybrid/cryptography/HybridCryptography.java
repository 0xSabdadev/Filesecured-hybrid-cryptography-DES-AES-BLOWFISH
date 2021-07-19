package hybrid.cryptography;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.*;
import javax.imageio.*;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.bind.DatatypeConverter;

import com.sun.prism.Graphics;

public class HybridCryptography extends JPanel implements ActionListener {
  static private final String newline = "\n";

  JButton encrypt, decrypt;

  JTextArea log;
  JTextArea log2;

  JFileChooser fc;
  String inputMessage, outputMessage;

  public HybridCryptography() {
    super(new BorderLayout());

    // Create the log first, because the action listeners
    // need to refer to it.
    log = new JTextArea("                                 Hybrid Cryptography | MiawIF", 5, 40);
    log.setFont(new Font("Serif", Font.ITALIC, 50));
    log.setMargin(new Insets(5, 5, 5, 5));
    log.setEditable(false);
    log.setBackground(Color.GRAY);

    log2 = new JTextArea(25, 85);
    log2.setMargin(new Insets(5, 5, 5, 5));
    log2.setEditable(false);
    log2.setBackground(Color.GRAY);
    // log.setVisible(true);

    JScrollPane logScrollPane = new JScrollPane(log);
    logScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    JScrollPane logScrollPane2 = new JScrollPane(log2);
    logScrollPane2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    // JScrollPane logScrollPane = new JScrollPane(log);

    // Create a file chooser
    fc = new JFileChooser();

    encrypt = new JButton("Enkripsi File");
    encrypt.addActionListener(this);
    encrypt.setPreferredSize(new Dimension(200, 75));

    // Create the save button. We use the image from the JLF
    // Graphics Repository (but we extracted it from the jar).
    decrypt = new JButton("Dekripsi File");
    decrypt.addActionListener(this);
    decrypt.setPreferredSize(new Dimension(200, 75));

    // For layout purposes, put the buttons in a separate panel
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10)); // use FlowLayout
    buttonPanel.setBackground(Color.GRAY);
    buttonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    buttonPanel.add(encrypt);
    buttonPanel.add(decrypt);

    // Add the buttons and the log to this panel.
    add(buttonPanel, BorderLayout.CENTER);
    add(logScrollPane, BorderLayout.PAGE_START);
    add(logScrollPane2, BorderLayout.PAGE_END);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == encrypt) {
      int returnVal = fc.showOpenDialog(HybridCryptography.this);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        String path = file.getPath();
        long len = file.length();
        System.out.println("Path:" + len);
        System.out.println("");
        System.out.println("Enter your key(>24 chars): ");
        Scanner scanner = new Scanner(System.in);
        String key_e = scanner.nextLine();
        // localKey = key_e;
        CipherExample c = new CipherExample();
        int num = (int) (len % 3);
        // for(int i=0; i < num;i++) {
        // c.aencrypt(key_e, path);
        // }
        // for(int j= num;j< (2*num)-1;j++) {
        // c.encrypt_AES(key_e,path);
        // }
        // for(int k= (2*num);k< (3*num)-1;k++) {
        // c.encrypt_blowfish(key_e,path);
        // }

        c.aencrypt(key_e, path);
        c.encrypt_AES(key_e, "cipher_DES.txt");
        c.encrypt_blowfish(key_e, "cipher_AES.txt");
        System.out.println("Encryption compeleted");

        log.append("\nOpening: " + file.getName() + "." + newline);
      } else {
        log.append(" " + newline);
      }
      log.setCaretPosition(log.getDocument().getLength());

      // Handle save button action.
    } else if (e.getSource() == decrypt) {
      int returnVal = fc.showSaveDialog(HybridCryptography.this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        String path = file.getPath();

        System.out.println("Enter your key: ");
        Scanner scanner = new Scanner(System.in);
        String key_d = scanner.nextLine();
        CipherExample c = new CipherExample();

        c.decrypt_blowfish(key_d, path);
        c.decrypt_AES(key_d, "decrypt_AES.txt");
        c.adecrypt(key_d, "decrypt_DES.txt");
        System.out.println("Decryption completed");

        // This is where a real application would save the file.
        log.append("\nSaving: " + file.getName() + "." + newline);
      } else {
        log.append(" " + newline);
      }
      log.setCaretPosition(log.getDocument().getLength());
    }
  }

  /** Returns an ImageIcon, or null if the path was invalid. */
  protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = HybridCryptography.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be invoked
   * from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    // Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);

    // Create and set up the window.
    JFrame frame = new JFrame("Hybrid Cryptography | MiawIF");

    // frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Pranita
    // Deshpande\\Documents\\Hybrid Cryptography[6211]\\Hybrid
    // Cryptography\\src\\hybrid\\cryptography\\Cryptography.jpg")));
    // Create and set up the content pane.
    JComponent newContentPane = new HybridCryptography();
    newContentPane.setOpaque(true); // content panes must be opaque
    frame.setContentPane(newContentPane);
    // frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Pranita
    // Deshpande\\Documents\\Hybrid Cryptography[6211]\\Hybrid
    // Cryptography\\src\\hybrid\\cryptography")));

    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    new BackgroundImageJFrame();
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}
