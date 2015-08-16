package tk.astris.weco.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import tk.astris.weco.ui.GUI;

public class MainInstallCreator {

	public static File safeDir = new File(System.getProperty("user.home") + "/astris/weco/InstallCreator/");
	public static File jarFile = new File(System.getProperty("user.home") + "/astris/weco/InstallCreator/installer.jar");
	public static File config = new File(System.getProperty("user.home") + "/astris/weco/InstallCreator/config.weco");
	public static File legal = new File(System.getProperty("user.home") + "/astris/weco/InstallCreator/legal.weco");
	public static File InstallerDir = new File(System.getProperty("user.home") + "/astris/weco/InstallCreator/installer");
	public static File finalFile = new File(System.getProperty("user.home") + "/astris/weco/InstallCreator/installer_finished.jar");
	public static File unzipDirEdit = new File(System.getProperty("user.home") + "/astris/weco/InstallCreator/installer/tk/astris/weco/main");
	public static ArrayList<File> files = new ArrayList<File>();
	public static GUI gui;
	
	public static void main(String[] args) throws IOException{
				
		if(!safeDir.exists()){
			safeDir.mkdirs();
		}
		
		if(!config.exists()){
			config.createNewFile();
		}
		
		if(InstallerDir.exists()){
			InstallerDir.delete();
		}
		
		if(!legal.exists()){
			legal.createNewFile();
		}
		
		gui = new GUI();
		gui.setVisible(true);
		
		
	}
	
}
