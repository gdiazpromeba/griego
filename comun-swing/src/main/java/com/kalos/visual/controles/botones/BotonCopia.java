package com.kalos.visual.controles.botones;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.kalos.recursos.CargaRecursos;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class BotonCopia extends JButton {
  public BotonCopia() {
    super(new ImageIcon(CargaRecursos.getResourceURL("img/Copy16.gif")));
    setPreferredSize(new Dimension(22, 22));
    setMaximumSize(new Dimension(22, 22));
  }
}