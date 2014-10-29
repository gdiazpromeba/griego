package kalos.flexion.declinacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kalos.enumeraciones.Caso;
import kalos.enumeraciones.Genero;
import kalos.enumeraciones.GradoComparacion;
import kalos.enumeraciones.Numero;

public class OcAdjetivo {
    private Map<GradoComparacion, Map<Genero, OcNominal>> ocurrencias = new HashMap<GradoComparacion, Map<Genero, OcNominal>>();

    public String getFormaIndividual(GradoComparacion parami, Genero genero, Caso caso, Numero numero, int indice) {
	Map<Genero, OcNominal> localMap = this.ocurrencias.get(parami);
	OcNominal localD = localMap.get(genero);
	if (localD == null) {
	    return null;
	}
	return localD.getFormaIndividual(caso, numero, indice);
    }

    public String getFormaPrimeraForma(GradoComparacion parami, Genero genero, Caso parama, Numero paramn) {
	Map<Genero, OcNominal> localMap = this.ocurrencias.get(parami);
	OcNominal localD = localMap.get(genero);
	if (localD == null) {
	    return null;
	}
	return localD.getPrimeraForma(parama, paramn);
    }

    public void setFormaIndividual(GradoComparacion parami, Genero genero, Caso parama, Numero paramn,
	    String paramString) {
	Map<Genero, OcNominal> localObject = this.ocurrencias.get(parami);
	if (localObject == null) {
	    localObject = new HashMap<Genero, OcNominal>();
	    this.ocurrencias.put(parami, localObject);
	}
	OcNominal localD = localObject.get(genero);
	if (localD == null) {
	    localD = new OcNominal();
	    localObject.put(genero, localD);
	}
	localD.setFormaIndividual(parama, paramn, paramString);
    }

    public void setFormaIndividual(GradoComparacion parami, Genero paramA, Caso parama, Numero paramn,
	    String paramString, int paramInt) {
	Map<Genero, OcNominal> localObject = this.ocurrencias.get(parami);
	if (localObject == null) {
	    localObject = new HashMap<Genero, OcNominal>();
	    this.ocurrencias.put(parami, localObject);
	}
	OcNominal localD = localObject.get(paramA);
	if (localD == null) {
	    localD = new OcNominal();
	    localObject.put(paramA, localD);
	}
	localD.setFormaIndividual(parama, paramn, paramString, paramInt);
    }

    public int cantidadDeFormas(GradoComparacion parami, Genero paramA, Caso parama, Numero numero) {
	Map<Genero, OcNominal> localMap = this.ocurrencias.get(parami);
	OcNominal localD = localMap.get(paramA);
	if (localD == null) {
	    return 0;
	}
	return localD.cantidadDeFormas(parama, numero);
    }

    public int cantidadDeFormasDelCaso(GradoComparacion parami, Genero paramA, Caso parama) {
	Map<Genero, OcNominal> localMap = this.ocurrencias.get(parami);
	OcNominal localD = localMap.get(paramA);
	if (localD == null) {
	    return 0;
	}
	return localD.cantidadDeFormasDelCaso(parama);
    }

    public List<String> getForm(GradoComparacion parami, Genero paramA, Caso parama, Numero paramn) {
	Map<Genero, OcNominal> localMap = this.ocurrencias.get(parami);
	if (localMap == null) {
	    return null;
	}
	OcNominal localD = localMap.get(paramA);
	if (localD == null) {
	    return null;
	}
	return localD.getFormas(parama, paramn);
    }

    public Set<Genero> getGeneros(GradoComparacion parami) {
	Map<Genero, OcNominal> localMap = this.ocurrencias.get(parami);
	return localMap.keySet();
    }

    public Set<GradoComparacion> getGrados() {
	return this.ocurrencias.keySet();
    }

    public OcNominal getOcurrenciaNominal(GradoComparacion parami, Genero paramA) {
	Map<Genero, OcNominal> localMap = this.ocurrencias.get(parami);
	if (localMap != null) {
	    return localMap.get(paramA);
	}
	return null;
    }

    public void setOcurrenciaNominal(GradoComparacion parami, Genero paramA, OcNominal paramD) {
	Map<Genero, OcNominal> localObject = this.ocurrencias.get(parami);
	if (localObject == null) {
	    localObject = new HashMap<Genero, OcNominal>();
	    this.ocurrencias.put(parami, localObject);
	}
	localObject.put(paramA, paramD);
    }

    public String getFormaSubindiceOPrimera(GradoComparacion parami, Genero paramA, Caso parama, Numero paramn,
	    int indice) {
	int i = cantidadDeFormas(parami, paramA, parama, paramn);
	String str = null;
	if (indice >= i) {
	    str = getFormaPrimeraForma(parami, paramA, parama, paramn);
	} else {
	    str = getFormaIndividual(parami, paramA, parama, paramn, indice);
	}
	return str;
    }
}
