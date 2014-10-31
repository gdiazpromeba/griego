// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.visual.controles.botones;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import kalos.C.F;
import kalos.recursos.Recursos;

public class BotonBuscar extends JButton
{

    public BotonBuscar()
    {
        super(Recursos.cargador.getImagen("buscar16.gif"));
        setPreferredSize(new Dimension(22, 22));
        setMaximumSize(new Dimension(22, 22));
        A();
    }

    private void A()
    {
        addComponentListener(new ComponentAdapter() {

            public void componentResized(ComponentEvent componentevent)
            {
                A.setSize(new Dimension(22, 22));
            }

            final B A;

            
            {
                A = B.this;
                super();
            }
        }
);
    }
}
