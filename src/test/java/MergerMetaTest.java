import fragment.submissions.HarveyBal;
import org.junit.Assert;
import org.junit.Test;

public class MergerMetaTest {

    @Test
    public void testMergerMetaReturnsLeft(){
        HarveyBal.MergerMeta meta = new HarveyBal.MergerMeta(0,1, HarveyBal.MergerMeta.Type.LEFT);
        Assert.assertTrue(meta.getType().equals(HarveyBal.MergerMeta.Type.LEFT));
    }

    @Test
    public void testMergerMetaReturnsRight(){
        HarveyBal.MergerMeta meta = new HarveyBal.MergerMeta(0,1, HarveyBal.MergerMeta.Type.RIGHT);
        Assert.assertTrue(meta.getType().equals(HarveyBal.MergerMeta.Type.RIGHT));
    }

    @Test
    public void testMergerMetaReturnsConstructorInput(){
        HarveyBal.MergerMeta meta = new HarveyBal.MergerMeta(0,1, HarveyBal.MergerMeta.Type.LEFT);
        Assert.assertTrue(meta.getType().equals(HarveyBal.MergerMeta.Type.LEFT));
        Assert.assertTrue(meta.getStart()==0);
        Assert.assertTrue(meta.getEnd()==1);
        Assert.assertTrue(meta.getLength()==1);
    }

}
