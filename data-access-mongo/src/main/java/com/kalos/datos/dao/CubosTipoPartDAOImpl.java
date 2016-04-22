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

import com.kalos.beans.CubosTipoPartBean;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 * @author <a href="mailto:gonzalo.diaz@turner.com">Gonzalo Diaz</a>
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("unchecked")
public class CubosTipoPartDAOImpl extends JdbcDaoSupport implements CubosTipoPartDAO  {

	private static String SELECCION_TODOS_SQL;
	
	private void puebla() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT   \n");
		sb.append("  NOMINATIVO,   \n");
		sb.append("  GENITIVO,   \n");
		sb.append("  GENERO,   \n");
		sb.append("  ASPECTO,   \n");
		sb.append("  VOZ,   \n");
		sb.append("  FUERTE,   \n");
		sb.append("  TIPO_SUSTANTIVO   \n");
		sb.append("FROM        \n");
		sb.append("  CUBOS_TIPO_PART   \n");

		SELECCION_TODOS_SQL = sb.toString();
	}

	public void setAutocommit(boolean flag) {
		try {
			getDataSource().getConnection().setAutoCommit(flag);
		} catch (Exception exception) {
			throw new RuntimeException("error poblando el valor autocommit");
		}
	}

	public void commit() {
		try {
			getDataSource().getConnection().commit();
		} catch (Exception exception) {
			throw new RuntimeException("error ejecutando commit");
		}
	}

	//general select
	abstract class SeleccionAbstracta extends MappingSqlQuery {
		public SeleccionAbstracta(DataSource dataSource, String sql) {
			super(dataSource, sql);
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			CubosTipoPartBean bean = new CubosTipoPartBean();
			bean.setNominativo(rs.getString("NOMINATIVO"));
			bean.setGenitivo(rs.getString("GENITIVO"));
			bean.setAspecto(Aspecto.getEnum(rs.getInt("ASPECTO")));
			bean.setFuerte(FuerteDebil.getEnum(rs.getInt("FUERTE")));
			bean.setGenero(Genero.getEnum(rs.getString("GENERO")));
			bean.setTipoSustantivo(rs.getInt("TIPO_SUSTANTIVO"));
			bean.setVoz(Voz.getEnum(rs.getInt("VOZ")));
			return bean;
		}
	}

	//selección de todos 
	class SeleccionTodos extends SeleccionAbstracta {
		public SeleccionTodos(DataSource dataSource) {
			super(dataSource, SELECCION_TODOS_SQL);
		}
	}

	private SeleccionTodos seleccionTodos;


	/* (non-Javadoc)
	 * @see kalos.dao.CubosTipoPartDAO#seleccionaTodos()
	 */
	public List<CubosTipoPartBean> seleccionaTodos() {
		return seleccionTodos.execute(new Object[] {});
	}

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccionTodos= new SeleccionTodos(getDataSource());
	}

}
