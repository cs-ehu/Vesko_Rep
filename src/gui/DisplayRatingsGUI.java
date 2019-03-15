package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;

import domain.FeedBack;
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DisplayRatingsGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblCurrent = new JLabel("0");
	private JLabel lblRate = new JLabel("");
	private JTextPane textPane = new JTextPane();
	private JLabel lblMax = new JLabel("5");
	private JLabel lblStatus = new JLabel("");
	private int current=0;
	private int max;



	/**
	 * Create the frame.
	 */
	public DisplayRatingsGUI(RuralHouse rh) {
		max=rh.getListaFeedBack().size();
		lblMax.setText(new StringBuilder().append(max).toString());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rate");
		lblNewLabel.setBounds(12, 13, 79, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblComent = new JLabel("Comment");
		lblComent.setBounds(12, 65, 56, 16);
		contentPane.add(lblComent);
		textPane.setEditable(false);
		
		textPane.setBounds(12, 92, 408, 86);
		contentPane.add(textPane);
		
		lblRate.setBounds(50, 13, 56, 16);
		contentPane.add(lblRate);
		
		JButton btnPrev = new JButton("Previous");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FeedBack fb = rh.getListaFeedBack().get(current);
				current--;
				textPane.setText(fb.getFeedBack());
				lblRate.setText(new StringBuilder().append(fb.getGrade()).toString());
				lblCurrent.setText(new StringBuilder().append(current).toString());
				if(current==0){
					current=max;
				}
			}
		});
		btnPrev.setBounds(12, 215, 97, 32);
		contentPane.add(btnPrev);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FeedBack fb = rh.getListaFeedBack().get(current);
				current++;
				textPane.setText(fb.getFeedBack());
				lblRate.setText(new StringBuilder().append(fb.getGrade()).toString());
				lblCurrent.setText(new StringBuilder().append(current).toString());
				if(current==max){
					current=0;
				}
			}
		});
		btnNext.setBounds(121, 215, 97, 32);
		contentPane.add(btnNext);
		
		JLabel label = new JLabel("/");
		label.setBounds(364, 13, 56, 16);
		contentPane.add(label);
		
		lblCurrent.setBounds(347, 13, 56, 16);
		contentPane.add(lblCurrent);
		
		lblMax.setBounds(376, 13, 56, 16);
		contentPane.add(lblMax);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				QueryAvailabilityClientAdvacedGUI a = new QueryAvailabilityClientAdvacedGUI();
				a.setVisible(true);
			}
		});
		button.setBounds(323, 215, 97, 32);
		contentPane.add(button);
		
		lblStatus.setBounds(12, 263, 408, 16);
		contentPane.add(lblStatus);
	}
}
