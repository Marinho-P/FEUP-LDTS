package project.l02gr06.viewerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.l02gr06.gui.MockGUI;
import project.l02gr06.model.Position;
import project.l02gr06.model.menu.MainMenu;
import project.l02gr06.viewer.menu.MainMenuViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MainMenuViewerTest {
    private MockGUI mockGUI;
    private MainMenu mainMenu;

    @BeforeEach
    void setUp() {
        this.mockGUI = new MockGUI();
        this.mainMenu = new MainMenu();
    }

    @Test
    void testDrawElements() throws IOException {
        MainMenuViewer mainMenuViewer = new MainMenuViewer(mainMenu);
        mainMenuViewer.draw(mockGUI);

        assertTrue(mockGUI.textAtPositionEquals(new Position(5, 5), "Welcome to"), "Welcome text should be present");
        assertTrue(mockGUI.textColorAtPositionEquals(new Position(5, 5), "#FFFFFF"), "Welcome text color should be white");

        assertTrue(mockGUI.textAtPositionEquals(new Position(6, 6), "Splunk Adventure"), "Adventure text should be present");
        assertTrue(mockGUI.textColorAtPositionEquals(new Position(6, 6), "#FFFFFF"), "Adventure text color should be white");

        for (int i = 0; i < mainMenu.getNumberOfOptions(); i++) {
            String expectedColor = mainMenu.isSelected(i) ? "#FFD700" : "#FFFFFF";
            assertTrue(mockGUI.textColorAtPositionEquals(new Position(5, 8 + i), expectedColor), "Option " + i + " color");
            assertTrue(mockGUI.textAtPositionEquals(new Position(5, 8 + i), mainMenu.getOption(i)), "Option " + i + " text");
        }
    }
}