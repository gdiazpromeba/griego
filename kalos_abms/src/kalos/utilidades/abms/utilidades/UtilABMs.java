package kalos.utilidades.abms.utilidades;

import java.util.Collections;
import java.util.List;

import kalos.beans.TipoJerarquico;
import kalos.datos.gerentes.GerenteDatos;
import kalos.operaciones.OpBeans;
import kalos.operaciones.comparadores.ComparadorBeansGriegos;
import kalos.recursos.Recursos;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;

/**
 * utilidades para los ABMs
 * 
 * @author gdiaz
 * 
 */
public class UtilABMs {

    /**
     * Utilizada en los beans que tienen alguna propiedad que representa la
     * clave de un recurso de internacionalización, más que el recurso mismo.
     * Busca el recurso y lo coloca en la misma propiedad
     * 
     * @param beans
     *            la lista de beans a analizar
     * @param propiedades
     *            las propiedades
     */
    public static void pasaClaveARecurso(List<?> beans, String[] propiedades) {
        try {
            for (Object bean : beans) {
                for (String propiedad : propiedades) {
                    String cadena = BeanUtils.getProperty(bean, propiedad);
                    cadena = Recursos.getCadena(cadena);
                    BeanUtils.setProperty(bean, propiedad, cadena);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * estrae un array de integers de un lista de tipos jerárquicos. útil para
     * procesar lo que sale de los selectores jerárquicos
     * 
     * @param lista
     * @return
     */
    public static Integer[] extraeArrayEnteros(List<TipoJerarquico> lista) {
        Integer[] array = new Integer[lista.size()];
        int i = 0;
        for (TipoJerarquico tij : lista) {
            if (tij.getId() == null) // es la raíz
                continue;
            Integer ven = tij.getValorEntero();
            array[i] = ven;
            i++;
        }
        return array;
    }

    /**
     * dada una lista de beans, la reordena según un campo en griego, y refleja
     * ese nuevo orden cambiando su campo código. Los nuevos valores de los
     * campos código quedarán numerados de 10 en 10. Además, escribe el cambio
     * de orden en la base de datos.
     * 
     * @param manager
     *            El manager que se usa para escribir los cambios. Debe tener un
     *            método "modifica" que acepta un bean como parámetro.
     * @param listaBeans
     *            La lista original de beans. El orden cambia, y refleja lo que
     *            está en los campos código.
     * @param campoCodigo
     *            El nombre del campo en los beans que contiene un entero que
     *            refleja el orden. Al terminar el método, este campo habrá sido
     *            cambiado.
     * @param camposGriegos
     *            Los campos (en código completo) de los beans que están en
     *            griego y se usan para comparar los beans y determinar el nuevo
     *            orden.
     * @param camposABeta
     *            La lista entrante tiene los campos griegos en completo, pero
     *            este array indica cuáles hay que pasar a beta antes de
     *            escribir en la base de datos
     * @return una lista de beans con los campos código ya cambiados y
     *         reordenada
     */
    public static void reordena(GerenteDatos manager, List<?> listaBeans, String campoCodigo, String[] camposGriegos,
            String[] camposABeta, int comienzo) {
        Collections.sort(listaBeans, new ComparadorBeansGriegos(camposGriegos));
        OpBeans.pasaDeCompletoABeta(listaBeans, camposABeta);
        int contador = comienzo;
        for (Object bean : listaBeans) {
            OpBeans.setPropiedad(bean, campoCodigo, new Integer(contador));
            contador += 10;
            try {
                MethodUtils.invokeMethod(manager, "actualiza", bean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
