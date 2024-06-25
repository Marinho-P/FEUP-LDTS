package project.l02gr06.model.game.objects;

public class Player extends Object{
    private int keys;
    public Player(int x, int y){
        super(x, y);
        keys = 0;
    }
    public void collectKey(){
        keys ++;
    }
    public void useKey(){keys--;}
    public boolean hasKey(){
        return keys > 0;
    }

    public int getKeys() {
        return keys;
    }
}
