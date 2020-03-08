package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import Threads.CstmThread;
import colors.Colors;
import controller.homePage.ChartController;
import controller.homePage.ControllerHomePage;
import filehandler.FileHandler;
import imagehandler.ImageResizer;

@SuppressWarnings("serial")
public class MapReduce extends JFrame implements ActionListener{
	
	private Border border;
	
	private JPanel panelFileUpload, panelChiffreAffaire, panelResult;
	
	private JButton btnFileUpload, btnSplitFile, btnCalculate, btnReset;
	
	private JFileChooser chooser;
	
	
	private JLabel lblAnnee, lblRefProduct, 
	lblRsltFile1, lblRsltFile2,lblRsltFile3, lblRsltFinal, 
	lblRsltFile1Val, lblRsltFile2Val, lblRsltFile3Val, lblRsltFinalVal, lblRsltFileLink;
	
	private JComboBox<String> comboAnnee, comboProductRef;
	
	private void initBtns() {
		
		btnFileUpload = new JButton();
		btnFileUpload.setPreferredSize(new Dimension(80, 30));
		ImageIcon icn = new ImageIcon((FileHandler.icons+"/upload_file_icn.png").substring(5));
		//System.out.println((FileHandler.icons+"/upload_file_icn.png").substring(5));
		icn = new ImageIcon(
				ImageResizer.getScaledImage(icn.getImage(), 20, 20)
				);
		btnFileUpload.setIcon(icn);
		btnFileUpload.setText("Upload");
		btnFileUpload.setHorizontalAlignment(SwingConstants.RIGHT);
		btnFileUpload.setBackground(Colors.SUCCESS);
		btnFileUpload.setOpaque(true);
		btnFileUpload.setBorderPainted(false);
		btnFileUpload.addActionListener(this);
		
		
		
		btnSplitFile = new JButton();
		icn = new ImageIcon(imagehandler.ImageResizer.getScaledImage((new ImageIcon((FileHandler.icons+"/split_file.png").substring(5))).getImage(), 20, 20));
		btnSplitFile.setIcon(icn);
		btnSplitFile.setText("Decomposer le fichier");
		btnSplitFile.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSplitFile.setBackground(Colors.TURQUOISE_SUCCESS);
		btnSplitFile.setOpaque(true);
		btnSplitFile.setBorderPainted(false);
		btnSplitFile.addActionListener(this);
		
		btnCalculate = new JButton();
		btnCalculate.setText("Calculer le chiffre d'affaire");
		btnCalculate.setBackground(Colors.BTN_COLOR);
		btnCalculate.setOpaque(true);
		btnCalculate.setBorderPainted(false);
		
		
		btnReset= new JButton();
		btnReset.setText("Reset");
	}
	
	private void initLbls() {
		lblAnnee = new JLabel("Annee:");
		lblRefProduct = new JLabel("References de Produit:"); 
		lblRsltFile1 = new JLabel("Resultat du fichier 1:");
		lblRsltFile2 = new JLabel("Resultat du fichier 2:");
		lblRsltFile3 = new JLabel("Resultat du fichier 3");
		lblRsltFinal = new JLabel("Resultat du fichier finale:");
		lblRsltFile1Val = new JLabel("valeur sur le fichier 1");
		lblRsltFile2Val = new JLabel("valeur sur le fichier 2");
		lblRsltFile3Val = new JLabel("valeur sur le fichier 3");
		lblRsltFinalVal = new JLabel("valeur finale");
		lblRsltFileLink = new JLabel("ouvrir le fichier resultant");
		lblRsltFileLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRsltFileLink.setPreferredSize(new Dimension(800,20));
		lblRsltFileLink.setBackground(Colors.SUCCESS);
	}
	
	private void initCombos() {
		comboAnnee = new JComboBox<String>();
		comboProductRef = new JComboBox<String>();
		comboAnnee.setBackground(Colors.SUCCESS);
	}
	
	private void initPanels() {
		//fileUploadPanel
		panelFileUpload = new JPanel();
		border = BorderFactory.createTitledBorder("Choisir un fichier");
		panelFileUpload.setBorder(border);
		panelFileUpload.setPreferredSize(new Dimension(395,100));
		panelFileUpload.setLayout(new BorderLayout());
		panelFileUpload.setBackground(Colors.PANELSBACKCOLOR);
		
		//ChiffreAffairePanel
		panelChiffreAffaire = new JPanel();
		border = BorderFactory.createTitledBorder("Choix de produit et d'annee");
		panelChiffreAffaire.setBorder(border);
		panelChiffreAffaire.setPreferredSize(new Dimension(395,100));
		panelChiffreAffaire.setLayout(new GridLayout(3,2));
		panelChiffreAffaire.setBackground(Colors.PANELSBACKCOLOR);
		
		//ResultPanel
		panelResult = new JPanel();
		border = BorderFactory.createTitledBorder("Resultat");
		panelResult.setBorder(border);
		panelResult.setPreferredSize(new Dimension(800,390));
		panelResult.setBackground(Colors.PANELSBACKCOLOR);
		panelResult.setLayout(new GridLayout(5, 2));
		
		//transparent panels
//		panelChiffreAffaire.setBackground(new Color(1.0f,1.0f,1.0f,0.9f));
//		panelResult.setBackground(new Color(1.0f,1.0f,1.0f,0.9f));
//		panelFileUpload.setBackground(new Color(1.0f,1.0f,1.0f,0.9f));
	}
	
