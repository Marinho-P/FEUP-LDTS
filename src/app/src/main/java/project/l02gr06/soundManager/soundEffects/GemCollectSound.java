package project.l02gr06.soundManager.soundEffects;

public class GemCollectSound extends SoundEffect{
    public GemCollectSound(float volume){
        super("gemSound.wav");
        this.setVolume(volume);
    }
}
