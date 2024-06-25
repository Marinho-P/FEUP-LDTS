package project.l02gr06.viewer.game.Space.tile;

import project.l02gr06.model.game.tiles.Tile;

public class LavaViewer implements TileViewer{

    @Override
    public String getTileColor(Tile tile){
        return "#FF5F1F";
    }
}