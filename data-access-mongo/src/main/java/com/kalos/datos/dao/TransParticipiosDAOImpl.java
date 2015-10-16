package com.kalos.datos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	StringBuffer stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  TRANS_PARTICIPIO_ID,   \n");
	stringbuffer.append("  SUBSTRAER,   \n");
	stringbuffer.append("  AGREGAR,   \n");
	stringbuffer.append("  ASPECTO,   \n");
	stringbuffer.append("  FUERTE,   \n");
	stringbuffer.append("  VOZ,   \n");
	stringbuffer.append("  CASO,   \n");
	stringbuffer.append("  AGREGAR_TEMA,   \n");
	stringbuffer.append("  TIPO_VERBO,   \n");
	stringbuffer.append("  GENERO,   \n");
	stringbuffer.append("  AGREGAR_ACENTO,   \n");
	stringbuffer.append("  AGREGAR_POSICION,   \n");
	stringbuffer.append("  SUBSTRACTA_POSICION,   \n");
	stringbuffer.append("  REGEX_ORIG,   \n");
	stringbuffer.append("  JUEGO,   \n");
	stringbuffer.append("  TIENE_CONT_COMEDORA   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  TRANS_PARTICIPIOS       \n");
	SEL_TODOS_SQL = stringbuffer.toString();
	stringbuffer = new StringBuffer(200);
	stringbuffer.append("SELECT   \n");
	stringbuffer.append("  TRANS_PARTICIPIO_ID,   \n");
	stringbuffer.append("  SUBSTRAER,   \n");
	stringbuffer.append("  AGREGAR,   \n");
	stringbuffer.append("  ASPECTO,   \n");
	stringbuffer.append("  FUERTE,   \n");
	stringbuffer.append("  VOZ,   \n");
	stringbuffer.append("  CASO,   \n");
	stringbuffer.append("  AGREGAR_TEMA,   \n");
	stringbuffer.append("  TIPO_VERBO,   \n");
	stringbuffer.append("  GENERO,   \n");
	stringbuffer.append("  AGREGAR_ACENTO,   \n");
	stringbuffer.append("  AGREGAR_POSICION,   \n");
	stringbuffer.append("  SUBSTRACTA_POSICION,   \n");
	stringbuffer.append("  REGEX_ORIG,   \n");
	stringbuffer.append("  JUEGO,   \n");
	stringbuffer.append("  TIENE_CONT_COMEDORA   \n");
	stringbuffer.append("FROM        \n");
	stringbuffer.append("  TRANS_PARTICIPIOS       \n");
	stringbuffer.append("WHERE        \n");
	stringbuffer.append("   SUBSTRAER=SUBSTRING(?, LENGTH(?)-LENGTH(SUBSTRAER)+1, LENGTH(?))  \n");
	SEL_POR_SUBSTRAER = stringbuffer.toString();
    }

    
    public List<TransParticipiosBean> getTodos() {
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
    }



    static String A() {
	return SEL_POR_SUBSTRAER;
    }

    private static String SEL_TODOS_SQL;
    private static String SEL_POR_SUBSTRAER;
    private SeleccionTodos seleccionTodos;
    private SeleccionPorSubstraer seleccionPorSubstraer;
}
