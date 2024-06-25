package project.l02gr06.state;

import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.game.GameViewer;
import project.l02gr06.controller.Controller;
import project.l02gr06.controller.game.LevelController;
import project.l02gr06.model.game.level.Level;

public class GameState extends State<Level>{
    public GameState(Level level) {
        super(level);
    }

    @Override
    protected Viewer<Level> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Level> getController() {
        return new LevelController(getModel());
    }
}
