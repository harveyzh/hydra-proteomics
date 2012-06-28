package org.systemsbiology.xtandem.fragmentation.ui;

import org.systemsbiology.jmol.*;
import org.systemsbiology.xtandem.fragmentation.*;
import org.systemsbiology.xtandem.peptide.*;

import java.io.*;
import java.util.*;

/**
 * org.systemsbiology.xtandem.fragmentation.ui.HTMLPageBuillder
 * User: Steve
 * Date: 6/25/12
 */
public class ThreeDModelBuillder extends AbstractHtmlFragmentHolder {
    public static final ThreeDModelBuillder[] EMPTY_ARRAY = {};

    private final ProteinFragmentationDescription m_Fragments;
    private final Map<ProteinFragment, AminoAcidAtLocation[]> m_FragmentLocations;
    private PDBObject m_Model;

    public ThreeDModelBuillder(final IHtmlFragmentHolder page, ProteinFragmentationDescription pfd) {
        super(page);
        m_Fragments = pfd;
        m_Model = pfd.getModel();
        if (m_Model == null)
            throw new IllegalArgumentException("no 3d model");
        m_FragmentLocations = pfd.getAminoAcidLocations();
        int index = 0;
        for(ProteinFragment pf : m_FragmentLocations.keySet())  {
            ThreeDModelAppletBuillder mf = new ThreeDModelAppletBuillder(this,pfd,pf,m_FragmentLocations.get(pf),index++);
          }
    }


    public ProteinFragmentationDescription getFragments() {
        return m_Fragments;
    }

    public PDBObject getModel() {
        return m_Model;
    }

    @Override
    public void addStartText(final Appendable out, final Object... data) {
        try {
            out.append("<body>");
            out.append("\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void addEndText(final Appendable out, final Object... data) {
        try {
            out.append("</body>");
            out.append("\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);

        }

    }


}