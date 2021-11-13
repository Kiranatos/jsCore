package net.kiranatos.e15fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.kiranatos.OzoHelper;

public class FontsView {
    
    private static final String PATH_TO_TEDDYBEAR_FONT = "src/net/kiranatos/e06fonts/res/Teddy_Bear.ttf";
    private static final String PATH_TO_MCGOTHIC_FONT = "src/net/kiranatos/e06fonts/res/msgothic.ttc";
    
    private static JLabel lab_1 = new JLabel("Пример № 1 おやすみなさい");
    private static JLabel lab_2 = new JLabel("Пример № 2 おやすみなさい");
    private static JLabel lab_3 = new JLabel("Пример № 3 おやすみなさい");        
    private static JLabel lab_4 = new JLabel("Пример № 4 おやすみなさい");
    
    public static void main(String[] args) throws FontFormatException, IOException {
        
        final JFrame jFrame = new JFrame("Пример отображения шрифтов");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(0, 1));
                        
        OzoHelper.printMe(null, 
                "Using standart Fonts: в первом случае логический шрифт, а во втором физический.",
                "- Physical fonts are the actual font libraries containing glyph data and",
                "tables to map from character sequences to glyph sequences, using a font technology such as TrueType or PostScript Type 1",
                "- Logical fonts are the five font families defined by the Java platform,",
                " which must be supported by any Java runtime environment:",
                "Serif, SansSerif, Monospaced, Dialog, and DialogInput", "\n");        
        lab_1.setFont(new Font("Serif", Font.PLAIN, 24));
        lab_2.setFont(new Font("MS Mincho", Font.PLAIN, 24));
        
        //теперь пробуем загрузить шрифт из внешнего файла        
        Font f_ye = Font.createFont(Font.TRUETYPE_FONT, new File(PATH_TO_TEDDYBEAR_FONT));
        lab_3.setFont(f_ye.deriveFont(Font.PLAIN, 24.0f));
        // и еще один шрифт из внешнего файла
        Font f_inv = Font.createFont(Font.TRUETYPE_FONT, new File(PATH_TO_MCGOTHIC_FONT));
        lab_4.setFont(f_inv.deriveFont(Font.PLAIN, 24.0f));
        
        // получим и выведем в виде JComboBox список всех шрифтов
        panel.add(new JLabel("Cписок всех шрифтов (getAllFonts)"));
        Font [] allFonts = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        panel.add(new JComboBox(allFonts));
        panel.add(new JLabel("Count fonts = "+ allFonts.length));
        // список названий всех шрифтов доступных для текущей локали
        panel.add(new JLabel("Шрифты доступные для текущей локали (getAvailableFontFamilyNames)"));
        String [] locFontNames = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        panel.add(new JComboBox(locFontNames));
        panel.add(new JLabel("Count fonts = "+ locFontNames.length));
        
        // Add all Labels
        panel.add(lab_1);
        panel.add(lab_2);
        panel.add(lab_3);
        panel.add(lab_4);        
        
        jFrame.setContentPane(panel);
        jFrame.pack();                
        
        SwingUtilities.invokeLater( new  Runnable(){ 
            public void run(){
            jFrame.setVisible(true);
            }
        });
        // Можно просто и 
        // jFrame.setVisible(true);      
        // SwingUtilities.invokeLater - вроде как отложенный вызов
    }
}
