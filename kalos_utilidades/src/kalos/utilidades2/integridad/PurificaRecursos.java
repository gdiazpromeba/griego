/*
 * Created on Feb 3, 2005
 */
package kalos.utilidades.integridad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;
import javax.swing.table.DefaultTableModel;

import kalos.operaciones.OpPalabras;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResource;

/**
 * Revisa todo el espacio de tranajo en busca de ocurrencias de Recursos.getCadena(...) en el código, poniendo
 * @author Gonzalo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PurificaRecursos {

	static Logger logger=Logger.getLogger(Logger.class.getName());
	
	
//	utilidadesTM.reemplazaNombresColumna(dtm, new String[]{"forma_flexionada", "accidentes", "significado" });
	
	public static void main2(String[] args) throws Exception{
		String u="utilidadesTM.reemplazaNombresColumna(dtm, new String[]{\"forma_flexionada\", \"accidentes\", \"significado\" });";
		int i=u.lastIndexOf('{');
		System.out.println(i);
	}
	
    public static void main(String[] args) throws Exception{
		DOMConfigurator.configure("log4j.xml");
    	contexto=creaContexto();
    	DataSource kalosDataSource = (DataSource) contexto.getBean("kalosDataSource");
		con=kalosDataSource.getConnection();
    	Set<ClaveProcedencia> claves=new HashSet<ClaveProcedencia>();
    	claves.addAll(getClavesDeArchivos("c:/kalos/kalos_ui/src", "ui"));
    	claves.addAll(getClavesDeArchivos("c:/kalos/kalos_comun/src", "comun"));
    	claves.addAll(getClavesDeArchivos("c:/kalos/kalos_abms/src", "abms"));
    	claves.addAll(getClavesDeTipo());
    	List<ClaveProcedencia> listaCP=new ArrayList<ClaveProcedencia>(claves);
    	Collections.sort(listaCP);
    	
    	String pathRecursos="c:/kalos/kalos_comun/src/kalos/recursos/";
    	
    	Set<String> procedenciasAIncluir=new HashSet<String>();
    	procedenciasAIncluir.add("ui");
    	procedenciasAIncluir.add("comun");
    	procedenciasAIncluir.add("bd");
//    	procedenciasAIncluir.add("abms");
    	
    	
    	versionPurificada(new File(pathRecursos + "Mensajes_es.properties"), 
    			listaCP, new File(pathRecursos + "Mensajes_es.properties_purificado"), procedenciasAIncluir);
    	
    	versionPurificada(new File(pathRecursos + "Mensajes_fr.properties"), 
    			listaCP, new File(pathRecursos + "Mensajes_fr.properties_purificado"), procedenciasAIncluir);
    	
    	versionPurificada(new File(pathRecursos + "Mensajes_en.properties"), 
    			listaCP, new File(pathRecursos + "Mensajes_en.properties_purificado"), procedenciasAIncluir);
    	
    	versionPurificada(new File(pathRecursos + "Mensajes_pr.properties"), 
    			listaCP, new File(pathRecursos + "Mensajes_pr.properties_purificado"), procedenciasAIncluir);
    	
    }
    
    
    private static class ClaveProcedencia implements Comparable<PurificaRecursos.ClaveProcedencia>{
    	public ClaveProcedencia(String clave, String procedencia){
    		this.clave=clave;
    		this.procedencia=procedencia;
    	}
    	private String clave;
    	private String procedencia;
    	
    	public int compareTo(PurificaRecursos.ClaveProcedencia obj){
    		return this.clave.trim().compareTo(obj.clave.trim());
    	}
    	
    	
    	
    	@Override
		public int hashCode() {
			return clave.hashCode();
		}



		public boolean equals(Object obj){
    		String claveObj=((ClaveProcedencia)obj).clave;
    		return this.clave.equals(claveObj);
    	}
    }
    
    
    private static Set<ClaveProcedencia> getClavesDeArchivos(String pathFuente, String marcadorProcedencia) throws Exception{
        Set<ClaveProcedencia> claves=new TreeSet<ClaveProcedencia>();
        List<File> archivos=null;
            archivos=getFileListing( new File(pathFuente));
            
            for (Iterator<File> it=archivos.iterator(); it.hasNext();){
                File archivo=it.next();
                if (archivo.isDirectory()) 
                    continue;
                cargaDesdeArchivo(archivo, claves, marcadorProcedencia);    
            }
            
            return claves;
    }
    
    
    /**
     * devuelve un Set con las claves que vienen de la base de datos
     * @return
     * @throws Exception
     */
    private static Set<ClaveProcedencia> getClavesDeTipo() throws Exception{
    	Set<ClaveProcedencia> clavesTipo=new HashSet<ClaveProcedencia>();
    	Statement stm=con.createStatement();
    	//tipos sustantivo
    	StringBuffer sb=new StringBuffer();
    	sb.append("SELECT DISTINCT TIPO_SUST_DES_CLAVE FROM TIPOS_SUSTANTIVO \n");
    	ResultSet rs=stm.executeQuery(sb.toString());
    	while (rs.next()){
    		clavesTipo.add(new ClaveProcedencia(rs.getString("TIPO_SUST_DES_CLAVE"), "bd"));
    	}
    	stm.close();
    	rs.close();
    	
    	//tipos verbo
    	sb=new StringBuffer();
    	sb.append("SELECT DISTINCT TIPO_VERBO_DES_CLAVE FROM TIPOS_VERBO \n");
    	Statement stm2=con.createStatement();
    	ResultSet rs2=stm2.executeQuery(sb.toString());
    	while (rs2.next()){
    		clavesTipo.add(new ClaveProcedencia(rs2.getString("TIPO_VERBO_DES_CLAVE"), "bd"));
    	}
    	stm.close();
    	rs2.close();
    	
    	//tipos adjetivo
    	sb=new StringBuffer();
    	sb.append("SELECT DISTINCT TIPO_ADJETIVO_DES_CLAVE FROM TIPOS_ADJETIVO \n");
    	Statement stm3=con.createStatement();
    	ResultSet rs3=stm3.executeQuery(sb.toString());
    	while (rs3.next()){
    		clavesTipo.add(new ClaveProcedencia(rs3.getString("TIPO_ADJETIVO_DES_CLAVE"), "bd"));
    	}
    	stm.close();
    	rs3.close();
    	
    	return clavesTipo;

    }
    
    
    private static Map<String, String> cargaRecursoActual(File recurso) throws IOException{
    	Map<String, String> map=new HashMap<String, String>();
        BufferedReader bur=new BufferedReader(new InputStreamReader(new FileInputStream(recurso)));
        String linea="";
        while(linea!=null){
            linea=bur.readLine();
            if (linea==null || StringUtils.isEmpty(linea)) break;
            int hasta=linea.indexOf("=");
            String clave=linea.substring(0, hasta);
            String valor=linea.substring(hasta+1);
            valor=unicodeEscape(valor);
            map.put(clave, valor);
        }
        bur.close();
        return map;
    }
    
    private static final char[] hexChar = {
        '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };
    
    
    private static String unicodeEscape(String s) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < s.length(); i++) {
    	    char c = s.charAt(i);
    	    if ((c >> 7) > 0) {
    		sb.append("\\u");
    		sb.append(hexChar[(c >> 12) & 0xF]); // append the hex character for the left-most 4-bits
    		sb.append(hexChar[(c >> 8) & 0xF]);  // hex for the second group of 4-bits from the left
    		sb.append(hexChar[(c >> 4) & 0xF]);  // hex for the third group
    		sb.append(hexChar[c & 0xF]);         // hex for the last group, e.g., the right most 4-bits
    	    }
    	    else {
    		sb.append(c);
    	    }
    	}
    	return sb.toString();
        }

    
    
    private static void versionPurificada(File original, List claves, File purificado, Set<String> procedenciasAIncluir) throws IOException{
        BufferedWriter buw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(purificado), "UTF-8"));
        Map recursoOriginal=cargaRecursoActual(original);
        for (ListIterator<ClaveProcedencia> it=claves.listIterator(); it.hasNext(); ){
            ClaveProcedencia cp=it.next();
            if (!procedenciasAIncluir.contains(cp.procedencia)){
//            	buw.write(cp.clave + "=#\n");
            }else{
	            String valor=(String)recursoOriginal.get(cp.clave);
	            if (valor==null){
	            	logger.warn("la clave " + cp.clave + " no está en el archivo de recursos " + original.getName());
	            }
	            buw.write(cp.clave + "=" + valor + "\n");
            }
        }
        buw.close();
        
    }
    
    
    private static FilenameFilter filtroJavaNoEste = new FilenameFilter() {
        public boolean accept(File dir, String name) {
          if (dir.isDirectory())
                return true;
          else{
            if (name.endsWith("\\.java") && name.indexOf("PurificaRecursos")<0)
                return true;
            else{
                return false;
            }
          }
        }
    };
    
    
    public static void cargaDesdeArchivo(File archivo, Set<ClaveProcedencia> entradas, final String marcadorProcedencia){
    	String linea=null;
    	try {
            BufferedReader bur=new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"));
            linea=bur.readLine();
            while (linea!=null){
            	entradas.addAll(extraeRecursosDirecto(linea, marcadorProcedencia));
            	entradas.addAll(extraeTareaLeyenda(linea, marcadorProcedencia));
            	entradas.addAll(extraeEncabezadosColumna(linea, marcadorProcedencia));
            	entradas.addAll(extraeDeReemplazaNombresColumna(linea, marcadorProcedencia));
              linea=bur.readLine();
            }
        } catch (Exception e) {
        	System.out.println("error en línea " +linea );
            e.printStackTrace();
        }
    }
    
    
    /**
     * analiza apariciones directas de la clave, entre comillas, dentro de Recursos.getCadena("clave")
     * @param linea
     * @param marcadorProcedencia
     * @return
     */
    private static Set<ClaveProcedencia> extraeRecursosDirecto(String linea, String marcadorProcedencia){
        Set<ClaveProcedencia> resultado=new HashSet<ClaveProcedencia>();
    	int principio=0;
        while(principio!=-1){
            principio=linea.indexOf("Recursos.getCadena(\"", principio) + 20;
            if (principio==19) break;
            int fin=linea.indexOf("\"", principio);
            String clave=linea.substring(principio, fin);
            resultado.add(new ClaveProcedencia(clave, marcadorProcedencia));
            principio=fin;
        }
        return resultado;
    }
    
    private static Set<ClaveProcedencia> extraeTareaLeyenda(String linea, String marcadorProcedencia){
        Set<ClaveProcedencia> resultado=new HashSet<ClaveProcedencia>();
    	int principio=0;
        while(principio!=-1){
            principio=linea.indexOf("new TareaLeyenda(", principio);
        	if (principio<0)
        		break;
        	principio=linea.indexOf("\"", principio) +1;
            int fin=linea.indexOf("\"", principio);
            String clave=linea.substring(principio, fin);
            resultado.add(new ClaveProcedencia(clave, marcadorProcedencia));
            principio=fin;
        }
        return resultado;
    }

    
    
    private static Set<ClaveProcedencia> extraeEncabezadosColumna(String linea, String marcadorProcedencia){
        Set<ClaveProcedencia> resultado=new HashSet<ClaveProcedencia>();
    	int principio=0;
        while(principio!=-1){
            principio=linea.indexOf("setEncabezadosComluma(new String[]", principio);
//        	principio=linea.indexOf("setEncabezadosComluma", principio);
        	if (principio<0)
        		break;
        	principio+=34;
            int fin=linea.indexOf("}", principio);
            String segmentoArray=linea.substring(principio, fin+1);
            String[] items=extraeItemsDeArray(segmentoArray);
            for (String item: items){
            	resultado.add(new ClaveProcedencia(item, marcadorProcedencia));	
            }
            principio=fin;
        }
        return resultado;
    }
    
    /**
     * extrae claves que en el código son llamadas desde la rutina  "UtilidadesTM.reemplazaNombresColumna()"
     * @param linea
     * @param marcadorProcedencia
     * @return
     */
    private static Set<ClaveProcedencia> extraeDeReemplazaNombresColumna(String linea, String marcadorProcedencia){
        Set<ClaveProcedencia> resultado=new HashSet<ClaveProcedencia>();
    	int principio=0;
        while(principio!=-1){
            principio=linea.indexOf(".reemplazaNombresColumna(", principio);
        	if (principio<0)
        		break;
        	
        	principio=linea.lastIndexOf("{");
        	int fin=linea.indexOf("}", principio);
            if (principio>=linea.length() ||  fin>=linea.length() || principio<0 || fin<0){
            	logger.error("error en la línea=" + linea);
            	logger.error("principio=" + principio + " fin=" + fin);
            	System.exit(0);
            	break;
            }
            String segmentoArray=linea.substring(principio, fin+ 1);
            String[] items=extraeItemsDeArray(segmentoArray);
            for (String item: items){
            	resultado.add(new ClaveProcedencia(item, marcadorProcedencia));	
            }
            principio=fin;
        }
        return resultado;
    }     
    
    
