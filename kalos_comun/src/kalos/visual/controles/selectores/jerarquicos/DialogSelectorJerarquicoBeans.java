// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.visual.controles.selectores.jerarquicos;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import kalos.A.B.F.A;
import kalos.C.F;
import kalos.K.e;
import kalos.recursos.Recursos;

public class DialogSelectorJerarquico extends SelectorActivoBeans
{

    public DialogSelectorJerarquico(String s, kalos.J.A a, String s1, boolean flag)
    {
        h = new JTree();
        j = new JLabel("");
        setTitle(s);
        l = a;
        A(a.getBeans());
        i = flag;
        h.setModel(a.getTreeModel());
        h.setShowsRootHandles(true);
        h.setEditable(false);
        h.setCellRenderer(kalos.A.A.B.obtieneRendererNodo());
        JScrollPane jscrollpane = new JScrollPane();
        I.removeAll();
        I.add(jscrollpane);
        jscrollpane.setViewportView(h);
        h.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent treeselectionevent)
            {
                A.k = (DefaultMutableTreeNode)A.h.getLastSelectedPathComponent();
                if(A.k == null)
                {
                    A.limpieza();
                    A.setAceptarhabilitado(false);
                    return;
                }
                if(!A.k.isLeaf() && A.i)
                    A.setAceptarhabilitado(false);
                else
                    A.setAceptarhabilitado(true);
                String s2 = kalos.G.A.getId(A.k.getUserObject());
                A.fuerzaSeleccion(s2);
                A.l.setPK(s2);
                A.llenaCampos();
            }

            final B A;

            
            {
                A = B.this;
                super();
            }
        }
);
        jscrollpane.setViewportView(h);
        j.setPreferredSize(new Dimension(512, 27));
        GridBagConstraints gridbagconstraints = new GridBagConstraints();
        gridbagconstraints.gridx = 0;
        gridbagconstraints.gridy = 1;
        gridbagconstraints.fill = 2;
        K.add(j, gridbagconstraints);
    }

    public void limpieza()
    {
        j.setText("");
    }

    public void llenaCampos()
    {
        k = (DefaultMutableTreeNode)h.getLastSelectedPathComponent();
        Object aobj[] = k.getUserObjectPath();
        StringBuffer stringbuffer = new StringBuffer();
        for(int i1 = 1; i1 < aobj.length; i1++)
        {
            e e1 = (e)aobj[i1];
            String s = e1.getDesClave();
            stringbuffer.append("/");
            stringbuffer.append(F.getCadena(s));
        }

        j.setText(stringbuffer.toString());
    }

    public java.util.List getHojasDeSeleccion()
    {
        return l.getHojas(k);
    }

    public java.util.List getSeleccionadoYAncestros()
    {
        return l.getBeanYAncestros(k);
    }

    public void setSeleccionado(String s)
    {
        Map map = l.getMapNodos();
        DefaultMutableTreeNode defaultmutabletreenode = (DefaultMutableTreeNode)map.get(s);
        if(defaultmutabletreenode != null)
        {
            h.setSelectionPath(new TreePath(defaultmutabletreenode.getPath()));
            return;
        } else
        {
            throw new RuntimeException((new StringBuilder()).append("DialogSelectorJerarquico.setSeleccionado:  no encontr\363 nada al querer seleccionar la id ").append(s).toString());
        }
    }

    public void mensajeVacio()
    {
        JOptionPane.showMessageDialog(this, F.getCadena("no_puede_ser_vacio"), Recursos.getCadena("busqueda"), 0);
    }

    public void fonts()
    {
    }

    protected void E()
    {
    }

    JTree h;
    JLabel j;
    DefaultMutableTreeNode k;
    kalos.J.A l;
    boolean i;
}
