package com.codenameone.testwav;

import static com.codename1.ui.CN.*;

import com.codename1.components.ToastBar;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.system.Lifecycle;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.io.*;
import com.codename1.ui.plaf.*;
import com.codename1.ui.util.Resources;
import java.io.InputStream;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose
 * of building native mobile applications using Java.
 */
public class TestWav extends Lifecycle {
    @Override
    public void runApp() {
        Form hi = new Form("Play Wav Test", BoxLayout.y());
        Button play_wav = new Button("Play Wav");
        play_wav.addActionListener(e -> playWav());
        Button play_mp3 = new Button("Play MP3");
        play_mp3.addActionListener(e -> playMp3());
        hi.addAll(play_wav, play_mp3);
        hi.show();
    }

    private void playWav() {
        new RegularSound("playwav.wav");
    }

    private void playMp3() {
        new RegularSound("mp3test.mp3");
    }
}

class RegularSound {
    private Media m;
    public RegularSound(String fileName) {
        new Thread(() -> {
            try {
                InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
                m = MediaManager.createMedia(is, "audio/wav");
            } catch (Exception e) {
                Log.e(e);
                ToastBar.showErrorMessage(e.getMessage());
            }
        }).start();
    }

    public void play() {
        m.setTime(0);
        m.play();
    }
}