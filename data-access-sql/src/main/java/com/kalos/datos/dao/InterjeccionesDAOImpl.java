
package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.kalos.beans.InterjeccionBean;
import com.kalos.beans.Significado;
import com.kalos.datos.dao.comunes.Borrado;
import com.kalos.datos.dao.comunes.ModificaCodigoIndividual;
import com.kalos.datos.dao.comunes.SeleccionIds;
import com.kalos.datos.util.Listas;
import com.kalos.enumeraciones.Idioma;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.recursos.Configuracion;

@SuppressWarnings("unchecked")
public class InterjeccionesDAOImpl extends JdbcDaoSupport implements InterjeccionesDAO {

    @Override
    public List<InterjeccionBean> seleccionaTodos() {
        return seleccionTodosConSig.execute();
    }

    @Override
    public List<String> seleccionaPorLetra(String letra) {
        return seleccionPorLetra.execute(letra);
    }

    @Override
    public List<String> seleccionaPorCanonica(String canonica) {
        return seleccionIdsPorCanonica.execute(canonica);
    }

    @Override
    public List<InterjeccionBean> seleccionaPorFormaSinSignificado(String forma) {
        return seleccionPorCanonicaConSig.execute(forma);
    }

    @Override
    public InterjeccionBean getInidvidual(String id) {
        List<String> ids = new ArrayList<String>();
        ids.add(id);
        List<InterjeccionBean> beans = getRegistros(ids);
        return beans.get(0);
    }

    @Override
    public void modificaCodigoIndividual(int nuevoCodigo, String id) {
        modificaCodigoIndividual.update(new Object[] { Integer.valueOf(nuevoCodigo), id });
    }

    public void initDao() throws Exception {
        super.initDao();
        puebla();
        borrado = new Borrado(getDataSource(), BORRADO_SQL);
        modificacion = new Modificacion(getDataSource());
        insercion = new Insercion(getDataSource());
        seleccionPorLetra = new SeleccionPorLetra(getDataSource());
        seleccionPorCanonicaConSig = new SeleccionPorCanonicaConSig(getDataSource());
        seleccionIdsPorCanonica = new SeleccionIdsPorCanonica(getDataSource());
        seleccionTodosConSig = new SeleccionTodosConSig(getDataSource());
        modificaCodigoIndividual = new ModificaCodigoIndividual(getDataSource(), MODIFICACION_CODIGO_SQL);
    }

    private void puebla() {
        StringBuffer sql  = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  ITJ.INTERJECCION_ID,   \n");
        sql.append("  ITJ.LETRA,   \n");
        sql.append("  ITJ.CODIGO,   \n");
        sql.append("  ITJ.INTERJECCION,   \n");
        sql.append("  ITJ.PARTIC,   \n");
        sql.append("  SIG.SIGNIFICADO_ID,   \n");
        sql.append("  SIG.VALOR   \n");
        sql.append("FROM        \n");
        sql.append("    INTERJECCIONES ITJ       \n");
        sql.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
        sql.append("      ON ITJ.INTERJECCION_ID=SIG.REFERENTE_ID       \n");
        sql.append("WHERE  \n");
        sql.append("  1=1   \n");
        sql.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='").append(Configuracion.getIdiomaSignificados()).append("')    \n");
        sql.append("ORDER BY \n");
        sql.append(" ITJ.LETRA, \n");
        sql.append(" ITJ.CODIGO \n");
        SELECCION_TODOS_CON_SIGNIFICADO_SQL = sql.toString();



        sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  ITJ.INTERJECCION_ID,   \n");
        sql.append("  ITJ.LETRA,   \n");
        sql.append("  ITJ.CODIGO,   \n");
        sql.append("  ITJ.INTERJECCION,   \n");
        sql.append("  ITJ.PARTIC,   \n");
        sql.append("  SIG.SIGNIFICADO_ID,   \n");
        sql.append("  SIG.VALOR   \n");
        sql.append("FROM        \n");
        sql.append("    INTERJECCIONES ITJ       \n");
        sql.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
        sql.append("      ON ITJ.INTERJECCION_ID=SIG.REFERENTE_ID       \n");
        sql.append("WHERE  \n");
        sql.append("  ITJ.INTERJECCION=?    \n");
        sql.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='").append(Configuracion.getIdiomaSignificados()).append("')    \n");
        sql.append("ORDER BY \n");
        sql.append(" ITJ.LETRA, \n");
        sql.append(" ITJ.CODIGO \n");
        SELECCION_POR_CANONICA_CON_SIGNIFICADO_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  ITJ.INTERJECCION_ID,   \n");
        sql.append("  ITJ.LETRA,   \n");
        sql.append("  ITJ.CODIGO,   \n");
        sql.append("  ITJ.INTERJECCION,   \n");
        sql.append("  ITJ.PARTIC,   \n");
        sql.append("  SIG.SIGNIFICADO_ID,   \n");
        sql.append("  SIG.VALOR   \n");
        sql.append("FROM        \n");
        sql.append("    INTERJECCIONES ITJ       \n");
        sql.append("    LEFT JOIN SIGNIFICADOS SIG                 \n");
        sql.append("      ON ITJ.INTERJECCION_ID=SIG.REFERENTE_ID       \n");
        sql.append("WHERE  \n");
        sql.append("  ITJ.INTERJECCION_ID IN (?)    \n");
        sql.append("  AND (SIG.IDIOMA IS NULL OR SIG.IDIOMA='").append(Configuracion.getIdiomaSignificados()).append("')    \n");
        sql.append("ORDER BY \n");
        sql.append(" ITJ.LETRA, \n");
        sql.append(" ITJ.CODIGO \n");
        SELECCION_POR_IDS_CON_SIGNIFICADO_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  ITJ.INTERJECCION_ID   \n");
        sql.append("FROM        \n");
        sql.append("    INTERJECCIONES ITJ       \n");
        sql.append("WHERE  \n");
        sql.append("  ITJ.LETRA=?    \n");
        sql.append("ORDER BY \n");
        sql.append(" ITJ.LETRA, \n");
        sql.append(" ITJ.CODIGO \n");
        SELECCION_POR_LETRA_SIN_SIGNIFICADO_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  ITJ.INTERJECCION_ID   \n");
        sql.append("FROM        \n");
        sql.append("    INTERJECCIONES ITJ       \n");
        sql.append("WHERE  \n");
        sql.append("  ITJ.INTERJECCION=?    \n");
        sql.append("ORDER BY \n");
        sql.append(" ITJ.LETRA, \n");
        sql.append(" ITJ.CODIGO \n");
        SELECCION_IDS_POR_CANONICA = sql.toString();

        sql = new StringBuffer(200);
        sql.append("INSERT INTO INTERJECCIONES (   \n");
        sql.append("  INTERJECCION_ID,   \n");
        sql.append("  LETRA,   \n");
        sql.append("  CODIGO,   \n");
        sql.append("  INTERJECCION,   \n");
        sql.append("  PARTIC   \n");
        sql.append(" ) VALUES (    \n");
        sql.append("  ?,?,?,?,?  \n");
        sql.append(")     \n");
        INSERCION_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("UPDATE INTERJECCIONES SET   \n");
        sql.append("  LETRA=?,   \n");
        sql.append("  CODIGO=?,   \n");
        sql.append("  INTERJECCION=?,   \n");
        sql.append("  PARTIC=?   \n");
        sql.append("WHERE    \n");
        sql.append("  INTERJECCION_ID=?   \n");
        MODIFICACION_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("DELETE FROM INTERJECCIONES WHERE   \n");
        sql.append("  INTERJECCION_ID=?  \n");
        BORRADO_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("UPDATE INTERJECCIONES SET   \n");
        sql.append("  CODIGO=?   \n");
        sql.append("WHERE    \n");
        sql.append("  INTERJECCION_ID=?   \n");
        MODIFICACION_CODIGO_SQL = sql.toString();
    }

    private static String INSERCION_SQL;
    private static String MODIFICACION_SQL;
    private static String BORRADO_SQL;
    private static String SELECCION_POR_IDS_CON_SIGNIFICADO_SQL;
    private static String SELECCION_TODOS_CON_SIGNIFICADO_SQL;
    private static String SELECCION_POR_LETRA_SIN_SIGNIFICADO_SQL;
    private static String SELECCION_POR_CANONICA_CON_SIGNIFICADO_SQL;
    private static String SELECCION_IDS_POR_CANONICA;
    private static String MODIFICACION_CODIGO_SQL;
    private Insercion insercion;
    private Modificacion modificacion;
    private Borrado borrado;
    private SeleccionPorLetra seleccionPorLetra;
    private SeleccionPorCanonicaConSig seleccionPorCanonicaConSig;
    private SeleccionIdsPorCanonica seleccionIdsPorCanonica;
    private SeleccionTodosConSig seleccionTodosConSig;
    private ModificaCodigoIndividual modificaCodigoIndividual;

    class Modificacion extends SqlUpdate {

        public Modificacion(DataSource datasource) {
            super(datasource, MODIFICACION_SQL);
            declareParameter(new SqlParameter(Types.CHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.CHAR));
            declareParameter(new SqlParameter(Types.CHAR));
        }
    }

    class Insercion extends SqlUpdate {

        public Insercion(DataSource datasource) {
            super(datasource, INSERCION_SQL);
            declareParameter(new SqlParameter(Types.CHAR));
            declareParameter(new SqlParameter(Types.CHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.CHAR));
        }
    }

    public void inserta(InterjeccionBean bean) {
        String id = com.kalos.datos.util.DBUtil.getHashableId();
        insercion.update(new Object[] { id, bean.getLetra(), Integer.valueOf(bean.getCodigo()), bean.getInterjeccion(),
               bean.getPartic().abreviatura()
        });
        bean.setId(id);
    }

    public void modifica(InterjeccionBean bean) {
        modificacion.update(new Object[] { bean.getLetra(), Integer.valueOf(bean.getCodigo()), bean.getInterjeccion(),
                bean.getPartic().abreviatura(), bean.getId()
        });
    }

    public void borra(String s) {
        borrado.update(new Object[] { s });
    }

    abstract class SeleccionAbstracta extends MappingSqlQuery {

        protected Object mapRow(ResultSet resultset, int j) throws SQLException {
            InterjeccionBean m1 = new InterjeccionBean();
            m1.setId(resultset.getString("INTERJECCION_ID"));
            m1.setLetra(resultset.getString("LETRA"));
            m1.setCodigo(resultset.getInt("CODIGO"));
            m1.setInterjeccion(resultset.getString("INTERJECCION"));
            m1.setPartic(Particularidad.getEnum(resultset.getString("PARTIC")));
            Significado q1 = new Significado();
            q1.setIdioma(Configuracion.getIdiomaSignificados());
            q1.setReferenteId(m1.getId());
            q1.setId(resultset.getString("SIGNIFICADO_ID"));
            q1.setValor(resultset.getString("VALOR"));
            HashMap<Idioma, Significado> hashmap = new HashMap<Idioma, Significado>();
            hashmap.put(Idioma.getEnum(Configuracion.getIdiomaSignificados()), q1);
            m1.setSignificados(hashmap);
            return m1;
        }

        public SeleccionAbstracta(DataSource datasource, String s) {
            super(datasource, s);
        }
    }

    abstract class SeleccionAbstractaSinSignificado extends MappingSqlQuery {

        protected Object mapRow(ResultSet resultset, int j) throws SQLException {
            InterjeccionBean m1 = new InterjeccionBean();
            m1.setId(resultset.getString("INTERJECCION_ID"));
            m1.setLetra(resultset.getString("LETRA"));
            m1.setCodigo(resultset.getInt("CODIGO"));
            m1.setInterjeccion(resultset.getString("INTERJECCION"));
            m1.setPartic(Particularidad.getEnum(resultset.getString("PARTIC")));
            return m1;
        }

        public SeleccionAbstractaSinSignificado(DataSource datasource, String s) {
            super(datasource, s);
        }
    }

    class SeleccionPorIds extends SeleccionAbstracta {

        public SeleccionPorIds(DataSource datasource, String sql) {
            super(datasource, sql);
        }
    }

    class SeleccionPorLetra extends SeleccionIds {

        public SeleccionPorLetra(DataSource datasource) {
            super(datasource, SELECCION_POR_LETRA_SIN_SIGNIFICADO_SQL, "INTERJECCION_ID");
            declareParameter(new SqlParameter(Types.CHAR));
        }
    }

    //    class SeleccionPorCanonicaSinSig extends SeleccionAbstractaSinSignificado {
    //	
    //	public SeleccionPorCanonicaSinSig(DataSource datasource) {
    //	    super(datasource, SELECCION_POR_CANONICA_SIN_SIGNIFICADO_SQL);
    //	    declareParameter(new SqlParameter(Types.VARCHAR));
    //	}
    //    }

    class SeleccionPorCanonicaConSig extends SeleccionAbstracta {

        public SeleccionPorCanonicaConSig(DataSource datasource) {
            super(datasource, SELECCION_POR_CANONICA_CON_SIGNIFICADO_SQL);
            declareParameter(new SqlParameter(Types.VARCHAR));
        }
    }

    class SeleccionTodosConSig extends SeleccionAbstracta {

        public SeleccionTodosConSig(DataSource datasource) {
            super(datasource, SELECCION_TODOS_CON_SIGNIFICADO_SQL);
        }
    }

    public List<InterjeccionBean> getRegistros(List<String> ids) {
        List<List<String>> listaDeListas = Listas.segmentos(ids, 500);
        List<InterjeccionBean> beans = new ArrayList<InterjeccionBean>();
        for (List<String> listaDeIds : listaDeListas) {
            StringBuffer sb = new StringBuffer(500);
            for (String id : listaDeIds) {
                sb.append("'").append(id).append("',");
            }
            sb.deleteCharAt(sb.length() - 1);
            String sql = SELECCION_POR_IDS_CON_SIGNIFICADO_SQL.replaceFirst("\\?", sb.toString());
            SeleccionPorIds sel = new SeleccionPorIds(getDataSource(), sql);
            List<InterjeccionBean> segmBeans = sel.execute();
            beans.addAll(segmBeans);
        }
        return beans;
    }

    class SeleccionIdsPorCanonica extends SeleccionIds {

        public SeleccionIdsPorCanonica(DataSource datasource) {
            super(datasource, SELECCION_IDS_POR_CANONICA, "INTERJECCION_ID");
            declareParameter(new SqlParameter(Types.VARCHAR));
        }
    }

}
