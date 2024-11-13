//package Sounds;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class VisualReprodutor {
//    private JButton playButton;
//    private JPanel panel1;
//
//    public VisualReprodutor() {
//        JFrame frame = new JFrame("Reprodutor Visual");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 400);
//        panel1 = new JPanel();
//
//        panel1.add(playButton);
//        frame.add(panel1);
//        frame.setVisible(true);
//        panel1.setBackground(new Color(86, 98, 110));
//        playButton.setFocusPainted(false);
//
//
//
//
//        playButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Musicas mp3 = new Musicas();
//                mp3.toca();
//            }
//        });
//    }
//}
package testtt;

import Sounds.Musicas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Testtt {
    private JPanel panel1;
    private JSlider slider1;
    private JButton backButton;
    private JButton nextButton;
    private JButton playButton;
    private JPanel Jpanelimg;

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

        // Configuração e exibição do frame
        frame.setVisible(true);

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Musicas mp3 = new Musicas();
                mp3.toca();
            }
        });
    }
}

