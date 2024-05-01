import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.awt.*; 
import javax.swing.border.EmptyBorder;  

public class HuffmanSwingGUI extends JFrame {

    private JRadioButton compressRadioButton;
    private JRadioButton decompressRadioButton;
    private JTextField inputFilePathTextField;
    private JTextField outputFilePathTextField;
    private JButton executeButton; 
 
    private JButton browseInputButton;
    private JButton browseOutputButton; 


    public HuffmanSwingGUI() {
        setTitle("Huffman Coding GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        addActionListeners();
        // pack(); // Adjusts frame size based on components
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // private void initComponents() {
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    //     compressRadioButton = new JRadioButton("Compress");
    //     compressRadioButton.setPreferredSize(new Dimension(150, 30)); // Set preferred size
    //     decompressRadioButton = new JRadioButton("Decompress");
    //     decompressRadioButton.setPreferredSize(new Dimension(150, 30)); // Set preferred size
    //     ButtonGroup buttonGroup = new ButtonGroup();
    //     buttonGroup.add(compressRadioButton);
    //     buttonGroup.add(decompressRadioButton);

    //     inputFilePathTextField = new JTextField(20);
    //     outputFilePathTextField = new JTextField(20);

    //     JButton browseInputButton = new JButton("Browse");
    //     JButton browseOutputButton = new JButton("Browse");

    //     executeButton = new JButton("Execute");

    //     panel.add(compressRadioButton);
    //     panel.add(decompressRadioButton);
    //     panel.add(new JLabel("Input File Path:"));
    //     JPanel inputPanel = new JPanel();
    //     inputPanel.add(inputFilePathTextField);
    //     inputPanel.add(browseInputButton);
    //     panel.add(inputPanel);
    //     panel.add(new JLabel("Output File Path:"));
    //     JPanel outputPanel = new JPanel();
    //     outputPanel.add(outputFilePathTextField);
    //     outputPanel.add(browseOutputButton);
    //     panel.add(outputPanel);
    //     panel.add(executeButton);

    //     add(panel);

    //     browseInputButton.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             JFileChooser fileChooser = new JFileChooser();

    //             String initialDir = "C:/study/college/DAA/huffman_cli";
    //             fileChooser.setCurrentDirectory(new File(initialDir));

    //             int returnValue = fileChooser.showOpenDialog(null);
    //             if (returnValue == JFileChooser.APPROVE_OPTION) {
    //                 String selectedPath = fileChooser.getSelectedFile().getPath();
    //                 selectedPath = "/mnt" + "/c" + selectedPath.substring(2);
    //                 selectedPath = selectedPath.replace("\\", "/");
    //                 inputFilePathTextField.setText(selectedPath);
    //             }
    //         }
    //     });

    //     browseOutputButton.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             JFileChooser fileChooser = new JFileChooser();
    //             fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    //             String initialDir = "C:/study/college/DAA/huffman_cli";
    //             fileChooser.setCurrentDirectory(new File(initialDir));

    //             int returnValue = fileChooser.showSaveDialog(null);
    //             if (returnValue == JFileChooser.APPROVE_OPTION) {
    //                 String selectedPath = fileChooser.getSelectedFile().getPath();
    //                 selectedPath = "/mnt" + "/c" + selectedPath.substring(2);
    //                 selectedPath = selectedPath.replace("\\", "/");
    //                 outputFilePathTextField.setText(selectedPath);
    //             }
    //         }
    //     });

    // }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        compressRadioButton = new JRadioButton("Compress");
        decompressRadioButton = new JRadioButton("Decompress");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(compressRadioButton);
        buttonGroup.add(decompressRadioButton);

        inputFilePathTextField = new JTextField(20);
        outputFilePathTextField = new JTextField(20);

        browseInputButton = new JButton("Browse");
        browseOutputButton = new JButton("Browse");
        executeButton = new JButton("Execute");

        // Set button colors and font
        browseInputButton.setForeground(Color.WHITE);
        browseInputButton.setBackground(new Color(52, 152, 219)); // Blue
        browseInputButton.setFocusPainted(false); // Remove focus border
        browseOutputButton.setForeground(Color.WHITE);
        browseOutputButton.setBackground(new Color(52, 152, 219)); // Blue
        browseOutputButton.setFocusPainted(false);
        executeButton.setForeground(Color.WHITE);
        executeButton.setBackground(new Color(39, 174, 96)); // Green
        executeButton.setFocusPainted(false);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(compressRadioButton, gbc);
        panel.add(decompressRadioButton, gbc);
        panel.add(new JLabel("Input File Path:"), gbc);
        panel.add(inputFilePathTextField, gbc);
        panel.add(browseInputButton, gbc);
        panel.add(new JLabel("Output File Path:"), gbc);
        panel.add(outputFilePathTextField, gbc);
        panel.add(browseOutputButton, gbc);
        panel.add(executeButton, gbc);

        getContentPane().add(panel);

            browseInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                String initialDir = "C:/study/college/DAA/huffman_cli";
                fileChooser.setCurrentDirectory(new File(initialDir));

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedPath = fileChooser.getSelectedFile().getPath();
                    selectedPath = "/mnt" + "/c" + selectedPath.substring(2);
                    selectedPath = selectedPath.replace("\\", "/");
                    inputFilePathTextField.setText(selectedPath);
                }
            }
        });

        browseOutputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                String initialDir = "C:/study/college/DAA/huffman_cli";
                fileChooser.setCurrentDirectory(new File(initialDir));

                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedPath = fileChooser.getSelectedFile().getPath();
                    selectedPath = "/mnt" + "/c" + selectedPath.substring(2);
                    selectedPath = selectedPath.replace("\\", "/");
                    outputFilePathTextField.setText(selectedPath);
                }
            }
        });
    }
    

    private void addActionListeners() {
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean compress = compressRadioButton.isSelected();
                boolean decompress = decompressRadioButton.isSelected();
                String inputFilePath = inputFilePathTextField.getText();
                String outputFilePath = outputFilePathTextField.getText();

                if (compress && decompress) {
                    JOptionPane.showMessageDialog(HuffmanSwingGUI.this,
                            "Cannot compress and decompress at the same time");
                    return;
                }

                if (!compress && !decompress) {
                    JOptionPane.showMessageDialog(HuffmanSwingGUI.this, "Please specify either compress or decompress");
                    return;
                }

                // Construct command-line arguments based on Swing GUI input
                String[] commandLineArgs;
                if (compress) {
                    commandLineArgs = new String[] { "-c", "-i", inputFilePath, "-o", outputFilePath };
                } else {
                    commandLineArgs = new String[] { "-d", "-i", inputFilePath, "-o", outputFilePath };
                }

                // Invoke command-line application with constructed arguments
                try {
                    System.out.println("herre");
                    StringBuilder command = new StringBuilder(
                            "wsl java -cp \"picocli-4.7.5.jar:./huffman_cli.jar\" com.huffman_cli.App ");
                    for (int i = 0; i < commandLineArgs.length; i++) {
                        command.append(commandLineArgs[i]);
                        command.append(" ");
                    }
                    @SuppressWarnings("deprecation")
                    Process process = Runtime.getRuntime().exec(command.toString());
                    System.out.println(command.toString());
                    // Optionally, read output from the process or handle errors
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HuffmanSwingGUI();
            }
        });
    }
}
