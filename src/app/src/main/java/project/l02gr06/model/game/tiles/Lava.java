package project.l02gr06.model.game.tiles;

import project.l02gr06.model.Position;

public class Lava extends Tile{
    public Lava(int x, int y){
        super(x,y,0,false);
    }
    public Lava(Position position){ super(position, 0, false);}
}
