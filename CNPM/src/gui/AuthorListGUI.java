package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bll.CustomizeJTable;
import dal.AuthorDAL;
import dto.AuthorDTO;

public class AuthorListGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfName;
	private JButton btnSearch;
	private JTable table;
	private JLabel lbFullName, lbEmail, lbExpertise, lbDateOfBirth;

	private AuthorDTO author = new AuthorDTO();
	private AuthorDAL dao = new AuthorDAL();
	private DefaultTableModel model;
	private CustomizeJTable custom = new CustomizeJTable();

	public AuthorListGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(225, 125, 916, 470);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(null);
		pnTitle.setBackground(new Color(186, 85, 211));
		pnTitle.setBounds(0, 0, 916, 75);
		contentPane.add(pnTitle);

		JLabel lbBack = new JLabel("");
		lbBack.setIcon(new ImageIcon(getClass().getClassLoader().getResource("home.png")));
		lbBack.setBounds(0, 0, 32, 32);
		pnTitle.add(lbBack);
		lbBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new HomeGUI().setVisible(true);
			}
		});

		JLabel lbAuthorList = new JLabel("Author List");
		lbAuthorList.setIcon(new ImageIcon(getClass().getClassLoader().getResource("writer.png")));
		lbAuthorList.setHorizontalAlignment(SwingConstants.CENTER);
		lbAuthorList.setForeground(Color.WHITE);
		lbAuthorList.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbAuthorList.setBounds(333, 2, 250, 70);
		pnTitle.add(lbAuthorList);

		JLabel lbName = new JLabel("Enter the name");
		lbName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbName.setBounds(11, 90, 125, 20);
		contentPane.add(lbName);

		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfName.setColumns(10);
		tfName.setBounds(165, 90, 320, 26);
		contentPane.add(tfName);

		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSearch.setBounds(510, 90, 140, 28);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(this);
		btnSearch.setFocusable(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 150, 700, 300);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = table.getSelectedRow();
					String id = String.valueOf(table.getValueAt(row, 0));
					AuthorDTO a = dao.findById(id);
					if (a != null) {
						lbFullName.setText(a.getLastName() + " " + a.getFirstName());
						lbExpertise.setText(a.getExpertise());
						lbEmail.setText(a.getEmail());
						lbDateOfBirth.setText(a.getDateOfBirth());
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);

		lbFullName = new JLabel("Full Name");
		lbFullName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbFullName.setBounds(730, 150, 175, 18);
		contentPane.add(lbFullName);

		lbExpertise = new JLabel("Expertise");
		lbExpertise.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbExpertise.setBounds(730, 190, 175, 18);
		contentPane.add(lbExpertise);

		lbEmail = new JLabel("Email");
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbEmail.setBounds(730, 230, 175, 18);
		contentPane.add(lbEmail);

		lbDateOfBirth = new JLabel("Date of birth");
		lbDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbDateOfBirth.setBounds(730, 270, 175, 18);
		contentPane.add(lbDateOfBirth);

		custom.customizeTable(table);
		initTable();
		loadDataToTable("");
		customColumnWidth();
	}

	private void customColumnWidth() {
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setMaxWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setMaxWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setMaxWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setMaxWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setMaxWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(170);
		table.getColumnModel().getColumn(6).setMaxWidth(170);
	}

	private void loadDataToTable(String input) {
		try {
			List<AuthorDTO> list = dao.displayData(input);

			model.setRowCount(0);
			for (AuthorDTO add : list) {
				model.addRow(new Object[] { add.getId(), add.getFirstName(), add.getLastName(), add.getExpertise(),
						add.getEmail(), add.getDateOfBirth(), add.getAbout() });
			}
			model.fireTableDataChanged();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initTable() {
		model = new DefaultTableModel();
		model.setColumnIdentifiers(
				new String[] { "Id", "First Name", "Last Name", "Expertise", "Email", "Date Of Birth", "About" });
		table.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			try {
				String input = tfName.getText();
				loadDataToTable(input);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
