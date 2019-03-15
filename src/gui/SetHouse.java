package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import dataAccess.DataAccess;
import domain.Owner;
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.SwingConstants;

public class SetHouse extends JFrame {

	private JPanel contentPane;
	private JTextField textCity;
	private JTextField textDescription;
	private JButton btnBack;
	private JButton btnNewButton;

	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();
	private JLabel lblMessage;
	private JLabel lblRoomNumber;
	private JLabel lblAddress;
	private JTextField textRoomNumber;
	private JTextField textAddress;

	/**
	 * Create the frame.
	 */
	public SetHouse() {
		setTitle("SetHouse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCity.setBounds(33, 40, 56, 16);
		contentPane.add(lblCity);

		textCity = new JTextField();
		textCity.setBounds(132, 39, 277, 22);
		contentPane.add(textCity);
		textCity.setColumns(10);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(33, 73, 78, 16);
		contentPane.add(lblDescription);

		textDescription = new JTextField();
		textDescription.setColumns(10);
		textDescription.setBounds(132, 72, 277, 22);
		contentPane.add(textDescription);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame a = new OwnerGUI();
				a.setVisible(true);
			}
		});
		btnBack.setBounds(132, 185, 97, 25);
		contentPane.add(btnBack);

		btnNewButton = new JButton("Register House");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI main = new MainGUI();
				Owner owner = main.getMainOwner();
				
				if(textCity.getText().equals("") || textAddress.getText().equals("")||textRoomNumber.getText().equals("")){
					lblMessage.setForeground(Color.RED);
					lblMessage.setText("Fill all the required fields");
				}
				else{
				RuralHouse rh = facade.createRuralHouse(owner,
						textCity.getText(), textDescription.getText(),
						textAddress.getText(),  textRoomNumber.getText());
				lblMessage.setForeground(Color.GREEN);
				lblMessage.setText("Registered Succesfully " + rh.getHouseNumber());
				main.getMainOwner().addRhList(rh);}
			}

		});
		btnNewButton.setBounds(245, 185, 162, 25);
		contentPane.add(btnNewButton);

		lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(245, 215, 164, 16);
		contentPane.add(lblMessage);

		lblRoomNumber = new JLabel("Room number");
		lblRoomNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRoomNumber.setBounds(33, 104, 102, 16);
		contentPane.add(lblRoomNumber);

		lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(33, 135, 78, 16);
		contentPane.add(lblAddress);

		textRoomNumber = new JTextField();
		textRoomNumber.setColumns(10);
		textRoomNumber.setBounds(132, 103, 277, 22);
		contentPane.add(textRoomNumber);

		textAddress = new JTextField();
		textAddress.setColumns(10);
		textAddress.setBounds(132, 136, 277, 22);
		contentPane.add(textAddress);

		
		
		JLabel label = new JLabel("*");
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
		label.setBounds(412, 45, 20, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_1.setBounds(412, 109, 20, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_2.setBounds(412, 142, 20, 16);
		contentPane.add(label_2);
		
		JLabel lblRequiredFields = new JLabel("* Required fields");
		lblRequiredFields.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRequiredFields.setBounds(316, 13, 108, 16);
		contentPane.add(lblRequiredFields);
	}

	public Vector<String> getTags(String text) {
		Vector<String> v = new Vector<String>();
		String[] s = text.split(", ");
		for (int i = 0; i < s.length; i++) {
			v.add(s[i]);
		}
		return v;
	}
}