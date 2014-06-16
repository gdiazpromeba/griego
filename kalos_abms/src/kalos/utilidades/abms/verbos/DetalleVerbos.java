/*
 * Created on 24/12/2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package kalos.utilidades.abms.verbos;

import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kalos.beans.TipoJerarquico;
import kalos.beans.VerboBean;
import kalos.enumeraciones.Particularidad;
import kalos.operaciones.OpPalabras;
import kalos.recursos.Recursos;
import kalos.utilidades.abms.PanelDetalleBeans;
import kalos.utilidades.abms.TratamientoSignificados;
import kalos.utilidades.abms.utilidades.UtilValidacion;
import kalos.visual.controles.combos.ComboEnumeracion;
import kalos.visual.controles.combos.ComboLetras;
import kalos.visual.controles.selectores.jerarquicos.SelectorJerarquicoBeans;
import kalos.visual.controles.textos.beta.TextoBeta;
import kalos.visual.controles.textos.txtarea.AreaTextoVertical;

import org.apache.commons.lang.StringUtils;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author gonzy
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */


public class DetalleVerbos extends PanelDetalleBeans {
	
	private JTextField codigo = new JTextField(5);

	private ComboLetras letra = new ComboLetras();
	
	private TextoBeta verbo = new TextoBeta(15, false);

	private TextoBeta sufijo = new TextoBeta(15, false);
	
	private ComboEnumeracion partic=new ComboEnumeracion("kalos.enumeraciones.Particularidad");
	
	private JCheckBox dibujado=new JCheckBox();
	
	private SelectorJerarquicoBeans<? extends TipoJerarquico> tipoVerbo;
	
	public JCheckBox compuesto=new JCheckBox();
	
	private AreaTextoVertical significadoCastellano = new AreaTextoVertical();

	private AreaTextoVertical significadoIngles = new AreaTextoVertical();

	private AreaTextoVertical significadoFrances = new AreaTextoVertical();	
	
	
	
	@Override
	public void disposicion() {
		FormLayout layout = new FormLayout("pref, 3dlu, 50dlu, 60dlu, 100dlu, 3dlu, 110dlu", // cols
				"fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,"
						+ "fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu," 
						+ "fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu," // rows
		);

		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

//		 	DefaultFormBuilder builder = 
//		    new DefaultFormBuilder(layout, new FormDebugPanel());

		CellConstraints cc = new CellConstraints();

		layout.setRowGroups(new int[][] { { 1,3,5,7,9,11,13,15,17,19,21, 23 } });

		builder.addLabel(Recursos.getCadena("codigo"), cc.xy(1, 1));
		builder.add(codigo, cc.xy(3, 1));

		builder.addLabel(Recursos.getCadena("letra"), cc.xy(1, 3));
		builder.add(letra, cc.xy(3, 3));

		builder.addLabel(Recursos.getCadena("verbo"), cc.xy(1, 5));
		builder.add(verbo, cc.xyw(3, 5, 3));
		builder.add(sufijo, cc.xy(7, 5));

		builder.addLabel(Recursos.getCadena("particularidad"), cc.xy(1, 7));
		builder.add(partic, cc.xyw(3, 7, 2));
		
		builder.addLabel(Recursos.getCadena("tipo_de_verbo"), cc.xy(1, 9));
		builder.add(tipoVerbo, cc.xyw(3, 9, 5));
		
		builder.addLabel(Recursos.getCadena("significado_castellano"), cc.xy(1, 11));
		builder.add(significadoCastellano, cc.xywh(3, 11, 4, 3));

		builder.addLabel(Recursos.getCadena("significado_ingles"), cc.xy(1, 15));
		builder.add(significadoIngles, cc.xywh(3, 15, 4, 3));

		builder.addLabel(Recursos.getCadena("significado_frances"), cc.xy(1, 19));
		builder.add(significadoFrances, cc.xywh(3, 19, 4, 3));

		builder.addLabel(Recursos.getCadena("dibujado"), cc.xy(1, 23));
		builder.add(dibujado, cc.xyw(3, 23, 2));
		builder.addLabel(Recursos.getCadena("compuesto"), cc.xy(5, 23));
		builder.add(compuesto, cc.xy(7, 23));
		

		panCentral.setLayout(new BorderLayout());
		panCentral.add(builder.getPanel());

		
	}	
	

	protected void inicializaCombosgenericos() {
		tipoVerbo=fabricaControles.getSelectorTiposVerbo(true, false, true);
	}
	
	public void especificaArrayFocos() {
		setFocos(new JComponent[] { 
				//agregado
				verbo.elTexto(), sufijo.elTexto(), partic, 
				tipoVerbo, significadoCastellano, significadoIngles, significadoFrances, 
				dibujado, compuesto}, 
				new JComponent[] { //edición
				verbo.elTexto(), sufijo.elTexto(), partic, 
				tipoVerbo, significadoCastellano, significadoIngles, significadoFrances, 
				dibujado, compuesto}
		);
	}
	
