package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JLabel;

import dataAccess.DataAccess;
import domain.Offer;
import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;

import javax.swing.SwingConstants;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteRuralHouse extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();
	private JLabel lblTheRuralHouse;

	private JButton okButton = new JButton("Yes");
	private JButton cancelButton = new JButton("No");

	/**
	 * Create the dialog.
	 */
	public DeleteRuralHouse(int num) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				ModifyHouseGUI m = new ModifyHouseGUI();
				dispose();
				m.setVisible(true);
			}
		});
		setBounds(100, 100, 450, 215);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JTextPane txtpnAttentionAreYou = new JTextPane();
		txtpnAttentionAreYou.setEditable(false);
		txtpnAttentionAreYou.setBackground(SystemColor.control);
		txtpnAttentionAreYou
				.setText("                                     Attention:\r\nAre you sure you want to remove the following Rural House?");
		txtpnAttentionAreYou.setBounds(41, 13, 353, 52);
		contentPanel.add(txtpnAttentionAreYou);

		RuralHouse rh = facade.getRuralHousebyHouseNumber(num);
		JLabel label = new JLabel(rh.toString());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 58, 410, 37);
		contentPanel.add(label);
		{
			lblTheRuralHouse = new JLabel("");
			lblTheRuralHouse.setHorizontalAlignment(SwingConstants.CENTER);
			lblTheRuralHouse.setBounds(10, 96, 410, 37);
			contentPanel.add(lblTheRuralHouse);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(checkRemove(rh)){
						facade.removeRuralHouse(rh.getHouseNumber());
						lblTheRuralHouse.setText("The Rural House has been removed, close this window");
						}
						else{
							lblTheRuralHouse.setText("The Rural House can not be removed,there are booked offers, close this window");
						}
						okButton.setEnabled(false);
						cancelButton.setEnabled(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lblTheRuralHouse
								.setText("The Rural House has not been removed, close this window");
						okButton.setEnabled(false);
						cancelButton.setEnabled(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean checkRemove(RuralHouse rh) {
		for (Offer o : rh.getOffers()) {
			if (o.isBooked()) {
				return false;
			}
		}
		return true;
	}
}
