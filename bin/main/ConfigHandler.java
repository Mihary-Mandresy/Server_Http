package server.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

import server.frame.PanelConfig;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

public class ConfigHandler extends JFrame {

    public static final int width = 900;
    public static final int height = 600;
    
    public ConfigHandler() throws Exception {
        setTitle("Configuration");
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);

        JLabel titre = new JLabel("Apache MV");
        titre.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        titre.setBounds(0, 0, width, 80);
        titre.setBackground(Color.black);
        titre.setOpaque(true);
        titre.setFont(new Font("arial", Font.BOLD, 30));
        titre.setForeground(Color.white);

        c.add(titre);

        PanelConfig conf = new PanelConfig(this);
        conf.setBounds(0, 80, width, height - 80);

        c.add(conf);

        setVisible(true);
    }
}
