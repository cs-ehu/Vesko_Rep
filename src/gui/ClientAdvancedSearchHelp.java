package gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ClientAdvancedSearchHelp extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClientAdvancedSearchHelp dialog = new ClientAdvancedSearchHelp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClientAdvancedSearchHelp() {
		setBounds(100, 100, 599, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane txtpnS = new JTextPane();
		txtpnS.setBackground(SystemColor.control);
		txtpnS.setEditable(false);
		txtpnS.setText("This is the advanced search, which allows you to customize your search, and adjust some parameters to meet your requirements. You can either set all the parameters or not. \r\n\r\nHere is a list with the different parameters you can set:\r\n- First day: All the available offers will be after this date.\r\n- Last day: All the available searched offers  will be before this date.\r\n- Number of nights: The available offers will have exactly the number of nights specified.\r\n- Tags: The available offers will have those tags in their rural house.\r\n- City: The rural house of the available offers will be in the city specified.\r\n- Rural House: The available offers will belong to the specified rural house.\r\n\r\nIf the button \"Rural house\" is selected, you won't be able to specify a city.\r\n\r\nIf the button \"City\" is selected, you will be only able to select a city but you won't be able to select a rural house.\r\n\r\nIf the button \"Restrict rural house to the selected city\" is selected, the list of rural houses displayed, will be restricted to the selected city. \r\n");
		txtpnS.setBounds(12, 37, 557, 293);
		contentPanel.add(txtpnS);
		
		JLabel lblHowToSearch = new JLabel("How to search an offer\r\n");
		lblHowToSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHowToSearch.setBounds(12, 13, 163, 16);
		contentPanel.add(lblHowToSearch);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
