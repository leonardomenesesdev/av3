package Sounds;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Musicas  {
    private MediaPlayer mediaPlayer;
    private Media media;
    private String audioSelecionado;
    private DefaultListModel<String> playlistModel;
    private ArrayList<String> playlist = new ArrayList<>();
    private boolean terminou = false;
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

    public void procuraPlaylist(TextArea textArea){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int escolha = fileChooser.showOpenDialog(null);
        if(escolha == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null,
                    "Operação cancelada");
        } else{
            FileInputStream entrada = null;
            var arquivo = fileChooser.getSelectedFile().toPath().toString();
            playlist.add(fileChooser.getSelectedFile().toPath().toString());
            textArea.append(new File(arquivo).getName());
        }
    }

    public void salvarPlaylist(JTextField textField){
        File arq = new File(textField.getText()+".txt");
        try {
            FileWriter escritor = new FileWriter(arq, true);
            for(String musica:playlist){
                escritor.write(musica+"\n");
            }
            escritor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String criaPlaylist(){
        String playlist = JOptionPane.showInputDialog(null, "Digite o nome do playlist");
        return playlist;
    }

    public Object duracao() {
       return mediaPlayer.getMedia().getDuration().toMinutes();
    }










    public List carregaPlaylist(String caminhoArquivo){
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))){
            List<String> linhas = new ArrayList<>();
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
            return linhas;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar ou reproduzir playlist: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }


//    public synchronized void reproduzirPlaylist(List playlist) {
//        for(int i = 0; i<playlist.size(); i++){
//            media = new Media(new File(String.valueOf(playlist.get(i))).toURI().toString());
//            mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.play();
//
//
//            mediaPlayer.setOnEndOfMedia(new Runnable() {
//                @Override
//                public void run() {
//                    mediaPlayer.stop();
//                    mediaPlayer.dispose();
//
//                }
//            });
//        }
//    }

    public synchronized void reproduzirPlaylist(final List<String> playlist) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < playlist.size(); i++) {
                    media = new Media(new File(playlist.get(i)).toURI().toString());
                    mediaPlayer = new MediaPlayer(media);

                    final CountDownLatch latch = new CountDownLatch(1);

                    mediaPlayer.setOnEndOfMedia(new Runnable() {
                        @Override
                        public void run() {
                            mediaPlayer.stop();
                            mediaPlayer.dispose();
                            latch.countDown();
                        }
                    });
                    mediaPlayer.play();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                return null;
            }
        };

        new Thread(task).start();
    }


}