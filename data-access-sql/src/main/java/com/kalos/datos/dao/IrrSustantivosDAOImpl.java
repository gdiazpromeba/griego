
package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.kalos.beans.IrrSustantivoBean;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.datos.util.Listas;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.Particularidad;

public class IrrSustantivosDAOImpl extends JdbcDaoSupport implements IrrSustantivosDAO {

    private static String SEL_POR_SUSTANTIVO_SQL;
    private static String SEL_POR_SUSTANTIVOS_SQL;
    private static String SEL_POR_SUST_Y_PARTIC_SQL;
    private static String SEL_POR_FORMA_SQL;
    private static String SEL_TODO_SQL;
    private static String SEL_PARTICS_POR_SUST_SQL;
    private static String INSERT_SQL;
    private static String UPDATE_SQL;
    private static String DELETE_SQL;
    private SeleccionPorId seleccionPorId;
    private SeleccionPorIdYPartic seleccionPorIdYPartic;
    private SeleccionPorForma seleccionPorForma;
    private SeleccionPartics seleccionPartics;
    private SeleccionTodo seleccionTodo;
    private Insercion insercion;
    private Modificacion modificacion;
    private Borrado borrado;

    private void puebla() {
        StringBuffer sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  IRR.IRR_SUSTANTIVO_ID,   \n");
        sql.append("  IRR.SUSTANTIVO_ID,   \n");
        sql.append("  IRR.PARTICULARIDAD,   \n");
        sql.append("  IRR.CASO,   \n");
        sql.append("  IRR.NUMERO,   \n");
        sql.append("  IRR.SUBINDICE,   \n");
        sql.append("  IRR.FORMA   \n");
        sql.append("FROM        \n");
        sql.append("  IRR_SUSTANTIVOS IRR       \n");
        sql.append("WHERE  \n");
        sql.append("  IRR.SUSTANTIVO_ID=?    \n");
        SEL_POR_SUSTANTIVO_SQL = sql.toString();
        sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  IRR.IRR_SUSTANTIVO_ID,   \n");
        sql.append("  IRR.SUSTANTIVO_ID,   \n");
        sql.append("  IRR.PARTICULARIDAD,   \n");
        sql.append("  IRR.CASO,   \n");
        sql.append("  IRR.NUMERO,   \n");
        sql.append("  IRR.SUBINDICE,   \n");
        sql.append("  IRR.FORMA   \n");
        sql.append("FROM        \n");
        sql.append("  IRR_SUSTANTIVOS IRR       \n");
        sql.append("WHERE  \n");
        sql.append("  IRR.IRR_SUSTANTIVO_ID IN (?)    \n");
        SEL_POR_SUSTANTIVOS_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  IRR.IRR_SUSTANTIVO_ID,   \n");
        sql.append("  IRR.SUSTANTIVO_ID,   \n");
        sql.append("  IRR.PARTICULARIDAD,   \n");
        sql.append("  IRR.CASO,   \n");
        sql.append("  IRR.NUMERO,   \n");
        sql.append("  IRR.SUBINDICE,   \n");
        sql.append("  IRR.FORMA   \n");
        sql.append("FROM        \n");
        sql.append("  IRR_SUSTANTIVOS IRR       \n");
        SEL_TODO_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  IRR.IRR_SUSTANTIVO_ID,   \n");
        sql.append("  IRR.SUSTANTIVO_ID,   \n");
        sql.append("  IRR.PARTICULARIDAD,   \n");
        sql.append("  IRR.CASO,   \n");
        sql.append("  IRR.NUMERO,   \n");
        sql.append("  IRR.SUBINDICE,   \n");
        sql.append("  IRR.FORMA   \n");
        sql.append("FROM        \n");
        sql.append("  IRR_SUSTANTIVOS IRR       \n");
        sql.append("WHERE  \n");
        sql.append("  IRR.SUSTANTIVO_ID=?    \n");
        sql.append("  AND IRR.PARTICULARIDAD=?    \n");
        SEL_POR_SUST_Y_PARTIC_SQL = sql.toString();
        sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  IRR.IRR_SUSTANTIVO_ID,   \n");
        sql.append("  IRR.SUSTANTIVO_ID,   \n");
        sql.append("  IRR.PARTICULARIDAD,   \n");
        sql.append("  IRR.CASO,   \n");
        sql.append("  IRR.NUMERO,   \n");
        sql.append("  IRR.SUBINDICE,   \n");
        sql.append("  IRR.FORMA   \n");
        sql.append("FROM        \n");
        sql.append("  IRR_SUSTANTIVOS IRR       \n");
        sql.append("WHERE  \n");
        sql.append("  IRR.FORMA=?    \n");
        SEL_POR_FORMA_SQL = sql.toString();
        sql = new StringBuffer(200);
        sql.append("SELECT DISTINCT  \n");
        sql.append("  IRR.PARTICULARIDAD  \n");
        sql.append("FROM  \n");
        sql.append("  IRR_SUSTANTIVOS IRR  \n");
        sql.append("  INNER JOIN PARTICULARIDADES PAR ON IRR.PARTICULARIDAD=PAR.PARTIC  \n");
        sql.append("WHERE   \n");
        sql.append("  SUSTANTIVO_ID=? \n");
        sql.append("ORDER BY \n");
        sql.append("  PAR.ORDEN \n");
        SEL_PARTICS_POR_SUST_SQL = sql.toString();
        sql = new StringBuffer(200);
        sql.append("INSERT INTO IRR_SUSTANTIVOS (   \n");
        sql.append("  IRR_SUSTANTIVO_ID,   \n");
        sql.append("  SUSTANTIVO_ID,   \n");
        sql.append("  PARTICULARIDAD,   \n");
        sql.append("  CASO,   \n");
        sql.append("  NUMERO,   \n");
        sql.append("  SUBINDICE,   \n");
        sql.append("  FORMA   \n");
        sql.append(") VALUES (        \n");
        sql.append("  ?,?,?,?,?,?,?      \n");
        sql.append(")  \n");
        INSERT_SQL = sql.toString();
        sql = new StringBuffer(200);
        sql.append("UPDATE IRR_SUSTANTIVOS SET   \n");
        sql.append("  SUSTANTIVO_ID=?,   \n");
        sql.append("  PARTICULARIDAD=?,   \n");
        sql.append("  CASO=?,   \n");
        sql.append("  NUMERO=?,   \n");
        sql.append("  SUBINDICE=?,   \n");
        sql.append("  FORMA=?   \n");
        sql.append("WHERE    \n");
        sql.append("  IRR_SUSTANTIVO_ID=?   \n");
        UPDATE_SQL = sql.toString();
        sql = new StringBuffer(200);
        sql.append("DELETE FROM IRR_SUSTANTIVOS \n");
        sql.append("WHERE    \n");
        sql.append("  IRR_SUSTANTIVO_ID=?   \n");
        DELETE_SQL = sql.toString();
    }

