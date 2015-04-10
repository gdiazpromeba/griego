// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.metal.MetalScrollButton;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.kalos.bibliotecadatos.FuenteDatosCacheable;
import com.kalos.operaciones.OpBeans;
import com.kalos.visual.controles.scroll.BarraScroll;

public abstract class PagingModel<T> extends AbstractTableModel {
    private static class UpActionListener implements java.awt.event.ActionListener {

	public void actionPerformed(java.awt.event.ActionEvent actionevent) {
	    A.pageUp();
	    if (A.getPageOffset() == 0)
		A.getUpButton().setEnabled(false);
	    A.getDownButton().setEnabled(true);
	}

	private PagingModel A;

	public UpActionListener(PagingModel b) {
	    A = b;
	}
    }

    private static class DownActionListener implements java.awt.event.ActionListener {

	public void actionPerformed(java.awt.event.ActionEvent actionevent) {
	    A.pageDown();
	    if (A.getPageOffset() == A.getPageCount() - 1)
		A.getDownButton().setEnabled(false);
	    else
		A.getDownButton().setEnabled(true);
	    A.getUpButton().setEnabled(true);
	}

	private PagingModel A;

	public DownActionListener(PagingModel b) {
	    A = b;
	}
    }

    public PagingModel(FuenteDatosCacheable b, int i) {
	upButton = new MetalScrollButton(1, 18, false);
	downButton = new MetalScrollButton(5, 18, false);
	J = System.currentTimeMillis();
	C = i;
	fuenteDatosCacheable = b;
	B();
    }

    private void B() {
	G = fuenteDatosCacheable.getLongitudTotal();
	B = fuenteDatosCacheable.getTodosLosId();
	D = 0;
	A();
	C();
    }

    public int getIndice(String s) {
	T obj =(T) OpBeans.getBeanConEsteID(beans, s);
	if (obj != null)
	    return beans.indexOf(obj);
	else
	    throw new RuntimeException((new StringBuilder()).append("error tratando de encontrar el bean con id=")
		    .append(s).append(" en la página").toString());
    }

    public void borra(int i) {
	beans.remove(i);
	G--;
	A();
    }

    public void modifica(T obj, int i) {
	setFila(i, obj);
	fireTableDataChanged();
    }

    public void inserta(T obj, int i) {
	int j;
	if (beans.size() > 0) {
	    Object bean = beans.get(i);
	    String id = OpBeans.getId(bean);
	    j = B.indexOf(id);
	} else {
	    j = 0;
	}
	B.add(j, OpBeans.getId(obj));
	addFila(i, obj);
	A();
	fireTableDataChanged();
    }

    private void A() {
	int i = D * C;
	int j = Math.min((i + C) - 1, G - 1);
	List list = B.subList(i, j + 1);
	beans = new ArrayList(fuenteDatosCacheable.getBeans(list));
    }

    public int getRowCount() {
	return beans.size();
    }

    public abstract int getColumnCount();

    public T getFila(int i) {
	T obj = beans.get(i);
	return obj;
    }

    public void setFila(int i, T obj) {
	beans.set(i, obj);
    }

    public void addFila(int i, T obj) {
	beans.add(i, obj);
    }

    public abstract Object getValueAt(int i, int j);

    public String getColumnName(int i) {
	return "";
    }

    public int getPageOffset() {
	return D;
    }

    public int getPageCount() {
	return (int) Math.ceil((double) G / (double) C);
    }

    public int getRealRowCount() {
	return G;
    }

    public int getPageSize() {
	return C;
    }

    public void pageDown() {
	if (D < getPageCount() - 1) {
	    D++;
	    A();
	    C();
	    fireTableDataChanged();
	}
    }

    public void pageUp() {
	if (D > 0) {
	    D--;
	    A();
	    C();
	    fireTableDataChanged();
	}
    }

    private void C() {
	barraScroll.setPaginaActual(D + 1);
	barraScroll.setTotalPaginas(getPageCount());
    }

    public static JScrollPane createPagingScrollPaneForTable(JTable jtable) {
	JScrollPane jscrollpane = new JScrollPane(jtable);
	TableModel tablemodel = jtable.getModel();
	if (!(tablemodel instanceof PagingModel))
	    return jscrollpane;
	PagingModel b = (PagingModel) tablemodel;
	b.getDownButton().setEnabled(true);
	b.getUpButton().setEnabled(false);
	if (b.getPageCount() <= 1)
	    b.getDownButton().setEnabled(false);
	b.getUpButton().addActionListener(new UpActionListener(b));
	b.getDownButton().addActionListener(new DownActionListener(b));
	jscrollpane.setVerticalScrollBarPolicy(22);
	jscrollpane.setHorizontalScrollBarPolicy(32);
	jscrollpane.setCorner("UPPER_RIGHT_CORNER", b.getUpButton());
	jscrollpane.setCorner("LOWER_RIGHT_CORNER", b.getDownButton());
	jscrollpane.setHorizontalScrollBar(barraScroll);
	return jscrollpane;
    }

    public JButton getUpButton() {
	return upButton;
    }

    public JButton getDownButton() {
	return downButton;
    }

    protected int C;
    protected int D;
    protected FuenteDatosCacheable fuenteDatosCacheable;
    protected int G;
    protected List B;
    protected List<T> beans;
    protected static BarraScroll barraScroll = new BarraScroll();
    long J;
    private JButton upButton;
    private JButton downButton;

}
