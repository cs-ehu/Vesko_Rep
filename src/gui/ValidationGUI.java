package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;

import bot.Comand;
import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import businessLogic.SendEmail;
import domain.Client;
import domain.Owner;

public class ValidationGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textValidation;
	private JTextField txtType;
	private JTextField txtUsername;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JRadioButton rdbtnEmail = new JRadioButton("Email");
	private JRadioButton rdbtnTelegram = new JRadioButton("Telegram");
	private JLabel lblStatus = new JLabel("");

	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();

	
	/**
	 * Create the frame.
	 */
	public ValidationGUI(Owner o, Client c, String s) {

		rdbtnEmail.setEnabled(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnValidate = new JButton("Validate");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int validNumber = Integer.parseInt(textValidation.getText());

					if (s.equals("client")
							&& validNumber == c.getValidationNumber()) {
						lblStatus.setText("Validation sucesfull");
						facade.updateClinetValidation(c);
						ValidationMesage a = new ValidationMesage();
						dispose();
						a.setVisible(true);

					} else if (s.equals("owner")
							&& validNumber == o.getValidationNumber()) {
						lblStatus.setText("Validation sucesfull");
						facade.updateOwnerValidation(o);
						ValidationMesage a = new ValidationMesage();
						dispose();
						a.setVisible(true);
					}else {
						lblStatus.setText("Validation number was not correct");
					}
				} catch (NumberFormatException e1) {
					lblStatus.setText("Validation number was not correct");
				}

			}
		});
		btnValidate.setBounds(124, 240, 198, 35);
		contentPane.add(btnValidate);

		textValidation = new JTextField();
		textValidation.setBounds(124, 205, 198, 22);
		contentPane.add(textValidation);
		textValidation.setColumns(10);

		JButton btnSendValidatioNumber = new JButton("Send validatio number");
		btnSendValidatioNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int validNumber;
				String email;
				String idTelegram;
				String message;
				if (s.equals("client")) {
					validNumber = c.getValidationNumber();
					idTelegram = c.getTelegramId();
					email = c.getEmail();
				} else {
					validNumber = o.getValidationNumber();
					idTelegram = o.getTelegramId();
					email = o.getEmail();
				}
				message = "Welcome to the rural house app, first, you have to validate the account. Please introduce the following code in the app: \n"
						+ validNumber;

				if (rdbtnEmail.isSelected()) {
					SendEmail se = new SendEmail(email,
							"Validating your account", message);
				} else {
					Comand cmd = new Comand();
					Long id = Long.parseLong(idTelegram);
					cmd.sendMessageFromTelegramToClient(id, message);
				}
			}
		});
		btnSendValidatioNumber.setBounds(124, 130, 198, 35);
		contentPane.add(btnSendValidatioNumber);

		buttonGroup.add(rdbtnTelegram);
		rdbtnTelegram.setBounds(135, 9, 94, 25);
		contentPane.add(rdbtnTelegram);

		buttonGroup.add(rdbtnEmail);
		rdbtnEmail.setBounds(237, 9, 127, 25);
		contentPane.add(rdbtnEmail);

		JLabel lblType = new JLabel("Type");
		lblType.setBounds(27, 67, 56, 16);
		contentPane.add(lblType);

		txtType = new JTextField();
		txtType.setEditable(false);
		txtType.setBounds(67, 64, 70, 22);
		contentPane.add(txtType);
		txtType.setColumns(10);

		JLabel lblUser = new JLabel("Username");
		lblUser.setBounds(162, 67, 82, 16);
		contentPane.add(lblUser);

		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setColumns(10);
		txtUsername.setBounds(237, 64, 183, 22);
		contentPane.add(txtUsername);

		lblStatus.setBounds(124, 291, 198, 16);
		contentPane.add(lblStatus);

		if (s.equals("client")) {
			txtType.setText("Client");
			txtUsername.setText(c.getUserName());
		} else {
			txtType.setText("Owner");
			txtUsername.setText(o.getUserName());

		}

	}
}