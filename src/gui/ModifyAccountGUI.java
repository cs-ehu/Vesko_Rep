package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Client;
import domain.Owner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;


import java.awt.Font;

public class ModifyAccountGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private JTextField txtTelegramId;
	private JTextField txtBancAccount;
	private JRadioButton rdbtnSendBookVia_1 = new JRadioButton("Send book via Email");
	private JRadioButton rdbtnSendBookVia = new JRadioButton("Send book via Telegram");
	private JLabel lblNewLabel = new JLabel("New label");
	private JLabel lblValidationSuccesfully = new JLabel("1");


	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();


	/**
	 * Create the frame.
	 */
	public ModifyAccountGUI(String type) {
		MainGUI a = new MainGUI();
		Owner o = a.owner;
		Client c = a.client;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setBounds(137, 13, 160, 22);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(50, 13, 75, 22);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(50, 48, 75, 22);
		contentPane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(50, 83, 75, 22);
		contentPane.add(lblEmail);
		
		JLabel lblTelegramId = new JLabel("Telegram Id");
		lblTelegramId.setBounds(50, 114, 75, 22);
		contentPane.add(lblTelegramId);
		
		JLabel lblBancAccount = new JLabel("Banc account");
		lblBancAccount.setBounds(50, 151, 75, 22);
		contentPane.add(lblBancAccount);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(137, 48, 160, 22);
		contentPane.add(txtPassword);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(137, 83, 160, 22);
		contentPane.add(txtEmail);
		
		txtTelegramId = new JTextField();
		txtTelegramId.setColumns(10);
		txtTelegramId.setBounds(137, 114, 160, 22);
		contentPane.add(txtTelegramId);
		
		txtBancAccount = new JTextField();
		txtBancAccount.setColumns(10);
		txtBancAccount.setBounds(137, 151, 160, 22);
		contentPane.add(txtBancAccount);
		
		JButton btnAceptChanges = new JButton("Validate changes");
		btnAceptChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(c!=null){
					MainGUI a = new MainGUI();
					c.setCuentaBancaria(txtBancAccount.getText());
					c.setPassword(txtPassword.getText());
					c.setEmail(txtEmail.getText());
					c.setTelegramId(txtTelegramId.getText());
					c.setSendBookByEmail(rdbtnSendBookVia_1.isSelected());
					c.setSendBookByTelegram(rdbtnSendBookVia.isSelected());
					a.client=facade.updateClient(c);
				}
				if(o!=null){
					o.setCuentaBancaria(txtBancAccount.getText());
					o.setPassword(txtPassword.getText());
					o.setEmail(txtEmail.getText());
					o.setTelegramId(txtTelegramId.getText());
					o.setSendBookByTelegram(rdbtnSendBookVia.isSelected());
					a.owner=facade.updateOwner(o);	
				}
				lblValidationSuccesfully.setText("The account has been succesfully updated");
				updateTxtFields(type);
			}
		});
		
		lblValidationSuccesfully.setForeground(new Color(0, 128, 0));
		lblValidationSuccesfully.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblValidationSuccesfully.setBounds(50, 344, 378, 16);
		lblValidationSuccesfully.setText("");
		contentPane.add(lblValidationSuccesfully);
		
		btnAceptChanges.setBounds(45, 303, 134, 25);
		contentPane.add(btnAceptChanges);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if(type.equals("Owner")){
					OwnerGUI a = new OwnerGUI();
					a.setVisible(true);
				}
				else{
					ClientMainGUI a = new ClientMainGUI();
					a.setVisible(true);
				}
			}
		});
		btnBack.setBounds(202, 303, 120, 25);
		contentPane.add(btnBack);
		
		rdbtnSendBookVia.setBounds(50, 209, 167, 25);
		contentPane.add(rdbtnSendBookVia);
		if (c != null)
			rdbtnSendBookVia.setSelected(c.isSendBookByTelegram());
		else {
			rdbtnSendBookVia.setEnabled(false);
		}
		
		rdbtnSendBookVia_1.setBounds(50, 241, 167, 25);
		contentPane.add(rdbtnSendBookVia_1);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(372, 16, 56, 16);
		contentPane.add(lblType);
		
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(372, 35, 75, 16);
		contentPane.add(lblNewLabel);
		if (c != null)
			rdbtnSendBookVia_1.setSelected(c.isSendBookByEmail());
		else {
			rdbtnSendBookVia_1.setEnabled(false);
		}
		lblNewLabel.setText(type);
		if (type.equals("Owner")){
			txtUsername.setText(o.getUserName());
			txtPassword.setText(o.getPassword());
			txtBancAccount.setText(o.getCuentaBancaria());
			txtEmail.setText(o.getEmail());
			txtTelegramId.setText(o.getTelegramId());
			rdbtnSendBookVia.setSelected(o.isSendBookByTelegram());
			rdbtnSendBookVia.setEnabled(true);
			rdbtnSendBookVia_1.setSelected(true);
		}
		else{
			txtUsername.setText(c.getUserName());
			txtPassword.setText(c.getPassword());
			txtBancAccount.setText(c.getCuentaBancaria());
			txtEmail.setText(c.getEmail());
			txtTelegramId.setText(c.getTelegramId());
			rdbtnSendBookVia.setSelected(c.isSendBookByTelegram());
			rdbtnSendBookVia_1.setSelected(c.isSendBookByEmail());
			rdbtnSendBookVia.setEnabled(true);
			rdbtnSendBookVia_1.setEnabled(true);
		}
	}
	public void updateTxtFields(String type){
		MainGUI a  = new MainGUI();
		Owner o = a.owner;
		Client c = a.client;
		if (type.equals("Owner")){
			txtUsername.setText(o.getUserName());
			txtPassword.setText(o.getPassword());
			txtBancAccount.setText(o.getCuentaBancaria());
			txtEmail.setText(o.getEmail());
			txtTelegramId.setText(o.getTelegramId());
			rdbtnSendBookVia.setEnabled(o.isSendBookByTelegram());

		}
		else{
			txtUsername.setText(c.getUserName());
			txtPassword.setText(c.getPassword());
			txtBancAccount.setText(c.getCuentaBancaria());
			txtEmail.setText(c.getEmail());
			txtTelegramId.setText(c.getTelegramId());
			rdbtnSendBookVia.setEnabled(c.isSendBookByTelegram());
			rdbtnSendBookVia_1.setEnabled(c.isSendBookByEmail());
		}
	}
}
