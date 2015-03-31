package com.kalos.iu.tareas;



import com.kalos.iu.PanelProgreso;
import com.kalos.recursos.Recursos;
import foxtrot.*;
import org.apache.log4j.Logger;

public class TareaLeyenda extends Task{

	private PanelProgreso panelProgreso;
	private String leyenda;
	private String[] sustituciones;
	
	Logger logger=Logger.getLogger(this.getClass().getName()); 
	
	public TareaLeyenda(PanelProgreso panelProgreso, String leyenda) {
		super();
		this.panelProgreso=panelProgreso;
		this.leyenda=leyenda;
	}
	
	/**
	 * con sustituciones para la cadena de recursos
	 * @param panelProgreso
	 * @param leyenda
	 * @param sustituciones
	 */
	public TareaLeyenda(PanelProgreso panelProgreso, String leyenda, String[] sustituciones) {
		this(panelProgreso, leyenda);
		this.sustituciones=sustituciones;
	}


	/* (non-Javadoc)
	 * @see foxtrot.Task#run()
	 */
	@Override
	public Object run() throws Exception {
		panelProgreso.etiqueta.setVisible(true);
		panelProgreso.barra.setVisible(true);
		panelProgreso.barra.setIndeterminate(true);
		String texto=Recursos.getCadena(leyenda);
		if (sustituciones!=null){
			for (String sustitucion: sustituciones){
				texto=texto.replaceFirst("\\{\\d\\}", sustitucion);
			}
		}
		panelProgreso.etiqueta.setText(texto);
		panelProgreso.etiqueta.repaint();
		panelProgreso.etiqueta.revalidate();
		return null;
	}

}
