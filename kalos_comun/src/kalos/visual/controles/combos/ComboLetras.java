package kalos.visual.controles.combos;

import java.awt.Component;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.UIManager;

import kalos.enumeraciones.CompLetras;
import kalos.operaciones.OpPalabras;
import kalos.recursos.Configuracion;


/**
 * Da a elegir las letras iniciales en Beta Code
 */

public class ComboLetras extends JComboBox {
  public ComboLetras() {
    super();
    Vector<String> vec=new Vector<String>();
    
    for (int i = 0; i <CompLetras.arrCompLetras.length; i++) {
    	String compLetra=new String(new char[] {CompLetras.arrCompLetras[i]});
        vec.add(compLetra);
    }
    DefaultComboBoxModel dm = new DefaultComboBoxModel(vec);
    setModel(dm);
    setRenderer(new RendererLetra());
  }
  
  public String getLetraSeleccionadaBeta(){
	  String letra=(String)getSelectedItem();
	  if (letra!=null)
		  return OpPalabras.strCompletoABeta(letra);
	  return letra;
  }
  
  public void setLetraSeleccionadaBeta(String letraBeta){
	  String letra=OpPalabras.strBetaACompleto(letraBeta);
	  setSelectedItem(letra);
  }
  
  
  private class RendererLetra extends DefaultListCellRenderer{


	  public Component getListCellRendererComponent(JList list, Object value, // value to display
	                                                int index,    // cell index
	                                                boolean isSelected,  // is selected
	                                                boolean chf) {  // cell has focus?


	            String texto=(String)value;
	            texto=OpPalabras.strCompletoAUnicode(texto);
	            setFont(Configuracion.getFont());
	            setText(texto);
	            
	        		if (isSelected){
	        				setBackground(UIManager.getColor("List.selectionBackground"));
	        				setForeground(UIManager.getColor("List.selectionForeground"));
	        			}
	        			else{
	        				setBackground(UIManager.getColor("List.background"));
	        				setForeground(UIManager.getColor("List.foreground"));
	        			}


	            return this;
	  }
	}
}

