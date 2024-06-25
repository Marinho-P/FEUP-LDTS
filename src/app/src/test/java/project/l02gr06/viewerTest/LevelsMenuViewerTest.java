package project.l02gr06.viewerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.l02gr06.gui.MockGUI;
import project.l02gr06.model.Position;
import project.l02gr06.model.menu.LevelsMenu;
import project.l02gr06.viewer.menu.LevelsMenuViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LevelsMenuViewerTest {
    private MockGUI mockGUI;

    @BeforeEach
    void setUp() {
        this.mockGUI = new MockGUI();
    }
    @Test
    void testDrawElements() throws IOException {
        LevelsMenu levelsMenu = new LevelsMenu();
        LevelsMenuViewer levelsMenuViewer = new LevelsMenuViewer(levelsMenu);
        levelsMenuViewer.draw(mockGUI);

        assertTrue(mockGUI.textAtPositionEquals(new Position(2, 1), "Levels Menu"), "Title should be present");
        assertTrue(mockGUI.textColorAtPositionEquals(new Position(2, 1), "#FFFFFF"), "Title color should be white");

        assertTrue(mockGUI.textAtPositionEquals(new Position(2, 3), "Gems collected:" + levelsMenu.getNumberOfGems()), "Gems collected line should be present");
        assertTrue(mockGUI.textColorAtPositionEquals(new Position(2, 3), "#FFFFFF"), "Gems collected line color should be white");

        for (int i = 0; i < levelsMenu.getNumberOfOptions(); i++) {
            String expectedColor = levelsMenuViewer.getColor(i);
            assertTrue(mockGUI.textColorAtPositionEquals(new Position(2, 5 + i), expectedColor), "Option " + i + " color");
            assertTrue(mockGUI.textAtPositionEquals(new Position(2, 5 + i), levelsMenu.getOption(i)), "Option " + i + " text");
        }
    }
}
