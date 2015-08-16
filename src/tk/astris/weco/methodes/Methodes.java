package tk.astris.weco.methodes;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class Methodes {
	
	public void compress(String inputFile,String compressedFile) throws ZipException {
		File base = new File(inputFile);
		ArrayList<File> filesToZip = new ArrayList<File>();
		
		if (base.isDirectory()) {
			filesToZip.addAll(Arrays.asList(base.listFiles()));
		}
		
		ZipFile zipFile = new ZipFile(compressedFile);

		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		parameters.setEncryptFiles(false);

		for (File f : filesToZip) {
			if (f.isDirectory()) {
				zipFile.addFolder(f.getAbsolutePath(), parameters);
			} else {
				zipFile.addFile(f, parameters);
			}
		}
		 }
	
	public void unzip(String resource, String location){
        try {
            ZipFile zipFile = new ZipFile(resource);

            @SuppressWarnings("unchecked")
            List<FileHeader> fileHeaders = zipFile.getFileHeaders();

            for(FileHeader fileHeader : fileHeaders) {
                    zipFile.extractFile(fileHeader, location);
            }

        } catch (ZipException e) {
            e.printStackTrace();
        }
	}
	
	
}
