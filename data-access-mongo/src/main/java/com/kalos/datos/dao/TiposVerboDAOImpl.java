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
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.TipoJerarquico;
import com.kalos.beans.TipoSustantivo;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class TiposVerboDAOImpl extends JdbcDaoSupport implements TiposVerboDAO {

	private static String SELECCION_TODOS_SQL;
	
	
	private void puebla() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  TIV.TIPO_VERBO_ID,   \n");
		sb.append("  TIV.TIPO_VERBO_COD,   \n");
		sb.append("  TIV.PADRE_ID,           \n");
		sb.append("  TIV.PADRE,           \n");
		sb.append("  TIV.TIPO_VERBO_DES_CLAVE    \n");
		sb.append("FROM        \n");
		sb.append("  TIPOS_VERBO TIV       \n");
		SELECCION_TODOS_SQL = sb.toString();
		

	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TipoSustantivo taj= new TipoSustantivo();
			taj.setId(rs.getString("TIPO_VERBO_ID"));
			taj.setPadreId(rs.getString("PADRE_ID"));
			taj.setValorEntero(rs.getInt("TIPO_VERBO_COD"));
			taj.setDesClave(rs.getString("TIPO_VERBO_DES_CLAVE"));
			return taj;
		}
	}

	//selección de todos
	class SeleccionTodos extends SeleccionAbstracta {
		public SeleccionTodos(DataSource dataSource) {
			super(dataSource, SELECCION_TODOS_SQL);
		}
	}

	//select todos
	private SeleccionTodos selectTodos;


	/* (non-Javadoc)
	 * @see kalos.dao.TiposVerboDAO#getTodos()
	 */
	public List<TipoJerarquico> getTodos() {
		return selectTodos.execute(new Object[] {});
	}

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		selectTodos = new SeleccionTodos(getDataSource());
	}

}
