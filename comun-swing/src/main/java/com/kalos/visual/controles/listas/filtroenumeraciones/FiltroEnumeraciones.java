package com.kalos.visual.controles.listas.filtroenumeraciones;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import org.apache.log4j.xml.DOMConfigurator;

import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.recursos.Configuracion;
import com.kalos.recursos.Recursos;

/**
 * Lista que presenta un array de enumeraciones, y es capaz de devolver un array con qué fue seleccionado
 * @author Manuela
 *
 */
public class FiltroEnumeraciones extends JPanel{
	
//    Logger logger=Logger.getLogger(this.getClass().getName());
    
	private JList[] listas;
	
	private void cantidadDeListas(int cantidadEnumeraciones, int maximosItemsPorColumna){
	    int cantidadListas=cantidadEnumeraciones / maximosItemsPorColumna;
	    if ((cantidadListas * maximosItemsPorColumna) < cantidadEnumeraciones)
	        cantidadListas++;
	    listas=new JList[cantidadListas];
	    setLayout(new GridLayout(0, listas.length));
	    for (int i=0; i<listas.length; i++){
	        listas[i]=new JList();
	        add(listas[i], i);
	        DefaultListModel dlm=new DefaultListModel();
	        listas[i].setCellRenderer(new MiPresentador());
	        listas[i].setModel(dlm);
	        listas[i].setBackground(UIManager.getColor("CheckBox.selectionBackground"));
	        listas[i].addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	                JList fuente=(JList)me.getSource();
	                if (me.getClickCount() != 1)
	                    return;
	                int selectedIndex = fuente.locationToIndex(me.getPoint());
	                if (selectedIndex < 0)
	                    return;
	                EnumSeleccionada item = (EnumSeleccionada)fuente.getModel().getElementAt(selectedIndex);
	                item.setSeleccionado(!item.isSeleccionado());
	                repaint();
	            }
	            });
	    }
	    
	}
    
	public <T extends Enum<?>> FiltroEnumeraciones(T[] enumeraciones){
		int maximoPorLista=5;
	    cantidadDeListas(enumeraciones.length, maximoPorLista);
		for (int i=0; i<enumeraciones.length; i++){
			EnumSeleccionada ese=new EnumSeleccionada(enumeraciones[i], true);
			int aQueLista=i/(maximoPorLista);
			DefaultListModel dlm=(DefaultListModel)listas[aQueLista].getModel();
			dlm.addElement(ese);
		}
	}
	
	/**
	 * renderer que presenta un checkbox con los valores de selección/texto del EnumSeleccionado
	 * @author Manuela
	 *
	 */
	private static class MiPresentador implements ListCellRenderer{
		/* (non-Javadoc)
		 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
		 */
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			EnumSeleccionada ens=(EnumSeleccionada)value;
			JCheckBox chk=new JCheckBox();
			chk.setText(ens.getEnumeracion().toString());
			chk.setSelected(ens.isSeleccionado());
			return chk;
		}
	}
	
	/**
	 * devuelve una lista con aquellas enumeraciones que tengan el checkbox seleccionado
	 * @return
	 */
	public List<EnumSeleccionada> getSeleccionadas() {
        List<EnumSeleccionada> resultado = new ArrayList<EnumSeleccionada>();
        for (int h = 0; h < listas.length; h++) {
            JList lista = listas[h];
            DefaultListModel dlm = (DefaultListModel) lista.getModel();
            for (int i = 0; i < dlm.getSize(); i++) {
                EnumSeleccionada ens = (EnumSeleccionada) dlm.get(i);
                if (ens.isSeleccionado()) {
                    resultado.add(ens);
                }
            }
        }
        return resultado;
    }
	
	/**
	 * devuelve una lista con todas las enumseleccionads, independientemente de su estado de selección
	 * @return
	 */
	public List<EnumSeleccionada> getTodas() {
        List<EnumSeleccionada> resultado = new ArrayList<EnumSeleccionada>();
        for (int h = 0; h < listas.length; h++) {
            JList lista = listas[h];
            DefaultListModel dlm = (DefaultListModel) lista.getModel();
            for (int i = 0; i < dlm.getSize(); i++) {
                EnumSeleccionada ens = (EnumSeleccionada) dlm.get(i);
                resultado.add(ens);
            }
        }
        return resultado;
    }
	
	
	/**
	 * convierte el contenido de EnumSeleccionadas en una lista de tipos de palabra
	 * @param enums
	 * @return
	 */
	public List<Enum<?>> getEnums() {
        List<Enum<?>> resultado = new ArrayList<Enum<?>>();
        for (int h = 0; h < listas.length; h++) {
            JList lista = listas[h];
            DefaultListModel dlm = (DefaultListModel) lista.getModel();
            for (int i = 0; i < dlm.getSize(); i++) {
                EnumSeleccionada ens = (EnumSeleccionada) dlm.get(i);
                resultado.add((Enum<?>) ens.getEnumeracion());
            }
        }
        return resultado;
    }
	
	/**
	 * retorna el contenido de las enums seleccionadas en una lista de tipos de palabra
	 * @param <T>
	 * @return
	 */
	public List<Enum<?>> getEnumsSeleccionadas() {
        List<Enum<?>> resultado = new ArrayList<Enum<?>>();
        for (int h = 0; h < listas.length; h++) {
            JList lista = listas[h];
            DefaultListModel dlm = (DefaultListModel) lista.getModel();
            for (int i = 0; i < dlm.getSize(); i++) {
                EnumSeleccionada ens = (EnumSeleccionada) dlm.get(i);
                if (ens.isSeleccionado()) {
                    resultado.add((Enum<?>) ens.getEnumeracion());
                }
            }
        }
        return resultado;
    }
	

	
	public static void main(String[] args){
		DOMConfigurator.configure("log4j.xml");
		Recursos.cambiaLocale(Configuracion.getUltimoIdioma());
		
		JFrame fra=new JFrame();
		FiltroEnumeraciones fie=new FiltroEnumeraciones(TipoPalabra.values());
//		fra.add(new JScrollPane(fie));
		fra.add(fie);
		fra.pack();
		fra.setVisible(true);
	}

}
