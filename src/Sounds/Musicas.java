package Sounds;


import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.io.File;
import java.io.InputStream;

public class Musicas  {
    private Player player;
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
    public void toca() {

        media = new Media(new File(audioSelecionado).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
//            InputStream inputStream = this.getClass().getResourceAsStream("Legend.mp3");
//            player = new Player(inputStream);
//            player.play();

    }

    }

