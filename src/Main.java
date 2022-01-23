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
import java.awt.event.*;
import java.util.ArrayList;

public class Main implements ActionListener {
    JScrollPane scrlpane;
    ArrayList<JPanel> items = new ArrayList<JPanel>();
    JPanel masterPanel = new JPanel();
    JFrame masterFrame;
    JScrollPane jScrollPane;
    int uniqueItems;

    public static void main(String args[]) {
        new Main();
    }

    public Main() {
        // Init the frame
        JFrame frame = new JFrame("Tabbed Pane Sample");
        masterFrame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Init the scrolling main section
        JLabel label = new JLabel("Label");
        label.setPreferredSize(new Dimension(1000, 1000));
        jScrollPane = new JScrollPane(label);

        // Init the add info button
        JButton newItemBtn = new JButton("New Item");
        newItemBtn.addActionListener(this);

        JButton deleteAll = new JButton("Delete All");
        deleteAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                masterPanel.removeAll();
                masterPanel.revalidate();
                masterPanel.repaint();
            }
        });
        masterFrame.add(deleteAll, BorderLayout.SOUTH);
        // to stores the ratings (1 stat, 2 star, etc)

        masterFrame.add(newItemBtn, BorderLayout.NORTH);

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setViewportBorder(new LineBorder(Color.RED));
        jScrollPane.setBorder(new EmptyBorder(25, 25, 25, 25));
        jScrollPane.getViewport().add(scrlpane, BorderLayout.CENTER);

        masterFrame.add(jScrollPane, BorderLayout.CENTER);
        masterFrame.setSize(1200, 750);
        masterFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        this.update();
    }

    public void update() {

        uniqueItems += 1;

        Item item = new Item();
        item.getInfo(masterFrame);

        masterPanel.add(item.makepanel(masterPanel));
        // masterFrame.getContentPane().add(scrlpane, BorderLayout.CENTER);
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.PAGE_AXIS));

        scrlpane = new JScrollPane(masterPanel);
        jScrollPane.getViewport().add(scrlpane, BorderLayout.CENTER);

        // masterFrame.revalidate();
        // masterFrame.repaint();
        // masterFrame.setVisible(true);
    }
}