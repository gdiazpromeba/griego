// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.scroll;

import java.awt.Dimension;

import javax.swing.JScrollBar;

// Referenced classes of package kalos.A.B.I:
//            A

public class BarraScroll extends JScrollBar {

    public BarraScroll() {
	setPreferredSize(new Dimension(18, 18));
	setOrientation(0);
	A = new SBUI();
	setUI(A);
    }

    public void setPaginaActual(int i) {
	A.setPaginaActual(i);
	repaint();
    }

    public void setTotalPaginas(int i) {
	A.setTotalPaginas(i);
	repaint();
    }

    private SBUI A;
}