    public List<String> seleccionaPartics(String paramString) {
        List localList = this.seleccionPartics.execute(new Object[] { paramString });
        return localList;
    }

    public List<IrrSustantivoBean> seleccionaPorId(String paramString) {
        List localList = this.seleccionPorId.execute(new Object[] { paramString });
        return localList;
    }

    public List<IrrSustantivoBean> seleccionaPorIdyPartic(String paramString, Particularidad paramX) {
        List localList = this.seleccionPorIdYPartic.execute(new Object[] { paramString, paramX.abreviatura() });
        return localList;
    }

    public List<IrrSustantivoBean> getRegistros(List<String> ids) {
        List<List<String>> listaDeListas = Listas.segmentos(ids, 500);
        List<IrrSustantivoBean> beans = new ArrayList<IrrSustantivoBean>();
        for (List<String> listaDeIds : listaDeListas) {
            StringBuffer sb = new StringBuffer(500);
            for (String id : listaDeIds) {
                sb.append("'").append(id).append("',");
            }
            String sql = SEL_POR_SUSTANTIVOS_SQL.replaceFirst("\\?", sb.toString());
            SeleccionPorSustantivos sel = new SeleccionPorSustantivos(getDataSource(), sql);
            List<IrrSustantivoBean> segmBeans = sel.execute();
            beans.addAll(segmBeans);
        }
        return beans;
    }

    public List<IrrSustantivoBean> seleccionaPorForma(String paramString) {
        List localList = this.seleccionPorForma.execute(new Object[] { paramString });
        return localList;
    }

    public List<IrrSustantivoBean> seleccionaTodo() {
        List localList = this.seleccionTodo.execute(new Object[] {  });
        return localList;
    }



