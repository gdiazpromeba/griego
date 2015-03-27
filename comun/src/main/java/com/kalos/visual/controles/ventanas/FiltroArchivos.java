// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.kalos.visual.controles.ventanas;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FiltroArchivos extends FileFilter {

    public boolean accept(File file) {
	if (file.isDirectory())
	    return true;
	else
	    return file.getName().endsWith(".kal");
    }

    public String getDescription() {
	return "KAL\323S files";
    }
}
