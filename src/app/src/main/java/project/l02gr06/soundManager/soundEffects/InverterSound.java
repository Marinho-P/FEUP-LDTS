package project.l02gr06.soundManager.soundEffects;

public class InverterSound extends SoundEffect{
    public InverterSound(float volume){
        super("invert.wav");
        this.setVolume(volume);
    }
}
