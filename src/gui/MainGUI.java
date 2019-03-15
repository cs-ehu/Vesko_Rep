package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import configuration.ConfigXML;
import domain.Client;
import domain.Owner;
import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;


public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton boton1 = null;
	private JButton boton3 = null;

	private static ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();
	
	public static ApplicationFacadeInterfaceWS getBusinessLogic(){
		return facade;
	}
	
	public static void setBussinessLogic (ApplicationFacadeInterfaceWS afi){
		facade=afi;
	}
	protected JLabel lblNewLabel;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblLogedInAs;
	public JLabel lblType;
	protected static Owner owner;
	protected static Client client;
	
	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);

			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(491, 292);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			JButton btnSignIn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnSignIn.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnSignIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new LoginGUI();
					dispose();
					a.setVisible(true);
					
				}
			});
			jContentPane.add(getLblLogedInAs());
			jContentPane.add(getLblType());
			btnSignIn.setBounds(155, 185, 144, 49);
			jContentPane.add(btnSignIn);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getPanel());
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes boton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (boton3 == null) {
			boton3 = new JButton();
			boton3.setBounds(0, 61, 477, 60);
			boton3.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailability"));
			boton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new QueryAvailabilityGUI();

					a.setVisible(true);
				}
			});
		}
		return boton3;
	}
	

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			lblNewLabel.setBounds(0, 1, 477, 60);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 123, 477, 49);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		boton3.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailability"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	private JLabel getLblLogedInAs() {
		if (lblLogedInAs == null) {
			lblLogedInAs = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.lblLogedInAs.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblLogedInAs.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblLogedInAs.setBounds(356, 13, 105, 16);
		}
		return lblLogedInAs;
	}
	private JLabel getLblType() {
		if (lblType == null) {
			lblType = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.lblNewLabel_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblType.setBounds(356, 30, 92, 16);
		}
		return lblType;
	}
	protected Owner getMainOwner(){
		FacadeImplementationWS facade= new FacadeImplementationWS();
		return facade.getOwnerByUsername(owner.getUserName());
	}
} // @jve:decl-index=0:visual-constraint="0,0"

