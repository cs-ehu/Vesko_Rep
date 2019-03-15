package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;

import domain.Client;
import domain.Owner;
import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import exceptions.OverlappingClientExists;
import exceptions.OverlappingOwnerExist;

import java.awt.Font;
import java.awt.Component;

public class SignIn extends JFrame {

	private JPanel contentPane;
	private JTextField userTextField;
	private JPasswordField passwordField;
	private JTextField textEmail;
	private JTextField textCuentaBancaria;
	private JTextField textTelegramId;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnClient = new JRadioButton("Client");
	private JRadioButton rdbtnOwner = new JRadioButton("Owner");
	private JLabel lblRegister = new JLabel("");

	
	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();



	/**
	 * Create the frame.
	 */
	public SignIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userTextField = new JTextField();
		userTextField.setColumns(10);
		userTextField.setBounds(177, 73, 254, 26);
		contentPane.add(userTextField);
		
		JLabel label = new JLabel("Username:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(28, 73, 137, 29);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Password:");
		label_1.setBounds(66, 115, 81, 29);
		contentPane.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 115, 254, 26);
		contentPane.add(passwordField);
		
		JButton button = new JButton("Sign in");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = userTextField.getText();
				String password = new String(transform(passwordField
						.getPassword()));
				String bancAccount= textCuentaBancaria.getText();
				String email = textEmail.getText();
				String telegramId = textTelegramId.getText();
				if (username != null && password != null && bancAccount!=null
						&& !username.equals("") && !password.equals("") && !bancAccount.equals("") && !email.equals("") && email!=null) {
					try {
						Owner o = null;
						Client c = null;
						String s;
						if (rdbtnClient.isSelected()) {
							 c = facade.createClient(username, password,telegramId,bancAccount,email);
							 s="client";
						} else {
							 o=facade.createOwner(username, password,telegramId,bancAccount,email);
							 s="owner";
						}
						lblRegister.setForeground(Color.GREEN);
						lblRegister.setText("Registered correctly");
						ValidationGUI vgui = new ValidationGUI(o, c, s);
						vgui.setVisible(true);
						dispose();
					} catch (OverlappingClientExists e1) {
						lblRegister.setForeground(Color.RED);
						lblRegister.setText("Client already registered");
					} catch (OverlappingOwnerExist e1) {
						lblRegister.setForeground(Color.RED);
						lblRegister.setText("Owner already registered");
					}
				} else {
					lblRegister.setForeground(Color.RED);
					lblRegister.setText("Please, fill all the camps");
				}
			}
		});
		button.setBounds(177, 267, 254, 29);
		contentPane.add(button);
		
		buttonGroup.add(rdbtnClient);
		rdbtnClient.setSelected(true);
		rdbtnClient.setBounds(226, 0, 89, 50);
		contentPane.add(rdbtnClient);
		
		buttonGroup.add(rdbtnOwner);
		rdbtnOwner.setBounds(319, 8, 89, 35);
		contentPane.add(rdbtnOwner);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI a = new LoginGUI();
				dispose();
				a.setVisible(true);
			}
		});
		button_1.setBounds(28, 11, 144, 29);
		contentPane.add(button_1);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(66, 160, 56, 16);
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(177, 154, 254, 26);
		contentPane.add(textEmail);
		
		JLabel lblCuentaBancaria = new JLabel("Bank account:");
		lblCuentaBancaria.setBounds(66, 199, 99, 16);
		contentPane.add(lblCuentaBancaria);
		
		textCuentaBancaria = new JTextField();
		textCuentaBancaria.setColumns(10);
		textCuentaBancaria.setBounds(177, 193, 254, 26);
		contentPane.add(textCuentaBancaria);
		
		JLabel labelTelegramId = new JLabel("Telegram id:");
		labelTelegramId.setAutoscrolls(true);
		labelTelegramId.setBounds(66, 234, 99, 16);
		contentPane.add(labelTelegramId);
		
		textTelegramId = new JTextField();
		textTelegramId.setColumns(10);
		textTelegramId.setBounds(177, 228, 254, 26);
		contentPane.add(textTelegramId);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HelpTelegram a = new HelpTelegram();
				a.setVisible(true);
			}
		});
		btnHelp.setBounds(443, 228, 97, 25);
		contentPane.add(btnHelp);
		
		JLabel label_2 = new JLabel("*");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_2.setBounds(434, 78, 56, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_3.setBounds(434, 121, 56, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_4.setBounds(434, 199, 56, 16);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_5.setBounds(434, 15, 108, 16);
		contentPane.add(label_5);
		
		JLabel lblRequired = new JLabel("Required field");
		lblRequired.setBounds(454, 13, 88, 16);
		contentPane.add(lblRequired);
		
		lblRegister.setForeground(Color.RED);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegister.setBounds(201, 309, 207, 16);
		contentPane.add(lblRegister);
		
		JLabel label_6 = new JLabel("*");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_6.setBounds(434, 160, 56, 16);
		contentPane.add(label_6);
	}
	public String transform(char[] psw) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < psw.length; i++) {
			sb.append(psw[i]);
		}
		return sb.toString();
	}
}