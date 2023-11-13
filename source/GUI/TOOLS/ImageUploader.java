package GUI.TOOLS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.*;

public class ImageUploader extends JFrame {

    private JTextField idField;
    private JLabel imageLabel;

    public ImageUploader() {
        super("Image Uploader for Cars");

        idField = new JTextField(20);
        JButton uploadButton = new JButton("Upload Image");
        imageLabel = new JLabel();

        setLayout(new FlowLayout());
        add(new JLabel("ID:"));
        add(idField);
        add(uploadButton);
        add(imageLabel);

        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif", "bmp"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        Path destination = Paths.get("carImages/" + idField.getText() + "/" + selectedFile.getName());
                        Files.createDirectories(destination.getParent());
                        Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                        ImageIcon icon = new ImageIcon(destination.toString());
                        imageLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
