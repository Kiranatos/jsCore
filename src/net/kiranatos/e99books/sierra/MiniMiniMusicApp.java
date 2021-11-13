package net.kiranatos.e99books.sierra;

import javax.sound.midi.*;

public class MiniMiniMusicApp { 
    public static void main(String[] args) {
        System.out.println("Begin...");
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.play();        
    }
    
    public void play() {
        try {
            // Получить синтезатор и открыть его
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            
            // Создать новую последовательность
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            // Получить новый трек из последовательности
            // трек находится в последовательности, а MIDI-данные в треке
            Track track = seq.createTrack();
            
            
        // Помещаем MIDI-события в трек
            // Создаем сообщение
            ShortMessage a = new ShortMessage();
            // Помещаем инструкцию в сообщение
                // начать проигрывать ноту № 60            
            a.setMessage(144, 1, 44, 100);
            // Используя сообщение, создаем новое событие
                // MidiEvent дополняет инструкцию инфо о моменте времени:
                    // сообщение а сработает на 1-м такте (бит 1)            
            MidiEvent noteOn = new MidiEvent(a,1);
            // Добавляем MIDI-событие в трек
                // Трек хранит все объекты MidiEvent.
                // они размещаются по времени срабатывания
            track.add(noteOn);
            
            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, 44, 100);
            /*
                128 - тип сообщения 
                    {
                    144 - начало проигрывния ноты, 
                    128 - конец проигрывния ноты,
                    176 - ControllerEvent
                    192 - изменение ноты
                    }
                Следуюющие три зависят от типа сообщения:
                1 - канал (0-127)
                44 - нота для проигрывания (0-127 от низких к высоким)
                100 - скорость и сила нажатия клавиши ( 0-100 % )
            */
            MidiEvent noteOff = new MidiEvent(b, 10);
            track.add(noteOff);
            
            // Передать последовательность в синтезатор
            player.setSequence(seq);
            // ???
            player.setTempoInBPM(220);
            // Запустить синтезатор
            player.start();
            
            try { Thread.sleep(3000); }
            catch (Exception e) {}
            
            player.stop();
            player.close();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}
