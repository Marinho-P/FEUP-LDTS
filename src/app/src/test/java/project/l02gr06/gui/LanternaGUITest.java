package project.l02gr06.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import project.l02gr06.model.Position;

import static org.mockito.Mockito.*;

class LanternaGUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        screen = mock(Screen.class);
        tg = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(tg);
        gui = new LanternaGUI(screen);
    }
    @Test void drawSpace(){
        Position position = new Position(1, 1);
        String tileColor = "WHITE";
        String objectColor = "BLACK";
        char objectCharacter = 'X';
        TextGraphics textGraphicsMock = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(textGraphicsMock);
        gui.drawSpace(position, tileColor, objectColor, objectCharacter);
        verify(textGraphicsMock).setForegroundColor(TextColor.Factory.fromString(objectColor));
        verify(textGraphicsMock).setBackgroundColor(TextColor.Factory.fromString(tileColor));
        verify(textGraphicsMock).putString(position.getX(), position.getY(), "" + objectCharacter);
    }
    @Test
    void testDrawText() {
        Position position = new Position(1, 1);
        String text = "Hello";
        String color = "GREEN";
        TextGraphics textGraphicsMock = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(textGraphicsMock);
        gui.drawText(position, text, color);
        verify(textGraphicsMock).setForegroundColor(TextColor.Factory.fromString(color));
        verify(textGraphicsMock).putString(position.getX(), position.getY(), text);
    }
}
