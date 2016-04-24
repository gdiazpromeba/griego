// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.VerbalizadorBean;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

// Referenced classes of package kalos.E.C:
//            x

public class VerbalizadorParticipiosDAOImpl extends JdbcDaoSupport implements VerbalizadorParticipiosDAO {

    class SeleccionPorGen extends SeleccionAbstracta {


        public SeleccionPorGen(DataSource datasource) {
            super(datasource, SELECT_BY_GEN_SQL);
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(12));
        }
    }

    class SeleccionPorNom extends SeleccionAbstracta {
        public SeleccionPorNom(DataSource datasource) {
            super(datasource, SELECT_BY_NOM_SQL);
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(12));
        }
    }

    class SeleccionTodo extends SeleccionAbstracta {
        public SeleccionTodo(DataSource datasource) {
            super(datasource, SELECT_TODO_SQL);
        }
    }

    abstract class SeleccionAbstracta extends MappingSqlQuery {

        protected Object mapRow(ResultSet resultset, int i) throws SQLException {
            VerbalizadorBean bean = new VerbalizadorBean();
            bean.setVoz(Voz.getEnum(resultset.getInt("VOZ")));
            bean.setAspecto(Aspecto.getEnum(resultset.getInt("ASPECTO")));
            bean.setFuerte(FuerteDebil.getEnum(resultset.getInt("FUERTE")));
            bean.setGenero(Genero.getEnum(resultset.getString("GENERO")));
            bean.setTerminacionGenitivo(resultset.getString("TERM_GEN"));
            bean.setTerminacionNominativo(resultset.getString("TERM_NOM"));
            bean.setTipoVerbo(resultset.getInt("TIPO_VERBO"));
            return bean;
        }

        public SeleccionAbstracta(DataSource datasource, String s) {
            super(datasource, s);
        }
    }

    class Insercion extends SqlUpdate {

        public Insercion(DataSource datasource) {
            super(datasource, INSERCION_SQL);
            declareParameter(new SqlParameter(Types.INTEGER)); //voz
            declareParameter(new SqlParameter(Types.INTEGER)); //aspecto
            declareParameter(new SqlParameter(Types.VARCHAR)); //term_nom
            declareParameter(new SqlParameter(Types.INTEGER)); //tipo verbo
            declareParameter(new SqlParameter(Types.INTEGER)); //fuerte
            declareParameter(new SqlParameter(Types.CHAR)); //genero
            declareParameter(new SqlParameter(Types.VARCHAR)); //term gen
        }
    }

    public void inserta(VerbalizadorBean bean) {
        insercion.update(new Object[] {
                bean.getVoz().valorEntero(),
                bean.getAspecto().valorEntero(),
                bean.getTerminacionNominativo(),
                bean.getTipoVerbo(),
                bean.getFuerte().valorEntero(),
                bean.getGenero().valorLetra(),
                bean.getTerminacionGenitivo()
        });
    }




    private void puebla() {
        StringBuffer sql = new StringBuffer(200);
        sql = new StringBuffer(200);
        sql.append("SELECT \n");
        sql.append("   VOZ, \n");
        sql.append("   ASPECTO, \n");
        sql.append("   TIPO_VERBO, \n");
        sql.append("   GENERO, \n");
        sql.append("   TERM_NOM, \n");
        sql.append("   TERM_GEN, \n");
        sql.append("   FUERTE \n");
        sql.append("FROM \n");
        sql.append("   VERBALIZADOR_PARTICIPIOS \n");
        sql.append("WHERE \n");
        sql.append(" SUBSTRING(?, LENGTH(?)-LENGTH(TERM_NOM)+1, LENGTH(?))=TERM_NOM \n");
        sql.append("ORDER BY \n");
        sql.append("   TIPO_VERBO, VOZ, ASPECTO, GENERO \n");
        SELECT_BY_NOM_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("SELECT \n");
        sql.append("   VOZ, \n");
        sql.append("   ASPECTO, \n");
        sql.append("   TIPO_VERBO, \n");
        sql.append("   GENERO, \n");
        sql.append("   TERM_NOM, \n");
        sql.append("   TERM_GEN, \n");
        sql.append("   FUERTE \n");
        sql.append("FROM \n");
        sql.append("   VERBALIZADOR_PARTICIPIOS \n");
        sql.append("WHERE \n");
        sql.append(" SUBSTRING(?, LENGTH(?)-LENGTH(TERM_GEN)+1, LENGTH(?))=TERM_GEN \n");
        sql.append("ORDER BY \n");
        sql.append("   TIPO_VERBO, VOZ, ASPECTO, GENERO \n");
        SELECT_BY_GEN_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("SELECT \n");
        sql.append("   VOZ, \n");
        sql.append("   ASPECTO, \n");
        sql.append("   TERM_NOM, \n");
        sql.append("   TIPO_VERBO, \n");
        sql.append("   FUERTE, \n");
        sql.append("   GENERO, \n");
        sql.append("   TERM_GEN \n");
        sql.append("FROM \n");
        sql.append("   VERBALIZADOR_PARTICIPIOS \n");
        SELECT_TODO_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("INSERT INTO VERBALIZADOR_PARTICIPIOS ( \n");
        sql.append("   VOZ, \n");
        sql.append("   ASPECTO, \n");
        sql.append("   TERM_NOM, \n");
        sql.append("   TIPO_VERBO, \n");
        sql.append("   FUERTE, \n");
        sql.append("   GENERO, \n");
        sql.append("   TERM_GEN \n");
        sql.append(") VALUES ( \n");
        sql.append("   ?,?,?,?,?,?,? \n");
        sql.append(") \n");
        INSERCION_SQL = sql.toString();
    }

    @SuppressWarnings("unchecked")
    public List<VerbalizadorBean> seleccionaPorTerminacionNominativo(String s) {
        List<VerbalizadorBean> list = seleccionPorGen.execute(new Object[]{s, s, s});
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<VerbalizadorBean> seleccionaPorTerminacionGenitivo(String s) {
        List<VerbalizadorBean> list = seleccionPorNom.execute(new Object[]{s, s, s});
        return list;
    }

    public List<VerbalizadorBean> seleccionaTodo() {
        List<VerbalizadorBean> list = seleccionTodo.execute(new Object[]{});
        return list;
    }

    public void initDao() throws Exception {
        super.initDao();
        puebla();
        seleccionPorGen = new SeleccionPorNom(getDataSource());
        seleccionPorNom = new SeleccionPorGen(getDataSource());
        seleccionTodo = new SeleccionTodo(getDataSource());
        insercion = new Insercion(getDataSource());
    }

    private static String SELECT_BY_GEN_SQL;
    private static String SELECT_BY_NOM_SQL;
    private static String SELECT_TODO_SQL;
    private static String INSERCION_SQL;
    private SeleccionPorNom seleccionPorGen;
    private SeleccionPorGen seleccionPorNom;
    private SeleccionTodo seleccionTodo;
    private Insercion insercion;
}