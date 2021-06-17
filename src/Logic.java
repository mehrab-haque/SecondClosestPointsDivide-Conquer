import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Logic {
    private List<House> houseList;

    public Logic(List<House> houseList){
        this.houseList=houseList;
    }

    private ClosestPair closestOfThree(House h1, House h2, House h3){
        ClosestPair p1=new ClosestPair(h1,h2);
        ClosestPair p2=new ClosestPair(h2,h3);
        ClosestPair p3=new ClosestPair(h3,h1);
        if(p1.getDistance()<p2.getDistance()){
            if(p1.getDistance()<p3.getDistance())
                return p1;
            else
                return p3;
        }else{
            if(p2.getDistance()<p3.getDistance())
                return  p2;
            else
                return p3;
        }
    }

    public ClosestPair getClosestPair(List<House> houseList){
        //Base case
        if(houseList.size()==2)
            return new ClosestPair(houseList.get(0),houseList.get(1));
        if(houseList.size()==3)
            return closestOfThree(houseList.get(0),houseList.get(1),houseList.get(2));

        //Divide
        List<House> leftHouses=houseList.subList(0,houseList.size()/2);
        List<House> rightHouses=houseList.subList(houseList.size()/2,houseList.size());
        ClosestPair p1=getClosestPair(leftHouses);
        ClosestPair p2=getClosestPair(rightHouses);
        ClosestPair cP=p1.getDistance()<p2.getDistance()?p1:p2;

        //Combine
        //Calculating x-window
        List<House> xWindowList=new ArrayList<>();
        double xPivot=(rightHouses.get(0).getX()-leftHouses.get(leftHouses.size()-1).getX())/2;
        for(int i=leftHouses.size()-1;i>=0;i--) {
            if (Math.abs(leftHouses.get(i).getX() - xPivot) <= cP.getDistance())
                xWindowList.add(leftHouses.get(i));
            else
                break;
        }
        for(int i=0;i<rightHouses.size();i++){
            if (Math.abs(rightHouses.get(i).getX() - xPivot) <= cP.getDistance())
                xWindowList.add(rightHouses.get(i));
            else
                break;
        }
        xWindowList.sort(new Comparator<House>() {
            @Override
            public int compare(House o1, House o2) {
                return o1.getY()-o2.getY();
            }
        });
        for(int i=0;i<xWindowList.size()-1;i++)
            for(int j=1;j<=7;j++)
                if(i+j< xWindowList.size() && xWindowList.get(i).distance(xWindowList.get(i+j))<cP.getDistance())
                    cP=new ClosestPair(xWindowList.get(i),xWindowList.get(i+j));
        return cP;
    }

    public ClosestPair findClosestHousePair(){
        houseList.sort(new Comparator<House>() {
            @Override
            public int compare(House o1, House o2) {
                return o1.getX()-o2.getX();
            }
        });
        return getClosestPair(houseList);
    }

    public ClosestPair findSecondClosestHousePair(){
        ClosestPair cP1=findClosestHousePair();
        houseList.remove(cP1.getHousePair()[0]);
        ClosestPair cP2=findClosestHousePair();
        houseList.add(cP1.getHousePair()[0]);
        houseList.remove(cP1.getHousePair()[1]);
        ClosestPair cP3=findClosestHousePair();
        houseList.add(cP1.getHousePair()[1]);
        if(cP2.getDistance()<cP3.getDistance())
            return cP2;
        return cP3;
    }
}
