package project.l02gr06.viewerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.l02gr06.gui.MockGUI;
import project.l02gr06.model.Position;
import project.l02gr06.model.menu.RetryMenu;
import project.l02gr06.viewer.menu.RetryMenuViewer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RetryMenuViewerTest {
    private MockGUI mockGUI;

    @BeforeEach
    void setUp() {
        this.mockGUI = new MockGUI();
    }

    @Test
    void testDrawElements() throws IOException {
        for (int level = 1; level <= 12; level++) {
            RetryMenu retryMenu = new RetryMenu(level);
            RetryMenuViewer retryMenuViewer = new RetryMenuViewer(retryMenu);
            retryMenuViewer.draw(mockGUI);

            String deathMessage = retryMenu.getDeathMessage();
            assertTrue(mockGUI.textAtPositionEquals(new Position(5, 5), deathMessage), "Death message should be present");
            assertTrue(mockGUI.textColorAtPositionEquals(new Position(5, 5), "#FFFFFF"), "Death message color should be white");

            for (int i = 0; i < retryMenu.getNumberOfOptions(); i++) {
                String expectedColor = retryMenu.isSelected(i) ? "#FFD700" : "#FFFFFF";
                assertTrue(mockGUI.textColorAtPositionEquals(new Position(5, 7 + i), expectedColor), "Option " + i + " color");
                assertTrue(mockGUI.textAtPositionEquals(new Position(5, 7 + i), retryMenu.getOption(i)), "Option " + i + " text");
            }
        }
    }

}