package com.kalos.recursos;

import com.kalos.enumeraciones.Acento;
import com.kalos.enumeraciones.AcentoConcuerda;
import com.kalos.enumeraciones.Aspecto;
import com.kalos.enumeraciones.AspectosSegundos;
import com.kalos.enumeraciones.Aumento;
import com.kalos.enumeraciones.Caso;
import com.kalos.enumeraciones.Contraccion;
import com.kalos.enumeraciones.FuerteDebil;
import com.kalos.enumeraciones.Genero;
import com.kalos.enumeraciones.GradoComparacion;
import com.kalos.enumeraciones.Ignorancia;
import com.kalos.enumeraciones.LugarSubcadena;
import com.kalos.enumeraciones.Modo;
import com.kalos.enumeraciones.Numero;
import com.kalos.enumeraciones.OrigenTema;
import com.kalos.enumeraciones.Particularidad;
import com.kalos.enumeraciones.Persona;
import com.kalos.enumeraciones.PosicionConcuerda;
import com.kalos.enumeraciones.Propagacion;
import com.kalos.enumeraciones.Reportes;
import com.kalos.enumeraciones.Silaba;
import com.kalos.enumeraciones.SubtipoConjuncion;
import com.kalos.enumeraciones.Tiempo;
import com.kalos.enumeraciones.TiempoSegundos;
import com.kalos.enumeraciones.TipoAdverbio;
import com.kalos.enumeraciones.TipoConjuncion;
import com.kalos.enumeraciones.TipoPalabra;
import com.kalos.enumeraciones.Voz;

/**
 * Dedicada a obtener los nombres String de las enumeraciones
 * 
 * @author Gonzalo
 * 
 */
public class CadenasEnum {

