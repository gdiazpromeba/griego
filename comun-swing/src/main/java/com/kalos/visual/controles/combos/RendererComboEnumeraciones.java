// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.combos;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.UIManager;

import com.kalos.recursos.CadenasEnum;
import com.kalos.recursos.Recursos;

public class RendererComboEnumeraciones extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList jlist, Object obj, int i, boolean flag, boolean flag1) {
	Enum enu = (Enum) obj;
	String s = null;
	if (obj == null)
	    s = Recursos.getCadena("nulo");
	else
	    s = CadenasEnum.getCadena(enu);
	setText(s);
	if (flag) {
	    setBackground(UIManager.getColor("List.selectionBackground"));
	    setForeground(UIManager.getColor("List.selectionForeground"));
	} else {
	    setBackground(UIManager.getColor("List.background"));
	    setForeground(UIManager.getColor("List.foreground"));
	}
	return this;
    }
}
