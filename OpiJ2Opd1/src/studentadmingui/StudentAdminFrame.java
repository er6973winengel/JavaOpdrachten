package studentadmingui;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import javax.swing.JTextField;

import studentadmin.CPP;
import studentadmin.Leerling;
import studentadmin.Opleiding;
import studentadmin.StudentAdminException;
import studentadmin.StudentenAdmin;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class StudentAdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTextArea uitvoerGebied = null;
	private JButton toonAlleKnop = null;
	private JTabbedPane mijnTabbladenPanel = null;
	private JPanel voegStudenttoePanel = null;
	private JPanel voegScholertoePanel = null;
	private JPanel studentPanel = null;
	private JPanel alleStudentenPanel = null;
	private JLabel bestaandenaamLabel = null;
	private JTextField bestaandeNaamVeld = null;
	private JLabel infoLabel = null;
	private JTextField studentInfoVeld = null;
	private JLabel nieuwepuntenLabel = null;
	private JTextField puntenVeld = null;
	private JLabel uitlegLabel = null;
	private JButton moduleKnop = null;
	private JLabel opleidingLabel = null;
	private JLabel nstudentLabel = null;
	private JComboBox opleidingComboBox = null;
	private JLabel studentLabel = null;
	private JTextField studentTextField = null;
	private JButton studentButton = null;
	private JComboBox scholingComboBox = null;
	private JLabel scholerLabel = null;
	private JTextField scholerTextField = null;
	private JButton scholerButton = null;
	// eigen attributen
	StudentenAdmin administratie = new StudentenAdmin();
	/**
	 * This is the default constructor
	 * 
	 */
	public StudentAdminFrame() {
		super();
		initialize();
		mijnInit();
	}
	// eigen methoden
	/**
	 * Vullen comboboxen voor aangeboden Opleidingen en CPP's + afhandeling van de verschillende keuzes die in het scherm gemaakt
	 * kunnen worden
	 */
	private void mijnInit() {
		try {
			// ophalen lijst met Opleidingen en de namen van de Opleidingen in de combobox plaatsen
			ArrayList<Opleiding> opleidingLijst = administratie.geefOpleidingLijst();
			for (Opleiding opleiding : opleidingLijst){
				opleidingComboBox.addItem(opleiding.getNaam());
			}
			opleidingComboBox.setSelectedIndex(-1);
		} catch (StudentAdminException stdntAdminEx) {
			geefMelding(voegStudenttoePanel, stdntAdminEx.getMessage());
		}
		try {
			// ophalen lijst met CCPs en de namen van de Opleidingen in de combobox plaatsen
			ArrayList<CPP> CcpLijst = administratie.geefCppLijst();
			for (CPP cpp : CcpLijst){
				scholingComboBox.addItem(cpp.getNaam());
			}
			scholingComboBox.setSelectedIndex(-1); 
		} catch (StudentAdminException stdntAdminEx) {
			geefMelding(voegScholertoePanel, stdntAdminEx.getMessage());
		}
	}
