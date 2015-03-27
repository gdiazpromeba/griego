package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.InterjeccionBean;
import com.kalos.beans.Significado;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.datos.dao.comunes.ModificaCodigoIndividual;
import com.kalos.datos.dao.comunes.SeleccionIds;
import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.recursos.Configuracion;
import com.kalos.utils.Listas;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

@SuppressWarnings("unchecked")
public class InterjeccionesDAOImpl extends JdbcDaoSupport implements InterjeccionesDAO {



    
    @Override
    public List<InterjeccionBean> seleccionaTodos() {
	return seleccionTodosConSig.execute();
    }

    @Override
    public List<String> seleccionaPorLetra(String letra) {
	return seleccionPorLetra.execute(letra);
    }

    @Override
    public List<String> seleccionaPorCanonica(String canonica) {
	return seleccionIdsPorCanonica.execute(canonica);
    }

    @Override
    public List<InterjeccionBean> seleccionaPorFormaSinSignificado(String forma) {
	return seleccionPorCanonicaConSig.execute(forma);
    }

    @Override
    public InterjeccionBean getInidvidual(String id) {
	List<String> ids=new ArrayList<String>();
	ids.add(id);
 	List<InterjeccionBean> beans=getRegistros(ids);
 	return beans.get(0);
    }

    @Override
    public void modificaCodigoIndividual(int nuevoCodigo, String id) {
	modificaCodigoIndividual.update(new Object[] { Integer.valueOf(nuevoCodigo), id });
    }



    
    public void initDao() throws Exception {
	super.initDao();
	puebla();
	borrado = new Borrado(getDataSource(), BORRADO_SQL);
	modificacion = new Modificacion(getDataSource());
	insercion = new Insercion(getDataSource());
	seleccionPorLetra = new SeleccionPorLetra(getDataSource());
	seleccionPorCanonicaConSig = new SeleccionPorCanonicaConSig(getDataSource());
	seleccionIdsPorCanonica = new SeleccionIdsPorCanonica(getDataSource());
	seleccionTodosConSig = new SeleccionTodosConSig(getDataSource());
	modificaCodigoIndividual = new ModificaCodigoIndividual(getDataSource(), MODIFICACION_CODIGO_SQL) ;
    }
    
