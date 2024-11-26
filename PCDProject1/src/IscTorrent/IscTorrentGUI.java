package IscTorrent;

import IscTorrent.Nodes;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;

import Reading.ReadFiles;
import managers.DownloadTasksManager;

public class IscTorrentGUI {
	// TODO
	
	private JFrame gui;
	private File[] files;
	
	private void updateFileList(JList<String> fileList, Nodes node) {
	    // Obtém os nomes dos ficheiros armazenados no nó
	    List<String> fileNames = node.getLocalFileNames();
	    fileList.setListData(fileNames.toArray(new String[0]));
	}
	
	// Creates the IscTorrent interface and ....
	public IscTorrentGUI(Nodes node) {
		// TODO
		this.node = node;
		files = new ReadFiles().getFiles();
		
		for(File f : files) {
			System.out.println(f.getName() + "[" + f.length() + " Bytes]");
		}
		
		for(File f : files) {
			DownloadTasksManager dm = new DownloadTasksManager(f);
			System.out.println(f.getName() + "[" + dm.getNumBlocks() + " Blocks]\n" + "hash: " + dm.getHash());
		}
		
		
		gui = new JFrame("No - Porta: " + node.getPort());
		
		gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		addGuiContent();
		
		gui.pack();
		
		//Changes the window size
		gui.setSize(700,400);
		
		//Sets the window display location to the center of the screen
		gui.setLocationRelativeTo(null);
	}
	
	public void open() {
		// sets the GUI window visible
		gui.setVisible(true);
	}

	// Adds content to the GUI
	private void addGuiContent() {
		// TODO
		gui.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(0,3));
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(0,1));
		
		JLabel searchLabel = new JLabel("Texto a procurar:");
		JTextField searchNode = new JTextField(15);
		JButton searchButton = new JButton("Procurar");
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				
			}
		});
		
		top.add(searchLabel);
		top.add(searchNode);
		top.add(searchButton);
		
		gui.add(top, BorderLayout.NORTH);
		
		JList<String> fileList = new JList<>();
		updateFileList(fileList, this.node);; // Atualiza a lista com os ficheiros do nó
		 gui.add(new JScrollPane(fileList), BorderLayout.CENTER);
		
		JButton downloadButton = new JButton("Descarregar");
		JButton connectToNodeButton = new JButton("Liga a No");

		downloadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				
			}
		});
		
		connectToNodeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				connectToNodeButton.setEnabled(false);
				gui.setEnabled(false);
				
				JFrame nodeConectionGUI = new JFrame("Conectar a no");
				
				nodeConectionGUI.addWindowListener(new WindowAdapter() {
					@Override
		            public void windowClosing(WindowEvent e) {
		                gui.setEnabled(true);
		                connectToNodeButton.setEnabled(true);
					}
					
				});
				
				nodeConectionGUI.setLayout(new FlowLayout());
				
				JLabel address = new JLabel("Endereco:");
				JLabel port = new JLabel("Porta:");
				
				JTextField nameAdd = new JTextField(10);
				JTextField portNum = new JTextField(5);
				
				JButton cancelButton = new JButton("Cancelar");
				JButton okButton = new JButton("OK");
				
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						gui.setEnabled(true);
						connectToNodeButton.setEnabled(true);
						nodeConectionGUI.dispose();
						
					}
				});
				
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
		                String address = nameAdd.getText().trim();
		                String portText = portNum.getText().trim();
		                
						int portInt = Integer.parseInt(portText);
						gui.setEnabled(true);
						
						node.connectToNode(address,portInt);
						connectToNodeButton.setEnabled(true);
						nodeConectionGUI.dispose();	
					}
				});
				
				nodeConectionGUI.add(address);
				nodeConectionGUI.add(nameAdd);
				nodeConectionGUI.add(port);
				nodeConectionGUI.add(portNum);
				nodeConectionGUI.add(cancelButton);
				nodeConectionGUI.add(okButton);
				
				nodeConectionGUI.pack();
				nodeConectionGUI.setLocationRelativeTo(gui);
				
				nodeConectionGUI.setVisible(true);
			}
		});
		
		right.add(downloadButton);
		right.add(connectToNodeButton);
		
		gui.add(right, BorderLayout.EAST);
		
	}

}
