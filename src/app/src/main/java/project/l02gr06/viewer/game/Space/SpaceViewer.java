package project.l02gr06.viewer.game.Space;

import project.l02gr06.viewer.game.Space.object.*;
import project.l02gr06.viewer.game.Space.tile.TileViewer;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.game.objects.Object;
import project.l02gr06.model.game.tiles.Tile;

public class SpaceViewer {


    public void draw(GUI gui, Tile tile, Object object) {
        TileViewer tileViewer = TileViewerFactory.getTileViewer(tile);
        ObjectViewer objectViewer = ObjectViewerFactory.getObjectViewer(object);
        assert objectViewer != null;
        gui.drawSpace(tile.getPosition(), tileViewer.getTileColor(tile), objectViewer.getObjectColor(object), objectViewer.getObjectCharacter(object));
    }
}



