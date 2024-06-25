package project.l02gr06.viewer.game.Space.object;

import project.l02gr06.model.game.objects.Object;

public class EmptyViewer implements ObjectViewer{
    @Override

    public String getObjectColor(Object object) {
        return "#FFFFFF";
    }

    @Override
    public char getObjectCharacter(Object object) {
        return '\u00A5'; // empty character

    }
}
