package project.l02gr06.state;

import org.junit.jupiter.api.Test;
import project.l02gr06.Game;
import project.l02gr06.controller.Controller;
import project.l02gr06.controller.menu.InstructionsMenuController;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.menu.InstructionsMenu;
import project.l02gr06.soundManager.SoundManager;
import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.menu.InstructionsMenuViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class InstructionsMenuStateTest {

    @Test
    public void testStepMethod() throws IOException {
        InstructionsMenu instructionsMenu = mock(InstructionsMenu.class);
        Game game = mock(Game.class);
        GUI gui = mock(GUI.class);
        long time = 100L;

        for (GUI.ACTION action : GUI.ACTION.values()) {
            reset(gui, instructionsMenu, game);

            when(gui.getNextAction()).thenReturn(action);

            InstructionsMenuState instructionsMenuState = spy(new InstructionsMenuState(instructionsMenu));

            InstructionsMenuViewer mockViewer = mock(InstructionsMenuViewer.class);
            InstructionsMenuController mockController = mock(InstructionsMenuController.class);
            SoundManager soundManager = mock(SoundManager.class);

            doReturn(mockViewer).when(instructionsMenuState).getViewer();
            doReturn(mockController).when(instructionsMenuState).getController();
            when(game.getSoundManager()).thenReturn(soundManager);


            instructionsMenuState.step(game, gui, time);
            mockController.step(game, action, time);
            mockViewer.draw(gui);

            verify(mockController).step(eq(game), eq(action), eq(time));
            verify(mockViewer).draw(eq(gui));
        }
    }
    @Test
    public void testStateInitialization() {
        InstructionsMenu instructionsMenu = mock(InstructionsMenu.class);

        InstructionsMenuState instructionsMenuState = new InstructionsMenuState(instructionsMenu);

        Viewer<InstructionsMenu> viewer = instructionsMenuState.getViewer();
        Controller<InstructionsMenu> controller = instructionsMenuState.getController();

        assertNotNull(viewer);
        assertNotNull(controller);
    }
}
