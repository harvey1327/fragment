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

            pageList.stream().map(page -> {
                List<Fragment> fragmentList = page.getFragmentList();

                while(fragmentList.size()!=1){
                    Fragment fragmentAlpha = fragmentList.get(0);
                    Merger merger = new Merger(fragmentAlpha);

                    for(Fragment fragmentBeta : fragmentList){
                        if(!fragmentAlpha.equals(fragmentBeta)){
                           MergerMeta meta = merger.processOverlap(fragmentBeta);
                           if(meta!=null){
                               merger.addToMap(meta, fragmentBeta);
                           }
                        }
                    }

                    MergerMeta maxMeta = merger.getMaxMeta();
                    Fragment maxFragment = merger.getMaxFragment();
                    Fragment newFragment = merger.mergeFragments(maxFragment, maxMeta);
                    fragmentList = replace(fragmentAlpha, maxFragment, newFragment, fragmentList);
                }
                //Assign String to Page
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

            MergerMeta metaRight = null;
            MergerMeta metaLeft = null;

            //Check Right
            for( int i = Math.min(stringAlpha.length(), stringBeta.length()); i>=2; i--){
                if(stringAlpha.endsWith(stringBeta.substring(0,i))){
                    metaRight = new MergerMeta(0,i, MergerMeta.Type.RIGHT);
                    break;
                }
            }
            //Check Left
            for( int i = 0; i<stringBeta.length(); i++){
                if(stringAlpha.startsWith(stringBeta.substring(i,stringBeta.length()))){
                    metaLeft = new MergerMeta(i,stringBeta.length(), MergerMeta.Type.LEFT);
                    break;
                }
            }

            if(metaRight!=null && metaLeft!=null){
                if(metaRight.getLength() > metaLeft.getLength()){
                   return metaRight;
                } else {
                    return metaLeft;
                }
            } else if(metaRight!=null){
                return metaRight;
            } else if(metaLeft!=null){
                return metaLeft;
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
            }
            return newFragment;
        }
    }

    public static class MergerMeta {

        public enum Type{
            LEFT,
            RIGHT
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
