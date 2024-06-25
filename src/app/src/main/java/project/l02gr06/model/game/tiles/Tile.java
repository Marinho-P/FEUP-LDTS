package project.l02gr06.model.game.tiles;

import project.l02gr06.model.Position;

public abstract class Tile {
    private int durability;
    private Position position;
    private boolean isStable;
    public Tile(int x, int y, int durability,boolean isStable){
        this.durability = durability;
        this.position = new Position(x,y);
        this.isStable = isStable;
    }
    public Tile(Position position, int durability, boolean isStable){
        this.durability =durability;
        this.position = position;
        this.isStable = isStable;
    }
    public int getDurability(){
        return durability;
    }
    public void setDurability(int durability){
        this.durability = durability;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}
