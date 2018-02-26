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

            //Convert each line in BufferedReader to a Page
            List<Page> pageList = in.lines()
                    .map(Page::new)
                    .collect(Collectors.toList());

            //From Fragments in page, Loop for overlap, if true merge together and replace into list?


            System.out.println(pageList);
        }
    }

    public static class Page {

        private List<Fragment> fragmentList;

        public Page(String line){
            fragmentList = Arrays.stream(line.split(";"))
                    .map(Fragment::new)
                    .collect(Collectors.toList());
        }

        public List<Fragment> getFragmentList(){
            return fragmentList;
        }

        public String toString(){
            return getFragmentList().toString();
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
            for( int i = Math.min(stringPrime.length(), stringBeta.length()); i>=0; i--){
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
