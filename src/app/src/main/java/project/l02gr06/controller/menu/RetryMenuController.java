package project.l02gr06.controller.menu;

import project.l02gr06.Game;
import project.l02gr06.controller.Controller;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.game.level.LevelBuilder;
import project.l02gr06.model.menu.LevelsMenu;
import project.l02gr06.model.menu.RetryMenu;
import project.l02gr06.state.GameState;
import project.l02gr06.state.LevelsMenuState;

import java.io.IOException;

public class RetryMenuController extends Controller<RetryMenu> {
    public RetryMenuController(RetryMenu retryMenu){
        super(retryMenu);
    }
    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                game.getSoundManager().playNavigateOptionSound();
                getModel().previousOption();
                break;
            case DOWN:
                game.getSoundManager().playNavigateOptionSound();
                getModel().nextOption();
                break;
            case SELECT:
                if (getModel().isExit()){
                    game.getSoundManager().playBackOptionSound();
                    game.setState(new LevelsMenuState(new LevelsMenu()));
                }
                if (getModel().isTryAgainOption()){
                    game.getSoundManager().playSelectOptionSound();
                    game.setState(new GameState(new LevelBuilder(getModel().getCurrentLevel()).createLevel()));
                }
                break;
            case QUIT:
                game.getSoundManager().playBackOptionSound();
                game.setState(new LevelsMenuState(new LevelsMenu()));
        }

    }
}
