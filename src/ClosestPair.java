import java.util.Arrays;

public class ClosestPair {
    private House[] housePair;
    private double distance;
    public ClosestPair(House h1,House h2){
        housePair=new House[2];
        housePair[0]=h1;
        housePair[1]=h2;
        distance=h1.distance(h2);
    }

    public House[] getHousePair() {
        return housePair;
    }

    public double getDistance() {
        return distance;
    }

    public void print(){
        String string="";
        if(housePair[0].getIndex()<housePair[1].getIndex())
            string=housePair[0].getIndex()+" "+housePair[1].getIndex();
        else
            string=housePair[1].getIndex()+" "+housePair[0].getIndex();
        System.out.println(string);
        System.out.printf("%.4f",distance);
    }

}
