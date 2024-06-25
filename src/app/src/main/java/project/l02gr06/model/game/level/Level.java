package project.l02gr06.model.game.level;

import org.checkerframework.checker.units.qual.K;
import project.l02gr06.model.Position;
import project.l02gr06.model.game.objects.*;
import project.l02gr06.model.game.objects.Object;
import project.l02gr06.model.game.tiles.Lava;
import project.l02gr06.model.game.tiles.StableTile;
import project.l02gr06.model.game.tiles.Tile;
import project.l02gr06.model.game.tiles.UnstableTile;
import project.l02gr06.soundManager.SoundManager;

import javax.sound.sampled.Clip;
import java.security.cert.PolicyQualifierInfo;
import java.sql.Struct;
import java.util.*;

public class Level {
    private int width;
    private int height;
    private int level;
    private Player player;
    private Gem gem;
    private boolean isGemCollected = false;
    private EndPortal endPortal;
    private List<Lock> locks;
    private List<Key> keys;
    private List<PushableBlock> pushableBlocks;
    private List<Inverter> inverters;
    private List<StableTile> stableTiles;
    private List<Lava> lavaList;
    private List<UnstableTile> unstableTiles;
    private List<Wall> walls;
    public Level(int width, int height, int level){
        this.width = width;
        this.height = height;
        this.level = level;
    }
    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Lock> getLocks() {
        return locks;
    }

    public void setLocks(List<Lock> lock) {
        this.locks = lock;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> key) {
        this.keys = key;
    }

    public Gem getGem() {
        return gem;
    }

    public void setGem(Gem gem) {
        this.gem = gem;
    }


    public void setEndPortal(EndPortal endPortal) {
        this.endPortal = endPortal;
    }

    public List<PushableBlock> getPushableBlocks() {
        return pushableBlocks;
    }

    public void setPushableBlocks(List<PushableBlock> pushableBlocks) {
        this.pushableBlocks = pushableBlocks;
    }

    public List<Inverter> getInverters() {
        return inverters;
    }

    public void setInverters(List<Inverter> inverters) {
        this.inverters = inverters;
    }

    public List<StableTile> getStableTiles() {
        return stableTiles;
    }

    public void setStableTiles(List<StableTile> stableTiles) {
        this.stableTiles = stableTiles;
    }

    public List<Lava> getLavaList() {
        return lavaList;
    }

    public void setLavaList(List<Lava> lavaList) {
        this.lavaList = lavaList;
    }

    public List<UnstableTile> getUnstableTiles() {
        return unstableTiles;
    }

