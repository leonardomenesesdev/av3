package Sounds;


import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


import javax.swing.*;
import java.io.File;

public class Musicas  {
    private MediaPlayer mediaPlayer;
    private Media media;
    private String audioSelecionado;
    public void escolherArquivo(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int escolha = fileChooser.showOpenDialog(null);
        if(escolha == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada",
                    "Reprodutor Av3",
                    JOptionPane.ERROR_MESSAGE);
        } else{
            JOptionPane.showMessageDialog(null,
                    "Arquivo: "+fileChooser.getSelectedFile().toPath(),
                    "Reprodutor Av3",
                    JOptionPane.INFORMATION_MESSAGE);
            audioSelecionado = fileChooser.getSelectedFile().toPath().toString(); //Talvez n precise do toString
        }
    }
    public void iniciaPrograma(){
        media = new Media(new File(audioSelecionado).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public String getAudioSelecionado() {
        return audioSelecionado;
    }
    public void toca() {
        mediaPlayer.play();
    }

    public void pausa() {
        mediaPlayer.pause();
    }

    public void parar() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
    }

//Pq está retornando NaN?
//    public Double duracao(){
//        return mediaPlayer.getStopTime().toSeconds();
//    }
}