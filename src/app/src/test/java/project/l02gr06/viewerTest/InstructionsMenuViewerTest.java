package project.l02gr06.viewerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.l02gr06.gui.LanternaGUI;
import project.l02gr06.gui.MockGUI;
import project.l02gr06.model.Position;
import project.l02gr06.model.menu.InstructionsMenu;
import project.l02gr06.viewer.menu.InstructionsMenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructionsMenuViewerTest {
    private MockGUI mockGUI;
    @BeforeEach
    void setUp(){
        this.mockGUI = new MockGUI();
    }
    @Test
    void testDrawTitle() throws IOException {
        InstructionsMenuViewer instructionsMenuViewer = new InstructionsMenuViewer(new InstructionsMenu());
        instructionsMenuViewer.draw(mockGUI);
        assertTrue(mockGUI.containsText("INSTRUCTIONS"), "The title should be present");
        assertTrue(mockGUI.containsText(">Hello PathFinder"), "The first message should be present");
        assertTrue(mockGUI.textColorAtPositionEquals(new Position(8, 1), "#FFFFFF"), "The title color should be white");
        assertTrue(mockGUI.textColorAtPositionEquals(new Position(0, 4), "#FFFFFF"), "The first message color should be white");
    }
    @Test
    void testDrawArrows() throws IOException {
        InstructionsMenuViewer instructionsMenuViewer = new InstructionsMenuViewer(new InstructionsMenu());
        instructionsMenuViewer.draw(mockGUI);
        assertTrue(mockGUI.textColorAtPositionEquals(new Position(13, 6), "#32CD32"), "Arrow color should be green");
        assertTrue(mockGUI.textAtPositionEquals(new Position(13, 6), "\u2191 \u2193 \u2190 \u2192"), "Arrows should be displayed");
    }
    @Test
    void testDrawLavaInstructions() throws IOException {
        InstructionsMenuViewer instructionsMenuViewer = new InstructionsMenuViewer(new InstructionsMenu());
        instructionsMenuViewer.draw(mockGUI);
        assertTrue(mockGUI.textAtPositionEquals(new Position(1, 8), "2."), "Second point should be displayed");
        assertTrue(mockGUI.textColorAtPositionEquals(new Position(3, 8), "#FF0000"), "Red color for lava tile");
        assertTrue(mockGUI.textAtPositionEquals(new Position(5, 8), "Lava becomes UnstableTile"), "Text for point 2 should be displayed");
        assertTrue(mockGUI.textAtPositionEquals(new Position(0, 9), "and vice-versa"), "Text for point 2 continued");
    }
    @Test
    void testDrawOptions() throws IOException {
        InstructionsMenuViewer instructionsMenuViewer = new InstructionsMenuViewer(new InstructionsMenu());
        instructionsMenuViewer.draw(mockGUI);
        for (int i = 0; i < getModel().getNumberOfOptions(); i++) {
            String expectedColor = getModel().isSelected(i) ? "#FFD700" : "#FFFFFF";
            assertTrue(mockGUI.textColorAtPositionEquals(new Position(i * 25, 20), expectedColor), "Option " + i + " color");
            assertTrue(mockGUI.textAtPositionEquals(new Position(i * 25, 20), getModel().getOption(i)), "Option " + i + " text");
        }
    }
    @Test
    void testDrawPushableBlockInstructions() throws IOException {
        InstructionsMenuViewer instructionsMenuViewer = new InstructionsMenuViewer(new InstructionsMenu());
        instructionsMenuViewer.draw(mockGUI);
        assertTrue(mockGUI.textAtPositionEquals(new Position(1, 11), "3."), "Third instruction should be displayed");
        assertTrue(mockGUI.textColorAtPositionEquals(new Position(3, 11), "#FFAC1C"), "Orange color for point 3");
        assertTrue(mockGUI.textAtPositionEquals(new Position(5, 11), "Can be pushed by a player"), "Text for point 3 should be displayed");
    }
    @Test
    void testDrawLockInstructions() throws IOException {
        InstructionsMenuViewer instructionsMenuViewer = new InstructionsMenuViewer(new InstructionsMenu());
        instructionsMenuViewer.draw(mockGUI);
        assertTrue(mockGUI.textAtPositionEquals(new Position(1, 13), "4."), "Fourth instruction should be displayed");
        assertTrue(mockGUI.textColorAtPositionEquals(new Position(3, 13), "#FFAC1C"), "Orange color for point 4");
        assertTrue(mockGUI.textAtPositionEquals(new Position(5, 13), "Key required to unlock"), "Text for point 4 should be displayed");
    }
    @Test
    void testDrawUnstableTileInstruction() throws IOException {
        InstructionsMenuViewer instructionsMenuViewer = new InstructionsMenuViewer(new InstructionsMenu());
        instructionsMenuViewer.drawElements(mockGUI);
        assertTrue(mockGUI.textAtPositionEquals(new Position(1, 15), "5."), "Fifth instruction should be displayed");
        assertTrue(mockGUI.textAtPositionEquals(new Position(3, 15), "\u00A5"), "Unstable tile char for point 5");
        assertTrue(mockGUI.textColorAtPositionEquals(new Position(3, 15), "#2a2a2a"), "UnstableTile color for point 5");
        assertTrue(mockGUI.textAtPositionEquals(new Position(5, 15), "Unstable tile changes to"), "Text for point 5 should be displayed");
        assertTrue(mockGUI.textAtPositionEquals(new Position(1, 16), "lava after player leaves"), "Text continued for point 5");
    }
    @Test
    void testDrawLastInstruction() throws IOException {
        InstructionsMenuViewer instructionsMenuViewer = new InstructionsMenuViewer(new InstructionsMenu());
        instructionsMenuViewer.draw(mockGUI);
        assertTrue(mockGUI.textAtPositionEquals(new Position(1, 18), "Lastly go and have some fun!"), "Last instruction should be displayed");
    }

    private InstructionsMenu getModel() {
        return new InstructionsMenu();
    }
}
