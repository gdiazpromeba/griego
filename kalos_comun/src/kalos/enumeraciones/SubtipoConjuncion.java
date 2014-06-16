package kalos.enumeraciones;

public enum SubtipoConjuncion {
    Copulativa, Adversativa, Disyuntiva, Comparativa, Declarativa, Causal, Temporal, Conclusiva, Final, Condicional, Concesiva;

    public static String getCadena(SubtipoConjuncion tipo) {
        switch (tipo) {
        case Copulativa:
            return "COP";
        case Adversativa:
            return "ADV";
        case Disyuntiva:
            return "DIS";
        case Comparativa:
            return "COM";
        case Declarativa:
            return "DEC";
        case Causal:
            return "CAU";
        case Temporal:
            return "TEM";
        case Conclusiva:
            return "CNC";
        case Final:
            return "FIN";
        case Condicional:
            return "CND";
        case Concesiva:
            return "CON";
        default:
            throw new RuntimeException("tipo de conjunción");
        }
    }

    public static SubtipoConjuncion getEnum(String tipo) {
        if (tipo.equals("COP"))
            return Copulativa;
        else if (tipo.equals("ADV"))
            return Adversativa;
        else if (tipo.equals("DIS"))
            return Disyuntiva;
        else if (tipo.equals("COM"))
            return Comparativa;
        else if (tipo.equals("DEC"))
            return Declarativa;
        else if (tipo.equals("CAU"))
            return Causal;
        else if (tipo.equals("TEM"))
            return Temporal;
        else if (tipo.equals("CNC"))
            return Conclusiva;
        else if (tipo.equals("FIN"))
            return Final;
        else if (tipo.equals("CND"))
            return Condicional;
        else if (tipo.equals("CON"))
            return Concesiva;
        else
            throw new RuntimeException("el tipo de conjunción para la cadena " + tipo + " no existe ");
    }
}
