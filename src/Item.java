
// Import necessary modules
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Item extends JPanel {
    // This class is responsible for getting data from the user and populating the
    // panel objects with that information.

    // Variables used throughout the class.
    private static JDialog d;
    JTextField t1, t2;
    String s1, s2;

    public JPanel makepanel(JPanel masterPanel) {

        // Initializes the panel that will be returned to the master panel.
        JPanel panel = new JPanel();
        JLabel count;

        // adds all elements to the panel
        panel.setLayout(new GridLayout());
        panel.add(new JLabel("Item Name: "));
        panel.add(new JLabel(s1));
        panel.add(new JLabel("Item Quantity: "));
        count = new JLabel(s2);
        panel.add(count);
        panel.setBorder(new EmptyBorder(new Insets(15, 15, 15, 15)));

        // Event listener for the delete button.
        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
            }
        });

        // Event listener for the addition button.
        JButton plus1 = new JButton("+1");
        plus1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(s2);
                amount += 1;
                s2 = String.valueOf(amount);
                count.setText(s2);

            }
        });

        // Event listener for the subtraction button.
        JButton minus1 = new JButton("-1");
        minus1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(s2);
                amount -= 1;
                s2 = String.valueOf(amount);
                count.setText(s2);
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