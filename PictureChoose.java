import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class PictureChoose extends JFrame {
    public String pictureName;
    public PictureChoose(){
        JFileChooser chooser = new JFileChooser();
        int flag = chooser.showOpenDialog(this);
        FileFilter fileFilter = new javax.swing.filechooser.FileFilter() {
            public boolean accept(File file) { //可接受的文件类型
                String  name = file.getName().toLowerCase();
                return  name.endsWith(".jpg")||
                        name.endsWith(".png")||
                        name.endsWith(".gif");
            }
            public String getDescription() { //文件描述
                return "jpg,png,gif 图片文件";
            }
        };

        //若选择了文件，则打印选择了什么文件
        chooser.setFileFilter(fileFilter);
        if (flag == JFileChooser.APPROVE_OPTION) {
            try {
                System.out.println("用户选择了文件：" + chooser.getSelectedFile().getPath());
                ImageSplit.originalImg = chooser.getSelectedFile().getPath();
                new Play1();
            } catch (Exception e) {
                e.printStackTrace();
                dispose();
                new PictureChoose();
            }
        }
    }
}