	private void addPanelsComponents() {
		panelFileUpload.add(btnFileUpload, BorderLayout.NORTH);
		panelFileUpload.add(btnSplitFile, BorderLayout.SOUTH);
		
		panelChiffreAffaire.add(lblAnnee);
		panelChiffreAffaire.add(comboAnnee);
		panelChiffreAffaire.add(lblRefProduct);
		panelChiffreAffaire.add(comboProductRef);
		panelChiffreAffaire.add(btnCalculate);
		
		
		panelResult.add(lblRsltFile1);
		panelResult.add(lblRsltFile1Val);
		panelResult.add(lblRsltFile2);
		panelResult.add(lblRsltFile2Val);
		panelResult.add(lblRsltFile3);
		panelResult.add(lblRsltFile3Val);
		panelResult.add(lblRsltFinal);
		panelResult.add(lblRsltFinalVal);
		panelResult.add(lblRsltFileLink);
	}
	private void addComponents() {
		add(panelFileUpload, BorderLayout.WEST);
		add(panelChiffreAffaire, BorderLayout.EAST);
		add(panelResult, BorderLayout.SOUTH);
	}
	
	private void init() {
		initBtns();
		initLbls();
		initCombos();
		initPanels();
		addPanelsComponents();
		addComponents();
		setResizable(false);
		setSize(800,600);
		getContentPane().setBackground(Colors.PANEBACKCOLOR);
	}
	
	public MapReduce(String title) {
		super(title);
		init();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MapReduce("Chiffre affaire");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	if(e.getSource() == btnFileUpload ) {
			//successfile.setVisible(false);
			//lienfile.setVisible(false);
			 chooser = new JFileChooser(System.getProperty("user.home") 
					+ System.getProperty("file.separator")
					+ "Documents");
			 chooser.setAcceptAllFileFilterUsed(true); 
            FileNameExtensionFilter restricttext = new FileNameExtensionFilter(".txt files", "txt"); 
            FileNameExtensionFilter restrictxls = new FileNameExtensionFilter(".xls files", "xlsx"); 
            FileNameExtensionFilter restrictcsv = new FileNameExtensionFilter(".csv files", "csv"); 
            chooser.addChoosableFileFilter(restricttext); 
            chooser.addChoosableFileFilter(restrictxls); 
            chooser.addChoosableFileFilter(restrictcsv); 
            int r = chooser.showOpenDialog(null); 
            if (r == JFileChooser.APPROVE_OPTION) { 
            	
            	
            	
            } 
		}
	
	if(e.getSource() == btnSplitFile) {
		
		System.out.println("Ok");
		try {
			
			FileHandler.fileSplitter(chooser.getSelectedFile().getAbsolutePath());
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} 	
		
		
	}
	
	
  if(e.getSource() == btnCalculate) {
		
	  ArrayList<String> Al = null;
		try {
			Al = FileHandler.ListeFichier(ControllerHomePage.pathtofile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Al);
		CstmThread c = new CstmThread(Al.get(0), "Chiffre d'affaire", comboBox.getSelectedItem().toString());
		CstmThread d = new CstmThread(Al.get(1), "Chiffre d'affaire", comboBox.getSelectedItem().toString());
		CstmThread e = new CstmThread(Al.get(2), "Chiffre d'affaire", comboBox.getSelectedItem().toString());
		c.setName("c");
		d.setName("d");
		e.setName("e");
	    c.start();
		d.start();
		e.start();
		new TestPieChart();
		if(c.getState() == State.TERMINATED && d.getState() == State.TERMINATED && e.getState() == State.TERMINATED) {
			try {
				
				ArrayList<String> files = FileHandler.ListeFichier(FileHandler.mainPath);
				
				ArrayList<String> lines = new ArrayList<>();
				
				FileHandler.deleteFile(files);
				lines.add(ChartController.fileCA1+"");
				lines.add(ChartController.fileCA2+"");
				lines.add(ChartController.fileCA3+"");
				FileHandler.writeToFile(new File(FileHandler.mainPath+FileSystems.getDefault().getSeparator()+"resultatCA.txt"),lines);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
		
		
		
	}
}
