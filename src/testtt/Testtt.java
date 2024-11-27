package testtt;

import Sounds.Musicas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Testtt {
    private JPanel panel1;
    private JButton backButton;
    private JButton nextButton;
    private JButton save;
    private JButton playButton;
    private JPanel Jpanelimg;
    private JButton pauseButton;
    private JPanel panel2;
    private JButton addMusic;
    private JList list1;
    private JButton listButton;
    private JProgressBar progressBar1;
    private JLabel JlabelEnd;
    private JLabel JLabelBegin;
    private JTextField textField1;
    private JButton novaPlaylist;
    private JButton escolherMusica;
    private JTable table1;
    private JList<String> playlistList;
    private DefaultListModel<String> playlistModel;

    private Musicas mp3;
    public Testtt() {
        JFrame frame = new JFrame("Music Player");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        TextArea area = new TextArea();
        area.setBounds(75, 30, 150, 150);
        frame.add(area);

        mp3 = new Musicas(playlistModel);

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
        JProgressBar progressBar1 = new JProgressBar();
        progressBar1.setBounds(30, 280, 240, 10);
        progressBar1.setValue(30); // Valor de exemplo
        frame.add(progressBar1);

        JLabel JLabelBegin = new JLabel("0:00");
        JLabelBegin.setBounds(5, 280, 30,10 );
        frame.add(JLabelBegin);

        JLabel JLabelEnd = new JLabel("0:00");
        JLabelEnd.setBounds(305, 280, 50,10 );
        frame.add(JLabelEnd);
        // Botões
        JButton prevButton = new JButton("⏮");
        prevButton.setBounds(50, 320, 50, 50);
        frame.add(prevButton);

        JButton escolherMusica = new JButton();
        escolherMusica.setText("Adicionar Música");
        escolherMusica.setBounds(145, 450, 150, 50);
        frame.add(escolherMusica);

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
        addMusic.setBounds(235, 25, 80, 40);
        frame.add(addMusic);

        JButton listButton = new JButton("Playlist");
        listButton.setBounds(5, 400, 80, 30);
        frame.add(listButton);

        JTextField textField1 = new JTextField();
        textField1.setBounds(100, 400, 240, 30);
        frame.add(textField1);

        JButton novaPlaylistButton = new JButton();
        novaPlaylistButton.setBounds(360, 400, 40, 30);
        frame.add(novaPlaylistButton);

        JButton save = new JButton();
        save.setText("s");
        save.setBounds(360, 450, 60, 30);
        frame.add(save);

        JButton carregarPlaylistButton = new JButton("Carregar Playlist");
        carregarPlaylistButton.setBounds(100, 500, 150, 30);
        frame.add(carregarPlaylistButton);

        frame.setVisible(true);

        addMusic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mp3.parar();
                playButton.setVisible(true);
                mp3.escolherArquivo();
                try {
                    mp3.iniciaPrograma(progressBar1);
                    JLabelEnd.setText(String.valueOf(mp3.duracao()));
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }


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

        novaPlaylistButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField1.setText(mp3.criaPlaylist());
            }
        });

        escolherMusica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp3.procuraPlaylist(area);
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp3.salvarPlaylist(textField1);
            }
        });

        carregarPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int escolha = fileChooser.showOpenDialog(null);

                if (escolha == JFileChooser.APPROVE_OPTION) {
                    String caminhoArquivo = fileChooser.getSelectedFile().getPath();
                    mp3.carregarEReproduzirPlaylist(caminhoArquivo);
                    try {
                        mp3.iniciaPrograma(progressBar1);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

    }

}