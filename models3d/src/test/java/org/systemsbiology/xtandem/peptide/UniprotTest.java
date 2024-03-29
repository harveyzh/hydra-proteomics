package org.systemsbiology.xtandem.peptide;

import org.junit.*;

/**
 * org.systemsbiology.xtandem.peptide.UniprotTest
 * User: Steve
 * Date: 3/18/13
 */
public class UniprotTest {
    public static final UniprotTest[] EMPTY_ARRAY = {};


    public static final String[] TEST_IDS = {
            "VNG0636G",
            "VNG0771G",
            "VNG0779C",
            "VNG1001G",
    };


    public static final int[] LENGTHS = {
            235,
            503,
            604,
            527,
    };


    @Test
    public void testRetrieve() throws Exception {
        for (int i = 0; i < TEST_IDS.length; i++) {
            String test = TEST_IDS[i];
            validateQuery(test, LENGTHS[i]);

        }

    }

    protected void validateQuery(String test, int length) {
        Uniprot up = Uniprot.getByQuery(test, length);
        Assert.assertNotNull(up);

    }


}
