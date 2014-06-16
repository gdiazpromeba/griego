/*
 * Created on 24/12/2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package kalos.utilidades.abms.preposiciones;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import kalos.beans.PreposicionBean;
import kalos.enumeraciones.Particularidad;
import kalos.recursos.Recursos;
import kalos.utilidades.abms.PanelDetalleBeans;
import kalos.utilidades.abms.TratamientoSignificados;
import kalos.utilidades.abms.utilidades.UtilValidacion;
import kalos.visual.controles.combos.ComboEnumeracion;
import kalos.visual.controles.textos.beta.TextoBeta;
import kalos.visual.controles.textos.txtarea.AreaTextoVertical;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author gonzy
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

public class DetallePreposiciones extends PanelDetalleBeans  {

	private TextoBeta forma = new TextoBeta(15, false);
	private TextoBeta formaDiccionario = new TextoBeta(15, false);
	private ComboEnumeracion partic=new ComboEnumeracion(Particularidad.Regular);
	private AreaTextoVertical significadoCastellano = new AreaTextoVertical();
	private AreaTextoVertical significadoIngles = new AreaTextoVertical();
	private AreaTextoVertical significadoFrances = new AreaTextoVertical();
	private JCheckBox genitivo=new JCheckBox();
	private JCheckBox acusativo=new JCheckBox();
	private JCheckBox dativo=new JCheckBox();
	


	protected void inicializaCombosgenericos() {}

	@Override
	public void disposicion() {
	    
		FormLayout layout = new FormLayout("pref, 3dlu, 80dlu, 60dlu, fill:100dlu:grow(1)", // cols
				"fill:15dlu,3dlu,  fill:15dlu,3dlu, fill:45dlu,3dlu,  fill:45dlu,3dlu,  fill:45dlu,3dlu,  fill:15dlu,3dlu, " +
				"fill:15dlu,3dlu, fill:15dlu,3dlu, fill:15dlu,3dlu"//rows
		);

		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		// 	DefaultFormBuilder builder = 
		//    new DefaultFormBuilder(layout, new FormDebugPanel());

		CellConstraints cc = new CellConstraints();

		layout.setRowGroups(new int[][] { { 1,3,11,13,15,17},  {5,7,9} });

		builder.addLabel(Recursos.getCadena("forma"), cc.xy(1, 1));
		builder.add(forma, cc.xyw(3, 1, 1));
		
        builder.addLabel(Recursos.getCadena("forma_canonica"), cc.xy(1, 3));
        builder.add(formaDiccionario, cc.xyw(3, 3, 1));

		builder.addLabel(Recursos.getCadena("significado_castellano"), cc.xy(1, 5));
		builder.add(significadoCastellano, cc.xyw(3, 5, 3));

		builder.addLabel(Recursos.getCadena("significado_ingles"), cc.xy(1, 7));
		builder.add(significadoIngles, cc.xyw(3, 7, 3));

		builder.addLabel(Recursos.getCadena("significado_frances"), cc.xy(1, 9));
		builder.add(significadoFrances, cc.xyw(3, 9, 3));

		builder.addLabel(Recursos.getCadena("particularidad"), cc.xy(1, 11));
		builder.add(partic, cc.xyw(3, 11, 2));
		
        builder.addLabel(Recursos.getCadena("genitivo"), cc.xy(1, 13));
        builder.add(genitivo, cc.xyw(3, 13, 2));

        builder.addLabel(Recursos.getCadena("acusativo"), cc.xy(1, 15));
        builder.add(acusativo, cc.xyw(3, 15, 2));
        
        builder.addLabel(Recursos.getCadena("dativo"), cc.xy(1, 17));
        builder.add(dativo, cc.xyw(3, 17, 2));
		
		panCentral.setLayout(new BorderLayout());
		panCentral.add(builder.getPanel());
		
	}
	
	
	public void especificaArrayFocos() {
		setFocos(new JComponent[] { 
				//agregado
				forma.elTexto(), formaDiccionario.elTexto(),
				significadoCastellano, significadoIngles, significadoFrances, partic}, 
				new JComponent[] { //edición
				forma.elTexto(), 
				significadoCastellano, significadoIngles, significadoFrances, partic}
		);
	}
	
	public void especificaControlesEditables() {
		setComponentesEditables(new JComponent[] { 
				forma.elTexto(), formaDiccionario.elTexto(),
				significadoCastellano, significadoIngles, significadoFrances, partic}
		);
	}
	
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#beanACampos(java.lang.Object)
	 */
	public void beanACampos(Object beanQueEscribe) {
		limpieza();
		PreposicionBean bean = (PreposicionBean) beanQueEscribe;
		forma.setText(bean.getPreposicion());
		formaDiccionario.setText(bean.getFormaDiccionario());
		TratamientoSignificados.beanAControles(new AreaTextoVertical[]{significadoCastellano, significadoIngles, significadoFrances}, (PreposicionBean)bean);		
		partic.setClave(bean.getParticularidad());
		genitivo.setSelected(bean.isGenitivo());
		acusativo.setSelected(bean.isAcusativo());
		dativo.setSelected(bean.isDativo());
	}
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#limpieza()
	 */
	public void limpieza() {
		forma.setText(null);
		formaDiccionario.setText(null);
		partic.setSelectedItem(Particularidad.Regular);
		significadoCastellano.setText("");
		significadoIngles.setText("");
		significadoFrances.setText("");
		genitivo.setSelected(false);
		acusativo.setSelected(false);
		dativo.setSelected(false);
		
	}
	
	

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#camposABean(java.lang.Object)
	 */
	public void camposABean(Object beanAPoblar) {
		PreposicionBean bean = (PreposicionBean) beanAPoblar;
		bean.setPreposicion(forma.getText());
		bean.setFormaDiccionario(formaDiccionario.getText());
		TratamientoSignificados.controlesABean(new AreaTextoVertical[]{significadoCastellano, significadoIngles, significadoFrances}, (PreposicionBean)bean);
		bean.setParticularidad((Particularidad) partic.getEnumeracionSeleccionada());
		bean.setGenitivo(genitivo.isSelected());
		bean.setAcusativo(acusativo.isSelected());
		bean.setDativo(dativo.isSelected());
	}
	
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#validaPreInsercion()
	 */
	public boolean validaPreInsercion() {
		//revisiones y complexiones generales que necesitan el bean poblado
		JTextField[] camposGriegos=new JTextField[]{forma.elTexto()};
		UtilValidacion.mayusculaBeta( camposGriegos);
		UtilValidacion.espituSuave(camposGriegos);
		UtilValidacion.sigmaFinal(camposGriegos);
//		if (!UtilValidacion.todoAcentuado(camposGriegos)){
//			JOptionPane.showMessageDialog(this, Recursos.getCadena("todos_campos_acentuados"));
////			return false;
//		}
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#llenadoPreInsercion(java.lang.Object)
	 */
	public void llenadoPreInsercion(Object bean) {
//		AdjetivoBean ea=(AdjetivoBean)bean;
//		codigo.setText(Integer.toString(ea.getCodigo()));
	}
	
	

	
	
	/**
	 * "pega" los significados (contenidos en un bean auxiliar), en los 
	 * controles de significado del panel 
	 * @param bean
	 */
	public void pegaSignificados(PreposicionBean bean){
		TratamientoSignificados.beanAControles(new AreaTextoVertical[]{significadoCastellano, significadoIngles, significadoFrances}, bean);		
	}
	
	
}