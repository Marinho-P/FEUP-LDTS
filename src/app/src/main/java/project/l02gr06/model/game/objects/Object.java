package project.l02gr06.model.game.objects;

import project.l02gr06.model.Position;

public abstract class Object {
    private Position position;
    public Object(int x , int y){
        this.position = new Position(x,y);
    }
    public Object(Position position){this.position = position;}
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}
