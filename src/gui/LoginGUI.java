package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import dataAccess.DataAccess;
import domain.Client;
import domain.Owner;
import exceptions.NoExistsClient;
import exceptions.NoExistsOwner;
import exceptions.OverlappingClientExists;
import exceptions.OverlappingOwnerExist;
import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import businessLogic.Login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField userTextField;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnLogIn = new JButton("Log in");
	private JLabel lblRegister = new JLabel("");
	private JLabel lblIncorrect = new JLabel("");
	private JLabel lblDontHaveAn = new JLabel("Dont have an\r\n account yet?");
	private JButton btnSignIn = new JButton("Sign in");
	private JLabel lblPassword = new JLabel("Password:");
	private JLabel lblUsername = new JLabel("Username:");
	private JRadioButton rdbtnClient = new JRadioButton("Client");
	private JRadioButton rdbtnOwner = new JRadioButton("Owner");
	
	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();
	private final JButton btnForgotPassword = new JButton("Forgot Password?");
	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setTitle("Log in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(12, 74, 137, 29);
		contentPane.add(lblUsername);

		userTextField = new JTextField();
		userTextField.setBounds(135, 75, 254, 26);
		contentPane.add(userTextField);
		userTextField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(50, 116, 81, 29);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(135, 117, 254, 26);
		contentPane.add(passwordField);

		buttonGroup.add(rdbtnClient);
		rdbtnClient.setBounds(183, 1, 89, 50);
		rdbtnClient.setSelected(true);
		contentPane.add(rdbtnClient);

		buttonGroup.add(rdbtnOwner);
		rdbtnOwner.setBounds(276, 9, 89, 35);
		contentPane.add(rdbtnOwner);

		// Registrar
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn a = new SignIn();
				dispose();
				a.setVisible(true);
			}
		});

		btnSignIn.setBounds(182, 223, 207, 29);
		contentPane.add(btnSignIn);

		lblDontHaveAn.setBounds(12, 220, 171, 35);
		contentPane.add(lblDontHaveAn);

		// Log IN
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userTextField.getText();
				String password = new String(transform(passwordField
						.getPassword()));
				if (username != null && password != null
						&& !username.equals("") && !password.equals("")) {
					String type = null;
					if (rdbtnClient.isSelected()) {
						type = rdbtnClient.getText();
					}
					if (rdbtnOwner.isSelected()) {
						type = rdbtnOwner.getText();
					}

					Login login = new Login();
					try {
						System.out.println(type);
						boolean correctLogin = login.doLogin(username,
								password, type);
						if (correctLogin && rdbtnClient.isSelected()) {
							dispose();
							JFrame a = new ClientMainGUI();
							MainGUI main = new MainGUI();
							Client c = facade.getClientByUsername(username);
							main.client=c;
							if(!c.isValidated()){
								ValidationGUI b = new ValidationGUI(null, c, "client");
								b.setVisible(true);
							}else{
							a.setVisible(true);}
						} else if (correctLogin && rdbtnOwner.isSelected()) {
							Owner o = facade.getOwnerByUsername(username);
							dispose();
							OwnerGUI a = new OwnerGUI();
							MainGUI main = new MainGUI();
							main.owner=o;
							if(!o.isValidated()){
								ValidationGUI b = new ValidationGUI(o, null, "owner");
								b.setVisible(true);	
							}else{
							a.setVisible(true);}
						} else {
							lblIncorrect.setText("Incorrect Username/Password");
						}
					} catch (NoExistsOwner e1) {
						lblIncorrect.setForeground(Color.RED);
						lblIncorrect.setText("Owner not registered");
					} catch (NoExistsClient e2) {
						lblIncorrect.setForeground(Color.RED);
						lblIncorrect.setText("Client not registered");
					}
				} else {
					lblIncorrect.setForeground(Color.RED);
					lblIncorrect.setText("Please, fill all the camps");
				}

			}
		});

		btnLogIn.setBounds(135, 156, 254, 29);
		contentPane.add(btnLogIn);

		lblIncorrect.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIncorrect.setForeground(Color.RED);
		lblIncorrect.setBounds(135, 198, 254, 16);
		contentPane.add(lblIncorrect);

		lblRegister.setForeground(Color.RED);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegister.setBounds(183, 307, 207, 16);
		contentPane.add(lblRegister);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				JFrame jframe = new MainGUI();
				jframe.setVisible(true);
				MainGUI maingui = new MainGUI();
			}
		});
		btnBack.setBounds(12, 12, 144, 29);
		contentPane.add(btnBack);
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnForgotPassword.setBounds(182, 265, 207, 29);
		
		contentPane.add(btnForgotPassword);
	}

	public String transform(char[] psw) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < psw.length; i++) {
			sb.append(psw[i]);
		}
		return sb.toString();
	}
}