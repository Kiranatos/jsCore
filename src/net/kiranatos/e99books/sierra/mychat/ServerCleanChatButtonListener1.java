package net.kiranatos.e99books.sierra.mychat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerCleanChatButtonListener1 implements ActionListener {
    
    private ServerGUI gui;
    private ServerSocketDEEPER usd;

    @Override
    public void actionPerformed(ActionEvent e) {                
        gui.mainChatJTextArea.setText("");                
    }
    
    public void setGUI(ServerGUI gui)
    {
        this.gui = gui;
    }
    public void setServerSocketDEEPER(ServerSocketDEEPER usd)
    {
        this.usd = usd;
    }
    
}


