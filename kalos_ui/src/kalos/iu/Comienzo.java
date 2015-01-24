// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package kalos.iu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import kalos.C.A;
import kalos.C.F;
import kalos.D.A.C;
import kalos.D.A.D;
import kalos.D.A.E;
import kalos.D.A.H;
import kalos.D.A.L;
import kalos.D.A.N;
import kalos.D.A.O;
import kalos.D.A.P;
import kalos.D.A.R;
import kalos.D.A.S;
import kalos.E.E.KA;
import kalos.E.E.M;
import kalos.E.E.U;
import kalos.H.C.I;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

// Referenced classes of package kalos.iu:
//            A, B, C, E

public class Comienzo
{
    static class _A
        implements ActionListener
    {

        public void actionPerformed(ActionEvent actionevent)
        {
            JFrame jframe = new JFrame();
            kalos.iu.C c = (kalos.iu.C)Comienzo.J().getBean("menues");
            c.inicializa(jframe);
            jframe.setTitle((new StringBuilder()).append("KAL\323S ").append(kalos.C.A.getVersionNumero()).toString());
            jframe.setLayout(new BorderLayout());
            kalos.iu.Comienzo.I = new JPanel();
            kalos.iu.Comienzo.I.setLayout(new BorderLayout());
            JTabbedPane jtabbedpane = new JTabbedPane();
            kalos.iu.Comienzo.I.add(kalos.iu.Comienzo.P(), "South");
            kalos.iu.Comienzo.I.add(jtabbedpane, "Center");
            jtabbedpane.add(kalos.C.F.getCadena("diccionario"), kalos.iu.Comienzo.D());
            jtabbedpane.add(kalos.C.F.getCadena("analisis_morfologico"), kalos.iu.Comienzo.M());
            jtabbedpane.add(kalos.C.F.getCadena("flexion"), kalos.iu.Comienzo.L());
            jframe.add(kalos.iu.Comienzo.I);
            jframe.setSize(777, 550);
            jframe.setLocationRelativeTo(null);
            jframe.setVisible(true);
            Comienzo.Q().dispose();
            jframe.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent windowevent)
                {
                    System.exit(0);
                }

                final _A A;

                
                {
                    A = _A.this;
                    super();
                }
            }
);
        }

        _A()
        {
        }
    }


    public Comienzo()
    {
    }

    public static void main(String args[])
    {
        DOMConfigurator.configure("log4j.xml");
        kalos.C.F.cambiaLocale(kalos.C.A.getUltimoIdioma());
        kalos.A.A.poneLYFPorDefecto();
        H.info("********************** INICIANDO *********************************");
        H.info((new StringBuilder()).append("user.dir=").append(System.getProperty("user.dir")).toString());
        H.info((new StringBuilder()).append("user.home=").append(System.getProperty("user.home")).toString());
        H.info((new StringBuilder()).append("user.name=").append(System.getProperty("user.name")).toString());
        H.info((new StringBuilder()).append("os.name=").append(System.getProperty("os.name")).toString());
        H.info((new StringBuilder()).append("os.version=").append(System.getProperty("os.version")).toString());
        H.info((new StringBuilder()).append("font=").append(kalos.C.A.getFont()).toString());
        H.info((new StringBuilder()).append("versi\363n de Kal\363s=").append(kalos.C.A.getVersionNumero()).toString());
        E = new kalos.iu.A();
        S();
        R();
        H();
        C();
        C = new JFrame();
        C.setUndecorated(true);
        C.setContentPane(E);
        C.setSize(533, 400);
        C.setLocationRelativeTo(null);
        C.setVisible(true);
        O();
        E.fuenteDatosCacheable.addActionListener(new _A());
    }

    private static void O()
    {
        E.beans.setEnabled(false);
        E.fuenteDatosCacheable.setEnabled(false);
        (new Thread() {

            public void run()
            {
                kalos.iu.Comienzo.F().setMensajeProgreso(kalos.C.F.getCadena("progreso.creando_contexto"));
                if(kalos.C.A.isDebug())
                    kalos.iu.Comienzo.A(Comienzo.creaContexto());
                else
                    kalos.iu.Comienzo.A(Comienzo.creaContextoClasspath());
                kalos.iu.Comienzo.F().setMensajeProgreso(kalos.C.F.getCadena("progreso.creando_controles_visuales"));
                kalos.iu.Comienzo.A((kalos.iu.D.A)Comienzo.J().getBean("panelResultadosDiccionario"));
                kalos.iu.Comienzo.A((kalos.iu.C.A)Comienzo.J().getBean("panelResultadosAM"));
                kalos.iu.Comienzo.M().setApplicationContext(Comienzo.J());
                kalos.iu.Comienzo.A((B)Comienzo.J().getBean("panelProgreso"));
                kalos.iu.Comienzo.A((kalos.iu.F.A)Comienzo.J().getBean("panelTablaFlexion"));
                kalos.iu.Comienzo.L().setApplicationContext(Comienzo.J());
                (new Thread() {

                    public void run()
                    {
                        D d = (D)Comienzo.J().getBean("amParticipios");
                        d.setParticipios((I)Comienzo.J().getBean("participios"));
                        d.setAmUtil((C)Comienzo.J().getBean("amUtil"));
                        d.setAmNominal((R)Comienzo.J().getBean("amNominal"));
                        d.setAmVerbal((P)Comienzo.J().getBean("amVerbal"));
                        d.setExtractorPrefijos((N)Comienzo.J().getBean("extractorPrefijos"));
                        d.setGerenteVerbos((kalos.E.E.P)Comienzo.J().getBean("gerenteVerbos"));
                        d.setGerenteVerbosCompuestos((U)Comienzo.J().getBean("gerenteVerbosCompuestos"));
                        d.setGerenteVerbalizadorParticipios((M)Comienzo.J().getBean("gerenteVerbalizadorParticipios"));
                        kalos.D.A.F f = (kalos.D.A.F)Comienzo.J().getBean("amSustantivos");
                        f.setApplicationContext(Comienzo.J());
                        L l = (L)Comienzo.J().getBean("amAdjetivos");
                        l.setApplicationContext(Comienzo.J());
                        S s = (S)Comienzo.J().getBean("amParticulas");
                        s.setApplicationContext(Comienzo.J());
                        H h = (H)Comienzo.J().getBean("amConjunciones");
                        h.setApplicationContext(Comienzo.J());
                        E e = (E)Comienzo.J().getBean("amPreposiciones");
                        e.setApplicationContext(Comienzo.J());
                        O o = (O)Comienzo.J().getBean("amAdverbios");
                        o.setApplicationContext(Comienzo.J());
                        kalos.D.A.A a = (kalos.D.A.A)Comienzo.J().getBean("amInterjecciones");
                        a.setApplicationContext(Comienzo.J());
                    }

                    final _cls1 A;

                    
                    {
                        A = _cls1.this;
                        super();
                    }
                }
).start();
                kalos.iu.Comienzo.F().setMensajeProgreso(kalos.C.F.getCadena("progreso.creando_control_eventos"));
                new kalos.iu.E(kalos.iu.Comienzo.D(), kalos.iu.Comienzo.L());
                kalos.iu.Comienzo.F().fuenteDatosCacheable.setEnabled(true);
                kalos.iu.Comienzo.F().beans.setEnabled(true);
                kalos.iu.Comienzo.F().E.setEnabled(true);
                kalos.iu.Comienzo.F().setMensajeProgreso("");
                Comienzo.T();
            }

        }
).start();
        E.repaint();
    }

    private static void I()
    {
        KA ka = (KA)D.getBean("gerenteSeguridad");
        kalos.C.A.setNombre(ka.getNombre());
    }

    private static void S()
    {
        if(kalos.C.A.getUltimoIdioma().equals("en"))
            E.beans.setSelectedIndex(0);
        else
        if(kalos.C.A.getUltimoIdioma().equals("es"))
            E.beans.setSelectedIndex(1);
        else
        if(kalos.C.A.getUltimoIdioma().equals("fr"))
            E.beans.setSelectedIndex(2);
        else
        if(kalos.C.A.getUltimoIdioma().equals("pr"))
            E.beans.setSelectedIndex(3);
    }

    private static void R()
    {
        if(kalos.C.A.getIdiomaSignificados().equals("en"))
            E.E.setSelectedIndex(0);
        else
        if(kalos.C.A.getIdiomaSignificados().equals("es"))
            E.E.setSelectedIndex(1);
        else
        if(kalos.C.A.getIdiomaSignificados().equals("fr"))
            E.E.setSelectedIndex(2);
    }

    private static void H()
    {
        E.B.setSelectedItem(kalos.C.A.getUltimoTeclado());
    }

    private static void B()
    {
        kalos.C.A.setUltimoTeclado((String)E.B.getSelectedItem());
        kalos.C.A.reescribeIni();
    }

    private static void C()
    {
        E.beans.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent itemevent)
            {
                if(itemevent.getStateChange() != 1)
                {
                    return;
                } else
                {
                    kalos.iu.Comienzo.E();
                    return;
                }
            }

        }
);
        E.E.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent itemevent)
            {
                if(itemevent.getStateChange() != 1)
                {
                    return;
                } else
                {
                    Comienzo.G();
                    return;
                }
            }

        }
);
        E.B.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent itemevent)
            {
                if(itemevent.getStateChange() != 1)
                {
                    return;
                } else
                {
                    kalos.iu.Comienzo.A();
                    return;
                }
            }

        }
);
    }

    private static void K()
    {
        switch(E.beans.getSelectedIndex())
        {
        case 0: // '\0'
            kalos.C.A.setUltimoIdioma("en");
            kalos.C.A.reescribeIni();
            break;

        case 1: // '\001'
            kalos.C.A.setUltimoIdioma("es");
            kalos.C.A.reescribeIni();
            break;

        case 2: // '\002'
            kalos.C.A.setUltimoIdioma("fr");
            kalos.C.A.reescribeIni();
            break;

        case 3: // '\003'
            kalos.C.A.setUltimoIdioma("pr");
            kalos.C.A.reescribeIni();
            break;
        }
        kalos.C.F.cambiaLocale(kalos.C.A.getUltimoIdioma());
        E.fuenteDatosCacheable.setText(kalos.C.F.getCadena("comenzar"));
        E.fuenteDatosCacheable.repaint();
        E.fuenteDatosCacheable.revalidate();
        O();
    }

    private static void N()
    {
        switch(E.E.getSelectedIndex())
        {
        case 0: // '\0'
            kalos.C.A.setIdiomaSignificados("en");
            kalos.C.A.reescribeIni();
            break;

        case 1: // '\001'
            kalos.C.A.setIdiomaSignificados("es");
            kalos.C.A.reescribeIni();
            break;

        case 2: // '\002'
            kalos.C.A.setIdiomaSignificados("fr");
            kalos.C.A.reescribeIni();
            break;
        }
        O();
    }

    public static ApplicationContext creaContextoClasspath()
    {
        DefaultListableBeanFactory defaultlistablebeanfactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlbeandefinitionreader = new XmlBeanDefinitionReader(defaultlistablebeanfactory);
        ClassPathResource classpathresource = new ClassPathResource("daos.xml");
        xmlbeandefinitionreader.loadBeanDefinitions(classpathresource);
        classpathresource = new ClassPathResource("flexion.xml");
        xmlbeandefinitionreader.loadBeanDefinitions(classpathresource);
        classpathresource = new ClassPathResource("gerentes-datos.xml");
        xmlbeandefinitionreader.loadBeanDefinitions(classpathresource);
        classpathresource = new ClassPathResource("analisisMorfologico.xml");
        xmlbeandefinitionreader.loadBeanDefinitions(classpathresource);
        classpathresource = new ClassPathResource("iu.xml");
        xmlbeandefinitionreader.loadBeanDefinitions(classpathresource);
        GenericApplicationContext genericapplicationcontext = new GenericApplicationContext(defaultlistablebeanfactory);
        return genericapplicationcontext;
    }

    public static ApplicationContext creaContexto()
    {
        H.info((new StringBuilder()).append("creando nuevo contexto, con la configuraci\363n de significado =").append(kalos.C.A.getIdiomaSignificados()).toString());
        DefaultListableBeanFactory defaultlistablebeanfactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlbeandefinitionreader = new XmlBeanDefinitionReader(defaultlistablebeanfactory);
        FileSystemResource filesystemresource = new FileSystemResource((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("daos.xml").toString());
        xmlbeandefinitionreader.loadBeanDefinitions(filesystemresource);
        filesystemresource = new FileSystemResource((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("gerentes-datos.xml").toString());
        xmlbeandefinitionreader.loadBeanDefinitions(filesystemresource);
        filesystemresource = new FileSystemResource((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("analisisMorfologico.xml").toString());
        xmlbeandefinitionreader.loadBeanDefinitions(filesystemresource);
        filesystemresource = new FileSystemResource((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("flexion.xml").toString());
        xmlbeandefinitionreader.loadBeanDefinitions(filesystemresource);
        filesystemresource = new FileSystemResource((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("iu.xml").toString());
        xmlbeandefinitionreader.loadBeanDefinitions(filesystemresource);
        GenericApplicationContext genericapplicationcontext = new GenericApplicationContext(defaultlistablebeanfactory);
        return genericapplicationcontext;
    }

    static kalos.iu.A F()
    {
        return E;
    }

    static ApplicationContext A(ApplicationContext applicationcontext)
    {
        return D = applicationcontext;
    }

    static kalos.iu.D.A A(kalos.iu.D.A a)
    {
        return B = a;
    }

    static ApplicationContext J()
    {
        return D;
    }

    static kalos.iu.C.A A(kalos.iu.C.A a)
    {
        return G = a;
    }

    static kalos.iu.C.A M()
    {
        return G;
    }

    static B A(B b)
    {
        return F = b;
    }

    static kalos.iu.F.A A(kalos.iu.F.A a)
    {
        return A = a;
    }

    static kalos.iu.F.A L()
    {
        return A;
    }

    static kalos.iu.D.A D()
    {
        return B;
    }

    static void T()
    {
        I();
    }

    static B P()
    {
        return F;
    }

    static JFrame Q()
    {
        return C;
    }

    static void E()
    {
        K();
    }

    static void G()
    {
        N();
    }

    static void A()
    {
        B();
    }

    private static Logger H = Logger.getLogger(kalos/iu/Comienzo.getName());
    private static kalos.iu.D.A B;
    private static kalos.iu.C.A G;
    private static B F;
    private static kalos.iu.F.A A;
    private static kalos.iu.A E;
    private static JFrame C;
    private static ApplicationContext D;
    public static JPanel I;

}
