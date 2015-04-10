package com.kalos.iu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.kalos.comun.config.AnalisisMorfologicoConfig;
import com.kalos.comun.config.SwingConfig;
import com.kalos.datos.dao.CombosDAO;
import com.kalos.datos.gerentes.GerenteAdjetivos;
import com.kalos.datos.gerentes.GerenteDiccionario;
import com.kalos.datos.gerentes.GerenteSeguridad;
import com.kalos.datos.gerentes.GerenteSustantivos;
import com.kalos.datos.gerentes.GerenteTiposAdjetivo;
import com.kalos.datos.gerentes.GerenteTiposSustantivo;
import com.kalos.datos.gerentes.GerenteTiposVerbo;
import com.kalos.datos.gerentes.GerenteVerbos;
import com.kalos.flexion.UtilidadesTM;
import com.kalos.iu.Menues;
import com.kalos.iu.PanelProgreso;
import com.kalos.iu.analisismorfologico.PanelAM;
import com.kalos.iu.diccionario.PanelDiccionario;
import com.kalos.iu.flexion.PanelFlexion;
import com.kalos.iu.flexion.ParametrosReporte;
import com.kalos.iu.jasper.UtilidadJasper;
import com.kalos.iu.registro.VentanaMolesta;
import com.kalos.iu.registro.VentanaRegistro;
import com.kalos.visual.controles.FabricaControles;

@Configuration
@Import(SwingConfig.class)
public class IuConfig {
	
	@Bean
	@Autowired
	public PanelAM panelResultadosAM() throws Exception{
		return new PanelAM();
	}
	
	@Bean
	@Autowired
    public FabricaControles fabricaControles(CombosDAO combosDAO, GerenteTiposAdjetivo gerenteTiposAdjetivo, GerenteTiposSustantivo gerenteTiposSustantivo,  
    		GerenteTiposVerbo gerenteTiposVerbo){
    	
    	FabricaControles service= new FabricaControles();
    	service.setCombosDAO(combosDAO);
    	service.setGerenteTiposAdjetivo(gerenteTiposAdjetivo);
    	service.setGerenteTiposSustantivo(gerenteTiposSustantivo);
    	service.setGerenteTiposVerbo(gerenteTiposVerbo);
    	return service;
    }
    
    
    
	@Bean
	@Autowired
    public PanelProgreso panelProgreso(){
    	return new PanelProgreso();
    }
    
    
	@Bean
	@Autowired
    public PanelDiccionario panelResultadosDiccionario(GerenteDiccionario gerenteDiccionario, PanelProgreso panelProgreso){
           	PanelDiccionario panel = new PanelDiccionario(gerenteDiccionario);
           	panel.setPanelProgreso(panelProgreso);
           	return panel;
    }
    
    
	@Bean
	@Autowired
    public UtilidadJasper utilidadJasper(UtilidadesTM utilidadesTM){
    	UtilidadJasper service = new UtilidadJasper();
    	service.setUtilidadesTM(utilidadesTM);
    	return service;
    }
    
    
	@Bean
	@Autowired
    public VentanaMolesta ventanaMolesta(GerenteSeguridad gerenteSeguridad){
    	VentanaMolesta panel = new VentanaMolesta(gerenteSeguridad);
    	return panel;
    }
    
    
    
	@Bean
	@Autowired
    public VentanaRegistro ventanaRegistro(GerenteSeguridad gerenteSeguridad){
    	VentanaRegistro panel = new VentanaRegistro(gerenteSeguridad);
    	return panel;
    }
    
    
    
	@Bean
	@Autowired
    public PanelFlexion panelTablaFlexion(){
    	return new PanelFlexion();
    }
	
    
	@Bean
	@Autowired
    public ParametrosReporte parametrosReporte(GerenteVerbos gerenteVerbos, GerenteSustantivos gerenteSustantivos, GerenteAdjetivos gerenteAdjetivos){
    	ParametrosReporte bean = new ParametrosReporte();
    	bean.setGerenteAdjetivos(gerenteAdjetivos);
    	bean.setGerenteSustantivos(gerenteSustantivos);
    	bean.setGerenteVerbos(gerenteVerbos);
    	return bean;
    }
    
    
    
	@Bean
	@Autowired
    public Menues menues(){
    	return new Menues();
    }
    
	

	

}
