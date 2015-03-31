
package com.kalos.recursos;

/**
 * Title:        Kalos
 * Description:  Greek verb conjugation and research tool
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */
import java.net.URL;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

public class CargaRecursos {

    Logger logger = Logger.getLogger(this.getClass().getName());



    public static URL getResourceURL(String pathRelativo) {
        try {
            ClassLoader loader = CargaRecursos.class.getClassLoader();
            URL urlRecurso = loader.getResource(pathRelativo);  
            return urlRecurso;
        } catch (Exception ee) {
            System.out.println("Error en getResourceURL directorio=" + pathRelativo + " debug" + Configuracion.isDebug());
            ee.printStackTrace();
            System.exit(-1);
            return null;
        }
    }

    /**
     * Obtiene un recurso que esté en kalos_lib (en producción) o
     * suelto en el directorio /lib en desarrollo
     * @param dirRelativo
     * @return
     */

    public URL getResourceEnLib(String dirRelativo) {
        try {
            if (Configuracion.isDebug()) {
                URL url = new URL("file:C:/kalos_eclipse/lib/" + dirRelativo);
                return url;
            } else {
                ClassLoader loader = Class.forName("kalos.recursos.AnclaImagenes").getClassLoader();
                java.net.URL url = loader.getResource(dirRelativo);
                return url;
            }
        } catch (Exception ee) {
            System.out.println("Error en getResourceLib directorio=" + dirRelativo + " debug" + Configuracion.isDebug());
            ee.printStackTrace();
            System.exit(-1);
            return null;
        }
    }

}