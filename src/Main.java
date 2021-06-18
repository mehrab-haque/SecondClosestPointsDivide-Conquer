import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<House> houseList=new ArrayList<>();
        Scanner scanner=new Scanner(new File("input.txt"));
        int n=scanner.nextInt();
        for(int i=0;i<n;i++){
            int x=scanner.nextInt();
            int y=scanner.nextInt();
            houseList.add(new House(i,x,y));
        }
        Logic logic=new Logic(houseList);
        logic.getSecondClosestPairs().print();
    }
}
