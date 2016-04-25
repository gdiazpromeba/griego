package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import com.kalos.beans.TransParticipiosBean;
import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.Voz;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

@SuppressWarnings("unchecked")
public class TransParticipiosDAOImpl extends JdbcDaoSupport implements TransParticipiosDAO {
    class SeleccionPorSubstraer extends SeleccionAbstracta {

        public SeleccionPorSubstraer(DataSource datasource) {
            super(datasource, SEL_POR_SUBSTRAER);
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(12));
            declareParameter(new SqlParameter(12));
        }
    }

    class SeleccionTodos extends SeleccionAbstracta {
        public SeleccionTodos(DataSource datasource) {
            super(datasource, SEL_TODOS_SQL);
        }
    }

    abstract class SeleccionAbstracta extends MappingSqlQuery {

        protected Object mapRow(ResultSet resultset, int i) throws SQLException {
            TransParticipiosBean n1 = new TransParticipiosBean();
            n1.setId(resultset.getString("TRANS_PARTICIPIO_ID"));
            n1.setSubstraer(resultset.getString("SUBSTRAER"));
            n1.setAgregar(resultset.getString("AGREGAR"));
            n1.setAspecto(Aspecto.getEnum(resultset.getInt("ASPECTO")));
            n1.setFuerte(FuerteDebil.getEnum(resultset.getInt("FUERTE")));
            n1.setVoz(Voz.getEnum(resultset.getInt("VOZ")));
            n1.setCaso(Caso.getEnum(resultset.getInt("CASO")));
            n1.setAgregarTema(resultset.getString("AGREGAR_TEMA"));
            n1.setTipoVerbo(resultset.getInt("TIPO_VERBO"));
            n1.setGenero(Genero.getEnum(resultset.getString("GENERO")));
            n1.setAgregarAcento(Acento.getEnum(resultset.getInt("AGREGAR_ACENTO")));
            n1.setAgregarPosicion(resultset.getInt("AGREGAR_POSICION"));
            n1.setSubstractaPosicion(resultset.getInt("SUBSTRACTA_POSICION"));
            n1.setRegexOrig(resultset.getString("REGEX_ORIG"));
            n1.setJuego(resultset.getInt("JUEGO"));
            n1.setContraccionComedora(resultset.getInt("TIENE_CONT_COMEDORA"));
            return n1;
        }


        public SeleccionAbstracta(DataSource datasource, String s) {
            super(datasource, s);
        }
    }

    private void puebla() {
        StringBuffer sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  TRANS_PARTICIPIO_ID,   \n");
        sql.append("  SUBSTRAER,   \n");
        sql.append("  AGREGAR,   \n");
        sql.append("  ASPECTO,   \n");
        sql.append("  FUERTE,   \n");
        sql.append("  VOZ,   \n");
        sql.append("  CASO,   \n");
        sql.append("  AGREGAR_TEMA,   \n");
        sql.append("  TIPO_VERBO,   \n");
        sql.append("  GENERO,   \n");
        sql.append("  AGREGAR_ACENTO,   \n");
        sql.append("  AGREGAR_POSICION,   \n");
        sql.append("  SUBSTRACTA_POSICION,   \n");
        sql.append("  REGEX_ORIG,   \n");
        sql.append("  JUEGO,   \n");
        sql.append("  TIENE_CONT_COMEDORA   \n");
        sql.append("FROM        \n");
        sql.append("  TRANS_PARTICIPIOS       \n");
        SEL_TODOS_SQL = sql.toString();

        sql = new StringBuffer(200);
        sql.append("SELECT   \n");
        sql.append("  TRANS_PARTICIPIO_ID,   \n");
        sql.append("  SUBSTRAER,   \n");
        sql.append("  AGREGAR,   \n");
        sql.append("  ASPECTO,   \n");
        sql.append("  FUERTE,   \n");
        sql.append("  VOZ,   \n");
        sql.append("  CASO,   \n");
        sql.append("  AGREGAR_TEMA,   \n");
        sql.append("  TIPO_VERBO,   \n");
        sql.append("  GENERO,   \n");
        sql.append("  AGREGAR_ACENTO,   \n");
        sql.append("  AGREGAR_POSICION,   \n");
        sql.append("  SUBSTRACTA_POSICION,   \n");
        sql.append("  REGEX_ORIG,   \n");
        sql.append("  JUEGO,   \n");
        sql.append("  TIENE_CONT_COMEDORA   \n");
        sql.append("FROM        \n");
        sql.append("  TRANS_PARTICIPIOS       \n");
        sql.append("WHERE        \n");
        sql.append("   SUBSTRAER=SUBSTRING(?, LENGTH(?)-LENGTH(SUBSTRAER)+1, LENGTH(?))  \n");
        SEL_POR_SUBSTRAER = sql.toString();

        sql = new StringBuffer(200);
        sql.append("INSERT INTO TRANS_PARTICIPIOS (   \n");
        sql.append("  TRANS_PARTICIPIO_ID,   \n");
        sql.append("  SUBSTRAER,   \n");
        sql.append("  AGREGAR,   \n");
        sql.append("  ASPECTO,   \n");
        sql.append("  FUERTE,   \n");
        sql.append("  VOZ,   \n");
        sql.append("  CASO,   \n");
        sql.append("  AGREGAR_TEMA,   \n");
        sql.append("  TIPO_VERBO,   \n");
        sql.append("  GENERO,   \n");
        sql.append("  AGREGAR_ACENTO,   \n");
        sql.append("  AGREGAR_POSICION,   \n");
        sql.append("  SUBSTRACTA_POSICION,   \n");
        sql.append("  REGEX_ORIG,   \n");
        sql.append("  JUEGO,   \n");
        sql.append("  TIENE_CONT_COMEDORA   \n");
        sql.append(") VALUES (        \n");
        sql.append("  ?,?,?,?,?,?,?,?,?,?,       \n");
        sql.append("  ?,?,?,?,?,?      \n");
        sql.append(")                   \n");
        INSERCION_SQL = sql.toString();
    }



    private class Insercion extends SqlUpdate {
        public Insercion(DataSource dataSource){
            super(dataSource, INSERCION_SQL);
            declareParameter(new SqlParameter(Types.CHAR)); //id
            declareParameter(new SqlParameter(Types.VARCHAR)); //substraer
            declareParameter(new SqlParameter(Types.VARCHAR)); //agregar
            declareParameter(new SqlParameter(Types.INTEGER)); //aspecto
            declareParameter(new SqlParameter(Types.INTEGER)); //fuerte
            declareParameter(new SqlParameter(Types.INTEGER)); //voz
            declareParameter(new SqlParameter(Types.INTEGER)); //caso
            declareParameter(new SqlParameter(Types.VARCHAR)); //agregar tema
            declareParameter(new SqlParameter(Types.INTEGER)); //tipo verbo
            declareParameter(new SqlParameter(Types.CHAR)); //género
            declareParameter(new SqlParameter(Types.INTEGER)); //agregar acento
            declareParameter(new SqlParameter(Types.INTEGER)); //agregar posición
            declareParameter(new SqlParameter(Types.INTEGER)); //substracta posición
            declareParameter(new SqlParameter(Types.VARCHAR)); //regex orig
            declareParameter(new SqlParameter(Types.INTEGER)); //juego
            declareParameter(new SqlParameter(Types.INTEGER)); //tiene cont comedora

        }
    }

    private Insercion insercion;

    public void inserta(TransParticipiosBean bean){
        insercion.update(bean.getId(),
                bean.getSubstraer(),
                bean.getAgregar(),
                bean.getAspecto().valorEntero(),
                bean.getFuerte().valorEntero(),
                bean.getVoz().valorEntero(),
                bean.getCaso().valorEntero(),
                bean.getAgregarTema(),
                bean.getTipoVerbo(),
                bean.getGenero().valorLetra(),
                bean.getAgregarAcento()!=null?bean.getAgregarAcento().valorEntero():null,
                bean.getAgregarPosicion(),
                bean.getSubstractaPosicion(),
                bean.getRegexOrig(),
                bean.getJuego(),
                bean.getContraccionComedora());
    }


    public List<TransParticipiosBean> seleccionaTodo() {
        return seleccionTodos.execute();
    }

    public List<TransParticipiosBean> seleccionaPorSubstraer(String s) {
        return seleccionPorSubstraer.execute(new Object[] { s, s, s });
    }

    public void initDao() throws Exception {
        super.initDao();
        puebla();
        seleccionTodos = new SeleccionTodos(getDataSource());
        seleccionPorSubstraer = new SeleccionPorSubstraer(getDataSource());
        insercion = new Insercion(getDataSource());
    }


    private static String SEL_TODOS_SQL;
    private static String SEL_POR_SUBSTRAER;
    private static String INSERCION_SQL;
    private SeleccionTodos seleccionTodos;
    private SeleccionPorSubstraer seleccionPorSubstraer;
}
