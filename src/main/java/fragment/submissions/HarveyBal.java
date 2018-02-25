package fragment.submissions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HarveyBal {

    public static void main(String args[]) throws IOException{
        try(BufferedReader in = new BufferedReader(new FileReader(args[0]))) {
            in.lines()
                    .map(HarveyBal::reassemble)
                    .forEach(System.out::println);
        }
    }

    static String reassemble(String line){
        List<String> fragments = Arrays.asList(line.split(";"));
        return fragments.toString();
    }
}
