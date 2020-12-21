import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ImageSplitTest {

    @ParameterizedTest
    @ValueSource(ints = {3,6,9})
    //自定义图片的分割
    public void imageSplitTest(int i) throws IOException {
        Image image;
        new ImageSplit("C:\\Users\\84181\\Desktop\\test" +
                "\\imageSplit\\图片\\" +i+".jpg",i);
        for(int j=0;j<ImageSplit.imageIcon.length;j++){
            image = ImageSplit.imageIcon[j].getImage();
            ImageIO.write((BufferedImage) image, "jpg",
                    new File("C:\\Users\\84181\\Desktop\\test" +
                            "\\imageSplit\\生成\\"+i+"\\"+"_"+j+".jpg"));
        }
    }

    @RepeatedTest(50)
    //(4*4时)有序随机数算法是否成功调用
    public void  newPing_tuTest(){
        int a[] ;
        int b=0;
        new ImageSplit("C:\\Users\\84181\\Desktop\\test\\newPing_tu\\1.jpg",4);
        NewPing_Tu ping_tu = new NewPing_Tu(16);
        a = ping_tu.arr;
        for(int i = 0;i < 15;i++){
            for(int j = i + 1;j < 15;j++){
                if(a[i] > a[j]){
                    b++;
                }
            }
        }
        assertEquals(0,b%2);
    }

    @ParameterizedTest
    @ValueSource(ints = {3,6,9})
    //默认图片拼图
    public void morenPingTuTest(int i) throws IOException {
        new Ping_Tu(i*i);
        Image image;
        for(int j=0;j<ImageSplit.imageIcon.length;j++){
            image = ImageSplit.imageIcon[j].getImage();
            ImageIO.write((RenderedImage) image, "jpg",
                    new File("C:\\Users\\84181\\Desktop\\test" +
                            "\\默认ping_tu\\"+i+"\\"+"_"+j+".jpg"));
        }
    }
}