/*
 * Created on 24/12/2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package kalos.utilidades.abms.adjetivos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kalos.beans.AdjetivoBean;
import kalos.beans.IrrAdjetivoEntero;
import kalos.beans.ValorCombo;
import kalos.datos.gerentes.GerenteTiposSustantivo;
import kalos.enumeraciones.Genero;
import kalos.enumeraciones.GradoComparacion;
import kalos.enumeraciones.Particularidad;
import kalos.flexion.declinacion.Declina;
import kalos.operaciones.OpPalabras;
import kalos.recursos.Recursos;
import kalos.utilidades.abms.IPanelDetalle;
import kalos.utilidades.abms.PanelDetalleBeans;
import kalos.utilidades.abms.utilidades.UtilValidacion;
import kalos.visual.controles.combos.ComboEnumeracion;
import kalos.visual.controles.combos.ComboGenerico;
import kalos.visual.controles.selectores.jerarquicos.SelectorJerarquicoBeans;
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

public class DetalleIrrAdjetivosEnteros extends PanelDetalleBeans implements IPanelDetalle  {

	
	private ComboEnumeracion genero=new ComboEnumeracion(Genero.Masculino);
	private ComboEnumeracion grado=new ComboEnumeracion(GradoComparacion.Positivo);
	private ComboEnumeracion partic=new ComboEnumeracion("kalos.enumeraciones.Particularidad");
	private JTextField subindice = new JTextField(40);
	private TextoBeta nominativo = new TextoBeta(15, false);
	private TextoBeta genitivo = new TextoBeta(15, false);
	private SelectorJerarquicoBeans tipoSustanivo;
	private JButton sugiere=new JButton(Recursos.getCadena("sugerir_tipo"));
	private Declina declina; 
	private GerenteTiposSustantivo gerenteTiposSustantivo;
	private Map<Integer, String> mapaTipos;
	private JCheckBox soloSingular=new JCheckBox();
	
	
	
	
	@Override
	public void disposicion() {
		FormLayout layout = new FormLayout("pref, 3dlu, 50dlu, 60dlu, 100dlu", // cols
				"fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,  fill:15dlu,3dlu,"
						+ "fill:15dlu,3dlu,    fill:15dlu,3dlu,  fill:15dlu,3dlu, fill:15dlu,3dlu" // rows
		);

		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		// 	DefaultFormBuilder builder = 
		//    new DefaultFormBuilder(layout, new FormDebugPanel());

		CellConstraints cc = new CellConstraints();

		layout.setRowGroups(new int[][] { { 1,3,5,7,9,11,13,15,17 } });

		builder.addLabel(Recursos.getCadena("genero"), cc.xy(1, 1));
		builder.add(genero, cc.xyw(3, 1, 2));

		builder.addLabel(Recursos.getCadena("particularidad"), cc.xy(1, 3));
		builder.add(partic, cc.xy(3, 3));

		builder.addLabel(Recursos.getCadena("subindice"), cc.xy(1, 5));
		builder.add(subindice, cc.xy(3, 5));

		builder.addLabel(Recursos.getCadena("nominativo"), cc.xy(1, 7));
		builder.add(nominativo, cc.xyw(3, 7, 3));

		builder.addLabel(Recursos.getCadena("genitivo"), cc.xy(1, 9));
		builder.add(genitivo, cc.xyw(3, 9, 3));
		
		builder.addLabel(Recursos.getCadena("tipo_sustantivo"), cc.xy(1, 11));
		builder.add(tipoSustanivo, cc.xyw(3, 11, 3));
		
		builder.addLabel(Recursos.getCadena("grado_de_comparacion"), cc.xy(1, 13));
		builder.add(grado, cc.xyw(3, 13, 2));

		builder.addLabel(Recursos.getCadena("solo_singular"), cc.xy(1, 15));
        builder.add(soloSingular, cc.xyw(3, 15, 2));
		
        builder.add(sugiere, cc.xy(1, 17));
		

		panCentral.setLayout(new BorderLayout());
		panCentral.add(builder.getPanel());

		
	}
	
	protected void inicializaCombosgenericos() {
		tipoSustanivo=fabricaControles.getSelectorTiposSustantivo(true, false, true);
	}
	
	public void especificaArrayFocos() {
		setFocos(new JComponent[] { 
				//agregad0
				genero, partic, subindice, nominativo.elTexto(), genitivo.elTexto(), tipoSustanivo, grado
				}, 
				new JComponent[] { //edición
				genero, partic, subindice, nominativo.elTexto(), genitivo.elTexto(), tipoSustanivo, grado 
				}
		);
	}
	
	public void especificaControlesEditables() {
		setComponentesEditables(new JComponent[] 
          { 
				genero, partic, subindice, nominativo.elTexto(), genitivo.elTexto(), tipoSustanivo, grado
		  }
		);
	}
	
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#beanACampos(java.lang.Object)
	 */
	public void beanACampos(Object bean) {
		IrrAdjetivoEntero iae = (IrrAdjetivoEntero) bean;
		genero.setClave(iae.getGenero());
		partic.setClave(iae.getParticularidad());
		subindice.setText(Integer.toString(iae.getSubindice()));
		nominativo.setText(iae.getNominativo());
		genitivo.setText(iae.getGenitivo());
		tipoSustanivo.fuerzaSeleccion(iae.getTipoSustantivoId());
		grado.setClave(iae.getGrado());
		soloSingular.setSelected(iae.isSoloSingular());
	}

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#limpieza()
	 */
	public void limpieza() {
		subindice.setText("0");
		nominativo.setText("");
		genitivo.setText("");
	}

	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#camposABean(java.lang.Object)
	 */
	public void camposABean(Object bean) {
		IrrAdjetivoEntero iae=(IrrAdjetivoEntero) bean;
		iae.setGenero((Genero)genero.getEnumeracionSeleccionada());
		iae.setParticularidad((Particularidad)partic.getEnumeracionSeleccionada());
		iae.setSubindice(Integer.parseInt(subindice.getText()));
		iae.setNominativo(nominativo.getText());
		iae.setGenitivo(genitivo.getText());
		iae.setTipoSustantivoId(tipoSustanivo.getIdSeleccionado());
		iae.setTipoSustantivo(tipoSustanivo.getEnteroSeleccionado());
		iae.setGrado((GradoComparacion)grado.getEnumeracionSeleccionada());
		iae.setSoloSingular(soloSingular.isSelected());
	}
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#validaPreInsercion()
	 */
	public boolean validaPreInsercion() {
		Scanner revisor=new Scanner(subindice.getText());
		if (!revisor.hasNextInt()){
			JOptionPane.showMessageDialog(this, kalos.recursos.Recursos.getCadena("validacion.campo_debe_ser_numerico"));
			subindice.requestFocus();
			return false;
		}
		
		//revisiones y complexiones generales que necesitan el bean poblado
		JTextField[] camposGriegos=new JTextField[]{nominativo.elTexto(), genitivo.elTexto()};
		UtilValidacion.mayusculaBeta( camposGriegos);
		UtilValidacion.espituSuave(camposGriegos);
		UtilValidacion.sigmaFinal(camposGriegos);
		if (!UtilValidacion.todoAcentuado(camposGriegos)){
			JOptionPane.showMessageDialog(this, Recursos.getCadena("todos_campos_acentuados"));
			return false;
		}
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see kalos.utilidades.abms.adjetivos.IPanelDetalle#llenadoPreInsercion(java.lang.Object)
	 */
	public void llenadoPreInsercion(Object bean) {}
	
	//propios de adjetivos
	
	/**
	 * aplica un tipo nominal dadas las dos entradas 
	 */
	public void aplicaTipoSustantivo(String tipoSugerido){
		tipoSustanivo.fuerzaSeleccion(tipoSugerido);
	}
	
	/**
	 * llamado desde el método homónimo del ABM secundario, se usa para transmitir cierra información 
	 * desde el ABM principal si se sabe que lo que se acaba de meter es irregular
	 * @param ea
	 */
	public void preliminarEnIrregulares(AdjetivoBean ea){
		partic.setClave(ea.getParticularidad());
		if (ea.getMascFem()!=null){
		    genero.setClave(Genero.MasculinoOFemenino);
			nominativo.setText(OpPalabras.strCompletoABeta(ea.getMascFem()));
		}else if (ea.getMasculino()!=null){
		    genero.setClave(Genero.Masculino);
			nominativo.setText(OpPalabras.strCompletoABeta(ea.getMasculino()));
		}else if (ea.getFemenino()!=null){
			genero.setClave(Genero.Femenino);
			nominativo.setText(OpPalabras.strCompletoABeta(ea.getFemenino()));
		}else if (ea.getNeutro()!=null){
			genero.setClave(Genero.Femenino);
			nominativo.setText(OpPalabras.strCompletoABeta(ea.getMasculino()));
		}
	}

    public void setDeclina(Declina declina) {
        this.declina = declina;
        sugiere.addActionListener(new EscuchaBotonSugerencia(declina));
    }
    
    
    private class EscuchaBotonSugerencia implements ActionListener{
        private Declina declina;
        public EscuchaBotonSugerencia(Declina declina){
            this.declina=declina;
        }
        public void actionPerformed(ActionEvent ev){
            String nomCompleto=OpPalabras.strBetaACompleto(nominativo.getText());
            String genCompleto=OpPalabras.strBetaACompleto(genitivo.getText());
            int tipo=declina.sugiereDeclinacion(nomCompleto, genCompleto, (Genero)genero.getEnumeracionSeleccionada());
            tipoSustanivo.fuerzaSeleccion(mapaTipos.get(tipo));
          }        
    }


    public void setGerenteTiposSustantivo(GerenteTiposSustantivo gerenteTiposSustantivo) {
        this.gerenteTiposSustantivo = gerenteTiposSustantivo;
        this.mapaTipos=gerenteTiposSustantivo.getMapaTiposID();
    }
    
}