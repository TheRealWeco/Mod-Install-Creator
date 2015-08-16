package tk.astris.weco.ui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import net.iharder.dnd.FileDrop;
import net.lingala.zip4j.exception.ZipException;
import tk.astris.weco.main.MainInstallCreator;
import tk.astris.weco.methodes.Methodes;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;
	JTextArea areaFileList = new JTextArea();
	public File[] files;
	private JTextField installerTitel;
	public File icon = null;
	public InputStream input = null;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Files", null, panel, null);
		
		JLabel lblDragDrop = new JLabel("Drag & Drop File(s) here");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
						.addComponent(lblDragDrop))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDragDrop)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		areaFileList.setEditable(false);
		scrollPane.setViewportView(areaFileList);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		  new  FileDrop( panel, new FileDrop.Listener(){
			@Override
			public void filesDropped(File[] args) {
				files = args;
				areaFileList.setText("");
				for(int i = 0; i < args.length; i++){
					File file = args[i];
					areaFileList.append(file + "\n");
					MainInstallCreator.files.add(file);
				}
			}
		  });
		  
		  JPanel panel_1 = new JPanel();
		  tabbedPane.addTab("Settings", null, panel_1, null);
		  
		  JLabel lblIconForInstaller = new JLabel("Icon for Installer (.png) (optional)");
		  
		  JButton btnSelectIcon = new JButton("Select Icon");
		  btnSelectIcon.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		JFileChooser chooser = new JFileChooser();
		  		chooser.showDialog(null, "Select");
		  		icon = chooser.getSelectedFile();
		  		
		  	}
		  });
		  
		  JLabel lblInstallerName = new JLabel("Installer Titel");
		  
		  installerTitel = new JTextField();
		  installerTitel.setText("Mod Installer");
		  installerTitel.setColumns(10);
		  
		  JLabel lblLegalInformations = new JLabel("Legal Informations");
		  
		  JScrollPane scrollPane_1 = new JScrollPane();
		  
		  final JRadioButton rdbtnAddOptionTo = new JRadioButton("Add Option to launch Minecraft after installation");
		  rdbtnAddOptionTo.setSelected(true);
		  
		  JLabel lblSelectInstallerTemplate = new JLabel("Select Installer Template");
		  
		  
		  String[] comboBoxText = {"none", "normal"};
		  final JComboBox comboBox = new JComboBox(comboBoxText);
		  
		  JLabel lblOr = new JLabel("or");
		  
		  JButton btnCustomTemplate = new JButton("Select custom Template");
		  btnCustomTemplate.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		if(comboBox.getSelectedItem().toString().equals("none")){
		  			JFileChooser chooser = new JFileChooser();
		  			chooser.showDialog(null, "Safe");
		  			try {
						input = new FileInputStream(chooser.getSelectedFile());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
		  			
		  		}
		  	}
		  });
		  GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		  gl_panel_1.setHorizontalGroup(
		  	gl_panel_1.createParallelGroup(Alignment.LEADING)
		  		.addGroup(gl_panel_1.createSequentialGroup()
		  			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
		  				.addGroup(gl_panel_1.createSequentialGroup()
		  					.addContainerGap()
		  					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
		  						.addComponent(rdbtnAddOptionTo)
		  						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
		  						.addComponent(btnSelectIcon, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
		  						.addComponent(lblIconForInstaller)
		  						.addComponent(lblInstallerName)
		  						.addComponent(installerTitel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
		  						.addComponent(lblLegalInformations)
		  						.addComponent(lblSelectInstallerTemplate)
		  						.addComponent(comboBox, 0, 549, Short.MAX_VALUE)))
		  				.addGroup(gl_panel_1.createSequentialGroup()
		  					.addContainerGap()
		  					.addComponent(btnCustomTemplate, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
		  				.addGroup(gl_panel_1.createSequentialGroup()
		  					.addGap(275)
		  					.addComponent(lblOr)))
		  			.addContainerGap())
		  );
		  gl_panel_1.setVerticalGroup(
		  	gl_panel_1.createParallelGroup(Alignment.LEADING)
		  		.addGroup(gl_panel_1.createSequentialGroup()
		  			.addContainerGap()
		  			.addComponent(lblIconForInstaller)
		  			.addPreferredGap(ComponentPlacement.RELATED)
		  			.addComponent(btnSelectIcon)
		  			.addPreferredGap(ComponentPlacement.RELATED)
		  			.addComponent(lblInstallerName)
		  			.addPreferredGap(ComponentPlacement.RELATED)
		  			.addComponent(installerTitel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		  			.addPreferredGap(ComponentPlacement.RELATED)
		  			.addComponent(lblLegalInformations)
		  			.addGap(8)
		  			.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
		  			.addPreferredGap(ComponentPlacement.UNRELATED)
		  			.addComponent(rdbtnAddOptionTo)
		  			.addPreferredGap(ComponentPlacement.RELATED)
		  			.addComponent(lblSelectInstallerTemplate)
		  			.addPreferredGap(ComponentPlacement.RELATED)
		  			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		  			.addPreferredGap(ComponentPlacement.RELATED)
		  			.addComponent(lblOr)
		  			.addPreferredGap(ComponentPlacement.RELATED)
		  			.addComponent(btnCustomTemplate)
		  			.addContainerGap(92, Short.MAX_VALUE))
		  );
		  
		  final JTextArea areaLegalInformation = new JTextArea();
		  areaLegalInformation.setText("Empty");
		  scrollPane_1.setViewportView(areaLegalInformation);
		  panel_1.setLayout(gl_panel_1);
		  
		  JPanel panel_2 = new JPanel();
		  tabbedPane.addTab("Finish", null, panel_2, null);
		  
		  JButton btnExport = new JButton("Export");
		  btnExport.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		System.out.println(MainInstallCreator.config);
		  		MainInstallCreator.config.delete();
		  		MainInstallCreator.legal.delete();
		  		MainInstallCreator.finalFile.delete();
		  		try {
					MainInstallCreator.config.createNewFile();
					MainInstallCreator.legal.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		  		
		  		String fileString = "files=";
		  		if(!(files.length < 1)){
				for(int i = 0; i < files.length; i++){
					fileString = fileString + files[i] + ";";
				}
		  		}else{
			  		fileString = "files=" + files[0];
		  		}
		  		
		  		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(MainInstallCreator.config, true)))) {
		  			
		  		    out.println(fileString);
		  		    
		  		    if(icon != null){
		  		    out.println("icon=" + icon);
		  		    }
		  		    
		  		    if(!areaLegalInformation.getText().equals("Empty")){
			  		    out.println("legal=true");
		  		    }else{
			  		    out.println("legal=false");
		  		    }
		  		    out.println("titel=" + installerTitel.getText());
		  		    out.println("launch=" + rdbtnAddOptionTo.isSelected());

		  		    
		  		    out.close();
		  		}catch (IOException ex) {
		  			
		  		}
		  		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(MainInstallCreator.legal, true)))) {
		  			
		  			for (String line : areaLegalInformation.getText().split("\\n")){
		  			out.println(line);
		  			}
		  			
		  			out.close();
		  			
		  		} catch (IOException e2) {
					e2.printStackTrace();
				}
		  		
		  		if(input == null){
		  			System.out.println(comboBox.getSelectedItem().toString());
		  		input = getClass().getResourceAsStream(comboBox.getSelectedItem().toString());
		  		}
		  		
		  		try {
					Files.copy(input, MainInstallCreator.jarFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		  		
				if(MainInstallCreator.InstallerDir.exists()){
					MainInstallCreator.InstallerDir.delete();
				}
				
		  		Methodes methodes = new Methodes();
		  		
		  		methodes.unzip(MainInstallCreator.jarFile.toPath() + "", MainInstallCreator.InstallerDir.toPath() + "");
		  				  		
		  		try {
					Files.copy(MainInstallCreator.config.toPath(), new File(MainInstallCreator.unzipDirEdit, "config.weco").toPath(), StandardCopyOption.REPLACE_EXISTING);
					Files.copy(MainInstallCreator.legal.toPath(), new File(MainInstallCreator.unzipDirEdit, "legal.weco").toPath(), StandardCopyOption.REPLACE_EXISTING);
					if(icon != null){
					Files.copy(icon.toPath(), new File(MainInstallCreator.unzipDirEdit, "icon.png").toPath(), StandardCopyOption.REPLACE_EXISTING);
					}
					
					for(File f : MainInstallCreator.files){
						Files.copy(f.toPath(), new File(MainInstallCreator.unzipDirEdit, f.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);	
					}
					
				} catch (IOException e2) {
					e2.printStackTrace();
				}
		  				  		
		  		try {
					methodes.compress(MainInstallCreator.InstallerDir.toPath() + "", MainInstallCreator.finalFile.toPath() + "");
				} catch (ZipException e1) {
					e1.printStackTrace();
				}
		  		
		  		try {
					Desktop.getDesktop().open(MainInstallCreator.safeDir);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		  		
		  		
		  	}
		  });
		  GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		  gl_panel_2.setHorizontalGroup(
		  	gl_panel_2.createParallelGroup(Alignment.LEADING)
		  		.addGroup(gl_panel_2.createSequentialGroup()
		  			.addContainerGap()
		  			.addComponent(btnExport, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
		  			.addContainerGap())
		  );
		  gl_panel_2.setVerticalGroup(
		  	gl_panel_2.createParallelGroup(Alignment.LEADING)
		  		.addGroup(gl_panel_2.createSequentialGroup()
		  			.addContainerGap()
		  			.addComponent(btnExport, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
		  			.addContainerGap())
		  );
		  panel_2.setLayout(gl_panel_2);
		  
	}
}
