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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bll.CustomizeJTable;
import bll.GenresBLL;
import bll.NoticeMissingInformation;
import dal.GenresDAL;
import dto.GenresDTO;

public class GenresGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfId, tfName, tfInput;
	private JTable table;
	private JButton btnFind, btnAdd, btnEdit, btnDelete;
	private JLabel lbNoteId, lbNoteName, lbBack;

	private GenresDTO genres = new GenresDTO();
	private GenresDAL dao = new GenresDAL();
	private DefaultTableModel model;
	private NoticeMissingInformation notify = new NoticeMissingInformation();
	private CustomizeJTable custom = new CustomizeJTable();
	private GenresBLL bll = new GenresBLL();

	public GenresGUI() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(438, 99, 485, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.lightGray);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbId = new JLabel("Id");
		lbId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbId.setBounds(38, 90, 30, 20);
		contentPane.add(lbId);

		tfId = new JTextField();
		tfId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfId.setBounds(80, 90, 50, 24);
		contentPane.add(tfId);
		tfId.setColumns(10);

		btnFind = new JButton("Find");
		btnFind.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnFind.setBounds(350, 220, 100, 25);
		contentPane.add(btnFind);
		btnFind.addActionListener(this);

		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(350, 90, 100, 25);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);

		btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEdit.setBounds(350, 125, 100, 25);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 262, 449, 220);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = table.getSelectedRow();
					String id = String.valueOf(table.getValueAt(row, 0));
					GenresDTO m = dao.findById(id);
					if (m != null) {
						tfId.setText(model.getValueAt(row, 0).toString());
						tfName.setText(model.getValueAt(row, 1).toString());
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);

		JLabel lbName = new JLabel("Name");
		lbName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbName.setBounds(11, 150, 50, 20);
		contentPane.add(lbName);

		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfName.setColumns(10);
		tfName.setBounds(80, 150, 200, 24);
		contentPane.add(tfName);

		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(350, 160, 100, 25);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);

		lbNoteName = notify.getNotice("name");
		lbNoteName.setForeground(Color.RED);
		lbNoteName.setBounds(81, 180, 126, 14);
		contentPane.add(lbNoteName);
		lbNoteName.setVisible(false);

		lbNoteId = notify.getNotice("id");
		lbNoteId.setForeground(Color.RED);
		lbNoteId.setBounds(81, 120, 126, 14);
		contentPane.add(lbNoteId);
		lbNoteId.setVisible(false);

		JLabel lbInput = new JLabel("Enter a name");
		lbInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbInput.setBounds(11, 220, 100, 20);
		contentPane.add(lbInput);

		tfInput = new JTextField();
		tfInput.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfInput.setBounds(121, 220, 210, 24);
		contentPane.add(tfInput);
		tfInput.setColumns(10);

		JPanel pnTitle = new JPanel();
		pnTitle.setBounds(0, 0, 485, 75);
		pnTitle.setBackground(new Color(72, 169, 197));
		contentPane.add(pnTitle);
		pnTitle.setLayout(null);

		lbBack = new JLabel("");
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

		JLabel lbTitle = new JLabel("Manage Books Generes");
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setIcon(new ImageIcon(getClass().getClassLoader().getResource("herachy.png")));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbTitle.setBounds(68, 2, 350, 70);
		pnTitle.add(lbTitle);

		btnDelete.setFocusable(false);
		btnAdd.setFocusable(false);
		btnFind.setFocusable(false);
		btnEdit.setFocusable(false);

		custom.customizeTable(table);
		initTable();
		loadDataToTable("");
	}

	private void loadDataToTable(String input) {
		try {
			List<GenresDTO> list = dao.displayData(input);

			model.setRowCount(0);
			for (GenresDTO add : list) {
				model.addRow(new Object[] { add.getId(), add.getName() });
			}
			model.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initTable() {
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] { "Id", "Name" });
		table.setModel(model);
	}

	private void resetText() {
		tfId.setText("");
		tfName.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAdd) {

			if (notify.isFilled(tfName.getText(), lbNoteName)) {
				return;
			}

			try {
				genres.setName(tfName.getText());

				if (dao.loadDataByName(tfName.getText())) {
					JOptionPane.showMessageDialog(this, "Genres already exists");
					return;
				}

				if (bll.addGenres(genres)) {
					loadDataToTable("");
					resetText();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		if (e.getSource() == btnEdit) {

			if (notify.isFilled(tfId.getText(), lbNoteId)) {
				return;
			}

			if (notify.isId(tfId.getText())) {
				JOptionPane.showMessageDialog(this, "Invalid id !");
				return;
			}

			try {
				genres.setName(tfName.getText());
				genres.setId(Integer.parseInt(tfId.getText()));

				if (JOptionPane.showConfirmDialog(this, "Do you want to update the changes?", "message",
						JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
					return;
				}

				if (bll.editGenres(genres)) {
					loadDataToTable("");
					resetText();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		if (e.getSource() == btnDelete) {
			if (notify.isFilled(tfId.getText(), lbNoteId)) {
				return;
			}

			if (notify.isId(tfId.getText())) {
				JOptionPane.showMessageDialog(this, "Invalid id !");
				return;
			}

			try {
				if (JOptionPane.showConfirmDialog(this, "Do you want to delete the selected item?", "message",
						JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
					return;
				}

				if (bll.deleteGenres(tfId.getText())) {
					loadDataToTable("");
					resetText();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		if (e.getSource() == btnFind) {
			try {
				String input = tfInput.getText();
				loadDataToTable(input);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