    public void setUnstableTiles(List<UnstableTile> unstableTiles) {
        this.unstableTiles = unstableTiles;
    }


    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }
    public Object fetchObject(Position position){
        if( endPortal.getPosition().equals(position)){
            return endPortal;
        }
        if(gem != null && gem.getPosition().equals(position)){
            return gem;
        }
        if(player.getPosition().equals(position)){
            return player;
        }
        for(Inverter inverter:inverters){
            if(inverter.getPosition().equals(position)){
                    return inverter;
                }
            }
            for(Key key:keys){
                if(key.getPosition().equals(position)){
                    return key;
                }
            }
            for(Lock lock:locks){
                if(lock.getPosition().equals(position)){
                    return lock;
                }
            }
            for(PushableBlock pushableBlock:pushableBlocks){
                if(pushableBlock.getPosition().equals(position)){
                    return pushableBlock;
                }
            }
            for(Wall wall:walls){
                if(wall.getPosition().equals(position)){
                    return wall;
                }
            }
        return null;
    }
    public Tile fetchTile( Position position){
            for(StableTile stableTile:stableTiles){
                if(stableTile.getPosition().equals(position)){
                    return stableTile;
                }
            }
            for(UnstableTile unstableTile:unstableTiles){
                if(unstableTile.getPosition().equals(position)){
                    return unstableTile;
                }
            }
            for(Lava lava:lavaList){
                if(lava.getPosition().equals(position)){
                    return lava;
                }
            }
        return null;
    }
    public boolean isWall(Position position){
        for (Wall wall:walls){
            if(wall.getPosition().equals(position)) return true;
            }
        return false;
    }

    public void crackFloor(Position position){
        for (UnstableTile unstableTile: unstableTiles){
            if(unstableTile.getPosition().equals(position)){
                unstableTile.decreaseDurability();
                if (unstableTile.getDurability() == 0){
                    unstableTiles.remove(unstableTile);
                    lavaList.add(new Lava(position.getX(),position.getY()));
                    break;
                }
            }
        }
    }
    public boolean pickIfAnyGem(){
        if(gem != null && player.getPosition().equals(gem.getPosition())){
            isGemCollected = true;
            gem = null;
            return true;
        }
        return false;
    }
    public boolean pickIfAnyKey(){
        for (Key key:keys){
            if(player.getPosition().equals(key.getPosition())){
                player.collectKey();
                keys.remove(key);
                return true;
            }
        }
        return false;
    }
    public boolean pickIfAnyInverter(Position position){
        Inverter inverter = findInverter(position);
        if (inverter != null){
            invertTiles();
            inverters.remove(inverter);
            sinkBlocks();
            return true;
        }
        return false;
    }

    public void sinkBlocks() {
        Iterator<Lava> itLava = lavaList.iterator();
        while (itLava.hasNext()) {
            Lava currentLava = itLava.next();
            Iterator<PushableBlock> itPush = pushableBlocks.iterator();
            while (itPush.hasNext()) {
                PushableBlock currentPush = itPush.next();
                if (currentLava.getPosition().equals(currentPush.getPosition())) {
                    stableTiles.add(new StableTile(currentLava.getPosition()));
                    itPush.remove();
                    itLava.remove();
                    break; // Exit the inner loop after removing the matching elements
                }
            }
        }
    }

    public void invertTiles(){
        List<UnstableTile> newUnstableTiles = new ArrayList<>();
        for (Lava lava:lavaList){
            newUnstableTiles.add(new UnstableTile(lava.getPosition(),1));
        }
        List<Lava> newLavaTiles = new ArrayList<>();
        for(UnstableTile unstableTile:unstableTiles){
            newLavaTiles.add(new Lava(unstableTile.getPosition()));
        }
        unstableTiles = newUnstableTiles;
        lavaList = newLavaTiles;
    }
    public boolean reachEndPortal(){
        return player.getPosition().equals(endPortal.getPosition());
    }
    public void pushBlock(PushableBlock pushableBlock,Position targetPosition){
                Lava findLava = findLava(targetPosition);
               if(findLava!=null){
                   stableTiles.add(new StableTile(targetPosition));
                   pushableBlocks.remove(pushableBlock);
                   lavaList.remove(findLava);
               }
               else{
                   pushableBlock.setPosition(targetPosition);
               }

   }
   public Lava findLava(Position position){
        for(Lava lava: lavaList){
            if(lava.getPosition().equals(position)){
                return lava;
            }
        }
        return null;
   }
    public boolean canBePushed(Position targetPosition){
        if (isWall(targetPosition)) return false;
        for (Key key:keys){
            if(key.getPosition().equals(targetPosition)) return false;
        }
        for (Inverter inverter:inverters){
            if(inverter.getPosition().equals(targetPosition)) return false;
        }
        for (Lock lock:locks){
            if(lock.getPosition().equals(targetPosition)) return false;
        }
        for (PushableBlock pushableBlock:pushableBlocks){
            if(pushableBlock.getPosition().equals(targetPosition)) return false;
        }
        if (endPortal.getPosition().equals(targetPosition)) return false;
        return gem == null || !gem.getPosition().equals(targetPosition);
    }
    public PushableBlock findPushableBlock(Position position){
        for(PushableBlock pushableBlock: pushableBlocks){
            if(pushableBlock.getPosition().equals(position)) return pushableBlock;
        }
        return null;
    }
    public Position calculateNextBlockPosition(PushableBlock block){
        Position pushableBlockPosition = block.getPosition();
        int newX = pushableBlockPosition.getX() + (pushableBlockPosition.getX() - player.getPosition().getX());
        int newY = pushableBlockPosition.getY() + (pushableBlockPosition.getY() - player.getPosition().getY());
        return new Position(newX,newY);
    }
    public Inverter findInverter(Position position){
        for (Inverter inverter:inverters){
            if (inverter.getPosition().equals(position)) return inverter;
        }
        return null;
    }
    public Lock findLock(Position position){
        for( Lock lock: locks){
            if(lock.getPosition().equals(position)){
                return lock;
            }
        }
        return null;
    }
    public void unlockLock(Lock lock){
        locks.remove(lock);
        player.useKey();
    }

    public boolean isGemCollected() {
        return isGemCollected;
    }
}
