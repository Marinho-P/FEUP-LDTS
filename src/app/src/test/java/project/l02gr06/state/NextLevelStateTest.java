package project.l02gr06.state;

import org.junit.jupiter.api.Test;
import project.l02gr06.Game;
import project.l02gr06.controller.Controller;
import project.l02gr06.controller.menu.NextLevelMenuController;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.menu.NextLevelMenu;
import project.l02gr06.soundManager.SoundManager;
import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.menu.NextLevelMenuViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class NextLevelStateTest {
    @Test void testStateInitialization() {
        NextLevelMenu nextLevelMenu = mock(NextLevelMenu.class);
        NextLevelMenuState nextLevelMenuState = new NextLevelMenuState(nextLevelMenu);
        Viewer<NextLevelMenu> viewer = nextLevelMenuState.getViewer();
        Controller<NextLevelMenu> controller = nextLevelMenuState.getController();

        assertNotNull(viewer);
        assertNotNull(controller);
    }
    @Test
    public void testStepMethod() throws IOException {
        NextLevelMenu nextLevelMenu = mock(NextLevelMenu.class);
        Game game = mock(Game.class);
        GUI gui = mock(GUI.class);
        long time = 100L;

        for (GUI.ACTION action : GUI.ACTION.values()) {
            reset(gui, nextLevelMenu, game);

            when(gui.getNextAction()).thenReturn(action);

            NextLevelMenuState nextLevelMenuState = spy(new NextLevelMenuState(nextLevelMenu));

            NextLevelMenuViewer mockViewer = mock(NextLevelMenuViewer.class);
            NextLevelMenuController mockController = mock(NextLevelMenuController.class);
            SoundManager soundManager = mock(SoundManager.class);

            doReturn(mockViewer).when(nextLevelMenuState).getViewer();
            doReturn(mockController).when(nextLevelMenuState).getController();
            when(game.getSoundManager()).thenReturn(soundManager);

            nextLevelMenuState.step(game, gui, time);
            mockController.step(game, action, time);
            mockViewer.draw(gui);

            verify(mockController).step(eq(game), eq(action), eq(time));
            verify(mockViewer).draw(eq(gui));
        }
    }
}
