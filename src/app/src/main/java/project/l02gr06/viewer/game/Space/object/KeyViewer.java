package project.l02gr06.viewer.game.Space.object;

import project.l02gr06.model.game.objects.Object;

public class KeyViewer implements  ObjectViewer {


    public String getObjectColor(Object object) {
        return "#FFFF00";
    }

    @Override
    public char getObjectCharacter(Object object) {
        return '\u00C2';

    }
}
