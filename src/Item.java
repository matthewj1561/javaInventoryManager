import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class Item extends JPanel {
    private static JDialog d;
    JTextField t1, t2;
    String s1, s2;

    public Item() {

    }

    public JPanel makepanel(JPanel masterPanel) {
        JPanel panel = new JPanel();
        JLabel count;

        // panel.setLayout(new BoxLayout(panel, 0));
        panel.setLayout(new GridLayout());
        // panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(new JLabel("Item Name: "));
        panel.add(new JLabel(s1));
        panel.add(new JLabel("Item Quantity: "));
        count = new JLabel(s2);
        panel.add(count);
        panel.setBorder(new EmptyBorder(new Insets(15, 15, 15, 15)));
        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
            }
        });
        JButton plus1 = new JButton("+1");
        plus1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(s2);
                amount += 1;
                s2 = String.valueOf(amount);
                count.setText(s2);

            }
        });
        JButton minus1 = new JButton("-1");
        minus1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(s2);
                amount -= 1;
                s2 = String.valueOf(amount);
                count.setText(s2);
            }
        });

        panel.setMaximumSize(new Dimension(900, 50));
        delete.setMinimumSize(new Dimension(150, 25));
        panel.add(plus1);
        panel.add(minus1);
        panel.add(delete);
        return panel;
    }

    public void getInfo(JFrame frame) {
        d = new JDialog(frame, "Dialog Example", true);
        d.setLayout(new FlowLayout());

        JButton b = new JButton("OK");
        t1 = new JTextField("Product Name");
        t2 = new JTextField("1");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                s1 = t1.getText();
                s2 = t2.getText();

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