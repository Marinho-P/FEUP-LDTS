package project.l02gr06.model.game.tiles;

import project.l02gr06.model.Position;

public class StableTile extends Tile{
    public StableTile(int x, int y){
        super(x,y,0,true);
    }
    public StableTile(Position position){ super(position, 0, true);}
}
