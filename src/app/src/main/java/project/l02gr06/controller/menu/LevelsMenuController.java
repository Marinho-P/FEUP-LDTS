package project.l02gr06.controller.menu;

import project.l02gr06.Game;
import project.l02gr06.controller.Controller;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.game.level.LevelBuilder;
import project.l02gr06.model.menu.LevelsMenu;
import project.l02gr06.model.menu.MainMenu;
import project.l02gr06.state.GameState;
import project.l02gr06.state.MainMenuState;

import java.io.IOException;

public class LevelsMenuController extends Controller<LevelsMenu> {
    public LevelsMenuController(LevelsMenu levelsMenu){
        super(levelsMenu);
    }
    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        int currentOption = getModel().getCurrentOption();
        int numberOfOptions = getModel().getNumberOfOptions();
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
                if (currentOption == numberOfOptions - 1 ){
                    game.getSoundManager().playBackOptionSound();
                    game.setState(new MainMenuState(new MainMenu()));
                }
                if (getModel().isSelectable(currentOption) && currentOption < numberOfOptions- 1 ){
                    game.getSoundManager().playSelectOptionSound();
                    game.getSoundManager().setVolume(0.78f);
                    game.setState(new GameState(new LevelBuilder(getModel().getCurrentOption()+1).createLevel()));
                }
                break;
            case QUIT:
                game.getSoundManager().playBackOptionSound();
                game.setState(new MainMenuState(new MainMenu()));
        }
    }
}
