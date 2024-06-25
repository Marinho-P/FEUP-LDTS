package project.l02gr06.controller.menu;

import project.l02gr06.Game;
import project.l02gr06.controller.Controller;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.game.level.LevelBuilder;
import project.l02gr06.model.menu.LevelsMenu;
import project.l02gr06.model.menu.MainMenu;
import project.l02gr06.model.menu.NextLevelMenu;
import project.l02gr06.saveManager.SaveFileManager;
import project.l02gr06.saveManager.SaveManager;
import project.l02gr06.state.GameState;
import project.l02gr06.state.LevelsMenuState;
import project.l02gr06.state.MainMenuState;

import java.io.IOException;

public class NextLevelMenuController extends Controller<NextLevelMenu> {
    public NextLevelMenuController(NextLevelMenu nextLevelMenu){
        super(nextLevelMenu);
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
                if (getModel().isNextLevel()){
                    game.getSoundManager().playSelectOptionSound();
                    loadNextLevel(game);
                }
                break;
            case QUIT:
                game.getSoundManager().playBackOptionSound();
                game.setState(new LevelsMenuState(new LevelsMenu()));
        }
    }

    private void loadNextLevel(Game game) throws IOException {
        SaveManager saveManager = new SaveFileManager();
        if(getModel().getCurrentLevel() == saveManager.getNumberOfLevels()){
            game.setState(new MainMenuState(new MainMenu()));
        }
        if(getModel().getCurrentLevel() == saveManager.getNumberOfLevels() - 1){
            if(saveManager.getNumberOfGems() == 7){
                game.setState(new GameState(new LevelBuilder(getModel().getCurrentLevel()+1).createLevel()));
            }
        }
        else{
            game.setState(new GameState(new LevelBuilder(getModel().getCurrentLevel()+1).createLevel()));
        }
    }
}
