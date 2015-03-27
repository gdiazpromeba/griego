package com.kalos.visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class ShowUIDefaults extends JFrame implements ActionListener {
    class _A extends JLabel implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable jtable, Object obj, boolean flag, boolean flag1, int i,
		int j) {
	    setBackground(null);
	    setBorder(null);
	    setIcon(null);
	    setText("");
	    if (obj instanceof Color) {
		setBackground((Color) obj);
	    } else if (obj instanceof Border) {
		setBorder((Border) obj);
	    } else if (obj instanceof Font) {
		setText("Sample");
		setFont((Font) obj);
	    } else if (obj instanceof Icon) {
		setIcon((Icon) obj);
	    }
	    return this;
	}

	public void paint(Graphics g) {
	    try {
		super.paint(g);
	    } catch (Exception exception) {
	    }
	}

	public _A() {
	    super();
	    setHorizontalAlignment(0);
	    setOpaque(true);
	}
    }

    JFrame C;
    JTabbedPane B;
    _A A;

    public ShowUIDefaults(String s) {
	super(s);
	C = this;
	A = new _A();
	getContentPane().setLayout(new BorderLayout());
	B = A();
	getContentPane().add(B);
	JPanel jpanel = new JPanel();
	jpanel.setLayout(new GridLayout(1, 3));
	getContentPane().add(jpanel, "South");
	JButton jbutton = new JButton("Metal");
	jbutton.setActionCommand("javax.swing.plaf.metal.MetalLookAndFeel");
	jbutton.addActionListener(this);
	jpanel.add(jbutton);
	JButton jbutton1 = new JButton("Windows");
	jbutton1.setActionCommand("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	jbutton1.addActionListener(this);
	jpanel.add(jbutton1);
	JButton jbutton2 = new JButton("Motif");
	jbutton2.setActionCommand("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
	jbutton2.addActionListener(this);
	jpanel.add(jbutton2);
    }

    public void actionPerformed(ActionEvent actionevent) {
	try {
	    UIManager.setLookAndFeel(actionevent.getActionCommand());
	} catch (Exception exception) {
	    System.out.println(exception);
	}
	getContentPane().remove(B);
	B = A();
	getContentPane().add(B);
	SwingUtilities.updateComponentTreeUI(C);
	C.pack();
    }

    private JTabbedPane A() {
	TreeMap treemap = new TreeMap();
	UIDefaults uidefaults = UIManager.getLookAndFeelDefaults();
	Object obj = uidefaults.keys();
	do {
	    if (!((Enumeration) (obj)).hasMoreElements()) {
		break;
	    }
	    Object obj1 = ((Enumeration) (obj)).nextElement();
	    Object obj2 = uidefaults.get(obj1);
	    Map map = A(((Map) (treemap)), obj1.toString());
	    if (map != null) {
		map.put(obj1, obj2);
	    }
	} while (true);
	obj = new JTabbedPane(3);
	((JTabbedPane) (obj)).setPreferredSize(new Dimension(800, 400));
	A(((JTabbedPane) (obj)), ((Map) (treemap)));
	return ((JTabbedPane) (obj));
    }

    private Map A(Map map, String s) {
	if (s.startsWith("class") | s.startsWith("javax")) {
	    return null;
	}
	int i = s.indexOf(".");
	String s1;
	if (i == -1) {
	    if (s.endsWith("UI")) {
		s1 = s.substring(0, s.length() - 2);
	    } else {
		s1 = "System Colors";
	    }
	} else {
	    s1 = s.substring(0, i);
	}
	if (s1.equals("Checkbox")) {
	    s1 = "CheckBox";
	}
	Object obj = map.get(s1);
	if (obj == null) {
	    obj = new TreeMap();
	    map.put(s1, obj);
	}
	return (Map) obj;
    }

    private void A(JTabbedPane jtabbedpane, Map map) {
	String as[] = { "Key", "Value", "Sample" };
	Set set = map.keySet();
	String s;
	JTable jtable;
	for (Iterator iterator = set.iterator(); iterator.hasNext(); jtabbedpane.addTab(s, new JScrollPane(jtable))) {
	    s = (String) iterator.next();
	    Map map1 = (Map) map.get(s);
	    Object aobj[][] = new Object[map1.size()][3];
	    int i = 0;
	    Set set1 = map1.keySet();
	    for (Iterator iterator1 = set1.iterator(); iterator1.hasNext();) {
		String s1 = (String) iterator1.next();
		aobj[i][0] = s1;
		Object obj = map1.get(s1);
		if (obj != null) {
		    aobj[i][1] = obj.toString();
		    aobj[i][2] = obj;
		} else {
		    aobj[i][1] = "null";
		    aobj[i][2] = "";
		}
		i++;
	    }

	    jtable = new JTable(aobj, as);
	    jtable.getColumnModel().getColumn(2).setCellRenderer(A);
	    jtable.getColumnModel().getColumn(0).setPreferredWidth(250);
	    jtable.getColumnModel().getColumn(1).setPreferredWidth(500);
	    jtable.getColumnModel().getColumn(2).setPreferredWidth(50);
	}

    }

    public static void main(String args[]) {
	ShowUIDefaults b = new ShowUIDefaults("UI Defaults");
	b.setDefaultCloseOperation(3);
	b.pack();
	b.setLocationRelativeTo(null);
	b.setVisible(true);
    }
}
