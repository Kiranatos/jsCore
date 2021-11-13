package net.kiranatos.e99books.sierra.mychat;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class UserGUI implements Runnable {
    
    private JFrame UserMainWindow;
    public JTextArea mainChatJTextArea = new JTextArea (20,10);;
    public JTextArea mainMessageJTextArea = new JTextArea (10,10);;

    @Override
    public void run() {
        
        UserSocketDEEPER usd = new UserSocketDEEPER();
        usd.setGUI(this);
        Thread userSocketThread = new Thread (usd);
        userSocketThread.start();     
        
        UserMainWindow = new JFrame("User Chat Window");
        UserMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        BoxLayout styleL = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);        
        mainPanel.setLayout(styleL);
        JLabel chatLabel = new JLabel("Общий чат");
        JLabel messageLabel = new JLabel("Напиши своё сообщение:");
        
        
        Font fontOfChat = new Font ("sanserif", Font.BOLD, 14);                
        mainChatJTextArea.setFont(fontOfChat);        
        mainChatJTextArea.setLineWrap(true);
        mainChatJTextArea.setEditable(false);        
        JScrollPane qScroller = new JScrollPane(mainChatJTextArea);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        
        Font fontOfMessage = new Font ("sanserif", Font.BOLD, 14);        
        mainMessageJTextArea.setFont(fontOfMessage);        
        mainMessageJTextArea.setLineWrap(false);
        mainMessageJTextArea.setEditable(true);        
                
        JButton sendMessageButton = new JButton ("Отправить сообщение");
        UserSendMessageListener userSendMessageListener = new UserSendMessageListener();
        sendMessageButton.addActionListener(userSendMessageListener);
        userSendMessageListener.setGUI(this);
        userSendMessageListener.setUserSocketDEEPER(usd);
        
        JButton cleanChatButton = new JButton ("Очистить окно чата");
        UserCleanChatButtonListener userСleanChatButtonListener = new UserCleanChatButtonListener();        
        cleanChatButton.addActionListener(userСleanChatButtonListener);
        userСleanChatButtonListener.setGUI(this);
        
        JPanel panelButtons = new JPanel ();
        panelButtons.add(sendMessageButton);
        panelButtons.add(cleanChatButton);
        
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Пункт меню, который ни хрена не делает =)");
        //loadMenuItem.addActionListener(new QuizCardPlayer.OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        
        
        mainPanel.add(chatLabel);
        mainPanel.add(qScroller);
        mainPanel.add(messageLabel);
        mainPanel.add(mainMessageJTextArea);        
        mainPanel.add(panelButtons);        
        
        UserMainWindow.setJMenuBar(menuBar);
        UserMainWindow.getContentPane().add(BorderLayout.CENTER, mainPanel);
        UserMainWindow.setSize(450,550);
        UserMainWindow.setVisible(true);
    }
    
}
