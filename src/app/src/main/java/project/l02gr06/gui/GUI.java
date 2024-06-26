package project.l02gr06.gui;

import project.l02gr06.model.Position;

import java.io.IOException;

public interface GUI {


    ACTION getNextAction() throws IOException;




    void drawSpace(Position position, String tileColor, String objectColor, char objectCharacter);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}
