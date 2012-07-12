package org.systemsbiology.asa;

/**
 * org.systemsbiology.asa.Element
 * User: Steve
 * Date: 7/12/12
 */
public enum Element {
    H(1.20),
    N(1.55),
    NA(2.27),
    CU(1.40),
    CL(1.75),
    C(1.70),
    O(1.52),
    I(1.98),
    P(1.80),
    B(1.85),
    BR(1.85),
    S(1.80),
    SE(1.90),
    F(1.47),
    FE(1.80),
    K(2.75),
    MN(1.73),
    MG(1.73),
    ZN(1.39),
    HG(1.8),
    XE(1.8),
    AU(1.8),
    LI(1.8),;
    // not used D,E,G,J,Q,R,T,U,V,W,Z
    public static final Element[] EMPTY_ARRAY = {};

    private final double m_Radius;

    private Element(double radius) {
        m_Radius = radius;
    }

    public double getRadius() {
        return m_Radius;
    }
}