	public void especificaControlesEditables() {
		setComponentesEditables(new JComponent[] { 
				verbo.elTexto(), sufijo.elTexto(), partic, 
				tipoVerbo, significadoCastellano, significadoIngles, significadoFrances, 
				dibujado, compuesto}
		);
	}	
	 
	public void beanACampos(Object bean) {
		limpieza();
		VerboBean ev = (VerboBean) bean;
		letra.setLetraSeleccionadaBeta(ev.getLetra());
		codigo.setText(Integer.toString(ev.getCodigo()));
		verbo.setText(ev.getVerbo());
		sufijo.setText(ev.getSufijo());
		partic.setClave(ev.getParticularidad());
		tipoVerbo.fuerzaSeleccion(ev.getIdTipo());
		TratamientoSignificados.beanAControles(new AreaTextoVertical[]{significadoCastellano, significadoIngles, significadoFrances}, (VerboBean)bean);		
		dibujado.setSelected(ev.isDibujado());
		compuesto.setSelected(ev.isCompuesto());
	}	
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#limpieza()
	 */
	public void limpieza() {
		codigo.setText(null);
		verbo.setText(null);
		sufijo.setText(null);
		partic.setSelectedItem(Particularidad.Regular);
		significadoCastellano.setText("");
		significadoIngles.setText("");
		significadoFrances.setText("");
		dibujado.setSelected(false);
		compuesto.setSelected(false);
	}

	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#camposABean(java.lang.Object)
	 */
	public void camposABean(Object bean) {
		VerboBean ev = (VerboBean) bean;
		ev.setLetra(letra.getLetraSeleccionadaBeta());
		Scanner scan=new Scanner(codigo.getText());
		if (scan.hasNextInt())
		  ev.setCodigo(scan.nextInt());
		String sVerbo=verbo.getText();
		ev.setVerbo(sVerbo);
		ev.setSufijo(sufijo.getText());
		TratamientoSignificados.controlesABean(new AreaTextoVertical[]{significadoCastellano, significadoIngles, significadoFrances}, (VerboBean)bean);
		ev.setParticularidad((Particularidad)partic.getEnumeracionSeleccionada());
		ev.setIdTipo(tipoVerbo.getIdSeleccionado());
		ev.setTipoVerbo(tipoVerbo.getEnteroSeleccionado());
		ev.setDibujado(dibujado.isSelected());
		ev.setCompuesto(compuesto.isSelected());
	}	
	
	

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#validaPreInsercion()
	 */
	public boolean validaPreInsercion() {
		
		if (
				(UtilValidacion.esVaciaONula(verbo.getText()))
			){
			JOptionPane.showMessageDialog(this, Recursos.getCadena("no_puede_ser_vacio"));
			verbo.elTexto().requestFocus();
			return false;
		   }
		
		Scanner revisor=new Scanner(codigo.getText());   
		if (!revisor.hasNextInt()){
			codigo.setText("-1");
		}
		
		//revisiones y complexiones generales que necesitan el bean poblado
		JTextField[] camposGriegos=new JTextField[]{verbo.elTexto(), sufijo.elTexto()};
		UtilValidacion.mayusculaBeta( camposGriegos);
		UtilValidacion.espituSuave(new JTextField[]{verbo.elTexto()});
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
		VerboBean ev=(VerboBean)bean;
		codigo.setText(Integer.toString(ev.getCodigo()));
	}	
	
	//propios de adjetivos
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#aplicaTipoAdjetivo(java.lang.String)
	 */
	public void aplicaTipoverbo(String tipoSugerido){
		tipoVerbo.fuerzaSeleccion(tipoSugerido);
	}	
    
    /**
     *aplica externamente el sufijo contracto 
     * @param valorBeta
     */
    public void aplicaSufijoContracto(String valorBeta){
        sufijo.setText(valorBeta);
    }       
	
	/**
	 * si el la letra inicial de los tres campos es igual, fuerza el combo de 
	 * letra inicial con eso
	 */
	private boolean sugiereLetra(){
		String sVer=verbo.getText();
		if (!StringUtils.isEmpty(sVer)){
		  String sLetra=OpPalabras.primeraLetraBeta(sVer);
		  letra.setLetraSeleccionadaBeta(sLetra);
		  return true;
		}else{
			return false;
		}
	}	
	
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#poneNeutro(java.lang.String)
	 */
	public void poneSufijo(String valorSufijo){
		sufijo.setText(valorSufijo);
		partic.requestFocus();
	}

	/**
	 * "pega" los significados (contenidos en un bean auxiliar), en los 
	 * controles de significado del panel 
	 * @param ea
	 */
	public void pegaSignificados(VerboBean bean){
		TratamientoSignificados.beanAControles(new AreaTextoVertical[]{significadoCastellano, significadoIngles, significadoFrances}, bean);
	}	

}