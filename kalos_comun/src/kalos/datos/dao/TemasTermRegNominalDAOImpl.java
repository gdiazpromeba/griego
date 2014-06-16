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

import kalos.beans.TemaTermRegNominal;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

/**
 * Acceso a la tabla TEMAS_TERM_REG_VERBOS, que representa los temas sencillos ficticios que sirven para 
 * contaer con las desinencias nominales que tienen contracción, y así poder calcular las terminaciones
 * nominales de estas desinencias.
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class TemasTermRegNominalDAOImpl extends JdbcDaoSupport implements TemasTermRegNominalDAO {

	private static String SELECCION_TODOS_SQL;
	private static String INSERCION_SQL;
	private static String BORRADO_TOTAL_SQL;

	private void puebla() {
		StringBuffer sb = new StringBuffer(200);

		sb.append("SELECT \n");
		sb.append("   TIPO_NOMINAL_ID, \n");
		sb.append("   TEMA \n");
		sb.append("FROM \n");
		sb.append("   TEMAS_TERM_REG_NOMINAL \n");
		sb.append("ORDER BY \n");
		sb.append(" TIPO_NOMINAL_ID \n");
		SELECCION_TODOS_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("INSERT INTO  TEMAS_TERM_REG_NOMINAL (  \n");
		sb.append("   TIPO_NOMINAL_ID, \n");
		sb.append("   TEMA \n");
		sb.append(") VALUES (  \n");
		sb.append("   ?, ? \n");
		sb.append(") \n");
		INSERCION_SQL = sb.toString();

		sb = new StringBuffer(200);
		sb.append("DELETE FROM  TEMAS_TERM_REG_NOMINAL  \n");
		BORRADO_TOTAL_SQL = sb.toString();

	}

	class SeleccionTodos extends MappingSqlQuery {
		public SeleccionTodos(DataSource dataSource) {
			super(dataSource, SELECCION_TODOS_SQL);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TemaTermRegNominal bean = new TemaTermRegNominal();
			bean.setIdTipoNominal(rs.getString("TIPO_NOMINAL_ID"));
			bean.setTema(rs.getString("TEMA"));
			return bean;
		}
	}

	private SeleccionTodos seleccionTodos;

	/* (non-Javadoc)
	 * @see kalos.dao.TemasTermRegNominalDAO#seleccionTodos()
	 */
	public List<TemaTermRegNominal> seleccionaTodos() {
		return seleccionTodos.execute();
	}

	class Insercion extends SqlUpdate {
		public Insercion(DataSource dataSource) {
			super(dataSource, INSERCION_SQL);
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
		}
	}

	private Insercion insercion;

	/* (non-Javadoc)
	 * @see kalos.dao.TemasTermRegNominalDAO#insercion(kalos.beans.TemaTermRegNominal)
	 */
	public void inserta(TemaTermRegNominal ttrn) {
		insercion.update(new Object[] { ttrn.getIdTipoNominal(), ttrn.getTema() });
	}

	class Borrado extends SqlUpdate {
		public Borrado(DataSource dataSource) {
			super(dataSource, BORRADO_TOTAL_SQL);
		}
	}

	private Borrado borrado;

	/* (non-Javadoc)
	 * @see kalos.dao.TemasTermRegNominalDAO#borrado()
	 */
	public void borra() {
		borrado.update();
	}

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionTodos = new SeleccionTodos(getDataSource());
		insercion = new Insercion(getDataSource());
		borrado = new Borrado(getDataSource());
	}

}
