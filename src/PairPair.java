public class PairPair {
    private HousePair closestPair;
    private HousePair secondClosestPair;

    public PairPair(HousePair closestPair, HousePair secondClosestPair) {
        this.closestPair = closestPair;
        this.secondClosestPair = secondClosestPair;
    }

    public PairPair(HousePair closestPair) {
        this.closestPair = closestPair;
        this.secondClosestPair=new HousePair();
    }

    public PairPair(){
        this.closestPair=new HousePair();
        this.secondClosestPair=new HousePair();
    }

    public HousePair getClosestPair() {
        return closestPair;
    }

    public HousePair getSecondClosestPair() {
        return secondClosestPair;
    }

    public void setClosestPair(HousePair closestPair) {
        this.closestPair = closestPair;
    }

    public void setSecondClosestPair(HousePair secondClosestPair) {
        this.secondClosestPair = secondClosestPair;
    }
}
