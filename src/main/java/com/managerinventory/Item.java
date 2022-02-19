package com.managerinventory;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Import necessary modules

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Item extends JPanel {
    // This class is responsible for getting data from the user and populating the
    // panel objects with that information.

    // Variables used throughout the class.

    private static JDialog d;
    JTextField t1, t2;
    String itemName, itemAmount;
    boolean valid = true;

    public JPanel makepanel(JPanel masterPanel) {

        // Initializes the panel that will be returned to the master panel.

        final JPanel panel = new JPanel();
        final JLabel count;

        // adds all elements to the panel
        panel.setLayout(new GridLayout());

        panel.add(new JLabel("Item Name: "));
        panel.add(new JLabel(itemName));
        panel.add(new JLabel("Item Quantity: "));
        count = new JLabel(itemAmount);
        panel.add(count);
        panel.setBorder(new EmptyBorder(new Insets(15, 15, 15, 15)));

        // Event listener for the delete button.
        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                valid = false;
            }
        });

        // Event listener for the addition button.
        JButton plus1 = new JButton("+1");
        plus1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(itemAmount);
                amount += 1;
                itemAmount = String.valueOf(amount);
                count.setText(itemAmount);

            }
        });

        // Event listener for the subtraction button.
        JButton minus1 = new JButton("-1");
        minus1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(itemAmount);
                amount -= 1;
                itemAmount = String.valueOf(amount);
                count.setText(itemAmount);
            }
        });

        // formatting and additional population of the panel.
        panel.setMaximumSize(new Dimension(900, 50));
        delete.setMinimumSize(new Dimension(150, 25));
        panel.add(plus1);
        panel.add(minus1);
        panel.add(delete);
        return panel;
    }

    public void getInfo(JFrame frame) {
        // Responsible for popping up a dialog box that the user can input information
        // in.
        d = new JDialog(frame, "New Item", true);
        d.setLayout(new FlowLayout());

        JButton b = new JButton("OK");
        t1 = new JTextField("Product Name");
        t2 = new JTextField("1");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                itemName = t1.getText();
                itemAmount = t2.getText();

                Item.d.setVisible(false);
            }
        });

        d.add(t1);
        d.add(t2);

        d.add(b);
        d.setSize(300, 300);
        d.setVisible(true);
    }

}