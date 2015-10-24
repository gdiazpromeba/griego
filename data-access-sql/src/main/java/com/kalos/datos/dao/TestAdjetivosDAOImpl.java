package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.TestAdjetivosBean;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Numero;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

public class TestAdjetivosDAOImpl extends JdbcDaoSupport implements TestAdjetivosDAO {
    private static String SELECT_SQL;
    private static String INSERT_SQL;
    private static String UPDATE_SQL;
    private static String DELETE_SQL;
    private Seleccion seleccion;
    private Insercion insercion;
    private Modificacion modificacion;
    private Borrado borrado;

    private void puebla() {
	StringBuffer localStringBuffer = new StringBuffer(200);
	localStringBuffer.append("SELECT   \n");
	localStringBuffer.append("  TES.TEST_ADJETIVO_ID,   \n");
	localStringBuffer.append("  TES.ADJETIVO_ID,   \n");
	localStringBuffer.append("  TES.FORMA,   \n");
	localStringBuffer.append("  TES.CASO,   \n");
	localStringBuffer.append("  TES.NUMERO,   \n");
	localStringBuffer.append("  TES.GENERO   \n");
	localStringBuffer.append("FROM        \n");
	localStringBuffer.append("  TEST_ADJETIVOS TES                 \n");
	SELECT_SQL = localStringBuffer.toString();
	localStringBuffer = new StringBuffer(200);
	localStringBuffer.append("INSERT INTO TEST_ADJETIVOS(  \n");
	localStringBuffer.append("  TEST_ADJETIVO_ID,   \n");
	localStringBuffer.append("  ADJETIVO_ID,   \n");
	localStringBuffer.append("  FORMA,   \n");
	localStringBuffer.append("  CASO,   \n");
	localStringBuffer.append("  NUMERO,   \n");
	localStringBuffer.append("  GENERO   \n");
	localStringBuffer.append(" ) VALUES (        \n");
	localStringBuffer.append("  ?,?,?,?,?,? )    \n");
	INSERT_SQL = localStringBuffer.toString();
	localStringBuffer = new StringBuffer(200);
	localStringBuffer.append("UPDATE TEST_ADJETIVOS SET  \n");
	localStringBuffer.append("  ADJETIVO_ID=?,   \n");
	localStringBuffer.append("  FORMA=?,   \n");
	localStringBuffer.append("  CASO=?,   \n");
	localStringBuffer.append("  NUMERO=?,   \n");
	localStringBuffer.append("  GENERO=?   \n");
	localStringBuffer.append("WHERE    \n");
	localStringBuffer.append("  TEST_ADJETIVO_ID=?   \n");
	UPDATE_SQL = localStringBuffer.toString();
	localStringBuffer = new StringBuffer(200);
	localStringBuffer.append("DELETE FROM TEST_ADJETIVOS WHERE   \n");
	localStringBuffer.append("  TEST_ADJETIVO_ID=?  \n");
	DELETE_SQL = localStringBuffer.toString();
    }

    @SuppressWarnings("unchecked")
    public List<TestAdjetivosBean> seleccionaTodos() {
	return this.seleccion.execute();
    }

    public void inserta(TestAdjetivosBean bean) {
	String pk = com.kalos.datos.util.DBUtil.getHashableId();
	this.insercion.update(new Object[] { bean.getId(), bean.getIdAdjetivo(), bean.getFormaDeclinada(),
		bean.getCaso().valorEntero(), bean.getNumero().valorEntero(),
		bean.getGenero().valorEntero() });
	bean.setId(pk);
    }

    public void modifica(TestAdjetivosBean bean) {
	this.modificacion.update(new Object[] { bean.getIdAdjetivo(), bean.getFormaDeclinada(),
		bean.getCaso().valorEntero(), bean.getNumero().valorEntero(),
		bean.getGenero().valorEntero(), bean.getId() });
    }

    public void borra(String paramString) {
	this.borrado.update(new Object[] { paramString });
    }

    public void initDao() throws Exception {
	super.initDao();
	puebla();
	this.seleccion = new Seleccion(getDataSource());
	this.insercion = new Insercion(getDataSource());
	this.modificacion = new Modificacion(getDataSource());
	this.borrado = new Borrado(getDataSource());
    }

    class Borrado extends SqlUpdate {
	public Borrado(DataSource paramDataSource) {
	    super(paramDataSource, DELETE_SQL);
	    declareParameter(new SqlParameter(1));
	}
    }

    class Modificacion extends SqlUpdate {
	public Modificacion(DataSource paramDataSource) {
	    super(paramDataSource, UPDATE_SQL);
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(1));
	}
    }

    class Insercion extends SqlUpdate {
	public Insercion(DataSource paramDataSource) {
	    super(paramDataSource, INSERT_SQL);
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(1));
	    declareParameter(new SqlParameter(12));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	    declareParameter(new SqlParameter(4));
	}
    }

    class Seleccion extends SeleccionAbstracta {
	public Seleccion(DataSource paramDataSource) {
	    super(paramDataSource, SELECT_SQL);
	}
    }

    abstract class SeleccionAbstracta extends MappingSqlQuery {
	public SeleccionAbstracta(DataSource paramDataSource, String paramString) {
	    super(paramDataSource, paramString);
	}

	protected Object mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
	    TestAdjetivosBean localM = new TestAdjetivosBean();
	    localM.setId(paramResultSet.getString("TEST_ADJETIVO_ID"));
	    localM.setIdAdjetivo(paramResultSet.getString("ADJETIVO_ID"));
	    localM.setFormaDeclinada(paramResultSet.getString("FORMA"));
	    localM.setCaso(Caso.getEnum(paramResultSet.getInt("CASO")));
	    localM.setNumero(Numero.getEnum(paramResultSet.getInt("NUMERO")));
	    localM.setGenero(Genero.getEnum(paramResultSet.getInt("GENERO")));
	    return localM;
	}
    }
}
