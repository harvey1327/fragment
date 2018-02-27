import fragment.submissions.HarveyBal;
import org.junit.Assert;
import org.junit.Test;

public class MergerTest {

    @Test
    public void testMergerMergeFragmentsMIDDLEReturnsFragment(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABC DEFC ");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("C D");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta meta = merger.processOverlap(fragmentBeta);
        HarveyBal.Fragment actual = merger.mergeFragments(fragmentBeta, meta);
        HarveyBal.Fragment expected = new HarveyBal.Fragment("ABC DEFC ");
        Assert.assertEquals(actual.getInternal(), expected.getInternal());
    }

    @Test
    public void testMergerProcessOverlapReturnsMetaFromMiddlePotentialRight(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABC DEFC ");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("C D");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta actual = merger.processOverlap(fragmentBeta);
        HarveyBal.MergerMeta expected = new HarveyBal.MergerMeta(2,5, HarveyBal.MergerMeta.Type.MIDDLE);
        Assert.assertEquals(actual.getStart(),expected.getStart());
        Assert.assertEquals(actual.getEnd(),expected.getEnd());
        Assert.assertEquals(actual.getType(),expected.getType());
        Assert.assertEquals(actual.getLength(),expected.getLength());
    }

    @Test
    public void testMergerProcessOverlapReturnsMetaFromMiddlePotentialLeft(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("DABC DEF ");
        HarveyBal.Fragment fragmentBeta = new HarveyBal.Fragment("C D");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta actual = merger.processOverlap(fragmentBeta);
        HarveyBal.MergerMeta expected = new HarveyBal.MergerMeta(3,6, HarveyBal.MergerMeta.Type.MIDDLE);
        Assert.assertEquals(actual.getStart(),expected.getStart());
        Assert.assertEquals(actual.getEnd(),expected.getEnd());
        Assert.assertEquals(actual.getType(),expected.getType());
        Assert.assertEquals(actual.getLength(),expected.getLength());
    }

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

    @Test
    public void testGetMaxFragmentReturnsMaxLengthObject(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta1 = new HarveyBal.Fragment("EFG");
        HarveyBal.Fragment fragmentBeta2 = new HarveyBal.Fragment("DEFGH");
        HarveyBal.Fragment fragmentBeta3 = new HarveyBal.Fragment("ZABCDE");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta meta1 = merger.processOverlap(fragmentBeta1);
        HarveyBal.MergerMeta meta2 = merger.processOverlap(fragmentBeta2);
        HarveyBal.MergerMeta meta3 = merger.processOverlap(fragmentBeta3);

        merger.addToMap(meta1, fragmentBeta1);
        merger.addToMap(meta2, fragmentBeta2);
        merger.addToMap(meta3, fragmentBeta3);

        HarveyBal.Fragment actual = merger.getMaxFragment();
        Assert.assertEquals(fragmentBeta3, actual);
    }

    @Test
    public void testGetMaxMetaReturnsMaxLengthObject(){
        HarveyBal.Fragment fragmentPrime = new HarveyBal.Fragment("ABCDEF");
        HarveyBal.Fragment fragmentBeta1 = new HarveyBal.Fragment("EFG");
        HarveyBal.Fragment fragmentBeta2 = new HarveyBal.Fragment("DEFGH");
        HarveyBal.Fragment fragmentBeta3 = new HarveyBal.Fragment("ZABCDE");

        HarveyBal.Merger merger = new HarveyBal.Merger(fragmentPrime);
        HarveyBal.MergerMeta meta1 = merger.processOverlap(fragmentBeta1);
        HarveyBal.MergerMeta meta2 = merger.processOverlap(fragmentBeta2);
        HarveyBal.MergerMeta meta3 = merger.processOverlap(fragmentBeta3);

        merger.addToMap(meta1, fragmentBeta1);
        merger.addToMap(meta2, fragmentBeta2);
        merger.addToMap(meta3, fragmentBeta3);

        HarveyBal.MergerMeta actual = merger.getMaxMeta();
        Assert.assertEquals(meta3, actual);
    }
}