    private void puebla() {
 	StringBuffer stringbuffer = new StringBuffer(200);
 	stringbuffer = new StringBuffer(200);
 	stringbuffer.append("SELECT   \n");
 	stringbuffer.append("  ITJ.INTERJECCION_ID,   \n");
 	stringbuffer.append("  ITJ.LETRA,   \n");
 	stringbuffer.append("  ITJ.CODIGO,   \n");
 	stringbuffer.append("  ITJ.INTERJECCION,   \n");
 	stringbuffer.append("  ITJ.PARTIC,   \n");
 	stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
 	stringbuffer.append("  SIG.VALOR   \n");
 	stringbuffer.append("FROM        \n");
 	stringbuffer.append("    INTERJECCION ITJ       \n");
 	stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
 	stringbuffer.append("      ON ITJ.INTERJECCION_ID=SIG.REFERENTE_ID       \n");
 	stringbuffer.append("WHERE  \n");
 	stringbuffer.append("  1=1   \n");
 	stringbuffer.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='").append(Configuracion.getIdiomaSignificados()).append("')    \n");
 	stringbuffer.append("ORDER BY \n");
 	stringbuffer.append(" ITJ.LETRA, \n");
 	stringbuffer.append(" ITJ.CODIGO \n");
 	SELECCION_TODOS_CON_SIGNIFICADO_SQL = stringbuffer.toString();
 	stringbuffer = new StringBuffer(200);
 	stringbuffer = new StringBuffer(200);
 	stringbuffer.append("SELECT   \n");
 	stringbuffer.append("  ITJ.INTERJECCION_ID,   \n");
 	stringbuffer.append("  ITJ.LETRA,   \n");
 	stringbuffer.append("  ITJ.CODIGO,   \n");
 	stringbuffer.append("  ITJ.INTERJECCION,   \n");
 	stringbuffer.append("  ITJ.PARTIC,   \n");
 	stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
 	stringbuffer.append("  SIG.VALOR   \n");
 	stringbuffer.append("FROM        \n");
 	stringbuffer.append("    INTERJECCION ITJ       \n");
 	stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
 	stringbuffer.append("      ON ITJ.INTERJECCION_ID=SIG.REFERENTE_ID       \n");
 	stringbuffer.append("WHERE  \n");
 	stringbuffer.append("  ITJ.INTERJECCION_ID IN (?)    \n");
 	stringbuffer.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='").append(Configuracion.getIdiomaSignificados()).append("')    \n");
 	stringbuffer.append("ORDER BY \n");
 	stringbuffer.append(" ITJ.LETRA, \n");
 	stringbuffer.append(" ITJ.CODIGO \n");
 	SELECCION_TODOS_CON_SIGNIFICADO_SQL = stringbuffer.toString(); 	
 	stringbuffer = new StringBuffer(200);
 	stringbuffer.append("SELECT   \n");
 	stringbuffer.append("  ITJ.INTERJECCION_ID,   \n");
 	stringbuffer.append("  ITJ.LETRA,   \n");
 	stringbuffer.append("  ITJ.CODIGO,   \n");
 	stringbuffer.append("  ITJ.INTERJECCION,   \n");
 	stringbuffer.append("  ITJ.PARTIC,   \n");
 	stringbuffer.append("  SIG.SIGNIFICADO_ID,   \n");
 	stringbuffer.append("  SIG.VALOR   \n");
 	stringbuffer.append("FROM        \n");
 	stringbuffer.append("    INTERJECCION ITJ       \n");
 	stringbuffer.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
 	stringbuffer.append("      ON ITJ.INTERJECCION_ID=SIG.REFERENTE_ID       \n");
 	stringbuffer.append("WHERE  \n");
 	stringbuffer.append("  ITJ.INTERJECCION=?    \n");
 	stringbuffer.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='").append(Configuracion.getIdiomaSignificados()).append("')    \n");
 	stringbuffer.append("ORDER BY \n");
 	stringbuffer.append(" ITJ.LETRA, \n");
 	stringbuffer.append(" ITJ.CODIGO \n");
 	SELECCION_POR_CANONICA_CON_SIGNIFICADO_SQL = stringbuffer.toString(); 	
 	stringbuffer = new StringBuffer(200);
 	stringbuffer.append("SELECT   \n");
 	stringbuffer.append("  ITJ.INTERJECCION_ID   \n");
 	stringbuffer.append("FROM        \n");
 	stringbuffer.append("    INTERJECCION ITJ       \n");
 	stringbuffer.append("WHERE  \n");
 	stringbuffer.append("  ITJ.LETRA=?    \n");
 	stringbuffer.append("ORDER BY \n");
 	stringbuffer.append(" ITJ.LETRA, \n");
 	stringbuffer.append(" ITJ.CODIGO \n");
 	SELECCION_POR_LETRA_SIN_SIGNIFICADO_SQL = stringbuffer.toString(); 
 	stringbuffer = new StringBuffer(200);
 	stringbuffer.append("SELECT   \n");
 	stringbuffer.append("  ITJ.INTERJECCION_ID   \n");
 	stringbuffer.append("FROM        \n");
 	stringbuffer.append("    INTERJECCION ITJ       \n");
 	stringbuffer.append("WHERE  \n");
 	stringbuffer.append("  ITJ.INTERJECCION=?    \n");
 	stringbuffer.append("ORDER BY \n");
 	stringbuffer.append(" ITJ.LETRA, \n");
 	stringbuffer.append(" ITJ.CODIGO \n");
 	SELECCION_IDS_POR_CANONICA = stringbuffer.toString();  	
 	stringbuffer = new StringBuffer(200);
 	stringbuffer.append("INSERT INTO INTERJECCIONES SET(   \n");
 	stringbuffer.append("  INTERJECCION_ID,   \n");
 	stringbuffer.append("  LETRA,   \n");
 	stringbuffer.append("  CODIGO,   \n");
 	stringbuffer.append("  INTERJECCION,   \n");
 	stringbuffer.append("  PARTIC   \n");
 	stringbuffer.append(" ) VALUES (    \n");
 	stringbuffer.append("  ?,?,?,?,?  \n");
 	stringbuffer.append(")     \n");
 	INSERCION_SQL = stringbuffer.toString();
 	stringbuffer = new StringBuffer(200);
 	stringbuffer.append("UPDATE INTERJECCIONES SET   \n");
 	stringbuffer.append("  LETRA=?,   \n");
 	stringbuffer.append("  CODIGO=?,   \n");
 	stringbuffer.append("  INTERJECCION=?,   \n");
 	stringbuffer.append("  PARTIC=?   \n");
 	stringbuffer.append("WHERE    \n");
 	stringbuffer.append("  INTERJECCION_ID=?   \n");
 	MODIFICACION_SQL = stringbuffer.toString();
 	stringbuffer = new StringBuffer(200);
 	stringbuffer.append("DELETE FROM INTERJECCIONES WHERE   \n");
 	stringbuffer.append("  INTERJECCION_ID=?  \n");
 	BORRADO_SQL = stringbuffer.toString();
 	stringbuffer = new StringBuffer(200);
 	stringbuffer.append("UPDATE INTERJECCIONES SET   \n");
	stringbuffer.append("  CODIGO=?   \n");
	stringbuffer.append("WHERE    \n");
	stringbuffer.append("  INTERJECCION_ID=?   \n");
	MODIFICACION_CODIGO_SQL = stringbuffer.toString(); 	
     }    
    
    private static String INSERCION_SQL;
    private static String MODIFICACION_SQL;
    private static String BORRADO_SQL;
    private static String SELECCION_POR_IDS_CON_SIGNIFICADO_SQL;
    private static String SELECCION_TODOS_CON_SIGNIFICADO_SQL;
    private static String SELECCION_POR_LETRA_SIN_SIGNIFICADO_SQL;
    private static String SELECCION_POR_CANONICA_CON_SIGNIFICADO_SQL;
    private static String SELECCION_IDS_POR_CANONICA;
    private static String MODIFICACION_CODIGO_SQL;
    private Insercion insercion;
    private Modificacion modificacion;
    private Borrado borrado;  
    private SeleccionPorLetra seleccionPorLetra;
    private SeleccionPorCanonicaConSig seleccionPorCanonicaConSig;
    private SeleccionIdsPorCanonica seleccionIdsPorCanonica;
    private SeleccionTodosConSig seleccionTodosConSig;
    private ModificaCodigoIndividual modificaCodigoIndividual;
    
    class Modificacion extends SqlUpdate {

	public Modificacion(DataSource datasource) {
	    super(datasource, MODIFICACION_SQL);
	    declareParameter(new SqlParameter(Types.CHAR));
	    declareParameter(new SqlParameter(Types.INTEGER));
	    declareParameter(new SqlParameter(Types.VARCHAR));
	    declareParameter(new SqlParameter(Types.CHAR));
	    declareParameter(new SqlParameter(Types.CHAR));
	}
    }   
    
    class Insercion extends SqlUpdate {

	public Insercion(DataSource datasource) {
	    super(datasource, INSERCION_SQL);
	    declareParameter(new SqlParameter(Types.CHAR));
	    declareParameter(new SqlParameter(Types.CHAR));
	    declareParameter(new SqlParameter(Types.INTEGER));
	    declareParameter(new SqlParameter(Types.VARCHAR));
	    declareParameter(new SqlParameter(Types.CHAR));
	}
    }  
    
    public void inserta(InterjeccionBean bean) {
	String id = com.kalos.datos.util.DBUtil.getHashableId();
	insercion.update(new Object[] { id, bean.getLetra(), Integer.valueOf(bean.getCodigo()), bean.getInterjeccion(),
		Particularidad.getString(bean.getPartic())
	});
	bean.setId(id);
    }
    
    public void modifica(InterjeccionBean bean) {
	modificacion.update(new Object[] { bean.getLetra(), Integer.valueOf(bean.getCodigo()), bean.getInterjeccion(),
		Particularidad.getString(bean.getPartic()), bean.getId()
	});
    }    

    public void borra(String s) {
	borrado.update(new Object[] { s });
    }    
    
    abstract class SeleccionAbstracta extends MappingSqlQuery {

	protected Object mapRow(ResultSet resultset, int j) throws SQLException {
	    InterjeccionBean m1 = new InterjeccionBean();
	    m1.setId(resultset.getString("INTERJECCION_ID"));
	    m1.setLetra(resultset.getString("LETRA"));
	    m1.setCodigo(resultset.getInt("CODIGO"));
	    m1.setInterjeccion(resultset.getString("INTERJECCION"));
	    m1.setPartic(Particularidad.getEnum(resultset.getString("PARTIC")));
	    Significado q1 = new Significado();
	    q1.setIdioma(Configuracion.getIdiomaSignificados());
	    q1.setReferenteId(m1.getId());
	    q1.setId(resultset.getString("SIGNIFICADO_ID"));
	    q1.setValor(resultset.getString("VALOR"));
	    HashMap<Idioma, Significado> hashmap = new HashMap<Idioma, Significado>();
	    hashmap.put(Idioma.getEnum(Configuracion.getIdiomaSignificados()), q1);
	    m1.setSignificados(hashmap);
	    return m1;
	}

	public SeleccionAbstracta(DataSource datasource, String s) {
	    super(datasource, s);
	}
    }  
    
    abstract class SeleccionAbstractaSinSignificado extends MappingSqlQuery {

	protected Object mapRow(ResultSet resultset, int j) throws SQLException {
	    InterjeccionBean m1 = new InterjeccionBean();
	    m1.setId(resultset.getString("INTERJECCION_ID"));
	    m1.setLetra(resultset.getString("LETRA"));
	    m1.setCodigo(resultset.getInt("CODIGO"));
	    m1.setInterjeccion(resultset.getString("INTERJECCION"));
	    m1.setPartic(Particularidad.getEnum(resultset.getString("PARTIC")));
	    return m1;
	}

	public SeleccionAbstractaSinSignificado(DataSource datasource, String s) {
	    super(datasource, s);
	}
    }  
    
    
    class SeleccionPorIds extends SeleccionAbstracta {
	
	public SeleccionPorIds(DataSource datasource, String sql) {
	    super(datasource, sql);
	}
    }   
    
    class SeleccionPorLetra extends SeleccionAbstractaSinSignificado {
	
	public SeleccionPorLetra(DataSource datasource) {
	    super(datasource, SELECCION_POR_LETRA_SIN_SIGNIFICADO_SQL);
	    declareParameter(new SqlParameter(Types.CHAR));
	}
    }
    
