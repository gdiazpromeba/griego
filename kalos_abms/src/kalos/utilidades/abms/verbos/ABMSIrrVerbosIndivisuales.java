package kalos.utilidades.abms.verbos;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import kalos.beans.IrrVerboIndividual;
import kalos.beans.VerboBean;
import kalos.datos.adaptadores.AdaptadorGerenteIrrVerbosIndividuales;
import kalos.datos.gerentes.GerenteIrrVerbosIndividuales;
import kalos.enumeraciones.Particularidad;
import kalos.flexion.conjugacion.Verbos;
import kalos.utilidades.abms.ABM;
import kalos.utilidades.abms.PanelDetalleBeans;
import kalos.visual.controles.FabricaControles;
import kalos.visual.controles.botones.BotonBuscar;
import kalos.visual.controles.combos.ComboEnumeracion;
import kalos.visual.modelos.IrrVerboIndividualPM;
import kalos.visual.renderers.FRGenericos;

import org.apache.log4j.Logger;
import org.springframework.jdbc.UncategorizedSQLException;


//import java.awt.HeadlessException;

/**
 * <p>Title: Kalos</p>
 * <p>Description: Greek verb conjugation and research tool</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ABMSIrrVerbosIndivisuales extends ABM  {
	
    Logger logger=Logger.getLogger(this.getClass().getName());
	
	private Verbos verbos;
	
    //para el panel de búsqueda
    BotonBuscar buscaPorPartic=new BotonBuscar();
    ComboEnumeracion partics=new ComboEnumeracion(Particularidad.Regular);

	//extras
	private JTable tblDeclinacion = new JTable();
	/**
	 * para proveer autonumeración
	 */
	private FabricaControles fac;

	private GerenteIrrVerbosIndividuales gerenteIrrVerbosIndividuales;
	



	public ABMSIrrVerbosIndivisuales(PanelDetalleBeans detalle, FabricaControles fac) {
		super(detalle, "ABM Irr Verbos");
		this.fac=fac;
		preparaPanelBusqueda();
		
		setPropiedades(new String[]{"forma", "partic", "subPart", "respetaAcento",
									"modo", "tiempo", "voz", 
									"fuerte"});
		setAnchos(new int[]{50, 50, 50,  50, 50, 50, 50});
		setRenderers(new String[]{FRGenericos.TEXTO_LATINO, FRGenericos.ENUMERACION, FRGenericos.NUMERO_ENTERO, FRGenericos.BOOLEAN_SINO,
				FRGenericos.ENUMERACION, FRGenericos.ENUMERACION, FRGenericos.ENUMERACION, 
				FRGenericos.ENUMERACION, 
           });		
		setEncabezadosComluma(new String[]{ "forma", "particularidad", "subindice", "respeta_acento",  		"modo"});
		
		//no llama a "disposición, ya que no hay panel de búsqueda

		//eventos de búsqueda - no hay
		
		//no prepara toolbar

		//no prepara panel declinación
		
		inhabilitaTodo();
	}
	
	
	public void habilitaOperaciones(){
		super.habilitaOperaciones();
	}
	
	
    @Override
    protected void seleccionCambio() {
    	super.seleccionCambioInterna();
        IrrVerboIndividual bean = (IrrVerboIndividual) ultimoBeanSeleccionado;
        if (bean != null) {
            panDetalle.beanACampos(bean);
        }
    }



	@Override
	protected final void preparaBarras() {}
	

    private void preparaPanelBusqueda(){
        panBusqueda.setLayout(new FlowLayout());
        panBusqueda.add(partics);
        panBusqueda.add(buscaPorPartic);
        buscaPorPartic.addActionListener(new EscuchaParticularidad());
    }
        
    private class EscuchaParticularidad implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            String idVerbo=entradaVerbo.getId();
            Particularidad partic=(Particularidad)partics.getEnumeracionSeleccionada();
            cargaBeansEnTablaDadoVerbo(idVerbo, partic);   
          }
    }
	

	/**
	 * el ID del adjetivo padre, que debe ser comunicado desde el ABM principal
	 */
	private VerboBean entradaVerbo;
	
	/**
	 * comunica desde el ABM de adjetivos el ID del adjetivo actualmente seleccionado
	 * @param idAdjetivo
	 */
	public void setBeanPrincipal(Object bean){
		VerboBean ea=(VerboBean)bean;
		this.entradaVerbo=ea;
//		cargaBeansEnTablaDadoVerbo(ea.getId());
		
	}
	
	

	@Override
	public void llenadoPreInsercion() {}
	
	@Override
	public boolean validaPreInsercion(){
//		sugiereTipoNominal();
	    boolean ok=panDetalle.validaPreInsercion();
		if (ok){
	      return true;
		}else{
			return false;
		}
	}
		
	
    protected void cargaBeansEnTablaDadoVerbo(String idVerbo, Particularidad partic) {
        preparaColumnas();
        AdaptadorGerenteIrrVerbosIndividuales apv = new AdaptadorGerenteIrrVerbosIndividuales(gerenteIrrVerbosIndividuales);
        apv.seleccionaPorVerbo(idVerbo, partic);
        pagingModel=new IrrVerboIndividualPM(apv, 100);
        tablemodelATabla();
        habilitaOperaciones();
    }

    @Override
    public void inserta() {
		IrrVerboIndividual iae=new IrrVerboIndividual();
		panDetalle.camposABean(iae);
		iae.setVerboId(this.entradaVerbo.getId());
		iae.setPartic((Particularidad)partics.getEnumeracionSeleccionada());
		try{
			  gerenteIrrVerbosIndividuales.inserta(iae);
			}catch(UncategorizedSQLException e){
				logger.error("error en la inserción de irr verbo", e);
				JOptionPane.showMessageDialog(this, "Excepción de base de datos. ¿Tal vez la combinación género-particularidad-subíndice no es única?");
			}
        setBeanPrincipal(this.entradaVerbo);			
        despuesDeInsertar(iae);
        tabla.repaint();
    }
    
    @Override
    public void actualiza() {
        IrrVerboIndividual iae= (IrrVerboIndividual) ultimoBeanSeleccionado;
        panDetalle.camposABean(iae);
        iae.setPartic((Particularidad)partics.getEnumeracionSeleccionada());
        iae.setVerboId(this.entradaVerbo.getId());
        gerenteIrrVerbosIndividuales.modifica(iae);
        despuesDeActualizar();
        setBeanPrincipal(this.entradaVerbo);
    }
    

	
    @Override
	public void borra(){
		gerenteIrrVerbosIndividuales.borra(getPk());
		despuesDeBorrar();
		setBeanPrincipal(this.entradaVerbo);
	}
	
	
	/**
	 * llamado cuando lo que se acaba de meter en el ABM principal es irregular.
	 * Copia algo de la información del bean principal al panel de detalle delABM secundario, 
	 * ahorrándome así algo de tipeo. 
	 *
	 */
	public void preliminarEnIrregulares(){
		((DetalleIrrVerbos)panDetalle).preliminarEnIrregulares(entradaVerbo);
	}










	/**
	 * @param declina The declina to set.
	 */
	public void setVerbos(Verbos verbos) {
		this.verbos = verbos;
	}





	public void setGerenteIrrVerbosIndividuales(GerenteIrrVerbosIndividuales gerenteIrrVerbosIndividuales) {
		this.gerenteIrrVerbosIndividuales = gerenteIrrVerbosIndividuales;
	}
	

}