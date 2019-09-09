package collections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        boolean foundMatching=true;
if(magazine.length<note.length){
    System.out.println("No");
}else{
    Map<String, Long> magazineWords=new HashMap<>();
    magazineWords=Arrays.asList(magazine).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    for(long i=0;i<note.length;i++){
        if(magazineWords.get(note[(int)i])==null || magazineWords.get(note[(int)i])<=0){
            System.out.println("No");
            foundMatching=false;
            break;

        }else{
            magazineWords.put(note[(int)i],magazineWords.get(note[(int)i])-1);
        }

        if(foundMatching){
            System.out.println("Yes");
        }
    }



}

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
