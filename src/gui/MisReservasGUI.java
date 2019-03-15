package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import java.awt.SystemColor;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import domain.Client;
import domain.Offer;
import domain.Reserva;
import domain.ReservaOfferRhOwnerContainer;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.Color;

public class MisReservasGUI extends JFrame {

	private JPanel contentPane;
	private JList list = new JList();
	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();
	private DefaultListModel<ReservaOfferRhOwnerContainer> model = new DefaultListModel<ReservaOfferRhOwnerContainer>();
	private JTextField textField;
	private JButton btnRemoveOffer = new JButton("Remove Booking");
	private JButton btnNewButton = new JButton("See Photo");
	private final JButton btnFeedBack = new JButton("Rate Rural House");


	/**
	 * Create the frame.
	 */
	public MisReservasGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 0));
		textField.setEditable(false);
		textField.setBounds(12, 355, 360, 22);
		contentPane.add(textField);
		textField.setColumns(10);		
		list.setBackground(SystemColor.controlHighlight);
		list.setBounds(12, 46, 607, 240);
		contentPane.add(list);
		MainGUI a = new MainGUI();
		Client c = facade.getClientByUsername(a.client.getUserName());
		for (ReservaOfferRhOwnerContainer rohoc : facade.getListReservaOfferRhOwnerContainer(c)){
			model.addElement(rohoc);
		}
		if (model.isEmpty()){
			textField.setForeground(new Color(0, 0, 0));
			textField.setText("There are no booked offers");
		    btnRemoveOffer.setEnabled(false);
			}
		list.setModel(model);
		
		
		btnRemoveOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReservaOfferRhOwnerContainer r = (ReservaOfferRhOwnerContainer) list.getSelectedValue();
				if (r==null){
					textField.setForeground(new Color(220, 20, 60));
					textField.setText("No booking selected");
				}else{
				model.removeElement(r);
				facade.removeReserva(r.getReserva());
				textField.setForeground(new Color(34, 139, 34));
				textField.setText("Removed Successfully");
				if (model.isEmpty()){
				textField.setForeground(new Color(0, 0, 0));
				textField.setText("There are no booked offers");
			    btnRemoveOffer.setEnabled(false);
				}
			}
				}
			
		});
		btnRemoveOffer.setBounds(12, 299, 141, 34);
		contentPane.add(btnRemoveOffer);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ClientMainGUI a = new ClientMainGUI();
				a.setVisible(true);
			}
		});
		btnBack.setBounds(384, 346, 235, 34);
		contentPane.add(btnBack);
		
		JLabel lblMyBookinngs = new JLabel("My Bookings");
		lblMyBookinngs.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMyBookinngs.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyBookinngs.setBounds(98, 13, 408, 28);
		contentPane.add(lblMyBookinngs);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ReservaOfferRhOwnerContainer r = (ReservaOfferRhOwnerContainer)list.getSelectedValue();
				Offer o = r.getOffer();
	    		if (o!=null){
	    			DisplayImagesGUI a = new DisplayImagesGUI(o.getRuralHouse(),1);
	    			dispose();
					a.setVisible(true);
	    		}
	    		}catch(NullPointerException e1){
	    			textField.setText("Select your Booking first");
	    		}

			}
			
		});
		btnNewButton.setBounds(165, 299, 151, 34);
		contentPane.add(btnNewButton);
		btnFeedBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReservaOfferRhOwnerContainer r = (ReservaOfferRhOwnerContainer)list.getSelectedValue();
				if(!r.getReserva().isRated()){
				dispose();
				FeedBackGUI a = new FeedBackGUI(r.getReserva());
				a.setVisible(true);}
				else{
					textField.setText("You aready rated this Rural House");
				}
			}
		});
		btnFeedBack.setBounds(333, 299, 286, 34);
		
		contentPane.add(btnFeedBack);
		
		
	}
}