package fragment.submissions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HarveyBal {

    public static void main(String args[]) throws IOException{
        try(BufferedReader in = new BufferedReader(new FileReader(args[0]))) {
//            in.lines()
//                    .map(HarveyBal::reassemble)
//                    .forEach(System.out::println);
        }
    }

//    static String reassemble(String line){
//        List<String> fragments = Arrays.asList(line.split(";"));
//        String result = findOverlap(fragments);
//        return result;
//    }

//    static String findOverlap(List<String> fragments){
//        for(int i = 0; i<fragments.size(); i++){
//            String fragmentAlpha = fragments.get(i);
//            for(int j = 0; j<fragments.size(); j++){
//                String fragmentBeta = fragments.get(i);
//                if(i!=j){
//
//                }
//            }
//        }
//    }

    public class Fragment {

        private String internal;

        public Fragment(String internal) {
            this.internal=internal;
        }

        public String getInternal(){
            return internal;
        }

        public String getOverlap(Fragment fragmentBeta){
            String resultRight = "";
            String resultLeft = "";
            String stringPrime = this.getInternal();
            String stringBeta = fragmentBeta.getInternal();
            //Check Right
            for( int i = Math.min(stringPrime.length(), stringBeta.length()); ; i--){
                if(stringPrime.endsWith(stringBeta.substring(0,i))){
                    resultRight = stringBeta.substring(0,i);
                    break;
                }
            }
            //Check Left
            for( int i = 0; i<stringBeta.length(); i++){
                if(stringPrime.startsWith(stringBeta.substring(i,stringBeta.length()))){
                    resultLeft = stringBeta.substring(i,stringBeta.length());
                    break;
                }
            }
            if(resultRight.length() > resultLeft.length()){
                return resultRight;
            } else {
                return resultLeft;
            }
        }
    }

}
