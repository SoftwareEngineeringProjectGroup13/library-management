package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import com.toedter.calendar.JDateChooser;

import bll.BookBLL;
import bll.ImageProcessing;
import bll.NoticeMissingInformation;
import dal.BookDAL;
import dto.BookDTO;

public class EditBookGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfId, tfPrice, tfName, tfPublisher, tfISBN;
	private JButton btnSearch;
	private JLabel lbNoteId, lbNoteISBN, lbNotePublisher, lbNotePrice, lbNoteDescription, lbImg, lbNoteName;
	private JComboBox cbAuthor, cbGenres;
	private JSpinner spQuantity;
	private JDateChooser dateChooser;
	private JButton btnSelectImg, btnEdit, btnReturn;
	private JTextArea taDescription;

	private DefaultComboBoxModel cbmGenres, cbmAuthor;
	private BookDAL dao = new BookDAL();
	private BookDTO book = new BookDTO();
	private byte[] personalImg;
	private NoticeMissingInformation notify = new NoticeMissingInformation();
	private ImageProcessing imgProcess = new ImageProcessing();
	private BookBLL bll = new BookBLL();

	public EditBookGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(265, 50, 836, 630);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(null);
		pnTitle.setBackground(new Color(255, 194, 14));
		pnTitle.setBounds(0, 0, 836, 75);
		contentPane.add(pnTitle);

		JLabel lbEditBook = new JLabel("Edit Book");
		lbEditBook.setIcon(new ImageIcon(getClass().getClassLoader().getResource("book.png")));
		lbEditBook.setHorizontalAlignment(SwingConstants.CENTER);
		lbEditBook.setForeground(Color.WHITE);
		lbEditBook.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbEditBook.setBounds(293, 2, 250, 70);
		pnTitle.add(lbEditBook);

		JLabel lbId = new JLabel("Enter Book ID");
		lbId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbId.setBounds(83, 90, 127, 20);
		contentPane.add(lbId);

		lbNoteId = notify.getNotice("id");
		lbNoteId.setForeground(Color.RED);
		lbNoteId.setBounds(83, 120, 126, 14);
		contentPane.add(lbNoteId);
		lbNoteId.setVisible(false);

		tfId = new JTextField();
		tfId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfId.setColumns(10);
		tfId.setBounds(215, 90, 120, 24);
		contentPane.add(tfId);

		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(364, 90, 91, 26);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(this);
		btnSearch.setFocusable(false);

		JLabel lbISBN = new JLabel("ISBN");
		lbISBN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbISBN.setBounds(83, 140, 40, 20);
		contentPane.add(lbISBN);

		tfISBN = new JTextField();
		tfISBN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfISBN.setColumns(10);
		tfISBN.setBounds(135, 140, 180, 24);
		contentPane.add(tfISBN);

		JLabel lbName = new JLabel("Name");
		lbName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbName.setBounds(76, 190, 45, 20);
		contentPane.add(lbName);

		JLabel lbAuthor = new JLabel("Author");
		lbAuthor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbAuthor.setBounds(68, 240, 55, 20);
		contentPane.add(lbAuthor);

		JLabel lbGenres = new JLabel("Genres");
		lbGenres.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbGenres.setBounds(68, 290, 50, 20);
		contentPane.add(lbGenres);

		JLabel lbQuatity = new JLabel("Quantity");
		lbQuatity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbQuatity.setBounds(56, 340, 65, 20);
		contentPane.add(lbQuatity);

		JLabel lbPublisher = new JLabel("Publisher");
		lbPublisher.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbPublisher.setBounds(50, 390, 70, 20);
		contentPane.add(lbPublisher);

		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfName.setColumns(10);
		tfName.setBounds(135, 190, 320, 24);
		contentPane.add(tfName);

		tfPublisher = new JTextField();
		tfPublisher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPublisher.setColumns(10);
		tfPublisher.setBounds(135, 390, 320, 24);
		contentPane.add(tfPublisher);

		cbGenres = new JComboBox();
		cbGenres.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbGenres.setBounds(135, 290, 200, 24);
		contentPane.add(cbGenres);

		spQuantity = new JSpinner();
		spQuantity.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spQuantity.setBounds(135, 340, 50, 24);
		contentPane.add(spQuantity);

		cbAuthor = new JComboBox();
		cbAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbAuthor.setBounds(135, 240, 200, 24);
		contentPane.add(cbAuthor);

		JLabel lbPrice = new JLabel("Price");
		lbPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbPrice.setBounds(78, 440, 40, 20);
		contentPane.add(lbPrice);

		tfPrice = new JTextField();
		tfPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPrice.setColumns(10);
		tfPrice.setBounds(135, 440, 150, 24);
		contentPane.add(tfPrice);

		JLabel lbDateReceived = new JLabel("Date Received");
		lbDateReceived.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbDateReceived.setBounds(14, 490, 110, 20);
		contentPane.add(lbDateReceived);

		dateChooser = new JDateChooser();
		dateChooser.setDate(new Date());
		dateChooser.setBounds(135, 490, 150, 24);
		contentPane.add(dateChooser);

		JLabel lbDescription = new JLabel("Book Description");
		lbDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbDescription.setBounds(545, 90, 135, 20);
		contentPane.add(lbDescription);

		taDescription = new JTextArea();
		taDescription.setWrapStyleWord(true);
		taDescription.setLineWrap(true);
		taDescription.setBounds(545, 130, 260, 125);
		contentPane.add(taDescription);

		JLabel lbBook = new JLabel("Book Cover");
		lbBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbBook.setBounds(545, 310, 95, 20);
		contentPane.add(lbBook);

		lbImg = new JLabel("");
		lbImg.setOpaque(true);
		lbImg.setHorizontalAlignment(SwingConstants.CENTER);
		lbImg.setBackground(Color.WHITE);
		lbImg.setBounds(545, 345, 120, 120);
		contentPane.add(lbImg);

		btnSelectImg = new JButton("select profile");
		btnSelectImg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSelectImg.setFocusable(false);
		btnSelectImg.setBounds(545, 480, 120, 26);
		contentPane.add(btnSelectImg);
		btnSelectImg.addActionListener(this);

		btnEdit = new JButton("Edit Book");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEdit.setFocusable(false);
		btnEdit.setBounds(238, 580, 150, 30);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(this);

		btnReturn = new JButton("Return Home");
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnReturn.setFocusable(false);
		btnReturn.setBounds(438, 580, 150, 30);
		contentPane.add(btnReturn);
		btnReturn.addActionListener(this);

		lbNoteISBN = notify.getNotice("isbn");
		lbNoteISBN.setForeground(Color.RED);
		lbNoteISBN.setBounds(83, 170, 126, 14);
		contentPane.add(lbNoteISBN);
		lbNoteISBN.setVisible(false);

		lbNoteName = notify.getNotice("book title");
		lbNoteName.setForeground(Color.RED);
		lbNoteName.setBounds(83, 220, 126, 14);
		contentPane.add(lbNoteName);
		lbNoteName.setVisible(false);

		lbNotePublisher = notify.getNotice("publisher");
		lbNotePublisher.setForeground(Color.RED);
		lbNotePublisher.setBounds(83, 420, 126, 14);
		contentPane.add(lbNotePublisher);
		lbNotePublisher.setVisible(false);

		lbNotePrice = notify.getNotice("price");
		lbNotePrice.setForeground(Color.RED);
		lbNotePrice.setBounds(83, 470, 126, 14);
		contentPane.add(lbNotePrice);
		lbNotePrice.setVisible(false);

		lbNoteDescription = notify.getNotice("description");
		lbNoteDescription.setForeground(Color.RED);
		lbNoteDescription.setBounds(545, 265, 126, 14);
		contentPane.add(lbNoteDescription);
		lbNoteDescription.setVisible(false);

		initComboBoxGenres();
		initComboBoxAuthor();
	}

	private void initComboBoxAuthor() {
		cbmAuthor = new DefaultComboBoxModel();
		try {
			HashMap<String, Integer> author = dao.getAuthorValue();
			for (String add : author.keySet()) {
				cbmAuthor.addElement(add);
			}
			cbAuthor.setModel(cbmAuthor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initComboBoxGenres() {
		cbmGenres = new DefaultComboBoxModel();
		try {
			HashMap<String, Integer> genres = dao.getGenresValue();
			for (String add : genres.keySet()) {
				cbmGenres.addElement(add);
			}
			cbGenres.setModel(cbmGenres);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void resetText() {
		tfId.setText("");
		tfISBN.setText("");
		tfName.setText("");
		cbGenres.setSelectedIndex(0);
		cbAuthor.setSelectedIndex(0);
		spQuantity.setValue(0);
		tfPublisher.setText("");
		tfPrice.setText("");
		dateChooser.setDate(new Date());
		taDescription.setText("");
		lbImg.setIcon(new ImageIcon(getClass().getClassLoader().getResource("user.png")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSearch) {

			if (notify.isFilled(tfId.getText(), lbNoteId)) {
				return;
			}
			
			if (notify.isId(tfId.getText())) {
				JOptionPane.showMessageDialog(this, "Invalid id !");
				return;
			}

			try {
				BookDTO b = dao.findById(tfId.getText());

				if (b != null) {

					int valueGenres = b.getGenresId();
					int valueAuthor = b.getAuthorId();

					tfId.setText(String.valueOf(b.getId()));
					tfISBN.setText(b.getIsbn());
					tfName.setText(b.getBookTitle());
					cbGenres.setSelectedItem(dao.getKeyGenresToDisplay(valueGenres));
					cbAuthor.setSelectedItem(dao.getKeyAuthorToDisplay(valueAuthor));
					spQuantity.setValue(b.getQuantity());
					tfPublisher.setText(b.getPublisher());
					tfPrice.setText(String.valueOf(b.getPrice()));

					Date convertStringToDate = new SimpleDateFormat("yyyy-MM-dd").parse(b.getDateReceived());
					dateChooser.setDate(convertStringToDate);

					taDescription.setText(b.getDescription());

					String[] extensionImg = { "jpg", "png", "jpeg", "gif" };
					for (String type : extensionImg) {
						Image img = imgProcess.convertByteArrayToImage(b.getPicture(), type);
						lbImg.setIcon(new ImageIcon(img));
						personalImg = b.getPicture();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Invalid id");
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
				HashMap<String, Integer> author = dao.getAuthorValue();
				HashMap<String, Integer> genres = dao.getGenresValue();
				int authorId = author.get(cbAuthor.getSelectedItem());
				int genresId = genres.get(cbGenres.getSelectedItem());

				book.setId(Integer.parseInt(tfId.getText()));
				book.setIsbn(tfISBN.getText());
				book.setBookTitle(tfName.getText());
				book.setAuthorId(authorId);
				book.setGenresId(genresId);
				book.setQuantity((Integer) spQuantity.getValue());
				book.setPublisher(tfPublisher.getText());
				book.setPrice(Integer.parseInt((tfPrice.getText())));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dob = sdf.format(dateChooser.getDate());
				book.setDateReceived(dob);

				book.setDescription(taDescription.getText());
				book.setPicture(personalImg);

				if (JOptionPane.showConfirmDialog(this, "Do you want to update book?", "Message",
						JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
					return;
				}

				if (bll.editBook(book)) {
					resetText();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		if (e.getSource() == btnSelectImg) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Select profile picture");

			fileChooser.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					if (f.isDirectory()) {
						return true;
					}

					String extension = imgProcess.getImageExtension(f);
					if (extension != null) {
						if (extension.equals(imgProcess.GIF) || extension.equals(imgProcess.JPG)
								|| extension.equals(imgProcess.JPEG) || extension.equals(imgProcess.PNG)) {
							return true;
						} else {
							return false;
						}
					}
					return false;
				}

				@Override
				public String getDescription() {
					return "Just Images";
				}
			});

			if (fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
				return;
			}

			String[] applyExtension = { "png", "jpg", "jpeg", "gif" };
			File file = fileChooser.getSelectedFile().getAbsoluteFile();
			try {
				ImageIcon icon = new ImageIcon(file.getPath());
				Image img = imgProcess.resizeImage(icon.getImage(), 120, 120);
				ImageIcon resize = new ImageIcon(img);
				lbImg.setIcon(resize);

				for (String type : applyExtension) {
					personalImg = imgProcess.convertImageToByteArray(img, type);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		if (e.getSource() == btnReturn) {
			dispose();
			new HomeGUI().setVisible(true);
		}
	}
}
