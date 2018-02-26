import fragment.submissions.HarveyBal;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HarveyTest {

    @Test
    public void testReplaceReturnsNewList(){
        HarveyBal.Fragment a = new HarveyBal.Fragment("ABC");
        HarveyBal.Fragment b = new HarveyBal.Fragment("ZAB");
        List<HarveyBal.Fragment> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        HarveyBal.Fragment newFragment = new HarveyBal.Fragment("ZABC");
        List<HarveyBal.Fragment> newList = HarveyBal.replace(a, b, newFragment, list);
        Assert.assertTrue(newList.size()==1);
        Assert.assertTrue(newList.get(0).equals(newFragment));
    }
}
