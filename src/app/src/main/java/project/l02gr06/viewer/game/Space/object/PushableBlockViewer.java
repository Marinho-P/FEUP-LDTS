package project.l02gr06.viewer.game.Space.object;

import project.l02gr06.model.game.objects.Object;

public class PushableBlockViewer implements ObjectViewer {
    @Override

    public String getObjectColor(Object object) {
        return "#FFAC1C";
    }

    @Override
    public char getObjectCharacter(Object object) {
        return '\u00C4';

    }

}
