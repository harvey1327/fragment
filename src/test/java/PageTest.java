import fragment.submissions.HarveyBal;
import org.junit.Assert;
import org.junit.Test;

public class PageTest {

    @Test
    public void testPageReturnsListLengthThree(){
        HarveyBal.Page page = new HarveyBal.Page("String1; String2; String3");
        Assert.assertTrue(page.getFragmentList().size()==3);
    }
}
