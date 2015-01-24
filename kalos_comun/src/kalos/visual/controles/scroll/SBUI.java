// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.visual.controles.scroll;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ScrollBarUI;

import kalos.recursos.Recursos;

public class SBUI extends ScrollBarUI {

    public SBUI() {
	A = UIManager.getFont("Table.font");
    }

    public void paint(Graphics g, JComponent jcomponent) {
	super.paint(g, jcomponent);
	Graphics2D graphics2d = (Graphics2D) g;
	RenderingHints renderinghints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	graphics2d.setRenderingHints(renderinghints);
	graphics2d.setFont(A);
	graphics2d.setColor(Color.lightGray);
	graphics2d.drawRect(0, 0, jcomponent.getWidth(), jcomponent.getHeight());
	graphics2d.setColor(Color.white);
	graphics2d.drawLine(1, 1, jcomponent.getWidth(), 1);
	graphics2d.drawLine(1, 1, 1, jcomponent.getHeight());
	graphics2d.setColor(Color.black);
	String as[] = { Integer.toString(C), Integer.toString(B) };
	graphics2d.drawString(Recursos.getCadena("pagina_tanto_de_tanto", as), 5, 12);
    }

    public void setPaginaActual(int i) {
	C = i;
    }

    public void setTotalPaginas(int i) {
	B = i;
    }

    private int C;
    private int B;
    private Font A;
}
