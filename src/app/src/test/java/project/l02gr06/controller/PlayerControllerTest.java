package project.l02gr06.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.l02gr06.controller.game.PlayerController;
import project.l02gr06.model.Position;
import project.l02gr06.model.game.level.Level;
import project.l02gr06.model.game.objects.*;
import project.l02gr06.soundManager.SoundManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerControllerTest {
    private PlayerController playerController;
    private Player player;
    private Level level;
    @BeforeEach
    void setUp() {
        level = new Level(30,20,1);
        player = new Player(5,5);
        level.setPlayer(player);
        level.setWalls(new ArrayList<>());
        level.setEndPortal(new EndPortal(20,20));
        level.setKeys(new ArrayList<>());
        level.setLavaList(new ArrayList<>());
        level.setInverters(new ArrayList<>());
        level.setLocks(new ArrayList<>());
        level.setPushableBlocks(new ArrayList<>());
        level.setUnstableTiles(new ArrayList<>());
        level.setStableTiles(new ArrayList<>());
        playerController = new PlayerController(level);
    }
    @Test
    void movePlayerRightEmpty(){
        playerController.movePlayerRight();
        assert(player.getPosition().getX() == 6);
        assert(player.getPosition().getY() == 5);
    }
    @Test
    void movePlayerLeftEmpty(){
        playerController.movePlayerLeft();
        assert(player.getPosition().getX() == 4);
        assert(player.getPosition().getY() == 5);
    }
    @Test
    void movePlayerUpEmpty(){
        playerController.movePlayerUp();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 4);
    }
    @Test
    void movePlayerDownEmpty(){
        playerController.movePlayerDown();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 6);
    }
    @Test
    void movePlayerRightWall(){
        level.setWalls(Arrays.asList(new Wall(6,5)));
        playerController.movePlayerRight();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 5);
    }
    @Test
    void movePlayerLeftWall(){
        level.setWalls(Arrays.asList(new Wall(4,5)));
        playerController.movePlayerLeft();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 5);
    }
    @Test
    void movePlayerUpWall(){
        level.setWalls(Arrays.asList(new Wall(5,4)));
        playerController.movePlayerUp();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 5);
    }
    @Test
    void movePlayerDownWall(){
        level.setWalls(Arrays.asList(new Wall(5,6)));
        playerController.movePlayerDown();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 5);
    }
    @Test
    void movePlayerRightPushableBlock(){
        level.setPushableBlocks(Arrays.asList(new PushableBlock(6,5)));
        playerController.movePlayerRight();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 5);
        PushableBlock pushableBlock = level.getPushableBlocks().get(0);
        assert(pushableBlock.getPosition().getX() == 7);
        assert(pushableBlock.getPosition().getY() == 5);
        playerController.movePlayerRight();
        assert(player.getPosition().getX() == 6);
        assert(player.getPosition().getY() == 5);
    }
    @Test
    void movePlayerLeftPushableBlock(){
        level.setPushableBlocks(Arrays.asList(new PushableBlock(4,5)));
        playerController.movePlayerLeft();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 5);
        PushableBlock pushableBlock = level.getPushableBlocks().get(0);
        assert(pushableBlock.getPosition().getX() == 3);
        assert(pushableBlock.getPosition().getY() == 5);
        playerController.movePlayerLeft();
        assert(player.getPosition().getX() == 4);
        assert(player.getPosition().getY() == 5);
    }
    @Test
    void movePlayerUpPushableBlock(){
        level.setPushableBlocks(Arrays.asList(new PushableBlock(5,4)));
        playerController.movePlayerUp();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 5);
        PushableBlock pushableBlock = level.getPushableBlocks().get(0);
        assert(pushableBlock.getPosition().getX() == 5);
        assert(pushableBlock.getPosition().getY() == 3);
        playerController.movePlayerUp();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 4);
    }
    @Test
    void movePlayerDownPushableBlock(){
        level.setPushableBlocks(Arrays.asList(new PushableBlock(5,6)));
        playerController.movePlayerDown();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 5);
        PushableBlock pushableBlock = level.getPushableBlocks().get(0);
        assert(pushableBlock.getPosition().getX() == 5);
        assert(pushableBlock.getPosition().getY() == 7);
        playerController.movePlayerDown();
        assert(player.getPosition().getX() == 5);
        assert(player.getPosition().getY() == 6);
    }
    @Test
    void movePlayerUpLock() {
        Position initialPlayerPosition = new Position(5, 5);
        level.getLocks().add(new Lock(5, 4));
        player.setPosition(initialPlayerPosition);
        playerController.movePlayerUp();
        assertEquals(5, player.getPosition().getX());
        assertEquals(5, player.getPosition().getY());
        Lock lock = level.getLocks().get(0);
        assertEquals(5, lock.getPosition().getX());
        assertEquals(4, lock.getPosition().getY());
        player.collectKey();
        playerController.movePlayerUp();
        assertEquals(5, player.getPosition().getX());
        assertEquals(4, player.getPosition().getY());
        assertTrue(level.getLocks().isEmpty());
    }
    @Test
    void movePlayerDownLock() {
        Position initialPlayerPosition = new Position(5, 5);
        level.getLocks().add(new Lock(5, 6));
        player.setPosition(initialPlayerPosition);
        playerController.movePlayerDown();
        assertEquals(5, player.getPosition().getX());
        assertEquals(5, player.getPosition().getY());
        Lock lock = level.getLocks().get(0);
        assertEquals(5, lock.getPosition().getX());
        assertEquals(6, lock.getPosition().getY());
        player.collectKey();
        playerController.movePlayerDown();
        assertEquals(5, player.getPosition().getX());
        assertEquals(6, player.getPosition().getY());
        assertTrue(level.getLocks().isEmpty());
    }
    @Test
    void movePlayerLeftLock() {
        Position initialPlayerPosition = new Position(5, 5);
        level.getLocks().add(new Lock(4, 5));
        player.setPosition(initialPlayerPosition);
        playerController.movePlayerLeft();
        assertEquals(5, player.getPosition().getX());
        assertEquals(5, player.getPosition().getY());
        Lock lock = level.getLocks().get(0);
        assertEquals(4, lock.getPosition().getX());
        assertEquals(5, lock.getPosition().getY());
        player.collectKey();
        playerController.movePlayerLeft();
        assertEquals(4, player.getPosition().getX());
        assertEquals(5, player.getPosition().getY());
        assertTrue(level.getLocks().isEmpty());
    }
    @Test
    void movePlayerRightLock() {
        Position initialPlayerPosition = new Position(5, 5);
        level.getLocks().add(new Lock(6, 5));
        player.setPosition(initialPlayerPosition);
        playerController.movePlayerRight();
        assertEquals(5, player.getPosition().getX());
        assertEquals(5, player.getPosition().getY());
        Lock lock = level.getLocks().get(0);
        assertEquals(6, lock.getPosition().getX());
        assertEquals(5, lock.getPosition().getY());
        player.collectKey();
        playerController.movePlayerRight();
        assertEquals(6, player.getPosition().getX());
        assertEquals(5, player.getPosition().getY());
        assertTrue(level.getLocks().isEmpty());
    }

}
