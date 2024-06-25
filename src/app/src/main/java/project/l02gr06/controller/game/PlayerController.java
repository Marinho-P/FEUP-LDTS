package project.l02gr06.controller.game;

import project.l02gr06.gui.GUI;
import project.l02gr06.Game;
import project.l02gr06.model.Position;
import project.l02gr06.model.game.level.Level;
import project.l02gr06.model.game.objects.Lock;
import project.l02gr06.model.game.objects.PushableBlock;
import project.l02gr06.model.menu.NextLevelMenu;
import project.l02gr06.soundManager.SoundManager;
import project.l02gr06.state.NextLevelMenuState;

public class PlayerController extends GameController{
    public PlayerController(Level level){
        super(level);
    }
    public void step(Game game, GUI.ACTION action, long time){
            if (action == GUI.ACTION.UP) {
                movePlayerUp();
            }
            if (action == GUI.ACTION.DOWN) {
                movePlayerDown();
            }
            if (action == GUI.ACTION.RIGHT) {
                movePlayerRight();
            }
            if (action == GUI.ACTION.LEFT) {
                movePlayerLeft();
            }
    }
    public void movePlayerDown(){
        movePlayer(getModel().getPlayer().getPosition(), getModel().getPlayer().getPosition().getDown());
    }
    public void movePlayerUp(){
        movePlayer(getModel().getPlayer().getPosition(), getModel().getPlayer().getPosition().getUp());

    }
    public void movePlayerLeft(){
        movePlayer(getModel().getPlayer().getPosition(), getModel().getPlayer().getPosition().getLeft());

    }
    public void movePlayerRight(){
        movePlayer(getModel().getPlayer().getPosition(), getModel().getPlayer().getPosition().getRight());
    }
    public void movePlayer(Position currentPosition, Position targetPosition){
        if((getModel().isWall(targetPosition))){return;}
        PushableBlock findBlock = getModel().findPushableBlock(targetPosition);
        if(findBlock != null){
            Position newBlockPosition = getModel().calculateNextBlockPosition(findBlock);
            if(getModel().canBePushed(newBlockPosition)){
                SoundManager.getInstance().getRandomPushableBlockSound().playAudio();
                getModel().pushBlock(findBlock,newBlockPosition);
            }
            return;
        }
        Lock findLock = getModel().findLock(targetPosition);
        if (findLock != null){
            if(getModel().getPlayer().hasKey()){
                SoundManager.getInstance().playUnlockLockSound();
                getModel().unlockLock(findLock);
                getModel().getPlayer().setPosition(targetPosition);
                getModel().crackFloor(currentPosition);
            }
            return;
        }
        SoundManager.getInstance().playPlayerMoveSound();
        getModel().crackFloor(currentPosition);
        getModel().getPlayer().setPosition(targetPosition);
        if(getModel().pickIfAnyInverter(targetPosition)){
            SoundManager.getInstance().playInverterSound();
        }
        if(getModel().pickIfAnyGem()){
            SoundManager.getInstance().playGemCollectSound();
        }
        if(getModel().pickIfAnyKey()){
            SoundManager.getInstance().playKeyCollectSound();
        }
    }
}
