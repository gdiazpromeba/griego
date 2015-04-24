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
package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.IrrAdjetivoIndividual;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.datos.dao.comunes.SeleccionIds;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.GradoComparacion;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class IrrAdjetivosIndividualesDAOImpl extends JdbcDaoSupport implements IrrAdjetivosIndividualesDAO {

    private static String SELECCION_POR_ADJETIVO_SQL;
    private static String SELECCION_INDIVIDUAL_SQL;
    private static String SELECCION_POR_FORMA_SQL;
    private static String SELECCION_POR_IDS_SQL;
    private static String SELECCION_PARTICS_SQL;
    private static String INSERCION_SQL;
    private static String MODIFICACION_SQL;
    private static String BORRADO_SQL;

    private void puebla() {
	StringBuffer sb = new StringBuffer(200);
	sb.append("SELECT   \n");
	sb.append("  IAE.IRR_ADJETIVO_INDIVIDUAL_ID   \n");
	sb.append("FROM        \n");
	sb.append("   IRR_ADJETIVOS_INDIVIDUALES IAE       \n");
	sb.append("WHERE  \n");
	sb.append("  IAE.ADJETIVO_ID=?    \n");
	sb.append("ORDER BY  \n");
	sb.append("  IAE.PARTIC,   \n");
	sb.append("  IAE.GENERO,   \n");
	sb.append("  IAE.NUMERO,   \n");
	sb.append("  IAE.CASO,   \n");
	sb.append("  IAE.SUBINDICE   \n");

	SELECCION_POR_ADJETIVO_SQL = sb.toString();

	sb = new StringBuffer(200);
	sb.append("SELECT   \n");
	sb.append("  IAE.IRR_ADJETIVO_INDIVIDUAL_ID,   \n");
	sb.append("  IAE.ADJETIVO_ID,   \n");
	sb.append("  IAE.GENERO,   \n");
	sb.append("  IAE.PARTIC,   \n");
	sb.append("  IAE.CASO,   \n");
	sb.append("  IAE.NUMERO,   \n");
	sb.append("  IAE.GRADO,   \n");
	sb.append("  IAE.SUBINDICE,   \n");
	sb.append("  IAE.FORMA   \n");
	sb.append("FROM        \n");
	sb.append("   IRR_ADJETIVOS_INDIVIDUALES IAE       \n");
	sb.append("WHERE  \n");
	sb.append("  IAE.IRR_ADJETIVO_INDIVIDUAL_ID=?   \n");

	SELECCION_INDIVIDUAL_SQL = sb.toString();

	sb = new StringBuffer(200);
	sb.append("SELECT   \n");
	sb.append("  IAE.IRR_ADJETIVO_INDIVIDUAL_ID,   \n");
	sb.append("  IAE.ADJETIVO_ID,   \n");
	sb.append("  IAE.GENERO,   \n");
	sb.append("  IAE.PARTIC,   \n");
	sb.append("  IAE.CASO,   \n");
	sb.append("  IAE.NUMERO,   \n");
	sb.append("  IAE.GRADO,   \n");
	sb.append("  IAE.SUBINDICE,   \n");
	sb.append("  IAE.FORMA   \n");
	sb.append("FROM        \n");
	sb.append("   IRR_ADJETIVOS_INDIVIDUALES IAE       \n");
	sb.append("WHERE  \n");
	sb.append("  IAE.FORMA=?   \n");

	SELECCION_POR_FORMA_SQL = sb.toString();

	sb = new StringBuffer(200);
	sb.append("SELECT   \n");
	sb.append("  IAE.IRR_ADJETIVO_INDIVIDUAL_ID,   \n");
	sb.append("  IAE.ADJETIVO_ID,   \n");
	sb.append("  IAE.GENERO,   \n");
	sb.append("  IAE.PARTIC,   \n");
	sb.append("  IAE.CASO,   \n");
	sb.append("  IAE.NUMERO,   \n");
	sb.append("  IAE.GRADO,   \n");
	sb.append("  IAE.SUBINDICE,   \n");
	sb.append("  IAE.FORMA   \n");
	sb.append("FROM        \n");
	sb.append("   IRR_ADJETIVOS_INDIVIDUALES IAE       \n");
	sb.append("WHERE  \n");
	sb.append("  IAE.IRR_ADJETIVO_INDIVIDUAL_ID IN (?)   \n");

	SELECCION_POR_IDS_SQL = sb.toString();

	sb = new StringBuffer();
	sb.append("select DISTINCT  \n");
	sb.append("  IRR.PARTIC \n");
	sb.append("FROM  \n");
	sb.append("  IRR_ADJETIVOS_INDIVIDUALES IRR \n");
	sb.append("    INNER JOIN PARTICULARIDADES PAR \n");
	sb.append("      ON PAR.PARTIC=IRR.PARTIC \n");
	sb.append("WHERE \n");
	sb.append("  ADJETIVO_ID=? \n");
	sb.append("ORDER BY \n");
	sb.append("  PAR.ORDEN \n");

	SELECCION_PARTICS_SQL = sb.toString();

	sb = new StringBuffer(200);
	sb.append("INSERT IRR_ADJETIVOS_INDIVIDUALES(   \n");
	sb.append("  IRR_ADJETIVO_INDIVIDUAL_ID,   \n");
	sb.append("  ADJETIVO_ID,   \n");
	sb.append("  GENERO,   \n");
	sb.append("  PARTIC,   \n");
	sb.append("  NUMERO,   \n");
	sb.append("  CASO,   \n");
	sb.append("  GRADO,   \n");
	sb.append("  SUBINDICE,   \n");
	sb.append("  FORMA   \n");
	sb.append(" ) VALUES (    \n");
	sb.append("  ?,?,?,?,?,?,?,?,?  \n");
	sb.append(")     \n");

	INSERCION_SQL = sb.toString();

	sb = new StringBuffer(200);
	sb.append("UPDATE IRR_ADJETIVOS_INDIVIDUALES SET   \n");
	sb.append("  ADJETIVO_ID=?,   \n");
	sb.append("  GENERO=?,   \n");
	sb.append("  PARTIC=?,   \n");
	sb.append("  NUMERO=?,   \n");
	sb.append("  CASO=?,   \n");
	sb.append("  GRADO=?,   \n");
	sb.append("  SUBINDICE=?,   \n");
	sb.append("  FORMA=?   \n");
	sb.append("WHERE    \n");
	sb.append("  IRR_ADJETIVO_INDIVIDUAL_ID=?   \n");

	MODIFICACION_SQL = sb.toString();

	sb = new StringBuffer(200);
	sb.append("DELETE FROM IRR_ADJETIVOS_INDIVIDUALES WHERE   \n");
	sb.append("  IRR_ADJETIVO_INDIVIDUAL_ID=?  \n");

	BORRADO_SQL = sb.toString();

    }

    // general select
    abstract class SeleccionAbstracta extends MappingSqlQuery {
	public SeleccionAbstracta(DataSource dataSource, String sql) {
	    super(dataSource, sql);
	}

	protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	    IrrAdjetivoIndividual bean = new IrrAdjetivoIndividual();
	    bean.setId(rs.getString("IRR_ADJETIVO_INDIVIDUAL_ID"));
	    bean.setAdjetivoId(rs.getString("ADJETIVO_ID"));
	    bean.setGenero(Genero.getEnum(rs.getString("GENERO")));
	    bean.setParticularidad(Particularidad.getEnum(rs.getString("PARTIC")));
	    bean.setNumero(Numero.getEnum(rs.getInt("NUMERO")));
	    bean.setCaso(Caso.getEnum(rs.getInt("CASO")));
	    bean.setGrado(GradoComparacion.getEnum(rs.getString("GRADO")));
	    bean.setSubindice(rs.getInt("SUBINDICE"));
	    bean.setForma(rs.getString("FORMA"));
	    return bean;
	}
    }

    class SeleccionPorForma extends SeleccionAbstracta {
	public SeleccionPorForma(DataSource dataSource) {
	    super(dataSource, SELECCION_POR_FORMA_SQL);
	    declareParameter(new SqlParameter(Types.VARCHAR));
	}
    }

    private SeleccionPorForma seleccionPorForma;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#seleccionaPorForma(java.lang
     * .String)
     */
    @Override
    public List<IrrAdjetivoIndividual> seleccionaPorForma(String forma) {
	return seleccionPorForma.execute(new Object[] { forma });
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
     * @see
     * com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#getRegistros(java.util.List)
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#getRegistros(java.util.List)
     */
    @Override
    public List<IrrAdjetivoIndividual> getRegistros(List<String> ids) {
	StringBuffer idsSeparadosComa = new StringBuffer(500);
	for (String id : ids) {
	    idsSeparadosComa.append("'" + id + "',");
	}
	if (idsSeparadosComa.length() > 0)
	    idsSeparadosComa.deleteCharAt(idsSeparadosComa.length() - 1);
	else
	    return new ArrayList<IrrAdjetivoIndividual>();
	String sql = SELECCION_POR_IDS_SQL.replaceFirst("\\?", idsSeparadosComa.toString());

	SeleccionPorIds seleccionPorIds = new SeleccionPorIds(getDataSource(), sql);
	List<IrrAdjetivoIndividual> entradasDic = seleccionPorIds.execute();
	return entradasDic;
    }

    // selección por adjetivo
    class SeleccionPorAdjetivo extends SeleccionIds {
	public SeleccionPorAdjetivo(DataSource dataSource) {
	    super(dataSource, SELECCION_POR_ADJETIVO_SQL, "IRR_ADJETIVO_INDIVIDUAL_ID");
	    declareParameter(new SqlParameter(Types.CHAR));

	}
    }

    SeleccionPorAdjetivo seleccionPorAdjetivo;

    /*
     * (non-Javadoc)
     * 
     * @see
     * kalos.dao.IrrAdjetivosEnterosDAO#seleccionaPorAdjetivo(java.lang.String)
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#seleccionaPorAdjetivo(java
     * .lang.String)
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#seleccionaPorAdjetivo(java
     * .lang.String)
     */
    @Override
    public List<String> seleccionaPorAdjetivo(String adjetivoId) {
	List<String> ids = seleccionPorAdjetivo.execute(new Object[] { adjetivoId });
	return ids;
    }

    // selección individual
    class SeleccionIndividual extends SeleccionAbstracta {
	public SeleccionIndividual(DataSource dataSource) {
	    super(dataSource, SELECCION_INDIVIDUAL_SQL);
	    declareParameter(new SqlParameter(Types.CHAR));
	}
    }

    private SeleccionIndividual seleccionIndividual;

    /*
     * (non-Javadoc)
     * 
     * @see
     * kalos.dao.IrrAdjetivosEnterosDAO#seleccionIndividual(java.lang.String)
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#seleccionIndividual(java.
     * lang.String)
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#seleccionIndividual(java.
     * lang.String)
     */
    @Override
    public IrrAdjetivoIndividual seleccionIndividual(String irrAdjetivoIndividualId) {
	List<IrrAdjetivoIndividual> lstAux = seleccionIndividual.execute(new Object[] { irrAdjetivoIndividualId });
	if (lstAux.size() == 0)
	    return null;
	else
	    return lstAux.get(0);
    }

    // selección de partics
    class SeleccionPartics extends MappingSqlQuery {
	public SeleccionPartics(DataSource dataSource) {
	    super(dataSource, SELECCION_PARTICS_SQL);
	    declareParameter(new SqlParameter(Types.CHAR));
	}

	protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	    String partic = rs.getString("CLAVE_VALOR");
	    return partic;
	}
    }

    private SeleccionPartics seleccionPartics;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#seleccionaPartics(java.lang
     * .String)
     */
    @Override
    public List<String> seleccionaPartics(String idAdjetivo) {
	List<String> resultado = seleccionPartics.execute(new Object[] { idAdjetivo });
	return resultado;
    }

    // inserción
    class Insercion extends SqlUpdate {
	public Insercion(DataSource dataSource) {
	    super(dataSource, INSERCION_SQL);
	    declareParameter(new SqlParameter(Types.CHAR)); // irr_adj_indiv_id
	    declareParameter(new SqlParameter(Types.CHAR)); // adjetivo_id
	    declareParameter(new SqlParameter(Types.CHAR)); // genero
	    declareParameter(new SqlParameter(Types.CHAR)); // partic
	    declareParameter(new SqlParameter(Types.INTEGER)); // numero
	    declareParameter(new SqlParameter(Types.INTEGER)); // caso
	    declareParameter(new SqlParameter(Types.CHAR)); // grado
	    declareParameter(new SqlParameter(Types.INTEGER)); // subíndice
	    declareParameter(new SqlParameter(Types.VARCHAR)); // forma
	}
    }

    private Insercion insercion;

    /*
     * (non-Javadoc)
     * 
     * @see kalos.dao.AdjetivoDAO#inserta(kalos.beans.AdjetivoBean)
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * kalos.dao.IrrAdjetivosEnterosDAO#inserta(kalos.beans.IrrAdjetivoEntero)
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#inserta(kalos.beans.
     * IrrAdjetivoIndividual)
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#inserta(kalos.beans.
     * IrrAdjetivoIndividual)
     */
    @Override
    public void inserta(IrrAdjetivoIndividual iae) {
	String pk = com.kalos.datos.util.DBUtil.getHashableId();
	insercion.update(new Object[] { pk, iae.getAdjetivoId(), iae.getGenero().valorEntero(),
		iae.getParticularidad().abreviatura(), iae.getNumero().valorEntero(),
		iae.getCaso().valorEntero(), iae.getGrado().abreviatura(), iae.getSubindice(),
		iae.getForma() });
	iae.setId(pk);
    }

    // update
    class Modificacion extends SqlUpdate {
	public Modificacion(DataSource dataSource) {
	    super(dataSource, MODIFICACION_SQL);
	    declareParameter(new SqlParameter(Types.CHAR)); // adjetivo_id
	    declareParameter(new SqlParameter(Types.CHAR)); // genero
	    declareParameter(new SqlParameter(Types.CHAR)); // partic
	    declareParameter(new SqlParameter(Types.INTEGER)); // numero
	    declareParameter(new SqlParameter(Types.INTEGER)); // caso
	    declareParameter(new SqlParameter(Types.CHAR)); // grado
	    declareParameter(new SqlParameter(Types.INTEGER)); // subíndice
	    declareParameter(new SqlParameter(Types.VARCHAR)); // forma
	    // where
	    declareParameter(new SqlParameter(Types.CHAR)); // irr_adj_indiv_id
	}
    }

    private Modificacion modificacion;

    /*
     * (non-Javadoc)
     * 
     * @see
     * kalos.dao.IrrAdjetivosEnterosDAO#modifica(kalos.beans.IrrAdjetivoEntero)
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#modifica(kalos.beans.
     * IrrAdjetivoIndividual)
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#modifica(kalos.beans.
     * IrrAdjetivoIndividual)
     */
    @Override
    public void modifica(IrrAdjetivoIndividual bean) {
	modificacion.update(new Object[] { bean.getAdjetivoId(), bean.getGenero().valorLetra(),
		bean.getParticularidad().abreviatura(), bean.getNumero().valorEntero(),
		bean.getCaso().valorEntero(), bean.getGrado().abreviatura(), bean.getSubindice(),
		bean.getForma(),
		// where
		bean.getId() });
    }

    private Borrado borrado;

    /*
     * (non-Javadoc)
     * 
     * @see kalos.dao.AdjetivoDAO#borra(java.lang.String)
     */
    /*
     * (non-Javadoc)
     * 
     * @see kalos.dao.IrrAdjetivosEnterosDAO#borra(java.lang.String)
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#borra(java.lang.String)
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.kalos.datos.dao.IrrAdjetivosIndividualesDAO#borra(java.lang.String)
     */
    @Override
    public void borra(String cmsGroupId) {
	borrado.update(new Object[] { cmsGroupId, });
    }

    public void initDao() throws Exception {
	super.initDao();
	puebla();
	seleccionPorAdjetivo = new SeleccionPorAdjetivo(getDataSource());
	seleccionIndividual = new SeleccionIndividual(getDataSource());
	seleccionPartics = new SeleccionPartics(getDataSource());
	seleccionPorForma = new SeleccionPorForma(getDataSource());
	insercion = new Insercion(getDataSource());
	borrado = new Borrado(getDataSource(), BORRADO_SQL);
	modificacion = new Modificacion(getDataSource());
    }

}
