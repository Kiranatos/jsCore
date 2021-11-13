package net.kiranatos.e99books.sierra.mychat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerSendMessageListener implements ActionListener {
    
    private ServerGUI gui;
    ServerSocketDEEPER ssd;

    @Override
    public void actionPerformed(ActionEvent e) {        
        String m = gui.mainMessageJTextArea.getText();        
        ssd.newServerMessage(m);
        gui.mainChatJTextArea.append("\nKiranatos: " + m);
        gui.mainMessageJTextArea.setText("");
    }
    
    public void setGUI(ServerGUI gui)
    {
        this.gui = gui;
    }
    public void setServerSocketDEEPER(ServerSocketDEEPER ssd)
    {
        this.ssd = ssd;
    }
    
}
