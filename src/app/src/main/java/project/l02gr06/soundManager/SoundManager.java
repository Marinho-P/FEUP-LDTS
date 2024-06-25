package project.l02gr06.soundManager;

import project.l02gr06.soundManager.soundEffects.*;

import javax.sound.sampled.*;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class SoundManager {
        private static SoundManager instance;
        private Clip backgroundTrack;
        private String audioFileName;
        private BackOptionSound backOptionSound;
        private GemCollectSound gemCollectSound;
        private InverterSound inverterSound;
        private KeyCollectSound keyCollectSound;
        private NavigateOptionSound navigateOptionSound;
        private PlayerWinSound playerWinSound;
        private PlayerDeathSound playerDeathSound;
        private PlayerMoveSound playerMoveSound;
        private PushBlockSound pushBlockSound;
        private SelectOptionSound selectOptionSound;
        private UnlockLockSound unlockLockSound;
        private YouWinSound youWinSound;
        private SnakeDeathSound snakeDeathSound;
        private PushingPSound pushingPSound;
        private MarioFallSound marioFallSound;
        private GruntSound gruntSound;
        private FnafWinSound fnafWinSound;
        public SoundManager(String audioFileName) {
            this.audioFileName = audioFileName;
            loadBackgroundTrack();
            this.playerWinSound = new PlayerWinSound(-15f);
            this.playerDeathSound = new PlayerDeathSound(-15f);
            this.playerMoveSound = new PlayerMoveSound(-4f);
            this.gemCollectSound = new GemCollectSound(-12f);
            this.keyCollectSound = new KeyCollectSound(-7f);
            this.unlockLockSound = new UnlockLockSound(-1f);
            this.inverterSound = new InverterSound(-24f);
            this.pushBlockSound = new PushBlockSound(-5f);
            this.navigateOptionSound = new NavigateOptionSound(-12f);
            this.selectOptionSound= new SelectOptionSound(-12f);
            this.backOptionSound = new BackOptionSound(-12f);
            this.fnafWinSound = new FnafWinSound(-15f);
            this.youWinSound = new YouWinSound(-15f);
            this.snakeDeathSound = new SnakeDeathSound(-15f);
            this.gruntSound = new GruntSound(-5f);
            this.marioFallSound = new MarioFallSound(-15f);
            this.pushingPSound = new PushingPSound(-5f);
        }

        public void loadBackgroundTrack() {
            try {
                URL resource = SoundManager.class.getResource("/audios/" + audioFileName);
                assert resource != null;
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(resource);
                backgroundTrack = AudioSystem.getClip();
                backgroundTrack.open(audioInputStream);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }
        public static synchronized SoundManager getInstance() {
            if (instance == null) {
                instance = new SoundManager("backgroundAudio.wav");
        }
        return instance;
    }
        public void playBackgroundTrack() {
            if (backgroundTrack != null) {
                backgroundTrack.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
        public void stopBackgroundTrack() {
            if (backgroundTrack != null) {
                backgroundTrack.stop();
            }
        }
        public void setVolume(float volume) {
            if (backgroundTrack != null && backgroundTrack.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) backgroundTrack.getControl(FloatControl.Type.MASTER_GAIN);
                float range = gainControl.getMaximum() - gainControl.getMinimum();
                float gain = (range * volume) + gainControl.getMinimum();
                gainControl.setValue(gain);
            }
        }
    public void playBackOptionSound() {
        backOptionSound.playAudio();
    }

    public void playGemCollectSound() {
        gemCollectSound.playAudio();
    }

    public void playInverterSound() {
        inverterSound.playAudio();
    }

    public void playKeyCollectSound() {
        keyCollectSound.playAudio();
    }

    public void playNavigateOptionSound() {
        navigateOptionSound.playAudio();
    }
    public SoundEffect getRandomWinSound(){
            List<SoundEffect> sounds = Arrays.asList(playerWinSound,fnafWinSound,youWinSound);
        Random random = new Random();
        int randomIndex = random.nextInt(sounds.size());
        return sounds.get(randomIndex);
    }
    public SoundEffect getRandomLossSound(){
        List<SoundEffect> sounds = Arrays.asList(playerDeathSound,snakeDeathSound,marioFallSound);
        Random random = new Random();
        int randomIndex = random.nextInt(sounds.size());
        return sounds.get(randomIndex);
    }
    public SoundEffect getRandomPushableBlockSound(){
        List<SoundEffect> sounds = Arrays.asList(pushingPSound,pushBlockSound,gruntSound);
        Random random = new Random();
        int randomIndex = random.nextInt(sounds.size());
        return sounds.get(randomIndex);
    }


    public void playPlayerMoveSound() {
        playerMoveSound.playAudio();
    }


    public void playSelectOptionSound() {
        selectOptionSound.playAudio();
    }

    public void playUnlockLockSound() {
        unlockLockSound.playAudio();
    }


}

