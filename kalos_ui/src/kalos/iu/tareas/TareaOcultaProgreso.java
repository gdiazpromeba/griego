package kalos.iu.tareas;

import kalos.iu.PanelProgreso;

import org.apache.log4j.Logger;

import foxtrot.Task;

public class TareaOcultaProgreso extends Task{

	private PanelProgreso panelProgreso;
	
	Logger logger=Logger.getLogger(this.getClass().getName()); 
	
	public TareaOcultaProgreso(PanelProgreso panelProgreso) {
		this.panelProgreso=panelProgreso;
	}

	/* (non-Javadoc)
	 * @see foxtrot.Task#run()
	 */
	@Override
	public Object run() throws Exception {
		panelProgreso.barra.setIndeterminate(false);
		panelProgreso.etiqueta.setVisible(false);
		panelProgreso.barra.setVisible(false);
		panelProgreso.repaint();
		panelProgreso.revalidate();
		return null;
	}

}
