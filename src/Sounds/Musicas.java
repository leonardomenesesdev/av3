package Sounds;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Musicas  {
    private MediaPlayer mediaPlayer;
    private Media media;
    private String audioSelecionado;
    private DefaultListModel<String> playlistModel;
    private ArrayList playlist = new ArrayList<>();

    public Musicas(DefaultListModel<String> playlistModel) {
        this.playlistModel = playlistModel;
    }

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
    public void iniciaPrograma(JProgressBar progressBar) throws InterruptedException {
        media = new Media(new File(audioSelecionado).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        Thread.sleep(3000);
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                double progress = newValue.toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
                progressBar.setValue((int) (progress * 100));
                System.out.println("mudança");
            }
        });
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

    public void salvaMusica(){
        //playlist.add("C:\\Users\\DELL\\Downloads\\Legend.mp3");
        playlist.add(audioSelecionado);
    //    playlistModel.addElement(new File(audioSelecionado).getName());
    }
    public void exibeLista(){
        System.out.println(playlist.toString());
    }

//Travando no 0.0
//    public void currentTime(JLabel label) {
//        label.setText(String.valueOf(mediaPlayer.getCurrentTime().toMinutes()));
//    }


    public Object duracao() {
       return mediaPlayer.getMedia().getDuration().toMinutes();
    }


}