//    class SeleccionPorCanonicaSinSig extends SeleccionAbstractaSinSignificado {
//	
//	public SeleccionPorCanonicaSinSig(DataSource datasource) {
//	    super(datasource, SELECCION_POR_CANONICA_SIN_SIGNIFICADO_SQL);
//	    declareParameter(new SqlParameter(Types.VARCHAR));
//	}
//    }
    
    
    
    class SeleccionPorCanonicaConSig extends SeleccionAbstracta {
	
	public SeleccionPorCanonicaConSig(DataSource datasource) {
	    super(datasource, SELECCION_POR_CANONICA_CON_SIGNIFICADO_SQL);
	    declareParameter(new SqlParameter(Types.VARCHAR));
	}
    }      
    
    class SeleccionTodosConSig extends SeleccionAbstracta {
	
	public SeleccionTodosConSig(DataSource datasource) {
	    super(datasource, SELECCION_TODOS_CON_SIGNIFICADO_SQL);
	}
    }     
    
    
    public List<InterjeccionBean> getRegistros(List<String> ids) {
	List<List<String>> listaDeListas =  Listas.segmentos(ids, 500);
	List<InterjeccionBean> beans = new ArrayList<InterjeccionBean>();
	for (List<String> listaDeIds : listaDeListas) {
	    StringBuffer sb = new StringBuffer(500);
	    for (String id : listaDeIds) {
		sb.append("'").append(id).append("',");
	    }
	    String sql = SELECCION_POR_IDS_CON_SIGNIFICADO_SQL.replaceFirst("\\?", sb.toString());
	    SeleccionPorIds sel= new SeleccionPorIds(getDataSource(), sql);
	    List<InterjeccionBean> segmBeans = sel.execute();
	    beans.addAll(segmBeans);
	} 
	return beans;
    }
    
    class SeleccionIdsPorCanonica extends SeleccionIds {
	public SeleccionIdsPorCanonica(DataSource datasource) {
	    super(datasource, SELECCION_IDS_POR_CANONICA, "INTERJECCION_ID");
	    declareParameter(new SqlParameter(Types.VARCHAR));
	}
    }    

}
