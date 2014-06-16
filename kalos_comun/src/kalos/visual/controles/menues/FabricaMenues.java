/*
 * Created on Jul 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package kalos.visual.controles.menues;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import kalos.recursos.Recursos;

/**
 * Sirve para crear menúes
 */
public class FabricaMenues {
	
	
	/**
	 * crea un menú con copiar/pegar y lo adjunta a un JtextField
	 * @param txt
	 */
	public static void adjuntaMenuCopiarPegar(JTextField txt, boolean copiar, boolean pegar){
		
		final JPopupMenu menu = new JPopupMenu();
		
		if (copiar){
		  JMenuItem itemCopiar = new JMenuItem(Recursos.getCadena("copiar"));
		  itemCopiar.addActionListener(new ListenerCopiar(txt));
		  menu.add(itemCopiar);
	    }
		if (pegar){
		  JMenuItem itemPegar = new JMenuItem(Recursos.getCadena("pegar"));
		  itemPegar.addActionListener(new ListenerPegar(txt));
		  menu.add(itemPegar);
		}
		
		
		
		
		
		// Set the component to show the popup menu
		txt.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if (evt.isPopupTrigger()) {
					menu.show(evt.getComponent(), evt.getX(), evt.getY());
				}
			}
			public void mouseReleased(MouseEvent evt) {
				if (evt.isPopupTrigger()) {
					menu.show(evt.getComponent(), evt.getX(), evt.getY());
				}
			}
		});
	}
	
	private static class ListenerCopiar implements ActionListener{
		private JTextField txt;
		public ListenerCopiar(JTextField txt){
			this.txt=txt;
		}
		public void actionPerformed(ActionEvent ev){
			Clipboard cb = Toolkit.getDefaultToolkit ().getSystemClipboard ();
			String s=txt.getText();
			StringSelection contents = new  StringSelection (s);  
			cb.setContents (contents, null);
		}
	}
	
	
	private static class ListenerPegar implements ActionListener{
		private JTextField txt;
		public ListenerPegar(JTextField txt){
			this.txt=txt;
		}
		public void actionPerformed(ActionEvent ev){
			Clipboard cb = Toolkit.getDefaultToolkit ().getSystemClipboard ();
			Transferable  content = cb.getContents (this);  
			try { 
				String S = (String)  content.getTransferData (DataFlavor.stringFlavor);
				txt.setText (S);		 
			}catch(Exception ex){
				ex.printStackTrace();
			}  	}
	}
	
	
	
}
