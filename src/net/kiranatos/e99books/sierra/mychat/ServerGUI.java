package net.kiranatos.e99books.sierra.mychat;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ServerGUI implements Runnable {
    
    private JFrame UserMainWindow;
    public JTextArea mainChatJTextArea = new JTextArea (20,10);;
    public JTextArea mainMessageJTextArea = new JTextArea (10,10);
    public JTextField textField = new JTextField (10);
    ServerSocketDEEPER ssd;
    
    private static ServerGUI one = new ServerGUI();
    private ServerGUI() {}    
    
    public static ServerGUI singletonServer() { return one; }
    
    public void setChatText(String str) { mainMessageJTextArea.setText(str); }
    public String getChatText()         { return mainMessageJTextArea.getText(); }

    @Override
    public void run() {        
        ssd = new ServerSocketDEEPER();
        ssd.setGUI(this);
        Thread serverSocketThread = new Thread (ssd);        
        serverSocketThread.start();     
        
        UserMainWindow = new JFrame("Server Chat Window");
        UserMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        
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
                
        JButton sendMessageButton = new JButton ("Send message");
        ServerSendMessageListener serverSendMessageListener = new ServerSendMessageListener();
        sendMessageButton.addActionListener(serverSendMessageListener);
        serverSendMessageListener.setGUI(one);
        serverSendMessageListener.setServerSocketDEEPER(ssd);
        
        JButton cleanChatButton = new JButton ("clean нахер!");
        ServerCleanChatButtonListener1 serverСleanChatButtonListener = new ServerCleanChatButtonListener1();        
        cleanChatButton.addActionListener(serverСleanChatButtonListener);
        serverСleanChatButtonListener.setGUI(this);
        
        JButton sendButton = new JButton ("отправить файл");
        ServerSendFileListener serverSendButtonListener = new ServerSendFileListener();
        sendButton.addActionListener(serverSendButtonListener);
        serverSendButtonListener.setGUI(this);
        serverSendButtonListener.setServerSocketDEEPER(ssd);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load card set");
        //loadMenuItem.addActionListener(new QuizCardPlayer.OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        
        
        mainPanel.add(qScroller);
        mainPanel.add(mainMessageJTextArea);
        mainPanel.add(sendMessageButton);
        mainPanel.add(cleanChatButton);       
        mainPanel.add(textField);
        mainPanel.add(sendButton);        
        
        UserMainWindow.setJMenuBar(menuBar);
        UserMainWindow.getContentPane().add(BorderLayout.CENTER, mainPanel);
        UserMainWindow.setSize(450,550);
        UserMainWindow.setVisible(true);
    }
    
}
