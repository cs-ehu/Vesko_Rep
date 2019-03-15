package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import configuration.ConfigXML;
import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterfaceWS;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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


public class ClientMainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton boton1 = null;
	private JButton boton3 = null;

    private static ApplicationFacadeInterfaceWS appFacadeInterface;
	
	public static ApplicationFacadeInterfaceWS getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (ApplicationFacadeInterfaceWS afi){
		appFacadeInterface=afi;
	}
	protected JLabel lblNewLabel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnLogOut;
	
	/**
	 * This is the default constructor
	 */
	public ClientMainGUI() {
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ApplicationFacadeInterfaceWS facade=ClientMainGUI.getBusinessLogic();
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);

			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getBtnLogOut());
			
			JButton btnSeeMyOffer = new JButton();
			btnSeeMyOffer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					MisReservasGUI a = new MisReservasGUI();
					a.setVisible(true);
				}
			});
			btnSeeMyOffer.setText(ResourceBundle.getBundle("Etiquetas").getString("ClientMainGUI.btnSeeMyOffer.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnSeeMyOffer.setBounds(0, 122, 477, 60);
			jContentPane.add(btnSeeMyOffer);
			
			JButton btnModifyAccount = new JButton();
			btnModifyAccount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					ModifyAccountGUI a = new ModifyAccountGUI("Client");
					a.setVisible(true);
				}
			});
			btnModifyAccount.setText(ResourceBundle.getBundle("Etiquetas").getString("ClientMainGUI.btnModifyAccount.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnModifyAccount.setBounds(0, 215, 477, 60);
			jContentPane.add(btnModifyAccount);
		}
		return jContentPane;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(491, 397);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
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
					JFrame a = new QueryAvailabilityClientAdvacedGUI();
					dispose();
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
	
	private void redibujar() {
		lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		boton3.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailability"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	private JButton getBtnLogOut() {
		if (btnLogOut == null) {
			btnLogOut = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnLogOut.text"));
			btnLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					MainGUI a = new MainGUI();
					a.client=null;
					a.setVisible(true);
				}
			});
			btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnLogOut.setBounds(165, 288, 144, 49);
		}
		return btnLogOut;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

