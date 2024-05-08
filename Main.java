import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
    private JPanel welcomePanel;
    private JPanel addPanel;
    private JPanel viewPanel;
    private ImagePanel imagePanel1;
    private ImagePanel imagePanel2;
    private List<File> uploadedFiles; // List to store uploaded files
    private List<Specimen> specimens;
    private List<String> uploadImagePaths;
    private JComboBox<String> specimenComboBox; // JComboBox to display specimen names

    private JButton continueButton;

    public Main() {
        setTitle("Specimen Profile Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 600);


        specimens = new ArrayList<>();
        startPanel();
        GUI();


        uploadImagePaths = new ArrayList<>(); // Initialize the list
        uploadedFiles = new ArrayList<>();

    }

    private void startPanel() {
        welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.lightGray);
        welcomePanel.setPreferredSize(new Dimension(800, 50));
        JLabel welcomeMessage = new JLabel("Click one of the options below to begin.");
        welcomeMessage.setFont(new Font("Serif", Font.BOLD, 20));
        welcomePanel.add(welcomeMessage);

        JButton addNew = new JButton("Add a new specimen");
        addNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().add(getAddPanel(), BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        welcomePanel.add(addNew);

        JButton view = new JButton("View a specimen");
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().add(getViewPanel(), BorderLayout.CENTER);
                revalidate();
                repaint();

                displaySavedSpecimens(); // Display the saved specimens
            }
        });
        welcomePanel.add(view);

        add(welcomePanel, BorderLayout.NORTH);
    }

    public JPanel getAddPanel() {
        addPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Start building a new specimen profile.");
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int warning = JOptionPane.showConfirmDialog(addPanel, "Your progress will not be saved. Are you sure you want to go back?", "WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (warning == JOptionPane.YES_OPTION) {
                    getContentPane().removeAll();
                    setImage(null);
                    getContentPane().add(welcomePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            }
        });

        // Create a button to name the specimens
        JButton nameButton = new JButton("Name Specimens");
        nameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameSpecimens();
            }
        });

        continueButton = new JButton("Continue");
        continueButton.setEnabled(false); // Initially disable the button
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel infoPanel = new JPanel(new GridLayout(5, 2)); // Create a panel for input fields
                JTextField dateField = new JTextField(20);
                JComboBox<String> speciesCombo = new JComboBox<>(new String[]{"Onthophagus orpheus", "Onthophagus taurus"});
                JTextField pronotumWidthField = new JTextField(20);
                JTextField hornLengthField = new JTextField(20);
                JTextField hornWidthField = new JTextField(20);

                // Add labels and text fields to the infoPanel
                infoPanel.add(new JLabel("Date:"));
                infoPanel.add(dateField);
                infoPanel.add(new JLabel("Species:"));
                infoPanel.add(speciesCombo);
                infoPanel.add(new JLabel("Pronotum Width:"));
                infoPanel.add(pronotumWidthField);
                infoPanel.add(new JLabel("Horn Length:"));
                infoPanel.add(hornLengthField);
                infoPanel.add(new JLabel("Horn Width:"));
                infoPanel.add(hornWidthField);


                // Show the input dialog
                int result = JOptionPane.showConfirmDialog(addPanel, infoPanel, "Enter Specimen Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    // Retrieve values from text fields
                    String date = dateField.getText();
                    String species = (String) speciesCombo.getSelectedItem();
                    double pronotumWidth = Double.parseDouble(pronotumWidthField.getText());
                    double hornLength = Double.parseDouble(hornLengthField.getText());
                    double hornWidth = Double.parseDouble(hornWidthField.getText());

                    String specimenData = "Date: " + date + ", Species: " + species + ", Pronotum Width: " + pronotumWidth + ", Horn Length: " + hornLength + ", Horn Width: " + hornWidth;
                    System.out.println(specimenData);

                    // Create a new Specimen object with the retrieved values
                    for (int i = 0; i < uploadImagePaths.size(); i++) {
                        String imagePath = uploadImagePaths.get(i);
                        Specimen specimen = new Specimen(imagePath, date, species, pronotumWidth, hornLength, hornWidth);
                        specimens.add(specimen);
                }

                    // Clear the list of uploaded files
                    uploadImagePaths.clear();
                    getContentPane().removeAll();
                    getContentPane().add(welcomePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
            }
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(label);
        topPanel.add(backButton);
        topPanel.add(nameButton); // Add the name button
        topPanel.add(continueButton);

        JPanel uploadPanel = new JPanel();
        JButton uploadButton = new JButton("Upload Files");
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile(); // Open the file
            }
        });

        uploadPanel.add(uploadButton);

        JPanel imageContainerPanel = new JPanel(new GridLayout(1, 2));

        // Create two ImagePanel instances for displaying images side by side
        imagePanel1 = new ImagePanel();
        imagePanel2 = new ImagePanel();

        imageContainerPanel.add(imagePanel1);
        imageContainerPanel.add(imagePanel2);

        addPanel.add(uploadPanel, BorderLayout.NORTH);
        addPanel.add(imageContainerPanel, BorderLayout.CENTER);
        addPanel.add(topPanel, BorderLayout.SOUTH);

        return addPanel;
    }

    public JPanel getViewPanel() {
        viewPanel = new JPanel();
        viewPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("View a saved specimen profile.");
        viewPanel.add(titleLabel, BorderLayout.NORTH);

        specimenComboBox = new JComboBox<>();  // Create a JComboBox to display specimen names
        specimenComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = specimenComboBox.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Display the selected specimen's images and data
                    displaySpecimen(specimens.get(selectedIndex));
                }
            }
        });

        viewPanel.add(specimenComboBox, BorderLayout.WEST); // Add the combo box to the view panel

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().add(welcomePanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        viewPanel.add(back, BorderLayout.SOUTH);

        return viewPanel;
    }

    // Method to name the specimens
    private void nameSpecimens() {
        if (uploadedFiles.isEmpty()) {
            JOptionPane.showMessageDialog(addPanel, "No specimens uploaded to name.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String name = JOptionPane.showInputDialog(addPanel, "Enter a name for the specimens:", "Name Specimens", JOptionPane.PLAIN_MESSAGE);
        if (name != null && !name.isEmpty()) {
            for (int i = 0; i < uploadImagePaths.size(); i++) {
                String imagePath = uploadImagePaths.get(i);

                JPanel infoPanel = new JPanel(new GridLayout(5, 2)); // Create a panel for input fields
                JTextField dateField = new JTextField(20);
                JComboBox<String> speciesCombo = new JComboBox<>(new String[]{"Onthophagus orpheus", "Onthophagus taurus"});
                JTextField pronotumWidthField = new JTextField(20);
                JTextField hornLengthField = new JTextField(20);
                JTextField hornWidthField = new JTextField(20);

                // Add labels and text fields to the infoPanel
                infoPanel.add(new JLabel("Date:"));
                infoPanel.add(dateField);
                infoPanel.add(new JLabel("Species:"));
                infoPanel.add(speciesCombo);
                infoPanel.add(new JLabel("Pronotum Width:"));
                infoPanel.add(pronotumWidthField);
                infoPanel.add(new JLabel("Horn Length:"));
                infoPanel.add(hornLengthField);
                infoPanel.add(new JLabel("Horn Width:"));
                infoPanel.add(hornWidthField);

                // Show the input dialog
                int result = JOptionPane.showConfirmDialog(addPanel, infoPanel, "Enter Specimen Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    // Retrieve values from text fields
                    String date = dateField.getText();
                    String species = (String) speciesCombo.getSelectedItem();
                    double pronotumWidth = Double.parseDouble(pronotumWidthField.getText());
                    double hornLength = Double.parseDouble(hornLengthField.getText());
                    double hornWidth = Double.parseDouble(hornWidthField.getText());

                    // Create a new Specimen object with the retrieved values
                    Specimen specimen = new Specimen(imagePath, date, species, pronotumWidth, hornLength, hornWidth);
                    specimen.setName(name);
                    specimens.add(specimen);

                    // Clear the list of uploaded files
                    uploadImagePaths.clear();

                    // Navigate back to the welcome panel
                    getContentPane().removeAll();
                    getContentPane().add(welcomePanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                }
                continueButton.setEnabled(true);

            // Do something with the uploaded files and the name
            System.out.println("Specimens named: " + name);
            uploadImagePaths.clear(); // Clear the list of uploaded files

            // Increment the count of specimens
            System.out.println("Number of specimens: " + specimens.size());

            // Update the display of saved specimens
            displaySavedSpecimens();
        }
            }
    }


        //     Method to display the selected specimen's images and data
    private void displaySpecimen(Specimen specimen) {
        // Clear the viewPanel
        viewPanel.removeAll();


        // Create JPanel to hold specimen details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0, 1));

        // Add specimen details labels to the detailsPanel
        detailsPanel.add(new JLabel("Date: " + specimen.getDate()));
        detailsPanel.add(new JLabel("Species: " + specimen.getSpecies()));
        detailsPanel.add(new JLabel("Pronotum Width: " + specimen.getPronotumWidth()));
        detailsPanel.add(new JLabel("Horn Length: " + specimen.getHornLength()));
        detailsPanel.add(new JLabel("Horn Width: " + specimen.getHornWidth()));

        JButton backButton = new JButton("Back"); // back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove all components from the current panel (viewPanel)
                viewPanel.removeAll();
                getContentPane().removeAll();                 // Add the welcome panel to the frame
                getContentPane().add(welcomePanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        detailsPanel.add(backButton);


        // Add detailsPanel to the viewPanel
        viewPanel.add(detailsPanel, BorderLayout.CENTER);

        // Revalidate and repaint the viewPanel to update the UI
        viewPanel.revalidate();
        viewPanel.repaint();
    }


    public void displaySavedSpecimens() {
        if (specimenComboBox == null) {
            // Initialize the viewPanel and specimenComboBox if they are null
            viewPanel = getViewPanel();
        }

        if (specimenComboBox != null) {
            // Clear existing items in the combo box
            specimenComboBox.removeAllItems();

            // Display the saved specimens in the JComboBox
            for (int i = 0; i < specimens.size(); i++) {
                Specimen specimen = specimens.get(i);
                specimenComboBox.addItem(specimen.getName());
            }
        } else {
            System.out.println("Error: specimenComboBox is null");
        }

        System.out.println("Number of specimens in displaySavedSpecimens(): " + specimens.size());
    }


    public void GUI() {
        setVisible(true);
    }

    public boolean openFile() {
        System.out.println("openFile() method called");
        try {
            File currentDirectory = new File("orpheus_males_test");
            JFileChooser fc = new JFileChooser(currentDirectory);

            FileNameExtensionFilter filter = new FileNameExtensionFilter(".TIF files", "tif");
            fc.setFileFilter(filter);

            int result = fc.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = new File(fc.getSelectedFile().getAbsolutePath());
                System.out.println("Selected Filepath: " + selectedFile.getAbsolutePath());
                System.out.println("Filepath: " + selectedFile);

                // Create a JComboBox for the user selection
                String[] options = {"Clean", "Annotated"};
                JComboBox<String> comboBox = new JComboBox<>(options);
                int optionResult = JOptionPane.showOptionDialog(addPanel, comboBox, "Select Image Type", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                // Check user selection
                String imageType = "";
                if (optionResult == JOptionPane.OK_OPTION) {
                    imageType = (String) comboBox.getSelectedItem();
                    System.out.println("Image Type: " + imageType);
                } else {
                    System.out.println("User canceled image type selection.");
                    return false;
                }

                BufferedImage img = ImageIO.read(selectedFile);
                uploadedFiles.add(selectedFile); // Add the uploaded file to the list

                // Determine which ImagePanel should display the image
                if (imagePanel1.getImage() == null) {
                    imagePanel1.setImage(img); // Set the image to the first ImagePanel
                } else if (imagePanel2.getImage() == null) {
                    imagePanel2.setImage(img); // Set the image to the second ImagePanel
                } else {
                    // Handle the case where there are no available ImagePanels
                    JOptionPane.showMessageDialog(addPanel, "Only two images can be uploaded.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }

                // If both images are uploaded, create a new specimen
                if (imagePanel1.getImage() != null && imagePanel2.getImage() != null) {
                    // Clear the uploadImagePaths list before adding the current file
                    uploadImagePaths.clear();
                    uploadImagePaths.add(selectedFile.getAbsolutePath());

                    // Prompt the user to name the specimens
                    nameSpecimens();
                }

                return true;
            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.println("Canceled");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return false;
    }


    public void setImage(BufferedImage img) {
        if (imagePanel1 != null && imagePanel2 != null) {
            imagePanel1.setImage(img);
            imagePanel2.setImage(img);
            revalidate();
            repaint();
        }
    }

    static class ImagePanel extends JPanel {
        private BufferedImage image;

        public void setImage(BufferedImage img) {
            System.out.println("setImage() method called");
            this.image = img;
            repaint();
        }

        public BufferedImage getImage() {
            return image;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                int width = getWidth();
                int height = getHeight();
                double scaleFactor = Math.min(1.0 * width / image.getWidth(), 1.0 * height / image.getHeight());
                int scaledWidth = (int) (scaleFactor * image.getWidth());
                int scaledHeight = (int) (scaleFactor * image.getHeight());
                int x = (width - scaledWidth) / 2;
                int y = (height - scaledHeight) / 2;
                g.drawImage(image, x, y, scaledWidth, scaledHeight, this);
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.GUI();
        Specimen specimen = new Specimen("imagePath", "date", "species", 0.0, 0.0, 0.0);
        main.specimens.add(specimen);
}
}