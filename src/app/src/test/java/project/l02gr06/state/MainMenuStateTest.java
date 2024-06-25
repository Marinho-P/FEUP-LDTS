package project.l02gr06.state;

import org.junit.jupiter.api.Test;
import project.l02gr06.Game;
import project.l02gr06.controller.Controller;
import project.l02gr06.controller.menu.MainMenuController;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.menu.MainMenu;
import project.l02gr06.soundManager.SoundManager;
import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.menu.MainMenuViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class MainMenuStateTest {

    @Test
    public void testStepMethod() throws IOException {
        MainMenu mainMenu = mock(MainMenu.class);
        Game game = mock(Game.class);
        GUI gui = mock(GUI.class);
        long time = 100L;

        for (GUI.ACTION action : GUI.ACTION.values()) {
            reset(gui, mainMenu, game);

            when(gui.getNextAction()).thenReturn(action);

            MainMenuState mainMenuState = spy(new MainMenuState(mainMenu));

            MainMenuViewer mockViewer = mock(MainMenuViewer.class);
            MainMenuController mockController = mock(MainMenuController.class);
            SoundManager soundManager = mock(SoundManager.class);

            doReturn(mockViewer).when(mainMenuState).getViewer();
            doReturn(mockController).when(mainMenuState).getController();
            when(game.getSoundManager()).thenReturn(soundManager);

            mainMenuState.step(game, gui, time);
            mockController.step(game, action, time);
            mockViewer.draw(gui);

            verify(mockController).step(eq(game), eq(action), eq(time));
            verify(mockViewer).draw(eq(gui));
        }
    }

        @Test
        public void testStateInitialization () {
            MainMenu mainMenu = mock(MainMenu.class);

            MainMenuState mainMenuState = new MainMenuState(mainMenu);

            Viewer<MainMenu> viewer = mainMenuState.getViewer();
            Controller<MainMenu> controller = mainMenuState.getController();

            assertNotNull(viewer);
            assertNotNull(controller);
        }
    }

