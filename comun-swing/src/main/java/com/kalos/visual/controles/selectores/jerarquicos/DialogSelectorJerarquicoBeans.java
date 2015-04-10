// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.selectores.jerarquicos;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.kalos.beans.TipoJerarquico;
import com.kalos.bibliotecadatos.JerarquiaBeans;
import com.kalos.operaciones.OpBeans;
import com.kalos.recursos.Recursos;
import com.kalos.visual.controles.selectores.DialogSelectorBeans;
import com.kalos.visual.renderers.FRGenericos;

public class DialogSelectorJerarquicoBeans extends DialogSelectorBeans {

    public DialogSelectorJerarquicoBeans(String titulo, JerarquiaBeans a, String s1, boolean flag) {
	arbol = new JTree();
	etiqueta = new JLabel("");
	setTitle(titulo);
	l = a;
	setListaSeleccionable(a.getBeans());
	i = flag;
	arbol.setModel(a.getTreeModel());
	arbol.setShowsRootHandles(true);
	arbol.setEditable(false);
	arbol.setCellRenderer(FRGenericos.obtieneRendererNodo());
	JScrollPane jscrollpane = new JScrollPane();
	panBusqueda.removeAll();
	panBusqueda.add(jscrollpane);
	jscrollpane.setViewportView(arbol);
	arbol.addTreeSelectionListener(new TreeSelectionListener() {

	    public void valueChanged(TreeSelectionEvent treeselectionevent) {
		nodoSeleccionado = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
		if (nodoSeleccionado == null) {
		    limpieza();
		    setAceptarhabilitado(false);
		    return;
		}
		if (!nodoSeleccionado.isLeaf() && i)
		    setAceptarhabilitado(false);
		else
		    setAceptarhabilitado(true);
		String s2 = OpBeans.getId(nodoSeleccionado.getUserObject());
		fuerzaSeleccion(s2);
		l.setPK(s2);
		llenaCampos();
	    }

	});
	jscrollpane.setViewportView(arbol);
	etiqueta.setPreferredSize(new Dimension(512, 27));
	GridBagConstraints gridbagconstraints = new GridBagConstraints();
	gridbagconstraints.gridx = 0;
	gridbagconstraints.gridy = 1;
	gridbagconstraints.fill = 2;
	panBusqueda.add(etiqueta, gridbagconstraints);
    }

    public void limpieza() {
	etiqueta.setText("");
    }

    public void llenaCampos() {
	nodoSeleccionado = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
	Object aobj[] = nodoSeleccionado.getUserObjectPath();
	StringBuffer stringbuffer = new StringBuffer();
	for (int i1 = 1; i1 < aobj.length; i1++) {
	    TipoJerarquico e1 = (TipoJerarquico) aobj[i1];
	    String s = e1.getDesClave();
	    stringbuffer.append("/");
	    stringbuffer.append(Recursos.getCadena(s));
	}

	etiqueta.setText(stringbuffer.toString());
    }

    public java.util.List getHojasDeSeleccion() {
	return l.getHojas(nodoSeleccionado);
    }

    public java.util.List getSeleccionadoYAncestros() {
	return l.getBeanYAncestros(nodoSeleccionado);
    }

    public void setSeleccionado(String s) {
	Map map = l.getMapNodos();
	DefaultMutableTreeNode defaultmutabletreenode = (DefaultMutableTreeNode) map.get(s);
	if (defaultmutabletreenode != null) {
	    arbol.setSelectionPath(new TreePath(defaultmutabletreenode.getPath()));
	    return;
	} else {
	    throw new RuntimeException(
		    (new StringBuilder())
			    .append("DialogSelectorJerarquico.setSeleccionado:  no encontr\363 nada al querer seleccionar la id ")
			    .append(s).toString());
	}
    }

    public void mensajeVacio() {
	JOptionPane.showMessageDialog(this, Recursos.getCadena("no_puede_ser_vacio"), Recursos.getCadena("busqueda"), 0);
    }

    public void fonts() {
    }

    protected void E() {
    }

    JTree arbol;
    JLabel etiqueta;
    DefaultMutableTreeNode nodoSeleccionado;
    JerarquiaBeans l;
    boolean i;
}
