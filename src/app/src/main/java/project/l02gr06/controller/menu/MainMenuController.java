package project.l02gr06.controller.menu;

import project.l02gr06.gui.GUI;
import project.l02gr06.Game;
import project.l02gr06.controller.Controller;
import project.l02gr06.model.menu.InstructionsMenu;
import project.l02gr06.model.menu.LevelsMenu;
import project.l02gr06.model.menu.MainMenu;
import project.l02gr06.state.InstructionsMenuState;
import project.l02gr06.state.LevelsMenuState;

import java.io.IOException;

public class MainMenuController extends Controller<MainMenu> {
    public MainMenuController(MainMenu mainMenu) {
        super(mainMenu);
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
                if (getModel().isExitOption()){
                    game.getSoundManager().playBackOptionSound();
                    game.setState(null);
                }
                if (getModel().isStartOption()){
                    game.getSoundManager().playSelectOptionSound();
                    LevelsMenu levelsMenu = new LevelsMenu();
                    LevelsMenuState state = new LevelsMenuState(levelsMenu);
                    game.setState(state);
                }
                if(getModel().isInstructionsOption()){
                    game.getSoundManager().playSelectOptionSound();
                    game.setState(new InstructionsMenuState(new InstructionsMenu()));
                }
                break;
            case QUIT:
                game.getSoundManager().playBackOptionSound();
                game.setState(null);
        }
    }
}
