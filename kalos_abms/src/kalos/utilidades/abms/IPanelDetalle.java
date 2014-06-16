package kalos.utilidades.abms;

public interface IPanelDetalle {

	void beanACampos(Object bean);

	void limpieza();

	void camposABean(Object bean);

	boolean validaPreInsercion();

	void llenadoPreInsercion(Object bean);
	
	void disposicion();

	
	
	/**
	 * cada método que descarte a éste debe llamar a un setComponentesEditables(new JComponent[]{})
	 * con los componentes que se quiere que se habiliten/inhabiliten automáticamente de acuerdo al 
	 * framework
	 */
	void especificaArrayFocos();
	
	/**
	 * cada método que descarte a éste debe llamar a un "setFocos(new JComponent[]{})
	 * con los componentes que participan en la iteración de focos
     */
	void especificaControlesEditables();


}