//    public void reemplazaNombresColumna(DefaultTableModel tm, String[] clavesRecurso){

    /**
     * toma un segmento de código que representa un array, en la forma {"a", "b", "c"});
     * y devuelve los items individuales
     * @param segmentoArray
     * @return
     */
    private static String[] extraeItemsDeArray(String segmentoArray){
    	segmentoArray=segmentoArray.trim();
    	if (!segmentoArray.contains("{")){
    		logger.error("el segmento no contiene {");
    		System.exit(0);
    	}
    	if (!segmentoArray.contains("}")){
    		logger.error("el segmento no contiene }");
    		throw new RuntimeException();
//    		System.exit(0);
    	}
    	segmentoArray=segmentoArray.substring(segmentoArray.indexOf("{"));
    	segmentoArray=segmentoArray.substring(0, segmentoArray.indexOf("}"));
    	String[] items=segmentoArray.split(",");
    	for (int i=0; i<items.length; i++){
    		String item=items[i];
    		item=item.trim();
    		if (item.length()==0)
    			continue;
    		int principio=item.indexOf('"');
    		int fin=item.lastIndexOf('"');
    		item=item.substring(principio+1, fin);
    		items[i]=item;
    	}
    	return items;
    }
    
    /**
     * Recursively walk a directory tree and return a List of all
     * Files found; the List is sorted using File.compareTo.
     *
     * @param aStartingDir is a valid directory, which can be read.
     */
     static public List getFileListing( File aStartingDir ) throws FileNotFoundException{
       validateDirectory(aStartingDir);
       List result = new ArrayList();
       File[] filesAndDirs = aStartingDir.listFiles(filtroJavaNoEste);
       List filesDirs = Arrays.asList(filesAndDirs);
       Iterator filesIter = filesDirs.iterator();
       File file = null;
       while ( filesIter.hasNext() ) {
         file = (File)filesIter.next();
         result.add(file); //always add, even if directory
         if (!file.isFile()) {
           //must be a directory
           //recursive call!
           List deeperList = getFileListing(file);
           result.addAll(deeperList);
         }

       }
       Collections.sort(result);
       return result;
     }
     
     /**
      * Directory is valid if it exists, does not represent a file, and can be read.
      */
      static private void validateDirectory (File aDirectory) throws FileNotFoundException {
        if (aDirectory == null) {
          throw new IllegalArgumentException("Directory should not be null.");
        }
        if (!aDirectory.exists()) {
          throw new FileNotFoundException("Directory does not exist: " + aDirectory);
        }
        if (!aDirectory.isDirectory()) {
          throw new IllegalArgumentException("Is not a directory: " + aDirectory);
        }
        if (!aDirectory.canRead()) {
          throw new IllegalArgumentException("Directory cannot be read: " + aDirectory);
        }
      }
      
    public static ApplicationContext  creaContexto() {
		ApplicationContext contexto;
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		FileSystemResource res = new FileSystemResource(System.getProperty("user.dir") + File.separator + "daos.xml");
		reader.loadBeanDefinitions(res);
		res = new FileSystemResource(System.getProperty("user.dir") + File.separator  + "gerentes-datos.xml");
		reader.loadBeanDefinitions(res);
		res = new FileSystemResource(System.getProperty("user.dir") + File.separator  + "flexion.xml");
		reader.loadBeanDefinitions(res);
		res = new FileSystemResource(System.getProperty("user.dir") + File.separator  + "analisisMorfologico-utilidades.xml");
		reader.loadBeanDefinitions(res);
		contexto=new GenericApplicationContext(factory);
		return contexto;
	}
	
	
	static ApplicationContext contexto;
	static Connection con;

}
