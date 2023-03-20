package me.wuzzyxy.prank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {

    boolean hovering = false;
    int buttWidth = 80;
    int buttHeight = 40;
    public Window() {
        super("Hoe?");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        JLabel label = new JLabel("Are you a hoe?");
        label.setBounds(getSize().width/2-getSize().width/8, 100, 200, 100);

        JButton yes = new JButton("Yes");
        yes.setBounds(getSize().width/4-getSize().width/8, 250, buttWidth, buttHeight);
        JButton maybe = new JButton("Maybe");
        maybe.setBounds(getSize().width/4*2-getSize().width/8, 250, buttWidth, buttHeight);
        JButton no = new JButton("No");
        no.setOpaque(false);
        no.setBounds(getSize().width/4*3-getSize().width/8, 250, buttWidth, buttHeight);

        yes.addActionListener(e -> {
            label.setText("You are a hoe!");
            label.setForeground(Color.RED);
            //make a timer that shuts it off after 5 seconds
            Timer timer = new Timer(1000, e1 -> {
                close();
            });
            timer.start();
        });
        //no logic
        no.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
            }
        });
        Timer timer = new Timer(10, e -> {
            if(hovering) {
                closestSide(no, MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
            }
        });
        timer.start();

        maybe.addActionListener(e -> {
            maybe.setVisible(false);
        });

        add(label);
        add(yes);
        add(maybe);
        add(no);

        setVisible(true);
    }

    public void close() {
        this.dispose();
    }
    public static void closestSide(JComponent butt, double pointX, double pointY) {
        double rectX = butt.getX();
        double rectY = butt.getY();
        double rectWidth = butt.getWidth();
        double rectHeight = butt.getHeight();

        double rectCenterX = rectX + rectWidth / 2;
        double rectCenterY = rectY + rectHeight / 2;

        // Calculate distances from point to each side and corner of the rectangle
        double distLeft = Math.abs(pointX - rectX);
        double distRight = Math.abs(pointX - (rectX + rectWidth));
        double distTop = Math.abs(pointY - rectY);
        double distBottom = Math.abs(pointY - (rectY + rectHeight));
        double distTopLeft = Math.sqrt(Math.pow(pointX - rectX, 2) + Math.pow(pointY - rectY, 2));
        double distTopRight = Math.sqrt(Math.pow(pointX - (rectX + rectWidth), 2) + Math.pow(pointY - rectY, 2));
        double distBottomLeft = Math.sqrt(Math.pow(pointX - rectX, 2) + Math.pow(pointY - (rectY + rectHeight), 2));
        double distBottomRight = Math.sqrt(Math.pow(pointX - (rectX + rectWidth), 2) + Math.pow(pointY - (rectY + rectHeight), 2));

        // Calculate closest side or corner based on minimum distance
        if (distLeft <= distRight && distLeft <= distTop && distLeft <= distBottom && (distLeft <= distTopLeft && distLeft <= distBottomLeft)) {
            //return "left";
            butt.setBounds((int) (rectX + 1), (int) rectY, (int) rectWidth, (int) rectHeight);
        } else if (distRight <= distLeft && distRight <= distTop && distRight <= distBottom && (distRight <= distTopRight && distRight <= distBottomRight)) {
            //return "right";
            butt.setBounds((int) (rectX - 1), (int) rectY, (int) rectWidth, (int) rectHeight);

        } else if (distTop <= distLeft && distTop <= distRight && distTop <= distBottom && (distTop <= distTopLeft && distTop <= distTopRight)) {
            //return "top";
            butt.setBounds((int) rectX, (int) (rectY - 1), (int) rectWidth, (int) rectHeight);
        } else if (distBottom <= distLeft && distBottom <= distRight && distBottom <= distTop && (distBottom <= distBottomLeft && distBottom <= distBottomRight)) {
            //return "bottom";
            butt.setBounds((int) rectX, (int) (rectY - 1), (int) rectWidth, (int) rectHeight);
        } else if (distTopLeft <= distTopRight && distTopLeft <= distBottomLeft && distTopLeft <= distBottomRight) {
            //return "top-left";
            butt.setBounds((int) (rectX + 1), (int) (rectY + 1), (int) rectWidth, (int) rectHeight);
        } else if (distTopRight <= distTopLeft && distTopRight <= distBottomLeft && distTopRight <= distBottomRight) {
            //return "top-right";
            butt.setBounds((int) (rectX - 1), (int) (rectY + 1), (int) rectWidth, (int) rectHeight);
        } else if (distBottomLeft <= distTopLeft && distBottomLeft <= distTopRight && distBottomLeft <= distBottomRight) {
            //return "bottom-left";
            butt.setBounds((int) (rectX + 1), (int) (rectY - 1), (int) rectWidth, (int) rectHeight);
        } else {
            //return "bottom-right";
            butt.setBounds((int) (rectX - 1), (int) (rectY - 1), (int) rectWidth, (int) rectHeight);
        }
    }


}
