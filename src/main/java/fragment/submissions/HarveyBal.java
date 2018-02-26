package fragment.submissions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HarveyBal {

    public static void main(String args[]) throws IOException{
        try(BufferedReader in = new BufferedReader(new FileReader(args[0]))) {
            List<Fragment> fragmentList = in.lines()
                    .map(line -> new Fragment(line))
                    .collect(Collectors.toList());
            System.out.println(fragmentList);
        }
    }

//    public static List<Fragment> convertToFragment(String line){
//        List<String> fragments = Arrays.asList(line.split(";"));
//        List<Fragment> fragmentList = new ArrayList<>();
//        for(String fragmentString: fragments){
//            Fragment fragment = new Fragment(fragmentString);
//            fragmentList.add(fragment);
//        }
//        return fragmentList;
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

    public static class Case {

        private List<String> caseList = new ArrayList<>();

        public List<String> getCaseList(){
            return caseList;
        }

        public void addCase(String s){
            caseList.add(s);
        }
    }

    public static class Page {

        private List<Fragment> fragmentList = new ArrayList<>();

        public List<Fragment> getFragmentList(){
            return fragmentList;
        }

        public void addFragment(Fragment fragment){
            fragmentList.add(fragment);
        }
    }

    public static class Fragment {

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

        public String toString(){
            return getInternal();
        }
    }

}
