package com.kalos.iu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.kalos.beans.EntradaDiccionario;
import com.kalos.beans.ResultadoUniversal;
import com.kalos.datos.gerentes.GerenteDiccionario;
import com.kalos.iu.analisismorfologico.PanelAM;
import com.kalos.iu.diccionario.PanelDiccionario;
import com.kalos.iu.flexion.PanelFlexion;
import com.kalos.recursos.Recursos;

import foxtrot.Task;


public class PanelPrincipal extends JPanel {
    
    
    private JLabel etiquetaMensaje = new JLabel(Recursos.getCadena("forma_de_diccionario_actual"));
    private JLabel etiquetaForma = new JLabel();
    private PanelAM panelAM;
    private GerenteDiccionario gerenteDiccionario;
    
    //Logger log= Logger.getLogger(this.getClass().getName());    
    
    public PanelPrincipal(PanelDiccionario panelDiccionario, PanelAM panelAM, PanelFlexion panelFlexion, PanelProgreso panelProgreso, GerenteDiccionario gerenteDiccionario){
        this.panelAM = panelAM;
        this.gerenteDiccionario = gerenteDiccionario;
        setLayout(new BorderLayout());
        JTabbedPane solapas = new JTabbedPane();
        add(Comienzo.getPanelProgreso(), "South");
        add(solapas, "Center");
        solapas.add(Recursos.getCadena("diccionario"), Comienzo.getPanelDiccionario());
        solapas.add(Recursos.getCadena("analisis_morfologico"), Comienzo.getPanelAM());
        solapas.add(Recursos.getCadena("flexion"), Comienzo.getPanelFlexion());
        FormLayout formlayout = new FormLayout("400dlu:grow(0.9),  right:100dlu:none, 3dlu, left:100dlu:grow(0.9)", "fill:25dlu");
        PanelBuilder panelbuilder = new PanelBuilder(formlayout);
        CellConstraints cellconstraints = new CellConstraints();
        panelbuilder.add(etiquetaMensaje, cellconstraints.xy(2, 1));
        panelbuilder.add(etiquetaForma, cellconstraints.xy(4, 1));
        add(panelbuilder.getPanel(), BorderLayout.NORTH);     
        add(panelProgreso, BorderLayout.SOUTH);
        
        //notificación a los paneles individuales, de que su solapa fue seleccionada
        solapas.getModel().addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent e) {
                int tabIndex = solapas.getSelectedIndex();
                EscuchaSolapa panelES = (EscuchaSolapa) solapas.getSelectedComponent();
                panelES.miSolapaSeleccionada();
                
            }
        });
    }
    
    private EntradaDiccionario entradaDiccionario;

    
    public void setEntradaDiccionario(EntradaDiccionario entradaDiccionario){
        this.entradaDiccionario = entradaDiccionario;
        etiquetaForma.setText(this.entradaDiccionario.getNormal());
        
    }
    
    public void setResultadoUniversal(ResultadoUniversal reu){
        
        try {
            new Task() {
                
                @Override
                public Object run() throws Exception {
                    EntradaDiccionario end = gerenteDiccionario.getEntradaDiccionario(reu);
                    entradaDiccionario = end;
                    etiquetaForma.setText(entradaDiccionario.getNormal());
                    return null;
                }
            }.run();
        } catch (Exception e) {

            e.printStackTrace();
        }
        

    }
    
    public EntradaDiccionario getEntradaDiccionario(){
        return this.entradaDiccionario;
    }
    
    public PanelAM getPanelAM(){
        return this.panelAM;
    }
    
    

}
