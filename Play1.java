import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.glass.ui.Cursor.setVisible;

public class Play1 extends JFrame {
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    Play1(){
        init();
        this.setTitle("拼图");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,400,400);
        setVisible(true);
        validate();
    }
    void init(){
        setLayout(null);
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button1.setText("3 * 3");
        button2.setText("4 * 4");
        button3.setText("5 * 5");
        button4.setText("6 * 6");

        button1.setBounds(10,10,100,100);
        button2.setBounds(120,10,100,100);
        button3.setBounds(10,120,100,100);
        button4.setBounds(120,120,100,100);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((JButton)e.getSource() == button1){
                    new ImageSplit(ImageSplit.originalImg,3);
                    dispose();
                }
                else if((JButton)e.getSource() == button2){
                    new ImageSplit(ImageSplit.originalImg,4);
                    dispose();
                }
                else if((JButton)e.getSource() == button3){
                    new ImageSplit(ImageSplit.originalImg,5);
                    dispose();
                }
                else if((JButton)e.getSource() == button4){
                    new ImageSplit(ImageSplit.originalImg,6);
                    dispose();
                }
            }
        };
        button1.addActionListener(listener);
        button2.addActionListener(listener);
        button3.addActionListener(listener);
        button4.addActionListener(listener);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        invalidate();
    }
}
