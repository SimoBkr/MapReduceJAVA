package view;

public class JrootFrameTransparent {
/*
 * 
 * 
 * 
 * package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

import filehandler.FileHandler;
import imagehandler.ImageResizer;

@SuppressWarnings("serial")
public class MapReduce extends JFrame{
	
	private Border border;
	private Color inActive = new Color(236,240,241);
	private Color panelsBackColor = new Color(0,120,215);//(0,153,188);//(81,92,107);//(236,240,241);//(52,152,219);
	private Color paneBackColor = new Color(189,195,199);//(41,128,185);
	private Color success = new Color(22,160,133);
	private Color turquoiseSuccess = new Color(26,188,156);
	private Color btnColor = new Color(231,76,60);
	
	private JPanel panelFileUpload, panelChiffreAffaire, panelResult;
	
	private JButton btnFileUpload, btnSplitFile, btnCalculate, btnReset;
	
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
		btnFileUpload.setBackground(success);
		btnFileUpload.setOpaque(true);
		btnFileUpload.setBorderPainted(false);
		
		
		
		btnSplitFile = new JButton();
		icn = new ImageIcon(imagehandler.ImageResizer.getScaledImage((new ImageIcon((FileHandler.icons+"/split_file.png").substring(5))).getImage(), 20, 20));
		btnSplitFile.setIcon(icn);
		btnSplitFile.setText("Decomposer le fichier");
		btnSplitFile.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSplitFile.setBackground(turquoiseSuccess);
		btnSplitFile.setOpaque(true);
		btnSplitFile.setBorderPainted(false);
		
		btnCalculate = new JButton();
		btnCalculate.setText("Calculer le chiffre d'affaire");
		btnCalculate.setBackground(btnColor);
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
		lblRsltFileLink.setBackground(success);
	}
	
	private void initCombos() {
		comboAnnee = new JComboBox<String>();
		comboProductRef = new JComboBox<String>();
		comboAnnee.setBackground(success);
	}
	
	private void initPanels() {
		//fileUploadPanel
		panelFileUpload = new JPanel();
		border = BorderFactory.createTitledBorder("Choisir un fichier");
		panelFileUpload.setBorder(border);
		panelFileUpload.setPreferredSize(new Dimension(395,100));
		panelFileUpload.setLayout(new BorderLayout());
		panelFileUpload.setBackground(panelsBackColor);
		
		//ChiffreAffairePanel
		panelChiffreAffaire = new JPanel();
		border = BorderFactory.createTitledBorder("Choix de produit et d'annee");
		panelChiffreAffaire.setBorder(border);
		panelChiffreAffaire.setPreferredSize(new Dimension(395,100));
		panelChiffreAffaire.setLayout(new GridLayout(3,2));
		panelChiffreAffaire.setBackground(panelsBackColor);
		
		//ResultPanel
		panelResult = new JPanel();
		border = BorderFactory.createTitledBorder("Resultat");
		panelResult.setBorder(border);
		panelResult.setPreferredSize(new Dimension(800,390));
		panelResult.setBackground(panelsBackColor);
		panelResult.setLayout(new GridLayout(5, 2));
		
		//transparent panels
		panelChiffreAffaire.setBackground(new Color(1.0f,1.0f,1.0f,0.9f));
		panelResult.setBackground(new Color(1.0f,1.0f,1.0f,0.9f));
		panelFileUpload.setBackground(new Color(1.0f,1.0f,1.0f,0.9f));
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
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		setResizable(false);
		setSize(800,600);
		getContentPane().setBackground(paneBackColor);
		DefaultMetalTheme z =
	            new DefaultMetalTheme ()
	            {
	                //inactive title color
	                public ColorUIResource
	                getWindowTitleInactiveBackground() 
	                { 
	                    return new ColorUIResource 
	                        (inActive); 
	                }

	                //active title color
	                public ColorUIResource
	                getWindowTitleBackground() 
	                { 
	                    return new ColorUIResource 
	                        (new Color(122,117,116)); 
	                }
	                //start ActiveBumps
	                public ColorUIResource 
	                getPrimaryControlHighlight() 
	                { 
	                    return new ColorUIResource 
	                        (new Color(122,117,116)); 
	                }
	                public ColorUIResource 
	                getPrimaryControlDarkShadow() 
	                { 
	                    return new ColorUIResource 
	                        (new Color(122,117,116)); 
	                }

	                public ColorUIResource 
	                getPrimaryControl() 
	                { 
	                    return new ColorUIResource 
	                        (new Color(122,117,116)); 
	                }
	                //end ActiveBumps

	                //start inActiveBumps
	                public ColorUIResource 
	                getControlHighlight ()
	                {
	                    return new ColorUIResource 
	                        (inActive); 
	                }

	                public ColorUIResource 
	                getControlDarkShadow ()
	                {
	                    return new ColorUIResource 
	                        (inActive); 
	                }

	                public ColorUIResource 
	                getControl ()
	                {
	                    return new ColorUIResource 
	                        (inActive); 
	                }
	                //end inActiveBumps



	            };
	            
	            
	            MetalLookAndFeel.setCurrentTheme
	            (
	                z
	            );

	            try
	            {
	                UIManager.setLookAndFeel
	                (
	                    new MetalLookAndFeel ()
	                );
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace ();
	            }   

	            setBackground(new Color(1.0f,1.0f,1.0f,0.5f));
	            SwingUtilities.updateComponentTreeUI (this);
	}
	
	
    //private Graphics paintComponent(Graphics g) {
     //   Graphics2D g2d = (Graphics2D) g;
      //  g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        //int w = getWidth();
        //int h = getHeight();
        //Color color1 = Color.RED;
        //Color color2 = Color.GREEN;
        //GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        //g2d.setPaint(gp);
        //g2d.fillRect(0, 0, w, h);
        //return g;
    //}
	public MapReduce(String title) {
		super(title);
		init();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MapReduce("Chiffre affaire");
	}
}

 * 
 * 
 */
}
