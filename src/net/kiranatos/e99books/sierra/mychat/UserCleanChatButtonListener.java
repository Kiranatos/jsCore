package net.kiranatos.e99books.sierra.mychat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCleanChatButtonListener implements ActionListener {
    
    private UserGUI gui;
    private UserSocketDEEPER usd;

    @Override
    public void actionPerformed(ActionEvent e) {                
        gui.mainChatJTextArea.setText("");                
    }
    
    public void setGUI(UserGUI gui)
    {
        this.gui = gui;
    }
    public void setUserSocketDEEPER(UserSocketDEEPER usd)
    {
        this.usd = usd;
    }
    
}


