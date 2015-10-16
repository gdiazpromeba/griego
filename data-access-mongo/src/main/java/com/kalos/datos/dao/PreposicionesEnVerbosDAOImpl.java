// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
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

    abstract class SeleccionAbstracta extends MappingSqlQuery {

	protected Object mapRow(ResultSet resultset, int i) throws SQLException {
	    String s = resultset.getString("PREPOSICION");
	    return s;
	}

	public SeleccionAbstracta(DataSource datasource, String s) {
	    super(datasource, s);
	}
    }

    public PreposicionesEnVerbosDAOImpl() {
    }

    private void C() {
	StringBuffer stringbuffer = new StringBuffer(200);
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  PREPOSICION   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  PREPOSICIONES_EN_VERBOS       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  VERBO_ID=?  \n");
	stringbuffer.append("ORDER BY  \n");
	stringbuffer.append("  ITEM   \n");
	SELECT_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("INSERT INTO PREPOSICIONES_EN_VERBOS(   \n");
	stringbuffer.append("  ITEM,    \n");
	stringbuffer.append("  PREPOSICION,    \n");
	stringbuffer.append("  VERBO_ID    \n");
	stringbuffer.append(")  VALUES (        \n");
	stringbuffer.append("?,?,?  \n");
	stringbuffer.append(") \n");
	INSERT_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("DELETE FROM    \n");
	stringbuffer.append("  PREPOSICIONES_EN_VERBOS       \n");
	stringbuffer.append("WHERE  \n");
	stringbuffer.append("  VERBO_ID=?  \n");
	DELETE_SQL = stringbuffer.toString();
    }

    public List seleccionaPorVerbo(String s) {
	return seleccion.execute(new Object[] { s });
    }

    public void borraPorVerbo(String s) {
	borrado.update(new Object[] { s });
    }

    public void inserta(String s, List list) {
	for (int i = 0; i < list.size(); i++)
	    insercion.update(new Object[] { Integer.valueOf(i), list.get(i), s });

    }

    public void initDao() throws Exception {
	super.initDao();
	C();
	seleccion = new Seleccion(getDataSource());
	borrado = new Borrado(getDataSource());
	insercion = new Insercion(getDataSource());
    }


    private static String SELECT_SQL;
    private static String DELETE_SQL;
    private static String INSERT_SQL;
    private Seleccion seleccion;
    private Borrado borrado;
    private Insercion insercion;
}
