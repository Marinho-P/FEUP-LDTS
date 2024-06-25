package project.l02gr06.viewer.game;

import project.l02gr06.viewer.game.Space.SpaceViewer;
import project.l02gr06.gui.GUI;
import project.l02gr06.viewer.Viewer;
import project.l02gr06.model.Position;
import project.l02gr06.model.game.level.Level;
import project.l02gr06.model.game.objects.Object;
import project.l02gr06.model.game.tiles.Tile;


public class GameViewer extends Viewer<Level> {
    public GameViewer(Level level) {
        super(level);
    }

    @Override
    public void drawElements(GUI gui) {
        SpaceViewer spaceViewer = new SpaceViewer();
        for (int i = 0; i < getModel().getWidth(); i++) {
            for (int k = 0; k < getModel().getHeight(); k++) {
                Position position = new Position(i, k);
                Tile tile = getModel().fetchTile(position);
                Object object = getModel().fetchObject(position);
                spaceViewer.draw(gui, tile, object);
            }

        }
        gui.drawText(new Position(0, 0), "Level:" + getModel().getLevel(), "#FFFFFF");
        gui.drawText(new Position(10,0),"Number of keys:" + getModel().getPlayer().getKeys(),"#FFFFFF");

    }
}

