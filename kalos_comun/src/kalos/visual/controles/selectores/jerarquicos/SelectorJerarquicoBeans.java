// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.visual.controles.selectores.jerarquicos;

import java.util.List;

import javax.swing.JTextField;

import kalos.C.F;
import kalos.J.A;
import kalos.K.e;
import kalos.beans.TipoJerarquico;
import kalos.bibliotecadatos.JerarquiaBeans;
import kalos.bibliotecadatos.ListaSeleccionable;
import kalos.operaciones.OpBeans;
import kalos.recursos.Configuracion;
import kalos.recursos.Recursos;
import kalos.visual.controles.selectores.DialogSelectorBeans;

// Referenced classes of package kalos.A.B.F.B:
//            A, B

public class SelectorJerarquicoBeans extends SelectorActivoBeans {

    public SelectorJerarquicoBeans(boolean flag, boolean flag1, boolean flag2, JerarquiaBeans jerBeans, boolean flag3, String s) {
	super(null, flag, flag1, flag3, false, "desClave");
	jerarquiaBeans = jerBeans;
	super.listaSeleccionable = new ListaSeleccionable(jerarquiaBeans.getBeans());
	P = flag2;
	if (!flag1)
	    C();
    }

    protected DialogSelectorBeans muestraDialog() {
	return new DialogSelectorJerarquico(Q, jerarquiaBeans, N, P);
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
	K = A();
	K.setSize(540, 380);
	K.setLocationRelativeTo(null);
	K.setModal(true);
	String s = jerarquiaBeans.getPK();
	((B) K).setSeleccionado(s);
	K.setVisible(true);
	if (K.isAcepto()) {
	    fuerzaSeleccion(K.getPK());
	    seleccionadoEHijos = ((B) K).getHojasDeSeleccion();
	    seleccionadoYAncestros = ((B) K).getSeleccionadoYAncestros();
	    B.setText(B());
	}
	K.dispose();
    }

    protected String B() {
	StringBuffer stringbuffer = new StringBuffer();
	seleccionadoYAncestros = jerarquiaBeans.getRegistroYAncestros(jerarquiaBeans.getPK());
	for (int i = 0; i < seleccionadoYAncestros.size(); i++) {
	    TipoJerarquico e1 = (TipoJerarquico) seleccionadoYAncestros.get(i);
	    String s = Configuracion.getPropiedad(e1, H);
	    stringbuffer.append(Recursos.getCadena(s));
	    if (i < seleccionadoYAncestros.size() - 1)
		stringbuffer.append("/");
	}

	return stringbuffer.toString();
    }

    public void fuerzaSeleccion(String s) {
	jerarquiaBeans.setPK(s);
	B.setText(B());
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
