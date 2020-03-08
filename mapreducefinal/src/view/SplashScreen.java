package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.nio.file.FileSystems;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import filehandler.FileHandler;

@SuppressWarnings("serial")
public class SplashScreen extends JFrame{
	private JPanel panel;
	private JProgressBar progressBar;
	private ImageIcon img;
	private JLabel imgLbl, msg;
	
	private void initIcons() {
		img = new ImageIcon(FileHandler.icons+FileSystems.getDefault().getSeparator()+"logo.png");
	}
	
	
	private void initProgressBar() {
		progressBar = new JProgressBar();
	}
	
	private void initLbl() {
		imgLbl = new JLabel(img);
		msg = new JLabel();
	}
	
	private void initPanel(){
		panel = new JPanel();
		panel.setBackground(Color.black);
    	panel.setLayout(new FlowLayout());
    	panel.add(imgLbl);
	}
	
	private void addImage(){
        imgLbl.setSize(600,200);
        add(imgLbl);
    }
	
	private void init(){
		initIcons();
		initLbl();
		initProgressBar();
		initPanel();
		
		
		getContentPane().setLayout(null);//setting layout to null
        setUndecorated(true);//Turning off Title bar
        setSize(600,400);//Setting size
        setLocationRelativeTo(null);//Setting location to the center of screen
        getContentPane().setBackground(Color.magenta);//setting background color
        setVisible(true);//setting visibility
		
		
		
		//setUndecorated(true);
		//setBounds(100, 100, 1000, 400);
		//setBackground(new Color(85,95,105));
		//setVisible(true);
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public SplashScreen(String title) throws HeadlessException {
		super(title);
		init();
	}
	
	public static void main(String[] args) {
		new SplashScreen("Loading...");
	}
}
