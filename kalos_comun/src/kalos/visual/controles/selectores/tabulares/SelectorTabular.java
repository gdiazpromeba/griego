// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.visual.controles.selectores.tabulares;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import kalos.datos.gerentes.SeleccionadorUno;
import kalos.datos.gerentes.SeleccionadorUnoTodos;
import kalos.operaciones.OpBeans;
import kalos.recursos.Configuracion;
import kalos.visual.controles.botones.BotonCopia;
import kalos.visual.controles.botones.BotonDetalle;
import kalos.visual.controles.botones.BotonQuita;
import kalos.visual.controles.selectores.DialogSelectorBeans;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public abstract class SelectorTabular extends JPanel {

    public SelectorTabular(SeleccionadorUno c1, boolean flag, boolean flag1, boolean flag2, boolean flag3,
	    String s) {
	A = new BotonDetalle();
	E = new BotonQuita();
	F = new BotonCopia();
	K = c1;
	C = flag;
	G = flag1;
	D = flag2;
	I = flag3;
	H = s;
	FormLayout formlayout = new FormLayout("30dlu:grow(0.9), 3dlu, pref,pref,pref ", "fill:15dlu");
	B = new JTextField();
	if (flag3)
	    B.setFont(Configuracion.getFont());
	setLayout(new BorderLayout());
	PanelBuilder panelbuilder = new PanelBuilder(formlayout);
	CellConstraints cellconstraints = new CellConstraints();
	if (!flag1 && !flag && !flag2) {
	    panelbuilder.add(B, cellconstraints.xyw(1, 1, 5));
	} else {
	    panelbuilder.add(B, cellconstraints.xy(1, 1));
	    panelbuilder.add(A, cellconstraints.xy(3, 1));
	    panelbuilder.add(E, cellconstraints.xy(4, 1));
	    panelbuilder.add(F, cellconstraints.xy(5, 1));
	}
	add(panelbuilder.getPanel());
	setEnabled(flag);
	B.setEnabled(false);
	B.setBackground(UIManager.getColor("Label.background"));
	if (!flag1)
	    E.setVisible(false);
	A.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent actionevent) {
		muestraDialog();
	    }

	});
	E.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent actionevent) {
		quitaSeleccion();
	    }

	});
    }

    public void seleccionaId(String s) {
	Object obj = K.seleccionaUno(s);
	setBeanSeleccionado(obj);
    }

    public void setBeanSeleccionado(Object obj) {
	L = obj;
	if (obj != null)
	    B.setText(OpBeans.getPropiedad(obj, H));
	else
	    B.setText(null);
    }

    public Object getBeanSeleccionado() {
	return L;
    }

    public String getIdSeleccionado() {
	if (L != null)
	    return OpBeans.getId(L);
	else
	    return null;
    }

    public void setEnabled(boolean flag) {
	A.setVisible(flag);
	E.setVisible(flag & G);
	F.setVisible(D & flag);
    }

    protected abstract DialogSelectorBeans getDialog();

    public abstract void muestraDialog();

    public final void quitaSeleccion() {
	L = null;
	B.setText("");
    }

    public JTextField getTextoDescripcion() {
	return B;
    }

    protected BotonDetalle A;
    protected BotonQuita E;
    public BotonCopia F;
    protected DialogSelectorBeans dialog;
    protected boolean C;
    protected boolean G;
    protected boolean D;
    protected JTextField B;
    protected boolean I;
    protected String H;
    SeleccionadorUno K;
    protected Object L;
}