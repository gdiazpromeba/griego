
package com.kalos.recursos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * <p>
 * Title: Kalos
 * </p>
 * <p>
 * Description: Greek verb conjugation and research tool
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class Recursos {

    public static void setApplicationContext(ApplicationContext aContext) {
        Recursos.context = aContext;
    }

    static private ApplicationContext context;

    static Logger logger = Logger.getLogger(Recursos.class.getName());

    private static Locale locale;

    // private static org.apache.log4j.Logger
    // logger=org.apache.log4j.Logger.getLogger(Recursos.class.getName());

    // recursos de cadenas
    private static ResourceBundle recursos;

    public static CargaRecursos cargador = new CargaRecursos();


    // private static Font font = new Font("Kalos Greek", Font.PLAIN, 12);
    // private static Font font = new Font("Arial Unicode MS", Font.PLAIN, 12);
    // private static Font font = new Font("Galatia SIL (True Type)",
    // Font.PLAIN, 12);
    // private static Font font = new Font("ALPHA-Demo", Font.PLAIN, 12);
    // private static Font font = new Font("GFS Didot", Font.PLAIN, 12);

    /**
     * busca en los bundles de mensajes por orden
     * 
     * @param clave
     * @return
     */
    public static String getCadena(String clave) {
        
        String ret = null;
        try {
            ret = recursos.getString(clave);
        } catch (MissingResourceException e) {
        }
        if (ret == null)
            throw new MissingResourceException("no se encontró cadena para la clave=" + clave, "java.lang.String",
                    clave);
        else
            return ret;
    }

    public static String getUrlHelp() {
        String url = null;
        try {
            if (Configuracion.isDebug()) {
                url = "file://../help/help.html";
            } else {
                url = "file://" + new File("").getCanonicalPath() + "/help/help.html";
                url = url.replaceAll("\\\\", "/");
                System.out.println();
            }
        } catch (Exception e) {
            logger.error("error al obtener la URL de la ayuda");
        }
        return url;
    }

    /**
     * devuelve una cadena internacionalizada con sustituciones
     * 
     * @param clave
     *            la clave de recurso cadena.
     * @param sustiticiones
     *            items de array que no cambian según el idioma, y son
     *            sustituidos en el recurso. Por ejemplo, "página {1} de {2}".
     * @return El recurso ya internacionalizado y sustituido, por ejemplo
     *         "página 3 de 40" o "page 3 of 40".
     */
    public static String getCadena(String clave, String[] sustiticiones) {
        String ret = getCadena(clave);
        for (int i = 0; i < sustiticiones.length; i++) {
            String aSustituir = "{" + (i + 1) + "}";
            ret = ret.replace(aSustituir, sustiticiones[i]);
        }
        return ret;
    }

    public static String[] getArray(String[] arrClaves) {
        List<String> lstAux = new ArrayList<String>();
        for (int i = 0; i < arrClaves.length; i++) {
            lstAux.add(getCadena(arrClaves[i]));
        }
        return (String[]) lstAux.toArray(new String[0]);
    }

    public static void cambiaLocale(String localeName) {
        // Configuracion.isDebug();
        locale = new Locale(localeName);
        recursos = ResourceBundle.getBundle("Mensajes", locale);
    }

}