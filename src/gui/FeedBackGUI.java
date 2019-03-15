package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

import domain.Reserva;
import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterfaceWS;

import javax.swing.JLabel;
import javax.swing.JTextPane;

import java.awt.SystemColor;
import java.awt.Color;

public class FeedBackGUI extends JFrame {

	private JPanel contentPane;
	private JTextPane textField;
	private int grade = 0;
	private JButton btnValidate = new JButton("Validate");
	private JButton button_5 = new JButton("");
	private JButton button_4 = new JButton("");
	private JButton button_3 = new JButton("");
	private JButton button_2 = new JButton("");
	private JButton button_1 = new JButton("");
	private ArrayList<JButton> listb = new ArrayList<JButton>();
	private JLabel lblFeedBackStatus = new JLabel("");

	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();



	/**
	 * Create the frame.
	 */
	public FeedBackGUI(Reserva r) {
		Border emptyBorder = BorderFactory.createEmptyBorder();
		button_1.setBorder(emptyBorder);
		button_2.setBackground(Color.WHITE);
		button_2.setBorder(emptyBorder);
		button_3.setBackground(Color.WHITE);
		button_3.setBorder(emptyBorder);
		button_4.setBackground(Color.WHITE);
		button_4.setBorder(emptyBorder);
		button_5.setBackground(Color.WHITE);
		button_5.setBorder(emptyBorder);
		listb.add(button_1);
		listb.add(button_2);
		listb.add(button_3);
		listb.add(button_4);
		listb.add(button_5);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 323);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.createFeedBack(textField.getText(), grade, r.getOffer().getRuralHouse());
				lblFeedBackStatus.setText("Thank you for your feedback");
				btnValidate.setEnabled(false);
				facade.setRated(r);
				
				
			}
		});
		btnValidate.setEnabled(true);
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.WHITE);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateStars(1);
			}
		});
		button_1.setBounds(78, 94, 50, 50);
		contentPane.add(button_1);
		button_1.setIcon(new ImageIcon("resources\\EsterllaBlanca"));

		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateStars(2);
			}
		});

		button_2.setBounds(140, 94, 50, 50);
		button_2.setIcon(new ImageIcon("resources\\EsterllaBlanca"));
		contentPane.add(button_2);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStars(3);

			}
		});

		button_3.setIcon(new ImageIcon("resources\\EsterllaBlanca"));
		button_3.setBounds(202, 94, 50, 50);
		contentPane.add(button_3);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStars(4);

			}
		});

		button_4.setIcon(new ImageIcon("resources\\EsterllaBlanca"));
		button_4.setBounds(264, 94, 50, 50);
		contentPane.add(button_4);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStars(5);

			}
		});

		button_5.setIcon(new ImageIcon("resources\\EsterllaBlanca"));
		button_5.setBounds(326, 94, 50, 50);
		contentPane.add(button_5);

		textField = new JTextPane();
		textField.setBackground(SystemColor.control);
		textField.setBounds(70, 164, 316, 71);
		contentPane.add(textField);

		btnValidate.setBounds(71, 13, 97, 25);
		contentPane.add(btnValidate);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MisReservasGUI a = new MisReservasGUI();
				a.setVisible(true);
			}
		});
		btnBack.setBounds(275, 13, 97, 25);
		contentPane.add(btnBack);
		
		lblFeedBackStatus.setBounds(70, 233, 111, 16);
		contentPane.add(lblFeedBackStatus);
	}
	public void updateStars(int i){
		grade=i;
		for(int j=0; j<i;j++){
			listb.get(j).setIcon(new ImageIcon("resources\\EstrellaAmarilla"));
		}
		for(int j=i;j<listb.size();j++){
			listb.get(j).setIcon(new ImageIcon("resources\\EsterllaBlanca"));

		}
		btnValidate.setEnabled(true);

	}
}
