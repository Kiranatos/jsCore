package net.kiranatos.e99books.sierra.p486;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

/*
page 486
 */

public class QuizCardPlayer {
    
    private JTextArea display;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private QuizCard currentCard;
    private int currentCardIndex;
    private JFrame frame;
    private JButton nextButton;
    private boolean isShowAnswer;
    
    public static void main (String[] args)
    {
        QuizCardPlayer reader = new QuizCardPlayer();
        reader.go();
    }
    
    public void go() {
        //Формируем gui
        frame = new JFrame("Quiz Card Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font ("sanserif", Font.BOLD, 24);
        
        display = new JTextArea (10,20);
        display.setFont(bigFont);
        
        display.setLineWrap(true);
        display.setEditable(false);
        
        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        nextButton = new JButton ("Show Question");
        mainPanel.add(qScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load card set");
        loadMenuItem.addActionListener(new OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(640,500);
        frame.setVisible(true);
    } //закрыть метод go
    
    public class NextCardListener implements ActionListener {
        public void actionPerformed (ActionEvent ev) {
            if (isShowAnswer) {
                // показывает ответ, так как вопрос уже был увиден
                display.setText(currentCard.getAnswer());
                nextButton.setText("NextCard");
                isShowAnswer = false;
            } else {
                //показываем следующий вопрос
                if (currentCardIndex < cardList.size()) {
                    showNextCard();
                } else {
                    // Больше карточек нет !
                    display.setText("That was last card");
                    nextButton.setEnabled(false);
                }
            }
        }
    }
    
    public class OpenMenuListener implements ActionListener {
        public void actionPerformed (ActionEvent ev) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }
    
    private void loadFile (File file) {
        cardList = new ArrayList<QuizCard>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null ) {                
                makeCard(line);                
            }
            reader.close();
        }
        catch (Exception ex) {
            System.out.println("couldn't read the card file");
            ex.printStackTrace();
        }
        
        // Пришло время показать первую карточку
        showNextCard();
    }
    
    private void makeCard(String lineToParse) {
        String[] result = lineToParse.split("/");
        QuizCard card = new QuizCard(result[0],result[1]);
        cardList.add(card);
        System.out.println("made a card");
    }
    
    private void showNextCard() {
        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer=true;        
    }
    
} //Закрываем класс
