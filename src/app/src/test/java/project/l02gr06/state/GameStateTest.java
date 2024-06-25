package project.l02gr06.state;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.l02gr06.Game;
import project.l02gr06.controller.Controller;
import project.l02gr06.controller.game.LevelController;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.game.level.Level;
import project.l02gr06.model.game.objects.Player;
import project.l02gr06.model.game.tiles.Lava;
import project.l02gr06.saveManager.SaveFileManager;
import project.l02gr06.soundManager.SoundManager;
import project.l02gr06.soundManager.soundEffects.SoundEffect;
import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.game.GameViewer;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class GameStateTest {

    @Test
    public void testStepMethod() throws IOException {
        Level level = mock(Level.class);
        Game game = mock(Game.class);
        GUI gui = mock(GUI.class);
        long time = 100L;
        Player player = mock(Player.class);

        for (GUI.ACTION action : GUI.ACTION.values()) {
            reset(gui, level, game, player);
            when(level.getPlayer()).thenReturn(player);
            level.setPlayer(player);
            when(gui.getNextAction()).thenReturn(action);

            GameState gameState = spy(new GameState(level));

            GameViewer mockViewer = mock(GameViewer.class);
            LevelController mockController = mock(LevelController.class);
            SoundManager soundManager = mock(SoundManager.class);
            SoundEffect winSound = mock(SoundEffect.class);

            doReturn(mockViewer).when(gameState).getViewer();
            doReturn(mockController).when(gameState).getController();
            when(game.getSoundManager()).thenReturn(soundManager);

            when(level.reachEndPortal()).thenReturn(true);
            when(soundManager.getRandomWinSound()).thenReturn(winSound);
            when(game.getSoundManager()).thenReturn(soundManager);
            when(level.findLava(player.getPosition())).thenReturn(action == GUI.ACTION.QUIT ? mock(Lava.class) : null);
            SoundEffect lossSound = mock(SoundEffect.class);
            when(soundManager.getRandomLossSound()).thenReturn(lossSound);
            mockController.step(game, action, time);
            mockViewer.draw(gui);
            gameState.step(game, gui, time);

            if (action == GUI.ACTION.QUIT) {
                verify(game).setState(any(RetryMenuState.class));
            } else if (level.reachEndPortal()) {
                verify(game).setState(any(NextLevelMenuState.class));
            } else {
                verify(mockController).step(eq(game), eq(action), eq(time));
            }
        }
    }

    @Test
    void testStateInitialization() {
        Level level = mock(Level.class);

        GameState gameState = new GameState(level);

        Viewer<Level> viewer = gameState.getViewer();
        Controller<Level> controller = gameState.getController();
        assertNotNull(viewer);
        assertNotNull(controller);
    }

}