/**
 * 	ieuwe student registreren
 */
	private void studentKnopAction(){
		geefMelding(voegStudenttoePanel, "");
		try {
			String naam = studentTextField.getText();
			administratie.maakStudent(naam , (String)opleidingComboBox.getSelectedItem());
			opleidingComboBox.setSelectedIndex(-1);
			studentTextField.setText("");
			geefMelding("student " + naam + " succesvol geregistreerd");  		
		} catch (StudentAdminException stdntAdminEx) {
			geefMelding(stdntAdminEx.getMessage());
		}
	}
	/**
	 * nieuwe scholer registreren
	 */
	private void scholerKnopAction(){
		geefMelding(voegScholertoePanel, "");
		try {
			String naam = scholerTextField.getText();
			administratie.maakScholer(naam , (String)scholingComboBox.getSelectedItem());
			scholingComboBox.setSelectedIndex(-1);
			scholerTextField.setText("");
			geefMelding("student " + naam + " succesvol geregistreerd");

		} catch (StudentAdminException stdntAdminEx){
			geefMelding(stdntAdminEx.getMessage());
		}
	}
	/**
	 *  zoek leerling met ingevoerde naam: als leerling bestaat toon de leerling informatie, geef melding als leerling niet bestaat
	 */
	private void bestaandeNaamVeldAction() {
		geefMelding(studentPanel, "");
		studentInfoVeld.setText("");
		try{
			Leerling leerling = administratie.geefLeerling(bestaandeNaamVeld.getText());
			studentInfoVeld.setText(leerling.toString());
		}
		catch (StudentAdminException stdntAdminEx){
			geefMelding(stdntAdminEx.getMessage());
			studentInfoVeld.setText("");
		}
	}
	/**
	 * aantal studiepunten van student verhogen
	 */
	private void puntenVeldAction() {
		geefMelding(studentPanel, "");
		Leerling leerling = null;
		try {
			// leerling opnieuw tonen omdat naam gewijzigd kan zijn
			// (+ nieuw leerling object tbv eventueel gebruik in catch nbrFrntEx)
			leerling = administratie.geefLeerling(bestaandeNaamVeld.getText());
			studentInfoVeld.setText(leerling.toString());
			// studiepunten verhogen
			administratie.verhoogStudiepunten(administratie.geefLeerling(bestaandeNaamVeld.getText()), Double.parseDouble(puntenVeld.getText()));
			// leerlinginformatie opnieuw ophalen en tonen na verhoging studiepunten
			studentInfoVeld.setText(administratie.geefLeerling(bestaandeNaamVeld.getText()).toString());
		} 
		catch (NumberFormatException nbrFrmtEx){
			// voor geven foutmelding eerst studentInfo nog een keer goed vullen
			studentInfoVeld.setText(leerling.toString());
			geefMelding("voor een geldig aantal studiepunten in");
		}
		catch (StudentAdminException stdntAdminEx){
			geefMelding(stdntAdminEx.getMessage());
		}
		puntenVeld.setText("");
	}
	/**
	 * aantal modules van scholer verhogen
	 */
	private void moduleKnopAction() {
		geefMelding(studentPanel, "");
		try {
			// leerling opnieuw tonen omdat naam gewijzigd kan zijn
			studentInfoVeld.setText(administratie.geefLeerling(bestaandeNaamVeld.getText()).toString());
			// verhoog modules
			administratie.verhoogModules(administratie.geefLeerling(bestaandeNaamVeld.getText()), 1);
			// leerlinginformatie opnieuw ophalen en tonen na verhoging modules
			studentInfoVeld.setText(administratie.geefLeerling(bestaandeNaamVeld.getText()).toString());
		} 
		catch (StudentAdminException stdntAdminEx){
			geefMelding(stdntAdminEx.getMessage());
		}
		puntenVeld.setText("");
	}
	/**
	 * alle leerlingen tonen, indien geen aanwezig: geef melding
	 */
	private void ToonAlleKnopAction(){
		uitvoerGebied.setText("");
		ArrayList<String> leerlingenInfoLijst = administratie.geefLeerlingenInfo();
		if (leerlingenInfoLijst.size() == 0){
			uitvoerGebied.setText("geen leeringen aanwezig");
		} else {
			for (String item : leerlingenInfoLijst){
				uitvoerGebied.append(item + "\n");
			}    	
		}
	}
	/**
	 * hulpmethode voor afdrukken fouttekst op juiste panel
	 * @param jpanel
	 * @param tekst
	 */
	private void geefMelding(JPanel jpanel, String tekst){
		jpanel.add(infoLabel);
		infoLabel.setText(tekst);
	}
	/**
	 * hulpmethode voor afdrukken fouttekst (als al op juiste panel geplaatst)
	 * @param tekst
	 */
	private void geefMelding(String tekst){
		infoLabel.setText(tekst);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(822, 519);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("StudentAdministratie");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getMijnTabbladenPanel(), null);
			infoLabel = new JLabel();
			infoLabel.setBackground(Color.ORANGE);
			infoLabel.setForeground(Color.BLUE);
			infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			infoLabel.setBounds(new Rectangle(14, 278, 523, 33));
			jContentPane.add(infoLabel, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(6, 65, 696, 174));
			jScrollPane.setViewportView(getUitvoerGebied());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes uitvoerGebied
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getUitvoerGebied() {
		if (uitvoerGebied == null) {
			uitvoerGebied = new JTextArea();
			uitvoerGebied.setFont(new Font("Courier New", Font.PLAIN, 21));
			uitvoerGebied.setEditable(false);
		}
		return uitvoerGebied;
	}

	/**
	 * This method initializes toonAlleKnop
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getToonAlleKnop() {
		if (toonAlleKnop == null) {
			toonAlleKnop = new JButton();
			toonAlleKnop.setFont(new Font("Tahoma", Font.PLAIN, 26));
			toonAlleKnop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ToonAlleKnopAction();
				}
			});
			toonAlleKnop.setText("Toon alle studenten");
			toonAlleKnop.setBounds(new Rectangle(11, 8, 266, 38));
		}
		return toonAlleKnop;
	}

	/**
	 * This method initializes mijnTabbladenPanel 
	 *    
	 * @return javax.swing.JTabbedPane    
	 */
	private JTabbedPane getMijnTabbladenPanel() {
		if (mijnTabbladenPanel == null) {
			mijnTabbladenPanel = new JTabbedPane();
			mijnTabbladenPanel.setFont(new Font("Tahoma", Font.PLAIN, 26));
			mijnTabbladenPanel.setBounds(new Rectangle(13, 19, 755, 400));
			mijnTabbladenPanel.addTab("nieuwe student", null,
					getVoegStudenttoePanel(), null);
			mijnTabbladenPanel.addTab("nieuwe scholer", null,
					getVoegScholertoePanel(), null);
			mijnTabbladenPanel.addTab("studentinfo", null, getStudentPanel(), null);
			mijnTabbladenPanel.addTab("alle studenten", null,
					getAlleStudentenPanel(), null);
		}
		return mijnTabbladenPanel;
	}

	/**
	 * This method initializes voegStudenttoePanel        
	 *    
	 * @return javax.swing.JPanel 
	 */
	private JPanel getVoegStudenttoePanel() {
		if (voegStudenttoePanel == null) {
			studentLabel = new JLabel();
			studentLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
			studentLabel.setBounds(new Rectangle(16, 80, 190, 24));
			studentLabel.setText("Naam student");
			nstudentLabel = new JLabel();
			nstudentLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
			nstudentLabel.setBounds(new Rectangle(16, 23, 286, 31));
			nstudentLabel.setText("Selecteer een opleiding");
			voegStudenttoePanel = new JPanel();
			voegStudenttoePanel.setLayout(null);
			voegStudenttoePanel.add(nstudentLabel, null);
			voegStudenttoePanel.add(getOpleidingComboBox(), null);
			voegStudenttoePanel.add(studentLabel, null);
			voegStudenttoePanel.add(getStudentTextField(), null);
			voegStudenttoePanel.add(getStudentButton(), null);
		}
		return voegStudenttoePanel;
	}

	private JPanel getVoegScholertoePanel() {
		if (voegScholertoePanel == null) {
			scholerLabel = new JLabel();
			scholerLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
			scholerLabel.setBounds(new Rectangle(17, 75, 173, 25));
			scholerLabel.setText("Naam scholer");
			opleidingLabel = new JLabel();
			opleidingLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
			opleidingLabel.setBounds(new Rectangle(17, 19, 364, 25));
			opleidingLabel.setText("Selecteer een CPP-Opleiding");
			voegScholertoePanel = new JPanel();
			voegScholertoePanel.setLayout(null);
			voegScholertoePanel.add(opleidingLabel, null);
			voegScholertoePanel.add(getScholingComboBox(), null);
			voegScholertoePanel.add(scholerLabel, null);
			voegScholertoePanel.add(getScholerTextField(), null);
			voegScholertoePanel.add(getScholerButton(), null);
		}
		return voegScholertoePanel;
	}

	/**
	 * This method initializes studentPanel       
	 *    
	 * @return javax.swing.JPanel 
	 */
	private JPanel getStudentPanel() {
		if (studentPanel == null) {
			uitlegLabel = new JLabel();
			uitlegLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
			uitlegLabel.setBounds(new Rectangle(16, 8, 430, 19));
			uitlegLabel.setText("Geef enter om invoer te bevestigen");
			nieuwepuntenLabel = new JLabel();
			nieuwepuntenLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
			nieuwepuntenLabel.setBounds(new Rectangle(20, 96, 532, 32));
			nieuwepuntenLabel.setText("Punten behaald (alleen reguliere opleiding) ");
			bestaandenaamLabel = new JLabel();
			bestaandenaamLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
			bestaandenaamLabel.setBounds(new Rectangle(20, 46, 172, 20));
			bestaandenaamLabel.setText("Studentnaam");
			studentPanel = new JPanel();
			studentPanel.setLayout(null);
			studentPanel.add(bestaandenaamLabel, null);
			studentPanel.add(getBestaandeNaamVeld(), null);
			studentPanel.add(getStudentInfoVeld(), null);
			studentPanel.add(nieuwepuntenLabel, null);
			studentPanel.add(getPuntenVeld(), null);
			studentPanel.add(uitlegLabel, null);
			studentPanel.add(getModuleKnop(), null);
		}
		return studentPanel;
	}

	/**
	 * This method initializes alleStudentenPanel 
	 *    
	 * @return javax.swing.JPanel 
	 */
	private JPanel getAlleStudentenPanel() {
		if (alleStudentenPanel == null) {
			alleStudentenPanel = new JPanel();
			alleStudentenPanel.setLayout(null);
			alleStudentenPanel.add(getToonAlleKnop(), null);
			alleStudentenPanel.add(getJScrollPane(), null);
		}
		return alleStudentenPanel;
	}

	/**
	 * This method initializes bestaandeNaamVeld  
	 *    
	 * @return javax.swing.JTextField     
	 */
	private JTextField getBestaandeNaamVeld() {
		if (bestaandeNaamVeld == null) {
			bestaandeNaamVeld = new JTextField();
			bestaandeNaamVeld.setFont(new Font("Tahoma", Font.PLAIN, 26));
			bestaandeNaamVeld.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bestaandeNaamVeldAction();
				}
			});
			bestaandeNaamVeld.setBounds(new Rectangle(232, 42, 180, 35));
		}
		return bestaandeNaamVeld;
	}

	/**
	 * This method initializes studentInfoVeld    
	 *    
	 * @return javax.swing.JTextField     
	 */
	private JTextField getStudentInfoVeld() {
		if (studentInfoVeld == null) {
			studentInfoVeld = new JTextField();
			studentInfoVeld.setForeground(Color.BLUE);
			studentInfoVeld.setFont(new Font("Tahoma", Font.PLAIN, 26));
			studentInfoVeld.setBounds(new Rectangle(20, 202, 635, 43));
			studentInfoVeld.setEditable(false);
		}
		return studentInfoVeld;
	}

	/**
	 * This method initializes puntenVeld 
	 *    
	 * @return javax.swing.JTextField     
	 */
	private JTextField getPuntenVeld() {
		if (puntenVeld == null) {
			puntenVeld = new JTextField();
			puntenVeld.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					puntenVeldAction();
				}
			});
			puntenVeld.setFont(new Font("Tahoma", Font.PLAIN, 26));
			puntenVeld.setBounds(new Rectangle(549, 101, 106, 35));
		}
		return puntenVeld;
	}

	/**
	 * This method initializes moduleKnop 
	 *    
	 * @return javax.swing.JButton        
	 */
	private JButton getModuleKnop() {
		if (moduleKnop == null) {
			moduleKnop = new JButton();
			moduleKnop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moduleKnopAction();
				}
			});
			moduleKnop.setFont(new Font("Tahoma", Font.PLAIN, 26));
			moduleKnop.setBounds(new Rectangle(17, 147, 395, 36));
			moduleKnop.setText("Module behaald (alleen CPP)");
		}
		return moduleKnop;
	}

	/**
	 * This method initializes opleidingComboBox  
	 *    
	 * @return javax.swing.JComboBox      
	 */
	private JComboBox getOpleidingComboBox() {
		if (opleidingComboBox == null) {
			opleidingComboBox = new JComboBox();
			opleidingComboBox.setFont(new Font("Tahoma", Font.PLAIN, 21));
			opleidingComboBox.setBounds(new Rectangle(319, 23, 200, 38));
		}
		return opleidingComboBox;
	}

	/**
	 * This method initializes studentTextField   
	 *    
	 * @return javax.swing.JTextField     
	 */
	private JTextField getStudentTextField() {
		if (studentTextField == null) {
			studentTextField = new JTextField();
			studentTextField.setFont(new Font("Tahoma", Font.PLAIN, 26));
			studentTextField.setBounds(new Rectangle(320, 80, 199, 38));
		}
		return studentTextField;
	}

	/**
	 * This method initializes studentButton      
	 *    
	 * @return javax.swing.JButton        
	 */
	private JButton getStudentButton() {
		if (studentButton == null) {
			studentButton = new JButton();
			studentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					studentKnopAction();
				}
			});
			studentButton.setFont(new Font("Sylfaen", Font.BOLD, 26));
			studentButton.setBounds(new Rectangle(16, 135, 243, 41));
			studentButton.setText("Voeg student toe");

		}
		return studentButton;
	}

	/**
	 * This method initializes scholingComboBox   
	 *    
	 * @return javax.swing.JComboBox      
	 */
	private JComboBox getScholingComboBox() {
		if (scholingComboBox == null) {
			scholingComboBox = new JComboBox();
			scholingComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 21));
			scholingComboBox.setBounds(new Rectangle(398, 21, 213, 34));
		}
		return scholingComboBox;
	}

	/**
	 * This method initializes scholerTextField   
	 *    
	 * @return javax.swing.JTextField     
	 */
	private JTextField getScholerTextField() {
		if (scholerTextField == null) {
			scholerTextField = new JTextField();
			scholerTextField.setFont(new Font("Tahoma", Font.PLAIN, 26));
			scholerTextField.setBounds(new Rectangle(398, 68, 213, 38));
		}
		return scholerTextField;
	}

	/**
	 * This method initializes scholerButton      
	 *    
	 * @return javax.swing.JButton        
	 */
	private JButton getScholerButton() {
		if (scholerButton == null) {
			scholerButton = new JButton();
			scholerButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
			scholerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					scholerKnopAction();
				}
			});
			scholerButton.setBounds(new Rectangle(27, 129, 281, 38));
			scholerButton.setText("Voeg scholer toe");
		}
		return scholerButton;
	}

	public static void main(String[] args) {
		StudentAdminFrame fr = new StudentAdminFrame();
		fr.setVisible(true);
	}
} // @jve:decl-index=0:visual-constraint="10,10"

