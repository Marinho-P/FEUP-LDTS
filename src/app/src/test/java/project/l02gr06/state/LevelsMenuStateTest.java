package project.l02gr06.state;

import org.junit.jupiter.api.Test;
import project.l02gr06.Game;
import project.l02gr06.controller.Controller;
import project.l02gr06.controller.menu.LevelsMenuController;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.menu.LevelsMenu;
import project.l02gr06.soundManager.SoundManager;
import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.menu.LevelsMenuViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class LevelsMenuStateTest {

    @Test
    public void testStepMethod() throws IOException {
        LevelsMenu levelsMenu = mock(LevelsMenu.class);
        Game game = mock(Game.class);
        GUI gui = mock(GUI.class);
        long time = 100L;

        for (GUI.ACTION action : GUI.ACTION.values()) {
            reset(gui, levelsMenu, game);

            when(gui.getNextAction()).thenReturn(action);

            LevelsMenuState levelsMenuState = spy(new LevelsMenuState(levelsMenu));

            LevelsMenuViewer mockViewer = mock(LevelsMenuViewer.class);
            LevelsMenuController mockController = mock(LevelsMenuController.class);
            SoundManager soundManager = mock(SoundManager.class);

            doReturn(mockViewer).when(levelsMenuState).getViewer();
            doReturn(mockController).when(levelsMenuState).getController();
            when(game.getSoundManager()).thenReturn(soundManager);


            levelsMenuState.step(game, gui, time);
            mockController.step(game, action, time);
            mockViewer.draw(gui);

            verify(mockController).step(eq(game), eq(action), eq(time));
            verify(mockViewer).draw(eq(gui));
        }
    }
    @Test
    public void testStateInitialization() {
        LevelsMenu levelsMenu = mock(LevelsMenu.class);

        LevelsMenuState levelsMenuState = new LevelsMenuState(levelsMenu);

        Viewer<LevelsMenu> viewer = levelsMenuState.getViewer();
        Controller<LevelsMenu> controller = levelsMenuState.getController();

        assertNotNull(viewer);
        assertNotNull(controller);
    }
}
