import com.sun.xml.internal.ws.api.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Play extends JFrame {

    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    Play(){
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
        button5 = new JButton();
        button6 = new JButton();

        button1.setText("3 * 3");
        button2.setText("4 * 4");
        button4.setText("5 * 5");
        button5.setText("6 * 6");
        button3.setText("自定义图片");
        button6.setText("读取记录");
        button1.setBounds(10,10,100,100);
        button2.setBounds(120,10,100,100);
        button3.setBounds(230,10,100,100);
        button4.setBounds(10,120,100,100);
        button5.setBounds(120,120,100,100);
        button6.setBounds(230,120,100,100);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((JButton)e.getSource() == button1){
                    dispose();
                    new Ping_Tu(9);
                }
                else if((JButton)e.getSource() == button2){
                    dispose();
                    new Ping_Tu(16);
                }
                else if((JButton)e.getSource() == button3){
                    new PictureChoose();
                    dispose();
                }
                else if((JButton)e.getSource() == button4){
                    dispose();
                    new Ping_Tu(25);
                }
                else if((JButton)e.getSource() == button5){
                    dispose();
                    new Ping_Tu(36);
                }
                else if((JButton)e.getSource() == button6){
                    String records3 = null,records4 = null,records5 = null,records6 = null;
                    try {
                        FileReader fileReader = new FileReader("9.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        records3 = bufferedReader.readLine();
                        bufferedReader.close();
                        fileReader.close();

                        fileReader = new FileReader("16.txt");
                        bufferedReader = new BufferedReader(fileReader);
                        records4 = bufferedReader.readLine();
                        bufferedReader.close();
                        fileReader.close();

                        fileReader = new FileReader("25.txt");
                        bufferedReader = new BufferedReader(fileReader);
                        records5 = bufferedReader.readLine();
                        bufferedReader.close();
                        fileReader.close();

                        fileReader = new FileReader("36.txt");
                        bufferedReader = new BufferedReader(fileReader);
                        records6 = bufferedReader.readLine();
                        bufferedReader.close();
                        fileReader.close();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    if(records3.equals("999999")){
                        records3 = "未有记录";
                    }
                    if(records4.equals("999999")){
                        records4 = "未有记录";
                    }
                    if(records5.equals("999999")){
                        records5 = "未有记录";
                    }
                    if(records6.equals("999999")){
                        records6 = "未有记录";
                    }
                    JLabel label = new JLabel("<html>"+"3*3 : "+records3+"<br>"+
                            "4*4 : "+records4+"<br>"+
                            "5*5 : "+records5+"<br>"+
                            "6*6 : "+records6+"</html>");
                    label.setFont(new Font("宋体",0,20));
                    JOptionPane.showMessageDialog(Play.this, label, "胜利", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
        button1.addActionListener(listener);
        button2.addActionListener(listener);
        button3.addActionListener(listener);
        button4.addActionListener(listener);
        button5.addActionListener(listener);
        button6.addActionListener(listener);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        invalidate();
    }
    public static void main(String argc[]){
        new Play();
    }
}
