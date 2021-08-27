package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bll.CustomizeJTable;
import bll.ImageProcessing;
import dal.BookDAL;
import dto.BookDTO;

public class BookListGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfName;
	private JTable table;
	private JLabel lbBookTitle, lbAuthor, lbImg, lbISBN, lbGenres, lbQuantity;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JButton btnSearch;

	private BookDAL dao = new BookDAL();
	private BookDTO book = new BookDTO();
	private ImageProcessing imgProcess = new ImageProcessing();
	private CustomizeJTable custom = new CustomizeJTable();
	private DefaultTableCellRenderer cellRenderer;

	public BookListGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 130, 1000, 470);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(null);
		pnTitle.setBackground(new Color(255, 194, 14));
		pnTitle.setBounds(0, 0, 1000, 75);
		contentPane.add(pnTitle);

		JLabel lbTitle = new JLabel("Book List");
		lbTitle.setIcon(new ImageIcon(getClass().getClassLoader().getResource("book.png")));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbTitle.setBounds(375, 2, 250, 70);
		pnTitle.add(lbTitle);

		JLabel lbBack = new JLabel("");
		lbBack.setIcon(new ImageIcon(getClass().getClassLoader().getResource("home.png")));
		lbBack.setBounds(0, 2, 32, 32);
		pnTitle.add(lbBack);
		lbBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new HomeGUI().setVisible(true);
			}
		});

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
		btnSearch.setBounds(510, 90, 130, 28);
		contentPane.add(btnSearch);
		btnSearch.setFocusable(false);
		btnSearch.addActionListener(this);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 150, 790, 300);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = table.getSelectedRow();
					String id = String.valueOf(table.getValueAt(row, 0));
					BookDTO b = dao.findById(id);
					if (b != null) {
						int valueGenres = b.getGenresId();
						int valueAuthor = b.getAuthorId();

						lbBookTitle.setText(b.getBookTitle());
						lbAuthor.setText(dao.getKeyAuthorToDisplay(valueAuthor));
						lbISBN.setText(b.getIsbn());
						lbGenres.setText(dao.getKeyGenresToDisplay(valueGenres));
						lbQuantity.setText(String.valueOf(b.getQuantity()));

						String[] extensionImg = { "jpg", "png", "jpeg", "gif" };
						for (String type : extensionImg) {
							Image img = imgProcess.convertByteArrayToImage(b.getPicture(), type);
							lbImg.setIcon(new ImageIcon(img));
						}
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}

			}
		});
		scrollPane.setViewportView(table);

		lbBookTitle = new JLabel("Book Title");
		lbBookTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbBookTitle.setBounds(814, 270, 175, 18);
		contentPane.add(lbBookTitle);

		lbAuthor = new JLabel("Author");
		lbAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbAuthor.setBounds(814, 310, 175, 18);
		contentPane.add(lbAuthor);

		lbISBN = new JLabel("ISBN");
		lbISBN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbISBN.setBounds(814, 350, 175, 18);
		contentPane.add(lbISBN);

		lbGenres = new JLabel("Genres");
		lbGenres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbGenres.setBounds(814, 390, 175, 18);
		contentPane.add(lbGenres);

		lbQuantity = new JLabel("Quantity");
		lbQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbQuantity.setBounds(814, 430, 175, 18);
		contentPane.add(lbQuantity);

		lbImg = new JLabel("");
		lbImg.setHorizontalAlignment(SwingConstants.CENTER);
		lbImg.setIcon(new ImageIcon(getClass().getClassLoader().getResource("book.png")));
		lbImg.setBounds(814, 151, 100, 100);
		contentPane.add(lbImg);
		lbImg.setBackground(Color.white);
		lbImg.setOpaque(true);

		custom.customizeTable(table);
		initTable();
		loadDataToTable("");
		customColumnWidth();
	}

	private void customColumnWidth() {
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(1).setMaxWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setMaxWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setMaxWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setMaxWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setMaxWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setMaxWidth(150);
	}

	private void loadDataToTable(String input) {
		try {
			List<BookDTO> list = dao.displayData(input);

			model.setRowCount(0);
			for (BookDTO add : list) {
				model.addRow(new Object[] { add.getId(), add.getIsbn(), add.getBookTitle(), add.getPublisher(),
						add.getPrice(), add.getDateReceived(), add.getDescription() });
			}
			model.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initTable() {
		model = new DefaultTableModel();
		model.setColumnIdentifiers(
				new String[] { "Id", "ISBN", "Name", "Publisher", "Price", "Date Received", "Description" });
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
