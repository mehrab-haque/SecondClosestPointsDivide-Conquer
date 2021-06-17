import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Logic {
    private List<House> xSortedHouseList;
    private List<House> ySortedHouseList;

    public Logic(List<House> houseList){
        //nlogn
        houseList.sort(new Comparator<House>() {
            @Override
            public int compare(House o1, House o2) {
                return o1.getX()-o2.getX();
            }
        });
        xSortedHouseList=new ArrayList<>(houseList);
        //nlogn
        houseList.sort(new Comparator<House>() {
            @Override
            public int compare(House o1, House o2) {
                return o1.getY()-o2.getY();
            }
        });
        ySortedHouseList=new ArrayList<>(houseList);
    }


    private PairPair getCloseOfThree(House h1, House h2, House h3){
        List<HousePair> housePairs=new ArrayList<>();
        housePairs.add(new HousePair(h1,h2));
        housePairs.add(new HousePair(h2,h3));
        housePairs.add(new HousePair(h3,h1));
        housePairs.sort(new Comparator<HousePair>() {
            @Override
            public int compare(HousePair o1, HousePair o2) {
                return (int)(o1.getDistance()-o2.getDistance());
            }
        });
        return new PairPair(housePairs.get(0),housePairs.get(1));
    }

    //cost########T(n)########
    public PairPair getClosePairs(List<House> xSortedList,List<House> ySortedList){

        //Base case
        //cost########1########
        if(xSortedList.size()==2)
            return new PairPair(new HousePair(xSortedList.get(0),xSortedList.get(1)));
        if(xSortedList.size()==3)
            return getCloseOfThree(xSortedList.get(0),xSortedList.get(1),xSortedList.get(2));

        //Divide
        //cost########2T(n/2)########
        List<House> leftXSorted=xSortedList.subList(0,xSortedList.size()/2);
        List<House> rightXSorted=xSortedList.subList(xSortedList.size()/2,xSortedList.size());
        List<House> leftYSorted=new ArrayList<>();
        List<House> rightYSorted=new ArrayList<>();
        for(int i=0;i<ySortedList.size();i++) {
            if (ySortedList.get(i).getX() <= leftXSorted.get(leftXSorted.size() - 1).getX())
                leftYSorted.add(ySortedList.get(i));
            else
                rightYSorted.add(ySortedList.get(i));
        }


        PairPair leftPairs=getClosePairs(leftXSorted,leftYSorted);
        PairPair rightPairs=getClosePairs(rightXSorted,rightYSorted);

        //cost########1########
        List<HousePair> housePairs=new ArrayList<>();
        housePairs.add(leftPairs.getClosestPair());
        housePairs.add(leftPairs.getSecondClosestPair());
        housePairs.add(rightPairs.getClosestPair());
        housePairs.add(rightPairs.getSecondClosestPair());
        housePairs.sort(new Comparator<HousePair>() {
            @Override
            public int compare(HousePair o1, HousePair o2) {
                return (int)(o1.getDistance()-o2.getDistance());
            }
        });
        PairPair pairPair=new PairPair(housePairs.get(0),housePairs.get(1));

        //Combine
        //Calculating x-window
        //cost########n########
        List<House> xWindowList=new ArrayList<>();
        for(House house:ySortedList)
            if(Math.abs(house.getX()-rightXSorted.get(0).getX())<=pairPair.getSecondClosestPair().getDistance())
                xWindowList.add(house);

        //cost########n########
        for(int i=0;i<xWindowList.size()-1;i++)
            for(int j=1;j<=7;j++)
                if(i+j< xWindowList.size()) {
                    if((leftXSorted.contains(xWindowList.get(i)) && rightXSorted.contains(xWindowList.get(i+j)) || (leftXSorted.contains(xWindowList.get(i+j)) && rightXSorted.contains(xWindowList.get(i))))){
                        if (xWindowList.get(i).distance(xWindowList.get(i + j)) < pairPair.getClosestPair().getDistance()) {
                            pairPair.setSecondClosestPair(pairPair.getClosestPair());
                            pairPair.setClosestPair(new HousePair(xWindowList.get(i), xWindowList.get(i + j)));
                        } else if (xWindowList.get(i).distance(xWindowList.get(i + j)) < pairPair.getSecondClosestPair().getDistance())
                            pairPair.setSecondClosestPair(new HousePair(xWindowList.get(i), xWindowList.get(i + j)));
                    }
                }

        //########Recurrence Relation########
        //T(n)=2T(n/2)+n+1

        return pairPair;
    }

    public HousePair callMe(){
        return getClosePairs(xSortedHouseList,ySortedHouseList).getSecondClosestPair();
    }
}
