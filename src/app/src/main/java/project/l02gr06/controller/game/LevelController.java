package project.l02gr06.controller.game;

import project.l02gr06.gui.GUI;
import project.l02gr06.Game;
import project.l02gr06.model.game.level.Level;
import project.l02gr06.model.menu.NextLevelMenu;
import project.l02gr06.model.menu.RetryMenu;
import project.l02gr06.saveManager.SaveFileManager;
import project.l02gr06.saveManager.SaveManager;
import project.l02gr06.state.NextLevelMenuState;
import project.l02gr06.state.RetryMenuState;

import java.io.IOException;

public class LevelController extends GameController{
    private final PlayerController playerController;

    public LevelController(Level level){
        super(level);
        this.playerController = new PlayerController(level);
    }
    public void step(Game game, GUI.ACTION action,long time) throws IOException{

        if(action == GUI.ACTION.QUIT || getModel().findLava(getModel().getPlayer().getPosition()) != null){
            game.getSoundManager().getRandomLossSound().playAudio();
            game.setState(new RetryMenuState(new RetryMenu(getModel().getLevel())));
            return;
        }
        if(getModel().reachEndPortal()){
            game.getSoundManager().getRandomWinSound().playAudio();
            game.setState(new NextLevelMenuState(new NextLevelMenu(getModel().getLevel())));
            SaveManager saveManager = new SaveFileManager();
            saveManager.saveLevelCompletion(getModel().getLevel(),getModel().isGemCollected());
        }
        else {
            playerController.step(game,action,time);
        }
    }
}
