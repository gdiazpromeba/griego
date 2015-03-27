package com.kalos.visual;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceBlue;
import java.awt.Color;
import java.io.PrintStream;
import java.util.Enumeration;
import javax.swing.*;

public class GerenteDeApariencias
{

    public static String G;
    public static String E;
    public static String F;
    public static String A = "Metal";
    public static String C = "Motif";
    public static String D = "GTK";
    public static String B[];



    public static Icon getIcon(String s)
    {
        return UIManager.getIcon(s);
    }

    public static void poneLYFPorDefecto()
    {
        try
        {
            PlasticLookAndFeel.setPlasticTheme(new ExperienceBlue());
            UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static void cambiaContenidosLyF()
    {
        UIManager.put("ComboBox.disabledForeground", new Color(100, 100, 100));
        UIManager.put("TextField.inactiveForeground", new Color(100, 100, 100));
        UIManager.put("Label.disabledForeground", new Color(100, 100, 100));
    }

    public static void listaClaves()
    {
        UIDefaults uidefaults = UIManager.getLookAndFeelDefaults();
        System.out.println((new StringBuilder()).append("Count Item = ").append(uidefaults.size()).toString());
        String as[] = {
            "Key", "Value"
        };
        String as1[][] = new String[uidefaults.size()][2];
        int i = 0;
        for(Enumeration enumeration = uidefaults.keys(); enumeration.hasMoreElements();)
        {
            Object obj = enumeration.nextElement();
            as1[i][0] = obj.toString();
            as1[i][1] = (new StringBuilder()).append("").append(uidefaults.get(obj)).toString();
            System.out.println((new StringBuilder()).append(as1[i][0]).append(" ,, ").append(as1[i][1]).toString());
            i++;
        }

        JFrame jframe = new JFrame("UIDefaults Key-Value sheet");
        JTable jtable = new JTable(as1, as);
        jframe.setContentPane(new JScrollPane(jtable));
        jframe.pack();
        jframe.setVisible(true);
    }

    public static void main(String args[])
    {
        listaClaves();
    }

    static 
    {
        G = "Kusntstoff";
        E = "Aqua";
        F = "Basic";
        B = (new String[] {
            E, G, F
        });
    }
}
