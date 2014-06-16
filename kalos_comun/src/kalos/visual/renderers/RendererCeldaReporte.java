/*
 * Created on Aug 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package kalos.visual.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import kalos.flexion.CeldaReporte;

/**
 * @author Gonzalo
 * 
 * Renderer para las tablas cuyos TableModels contienen sólo elementos de tipo
 * ICeldaReporte. Nótese que no se trabajo un componente genérico, sino que se
 * repiten los seteos de propiedades para la lbl y el chk, como si el renderer
 * quisiera conservar la lbl=this todo lo que puede.
 * 
 */
public class RendererCeldaReporte extends DefaultTableCellRenderer {

    private boolean todoComoCeldaReporte = false;

    // Logger logger=Logger.getLogger(this.getClass().getName());

    /**
     * Constructor del renderer. Puede dibujar dos tipos de datos, una "celda
     * reporte" o una "forma borrable", que es un tipo especial de "celda
     * reporte" con un checkbox.
     * 
     * @param todoComoCeldaReporte
     *            indica que todo debe mostrarse sin el checkbox, sin importar
     *            el tipo de contenido
     */
    public RendererCeldaReporte(boolean todoComoCeldaReporte) {
        this.todoComoCeldaReporte = todoComoCeldaReporte;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {

        try {
            if (value instanceof CeldaReporte) {
                CeldaReporte cr = (CeldaReporte) value;

                if ((value instanceof CeldaReporte) || todoComoCeldaReporte) {
                    JLabel lbl = (JLabel) this; // nótese que para la label uso
                    // this
                    // si tratara de usar una label nueva y después devolver
                    // eso, no andaría
                    if (cr.getContenido() != null)
                        lbl.setText(cr.getContenido());
                    else
                        lbl.setText("");

                    if (cr.getFondo() != null)
                        lbl.setBackground(cr.getFondo());
                    lbl.setOpaque(true);

                    lbl.setFont(table.getFont());
                    return lbl;
                }
            } else if (value instanceof String) {
                JLabel lbl = (JLabel) this;
                lbl.setBackground(Color.white);
                lbl.setText((String) value);
                lbl.setFont(table.getFont());
                return lbl;
            }
            return this; // para el compilador

        } catch (Exception e) {
            StringBuffer sb = new StringBuffer();
            sb.append("Problemas en el RendererICeldaReporte, fila " + row + ", columna " + column + " \n");
            sb.append("El valor problemático es " + value);
            e.printStackTrace();
            throw new RuntimeException(sb.toString());
        }

    }

}
