package project.l02gr06;
import project.l02gr06.gui.LanternaGUI;
import project.l02gr06.model.menu.MainMenu;
import project.l02gr06.saveManager.SaveFileManager;
import project.l02gr06.saveManager.SaveManager;
import project.l02gr06.soundManager.SoundManager;
import project.l02gr06.state.MainMenuState;
import project.l02gr06.state.State;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private State state;
    private SoundManager soundManager;
    public Game() throws FontFormatException, IOException, URISyntaxException, LineUnavailableException {
        this.gui = new LanternaGUI(30, 20);
        this.state = new MainMenuState(new MainMenu());
        this.soundManager = new SoundManager("backgroundAudio.wav");
        this.soundManager.playBackgroundTrack();
        this.soundManager.setVolume(0.82f);
    }


    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException,LineUnavailableException {
        new Game().start();
    }

    public void setState(State state) {
        this.state = state;
    }

    private void start() throws IOException {
        int FPS = 60;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();
            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
        this.soundManager.stopBackgroundTrack();
        gui.close();
    }
    public SoundManager getSoundManager(){
        return soundManager;
    }
}
