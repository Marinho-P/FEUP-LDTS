package project.l02gr06.viewer.game.Space;

import project.l02gr06.model.game.tiles.Lava;
import project.l02gr06.model.game.tiles.StableTile;
import project.l02gr06.model.game.tiles.Tile;
import project.l02gr06.model.game.tiles.UnstableTile;
import project.l02gr06.viewer.game.Space.tile.LavaViewer;
import project.l02gr06.viewer.game.Space.tile.StableTileViewer;
import project.l02gr06.viewer.game.Space.tile.TileViewer;
import project.l02gr06.viewer.game.Space.tile.UnstableTileViewer;

public class TileViewerFactory {

    public static TileViewer getTileViewer(Tile tile) { // sub in for generics if possible
        if (tile instanceof Lava) {
            return new LavaViewer();
        }
        if (tile instanceof UnstableTile) {
            return new UnstableTileViewer();
        }
        if (tile instanceof StableTile) {
            return new StableTileViewer();
        }
        return null;
    }
}