/**
 * 
 */
package br.com.projeto.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author vitor
 *
 */
public class imagemUtil {

	public static byte[] extractBytes(String ImageName) throws IOException {
		File imgPath = new File(ImageName);
		byte[] fileContent = Files.readAllBytes(imgPath.toPath());
		
		return fileContent;
	}

}
