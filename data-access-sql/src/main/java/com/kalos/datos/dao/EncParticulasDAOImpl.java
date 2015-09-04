
package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.EncParticulaBean;
import com.kalos.beans.ParticulaBean;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.enumeraciones.TipoPalabra;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

public class EncParticulasDAOImpl extends JdbcDaoSupport implements EncParticulasDAO {

    private static String SELECT_SQL;
    private static String INSERT_SQL;
    private static String UPDATE_SQL;
    private static String DELETE_SQL;

    private Seleccion seleccion;
    private Modificacion modificacion;
    private Insercion insercion;
    private Borrado borrado;

    private void puebla() {
        StringBuffer sql = new StringBuffer(200);
        sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  PARTICULA_ENCABEZADO_ID,   \n");
        sql.append("  TIPO_PALABRA,   \n");
        sql.append("  FORMA   \n");
        sql.append("FROM   \n");
        sql.append("  PARTICULA_ENCABEZADOS   \n");
        SELECT_SQL = sql.toString();


        sql = new StringBuffer(200);
        sql.append("INSERT INTO PARTICULA_ENCABEZADOS(  \n");
        sql.append("  PARTICULA_ENCABEZADO_ID,   \n");
        sql.append("  TIPO_PALABRA,   \n");
        sql.append("  FORMA   \n");
        sql.append("  ?,?,?   \n");
        sql.append(" )   \n");
        INSERT_SQL = sql.toString();
        sql = new StringBuffer(200);
        sql.append("UPDATE PARTICULA_ENCABEZADOS SET  \n");
        sql.append("  PARTICULA_ENCABEZADO_ID=?,   \n");
        sql.append("  TIPO_PALABRA=?,   \n");
        sql.append("  FORMA=?   \n");
        sql.append("WHERE     \n");
        sql.append("  PARTICULA_ENCABEZADO_ID=?   \n");
        UPDATE_SQL = sql.toString();
        sql = new StringBuffer(200);
        sql.append("DELETE FROM PARTICULA_ENCABEZADOS  \n");
        sql.append("WHERE     \n");
        sql.append("  PARTICULA_ENCABEZADO_ID=?   \n");
        DELETE_SQL = sql.toString();
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.dao.EncParticulasDAO#seleccionaEncParticulasTodos()
     */
    @Override
    public List<EncParticulaBean> seleccionaEncParticulasTodos() {
        return seleccion.execute();
    }

    public List<EncParticulaBean> seleccionaEncabezadoPorFormaParticula(String formaParticula) {
        return seleccion.execute(formaParticula);
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.dao.EncParticulasDAO#inserta(kalos.beans.EncParticulaBean)
     */
    @Override
    public void inserta(EncParticulaBean paramf) {
        String str = com.kalos.datos.util.DBUtil.getHashableId();
        insercion.update(new Object[] { str, paramf.getTipoPalabra().getAbreviatura(), paramf.getForma() });
        paramf.setId(str);
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.dao.EncParticulasDAO#modifica(kalos.beans.ParticulaBean)
     */
    @Override
    public void modifica(ParticulaBean paraml) {
        modificacion.update(new Object[] { paraml.getParticulaTipo().getAbreviatura(), paraml.getForma(), paraml.getId() });
    }

    /* (non-Javadoc)
     * @see com.kalos.datos.dao.EncParticulasDAO#borra(java.lang.String)
     */
    @Override
    public void borra(String paramString) {
        this.borrado.update(new Object[] { paramString });
    }

    public void initDao() throws Exception {
        super.initDao();
        puebla();
        seleccion = new Seleccion(getDataSource());
        modificacion = new Modificacion(getDataSource());
        insercion = new Insercion(getDataSource());
        borrado = new Borrado(getDataSource(), DELETE_SQL);
    }

    class Insercion extends SqlUpdate {

        public Insercion(DataSource paramDataSource) {
            super(paramDataSource, INSERT_SQL);
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(1));
        }
    }

    class Modificacion extends SqlUpdate {

        public Modificacion(DataSource paramDataSource) {
            super(paramDataSource, UPDATE_SQL);
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(12));
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
            EncParticulaBean localf = new EncParticulaBean();
            localf.setId(paramResultSet.getString("PARTICULA_ENCABEZADO_ID"));
            localf.setTipoPalabra(TipoPalabra.getEnum(paramResultSet.getString("TIPO_PALABRA")));
            localf.setForma(paramResultSet.getString("FORMA"));
            return localf;
        }
    }
}