package kalos.utilidades.abms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class SeleccionPreposiciones extends JDialog {

//selección de la forma de diccionario
  JButton butOK = new JButton("OK");
  JButton butCancelar = new JButton("Cancel");
  JPanel panBusqueda=new JPanel();
  JPanel panBotones=new JPanel();
  JList lstUsadas=new JList();
  JList lstDisponibles=new JList();
  JPanel panListasPreps=new JPanel();
  JButton butDisponibleAUsada=new JButton("<");
  JButton butUsadaADisponible=new JButton(">");
  JScrollPane scpDisponibles=new JScrollPane(lstDisponibles);
  JScrollPane scpUsadas=new JScrollPane(lstUsadas);
  public List prepsElegidas=new ArrayList();
  public boolean cancel=true;


  public SeleccionPreposiciones() {
    //panel central
    Container cont=getContentPane();
    cont.setLayout(new BorderLayout());
    cont.add(panBotones, BorderLayout.SOUTH);
    cont.add(panListasPreps, BorderLayout.CENTER);
    //panel de preposiciones disponibles y usadas
    DefaultListModel pdlm = new DefaultListModel();
    List colPrepsDisp = new ArrayList();
    //DataAccess.selPreposiciones(colPrepsDisp, KalosApp.con);
    for (int i = 0; i < colPrepsDisp.size(); i++) {
      pdlm.addElement(colPrepsDisp.get(i));
    }
    panListasPreps.setLayout(new GridBagLayout());
    scpDisponibles.setPreferredSize(new Dimension(90, 290));
    scpDisponibles.setMinimumSize(new Dimension(90, 290));
    scpUsadas.setPreferredSize(new Dimension(90, 290));
    scpUsadas.setMinimumSize(new Dimension(90, 290));
    butDisponibleAUsada.setPreferredSize(new Dimension(50, 27));
    butUsadaADisponible.setPreferredSize(new Dimension(50, 27));
    panListasPreps.setBorder(BorderFactory.createEmptyBorder(3,3,3,3) );
    panListasPreps.add(scpUsadas,              new GridBagConstraints(0, 0, 2, 4, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0,0,0,0), 2, 2));
    panListasPreps.add(butDisponibleAUsada,    new GridBagConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0,0,0,0), 2, 2));
    panListasPreps.add(butUsadaADisponible,    new GridBagConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0,0,0,0), 2, 2));
    panListasPreps.add(scpDisponibles,         new GridBagConstraints(4, 0, 2, 4, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0,0,0,0), 2, 2));

    lstDisponibles.setModel(pdlm);
    lstDisponibles.invalidate();
    DefaultListModel lmUsadas = new DefaultListModel();
    lstUsadas.setModel(lmUsadas);

    //panel de botones
    panBotones.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    panBotones.setLayout(new GridBagLayout());
    panBotones.add(butOK, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0,0,2,0), 0, 0));
    panBotones.add(butCancelar, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0,0,2,0), 0, 0));
    //eventos - botones
    butOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (lstUsadas.getModel().getSize()==0) {
          return;
        }
        prepsElegidas.clear();
        Enumeration usadas=((DefaultListModel)lstUsadas.getModel()).elements();
        while(usadas.hasMoreElements())
          prepsElegidas.add(usadas.nextElement());
        cancel=false;
        setVisible(false);
      }
    });
    butCancelar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cancel=true;
        setVisible(false);
      }
    });
    butDisponibleAUsada. addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (lstDisponibles.getSelectedValue() != null) {
          int seleccionado = lstDisponibles.getSelectedIndex();
          DefaultListModel usadas = (DefaultListModel) lstUsadas.getModel();
          DefaultListModel disponibles = (DefaultListModel) lstDisponibles.getModel();
          usadas.addElement(lstDisponibles.getSelectedValue());
          disponibles.removeElementAt(seleccionado);
        }
      }
    });
    butUsadaADisponible.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (lstUsadas.getSelectedValue() != null) {
          int seleccionado = lstUsadas.getSelectedIndex();
          DefaultListModel disponibles = (DefaultListModel) lstDisponibles.getModel();
          DefaultListModel usadas = (DefaultListModel) lstUsadas.getModel();
          disponibles.addElement(lstUsadas.getSelectedValue());
          usadas.removeElementAt(seleccionado);
        }
      }
    });
  }


}