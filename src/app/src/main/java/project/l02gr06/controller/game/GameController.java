package project.l02gr06.controller.game;

import project.l02gr06.controller.Controller;
import project.l02gr06.model.game.level.Level;
import project.l02gr06.soundManager.SoundManager;

public abstract class GameController extends Controller<Level> {
    public GameController(Level level) {
        super(level);
    }
}
