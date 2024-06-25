package project.l02gr06.state;

import org.junit.jupiter.api.Test;
import project.l02gr06.Game;
import project.l02gr06.controller.Controller;
import project.l02gr06.controller.menu.RetryMenuController;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.menu.RetryMenu;
import project.l02gr06.soundManager.SoundManager;
import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.menu.RetryMenuViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RetryMenuStateTest {

    @Test
    public void testStepMethod() throws IOException {
        RetryMenu retryMenu = mock(RetryMenu.class);
        Game game = mock(Game.class);
        GUI gui = mock(GUI.class);
        long time = 100L;

        for (GUI.ACTION action : GUI.ACTION.values()) {
            reset(gui, retryMenu, game);

            when(gui.getNextAction()).thenReturn(action);

            RetryMenuState retryMenuState = spy(new RetryMenuState(retryMenu));

            RetryMenuViewer mockViewer = mock(RetryMenuViewer.class);
            RetryMenuController mockController = mock(RetryMenuController.class);
            SoundManager soundManager = mock(SoundManager.class);

            doReturn(mockViewer).when(retryMenuState).getViewer();
            doReturn(mockController).when(retryMenuState).getController();
            when(game.getSoundManager()).thenReturn(soundManager);

            retryMenuState.step(game, gui, time);
            mockViewer.draw(gui);
            mockController.step(game, action, time);

            verify(mockController).step(eq(game), eq(action), eq(time));
            verify(mockViewer).draw(eq(gui));
        }
    }
    @Test
    public void testStateInitialization() {
        RetryMenu retryMenu = mock(RetryMenu.class);

        RetryMenuState retryMenuState = new RetryMenuState(retryMenu);

        Viewer<RetryMenu> viewer = retryMenuState.getViewer();
        Controller<RetryMenu> controller = retryMenuState.getController();

        assertNotNull(viewer);
        assertNotNull(controller);
    }

}