import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openMenuItem, saveMenuItem, exitMenuItem;

    public TextEditor() {
        setTitle("Text Editor");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the text area
        textArea = new JTextArea();

        // Create the menu bar
        menuBar = new JMenuBar();

        // Create the file menu
        fileMenu = new JMenu("File");

        // Create the menu items
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        exitMenuItem = new JMenuItem("Exit");

        // Add action listeners to menu items
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

        // Add menu items to the file menu
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        // Add the file menu to the menu bar
        menuBar.add(fileMenu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Add the text area to the frame
        add(new JScrollPane(textArea));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openMenuItem) {
            openFile();
        } else if (e.getSource() == saveMenuItem) {
            saveFile();
        } else if (e.getSource() == exitMenuItem) {
            System.exit(0);
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                textArea.read(reader, null);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                textArea.write(writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextEditor();
            }
        });
    }
}
