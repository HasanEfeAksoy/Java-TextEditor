import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;

public class Form1 extends JFrame {
    private JTextArea textArea1;
    private JButton saveButton;
    private JButton openButton;
    private JButton newButton;
    private JToolBar toolbar;
    private JPanel panel;
    private JScrollPane textareascrollpane;
    private JButton saveAsButton;


    String myPath = "";

    public Form1() {
        this.add(panel);
        this.setTitle("wRight - version 1.0.1 || " + myPath);
        this.setSize(800, 600);
        //this.setIconImage(new ImageIcon("src/open-book.png").getImage());
        try {
            this.setIconImage(ImageIO.read(new URL("https://cdn-icons-png.flaticon.com/512/277/277938.png")));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        this.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        this.saveAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAs();
            }
        });
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();
            }
        });


    }


    public void save() {
        if (!new File(this.myPath).exists()) {
            this.saveAs();
        }
        else {
            try {
                FileWriter fw = new FileWriter(new File(this.myPath));
                fw.write(this.textArea1.getText().toString());
                fw.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
    public void saveAs() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Save as File..");

        int result = jFileChooser.showSaveDialog(this.panel);

        if (result == JFileChooser.APPROVE_OPTION) {
            File targetFile = jFileChooser.getSelectedFile();

            try {
                if (!targetFile.exists()) {
                    targetFile.createNewFile();
                }

                FileWriter fw = new FileWriter(targetFile);
                fw.write(this.textArea1.getText().toString());
                fw.close();

                this.myPath = jFileChooser.getSelectedFile().toPath().toString();
                this.setTitle("wRight - version 1.0.1 || " + this.myPath);

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
    public void open() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Open File..");

        int result = jFileChooser.showOpenDialog(this.panel);

        if (result == JFileChooser.APPROVE_OPTION) {
            Path path = jFileChooser.getSelectedFile().toPath();

            FileReader reader = null;
            try {
                reader = new FileReader(path.toString());
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            try {
                this.textArea1.read(reader, path.toString());
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            this.myPath = path.toString();
            this.setTitle("wRight - version 1.0.1 || " + this.myPath);
        }
    }
    public void newFile() {
        this.textArea1.setText("");

        this.myPath = "";
        this.setTitle("wRight - version 1.0.1 || " + this.myPath);
    }
}
