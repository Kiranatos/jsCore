package net.kiranatos.e15fonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextToBuferedImage {
    
    private String text;
    private Graphics graphics;
    private BufferedImage bufferedImage;
    
    //x, y - размеры рисунка BufferedImage
    public BufferedImage getBufferedImage(String text, int x, int y){
        this.text = text;        

        bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);        
        graphics = bufferedImage.getGraphics();
        
        //сначала рисуем белый прямоугольник:
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 200, 50); //размер прямоугольника
        
        //Теперь рисуем RED текст
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 20)); //размер шрифта
        graphics.drawString(this.text, 120, 25); //позиция
        
        //создание jpg-файла
        //ImageIO.write(bufferedImage, "jpg", new File("F:/img.jpg"));                
        
        return bufferedImage;
    }
    
    public static void main(String[] args) throws IOException {
        TextToBuferedImage ttbi = new TextToBuferedImage();
        BufferedImage b = ttbi.getBufferedImage("Строка 2", 500, 500);
        ImageIO.write(b, "png", new File("img.png")); //на одном уровне с src папкой
    }
}