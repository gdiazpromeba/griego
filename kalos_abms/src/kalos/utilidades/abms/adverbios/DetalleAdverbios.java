/*
 * Created on 24/12/2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package kalos.utilidades.abms.adverbios;

import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kalos.beans.AdjetivoBean;
import kalos.beans.AdverbioBean;
import kalos.enumeraciones.GradoComparacion;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.TipoAdverbio;
import kalos.operaciones.OpPalabras;
import kalos.recursos.Recursos;
import kalos.utilidades.abms.PanelDetalleBeans;
import kalos.utilidades.abms.TratamientoSignificados;
import kalos.utilidades.abms.utilidades.UtilValidacion;
import kalos.visual.controles.combos.ComboEnumeracion;
import kalos.visual.controles.combos.ComboLetras;
import kalos.visual.controles.textos.beta.TextoBeta;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author gonzy
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

public class DetalleAdverbios extends PanelDetalleBeans  {

	private JTextField codigo = new JTextField(5);

	private ComboLetras letra = new ComboLetras();

	private TextoBeta adverbio = new TextoBeta(15, false);

	private JTextField significadoCastellano = new JTextField(40);

	private JTextField significadoIngles = new JTextField(40);

	private JTextField significadoFrances = new JTextField(40);

	private ComboEnumeracion partic=new ComboEnumeracion(Particularidad.Regular);
	
	private ComboEnumeracion grado=new ComboEnumeracion(GradoComparacion.Positivo);
	
	private ComboEnumeracion tipoAdverbio=new ComboEnumeracion(TipoAdverbio.Modo);
	



	@Override
	public void disposicion() {
		FormLayout layout = new FormLayout("pref, 3dlu, 50dlu, 60dlu, 100dlu", // cols
				"fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu," + 
				"fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu" //rows
		);

		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		// 	DefaultFormBuilder builder = 
		//    new DefaultFormBuilder(layout, new FormDebugPanel());

		CellConstraints cc = new CellConstraints();

		layout.setRowGroups(new int[][] { { 1,3,5,7,9,11,13,15 } });

		builder.addLabel(Recursos.getCadena("codigo"), cc.xy(1, 1));
		builder.add(codigo, cc.xy(3, 1));

		builder.addLabel(Recursos.getCadena("letra"), cc.xy(1, 3));
		builder.add(letra, cc.xy(3, 3));

		builder.addLabel(Recursos.getCadena("adverbio"), cc.xy(1, 5));
		builder.add(adverbio, cc.xyw(3, 5, 3));

		builder.addLabel(Recursos.getCadena("tipo_adverbio"), cc.xy(1, 7));
		builder.add(tipoAdverbio, cc.xyw(3, 7, 2));

		builder.addLabel(Recursos.getCadena("significado_castellano"), cc.xy(1, 9));
		builder.add(significadoCastellano, cc.xyw(3, 9, 3));

		builder.addLabel(Recursos.getCadena("significado_ingles"), cc.xy(1, 11));
		builder.add(significadoIngles, cc.xyw(3, 11, 3));

		builder.addLabel(Recursos.getCadena("significado_frances"), cc.xy(1, 13));
		builder.add(significadoFrances, cc.xyw(3, 13, 3));

		builder.addLabel(Recursos.getCadena("particularidad"), cc.xy(1, 15));
		builder.add(partic, cc.xyw(3, 15, 2));
		
		builder.addLabel(Recursos.getCadena("grado_de_comparacion"), cc.xy(1, 17));
		builder.add(grado, cc.xyw(3, 17, 2));
		
		
		panCentral.setLayout(new BorderLayout());
		panCentral.add(builder.getPanel());
		
	}
	
	protected void inicializaCombosgenericos() {
	}
	
	public void especificaArrayFocos() {
		setFocos(new JComponent[] { 
				//agregado
				adverbio.elTexto(), 
				tipoAdverbio, significadoCastellano, significadoIngles, significadoFrances, partic,
				grado}, 
				new JComponent[] { //edición
				adverbio.elTexto(), 
				tipoAdverbio, significadoCastellano, significadoIngles, significadoFrances, partic,
				grado}
		);
	}
	
	public void especificaControlesEditables() {
		setComponentesEditables(new JComponent[] { 
				adverbio.elTexto(), 
				tipoAdverbio, significadoCastellano, significadoIngles, significadoFrances, partic,
				grado}
		);
	}
	
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#beanACampos(java.lang.Object)
	 */
	public void beanACampos(Object beanQueEscribe) {
		limpieza();
		AdverbioBean bean = (AdverbioBean) beanQueEscribe;
		codigo.setText(Integer.toString(bean.getCodigo()));
		adverbio.setText(bean.getAdverbio());
		letra.setLetraSeleccionadaBeta(bean.getLetra());
		TratamientoSignificados.beanAControles(new JTextField[]{significadoCastellano, significadoIngles, significadoFrances}, (AdverbioBean)bean);		
		partic.setClave(bean.getPartic());
		tipoAdverbio.setClave(bean.getTipo());
		grado.setClave(bean.getGrado());
	}
	
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#limpieza()
	 */
	public void limpieza() {
		codigo.setText(null);
		adverbio.setText(null);
		partic.setClave(Particularidad.Regular);
		significadoCastellano.setText("");
		significadoIngles.setText("");
		significadoFrances.setText("");
		grado.setClave(GradoComparacion.Positivo);
		tipoAdverbio.setClave(TipoAdverbio.Modo);
	}
	
	

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#camposABean(java.lang.Object)
	 */
	public void camposABean(Object beanAPoblar) {
		AdverbioBean bean = (AdverbioBean) beanAPoblar;
		bean.setLetra(letra.getLetraSeleccionadaBeta());
		Scanner scan=new Scanner(codigo.getText());
		if (scan.hasNextInt())
		  bean.setCodigo(scan.nextInt());
		bean.setAdverbio(adverbio.getText());
		TratamientoSignificados.controlesABean(new JTextField[]{significadoCastellano, significadoIngles, significadoFrances}, (AdverbioBean)bean);		
		bean.setPartic((Particularidad)partic.getEnumeracionSeleccionada());
		bean.setTipo((TipoAdverbio)tipoAdverbio.getEnumeracionSeleccionada());
		bean.setGrado((GradoComparacion)grado.getEnumeracionSeleccionada());
	}

	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#validaPreInsercion()
	 */
	public boolean validaPreInsercion() {
		Scanner revisor=new Scanner(codigo.getText());
		if (!revisor.hasNextInt()){
			codigo.setText("-1");
		}
		
		//revisiones y complexiones generales que necesitan el bean poblado
		JTextField[] camposGriegos=new JTextField[]{adverbio.elTexto()};
		UtilValidacion.mayusculaBeta( camposGriegos);
		UtilValidacion.espituSuave(camposGriegos);
		UtilValidacion.sigmaFinal(camposGriegos);
		if (!UtilValidacion.todoAcentuado(camposGriegos)){
			JOptionPane.showMessageDialog(this, Recursos.getCadena("todos_campos_acentuados"));
			return false;
		}
		sugiereLetra();
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#llenadoPreInsercion(java.lang.Object)
	 */
	public void llenadoPreInsercion(Object bean) {
		AdjetivoBean ea=(AdjetivoBean)bean;
		codigo.setText(Integer.toString(ea.getCodigo()));
	}
	
	
	/**
	 * si el la letra inicial de los tres campos es igual, fuerza el combo de 
	 * letra inicial con eso
	 */
	private boolean sugiereLetra(){
		String adv=adverbio.getText();
		
		if (!UtilValidacion.esVaciaONula(adv)){
			adv=OpPalabras.primeraLetraBeta(adv);
			letra.setLetraSeleccionadaBeta(adv);
			return true;
		}else{
			JOptionPane.showMessageDialog(this, "No se ha podido deducir cuál es la letra inicial");
			return false;
		}
	}
	
	
	/**
	 * "pega" los significados (contenidos en un bean auxiliar), en los 
	 * controles de significado del panel 
	 * @param bean
	 */
	public void pegaSignificados(AdverbioBean bean){
		TratamientoSignificados.beanAControles(new JTextField[]{significadoCastellano, significadoIngles, significadoFrances}, bean);
	}
	
	
}