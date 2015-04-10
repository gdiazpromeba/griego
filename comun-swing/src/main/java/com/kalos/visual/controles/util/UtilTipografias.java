// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.util;

import java.awt.*;
import javax.swing.JTable;

public class UtilTipografias {

    public static void cambiaTamañoEnTabla(JTable jtable, float f) {
	Font font = cambiaTamañoEnComponente(jtable, f);
	FontMetrics fontmetrics = jtable.getFontMetrics(font);
	jtable.setRowHeight(fontmetrics.getMaxAscent() + fontmetrics.getMaxDescent() + 4);
    }

    public static Font cambiaTamañoEnComponente(Component component, float f) {
	Font font = component.getFont();
	Font font1 = new Font(font.getName(), font.getStyle(), (int) f);
	component.setFont(font1);
	return font1;
    }
}
