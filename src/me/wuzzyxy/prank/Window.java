package me.wuzzyxy.prank;

import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        super("Hoe?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        JLabel label = new JLabel("Are you a hoe?");

        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");
        JButton maybe = new JButton("Maybe");

        yes.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "You are a hoe!");
            this.close();
        });

        add(label);
        add(yes);
        add(no);
        add(maybe);

        setVisible(true);
    }

    public void close() {
        this.dispose();
    }
}