    @SuppressWarnings("unchecked")
    public static String getCadena(Enum enumeracion) {
	if (enumeracion instanceof Genero) {
	    Genero genero = (Genero) enumeracion;
	    switch (genero) {
	    case Masculino:
		return Recursos.getCadena("masculino");
	    case Femenino:
		return Recursos.getCadena("femenino");
	    case Neutro:
		return Recursos.getCadena("neutro");
	    case MasculinoOFemenino:
		return Recursos.getCadena("masculino_o_femenino");
	    }
	    throw new RuntimeException("género no encontrado");
	} else if (enumeracion instanceof Numero) {
	    Numero numero = (Numero) enumeracion;
	    switch (numero) {
	    case Singular:
		return Recursos.getCadena("singular");
	    case Plural:
		return Recursos.getCadena("plural");
	    case Dual:
		return Recursos.getCadena("dual");
	    }
	    throw new RuntimeException("número no encontrado");
	} else if (enumeracion instanceof Silaba) {
	    Silaba silaba = (Silaba) enumeracion;
	    switch (silaba) {
	    case ninguna:
		return "0";
	    case penultima:
		return "-2";
	    case antepenultima:
		return "-3";
	    case ultima:
		return "-1";
	    case primera:
		return "1";
	    case segunda:
		return "2";
	    }
	    throw new RuntimeException("sílaba no encontrado");
	} else if (enumeracion instanceof Caso) {
	    Caso caso = (Caso) enumeracion;
	    switch (caso) {
	    case Nominativo:
		return Recursos.getCadena("nominativo");
	    case Vocativo:
		return Recursos.getCadena("vocativo");
	    case Acusativo:
		return Recursos.getCadena("acusativo");
	    case Genitivo:
		return Recursos.getCadena("genitivo");
	    case Dativo:
		return Recursos.getCadena("dativo");
	    }
	    throw new RuntimeException("caso no encontrado");
	} else if (enumeracion instanceof Propagacion) {
	    Propagacion propagacion = (Propagacion) enumeracion;
	    switch (propagacion) {
	    case Ninguna:
		return Recursos.getCadena("propagacion.ninguna");
	    case haciaElModo:
		return Recursos.getCadena("propagacion.hacia_el_modo");
	    case haciaModoYVoz:
		return Recursos.getCadena("propagacion.hacia_modo_y_voz");
	    case haciaLaVoz:
		return Recursos.getCadena("propagacion.hacia_la_voz");
	    }
	    throw new RuntimeException("propagación no encontrada");
	} else if (enumeracion instanceof Voz) {
	    Voz voz = (Voz) enumeracion;
	    switch (voz) {
	    case Activa:
		return Recursos.getCadena("voz_activa");
	    case Media:
		return Recursos.getCadena("voz_media");
	    case Pasiva:
		return Recursos.getCadena("voz_pasiva");
	    }
	    throw new RuntimeException("voz no encontrado");

	} else if (enumeracion instanceof Modo) {
	    Modo modo = (Modo) enumeracion;
	    switch (modo) {
	    case Indicativo:
		return Recursos.getCadena("indicativo");
	    case Subjuntivo:
		return Recursos.getCadena("subjuntivo");
	    case Optativo:
		return Recursos.getCadena("optativo");
	    case Imperativo:
		return Recursos.getCadena("imperativo");
	    }
	    throw new RuntimeException("modo no encontrado");
	} else if (enumeracion instanceof TipoPalabra) {
	    TipoPalabra tipp = (TipoPalabra) enumeracion;
	    switch (tipp) {
	    case Adjetivo:
		return Recursos.getCadena("adjetivo");
	    case Adverbio:
		return Recursos.getCadena("adverbio");
	    case Sustantivo:
		return Recursos.getCadena("sustantivo");
	    case Verbo:
		return Recursos.getCadena("verbo");
	    case Infinitivo:
		return Recursos.getCadena("infinitivo");
	    case Participio:
		return Recursos.getCadena("participio");
	    case Articulo:
		return Recursos.getCadena("articulo");
	    case PronombrePersonal:
		return Recursos.getCadena("pronombre_personal");
	    case PronombreRelativo:
		return Recursos.getCadena("pronombre_relativo");
	    case Conjuncion:
		return Recursos.getCadena("conjuncion");
	    case Preposicion:
		return Recursos.getCadena("preposicion");
	    case PronombreReflexivo:
		return Recursos.getCadena("pronombre_reflexivo");
	    case PronombreInterrogativo:
		return Recursos.getCadena("pronombre_interrogativo");
	    case Interjeccion:
		return Recursos.getCadena("interjeccion");
	    case PronombreIndefinido:
		return Recursos.getCadena("pronombre_indefinido");
	    }
	    throw new RuntimeException("tipo de palabra no encontrado");
	} else if (enumeracion instanceof Tiempo) {
	    Tiempo tipp = (Tiempo) enumeracion;
	    switch (tipp) {
	    case Presente:
		return Recursos.getCadena("presente");
	    case Imperfecto:
		return Recursos.getCadena("imperfecto");
	    case Futuro:
		return Recursos.getCadena("futuro");
	    case Aoristo:
		return Recursos.getCadena("aoristo");
	    case Perfecto:
		return Recursos.getCadena("perfecto");
	    case Pluscuamperfecto:
		return Recursos.getCadena("pluscuamperfecto");
	    }
	    throw new RuntimeException("tiempo no encontrado");
	} else if (enumeracion instanceof TiempoSegundos) {
	    TiempoSegundos tis = (TiempoSegundos) enumeracion;
	    switch (tis) {
	    case Presente:
		return Recursos.getCadena("presente");
	    case Imperfecto:
		return Recursos.getCadena("imperfecto");
	    case Futuro:
		return Recursos.getCadena("futuro");
	    case FuturoSegundo:
		return Recursos.getCadena("futuro_segundo");
	    case Aoristo:
		return Recursos.getCadena("aoristo");
	    case AoristoSegundo:
		return Recursos.getCadena("aoristo_segundo");
	    case Perfecto:
		return Recursos.getCadena("perfecto");
	    case PerfectoSegundo:
		return Recursos.getCadena("perfecto_segundo");
	    case Pluscuamperfecto:
		return Recursos.getCadena("pluscuamperfecto");
	    case PluscuamperfectoSegundo:
		return Recursos.getCadena("pluscuamperfecto_segundo");
	    }
	    throw new RuntimeException("tiempo no encontrado");
	} else if (enumeracion instanceof AspectosSegundos) {
	    AspectosSegundos tis = (AspectosSegundos) enumeracion;
	    switch (tis) {
	    case Infectivo:
		return Recursos.getCadena("infectivo");
	    case Futuro:
		return Recursos.getCadena("futuro");
	    case FuturoSegundo:
		return Recursos.getCadena("futuro_segundo");
	    case Confectivo:
		return Recursos.getCadena("confectivo");
	    case ConfectivoSegundo:
		return Recursos.getCadena("confectivo_segundo");
	    case Perfectivo:
		return Recursos.getCadena("perfectivo");
	    case PerfectivoSegundo:
		return Recursos.getCadena("perfectivo_segundo");
	    }
	    throw new RuntimeException("tiempo no encontrado");
	} else if (enumeracion instanceof Persona) {
	    Persona tipp = (Persona) enumeracion;
	    switch (tipp) {
	    case _1ps:
		return Recursos.getCadena("1ps");
	    case _2ps:
		return Recursos.getCadena("2ps");
	    case _3ps:
		return Recursos.getCadena("3ps");
	    case _1pp:
		return Recursos.getCadena("1pp");
	    case _2pp:
		return Recursos.getCadena("2pp");
	    case _3pp:
		return Recursos.getCadena("3pp");
	    case _2pd:
		return Recursos.getCadena("2pd");
	    case _3pd:
		return Recursos.getCadena("3pd");
	    }
	    throw new RuntimeException("persona no encontrada");
	} else if (enumeracion instanceof Contraccion) {
	    Contraccion contr = (Contraccion) enumeracion;
	    switch (contr) {
	    case comePrimera:
		return "Come primera";
	    case consonantica:
		return "Consonántica";
	    case jonica:
		return "Júnica";
	    case monosilabosEw:
		return "Monisílabos en éw";
	    case suma:
		return "Suma";
	    case sumaAcentuada:
		return "Suma acentuada";
	    case vocalica:
		return "Vocálica";
	    }
	    throw new RuntimeException("contracción no encontrada");
	} else if (enumeracion instanceof GradoComparacion) {
	    GradoComparacion grado = (GradoComparacion) enumeracion;
	    switch (grado) {
	    case Positivo:
		return Recursos.getCadena("positivo");
	    case Comparativo:
		return Recursos.getCadena("comparativo");
	    case Superlativo:
		return Recursos.getCadena("superlativo");
	    }
	    throw new RuntimeException("grado de comparacúon no encontrado");
	} else if (enumeracion instanceof LugarSubcadena) {
	    LugarSubcadena lugar = (LugarSubcadena) enumeracion;
	    switch (lugar) {
	    case Principio:
		return Recursos.getCadena("lugar.principio");
	    case Medio:
		return Recursos.getCadena("lugar.mitad");
	    case Fin:
		return Recursos.getCadena("lugar.final");
	    case Exacto:
		return Recursos.getCadena("lugar.exacto");
	    }
	    throw new RuntimeException("lugar de la subcadena no encontrado");
	} else if (enumeracion instanceof Aumento) {
	    Aumento aumento = (Aumento) enumeracion;
	    switch (aumento) {
	    case Ninguno:
		return Recursos.getCadena("ninguno");
	    case Normal:
		return Recursos.getCadena("normal");
	    case enEi:
		return Recursos.getCadena("en_ei");
	    }
	    throw new RuntimeException("aumento no encontrado");
	} else if (enumeracion instanceof FuerteDebil) {
	    FuerteDebil fuerte = (FuerteDebil) enumeracion;
	    switch (fuerte) {
	    case Fuerte:
		return Recursos.getCadena("fuerte");
	    case Debil:
		return Recursos.getCadena("debil");
	    }
	    throw new RuntimeException("carácter fuerte no encontrado");
	} else if (enumeracion instanceof Ignorancia) {
	    Ignorancia ignorancia = (Ignorancia) enumeracion;
	    switch (ignorancia) {
	    case Nada:
		return Recursos.getCadena("ignorar.nada");
	    case SignosLargaCorta:
		return Recursos.getCadena("ignorar.signos_larga_corta");
	    case TodosLosDiacriticos:
		return Recursos.getCadena("ignorar.todos_diacriticos");
	    }
	    throw new RuntimeException("lugar de la subcadena no encontrado");
	} else if (enumeracion instanceof Aspecto) {
	    Aspecto aspecto = (Aspecto) enumeracion;
	    switch (aspecto) {
	    case Infectivo:
		return Recursos.getCadena("infectivo");
	    case Futuro:
		return Recursos.getCadena("futuro");
	    case Confectivo:
		return Recursos.getCadena("confectivo");
	    case Perfectivo:
		return Recursos.getCadena("perfectivo");
	    }
	    throw new RuntimeException("aspecto no encontrado");
	} else if (enumeracion instanceof Acento) {
	    Acento acento = (Acento) enumeracion;
	    switch (acento) {
	    case Ninguno:
		return Recursos.getCadena("ninguno");
	    case Agudo:
		return Recursos.getCadena("agudo");
	    case Grave:
		return Recursos.getCadena("grave");
	    case Circunflejo:
		return Recursos.getCadena("circunflejo");
	    }
	    throw new RuntimeException("acento no encontrado");
	} else if (enumeracion instanceof Particularidad) {
	    Particularidad partic = (Particularidad) enumeracion;
	    switch (partic) {
	    case Regular:
		return Recursos.getCadena("partic.regular");
	    case Anterior:
		return Recursos.getCadena("partic.anterior");
	    case Atico:
		return Recursos.getCadena("partic.atico");
	    case Compuestos:
		return Recursos.getCadena("partic.compuestos");

	    case Contracto:
		return Recursos.getCadena("partic.contracto");
	    case Dorico:
		return Recursos.getCadena("partic.dorico");
	    case Eolico:
		return Recursos.getCadena("partic.eolico");
	    case Epico:
		return Recursos.getCadena("partic.epico");
	    case HebreoNT:
		return Recursos.getCadena("partic.hebreont");
	    case Homerico:
		return Recursos.getCadena("partic.homerico");
	    case Jonico:
		return Recursos.getCadena("partic.jonico");
	    case NoAtico:
		return Recursos.getCadena("partic.noatico");
	    case Poetico:
		return Recursos.getCadena("partic.poetico");
	    case Posterior:
		return Recursos.getCadena("partic.posterior");
	    case Raro:
		return Recursos.getCadena("partic.raro");
	    case Reciente:
		return Recursos.getCadena("partic.reciente");
	    case Sincopado:
		return Recursos.getCadena("partic.sincopado");
	    }
	    throw new RuntimeException("particularidad " + partic + " no encontrada");
	} else if (enumeracion instanceof TipoConjuncion) {
	    TipoConjuncion tipoConj = (TipoConjuncion) enumeracion;
	    switch (tipoConj) {
	    case Coordinante:
		return Recursos.getCadena("conjuncion.coordinante");
	    case Subordinante:
		return Recursos.getCadena("conjuncion.subordinante");
	    }
	    throw new RuntimeException("tipo de conjunción " + tipoConj + " no encontrado");
	} else if (enumeracion instanceof SubtipoConjuncion) {
	    SubtipoConjuncion subtipoConj = (SubtipoConjuncion) enumeracion;
	    switch (subtipoConj) {
	    case Copulativa:
		return Recursos.getCadena("conjuncion.copulativa");
	    case Adversativa:
		return Recursos.getCadena("conjuncion.adversativa");
	    case Disyuntiva:
		return Recursos.getCadena("conjuncion.disyuntiva");
	    case Comparativa:
		return Recursos.getCadena("conjuncion.comparativa");
	    case Declarativa:
		return Recursos.getCadena("conjuncion.declarativa");
	    case Causal:
		return Recursos.getCadena("conjuncion.causal");
	    case Temporal:
		return Recursos.getCadena("conjuncion.temporal");
	    case Conclusiva:
		return Recursos.getCadena("conjuncion.conclusiva");
	    case Final:
		return Recursos.getCadena("conjuncion.final");
	    case Condicional:
		return Recursos.getCadena("conjuncion.supositiva");
	    case Concesiva:
		return Recursos.getCadena("conjuncion.concesiva");
	    }
	    throw new RuntimeException("subtipo de conjunción " + subtipoConj + " no encontrado");

	} else if (enumeracion instanceof OrigenTema) {
	    OrigenTema ot = (OrigenTema) enumeracion;
	    switch (ot) {
	    case NominativoSingular:
		return Recursos.getCadena("origen_tema.nominativo_singular");
	    case NominativoPlural:
		return Recursos.getCadena("origen_tema.nominativo_plural");
	    case VocativoSingular:
		return Recursos.getCadena("origen_tema.vocativo_singular");
	    case GenitivoSingular:
		return Recursos.getCadena("origen_tema.genitivo_singular");
	    case DativoPlural:
		return Recursos.getCadena("origen_tema.dativo_plural");
	    }
	    throw new RuntimeException("origen de tema  " + ot + " no encontrado");
	} else if (enumeracion instanceof AcentoConcuerda) {
	    AcentoConcuerda ac = (AcentoConcuerda) enumeracion;
	    switch (ac) {
	    case SinForzado:
		return Recursos.getCadena("acento_concuerda.sin_forzar");
	    case ElMismo:
		return Recursos.getCadena("acento_concuerda.el_mismo");
	    case Nominativo:
		return Recursos.getCadena("acento_concuerda.nominativo");
	    case Innecesario:
		return Recursos.getCadena("acento_concuerda.innecesario");
	    case Forzado:
		return Recursos.getCadena("acento_concuerda.forzado");
	    }
	    throw new RuntimeException("concordancia de acento " + ac + " no encontrada");
	} else if (enumeracion instanceof PosicionConcuerda) {
	    PosicionConcuerda pc = (PosicionConcuerda) enumeracion;
	    switch (pc) {
	    case Estandar:
		return Recursos.getCadena("posicion_concuerda.estandar");
	    case ReferenciaNatural:
		return Recursos.getCadena("posicion_concuerda.referencia_natural");
	    case UltimaONormal:
		return Recursos.getCadena("posicion_concuerda.ultima_o_normal");
	    case NGSing:
		return Recursos.getCadena("posicion_concuerda.nominativo_o_genitivo");
	    case Silaba:
		return Recursos.getCadena("posicion_concuerda.silaba");
	    case GSing:
		return Recursos.getCadena("posicion_concuerda.genitivo_singular");
	    case NSing:
		return Recursos.getCadena("posicion_concuerda.nominativo_singular");
	    }
	    throw new RuntimeException("Concordancia de posición " + pc + " no encontrada");
	} else if (enumeracion instanceof TipoAdverbio) {
	    TipoAdverbio tipo = (TipoAdverbio) enumeracion;
	    switch (tipo) {
	    case Tiempo:
		return Recursos.getCadena("adverbio.tiempo");
	    case Lugar:
		return Recursos.getCadena("adverbio.lugar");
	    case Cantidad:
		return Recursos.getCadena("adverbio.cantidad");
	    case Medio:
		return Recursos.getCadena("adverbio.medio");
	    case Frecuencia:
		return Recursos.getCadena("adverbio.frecuencia");
	    case Negacion:
		return Recursos.getCadena("adverbio.negacion");
	    case Causa:
		return Recursos.getCadena("adverbio.causa");
	    case Modo:
		return Recursos.getCadena("adverbio.modo");
	    case Afirmacion:
		return Recursos.getCadena("adverbio.afirmacion");
	    case Otro:
		return Recursos.getCadena("adverbio.otro");
	    }
	    throw new RuntimeException("tipo de adverbio  " + tipo + " no encontrado");
	} else {
	    StringBuffer mensaje = new StringBuffer();
	    mensaje.append("no hay getCadena() definido para ese tipo de enumeración ");
	    mensaje.append(enumeracion);
	    if (enumeracion != null) {
		mensaje.append(" de la clase " + enumeracion.getClass().getName());
	    }
	    throw new RuntimeException(mensaje.toString());
	}
    }

}
