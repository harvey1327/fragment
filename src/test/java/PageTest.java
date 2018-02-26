import fragment.submissions.HarveyBal;
import org.junit.Assert;
import org.junit.Test;

public class PageTest {

    @Test
    public void testPageReturnsListLengthOne(){
        HarveyBal.Page page = new HarveyBal.Page();
        page.addFragment(new HarveyBal.Fragment("String"));
        Assert.assertTrue(page.getFragmentList().size()==1);
    }
}
