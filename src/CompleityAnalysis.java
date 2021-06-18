import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompleityAnalysis {

    public static void main(String[] args) throws FileNotFoundException {
        String[] files={
                "input_test 1.txt",
                "input_test 2.txt",
                "input_test 6.txt",
                "input_test 9.txt",
                "input_test 11.txt",
                "input_test 12.txt",
                "input_test 15.txt",
                "input_test 16.txt",
        };
        int iterations=10;
        for(String file:files){
            List<House> houseList=new ArrayList<>();
            Scanner scanner=new Scanner(new File(file));
            int n=scanner.nextInt();
            for(int i=0;i<n;i++){
                int x=scanner.nextInt();
                int y=scanner.nextInt();
                houseList.add(new House(i,x,y));
            }
            Logic logic=new Logic(houseList);
            HousePair pair = null;
            double start=System.currentTimeMillis();
            for(int i=0;i<iterations;i++)
                pair=logic.getSecondClosestPairs();
            double end=System.currentTimeMillis();
            System.out.println("########"+file+"########");
            pair.print();
            System.out.println("n="+n);
            double avgT=(end-start)/iterations;
            System.out.println("Average time : "+avgT+"mS");
            System.out.println("Constant, c="+avgT/(n*Math.log(n)/Math.log(2)));
        }
    }
}
