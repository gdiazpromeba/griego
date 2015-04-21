package com.kalos.iu;

/**
 * esta interfaz debe ser implementada por todos los paneles agregados directamente a la solapa principal,
 * para reaccionar adecuadamente a dicha selección
 * @author gdiaz
 *
 */
public interface EscuchaSolapa {
    
    void miSolapaSeleccionada();
    
    void setPanelPrincipal (PanelPrincipal panelPrincipal);

}
