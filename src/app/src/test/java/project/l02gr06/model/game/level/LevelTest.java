package project.l02gr06.model.game.level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.l02gr06.Game;
import project.l02gr06.model.Position;
import project.l02gr06.model.game.objects.*;
import project.l02gr06.model.game.objects.Object;
import project.l02gr06.model.game.tiles.Lava;
import project.l02gr06.model.game.tiles.StableTile;
import project.l02gr06.model.game.tiles.Tile;
import project.l02gr06.model.game.tiles.UnstableTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    Level level;
    private List<Lock> locks;
    private List<Key> keys;
    private List<PushableBlock> pushableBlocks ;
    private List<Inverter> inverters;
    private List<StableTile> stableTiles;
    private List<Lava> lavaList;
    private List<UnstableTile> unstableTiles;
    private List<Wall> walls ;
    private Player player ;
    private EndPortal endPortal;
    @BeforeEach
    public void setUp() {
        locks = new ArrayList<>();
        keys = new ArrayList<>();
        pushableBlocks = new ArrayList<>();
        inverters = new ArrayList<>();
        stableTiles = new ArrayList<>();
        lavaList = new ArrayList<>();
        unstableTiles = new ArrayList<>();
        walls = new ArrayList<>();
        player = new Player(1,1);
        endPortal = new EndPortal(2,2);

        level = new Level(30,20,1);
        level.setLocks(locks);
        level.setKeys(keys);
        level.setPushableBlocks(pushableBlocks);
        level.setInverters(inverters);
        level.setStableTiles(stableTiles);
        level.setLavaList(lavaList);
        level.setUnstableTiles(unstableTiles);
        level.setWalls(walls);
        level.setPlayer(player);
        level.setEndPortal(endPortal);
    }
    @Test
    public void findLava() {
        Position position = new Position(1,1);
        lavaList.add(new Lava(position.getX(),position.getY()));
        assertEquals(position, level.findLava(position).getPosition());
        level.setLavaList(Collections.emptyList());
        assertNull(level.findLava(position));
        Position nonExistentPosition = new Position(10, 10);
        assertNull(level.findLava(nonExistentPosition));
    }
    @Test
    public void testFetchObject_EndPortal() {
        Position position = new Position(2, 2);
        Object result = level.fetchObject(position);
        assertTrue(result instanceof EndPortal);
        assertEquals(position, result.getPosition());
    }
    @Test
    public void testFetchObject_Gem() {
        Position position = new Position(1, 1);
        Gem gem = new Gem(position.getX(),position.getY());
        level.setGem(gem);
        Object result = level.fetchObject(position);
        assertTrue(result instanceof Gem);
        assertEquals(position, result.getPosition());
    }
    @Test public void testFetchObject_Keys() {
        Position position = new Position(3, 3);
        Key key = new Key(position.getX(),position.getY());
        keys.add(key);
        Object result = level.fetchObject(position);
        assertTrue(result instanceof Key);
        assertEquals(position, result.getPosition());
    }
    @Test
    public void testFetchObject_Locks(){
        Position position = new Position(3, 3);
        Lock lock = new Lock(position.getX(),position.getY());
        locks.add(lock);
        Object result = level.fetchObject(position);
        assertTrue(result instanceof Lock);
        assertEquals(position, result.getPosition());
    }
    @Test
    public void testFetchObject_Walls(){
        Position position = new Position(3, 3);
        Wall wall = new Wall(position.getX(),position.getY());
        walls.add(wall);
        Object result = level.fetchObject(position);
        assertTrue(result instanceof Wall);
        assertEquals(position, result.getPosition());
    }
    @Test
    public void testFetchObject_PushableBlock(){
        Position position = new Position(3, 3);
        PushableBlock pushableBlock = new PushableBlock(position.getX(),position.getY());
        pushableBlocks.add(pushableBlock);
        Object result = level.fetchObject(position);
        assertTrue(result instanceof PushableBlock);
        assertEquals(position, result.getPosition());
    }
    @Test public void testFetchObject_Player(){
        Position position = new Position(1, 1);
        Player player = new Player(position.getX(),position.getY());
        level.setPlayer(player);
        Object result = level.fetchObject(position);
        assertTrue(result instanceof Player);
        assertEquals(position, result.getPosition());
    }
    @Test public void testFetchObject_Inverters(){
        Position position = new Position(3, 3);
        Inverter inverter = new Inverter(position.getX(),position.getY());
        inverters.add(inverter);
        Object result = level.fetchObject(position);
        assertTrue(result instanceof Inverter);
        assertEquals(position, result.getPosition());
    }
    @Test public void testFetchObject_Null(){
        Position position = new Position(3, 3);
        Object result = level.fetchObject(position);
        assertNull(result);
    }
    @Test public void testFetchTile_StableTiles(){
        Position position = new Position(1, 1);
        StableTile stableTile = new StableTile(position.getX(),position.getY());
        stableTiles.add(stableTile);
        Tile result = level.fetchTile(position);
        assertTrue(result instanceof StableTile);
        assertEquals(position, result.getPosition());
    }
    @Test public void testFetchTile_Lava(){
        Position position = new Position(1, 1);
        Lava lava = new Lava(position.getX(),position.getY());
        lavaList.add(lava);
        Tile result = level.fetchTile(position);
        assertTrue(result instanceof Lava);
        assertEquals(position, result.getPosition());
    }
    @Test public void testFetchTile_Null(){
        Position position = new Position(1, 1);
        Tile result = level.fetchTile(position);
        assertNull(result);
    }
    @Test public void testFetchTile_UnstableTiles(){
        Position position = new Position(1, 1);
        UnstableTile unstableTile = new UnstableTile(position.getX(),position.getY(),1);
        unstableTiles.add(unstableTile);
        Tile result = level.fetchTile(position);
        assertTrue(result instanceof UnstableTile);
        assertEquals(position, result.getPosition());
    }
    @Test public void isWall(){
        Position position = new Position(1, 1);
        Wall wall = new Wall(position.getX(),position.getY());
        walls.add(wall);
        assertTrue(level.isWall(position));
        level.setWalls(Collections.emptyList());
        assertFalse(level.isWall(position));
    }
    @Test public void pickIfAnyGem_Success(){
        level.setGem(new Gem(1,1));
        level.setPlayer(new Player(1,1));
        boolean result = level.pickIfAnyGem();
        assertTrue(result);
        assertTrue(level.isGemCollected());
        assertNull(level.getGem());
    }
    @Test public void pickIfAnyGem_Fail(){
        level.setGem(new Gem(1,1));
        level.setPlayer(new Player(2,2));
        boolean result = level.pickIfAnyGem();
        assertFalse(result);
        assertFalse(level.isGemCollected());
        assertNotNull(level.getGem());
    }
    @Test public void pickIfAnyKey_Success(){
        keys.add(new Key(1, 1));
        level.setPlayer(new Player(1,1));
        boolean result = level.pickIfAnyKey();
        assertTrue(result);
        assertTrue(level.getPlayer().hasKey());
        assertTrue(level.getKeys().isEmpty());
    }
    @Test public void pickIfAnyKey_Fail(){
        keys.add(new Key(1, 1));
        level.setKeys(keys);
        level.setPlayer(new Player(2,2));
        boolean result = level.pickIfAnyKey();
        assertFalse(result);
        assertFalse(level.getPlayer().hasKey());
        assertEquals(keys, level.getKeys());
    }
    @Test public void findInverter(){
        Position position = new Position(1,1);
        Inverter inverter = new Inverter(position.getX(),position.getY());
        inverters.add(inverter);
        assertEquals(position, level.findInverter(position).getPosition());
        level.setInverters(Collections.emptyList());
        assertNull(level.findInverter(position));
        Position nonExistentPosition = new Position(10, 10);
        assertNull(level.findInverter(nonExistentPosition));
    }
    @Test public void crackFloor(){
        Position position = new Position(1, 1);
        UnstableTile unstableTile = new UnstableTile(position,1);
        unstableTiles.add(unstableTile);
        level.crackFloor(position);
        assertTrue(unstableTiles.isEmpty());
        assertEquals(1, level.getLavaList().size());
    }
    @Test public void testInvertTiles(){
        lavaList.add(new Lava(new Position(3, 3)));
        lavaList.add(new Lava(new Position(4, 4)));
        lavaList.add(new Lava(new Position(5, 5)));
        unstableTiles.add(new UnstableTile(new Position(6, 6), 1));
        unstableTiles.add(new UnstableTile(new Position(7, 7), 1));
        level.invertTiles();
        assertEquals(level.getLavaList().size()+1 , level.getUnstableTiles().size());
    }
    @Test public void pickIfAnyInverter_Success(){
        inverters.add(new Inverter(1, 1));
        boolean result = level.pickIfAnyInverter(new Position(1,1));
        assertTrue(result);
        assertTrue(level.getInverters().isEmpty());
    }
    @Test public void pickIfAnyInverter_Fail(){
        inverters.add(new Inverter(1, 1));
        boolean result = level.pickIfAnyInverter(new Position(2,2));
        assertFalse(result);
        assertEquals(inverters, level.getInverters());
    }
    @Test public void sinkBlocks(){
        Lava lava1 = new Lava(new Position(3, 3));
        Lava lava2 = new Lava(new Position(5, 5));
        PushableBlock pushableBlock1 = new PushableBlock(3,3);
        PushableBlock pushableBlock2 = new PushableBlock(4,4);
        lavaList.add(lava1);
        lavaList.add(lava2);
        pushableBlocks.add(pushableBlock1);
        pushableBlocks.add(pushableBlock2);
        level.sinkBlocks();
        assertEquals(1, level.getStableTiles().size());  // One StableTile should be added
        assertEquals(1, level.getPushableBlocks().size());  // One PushableBlock should be removed
        assertEquals(1, level.getLavaList().size());  // One Lava should be removed
    }
    @Test public void reachEndPortal(){
        level.setPlayer(new Player(2,2));
        assertTrue(level.reachEndPortal());
        level.setEndPortal(new EndPortal(3,3));
        assertFalse(level.reachEndPortal());
    }
    @Test public void unlockLock(){
        Position position = new Position(1, 1);
        Lock lock = new Lock(position.getX(),position.getY());
        player.collectKey();
        locks.add(lock);
        level.unlockLock(lock);
        assertTrue(locks.isEmpty());
        assertEquals(0,player.getKeys());
    }
    @Test public void findLock(){
        Position position = new Position(3, 3);
        Lock lock = new Lock(position.getX(),position.getY());
        locks.add(lock);
        assertEquals(position, level.findLock(position).getPosition());
        level.setLocks(Collections.emptyList());
        assertNull(level.findLock(position));
        Position nonExistentPosition = new Position(10, 10);
        assertNull(level.findLock(nonExistentPosition));
    }
    @Test public void calculateNextBlockPosition(){
        PushableBlock pushableBlock = new PushableBlock(6,6);
        pushableBlocks.add(pushableBlock);
        player.setPosition(new Position(5,6));
        Position nextBlockPosition = level.calculateNextBlockPosition(pushableBlock);
        assertEquals(7, nextBlockPosition.getX());
        assertEquals(6, nextBlockPosition.getY());
    }
    @Test public void findPushableBlock(){
        Position position = new Position(3, 3);
        PushableBlock pushableBlock = new PushableBlock(position.getX(),position.getY());
        pushableBlocks.add(pushableBlock);
        assertEquals(position, level.findPushableBlock(position).getPosition());
        level.setPushableBlocks(Collections.emptyList());
        assertNull(level.findPushableBlock(position));
        Position nonExistentPosition = new Position(10, 10);
        assertNull(level.findPushableBlock(nonExistentPosition));
    }
    @Test public void PushBlock_WithLava(){
        Position targetPosition = new Position(1, 3);
        PushableBlock pushableBlock = new PushableBlock(1,2);
        Lava lavaAtTarget = new Lava(targetPosition.getX(),targetPosition.getY());
        lavaList.add(lavaAtTarget);
        pushableBlocks.add(pushableBlock);
        level.pushBlock(pushableBlock, targetPosition);
        assertTrue(level.getStableTiles().stream().anyMatch(st -> st.getPosition().equals(targetPosition)));
        assertFalse(level.getPushableBlocks().contains(pushableBlock));
        assertFalse(level.getLavaList().contains(lavaAtTarget));
    }
    @Test
    public void testPushBlockWithoutLava() {
        Position targetPosition = new Position(1, 3);
        PushableBlock pushableBlock = new PushableBlock(1,2);
        lavaList.add(new Lava(1,4));
        pushableBlocks.add(pushableBlock);
        level.pushBlock(pushableBlock, targetPosition);
        assertFalse(level.getStableTiles().stream().anyMatch(st -> st.getPosition().equals(targetPosition)));
        assertTrue(level.getPushableBlocks().contains(pushableBlock));
        assertFalse(level.getLavaList().isEmpty());
    }
    @Test public void canBePushed(){
        walls.add(new Wall(5,5));
        keys.add(new Key(6,6));
        locks.add(new Lock(7,7));
        inverters.add(new Inverter(8,8));
        pushableBlocks.add(new PushableBlock(9,9));
        assertFalse(level.canBePushed(new Position(2,2)));
        assertFalse(level.canBePushed(new Position(5,5)));
        assertFalse(level.canBePushed(new Position(6,6)));
        assertFalse(level.canBePushed(new Position(7,7)));
        assertFalse(level.canBePushed(new Position(8,8)));
        assertFalse(level.canBePushed(new Position(9,9)));
        level.setGem(new Gem(1,3));
        assertFalse(level.canBePushed(new Position(1,3)));
        level.setGem(null);
        assertTrue(level.canBePushed(new Position(1, 3)));
    }
}