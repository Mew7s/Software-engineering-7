import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

public class NewPing_Tu extends JFrame implements MouseListener {
    //设置总块数
    int number;
    //每一个拼图图块的大小
    private int width = 100;
    private int height = 100;

    ImageIcon imageIcon[];
    Picture picture[];
    int arr[];
    Point point[];
    JButton flag = new JButton();
    JButton restart = new JButton();
    JButton compare = new JButton();
    JButton back = new JButton();
    Timer timer;
    String time;
    JLabel label;
    JLabel timelable;

    int num = 0;
    int state = 0;

    public NewPing_Tu(int i){
        number = i;
        imageIcon = new ImageIcon[number-1];
        picture = new Picture[number];
        arr = new int[number-1];
        point = new Point[number-1];

        timer = new Timer(1000,TimerListener);
        time = "0";
        label = new JLabel("时间:");
        label.setBounds(((int)(Math.sqrt(number))*2 + 1)*100 - 30,((int)(Math.sqrt(number)) + 2)*50-100,50,50);
        timelable = new JLabel(time);
        timelable.setBounds(((int)(Math.sqrt(number))*2 + 1)*100 + 30,((int)(Math.sqrt(number)) + 2)*50-100,50,50);
        label.setFont(new Font("宋体",Font.PLAIN,20));
        timelable.setFont(new Font("宋体",Font.PLAIN,20));
        add(label);
        add(timelable);

        init();
        handinit();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,((int)(Math.sqrt(number))*2 + 2)*100,((int)(Math.sqrt(number)) + 2)*100);//需要重新设置
        setVisible(true);
        validate();
        timer.start();
    }

    private void handinit(){
        //需要重新设置
        for(int i = 0;i < number -1;i++){
            imageIcon[i] = ImageSplit.imageIcon[i];
        }

        for(int i = 0;i < number-1;i++){
            picture[i].setIcon(imageIcon[arr[i]]);
        }
        //需要重新设置
        double number_copy = number;
        int num = (int)Math.sqrt(number_copy);
        int restart_y = (int)(Math.sqrt(number_copy)*width + 30);
        restart.setBounds((num-1)*width/2+10,restart_y,100,50);
        restart.setText("重新开始");
        restart.addMouseListener(this);
        add(restart);

        //需要重新设置
        int back_y = (int)(Math.sqrt(number_copy)*width + 30);
        back.setBounds((num*width)+(num*width/2),back_y,100,50);
        back.setText("返回主界面");
        back.addMouseListener(this);
        add(back);

        //需要重新设置
        File file = new File(ImageSplit.originalImg);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedImage image = null;
        Image simg = null;
        try {
            image = ImageIO.read(fis);
            simg = image.getScaledInstance(num*width, num*height,
                    BufferedImage.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        flag.setIcon(new ImageIcon(simg));
        flag.setBounds(num*width+width/2,10,num*width,num*height);
        add(flag);

        invalidate();
    }

    private void init() {
        setLayout(null);
        for(int i = 0;i < number - 1;i++){
            arr[i] = i;
        }
        for(int i = 0;i < number-1;i++){
            picture[i] = new Picture();
            picture[i].setBounds((int) (10+i%(int)(Math.sqrt(number))*width), (int) (10+(i/(int)(Math.sqrt(number)))*height),width,height);
            add(picture[i]);
            picture[i].addMouseListener(this);
            picture[i].setContentAreaFilled(false);
        }
        picture[number-1] = new Picture();
        picture[number-1].setBounds(10+(number-1)%(int)(Math.sqrt(number))*width,10+((number-1)/(int)(Math.sqrt(number)))*height,width,height);
        picture[number-1].setContentAreaFilled(false);
        add(picture[number-1]);

        for(int i = 0;i < number-1;i++){
            point[i] = picture[i].getLocation();
        }

        shuffle(arr);
        while(!check(arr)){
            shuffle(arr);
        }

        invalidate();
    }

    Boolean check(int a[]){
        for(int i = 0;i < number-1;i++){
            for(int j = i + 1;j < number-1;j++){
                if(a[i] > a[j]){
                    state++;
                }
            }
        }
        if(state % 2 == 0){
            state = 0;
            return true;
        }
        else {
            state = 0;
            return false;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    void shuffle(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length; i > 1; i--) {
            swap(arr, i - 1, rnd.nextInt(i));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if((JButton)e.getSource() == restart){
            dispose();
            new NewPing_Tu(number);
        }
        else if((JButton)e.getSource() == back){
            dispose();
            new Play();
        }
        else{
            Picture picture1 = (Picture)e.getSource();
            Point pictureLocation = picture1.getLocation();
            Point flagLocation = picture[number-1].getLocation();

            int right = flagLocation.x - pictureLocation.x;
            int left = pictureLocation.x - flagLocation.x;
            int above = flagLocation.y - pictureLocation.y;
            int below = pictureLocation.y - flagLocation.y;
            if(right == width&&above == 0){
                picture1.setLocation(flagLocation);
                picture[number-1].setLocation(pictureLocation);
            }
            else if(left == width&&above == 0){
                picture1.setLocation(flagLocation);
                picture[number-1].setLocation(pictureLocation);
            }
            else if(above == height&&left == 0){
                picture1.setLocation(flagLocation);
                picture[number-1].setLocation(pictureLocation);
            }
            else if(below == height&&left == 0){
                picture1.setLocation(flagLocation);
                picture[number-1].setLocation(pictureLocation);
            }

            for(int i = 0;i < number-1;i++){
                for(int j = 0;j < number-1;j++){
                    if(picture[i].getLocation().equals(point[j])&&picture[i].getIcon().equals(imageIcon[j])){
                        num++;
                        break;
                    }
                }
            }

            if(num == number-1){
                timer.stop();
                timer = null;
                try {
                    FileReader fileReader = new FileReader(number+".txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    try {
                        String time1 = bufferedReader.readLine();
                        bufferedReader.close();
                        fileReader.close();
                        if(Integer.parseInt(time1) > Integer.parseInt(time)){
                            try {
                                FileWriter fileWriter = new FileWriter(number+".txt");
                                fileWriter.write(time);
                                fileWriter.flush();
                                fileWriter.close();
                                JOptionPane.showMessageDialog(this, "拼图成功,刷新记录，使用了"+time+"秒", "胜利", JOptionPane.PLAIN_MESSAGE);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "拼图成功,使用了"+time+"秒", "胜利", JOptionPane.PLAIN_MESSAGE);
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                dispose();
                new NewPing_Tu(number);
                num = 0;
            }
            else{
                num = 0;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    ActionListener TimerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int time_int = Integer.parseInt(time);
            time_int++;
            time = String.valueOf(time_int);
            timelable.setText(time);
        }
    };
}
