import fragment.submissions.HarveyBal;
import org.junit.Assert;
import org.junit.Test;

public class HarveyBalTest{

    @Test
    public void testFragmentGetOverlapReturnsStringFromRight(){
        HarveyBal hb = new HarveyBal();
        HarveyBal.Fragment fragmentPrime = hb.new Fragment("ABC");
        HarveyBal.Fragment fragmentBeta = hb.new Fragment("BCZ");
        Assert.assertEquals("BC", fragmentPrime.getOverlap(fragmentBeta));
    }

    @Test
    public void testFragmentGetOverlapReturnsStringFromLeft(){
        HarveyBal hb = new HarveyBal();
        HarveyBal.Fragment fragmentPrime = hb.new Fragment("ABC");
        HarveyBal.Fragment fragmentBeta = hb.new Fragment("ZAB");
        Assert.assertEquals("AB", fragmentPrime.getOverlap(fragmentBeta));
    }

    @Test
    public void testFragmentGetOverlapReturnsStringFromLeftLarger(){
        HarveyBal hb = new HarveyBal();
        HarveyBal.Fragment fragmentPrime = hb.new Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta = hb.new Fragment("EFABC");
        Assert.assertEquals("ABC", fragmentPrime.getOverlap(fragmentBeta));
    }

    @Test
    public void testFragmentGetOverlapReturnsStringFromRightLarger(){
        HarveyBal hb = new HarveyBal();
        HarveyBal.Fragment fragmentPrime = hb.new Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta = hb.new Fragment("DEFAB");
        Assert.assertEquals("DEF", fragmentPrime.getOverlap(fragmentBeta));
    }

    @Test
    public void testFragmentGetOverlapReturnsEmptyBasic(){
        HarveyBal hb = new HarveyBal();
        HarveyBal.Fragment fragmentPrime = hb.new Fragment("ABCD");
        HarveyBal.Fragment fragmentBeta = hb.new Fragment("ZBCY");
        Assert.assertEquals("", fragmentPrime.getOverlap(fragmentBeta));
    }

    @Test
    public void testFragmentGetOverlapReturnsEmptyMedium(){
        HarveyBal hb = new HarveyBal();
        HarveyBal.Fragment fragmentPrime = hb.new Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta = hb.new Fragment("ZEFABZ");
        Assert.assertEquals("", fragmentPrime.getOverlap(fragmentBeta));
    }

}
