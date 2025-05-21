package InformatikProjekt;

import javax.swing.*;

public class MenueGUI {

    public void guiStart(){
        JFrame frame = new JFrame("Startmenü");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Schafkopf");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        frame.add(label);

        JButton button = new JButton("Spiel starten");
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        frame.add(button);
        button.addActionListener(e -> {System.out.println("Spiel gestartet");});

        frame.setVisible(true);
    }
}
