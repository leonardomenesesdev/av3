package Sounds;


import javafx.scene.media.Media;

import javafx.stage.FileChooser;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.InputStream;

public class Musicas  {
    private Player player;
    public void toca() {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("Legend.mp3");
            player = new Player(inputStream);
            player.play();

        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }

    }

