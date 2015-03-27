// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.// Jad home page: http://www.kpdus.com/jad.html// Decompiler options: packimports(3) package com.kalos.recursos;import java.awt.Font;import java.io.*;import org.apache.commons.lang.StringUtils;import org.apache.log4j.Logger;import org.dom4j.*;import org.dom4j.io.*;public class Configuracion{    public Configuracion()    {    }    public static String getNombreConexion()    {        Node node = E.selectSingleNode("conexion");        return node.getText();    }    public static boolean isDebug()    {        return W;    }    public static int getMaximoFormasAM()    {        return Z;    }    public static String getUltimoIdioma()    {        return C;    }    public static String getIdiomaSignificados()    {        return R;    }    public static int getTamañoDiccionario()    {        return Integer.parseInt(U.attributeValue("sizeDiccionario"));    }    public static int getTamañoAM()    {        return Integer.parseInt(U.attributeValue("sizeAM"));    }    public static int getTamañoFlexion()    {        return Integer.parseInt(U.attributeValue("sizeFlexion"));    }    public static void setTamañoDiccionario(int i)    {        U.attribute("sizeDiccionario").setValue(Integer.toString(i));        reescribeIni();    }    public static void setTamañoAM(int i)    {        U.attribute("sizeAM").setValue(Integer.toString(i));        reescribeIni();    }    public static void setTamañooFlexion(int i)    {        U.attribute("sizeFlexion").setValue(Integer.toString(i));        reescribeIni();    }    public static void setUltimoIdioma(String s)    {        F.attribute("valor").setValue(s);        C = F.attributeValue("valor");    }    public static void setIdiomaSignificados(String s)    {        O.attribute("valor").setValue(s);        R = O.attributeValue("valor");    }    public static void setUltimoTeclado(String s)    {        S.setText(s);        _ = S.getText();    }    public static String getUltimoTeclado()    {        return _;    }    public static void reescribeIni()    {        try        {            File file = new File((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("kalosini.xml").toString());            FileOutputStream fileoutputstream = new FileOutputStream(file);            OutputFormat outputformat = OutputFormat.createPrettyPrint();            outputformat.setEncoding("UTF-8");            XMLWriter xmlwriter = new XMLWriter(fileoutputstream, outputformat);            xmlwriter.write(A);            xmlwriter.flush();        }        catch(IOException ioexception)        {            System.out.println("error re-writing kalosini.xml");            ioexception.printStackTrace();        }    }    public static boolean getMuestraMakrones()    {        return H;    }    public static void setMuestraMakrones(boolean flag)    {        H = flag;        K.setText(flag ? "true" : "false");    }    public static String getVersionNumero()    {        return L;    }    public static void setVersionNumero(String s)    {        L = s;        G.setText(s);    }    public static String getAnclaRecursos()    {        return Y;    }    public static String getComandoAbre()    {        return T;    }    public static String getParmetrosAbre()    {        return D;    }    public static String getComandoCierra()    {        return B;    }    public static String getParmetrosCierra()    {        return c;    }    public static long getVecesVentana()    {        return X;    }    public static void aumentaVecesVentana()    {        X++;    }    public static Font getFont()    {        return V;    }    public static void setFont(Font font)    {        V = font;        U.attribute("name").setValue(font.getName());        U.attribute("size").setValue(Integer.toString(font.getSize()));        reescribeIni();    }    public static String getNombre()    {        String s = b.attribute("nombre").getValue();        if(StringUtils.isEmpty(s))            return null;        else            return s;    }    public static void setNombre(String s)    {        if(s == null)            b.attribute("nombre").setValue("");        else            b.attribute("nombre").setValue(s);        reescribeIni();    }    private static Logger d;    private static Font V;    private static Element E;    private static Document A;    private static boolean W;    private static String C;    private static String _;    private static String R;    private static String L;    static Element U;    static Element I;    static Element F;    static Element S;    static Element G;    static Element O;    static Element N;    static Element Q;    static Element a;    static Element P;    private static boolean H;    static Element K;    static Element J;    private static String Y;    private static String T;    private static String B;    private static String D;    private static String c;    private static long X = 0L;    private static Element b;    private static Element M;    private static int Z;    static     {        d = Logger.getLogger(com.kalos.recursos.Configuracion.class.getName());        try        {        	String home = System.getProperty("user.home");        	File file = new File(home + "/kalosini.xml");            if(file.exists())                System.out.println("el archivo de inicialización existe");            else                System.out.println("el archivo de inicialización no existe");            FileInputStream fileinputstream = new FileInputStream(file);            SAXReader saxreader = new SAXReader();            A = saxreader.read(fileinputstream);            E = A.getRootElement();        }        catch(Exception exception)        {            System.out.println("Error reading congiguration file kalos.xml");            exception.printStackTrace();            System.exit(0);        }        W = Boolean.parseBoolean(E.element("debug").getText());        F = E.element("ultimo_idioma");        O = E.element("idioma_significados");        S = E.element("ultimo_teclado");        G = E.element("version");        C = F.attributeValue("valor");        R = O.attributeValue("valor");        _ = S.getText();        L = G.getText();        U = E.element("font");        String s = U.attributeValue("name");        int i = Integer.parseInt(U.attributeValue("size"));        V = new Font(s, 0, i);        if(!V.canDisplay('\u1F87'))        {            d.warn((new StringBuilder()).append("ninguna font hallada para el nombre=").append(s).append(" tama\361o ").append(i).toString());            V = null;        }        J = E.element("claserecursos");        Y = J.attributeValue("valor");        b = E.element("registrado");        M = E.element("analisis-morfologico");        Z = Integer.parseInt(M.attribute("maximo-formas").getValue());    }}