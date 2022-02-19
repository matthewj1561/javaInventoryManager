package com.managerinventory;

// Import necessary modules
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.google.cloud.firestore.DocumentSnapshot;

import org.springframework.beans.factory.annotation.Autowired;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Main implements ActionListener {

    // Class wide variables
    JScrollPane scrlpane;
    static FirebaseInitialize innit = new FirebaseInitialize();
    JPanel masterPanel = new JPanel();
    JFrame masterFrame;
    JScrollPane jScrollPane;
    Map<String, Object> items = new HashMap<>();
    ArrayList<Item> itemArray = new ArrayList<>();

    // Program Entry Point.

    @Autowired
    public static void main(String args[]) {
        innit.initialize();
        new Main();
    }

    public Main() {

        // Init the frame
        JFrame frame = new JFrame("Inventory Manager");
        masterFrame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Init the scrolling main section
        JLabel label = new JLabel("Label");
        label.setPreferredSize(new Dimension(1000, 1000));
        jScrollPane = new JScrollPane(label);

        // Init the add info button
        JButton newItemBtn = new JButton("New Item");
        newItemBtn.addActionListener(this);

        // Init the save button
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // System.out.println(items);
                for (Item item : itemArray) {
                    if (item.valid) {
                        items.put(item.itemName, item.itemAmount);
                    } else {
                        boolean keyIsPresent = items.containsKey(item.itemName);
                        if (keyIsPresent) {
                            items.remove(item.itemName);
                        }
                    }
                }

                try {
                    FirebaseService.saveItem(items);
                } catch (InterruptedException | ExecutionException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        // Init the load button
        JButton loadBtn = new JButton("Load");
        loadBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    masterPanel.removeAll();
                    masterPanel.revalidate();
                    masterPanel.repaint();
                    Map<String, Object> document = FirebaseService.loadItem().getData();
                    for (String key : document.keySet()) {
                        Item item = new Item();
                        item.itemName = key;
                        item.itemAmount = (String) document.get(key);
                        render(item);
                    }
                } catch (InterruptedException | ExecutionException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        // Init the delete all button with functionality to remove all items.
        JButton deleteAll = new JButton("Delete All");
        deleteAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                masterPanel.removeAll();
                masterPanel.revalidate();
                masterPanel.repaint();
            }
        });

        // Layout all elements on JFrame.
        masterFrame.add(deleteAll, BorderLayout.SOUTH);
        masterFrame.add(saveBtn, BorderLayout.EAST);
        masterFrame.add(loadBtn, BorderLayout.WEST);
        masterFrame.add(newItemBtn, BorderLayout.NORTH);

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setViewportBorder(new LineBorder(Color.RED));
        jScrollPane.setBorder(new EmptyBorder(25, 25, 25, 25));
        jScrollPane.getViewport().add(scrlpane, BorderLayout.CENTER);

        masterFrame.add(jScrollPane, BorderLayout.CENTER);
        masterFrame.setSize(1200, 750);
        masterFrame.setVisible(true);
    }

    // Fires when addItem button is pressed.
    public void actionPerformed(ActionEvent e) {

        this.update();
    }

    public void update() {

        // Uses the item class to make a panel that will be added to the scrollable
        // window.
        Item item = new Item();
        item.getInfo(masterFrame);

        masterPanel.add(item.makepanel(masterPanel));

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.PAGE_AXIS));
        itemArray.add(item);
        scrlpane = new JScrollPane(masterPanel);
        jScrollPane.getViewport().add(scrlpane, BorderLayout.CENTER);

    }

    public void render(Item item) {

        masterPanel.add(item.makepanel(masterPanel));

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.PAGE_AXIS));
        itemArray.add(item);
        scrlpane = new JScrollPane(masterPanel);
        jScrollPane.getViewport().add(scrlpane, BorderLayout.CENTER);
    }
}