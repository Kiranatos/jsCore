package net.kiranatos.e99books.sierra.rmibrowser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

class DiceService  implements Service {    
    JLabel label;
    JComboBox numOfDice;
        
    @Override
    public JPanel getGuiPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Roll them! ");
        String[] choices = {"1","2","3","4","5"};
        numOfDice = new JComboBox(choices);
        label = new JLabel("dice values here");
        button.addActionListener(new RollEmListener());
        panel.add(numOfDice);
        panel.add(button);
        panel.add(label);
        return panel;        
    }
    
    public class RollEmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Бросаем кости
            String diceOutput = "";
            String selection = (String) numOfDice.getSelectedItem();
            int numOfDiceToRoll = Integer.parseInt(selection);
            for (int i = 0; i < numOfDiceToRoll; i++) {
                int r = (int)((Math.random()*6)+1);
                diceOutput += (" " + r);
            }
            label.setText(diceOutput);
        }        
    }    
}
