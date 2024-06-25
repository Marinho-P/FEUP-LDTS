package project.l02gr06.soundManager.soundEffects;

public class PlayerDeathSound extends SoundEffect{
    public PlayerDeathSound(float volume){
        super("oof.wav");
        this.setVolume(volume);
    }
}
