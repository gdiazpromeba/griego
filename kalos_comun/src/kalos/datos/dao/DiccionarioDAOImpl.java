/*
 *  This program is an unpublished work fully protected by the United
 *  States copyright laws and is considered a trade secret belonging
 *  to Turner Entertainment Group. To the extent that this work may be
 *  considered "published,"
 *  the following notice applies:
 *
 *      "Copyright 2005, Turner Entertainment Group."
 *
 *  Any unauthorized use, reproduction, distribution, display,
 *  modification, or disclosure of this program is strictly prohibited.
 *
 */
/**
 * 
 */
package kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import kalos.beans.EntradaDiccionario;
import kalos.beans.IrrVerbo;
import kalos.beans.Significado;
import kalos.datos.dao.IrrVerbosDAOImpl.Insercion;
import kalos.datos.dao.comunes.ModificaCodigoIndividual;
import kalos.enumeraciones.Aumento;
import kalos.enumeraciones.Contraccion;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Idioma;
import kalos.enumeraciones.LugarSubcadena;
import kalos.enumeraciones.Modo;
import kalos.enumeraciones.Particularidad;
import kalos.enumeraciones.Propagacion;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.TipoPalabra;
import kalos.enumeraciones.Voz;
import kalos.recursos.Configuracion;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class DiccionarioDAOImpl extends JdbcDaoSupport implements DiccionarioDAO {

    private static String SELECCION_IDS_POR_LETRA;
    private static String SELECCION_IDS_POR_NEUTRALIZADA_LIKE_CON_SIG;
    private static String SELECCION_IDS_POR_TIPOS_CON_SIG;
    private static String SELECCION_IDS_POR_SIN_LARGAS_LIKE_CON_SIG;
    private static String SELECCION_IDS_POR_NORMAL_LIKE_CON_SIG;
    private static String SELECCION_DICCIONARIO_POR_IDS;
    private static String MODIFICA_CODIGO_INDIVIDUAL_SQL;
    private static String INSERCION_SQL;

    private void puebla() {
	StringBuffer sb = new StringBuffer(200);

	sb.append("SELECT \n");
	sb.append("  DIC.REFERENTE_ID \n");
	sb.append("FROM \n");
	sb.append("  DICCIONARIO DIC  \n");
	sb.append("WHERE \n");
	sb.append("  DIC.LETRA=? \n");
	sb.append("ORDER BY \n");
	sb.append("  DIC.CODIGO \n");

	SELECCION_IDS_POR_LETRA = sb.toString();

	sb = new StringBuffer(200);
	sb.append("SELECT \n");
	sb.append("  DIC.REFERENTE_ID \n");
	sb.append("FROM \n");
	sb.append("  DICCIONARIO DIC \n");
	sb.append("  INNER JOIN SIGNIFICADOS SIG  ON SIG.REFERENTE_ID=DIC.REFERENTE_ID \n");
	sb.append("WHERE \n");
	sb.append("  DIC.NEUTRALIZADA LIKE __NEUTRALIZADA__ \n");
	sb.append("  AND SIG.IDIOMA='" + Configuracion.getIdiomaSignificados() + "'  \n");
	sb.append("  AND DIC.TIPO_PALABRA IN (__TIPO_PALABRA__)  \n");
	sb.append("ORDER BY \n");
	sb.append("  DIC.CODIGO \n");

	SELECCION_IDS_POR_NEUTRALIZADA_LIKE_CON_SIG = sb.toString();

	sb = new StringBuffer(200);
	sb.append("SELECT \n");
	sb.append("  DIC.REFERENTE_ID \n");
	sb.append("FROM \n");
	sb.append("  DICCIONARIO DIC \n");
	sb.append("  INNER JOIN SIGNIFICADOS SIG  ON SIG.REFERENTE_ID=DIC.REFERENTE_ID \n");
	sb.append("WHERE \n");
	sb.append("  SIG.IDIOMA='" + Configuracion.getIdiomaSignificados() + "'  \n");
	sb.append("  AND DIC.TIPO_PALABRA IN (__TIPO_PALABRA__)  \n");
	sb.append("ORDER BY \n");
	sb.append("  DIC.CODIGO \n");

	SELECCION_IDS_POR_TIPOS_CON_SIG = sb.toString();

	sb = new StringBuffer(200);
	sb.append("SELECT \n");
	sb.append("  DIC.REFERENTE_ID \n");
	sb.append("FROM \n");
	sb.append("  DICCIONARIO DIC \n");
	sb.append("  INNER JOIN SIGNIFICADOS SIG  ON SIG.REFERENTE_ID=DIC.REFERENTE_ID \n");
	sb.append("WHERE \n");
	sb.append("  DIC.SIN_LARGAS LIKE __SIN_LARGAS__ \n");
	sb.append("  AND SIG.IDIOMA='" + Configuracion.getIdiomaSignificados() + "'  \n");
	sb.append("  AND DIC.TIPO_PALABRA IN (__TIPO_PALABRA__)  \n");
	sb.append("ORDER BY \n");
	sb.append("  DIC.CODIGO \n");

	SELECCION_IDS_POR_SIN_LARGAS_LIKE_CON_SIG = sb.toString();

	sb = new StringBuffer(200);
	sb.append("SELECT \n");
	sb.append("  DIC.REFERENTE_ID \n");
	sb.append("FROM \n");
	sb.append("  DICCIONARIO DIC \n");
	sb.append("WHERE \n");
	sb.append("  DIC.NORMAL LIKE ? \n");
	sb.append("  AND DIC.TIPO_PALABRA IN (__TIPO_PALABRA__)  \n");
	sb.append("ORDER BY \n");
	sb.append("  DIC.CODIGO \n");
	SELECCION_IDS_POR_NORMAL_LIKE_CON_SIG = sb.toString();

	sb = new StringBuffer(200);
	sb.append("SELECT \n");
	sb.append("	DIC.REFERENTE_ID,\n");
	sb.append("	DIC.NORMAL,\n");
	sb.append("	DIC.NORMAL_UNICODE,\n");
	sb.append("	DIC.TIPO_PALABRA,\n");
	sb.append("	SIG.SIGNIFICADO_ID,\n");
	sb.append("	SIG.VALOR\n");
	sb.append("FROM\n");
	sb.append("	DICCIONARIO DIC\n");
	sb.append(" INNER JOIN SIGNIFICADOS SIG\n");
	sb.append(" ON DIC.REFERENTE_ID=SIG.REFERENTE_ID\n");
	sb.append("WHERE\n");
	sb.append(" SIG.IDIOMA='" + Configuracion.getIdiomaSignificados() + "'\n");
	sb.append(" AND DIC.REFERENTE_ID IN (?)\n");
	sb.append("ORDER BY \n");
	sb.append("  DIC.CODIGO \n");

	SELECCION_DICCIONARIO_POR_IDS = sb.toString();

	sb = new StringBuffer(200);
	sb.append("INSERT INTO DICCIONARIO ( \n");
	sb.append("	REFERENTE_ID,    \n");
	sb.append("	LETRA,           \n");
	sb.append("	CODIGO,          \n");
	sb.append("	TIPO_PALABRA,    \n");
	sb.append("	NORMAL,          \n");
	sb.append("	SIN_LARGAS,      \n");
	sb.append("	NEUTRALIZADA,    \n");
	sb.append("	NORMAL_UNICODE   \n");
	sb.append(") VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )  \n");
	INSERCION_SQL = sb.toString();

	sb = new StringBuffer(200);
	sb.append("UPDATE DICCIONARIO SET   \n");
	sb.append("  CODIGO=?   \n");
	sb.append("WHERE    \n");
	sb.append("  REFERENTE_ID=?   \n");

	MODIFICA_CODIGO_INDIVIDUAL_SQL = sb.toString();

    }
    
    
	//inserción
	class Insercion extends SqlUpdate {
		public Insercion(DataSource dataSource) {
			super(dataSource, INSERCION_SQL);
			declareParameter(new SqlParameter(Types.CHAR));  //referente_id
			declareParameter(new SqlParameter(Types.CHAR));  //letra
			declareParameter(new SqlParameter(Types.INTEGER));  //codigo
			declareParameter(new SqlParameter(Types.CHAR));   //tipo palabra
			declareParameter(new SqlParameter(Types.VARCHAR));  //normal
			declareParameter(new SqlParameter(Types.VARCHAR));  //sin largas
			declareParameter(new SqlParameter(Types.VARCHAR));  //neutralizada
			declareParameter(new SqlParameter(Types.VARCHAR));  //normal unicode
		}
	}   
	
	private Insercion insercion;
	
	@Override
	public void inserta(EntradaDiccionario bean) {
                

		insercion.update(new Object[] {
			  bean.getReferenteId(),
			  bean.getLetra(),
			  bean.getCodigo(),
			  bean.getTipoPalabra().getStringCorta(),
			  bean.getNormalBeta(),
			  bean.getSinLargasBeta(),
			  bean.getNeutralizadaBeta(),
			  bean.getUnicode()
		}); 

	}	

    // selección de registros a mostrar del diccionario, dados los IDs
    abstract class SeleccionAbstracta extends MappingSqlQuery {
	public SeleccionAbstracta(DataSource dataSource, String sql) {
	    super(dataSource, sql);
	}

	protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	    EntradaDiccionario bean = new EntradaDiccionario();
	    bean.setId(rs.getString("REFERENTE_ID"));
	    bean.setNormal(rs.getString("NORMAL_UNICODE"));
	    bean.setTipoPalabra(TipoPalabra.getEnum(rs.getString("TIPO_PALABRA")));
	    bean.setNormalBeta(rs.getString("NORMAL"));
	    // un significado
	    Significado sig = new Significado();
	    sig.setIdioma(Configuracion.getIdiomaSignificados());
	    sig.setReferenteId(bean.getId());
	    sig.setId(rs.getString("SIGNIFICADO_ID"));
	    sig.setValor(rs.getString("VALOR"));
	    Map<Idioma, Significado> mapSignificados = new HashMap<Idioma, Significado>();
	    mapSignificados.put(Idioma.getEnum(Configuracion.getIdiomaSignificados()), sig);
	    bean.setSignificados(mapSignificados);
	    return bean;
	}
    }

    // selección de registros a mostrar del diccionario, dados los IDs
    class SeleccionDiccionarioPorIds extends SeleccionAbstracta {
	public SeleccionDiccionarioPorIds(DataSource dataSource, String sql) {
	    super(dataSource, sql);
	}
    }

    // select de los ids solamente
    class SeleccionIds extends MappingSqlQuery {
	public SeleccionIds(DataSource dataSource, String sql) {
	    super(dataSource, sql);
	}

	protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	    String id = rs.getString("REFERENTE_ID");
	    return id;
	}
    }

    // selección por letra inicial
    private class SeleccionPorLetra extends SeleccionIds {
	public SeleccionPorLetra(DataSource dataSource) {
	    super(dataSource, SELECCION_IDS_POR_LETRA);
	    declareParameter(new SqlParameter(Types.CHAR));
	}
    }

    private SeleccionPorLetra seleccionPorLetra;

    public List<String> seleccionaPorLetra(String letra) {
	return seleccionPorLetra.execute(new Object[] { letra });
    }

    /*
     * (non-Javadoc)
     * 
     * @see kalos.dao.DiccionarioDAO#selectPorNeutralizadaLike(java.lang.String,
     * kalos.enumeraciones.LugarSubcadena)
     */
    public List<String> seleccionaPorNeutralizada(String neutralizada, LugarSubcadena lugar, List<TipoPalabra> tipos) {
	String parametro = null;
	switch (lugar) {
	case Principio:
	    parametro = "'" + neutralizada + "%'";
	    break;
	case Medio:
	    parametro = "'%" + neutralizada + "%'";
	    break;
	case Fin:
	    parametro = "'%" + neutralizada + "'";
	    break;
	case Exacto:
	    parametro = "'" + neutralizada + "'";
	}
	String sql = SELECCION_IDS_POR_NEUTRALIZADA_LIKE_CON_SIG.replaceFirst("__NEUTRALIZADA__", parametro);
	sql = sql.replaceFirst("__TIPO_PALABRA__", tiposAString(tipos));
	SeleccionIds seleccionIds = new SeleccionIds(getDataSource(), sql);
	List<String> ids = seleccionIds.execute();
	return ids;
    }

    /**
     * selecciona palabras del diccionario exclusivamente por los tipos de
     * palabra
     * 
     * @param tipos
     * @return
     */
    public List<String> seleccionaPorTipos(List<TipoPalabra> tipos) {
	String sql = SELECCION_IDS_POR_TIPOS_CON_SIG;
	sql = sql.replaceFirst("__TIPO_PALABRA__", tiposAString(tipos));
	SeleccionIds seleccionIds = new SeleccionIds(getDataSource(), sql);
	List<String> ids = seleccionIds.execute();
	return ids;
    }

    /**
     * convierte el array de tipos de palabra en la parte paaramétrica de una
     * cadena SQL
     * 
     * @param tipos
     * @return
     */
    private String tiposAString(List<TipoPalabra> tipos) {
	StringBuffer sb = new StringBuffer(200);
	for (TipoPalabra tipo : tipos) {
	    sb.append("'" + TipoPalabra.getString(tipo) + "',");
	}
	if (sb.length() > 0) {
	    sb.delete(sb.length() - 1, sb.length()); // la coma
	}
	return sb.toString();
    }

    public List<String> seleccionaPorSinLargas(String neutralizada, LugarSubcadena lugar, List<TipoPalabra> tipos) {
	String parametro = null;
	switch (lugar) {
	case Principio:
	    parametro = "'" + neutralizada + "%'";
	    break;
	case Medio:
	    parametro = "'%" + neutralizada + "%'";
	    break;
	case Fin:
	    parametro = "'%" + neutralizada + "'";
	    break;
	case Exacto:
	    parametro = "'" + neutralizada + "'";
	}
	String sql = SELECCION_IDS_POR_SIN_LARGAS_LIKE_CON_SIG.replaceFirst("__SIN_LARGAS__", parametro);
	sql = sql.replaceFirst("__TIPO_PALABRA__", tiposAString(tipos));
	SeleccionIds seleccionIds = new SeleccionIds(getDataSource(), sql);
	List<String> ids = seleccionIds.execute();
	return ids;
    }

    public List<String> seleccionaPorNormal(String neutralizada, LugarSubcadena lugar, List<TipoPalabra> tipos) {
	String parametro = null;
	switch (lugar) {
	case Principio:
	    parametro = "'" + neutralizada + "%'";
	    break;
	case Medio:
	    parametro = "'%" + neutralizada + "%'";
	    break;
	case Fin:
	    parametro = "'%" + neutralizada + "'";
	    break;
	case Exacto:
	    parametro = "'" + neutralizada + "'";
	}
	parametro = StringUtils.replace(parametro, "_", "\\\\_");
	String sql = StringUtils.replace(SELECCION_IDS_POR_NORMAL_LIKE_CON_SIG, "?", parametro);
	sql = sql.replaceFirst("__TIPO_PALABRA__", tiposAString(tipos));
	SeleccionIds seleccionIds = new SeleccionIds(getDataSource(), sql);
	List<String> ids = seleccionIds.execute();
	return ids;
    }

    private ModificaCodigoIndividual modificaCodigoIndividual;

    public void modificaCodigoIndividual(int nuevoCodigo, String idReferente) {
	modificaCodigoIndividual.update(new Object[] { nuevoCodigo, idReferente });
    }

    // selección de registros a mostrar de la tabla "verbos", dados los IDs
    class SeleccionPorIds extends SeleccionAbstracta {
	public SeleccionPorIds(DataSource dataSource, String sql) {
	    super(dataSource, sql);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see kalos.dao.DiccionarioDAO#getEntradasDiccionario(java.util.List)
     */
    public List<EntradaDiccionario> getRegistros(List<String> ids) {
	List<EntradaDiccionario> resultado = new ArrayList<EntradaDiccionario>();
	int restantes = ids.size();
	int comienzo = 0;
	int segmento = 1000;
	while (restantes > 0) {
	    List<String> subIds = ids.subList(comienzo, Math.min(comienzo + segmento, ids.size()));
	    StringBuffer idsSeparadosComa = new StringBuffer(500);
	    for (String id : subIds) {
		idsSeparadosComa.append("'" + id + "',");
	    }
	    idsSeparadosComa.deleteCharAt(idsSeparadosComa.length() - 1);
	    String sql = SELECCION_DICCIONARIO_POR_IDS.replaceFirst("\\?", idsSeparadosComa.toString());

	    SeleccionPorIds seleccionPorIds = new SeleccionPorIds(getDataSource(), sql);
	    List<EntradaDiccionario> entradasDic = seleccionPorIds.execute();
	    resultado.addAll(entradasDic);
	    comienzo += segmento;
	    restantes -= segmento;
	}
	return resultado;
    }

    public void initDao() throws Exception {
	super.initDao();
	puebla();
	modificaCodigoIndividual = new ModificaCodigoIndividual(getDataSource(), MODIFICA_CODIGO_INDIVIDUAL_SQL);
	seleccionPorLetra = new SeleccionPorLetra(getDataSource());
	insercion = new Insercion(getDataSource());
    }

}
