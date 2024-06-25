package project.l02gr06.model.game.level;

import project.l02gr06.model.game.objects.*;
import project.l02gr06.model.game.tiles.Lava;
import project.l02gr06.model.game.tiles.StableTile;
import project.l02gr06.model.game.tiles.UnstableTile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LevelBuilder {
    public static final int HEIGHT = 20;
    public static final int WIDTH = 30;
    private int level;
    private List<String> tileInformation;
    private List<String> objectInformation;
    public LevelBuilder(int level) throws IOException {
        this.level = level;
        URL resource = LevelBuilder.class.getResource("/levels/level"+ level +".lvl");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(resource.getFile()));
        this.objectInformation = readFile(bufferedReader);
        this.tileInformation = readFile(bufferedReader);
        bufferedReader.close();
    }
    private List<String> readFile(BufferedReader reader) throws IOException {
        List<String> fileLines = new ArrayList<>();
        String fileLine;
        int counter = 0;
        while (counter < HEIGHT){
            fileLine = reader.readLine();
            fileLines.add(fileLine);
            counter++;
        }
        return fileLines;
    }
    public Level createLevel(){
        Level level = new Level(WIDTH, HEIGHT,this.level);
        level.setLocks(createLocks());
        level.setEndPortal(createEndPortal());
        level.setStableTiles(createStableTiles());
        level.setWalls(createWalls());
        level.setPlayer(createPlayer());
        level.setPushableBlocks(createPushableBlocks());
        level.setGem(createGem());
        level.setInverters(createInverters());
        level.setLavaList(createLava());
        level.setUnstableTiles(createUnstableTiles());
        level.setKeys(createKeys());
        return level;
    }
    private Player createPlayer(){
        for (int y = 0; y < objectInformation.size(); y++) {
            String line = objectInformation.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'X') return new Player(x,y);
            }
        }
        return null;
    }
    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();
        for (int y = 0; y < objectInformation.size(); y++) {
            String line = objectInformation.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '#') walls.add(new Wall(x,y));
            }
        }
        return walls;
    }
    private List<PushableBlock> createPushableBlocks(){
        List<PushableBlock> pushableBlocks = new ArrayList<>();
        for (int y = 0; y < objectInformation.size(); y++) {
            String line = objectInformation.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'P') pushableBlocks.add(new PushableBlock(x,y));
            }
        }
        return pushableBlocks;
    }
    private List<Lock> createLocks(){
        List<Lock> locks = new ArrayList<>();
        for (int y = 0; y < objectInformation.size(); y++) {
            String line = objectInformation.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'O') locks.add(new Lock(x,y));
            }
        }
        return locks;
    }
    private EndPortal createEndPortal(){
        for (int y = 0; y < objectInformation.size(); y++) {
            String line = objectInformation.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'E') return new EndPortal(x,y);
            }
        }
        return null;
    }
    private Gem createGem(){
        for (int y = 0; y < objectInformation.size(); y++) {
            String line = objectInformation.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'G') return new Gem(x,y);
            }
        }
        return null;
    }
    private List<Inverter> createInverters(){
        List<Inverter> inverters = new ArrayList<>();
        for (int y = 0; y < objectInformation.size(); y++) {
            String line = objectInformation.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'I') inverters.add(new Inverter(x,y));
            }
        }
        return inverters;
    }
    private List<Key> createKeys(){
        List<Key> keys = new ArrayList<>();
        for (int y = 0; y < objectInformation.size(); y++) {
            String line = objectInformation.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'K') keys.add(new Key(x,y));
            }
        }
        return keys;
    }
    private List<Lava> createLava(){
        List<Lava> lavaList = new ArrayList<>();
        for (int y = 0; y < tileInformation.size(); y++){
            String line = tileInformation.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'L') lavaList.add(new Lava(x,y));
            }
        }
        return lavaList;
    }
    private List<UnstableTile> createUnstableTiles(){
        List<UnstableTile> unstableTiles = new ArrayList<>();
        for (int y = 0; y < tileInformation.size(); y++){
            String line = tileInformation.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '1') unstableTiles.add(new UnstableTile(x,y,1));
                if (line.charAt(x) == '2') unstableTiles.add(new UnstableTile(x,y,2));
            }
        }
        return unstableTiles;
    }
    private List<StableTile> createStableTiles(){
        List<StableTile> stableTiles = new ArrayList<>();
        for (int y = 0; y < tileInformation.size(); y++){
            String line = tileInformation.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'S') stableTiles.add(new StableTile(x,y));
            }
        }
        return stableTiles;
    }
}
