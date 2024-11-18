package testtt;

import Sounds.Musicas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Testtt {
    private JPanel panel1;
    private JSlider slider1;
    private JButton backButton;
    private JButton nextButton;
    private JButton playButton;
    private JPanel Jpanelimg;
    private JButton pauseButton;
    private JPanel panel2;
    private JButton addMusic;
    private Musicas mp3 = new Musicas();
    public Testtt() {        // Criação do JFrame
        JFrame frame = new JFrame("Music Player");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Painel para imagem de perfil
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK); // Placeholder para a imagem (apenas um círculo).
                g.fillOval(0, 0, getWidth(), getHeight());
            }
        };
        imagePanel.setBounds(75, 30, 150, 150);
        frame.add(imagePanel);


        // Título da música
        JLabel songTitleLabel = new JLabel("Song Title", SwingConstants.CENTER);
        songTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        songTitleLabel.setBounds(50, 200, 200, 30);
        frame.add(songTitleLabel);

        // Nome do artista
        JLabel artistNameLabel = new JLabel("Artist Name", SwingConstants.CENTER);
        artistNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        artistNameLabel.setBounds(50, 230, 200, 30);
        frame.add(artistNameLabel);

        // Barra de progresso (simples)
        JSlider progressBar = new JSlider();
        progressBar.setBounds(30, 280, 240, 10);
        progressBar.setValue(30); // Valor de exemplo
        frame.add(progressBar);

        // Botões
        JButton prevButton = new JButton("⏮");
        prevButton.setBounds(50, 320, 50, 50);
        frame.add(prevButton);

        JButton playButton = new JButton("▶");
        playButton.setBounds(125, 320, 50, 50);
        frame.add(playButton);

        JButton nextButton = new JButton("⏭");
        nextButton.setBounds(200, 320, 50, 50);
        frame.add(nextButton);

        JButton pauseButton = new JButton("||");
        pauseButton.setBounds(125, 320, 50, 50);
        frame.add(pauseButton);


        JButton addMusic = new JButton("☰");
        addMusic.setBounds(225, 25, 40, 40);
        frame.add(addMusic);

        // Configuração e exibição do frame
        frame.setVisible(true);


        addMusic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mp3.parar();
                playButton.setVisible(true);
                mp3.escolherArquivo();
                mp3.iniciaPrograma();
//                double contador=0;
//                System.out.println(mp3.duracao());
//                while ( contador<mp3.duracao()){
//                slider1.setValue((int)contador);
//                contador++;
//                    System.out.println(contador);
//                }


                String caminhoArquivo = mp3.getAudioSelecionado();
                if (caminhoArquivo != null) {
                    if (caminhoArquivo.equals("C:\\Users\\lucio\\Downloads\\av3-main\\av3-main\\src\\Sounds\\Legend.mp3")) {
                        songTitleLabel.setText("Legend");
                        artistNameLabel.setText("LeozinDograu");
                    } else {

                        songTitleLabel.setText(new File(caminhoArquivo).getName());
                    }
                }
            }
        });


        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playButton.setVisible(false);
                pauseButton.setVisible(true);
                mp3.toca();
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playButton.setVisible(true);
                pauseButton.setVisible(false);
                mp3.pausa();
            }
        });

    }
}