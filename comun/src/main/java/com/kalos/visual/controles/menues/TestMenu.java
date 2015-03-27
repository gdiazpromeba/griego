package com.kalos.visual.controles.menues;

import javax.swing.JFrame;

import com.kalos.visual.controles.deslizador.Deslizador;

public class TestMenu
{



    public static void main(String args[])
    {
        JFrame jframe = new JFrame();
        jframe.add(new Deslizador());
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }
}