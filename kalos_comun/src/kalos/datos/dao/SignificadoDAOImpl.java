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
import java.util.List;

import javax.sql.DataSource;

import kalos.beans.Significado;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class SignificadoDAOImpl extends JdbcDaoSupport implements  SignificadoDAO {

	private static String SELECCION_POR_REFERENTE_SQL;
	private static String SELECCION_INDIVIDUAL_SQL;
	private static String INSERCION_SQL;
	private static String MODIFICACION_SQL;
	private static String BORRADO_SQL;

	private void puebla() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  SIG.SIGNIFICADO_ID,   \n");
		sb.append("  SIG.IDIOMA,   \n");
		sb.append("  SIG.REFERENTE_ID,   \n");
		sb.append("  SIG.VALOR   \n");
		sb.append("FROM        \n");
		sb.append("  SIGNIFICADOS SIG       \n");
		sb.append("WHERE  \n");
		sb.append("  SIG.REFERENTE_ID=?    \n");
		sb.append("ORDER BY  \n");
		sb.append("  SIG.IDIOMA   \n");

		SELECCION_POR_REFERENTE_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  SIG.SIGNIFICADO_ID,   \n");
		sb.append("  SIG.IDIOMA,   \n");
		sb.append("  SIG.REFERENTE_ID,   \n");
		sb.append("  SIG.VALOR   \n");
		sb.append("FROM        \n");
		sb.append("  SIGNIFICADOS SIG  \n");
		sb.append("WHERE  \n");
		sb.append("  SIG.SIGNIFICADO_ID=?    \n");
		sb.append("ORDER BY  \n");
		sb.append("  SIG.IDIOMA   \n");

		SELECCION_INDIVIDUAL_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("INSERT SIGNIFICADOS(   \n");
		sb.append("  SIGNIFICADO_ID,   \n");
		sb.append("  IDIOMA,   \n");
		sb.append("  REFERENTE_ID,   \n");
		sb.append("  VALOR   \n");
		sb.append(" ) VALUES (    \n");
		sb.append("  ?,?,?,?     \n");
		sb.append(")     \n");

		INSERCION_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("UPDATE SIGNIFICADOS SET   \n");
		sb.append("  IDIOMA=?,   \n");
		sb.append("  REFERENTE_ID=?,   \n");
		sb.append("  VALOR=?   \n");
		sb.append("WHERE    \n");
		sb.append("  SIGNIFICADO_ID=?   \n");

		MODIFICACION_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("DELETE FROM SIGNIFICADOS WHERE   \n");
		sb.append("  SIGNIFICADO_ID=?  \n");

		BORRADO_SQL = sb.toString();

	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Significado si = new Significado();
			si.setId(rs.getString("SIGNIFICADO_ID"));
			si.setIdioma(rs.getString("IDIOMA"));
			si.setReferenteId(rs.getString("REFERENTE_ID"));
			si.setValor(rs.getString("VALOR"));
			return si;
		}
	}

	//selección de todos
	class SeleccionPorReferente extends SeleccionAbstracta {
		public SeleccionPorReferente(DataSource dataSource) {
			super(dataSource, SELECCION_POR_REFERENTE_SQL);
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private SeleccionPorReferente seleccionPorReferente;

	/* (non-Javadoc)
	 * @see kalos.dao.SignificadoDAO#getPorReferente(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see kalos.datos.dao.SignificadoDAO#getPorReferente(java.lang.String)
	 */
	@Override
	public List<Significado> getPorReferente(String referenteId) {
		return seleccionPorReferente.execute(new Object[] { referenteId });
	}

	//selección individual
	class SeleccionIndividual extends SeleccionAbstracta {
		public SeleccionIndividual(DataSource dataSource) {
			super(dataSource, SELECCION_INDIVIDUAL_SQL);
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private SeleccionIndividual seleccionIndividual;

	/* (non-Javadoc)
	 * @see kalos.dao.SignificadoDAO#getInidvidual(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see kalos.datos.dao.SignificadoDAO#getInidvidual(java.lang.String)
	 */
	@Override
	public Significado getInidvidual(String significadoId) {
		List<Significado> lstAux = seleccionIndividual.execute(new Object[] { significadoId });
		if (lstAux.size() == 0)
			return null;
		else
			return lstAux.get(0);
	}

	//inserción
	class Insercion extends SqlUpdate {
		public Insercion(DataSource dataSource) {
			super(dataSource, INSERCION_SQL);
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
		}
	}

	private Insercion insercion;

	/* (non-Javadoc)
	 * @see kalos.dao.SignificadoDAO#inserta(kalos.beans.Significado)
	 */
	/* (non-Javadoc)
	 * @see kalos.datos.dao.SignificadoDAO#inserta(kalos.beans.Significado)
	 */
	@Override
	public void inserta(Significado si) {
		String pk = kalos.datos.util.DBUtil.getHashableId();
		insercion.update(new Object[] { pk, si.getIdioma(), si.getReferenteId(), si.getValor() });
		si.setId(pk);
	}

	// modificacion
	class Modificacion extends SqlUpdate {
		public Modificacion(DataSource dataSource) {
			super(dataSource, MODIFICACION_SQL);
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			//where
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private Modificacion modificacion;

	/* (non-Javadoc)
	 * @see kalos.dao.SignificadoDAO#modifica(kalos.beans.Significado)
	 */
	/* (non-Javadoc)
	 * @see kalos.datos.dao.SignificadoDAO#modifica(kalos.beans.Significado)
	 */
	@Override
	public void modifica(Significado si) {
		modificacion.update(new Object[] { si.getIdioma(), si.getReferenteId(), si.getValor(),
		//where
				si.getId() });
	}

	//borrado
	class Borrado extends SqlUpdate {
		public Borrado(DataSource dataSource) {
			super(dataSource, BORRADO_SQL);
			declareParameter(new SqlParameter(Types.CHAR));
		}
	}

	private Borrado borrado;

	/* (non-Javadoc)
	 * @see kalos.dao.SignificadoDAO#borra(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see kalos.datos.dao.SignificadoDAO#borra(java.lang.String)
	 */
	@Override
	public void borra(String significadoId) {
		borrado.update(new Object[] { significadoId });
	}

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionPorReferente = new SeleccionPorReferente(getDataSource());
		seleccionIndividual = new SeleccionIndividual(getDataSource());
		insercion = new Insercion(getDataSource());
		borrado = new Borrado(getDataSource());
		modificacion = new Modificacion(getDataSource());
	}

}
