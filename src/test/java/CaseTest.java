import fragment.submissions.HarveyBal;
import org.junit.Assert;
import org.junit.Test;

public class CaseTest {

    @Test
    public void testCaseReturnsListLengthOne(){
        HarveyBal.Case Case = new HarveyBal.Case();
        Case.addCase("String1");
        Assert.assertTrue(Case.getCaseList().size()==1);
    }
}
