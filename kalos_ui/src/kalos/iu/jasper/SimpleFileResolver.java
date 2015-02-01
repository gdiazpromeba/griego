package kalos.iu.jasper;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRFontUtil.java 1498 2006-11-16 12:52:01Z teodord $
 */
public class SimpleFileResolver {

	protected List folders = null;

	/**
      	 *
      	 */
	public SimpleFileResolver(File parentFolder) {
		folders = new ArrayList();
		folders.add(parentFolder);
	}

	/**
      	 *
     	 */
	public SimpleFileResolver(List parentFolders) {
		folders = parentFolders;
	}

	/**
      	 *
      	 */
	public File resolveFile(String fileName) {
		if (fileName != null) {
			for (Iterator it = folders.iterator(); it.hasNext();) {
				File folder = (File) it.next();
				File file = new File(folder, fileName);
				if (file.exists() && file.isFile()) {
					return file;
				}
			}
		}
		return null;
	}

}