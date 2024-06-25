package project.l02gr06.gui;

import project.l02gr06.model.Position;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MockGUI implements GUI {
    private Map<Position, String> textMap = new HashMap<>();
    private Map<Position, String> textColorMap = new HashMap<>();

    @Override
    public ACTION getNextAction() throws IOException {
        return null;
    }

    @Override
    public void drawSpace(Position position, String tileColor, String objectColor, char objectCharacter) {
        textMap.put(position, ""+ objectCharacter);
        textColorMap.put(position, tileColor);
    }

    @Override
    public void drawText(Position position, String text, String color) {
        textMap.put(position, text);
        textColorMap.put(position, color);
    }

    @Override
    public void clear() {
        textMap.clear();
        textColorMap.clear();
    }

    @Override
    public void refresh() throws IOException {

    }

    @Override
    public void close() throws IOException {

    }

    public boolean containsText(String text) {
        return textMap.values().contains(text);
    }

    public boolean textAtPositionEquals(Position position, String expectedText) {
        return expectedText.equals(textMap.get(position));
    }

    public boolean textColorAtPositionEquals(Position position, String expectedColor) {
        return expectedColor.equals(textColorMap.get(position));
    }

}
