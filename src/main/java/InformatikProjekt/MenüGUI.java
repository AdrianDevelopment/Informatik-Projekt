package InformatikProjekt;

import javax.swing.JFrame;

public class MenüGUI {

public void guiStart(){
    JFrame frame = new JFrame();
    frame.setTitle("Startmenue");
    frame.setSize(500,500);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    //frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
}
}
