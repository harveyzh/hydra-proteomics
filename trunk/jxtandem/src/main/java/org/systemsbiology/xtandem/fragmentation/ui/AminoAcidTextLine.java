package org.systemsbiology.xtandem.fragmentation.ui;

import org.systemsbiology.xtandem.*;
import org.systemsbiology.xtandem.fragmentation.*;

import java.io.*;

/**
 * org.systemsbiology.xtandem.fragmentation.ui.AminoAcidTextLine
 * User: Steve
 * Date: 6/25/12
 */
public class AminoAcidTextLine extends AbstractHtmlFragmentHolder {
    public static final AminoAcidTextLine[] EMPTY_ARRAY = {};

    private final int m_Index;
    private final ProteinFragmentLine m_LineFragment;

    public AminoAcidTextLine(final HTMLPageBuillder page, ProteinFragmentLine coverageFragment,int index) {
        super(page);
        m_Index = index;
        m_LineFragment = coverageFragment;
        addBuilder(new ProteinLineBuillder(page,m_LineFragment));
     }

    public int getIndex() {
        return m_Index;
    }

    public ProteinFragmentLine getLineFragment() {
        return m_LineFragment;
    }



    protected int getLineHeight()
    {
        int coverageDepth = 0;
          ProteinFragmentationDescription fragments = getLineFragment().getFragments();
         short[] allCoverage = fragments.getAllCoverage();
        for (int i = 0; i < allCoverage.length; i++) {
            short i1 = allCoverage[i];
            coverageDepth = Math.max(coverageDepth,Math.min(i1,CoverageFragment.MAX_COVERAGE_DEPTH));
        }
        return CoverageFragment.AMINO_ACID_HEIGHT + coverageDepth * CoverageFragment.RECTANGLE_HEIGHT;
    }

    @Override
    public void addStartText(final Appendable out, final Object... data) {
        try {
            indent(out,2);
            out.append("<g id=\"" + getUniqueId() + "\"  transform=\"translate(0," +
                    ( getLineHeight() * getIndex()) + ")\" >\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addEndText(final Appendable out, final Object... data) {
        try {
            indent(out,2);
            out.append("</g>\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

}