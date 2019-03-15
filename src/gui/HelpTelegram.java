package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;

public class HelpTelegram extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HelpTelegram dialog = new HelpTelegram();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HelpTelegram() {
		setTitle("Help");
		setBounds(100, 100, 515, 285);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextArea txtrG = new JTextArea();
		txtrG.setEditable(false);
		txtrG.setText("1. Go to telegram app\r\n2. Search the following contact: @userinfobot\r\n3. Send the following message to @userinfobot: /start\r\n4. @userinfobot will reply with your id.\r\n\r\n");
		txtrG.setBounds(12, 29, 473, 76);
		contentPanel.add(txtrG);
		{
			JTextArea txtrGoTo = new JTextArea();
			txtrGoTo.setEditable(false);
			txtrGoTo.setText("1. Go to telegram app\r\n2. Search the following contact: @Scrum_v2Bot\r\n3. Send the following message to @Scrum_v2Bot: /start\r\n4. Your account now is shyncronized ");
			txtrGoTo.setBounds(12, 152, 473, 76);
			contentPanel.add(txtrGoTo);
		}
		
		JLabel lblHowDoI = new JLabel("How do I shyncronize my account with telegram");
		lblHowDoI.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHowDoI.setBounds(12, 137, 497, 16);
		contentPanel.add(lblHowDoI);
		{
			JLabel lblNewLabel = new JLabel("How do i get my telegram id ?");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setBounds(12, 8, 352, 27);
			contentPanel.add(lblNewLabel);
		}
	}
}