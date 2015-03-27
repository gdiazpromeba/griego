/*
 * Created on May 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.kalos.visual.controles.textos.alternable;

import java.awt.Shape;

import javax.swing.text.Element;
import javax.swing.text.FieldView;

/**
 * @author GDiaz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MiFieldView extends FieldView {
	public MiFieldView(Element elemento){
		super(elemento);
	}
	protected Shape adjustAllocation(Shape shape){
		if (shape == null)
		  return null;
		java.awt.Rectangle bounds = shape.getBounds();
		bounds = (java.awt.Rectangle) super.adjustAllocation(shape);
		bounds.x+=2;
		return bounds;
	}
}
