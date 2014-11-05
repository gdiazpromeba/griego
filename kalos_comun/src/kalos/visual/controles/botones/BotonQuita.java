// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.visual.controles.botones;

import java.awt.Dimension;

import javax.swing.JButton;

import kalos.recursos.Recursos;

public class BotonQuita extends JButton {

    public BotonQuita() {
	super(Recursos.cargador.getImagen("quita16.gif"));
	setPreferredSize(new Dimension(22, 22));
	setMaximumSize(new Dimension(22, 22));
    }
}