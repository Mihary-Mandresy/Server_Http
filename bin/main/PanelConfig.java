package server.frame;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import server.Serveur;

public class PanelConfig extends JPanel {

    private JFrame parent;

    public PanelConfig(JFrame parent) throws Exception {
        this.parent = parent;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        HashMap<String, String> mapy = Serveur.getDataInFile();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel portLabel = new JLabel("Port :");
        portLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(portLabel, gbc);

        JTextField portField = new JTextField(20);
        portField.setText(mapy.get("port"));
        portField.setFont(new Font("Tahoma", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(portField, gbc);

        JLabel rootLabel = new JLabel("Racine du projet :");
        rootLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(rootLabel, gbc);

        JTextField rootField = new JTextField(20);
        rootField.setFont(new Font("Tahoma", Font.BOLD, 20));
        rootField.setText(mapy.get("root"));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(rootField, gbc);

        JLabel phpLabel = new JLabel("Interpréter le PHP :");
        phpLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(phpLabel, gbc);

        JCheckBox phpCheckbox = new JCheckBox();
        phpCheckbox.setSelected(Boolean.valueOf(mapy.get("php")));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(phpCheckbox, gbc);

        JButton validateButton = new JButton("Valider");
        validateButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(validateButton, gbc);

        validateButton.addActionListener(e -> {
            String port = portField.getText();
            String rootPath = rootField.getText();
            boolean interpretPhp = phpCheckbox.isSelected();

            if (port.isEmpty() || rootPath.isEmpty()) {
                JOptionPane.showConfirmDialog(this, "Inserer bien le port ou le root", "Erreur",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            } else {
                HashMap<String, String> vaovao = new HashMap<>();
                vaovao.put("port", port);
                vaovao.put("root", rootPath);
                vaovao.put("php", String.valueOf(interpretPhp));

                Serveur.editConfig(vaovao);

                String after = "Le serveur doit etre redémmarer";

                JOptionPane.showMessageDialog(
                        this,
                        "Configuration :\nPort : " + port + "\nRacine : " + rootPath + "\nPHP : "
                                + (interpretPhp ? "Oui" : "Non") + "\n" + after,
                        "Confirmation",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Serveur closed");
                System.exit(0);
            }

        });
    }
}