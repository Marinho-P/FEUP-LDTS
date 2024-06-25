package project.l02gr06.viewerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.l02gr06.gui.MockGUI;
import project.l02gr06.model.Position;
import project.l02gr06.model.menu.NextLevelMenu;
import project.l02gr06.viewer.menu.NextLevelMenuViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class NextLevelMenuViewerTest {
    private MockGUI mockGUI;

    @BeforeEach
    void setUp() {
        this.mockGUI = new MockGUI();
    }

    @Test
    void testDrawElementsForAllLevels() throws IOException {
        for (int level = 1; level <= 12; level++) {
            NextLevelMenu nextLevelMenu = new NextLevelMenu(level);
            NextLevelMenuViewer nextLevelMenuViewer = new NextLevelMenuViewer(nextLevelMenu);

            nextLevelMenuViewer.draw(mockGUI);

            String congratsMessage = nextLevelMenu.getCongratsMessage();
            assertTrue(mockGUI.textAtPositionEquals(new Position(5, 5), congratsMessage), "Congrats message should be present");
            assertTrue(mockGUI.textColorAtPositionEquals(new Position(5, 5), "#FFFFFF"), "Congrats message color should be white");

            for (int i = 0; i < nextLevelMenu.getNumberOfOptions(); i++) {
                String expectedColor = nextLevelMenu.isSelected(i) ? "#FFD700" : "#FFFFFF";
                assertTrue(mockGUI.textColorAtPositionEquals(new Position(5, 7 + i), expectedColor), "Option " + i + " color");
                assertTrue(mockGUI.textAtPositionEquals(new Position(5, 7 + i), nextLevelMenu.getOption(i)), "Option " + i + " text");
            }
            mockGUI.clear();
        }
    }

}
