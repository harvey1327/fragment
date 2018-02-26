import fragment.submissions.HarveyBal;
import org.junit.Assert;
import org.junit.Test;

public class FragmentTest{

    @Test
    public void testFragmentGetOverlapReturnsStringFromRight(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABC");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("BCZ");
        Assert.assertEquals("BC", fragmentPrime.getOverlap(fragmentBeta));
    }

    @Test
    public void testFragmentGetOverlapReturnsStringFromLeft(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABC");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("ZAB");
        Assert.assertEquals("AB", fragmentPrime.getOverlap(fragmentBeta));
    }

    @Test
    public void testFragmentGetOverlapReturnsStringFromLeftLarger(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("EFABC");
        Assert.assertEquals("ABC", fragmentPrime.getOverlap(fragmentBeta));
    }

    @Test
    public void testFragmentGetOverlapReturnsStringFromRightLarger(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("DEFAB");
        Assert.assertEquals("DEF", fragmentPrime.getOverlap(fragmentBeta));
    }

    @Test
    public void testFragmentGetOverlapReturnsEmptyBasic(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCD");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("ZBCY");
        Assert.assertEquals("", fragmentPrime.getOverlap(fragmentBeta));
    }

    @Test
    public void testFragmentGetOverlapReturnsEmptyMedium(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("ZEFABZ");
        Assert.assertEquals("", fragmentPrime.getOverlap(fragmentBeta));
    }

}
