package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import domain.Owner;
import domain.RuralHouse;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class DisplayImagesGUI extends JFrame {

	private JPanel contentPane;

	private int maxPhotos;
	private int actualphoto=0;
	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();
	private JLabel lblActPhoto = new JLabel("");

	/**
	 * Create the frame.
	 * @param selectedRH 
	 */
	public DisplayImagesGUI(RuralHouse rh, int from) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImg = new JLabel("");
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setBounds(12, 13, 1458, 753);
		contentPane.add(lblImg);
		
		JPanel panel = new JPanel();
		panel.setBounds(443, 779, 596, 61);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNextImage = new JButton("Next Image");
		btnNextImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxPhotos=rh.getImages().size();
				if(maxPhotos==0){
					lblImg.setText("There are no images for this Rural House yet");
				}else{
				byte[] bytes = rh.getImages().get(actualphoto);
				lblActPhoto.setText("Photo: "+(actualphoto+1)+"/"+maxPhotos);

					if (actualphoto<maxPhotos-1){
						actualphoto++;
					}else{
						actualphoto=0;
					}
				try {
					BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytes));
					lblImg.setIcon(new ImageIcon(imag));

				} catch (IOException e1) {
				System.out.println("error");
				}
			}}
		});
		btnNextImage.setBounds(314, 0, 167, 61);
		panel.add(btnNextImage);
		
		JButton btnPreviousImage = new JButton("Previous Image");
		btnPreviousImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxPhotos=rh.getImages().size();
				if(maxPhotos==0){
					lblImg.setText("There are no images for this Rural House yet");
				}else{
				byte[] bytes = rh.getImages().get(actualphoto);
				lblActPhoto.setText("Photo: "+(actualphoto+1)+"/"+maxPhotos);
				System.out.println("valor " +actualphoto);
					if (0<actualphoto){
						actualphoto--;
					}else{
						actualphoto=maxPhotos-1;
					}
				try {
					BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytes));
					lblImg.setIcon(new ImageIcon(imag));

				} catch (IOException e1) {
				System.out.println("error");
				}
			}
			}
		});
		btnPreviousImage.setBounds(149, 0, 167, 61);
		panel.add(btnPreviousImage);
		
		lblActPhoto.setBounds(708, 762, 101, 16);
		contentPane.add(lblActPhoto);
		
		JButton btnNewButton = new JButton("Back"); 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (from==0){	//advancedGUI
					QueryAvailabilityClientAdvacedGUI a = new QueryAvailabilityClientAdvacedGUI();
					a.setVisible(true);
				}
				else if (from == 1){	//MisReservas
					MisReservasGUI a = new MisReservasGUI();
					a.setVisible(true);
				}
				else{			// ModifyHouse
					ModifyHouseGUI a = new ModifyHouseGUI();
					a.setVisible(true);
				}
				dispose();
			}
		});
		btnNewButton.setBounds(55, 779, 152, 61);
		contentPane.add(btnNewButton);
	}
}
