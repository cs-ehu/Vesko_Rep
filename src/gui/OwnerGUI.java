package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import domain.Owner;
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

public class OwnerGUI extends JFrame {

	private JPanel contentPane;
	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();


	/**
	 * Create the frame.
	 */
	public OwnerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
		contentPane.add(panel);
		
		JRadioButton radioButton = new JRadioButton("Euskara");
		panel.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Castellano");
		panel.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("English");
		panel.add(radioButton_2);
		
		JButton button = new JButton();
		button.setBounds(0, 0, 0, 0);
		button.setText("Query Availability");
		contentPane.add(button);
		
		JLabel label = new JLabel("Select Option");
		label.setBounds(0, 0, 0, 0);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Select Option");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(0, 0, 477, 60);
		contentPane.add(label_1);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainGUI a = new MainGUI();
				a.owner=null;
				a.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogOut.setBounds(181, 368, 144, 49);
		contentPane.add(btnLogOut);
		
		JButton btnSetAvailiability = new JButton();
		btnSetAvailiability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainGUI main = new MainGUI();
				Owner owner = main.getMainOwner();
				ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
				ArrayList<RuralHouse>rhs = owner.getRhList();
				JFrame a = new SetAvailabilityGUI(rhs,owner);
				owner.toString();
				a.setVisible(true);
			}
		});
		btnSetAvailiability.setText("Set Availability");
		btnSetAvailiability.setBounds(0, 55, 477, 60);
		contentPane.add(btnSetAvailiability);
		
		JButton btnSetHouse = new JButton();
		btnSetHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame a = new SetHouse();
				a.setVisible(true);
			}
		});
		btnSetHouse.setText("Set House");
		btnSetHouse.setBounds(0, 116, 477, 60);
		contentPane.add(btnSetHouse);
		
		JButton btnModifyHouse = new JButton();
		btnModifyHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModifyHouseGUI a = new ModifyHouseGUI();
				dispose();
				a.setVisible(true);
			}
		});
		btnModifyHouse.setText("Modify House");
		btnModifyHouse.setBounds(0, 178, 477, 60);
		contentPane.add(btnModifyHouse);
		
		JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModifyAccountGUI a = new ModifyAccountGUI("Owner");
				dispose();
				a.setVisible(true);
			}
		});
		button_1.setText("Modify Account ");
		button_1.setBounds(0, 270, 477, 60);
		contentPane.add(button_1);
	}
}
