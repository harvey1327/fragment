import fragment.submissions.HarveyBal;
import org.junit.Assert;
import org.junit.Test;

public class MergerTest {

    @Test
    public void testMergerProcessOverlapReturnsMetaFromRight(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABC");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("BCZ");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta actual = merger.processOverlap(fragmentBeta);
        HarveyBal.MergerMeta expected = new HarveyBal.MergerMeta(0,2, HarveyBal.MergerMeta.Type.RIGHT);
        Assert.assertEquals(actual.getStart(),expected.getStart());
        Assert.assertEquals(actual.getEnd(),expected.getEnd());
        Assert.assertEquals(actual.getType(),expected.getType());
        Assert.assertEquals(actual.getLength(),expected.getLength());
    }

    @Test
    public void testMergerProcessOverlapReturnsStringFromLeft(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABC");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("ZAB");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta actual = merger.processOverlap(fragmentBeta);
        HarveyBal.MergerMeta expected = new HarveyBal.MergerMeta(1,3, HarveyBal.MergerMeta.Type.LEFT);
        Assert.assertEquals(actual.getStart(),expected.getStart());
        Assert.assertEquals(actual.getEnd(),expected.getEnd());
        Assert.assertEquals(actual.getType(),expected.getType());
        Assert.assertEquals(actual.getLength(),expected.getLength());
    }

    @Test
    public void testMergerProcessOverlapReturnsStringFromLeftLarger(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("EFABC");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta actual = merger.processOverlap(fragmentBeta);
        HarveyBal.MergerMeta expected = new HarveyBal.MergerMeta(2,5, HarveyBal.MergerMeta.Type.LEFT);
        Assert.assertEquals(actual.getStart(),expected.getStart());
        Assert.assertEquals(actual.getEnd(),expected.getEnd());
        Assert.assertEquals(actual.getType(),expected.getType());
        Assert.assertEquals(actual.getLength(),expected.getLength());
    }

    @Test
    public void testMergerProcessOverlapReturnsStringFromRightLarger(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("DEFAB");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta actual = merger.processOverlap(fragmentBeta);
        HarveyBal.MergerMeta expected = new HarveyBal.MergerMeta(0,3, HarveyBal.MergerMeta.Type.RIGHT);
        Assert.assertEquals(actual.getStart(),expected.getStart());
        Assert.assertEquals(actual.getEnd(),expected.getEnd());
        Assert.assertEquals(actual.getType(),expected.getType());
        Assert.assertEquals(actual.getLength(),expected.getLength());
    }

    @Test
    public void testMergerProcessOverlapReturnsNullBasic(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCD");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("ZBCY");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta actual = merger.processOverlap(fragmentBeta);
        Assert.assertNull(actual);
    }

    @Test
    public void testMergerProcessOverlapReturnsNullBasic2(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCD");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("ZCB");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta actual = merger.processOverlap(fragmentBeta);
        Assert.assertNull(actual);
    }

    @Test
    public void testMergerProcessOverlapReturnsNullBasic3(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCD");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("CZZ");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta actual = merger.processOverlap(fragmentBeta);
        Assert.assertNull(actual);
    }

    @Test
    public void testMergerProcessOverlapReturnsNullMedium(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("ZEFABZ");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta actual = merger.processOverlap(fragmentBeta);
        Assert.assertNull(actual);
    }

    @Test
    public void testMergerMergeFragmentsLEFTReturnsFragment(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("EFABC");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta meta = merger.processOverlap(fragmentBeta);
        HarveyBal.Fragment actual = merger.mergeFragments(fragmentBeta, meta);
        HarveyBal.Fragment expected = new HarveyBal.Fragment("EFABCDEF");
        Assert.assertEquals(actual.getInternal(), expected.getInternal());
    }

    @Test
    public void testMergerMergeFragmentsRIGHTTReturnsFragment(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("DEFGH");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta meta = merger.processOverlap(fragmentBeta);
        HarveyBal.Fragment actual = merger.mergeFragments(fragmentBeta, meta);
        HarveyBal.Fragment expected = new HarveyBal.Fragment("ABCDEFGH");
        Assert.assertEquals(actual.getInternal(), expected.getInternal());
    }
}
