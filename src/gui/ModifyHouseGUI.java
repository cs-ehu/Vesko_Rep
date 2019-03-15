package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.swing.JComboBox;

import domain.Owner;
import domain.RuralHouse;

public class ModifyHouseGUI extends JFrame {

	private JPanel contentPane;
	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();
	private Owner owner;
	protected RuralHouse selectedRH;
	
	private JButton btnUploadFoto = new JButton("Upload Photo");
	private JButton btnSeePhoto = new JButton("See Photo");
	private JButton btnDeleteRuralHouse = new JButton("Delete Rural House");	
	private DefaultComboBoxModel<RuralHouse> casas = new DefaultComboBoxModel<RuralHouse>();

	/**
	 * Create the frame.
	 */
	public ModifyHouseGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MainGUI a = new MainGUI();
        owner = a.getMainOwner();
        
        if(owner.getRhList().isEmpty()){
        	btnDeleteRuralHouse.setEnabled(false);
        	btnSeePhoto.setEnabled(false);
        	btnUploadFoto.setEnabled(false);
        	
        	
        }
          
		btnUploadFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  JFileChooser file = new JFileChooser();
		          file.setCurrentDirectory(new File(System.getProperty("user.home")));
		          //filter the files
		          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		          file.addChoosableFileFilter(filter);
		          int result = file.showSaveDialog(null);
		          if(result == JFileChooser.APPROVE_OPTION){
		              File selectedFile = file.getSelectedFile();
					try {
						ByteArrayOutputStream baos=new ByteArrayOutputStream(1000);
						BufferedImage img=ImageIO.read(selectedFile);
						ImageIO.write(img, "jpg", baos);
						baos.flush();
						String base64String=Base64.getEncoder().encodeToString(baos.toByteArray());
						baos.close();
						byte [] imgen = Base64.getDecoder().decode(base64String);
					    facade.addImageToRH(imgen, owner, selectedRH);
					    selectedRH.addImage(imgen);
						} catch (IOException e){System.out.println("ERROR I/O");}  
		          }


		          else if(result == JFileChooser.CANCEL_OPTION){
		              System.out.println("No File Select");
		          }
			}
		});
		
		btnUploadFoto.setBounds(110, 54, 282, 48);
		btnDeleteRuralHouse.setEnabled(false);
		btnSeePhoto.setEnabled(false);
		btnUploadFoto.setEnabled(false);
		contentPane.add(btnUploadFoto);
		for (RuralHouse rh : owner.getRhList()){
			casas.addElement(rh);
		}
		JComboBox<RuralHouse> comboBox = new JComboBox<RuralHouse>();
		comboBox.setToolTipText("");
		comboBox.setModel(casas);
		comboBox.setSelectedIndex(-1);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedRH = (RuralHouse) comboBox.getSelectedItem();
			 	System.out.println("ComboBox: "+selectedRH + selectedRH.getHouseNumber());
			 	if (selectedRH!=null){
			 		btnDeleteRuralHouse.setEnabled(true);
					btnSeePhoto.setEnabled(true);
					btnUploadFoto.setEnabled(true);
			 	}
			}
		});
		
		comboBox.setBounds(42, 13, 350, 28);
		contentPane.add(comboBox);
		
		btnSeePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRH=facade.getRuralHouse(selectedRH, owner);
				DisplayImagesGUI a =new DisplayImagesGUI(selectedRH,2);
				a.setVisible(true);
			}
		});
		btnSeePhoto.setBounds(110, 115, 282, 48);
		contentPane.add(btnSeePhoto);
		
		btnDeleteRuralHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DeleteRuralHouse a = new DeleteRuralHouse(selectedRH.getHouseNumber());
				 a.setVisible(true);
				 dispose();
			}
		});
		btnDeleteRuralHouse.setBounds(110, 192, 282, 48);
		contentPane.add(btnDeleteRuralHouse);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OwnerGUI o = new OwnerGUI();
				dispose();
				o.setVisible(true);
			}
		});
		btnBack.setBounds(12, 192, 78, 46);
		contentPane.add(btnBack);
	}
}
