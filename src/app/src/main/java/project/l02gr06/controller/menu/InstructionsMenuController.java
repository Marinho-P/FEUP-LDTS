package project.l02gr06.controller.menu;

import project.l02gr06.Game;
import project.l02gr06.controller.Controller;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.menu.InstructionsMenu;
import project.l02gr06.model.menu.LevelsMenu;
import project.l02gr06.model.menu.MainMenu;
import project.l02gr06.state.LevelsMenuState;
import project.l02gr06.state.MainMenuState;

import java.io.IOException;

public class InstructionsMenuController extends Controller<InstructionsMenu> {
    public InstructionsMenuController(InstructionsMenu instructionsMenu){
        super(instructionsMenu);
    }
    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case LEFT:
                game.getSoundManager().playNavigateOptionSound();
                getModel().previousOption();
                break;
            case RIGHT:
                game.getSoundManager().playNavigateOptionSound();
                getModel().nextOption();
                break;
            case SELECT:
                if (getModel().isBackToMainMenu()){
                    game.getSoundManager().playBackOptionSound();
                    game.setState(new MainMenuState(new MainMenu()));
                }
                if (getModel().isPlayOption()){
                    game.getSoundManager().playSelectOptionSound();
                    LevelsMenu levelsMenu = new LevelsMenu();
                    LevelsMenuState state = new LevelsMenuState(levelsMenu);
                    game.setState(state);
                };
                break;
            case QUIT:
                game.getSoundManager().playBackOptionSound();
                game.setState(new MainMenuState(new MainMenu()));
        }
    }
}
