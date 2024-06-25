package project.l02gr06.viewer.game.Space.tile;

import project.l02gr06.model.game.tiles.Tile;

public class UnstableTileViewer implements  TileViewer{


    @Override
    public String getTileColor(Tile tile){
        String color;
        if(tile.getDurability()== 1){
            color = "#2a2a2a";
        }
        else{
            color = "#bebebe";
        }
        return color;
    }

}