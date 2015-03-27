// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.selectores.jerarquicos;

import java.util.List;

import com.kalos.beans.TipoJerarquico;
import com.kalos.bibliotecadatos.JerarquiaBeans;
import com.kalos.bibliotecadatos.ListaSeleccionable;
import com.kalos.operaciones.OpBeans;
import com.kalos.recursos.Recursos;

// Referenced classes of package kalos.A.B.F.B:
//            A, B

public class SelectorJerarquicoBeans extends SelectorActivoBeans {

    public SelectorJerarquicoBeans(boolean flag, boolean flag1, boolean flag2, JerarquiaBeans jerBeans, boolean flag3,
	    String s) {
	super(null, flag, flag1, flag3, false, "desClave");
	jerarquiaBeans = jerBeans;
	super.listaSeleccionable = new ListaSeleccionable(jerarquiaBeans.getBeans());
	P = flag2;
	if (!flag1)
	    C();
    }

    protected DialogSelectorJerarquicoBeans getDialog() {
	return new DialogSelectorJerarquicoBeans(Q, jerarquiaBeans, N, P);
    }

    private void C() {
	String s;
	if (!P) {
	    s = null;
	} else {
	    List list = jerarquiaBeans.getHojas((String) null);
	    TipoJerarquico e1 = (TipoJerarquico) list.get(0);
	    s = OpBeans.getId(e1);
	}
	fuerzaSeleccion(s);
	seleccionadoEHijos = jerarquiaBeans.getHojas(s);
	seleccionadoYAncestros = jerarquiaBeans.getRegistroYAncestros(s);
    }

    public void muestraDialog() {
	dialogSelectorBeans = getDialog();
	dialogSelectorBeans.setSize(540, 380);
	dialogSelectorBeans.setLocationRelativeTo(null);
	dialogSelectorBeans.setModal(true);
	String s = jerarquiaBeans.getPK();
	((DialogSelectorJerarquicoBeans) dialogSelectorBeans).setSeleccionado(s);
	dialogSelectorBeans.setVisible(true);
	if (dialogSelectorBeans.isAcepto()) {
	    fuerzaSeleccion(dialogSelectorBeans.getPK());
	    seleccionadoEHijos = ((DialogSelectorJerarquicoBeans) dialogSelectorBeans).getHojasDeSeleccion();
	    seleccionadoYAncestros = ((DialogSelectorJerarquicoBeans) dialogSelectorBeans).getSeleccionadoYAncestros();
	    textoDescripcion.setText(cadenaPathSeleccionado());
	}
	dialogSelectorBeans.dispose();
    }

    protected String cadenaPathSeleccionado() {
	StringBuffer stringbuffer = new StringBuffer();
	seleccionadoYAncestros = jerarquiaBeans.getRegistroYAncestros(jerarquiaBeans.getPK());
	for (int i = 0; i < seleccionadoYAncestros.size(); i++) {
	    TipoJerarquico e1 = (TipoJerarquico) seleccionadoYAncestros.get(i);
	    String s = OpBeans.getPropiedad(e1, H);
	    stringbuffer.append(Recursos.getCadena(s));
	    if (i < seleccionadoYAncestros.size() - 1)
		stringbuffer.append("/");
	}

	return stringbuffer.toString();
    }

    public void fuerzaSeleccion(String s) {
	jerarquiaBeans.setPK(s);
	textoDescripcion.setText(cadenaPathSeleccionado());
	super.listaSeleccionable.setPK(s);
    }

    public String getIdSeleccionado() {
	return jerarquiaBeans.getPK();
    }

    public int getEnteroSeleccionado() {
	jerarquiaBeans.getPK();
	TipoJerarquico e1 = (TipoJerarquico) getBeanSeleccionado();
	return e1.getValorEntero();
    }

    public List getSeleccionadoEHijos() {
	return seleccionadoEHijos;
    }

    public List getSeleccionadoYAncestros() {
	return seleccionadoYAncestros;
    }

    private List seleccionadoEHijos;
    private List seleccionadoYAncestros;
    String N;
    private boolean P;
    protected String Q;
    protected JerarquiaBeans jerarquiaBeans;
}
