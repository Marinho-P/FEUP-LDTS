package project.l02gr06.soundManager.soundEffects;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public abstract class SoundEffect {
    protected Clip soundEffect;
    protected String soundEffectFileName;
    public SoundEffect(String soundEffectFileName){
        this.soundEffectFileName = soundEffectFileName;
        activateAudios();
    }
    public void activateAudios() {
        try {
            URL resource = SoundEffect.class.getResource("/audios/"+soundEffectFileName);
            assert resource != null;
            AudioInputStream sound = AudioSystem.getAudioInputStream(resource);
            soundEffect = AudioSystem.getClip();
            soundEffect.open(sound);
        }catch (UnsupportedAudioFileException | LineUnavailableException | IOException e){
            e.printStackTrace();
        }
    }
    public void playAudio(){
        soundEffect.setFramePosition(0);
        soundEffect.start();
    }
    public String getSoundEffectFileName() {
        return soundEffectFileName;
    }
    public void setVolume(float volumeFactor) {
        if (soundEffect != null && soundEffect.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) soundEffect.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volumeFactor);
        }
    }

}
