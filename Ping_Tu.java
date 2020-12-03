import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Ping_Tu{
    //设置总块数
    int number;
    //每一个拼图图块的大小

    public Ping_Tu(int i) {
        number = i;
        new ImageSplit("1.jpg",(int)(Math.sqrt(number)));
    }
}
