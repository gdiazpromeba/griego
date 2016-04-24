// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import com.kalos.beans.PreposicionEnVerbo;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

// Referenced classes of package kalos.E.C:
//            HA

public class PreposicionesEnVerbosDAOImpl extends JdbcDaoSupport implements PreposicionesEnVerbosDAO {
	class Insercion extends SqlUpdate {
		public Insercion(DataSource datasource) {
			super(datasource, INSERT_SQL);
			declareParameter(new SqlParameter(4));
			declareParameter(new SqlParameter(12));
			declareParameter(new SqlParameter(1));
		}
	}

	class Borrado extends SqlUpdate {
		public Borrado(DataSource datasource) {
			super(datasource, DELETE_SQL);
			declareParameter(new SqlParameter(1));
		}
	}

	class Seleccion extends SeleccionAbstracta {
		public Seleccion(DataSource datasource) {
			super(datasource, SELECT_SQL);
			declareParameter(new SqlParameter(1));
		}
	}

    class SeleccionTodo extends SeleccionAbstractaTodo {
        public SeleccionTodo(DataSource datasource) {
            super(datasource, SELECT_TODO_SQL);
        }
    }

	abstract class SeleccionAbstracta extends MappingSqlQuery {

		protected Object mapRow(ResultSet resultset, int i) throws SQLException {
			String s = resultset.getString("PREPOSICION");
			return s;
		}

		public SeleccionAbstracta(DataSource datasource, String s) {
			super(datasource, s);
		}
	}

    abstract class SeleccionAbstractaTodo extends MappingSqlQuery {

        protected PreposicionEnVerbo mapRow(ResultSet resultset, int i) throws SQLException {
            PreposicionEnVerbo bean = new PreposicionEnVerbo();
             bean.setItem(resultset.getInt("ITEM"));
            bean.setPreposicion(resultset.getString("PREPOSICION"));
            bean.setVerboId(resultset.getString("VERBO_ID"));
            return bean;
        }

        public SeleccionAbstractaTodo(DataSource datasource, String sql) {
            super(datasource, sql);
        }
    }



	private void puebla() {
		StringBuffer sql = new StringBuffer(200);
		sql = new StringBuffer(200);
		sql.append("SELECT   \n");
		sql.append("  PREPOSICION   \n");
		sql.append("FROM        \n");
		sql.append("  PREPOSICIONES_EN_VERBOS       \n");
		sql.append("WHERE  \n");
		sql.append("  VERBO_ID=?  \n");
		sql.append("ORDER BY  \n");
		sql.append("  ITEM   \n");
		SELECT_SQL = sql.toString();

		sql = new StringBuffer(200);
		sql = new StringBuffer(200);
		sql.append("SELECT   \n");
        sql.append("  ITEM,   \n");
		sql.append("  PREPOSICION,   \n");
        sql.append("  VERBO_ID   \n");
		sql.append("FROM        \n");
		sql.append("  PREPOSICIONES_EN_VERBOS       \n");
		SELECT_TODO_SQL = sql.toString();

		sql = new StringBuffer(200);
		sql.append("INSERT INTO PREPOSICIONES_EN_VERBOS(   \n");
		sql.append("  ITEM,    \n");
		sql.append("  PREPOSICION,    \n");
		sql.append("  VERBO_ID    \n");
		sql.append(")  VALUES (        \n");
		sql.append("?,?,?  \n");
		sql.append(") \n");
		INSERT_SQL = sql.toString();

		sql = new StringBuffer(200);
		sql = new StringBuffer(200);
		sql.append("DELETE FROM    \n");
		sql.append("  PREPOSICIONES_EN_VERBOS       \n");
		sql.append("WHERE  \n");
		sql.append("  VERBO_ID=?  \n");
		DELETE_SQL = sql.toString();
	}

	public List seleccionaPorVerbo(String s) {
		return seleccion.execute(new Object[] { s });
	}

    public List<PreposicionEnVerbo> seleccionaTodo() {
        return seleccionTodo.execute(new Object[] {  });
    }

	public void borraPorVerbo(String s) {
		borrado.update(new Object[] { s });
	}

	public void inserta(String s, List list) {
		for (int i = 0; i < list.size(); i++)
			insercion.update(new Object[] { Integer.valueOf(i), list.get(i), s });

	}



    public void inserta(PreposicionEnVerbo bean) {

            insercion.update(new Object[] { bean.getItem(), bean.getPreposicion(), bean.getVerboId() });

    }

	public void initDao() throws Exception {
		super.initDao();
		puebla();
		seleccion = new Seleccion(getDataSource());
        seleccionTodo = new SeleccionTodo(getDataSource());
		borrado = new Borrado(getDataSource());
		insercion = new Insercion(getDataSource());
	}


	private static String SELECT_SQL;
	private static String SELECT_TODO_SQL;
	private static String DELETE_SQL;
	private static String INSERT_SQL;
	private Seleccion seleccion;
    private SeleccionTodo seleccionTodo;
	private Borrado borrado;
	private Insercion insercion;
}
