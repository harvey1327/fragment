import fragment.submissions.HarveyBal;
import org.junit.Assert;
import org.junit.Test;

public class FragmentTest{

    @Test
    public void testFragmentReturnsInternal(){
        HarveyBal.Fragment fragment = new HarveyBal.Fragment("ABC");
        Assert.assertEquals("ABC", fragment.getInternal());
    }

}
