package gui;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import businessLogic.FilterSearch;
import businessLogic.GoogleMapsWeb;
import businessLogic.SendBooking;
import businessLogic.SendEmail;

import com.toedter.calendar.JCalendar;

import domain.Offer;
import domain.Reserva;
import domain.ReservaOfferRhOwnerContainer;
import domain.RuralHouse;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import org.telegram.telegrambots.api.methods.send.SendMessage;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class QueryAvailabilityClientAdvacedGUI extends JFrame {
private static final long serialVersionUID = 1L;

  private JLabel jLabel1 = new JLabel();
  private JButton btnEnableFirstDay = new JButton();
  private JTextField jTextField2 = new JTextField();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  
  // Code for JCalendar
  private JCalendar jCalendar1 = new JCalendar();
  private JCalendar calendar = new JCalendar();

  private Calendar calendarMio = null;
  private JScrollPane scrollPane = new JScrollPane();
  private JComboBox<RuralHouse> comboBoxRH ;
  private JTable table;
  private DefaultTableModel tableModel;
  private final JLabel labelNoOffers = new JLabel("");
  private String[] columnNames = new String[] {
		  ResourceBundle.getBundle("Etiquetas").getString("OfferN"), 
		  ResourceBundle.getBundle("Etiquetas").getString("RuralHouse"), 
		  ResourceBundle.getBundle("Etiquetas").getString("FirstDay"), 
		  ResourceBundle.getBundle("Etiquetas").getString("LastDay"), 
		  ResourceBundle.getBundle("Etiquetas").getString("Price") 
  	};
  private final JLabel lblCity = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.lblCity.text")); //$NON-NLS-1$ //$NON-NLS-2$
  private final JButton btnSearch = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.btnSearch.text")); //$NON-NLS-1$ //$NON-NLS-2$
  private final JRadioButton rdbtnRestrictRuralHouse = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.rdbtnRestrictRuralHouse.text")); //$NON-NLS-1$ //$NON-NLS-2$
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private JRadioButton rdbtnCity = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.radioButton.arg0")); //$NON-NLS-1$ //$NON-NLS-2$
  private JRadioButton rdbtnRuralHouse = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.rdbtnNewRadioButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private ApplicationFacadeInterfaceWS facade = new MainGUI().getBusinessLogic();
  private FilterSearch filter = new FilterSearch();
  private JTextField txtNumberNights;
  private JList<Offer> list = new JList<Offer>();
  private Vector<RuralHouse> listRh = facade.getAllRuralHouses();
  private DefaultListModel<Offer> model = new DefaultListModel<Offer>();
  private JComboBox comboBoxC;
  private DefaultComboBoxModel<String> combomodelc;
  private DefaultComboBoxModel<RuralHouse> combomodelrh;
  private final JButton btnEnableLastDay = new JButton();
  private JTextPane textPane = new JTextPane();
  private final JButton btnSeePhotos = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
  private int maxPrice=-1;
  private final JLabel lblPriceSelected = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.label.text")); //$NON-NLS-1$ //$NON-NLS-2$
  private int current  = maxPrice;
  private JButton btnSeeAddres = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.btnSeeAddres.text")); //$NON-NLS-1$ //$NON-NLS-2$
  private final JButton btnFeedBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.btnFeedBack.text")); //$NON-NLS-1$ //$NON-NLS-2$

 // private static configuration.ConfigXML c;
	
  public QueryAvailabilityClientAdvacedGUI()
  {
  	addWindowListener(new WindowAdapter() {
  		@Override
  		public void windowClosing(WindowEvent arg0) {
  			dispose();
  			System.exit(1);
  		}
  	});
  	
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception
  {
	ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
		
	Vector<RuralHouse> rhs=facade.getAllRuralHouses();
	combomodelrh = new DefaultComboBoxModel<RuralHouse>();
	combomodelc= new DefaultComboBoxModel<String>();
	for(RuralHouse rh : rhs){
		combomodelrh.addElement(rh);
		
	}
	comboBoxRH = new JComboBox<RuralHouse>(combomodelrh);
	Vector<String>listc = filter.getAllCity(rhs);
	for(String s : listc){
		combomodelc.addElement(s);
		
	}
	comboBoxC = new JComboBox<String>(combomodelc);
	
	comboBoxC.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String city =(String) comboBoxC.getSelectedItem();
			System.out.println("SELECTED CITY: "+ city);
			if(city==null)city="ALL";
			listRh=facade.getAllRuralHouses();
			Vector<RuralHouse> rhouselist = filter.filterByCity(city, listRh);
			combomodelrh.removeAllElements();
			for(RuralHouse rh : rhouselist){
				combomodelrh.addElement(rh);
			}
			comboBoxRH.setModel(combomodelrh);
		}
	});	
	comboBoxRH.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent arg0) {
			listRh = new Vector<RuralHouse>();
			listRh.addElement((RuralHouse)comboBoxRH.getSelectedItem());
		}
	});
	//

	
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(672, 1021));
    this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailability"));
    jLabel1.setText(ResourceBundle.getBundle("Etiquetas").getString("RuralHouse"));
    jLabel1.setBounds(new Rectangle(301, 106, 116, 25));
    btnEnableFirstDay.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		if(btnEnableFirstDay.getText().equals("Enable First Day")){
    		jCalendar1.setEnabled(true);
    		btnEnableFirstDay.setText("Disable First Day");}
    		else{
    			jCalendar1.setEnabled(false);
        		btnEnableFirstDay.setText("Enable First Day");
    		}
    	}
    });
	btnFeedBack.setEnabled(false);
    btnEnableFirstDay.setFont(new Font("Tahoma", Font.PLAIN, 10));
    btnEnableFirstDay.setText(ResourceBundle.getBundle("Etiquetas").getString("FirstDay")); //$NON-NLS-1$ //$NON-NLS-2$
    btnEnableFirstDay.setText("Enable First Day");
    btnEnableFirstDay.setBounds(new Rectangle(79, 350, 225, 25));
    jTextField2.setBounds(new Rectangle(79, 532, 225, 25));
    jTextField2.setEditable(false);
    jButton1.setText(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
    jButton1.setBounds(new Rectangle(77, 880, 493, 43));
    jButton1.setEnabled(false);
    jButton1.setText("Book offer");
    jButton1.addActionListener(new ActionListener()
    	      {
    	        public void actionPerformed(ActionEvent e)
    	        {
    	          jButton1_actionPerformed(e);
    	        }
    	      });
    jButton2.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
    jButton2.setBounds(new Rectangle(330, 23, 149, 43));
    jButton2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton2_actionPerformed(e);
        }
      });
    jCalendar1.setBounds(new Rectangle(79, 381, 225, 150));
    jCalendar1.setEnabled(false);
    calendar.setEnabled(false);
    scrollPane.setBounds(new Rectangle(77, 651, 493, 30));
    
    this.getContentPane().add(scrollPane, null);
    
    table = new JTable();
    table.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		//int i=table.getSelectedRow();
    		//int houseNumber = (int) tableModel.getValueAt(i,1);
       		//Date firstDate=new Date(((java.util.Date)tableModel.getValueAt(i,2)).getTime());
       		//Date lastDate=new Date(((java.util.Date)tableModel.getValueAt(i,3)).getTime());
	
			//BookRuralHouseGUI b=new BookRuralHouseGUI(houseNumber,firstDate,lastDate);
			//b.setVisible(true);
       		}
    });

    scrollPane.setViewportView(table);
    tableModel = new DefaultTableModel(
    			null,
            	columnNames);
        	
    table.setModel(new DefaultTableModel(
    	new Object[][] {
    	},
    	new String[] {
    		"Offer#", "Rural House", "First Day", "Last Day", "Price"
    	}
    ));
    table.getColumnModel().getColumn(0).setPreferredWidth(50);
    table.getColumnModel().getColumn(1).setPreferredWidth(100);
    table.getColumnModel().getColumn(2).setPreferredWidth(50);
    table.getColumnModel().getColumn(3).setPreferredWidth(50);
    table.getColumnModel().getColumn(4).setPreferredWidth(50);
    this.getContentPane().add(jCalendar1, null);
    
    calendar.setBounds(new Rectangle(155, 58, 225, 150));
    calendar.setBounds(345, 381, 225, 150);
    getContentPane().add(calendar);
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jTextField2, null);
    this.getContentPane().add(btnEnableFirstDay, null);
    this.getContentPane().add(jLabel1, null);
    comboBoxRH.setBounds(new Rectangle(245, 22, 115, 20));
    comboBoxRH.setBounds(384, 106, 186, 25);
    
    getContentPane().add(comboBoxRH);
    labelNoOffers.setBounds(204, 624, 265, 14);
    
    getContentPane().add(labelNoOffers);
    lblCity.setBounds(301, 136, 56, 16);
    
    getContentPane().add(lblCity);
    btnSearch.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			btnFeedBack.setEnabled(true);
			jButton1.setEnabled(true);
    		Vector<Offer> listO = null;
    		model.clear();
    		list.setModel(model);
    		listRh=facade.getAllRuralHouses();
    		if (txtNumberNights.getText().compareTo("")==0){
    			listO = filter.filterOfferByNights("none",listRh);
    		}else{
    			String s = txtNumberNights.getText();
    			listO = filter.filterOfferByNights(s,listRh);
    		}
    		Vector<Offer> listO2 = null;
    		if (btnEnableFirstDay.getText().equals("Disable First Day")){	//---> it is enabled
    	 		Date firstDay=trim(new Date(jCalendar1.getCalendar().getTime().getTime()));
    			listO2=filter.filterOfferByFirstDay(firstDay, listO);
    		}else{
    			System.out.println("ELSE");
    			listO2=listO;
    		}
    		Vector<Offer> listO3 = null;
    		if (btnEnableLastDay.getText().equals("Disable Last Day")){	//---> it is enabled
    	 		Date lastDay=trim(new Date(calendar.getCalendar().getTime().getTime()));
    			listO3=filter.filterOfferByLastDay(lastDay, listO2);
    		}else{
    			System.out.println("ELSE 2");
    			listO3=listO2;
    		}
    		Vector<Offer> listO4 = null;
    			if (rdbtnCity.isSelected()){
    			String city = (String)comboBoxC.getSelectedItem();
    				listO4=filter.filterOfferByCity(city,listO3);
    			}else if (rdbtnRestrictRuralHouse.isSelected()||rdbtnRuralHouse.isSelected()){
    				RuralHouse rh =(RuralHouse) comboBoxRH.getSelectedItem();
    				listO4= filter.filterOfferByRh(rh,listO3);
    			}
        	Vector<Offer> listO5 = null;
        	listO5 = filter.filterOfferByPrice(listO4, current);

    		
    		
    		try{
			tableModel.setDataVector(null, columnNames);
			if (listO5.isEmpty())
				labelNoOffers.setText(ResourceBundle.getBundle("Etiquetas").getString("NoOffers"));
			else {
				labelNoOffers.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOffer"));
				for(Offer of : listO5){
					System.out.println("Offer retrieved: "+of.toString2());
					model.addElement(of);
				}
				list.setModel(model);
			}
		
       } catch (Exception e1) {

    	   labelNoOffers.setText(e1.getMessage());
	}
    	}
    });
    btnSearch.setBounds(79, 570, 491, 43);
    
    getContentPane().add(btnSearch);
    
    JButton btnHelp = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.btnHelp.text")); //$NON-NLS-1$ //$NON-NLS-2$
    btnHelp.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		ClientAdvancedSearchHelp a = new ClientAdvancedSearchHelp();
    		a.setVisible(true);
  
    	}
    });
    btnHelp.setBounds(146, 23, 171, 43);
    getContentPane().add(btnHelp);
    buttonGroup.add(rdbtnRestrictRuralHouse);
    rdbtnRestrictRuralHouse.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	    btnSearch.setEnabled(true);
    		comboBoxC.setEnabled(true);
    		comboBoxRH.setEnabled(true);
    	}
    });
    rdbtnRestrictRuralHouse.setBounds(77, 86, 209, 28);
    
    getContentPane().add(rdbtnRestrictRuralHouse);
    
    rdbtnRuralHouse.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	    btnSearch.setEnabled(true);
    		comboBoxC.setEnabled(false);
    		comboBoxRH.setEnabled(true);
    		combomodelrh.removeAllElements();
    		listRh=facade.getAllRuralHouses();
    		for(RuralHouse rh : listRh){
    			combomodelrh.addElement(rh);

    		}
    		comboBoxRH.setModel(combomodelrh);
    	}
    });
    buttonGroup.add(rdbtnRuralHouse);
    rdbtnRuralHouse.setBounds(77, 119, 209, 25);
    getContentPane().add(rdbtnRuralHouse);
    
    rdbtnCity.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	    btnSearch.setEnabled(true);
    		comboBoxRH.setEnabled(false);
    		comboBoxC.setEnabled(true);    		
    		listRh=facade.getAllRuralHouses();
    		Vector<String>listc = filter.getAllCity(listRh);
    		combomodelc.removeAllElements();
    		for(String s : listc){
    			combomodelc.addElement(s);
    		}
    		comboBoxC.setModel(combomodelc);
    	}
    });
    buttonGroup.add(rdbtnCity);
    rdbtnCity.setBounds(77, 147, 127, 25);
    getContentPane().add(rdbtnCity);
    comboBoxRH.setEnabled(false);
    btnSearch.setEnabled(false);
    
    txtNumberNights = new JTextField();
    txtNumberNights.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.txtNumberNights.text")); //$NON-NLS-1$ //$NON-NLS-2$
    txtNumberNights.setColumns(10);
    txtNumberNights.setBounds(204, 278, 225, 30);
    txtNumberNights.setText("");
    getContentPane().add(txtNumberNights);
    
    JLabel lblNumberNights = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.lblNumberNights.text")); //$NON-NLS-1$ //$NON-NLS-2$
    lblNumberNights.setBounds(77, 285, 116, 16);
    getContentPane().add(lblNumberNights);
    list.addListSelectionListener(new ListSelectionListener() {
    	public void valueChanged(ListSelectionEvent arg0) {
    		if(!list.isSelectionEmpty()){
    			btnSeeAddres.setEnabled(true);
    		}
    	}
    });
    
    list.setBackground(SystemColor.controlHighlight);
    list.setBounds(79, 681, 491, 132);
    getContentPane().add(list);
    
    comboBoxC.setEnabled(false);
    comboBoxC.setBounds(new Rectangle(245, 22, 115, 20));
    comboBoxC.setBounds(384, 135, 186, 25);
    getContentPane().add(comboBoxC);
    btnEnableLastDay.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if(btnEnableLastDay.getText().equals("Enable Last Day")){
        		calendar.setEnabled(true);
        		btnEnableLastDay.setText("Disable Last Day");
        	}else{
        		calendar.setEnabled(false);
        		btnEnableLastDay.setText("Enable Last Day");
        		}
    	}
    });
    btnEnableLastDay.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.btnEnableLastDay.text")); //$NON-NLS-1$ //$NON-NLS-2$
    btnEnableLastDay.setFont(new Font("Tahoma", Font.PLAIN, 10));
    btnEnableLastDay.setBounds(new Rectangle(40, 181, 108, 25));
    btnEnableLastDay.setBounds(345, 350, 225, 25);
    
    getContentPane().add(btnEnableLastDay);
    textPane.setFont(new Font("Tahoma", Font.BOLD, 13));
    
    textPane.setEditable(false);
    textPane.setBackground(SystemColor.control);
    textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.textPane.text")); //$NON-NLS-1$ //$NON-NLS-2$
    textPane.setText(" ");
    textPane.setBounds(247, 939, 164, 22);
    getContentPane().add(textPane);
    btnSeePhotos.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		Offer o = list.getSelectedValue();
    		if (o!=null){
    			DisplayImagesGUI a = new DisplayImagesGUI(o.getRuralHouse(),0);
    			dispose();
				a.setVisible(true);
    		}
    	}
    });
    btnSeePhotos.setBounds(77, 837, 164, 30);
    
    getContentPane().add(btnSeePhotos);
    
    JSlider slider = new JSlider();
    maxPrice= filter.getMaxPrice(listRh);
    current=maxPrice;
    slider.setMaximum(maxPrice);
    slider.setMinimum(0);
    slider.setValue(maxPrice);
	lblPriceSelected.setText(new StringBuilder().append(maxPrice).toString());
    slider.addChangeListener(new ChangeListener() {
    	public void stateChanged(ChangeEvent arg0) {
    		current = slider.getValue();
    		lblPriceSelected.setText(new StringBuilder().append(current).toString());

    	}
    });
    slider.setBounds(204, 246, 225, 26);
    getContentPane().add(slider);
    
    JLabel lblPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailabilityClientAdvacedGUI.lblPrice.text")); //$NON-NLS-1$ //$NON-NLS-2$
    lblPrice.setBounds(77, 246, 56, 16);
    getContentPane().add(lblPrice);
    lblPriceSelected.setBounds(287, 233, 56, 16);
    
    getContentPane().add(lblPriceSelected);
    btnSeeAddres.setEnabled(false);
    btnSeeAddres.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		GoogleMapsWeb googleMapsWeb = new GoogleMapsWeb();
    		googleMapsWeb.browseAddress(list.getSelectedValue().getRuralHouse().getAddress());
    	}
		});
		btnSeeAddres.setBounds(421, 837, 149, 30);
		getContentPane().add(btnSeeAddres);
		btnFeedBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RuralHouse rh11 = list.getSelectedValue().getRuralHouse();
				if (!list.getSelectedValue().getRuralHouse().getListaFeedBack().isEmpty()) {
					dispose();
					DisplayRatingsGUI a = new DisplayRatingsGUI(list
							.getSelectedValue().getRuralHouse());
					a.setVisible(true);
				} else {
					System.out.println("OK");
					textPane.setText("There are no ratings for this rural house");
				}
			}
		});
		btnFeedBack.setBounds(247, 837, 164, 30);
    
    getContentPane().add(btnFeedBack);
    
    // Codigo para el JCalendar
    this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent propertychangeevent)
      {
        if (propertychangeevent.getPropertyName().equals("locale"))
        {
          jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
          DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField2.setText(dateformat.format(calendarMio.getTime()));
        }
        else if (propertychangeevent.getPropertyName().equals("calendar"))
        {
          calendarMio = (Calendar) propertychangeevent.getNewValue();
          DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField2.setText(dateformat1.format(calendarMio.getTime()));
          jCalendar1.setCalendar(calendarMio);
        }
      } 
    });
    

  }

  private void jButton2_actionPerformed(ActionEvent e)
  {
	  dispose();
	  ClientMainGUI a = new ClientMainGUI();
	  a.setVisible(true);
  }
 private Date trim(Date date) {

     Calendar calendar = Calendar.getInstance();
     calendar.setTime(date);
     calendar.set(Calendar.MILLISECOND, 0);
     calendar.set(Calendar.SECOND, 0);
     calendar.set(Calendar.MINUTE, 0);
     calendar.set(Calendar.HOUR_OF_DAY, 0);
     return calendar.getTime();
 }

 private void jButton1_actionPerformed(ActionEvent e)
 {	
	 Offer o = list.getSelectedValue();
	 if (o==null){
		 textPane.setForeground(new Color(220, 20, 60));
		 textPane.setText("No selected offer");
	 }else{
	 MainGUI a  = new MainGUI();
     Calendar calendar = Calendar.getInstance();
     calendar.set(Calendar.MILLISECOND, 0);
     calendar.set(Calendar.SECOND, 0);
     calendar.set(Calendar.MINUTE, 0);
     calendar.set(Calendar.HOUR_OF_DAY, 0);
     Date d=calendar.getTime();
     Reserva r = facade.createReserva(o, a.client, d, "Booked");
	 textPane.setForeground(new Color(34, 139, 34));
	 ReservaOfferRhOwnerContainer roc = facade.getReservaOfferRhOwnerContainer(r);

			if (a.client.isSendBookByTelegram()) {
				SendBooking sendb = new SendBooking();
				sendb.sendBookToTelegram(a.client,null, roc.getReserva(),"Client");
			}
			if (a.client.isSendBookByEmail()) {
				StringBuilder sb = new StringBuilder();
				sb.append("Acabas de realizar la siguiente reserva : \n");
				sb.append(roc.getReserva().toString2() + "\n");
				sb.append("Gracias por utilizar nuestra aplicación\n"	);
				SendEmail sendEmail = new SendEmail(a.client.getEmail(),
						"New Booking", sb.toString());
				System.out.println(a.client.getEmail());
			}
			if(roc.getOwner().isSendBookByTelegram()){
			SendBooking sendb = new SendBooking();
			sendb.sendBookToTelegram(null,roc.getOwner(), roc.getReserva(),"Owner");
			}
			System.out.println(roc.getOwner().getEmail());
			StringBuilder sb = new StringBuilder();
			sb.append("Estimado " + roc.getOwner().getUserName() +", el cliente: \n");
			sb.append("Usuario: " + a.client.getUserName()+"\n");
			sb.append("Email: " + a.client.getEmail()+"\n");
			sb.append("Acaba de realizar la siguiente reserva: \n");
			sb.append(roc.getReserva().toString2() + "\n");
			SendEmail sendEmail = new SendEmail(roc.getOwner().getEmail(),
					"New Booking", sb.toString());
	 textPane.setText("Offer successfully booked");
	 model.removeElement(o);}
 }
}