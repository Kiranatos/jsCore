package net.kiranatos.e99books.sierra.rmibrowser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JButton;
import javax.swing.JPanel;

class MiniMusicService implements Service {
    
    MyDrawPanel myPanel;    

    @Override
    public JPanel getGuiPanel() {
        JPanel mainPanel = new JPanel();
        myPanel = new MyDrawPanel();
        JButton playItButton = new JButton("Play It");
        playItButton.addActionListener(new PlayItListener());
        mainPanel.add(myPanel);
        mainPanel.add(playItButton);
        return mainPanel;
    }
    
    public class PlayItListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Sequencer sequencer = MidiSystem.getSequencer();
                sequencer.open();
                sequencer.addControllerEventListener(myPanel, new int[] {127});
                Sequence seq = new Sequence(Sequence.PPQ, 4);
                Track track = seq.createTrack();
                for (int i = 0; i < 100; i+=4) {
                    int rNum = (int) ((Math.random()*50)+1);
                    if (rNum < 38) { // Теперь делаем это, только если num < 38 (75 % времени)
                        track.add(makeEvent(144, 1, rNum, 100, i));
                        track.add(makeEvent(176, 1, 127, 0, i));
                        track.add(makeEvent(128, 1, rNum, 100, i+2));
                    }
                } //Конец цикла
                
                sequencer.setSequence(seq);
                sequencer.start();
                sequencer.setTempoInBPM(220);
            } catch (MidiUnavailableException | InvalidMidiDataException ex) {
                Logger.getLogger(MiniMusicService.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        } // Закрываем actionperformed        
    } // Закрываем вложенный класс
    
    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;        
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(MiniMusicService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return event;
    }
    
    class MyDrawPanel extends JPanel implements ControllerEventListener {
        //Рисуем только тогда, когда наступит определённое событие
        boolean msg = false;
        
        public void controlChange (ShortMessage event) {
            msg = true;
            repaint();
        }
        
        public Dimension getPreferedSize() {
            return new Dimension(300, 300);
        }
        
        public void paintComponent(Graphics g) {
            if (msg) {
                Graphics2D g2 = (Graphics2D) g;
                int r = (int)(Math.random() * 250);
                int gr = (int)(Math.random() * 250);
                int b = (int)(Math.random() * 250);
                
                g.setColor(new Color(r, gr, b));
                
                int ht = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);
                
                int x = (int) ((Math.random() * 40) + 10);
                int y = (int) ((Math.random() * 40) + 10);
                
                g.fillRect(x, y, ht, width);
                msg = false;
            } // Закрываем if
        } // Закрываем метод
    } // Закрываем вложенный класс
} // Закрываем класс
