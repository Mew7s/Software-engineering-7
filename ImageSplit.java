import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageSplit{
    static public String originalImg;
    private int rowAndcol;
    static public ImageIcon[] imageIcon;
    ImageSplit(String pictureName,int i){
        originalImg = pictureName;
        rowAndcol = i;
        imageSplit();
    }
    public void imageSplit(){
        // 读入大图
        File file = new File(originalImg);
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
            simg = image.getScaledInstance(rowAndcol*100, rowAndcol*100,
                    BufferedImage.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 分割成 rowAndcol 个小图
        int rows = rowAndcol;
        int cols = rowAndcol;
        int chunks = rows * cols;

        // 计算每个小图的宽度和高度
        int chunkWidth = 100;
        int chunkHeight = 100;

        int count = 0;
        imageIcon = new ImageIcon[chunks];
        BufferedImage imgs[] = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //设置小图的大小和类型
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight,BufferedImage.TYPE_INT_ARGB);
                //写入图像内容
                Graphics2D gr = imgs[count].createGraphics();
                gr.drawImage(simg, 0, 0,
                        chunkWidth, chunkHeight,
                        chunkWidth * y, chunkHeight * x,
                        chunkWidth * y + chunkWidth,
                        chunkHeight * x + chunkHeight, null);
                gr.dispose();
                imageIcon[count] = new ImageIcon(imgs[count]);
                count++;
            }
        }
        new NewPing_Tu(rowAndcol*rowAndcol);
    }
}
