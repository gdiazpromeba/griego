package kalos.utilidades.comienzo;

import foxtrot.Task;


/**
 * Clase auxiliar que provoca un efecto de gradación (creciente o decreciente) en el panel carátula.
 * Simplemente modifica el valor "alfa" del objeto carátula, que es la densidad de la tinta, y lo fuerza
 * a que se redibuje cada cierto tiempo
 */
class GradacionPanel extends Task{
    	private int alfaInicial, alfaFinal, paso;
    	private Caratula panel;
    	private GradacionPanel este;
    	
    	public GradacionPanel(int alfaInicial, int alfaFinal, int paso, Caratula caratula){
    		this.este=this;
    		this.alfaInicial=alfaInicial;
    		this.alfaFinal=alfaFinal;
    		this.paso=paso;
    		this.panel=caratula;
    	}
    	
    	/**
    	 * expone el  run de esta clase en forma de thread, para que dicho run() pueda ser llamado independientemente
    	 * de un Work
    	 * @return
    	 */
    	public Thread getThread(){
    		Thread ret=new Thread(){
    			public void run(){
    				este.run();
    			}
    		};
    		return ret;
    	}
    	
    	public Object run() {
    		int i=alfaInicial;
    		while(true){
    			i+=paso;
    			if (paso>0 && i>alfaFinal)
    				break;
    			if (paso<0 && i<0)
    				break;
    			panel.setValorAlfa(i);
    			try {
    				Thread.sleep(90);
    				panel.repaint();
    				panel.revalidate();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		return null;
    	}
    	
    };