    public void inserta(IrrSustantivoBean paramU) {
        String str = com.kalos.datos.util.DBUtil.getHashableId();
        this.insercion.update(new Object[] { str, paramU.getSustantivoId(),
                paramU.getParticularidad().abreviatura(), paramU.getCaso().valorEntero(),
                paramU.getNumero().valorEntero(), Integer.valueOf(paramU.getSubindice()),
                paramU.getForma() });
        paramU.setId(str);
    }

    public void actualiza(IrrSustantivoBean irrSust) {
        this.modificacion.update(new Object[] { irrSust.getSustantivoId(), irrSust.getParticularidad().abreviatura(),
                irrSust.getCaso().valorEntero(), irrSust.getNumero().valorEntero(),
                Integer.valueOf(irrSust.getSubindice()), irrSust.getForma(), irrSust.getId() });
    }

    public void borra(String paramString) {
        this.borrado.update(new Object[] { paramString });
    }

    public void initDao() throws Exception {
        super.initDao();
        puebla();
        this.insercion = new Insercion(getDataSource());
        this.modificacion = new Modificacion(getDataSource());
        this.borrado = new Borrado(getDataSource(), DELETE_SQL);
        this.seleccionPorId = new SeleccionPorId(getDataSource());
        this.seleccionPorForma = new SeleccionPorForma(getDataSource());
        this.seleccionPartics = new SeleccionPartics(getDataSource());
        this.seleccionPorIdYPartic = new SeleccionPorIdYPartic(getDataSource());
        seleccionTodo = new SeleccionTodo(getDataSource());
    }

    private class Modificacion extends SqlUpdate {

        public Modificacion(DataSource paramDataSource) {
            super(paramDataSource, UPDATE_SQL);
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(1));
        }
    }

    private class Insercion extends SqlUpdate {

        public Insercion(DataSource paramDataSource) {
            super(paramDataSource, INSERT_SQL);
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(4));
            declareParameter(new SqlParameter(12));
        }
    }

    private class SeleccionPartics extends MappingSqlQuery {

        public SeleccionPartics(DataSource paramDataSource) {
            super(paramDataSource, SEL_PARTICS_POR_SUST_SQL);
            declareParameter(new SqlParameter(1));
        }

        protected Object mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
            String str = paramResultSet.getString("PARTICULARIDAD");
            return str;
        }
    }

    private class SeleccionPorForma extends SeleccionAbstracta {

        public SeleccionPorForma(DataSource paramDataSource) {
            super(paramDataSource, SEL_POR_FORMA_SQL);
            declareParameter(new SqlParameter(12));
        }
    }

    private class SeleccionTodo extends SeleccionAbstracta {

        public SeleccionTodo(DataSource paramDataSource) {
            super(paramDataSource, SEL_TODO_SQL);
        }
    }

    private class SeleccionPorIdYPartic extends SeleccionAbstracta {

        public SeleccionPorIdYPartic(DataSource paramDataSource) {
            super(paramDataSource, SEL_POR_SUST_Y_PARTIC_SQL);
            declareParameter(new SqlParameter(1));
            declareParameter(new SqlParameter(1));
        }
    }

    private class SeleccionPorSustantivos extends SeleccionAbstracta {

        public SeleccionPorSustantivos(DataSource paramDataSource, String paramString) {
            super(paramDataSource, paramString);
        }
    }

    private class SeleccionPorId extends SeleccionAbstracta {

        public SeleccionPorId(DataSource paramDataSource) {
            super(paramDataSource, SEL_POR_SUSTANTIVO_SQL);
            declareParameter(new SqlParameter(1));
        }
    }

    private abstract class SeleccionAbstracta extends MappingSqlQuery {

        public SeleccionAbstracta(DataSource paramDataSource, String paramString) {
            super(paramDataSource, paramString);
        }

        protected Object mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
            IrrSustantivoBean localU = new IrrSustantivoBean();
            localU.setId(paramResultSet.getString("IRR_SUSTANTIVO_ID"));
            localU.setSustantivoId(paramResultSet.getString("SUSTANTIVO_ID"));
            localU.setParticularidad(Particularidad.getEnum(paramResultSet.getString("PARTICULARIDAD")));
            localU.setCaso(Caso.getEnum(paramResultSet.getInt("CASO")));
            localU.setNumero(Numero.getEnum(paramResultSet.getInt("NUMERO")));
            localU.setSubindice(paramResultSet.getInt("SUBINDICE"));
            localU.setForma(paramResultSet.getString("FORMA"));
            return localU;
        }
    }
}
