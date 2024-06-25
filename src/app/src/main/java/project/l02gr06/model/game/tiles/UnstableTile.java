package project.l02gr06.model.game.tiles;

import project.l02gr06.model.Position;

public class UnstableTile extends Tile{
    public UnstableTile(int x, int y,int durability){
        super(x,y,durability ,false);
    }
    public UnstableTile(Position position, int durability) { super(position, durability,false);}
    public void decreaseDurability(){
        setDurability(getDurability()-1);
    }
}
