package fragment.submissions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HarveyBal {

    public static void main(String args[]) throws IOException{
        try(BufferedReader in = new BufferedReader(new FileReader(args[0]))) {

            //Convert each line in BufferedReader to a Page
            List<Page> pageList = in.lines()
                    .map(Page::new)
                    .collect(Collectors.toList());

            //Stream each page to be processed
            pageList.stream().map(page -> {
                List<Fragment> fragmentList = page.getFragmentList();

                while(fragmentList.size()!=1){
                    //Get largest fragment as alpha
                    Fragment fragmentAlpha = fragmentList.stream()
                            .max(Comparator.comparing(fragment -> fragment.getInternal().length()))
                            .get();

                    //Find potential overlaps with beta against alpha
                    Merger merger = new Merger(fragmentAlpha);
                    for(Fragment fragmentBeta : fragmentList){
                        if(!fragmentAlpha.equals(fragmentBeta)){
                           MergerMeta meta = merger.processOverlap(fragmentBeta);
                           if(meta!=null){
                               merger.addToMap(meta, fragmentBeta);
                           }
                        }
                    }

                    //Get largest overlapping fragment with its meta
                    MergerMeta maxMeta = merger.getMaxMeta();
                    Fragment maxBetaFragment = merger.getMaxFragment();
                    //Merge alpha with the largest beta
                    Fragment newFragment = merger.mergeFragments(maxBetaFragment, maxMeta);
                    //Update the current list with merged alpha+beta and remove alpha, beta from list
                    fragmentList = replace(fragmentAlpha, maxBetaFragment, newFragment, fragmentList);
                }
                //Assign remaining fragment to Page
                page.setResult(fragmentList.get(0).getInternal());
                return page.getResult();
            }).forEach(System.out::println);

        }
    }

    public static List<Fragment> replace(Fragment a, Fragment b, Fragment newFragment, List<Fragment> fragmentList){
        List<Fragment> newList = fragmentList.stream()
                .map(fragment -> new Fragment(fragment.getInternal()))
                .collect(Collectors.toList());
        newList.removeIf(fragment -> fragment.getInternal().equals(a.getInternal()));
        newList.removeIf(fragment -> fragment.getInternal().equals(b.getInternal()));
        newList.add(newFragment);
        return newList;
    }

    public static class Merger {

        private Map<MergerMeta, Fragment> map = new HashMap<>();
        private Fragment fragmentAlpha;

        public Merger(Fragment fragmentAlpha){
            this.fragmentAlpha=fragmentAlpha;
        }

        public MergerMeta processOverlap(Fragment fragmentBeta){
            String stringAlpha = this.fragmentAlpha.getInternal();
            String stringBeta = fragmentBeta.getInternal();

            List<MergerMeta> metaList = new ArrayList<>();

            //Check Right of alpha
            for( int i = Math.min(stringAlpha.length(), stringBeta.length()); i>=2; i--){
                String right = stringBeta.substring(0,i);
                if(stringAlpha.endsWith(right) && right.length() >=2){
                    metaList.add(new MergerMeta(0,i, MergerMeta.Type.RIGHT));
                    break;
                }
            }
            //Check Left of alpha
            for( int i = 0; i<stringBeta.length(); i++){
                String left = stringBeta.substring(i,stringBeta.length());
                if(stringAlpha.startsWith(left) && left.length() >=2){
                    metaList.add(new MergerMeta(i,stringBeta.length(), MergerMeta.Type.LEFT));
                    break;
                }
            }
            //Check Middle of alpha
            if(stringAlpha.contains(stringBeta)){
                int beginIndex = stringAlpha.indexOf(stringBeta);
                metaList.add(new MergerMeta(beginIndex, beginIndex+stringBeta.length(), MergerMeta.Type.MIDDLE));
            }

            //Return largest overlap against alpha
            if(metaList.size()>0){
                return metaList.stream()
                        .max(Comparator.comparing(MergerMeta::getLength))
                        .get();
            } else {
                return null;
            }

        }

        public void addToMap(MergerMeta meta, Fragment beta){
            map.put(meta, beta);
        }


        public Fragment getMaxFragment(){
            MergerMeta max = getMaxMeta();
            return map.get(max);
        }

        public MergerMeta getMaxMeta(){
            return map.keySet().stream()
                    .max(Comparator.comparing(MergerMeta::getLength))
                    .get();
        }

        public Fragment mergeFragments(Fragment fragmentBeta, MergerMeta meta){
            String stringAlpha = this.fragmentAlpha.getInternal();
            String stringBeta = fragmentBeta.getInternal();
            Fragment newFragment = null;

            if(meta.getType().equals(MergerMeta.Type.RIGHT)){
                String right = stringBeta.substring(meta.getEnd(), stringBeta.length());
                newFragment = new Fragment(stringAlpha+right);
            } else if(meta.getType().equals(MergerMeta.Type.LEFT)) {
                String left = stringBeta.substring(0, meta.getStart());
                newFragment = new Fragment(left+stringAlpha);
            } else if(meta.getType().equals(MergerMeta.Type.MIDDLE)) {
                newFragment = this.fragmentAlpha;
            }
            return newFragment;
        }
    }

    //Data class to hold information regarding overlap for fragments
    public static class MergerMeta {

        public enum Type{
            LEFT,
            RIGHT,
            MIDDLE
        }

        int start;
        int end;
        int length;
        Type type;

        public MergerMeta(int start, int end, Type type){
            this.start=start;
            this.end=end;
            this.type=type;
            this.length=end-start;
        }

        public int getLength(){
            return length;
        }

        public Type getType(){
            return type;
        }

        public int getStart(){
            return start;
        }

        public int getEnd(){
            return end;
        }
    }

    //Data class to hold list of fragments, i.e line in file
    public static class Page {

        private List<Fragment> fragmentList;
        private String result="";

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

        public void setResult(String result){
            this.result=result;
        }

        public String getResult(){
            return result;
        }
    }

    //Data class to hold individual fragments
    public static class Fragment {

        private String internal;

        public Fragment(String internal) {
            this.internal=internal;
        }

        public String getInternal(){
            return internal;
        }

        public String toString(){
            return getInternal();
        }
    }

}
