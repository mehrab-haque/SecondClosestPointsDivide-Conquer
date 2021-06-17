public class House {
    private int x;
    private int y;
    private int index;
    public House(int index,int x,int y){
        this.index=index;
        this.x=x;
        this.y=y;
    }
    public double distance(House h){
        return Math.sqrt((x-h.x)*(x-h.x)+(y-h.y)*(y-h.y));
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getIndex(){
        return index;
    }
}
