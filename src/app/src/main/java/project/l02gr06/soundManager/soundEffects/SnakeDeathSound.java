package project.l02gr06.soundManager.soundEffects;

public class SnakeDeathSound extends SoundEffect{
    public SnakeDeathSound(float volume){
        super("snake.wav");
        this.setVolume(volume);
    }
}
