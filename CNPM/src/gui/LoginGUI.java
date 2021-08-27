package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bll.NoticeMissingInformation;
import dal.LoginDAL;
import dto.LoginDTO;

public class LoginGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfUn;
	private JPasswordField pw;
	private JButton btnLogin, btnSu;
	private JCheckBox cb;
	private JLabel lbNoteUn, lbForget, lbFb, lbGg, lbTw, lbNotePw;

	private LoginDTO login = new LoginDTO();
	private LoginDAL dao = new LoginDAL();
	private NoticeMissingInformation notify = new NoticeMissingInformation();

	public LoginGUI() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 20, 600, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(28, 121, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(105, 30, 390, 620);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbTitle = new JLabel("Login");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbTitle.setBounds(162, 50, 70, 30);
		panel.add(lbTitle);

		JLabel lbUn = new JLabel("Username");
		lbUn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbUn.setBounds(40, 140, 57, 14);
		panel.add(lbUn);

		JLabel lbPw = new JLabel("Password");
		lbPw.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbPw.setBounds(40, 230, 56, 14);
		panel.add(lbPw);

		tfUn = new JTextField();
		tfUn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfUn.setBounds(40, 170, 300, 30);
		panel.add(tfUn);
		tfUn.setColumns(10);

		pw = new JPasswordField();
		pw.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pw.setBounds(40, 260, 300, 30);
		panel.add(pw);

		cb = new JCheckBox("Show password");
		cb.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb.setBounds(38, 310, 125, 20);
		panel.add(cb);

		char displayEchoChar = pw.getEchoChar();
		cb.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					pw.setEchoChar(displayEchoChar);
				} else {
					pw.setEchoChar((char) 0);
				}
			}
		});

		lbForget = new JLabel(" Forgot password?");
		lbForget.setForeground(new Color(0, 0, 255));
		lbForget.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbForget.setBounds(231, 310, 109, 20);
		panel.add(lbForget);

		btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(40, 374, 300, 30);
		panel.add(btnLogin);
		btnLogin.setFocusable(false);
		btnLogin.addActionListener(this);

		JLabel lbOr1 = new JLabel("Or Sign Up Using");
		lbOr1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbOr1.setBounds(145, 430, 100, 14);
		panel.add(lbOr1);

		lbFb = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("fb.png")));
		lbFb.setBounds(124, 465, 46, 32);
		panel.add(lbFb);

		lbGg = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("gg.png")));
		lbGg.setBounds(170, 465, 46, 32);
		panel.add(lbGg);

		lbTw = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("twitter.png")));
		lbTw.setBounds(216, 465, 46, 32);
		panel.add(lbTw);

		btnSu = new JButton("SIGN UP");
		btnSu.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSu.setBounds(145, 570, 100, 30);
		panel.add(btnSu);
		btnSu.setFocusable(false);
		btnSu.addActionListener(this);

		JLabel lbOr2 = new JLabel("Or Sign Up Using");
		lbOr2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbOr2.setBounds(145, 530, 100, 14);
		panel.add(lbOr2);

		lbNoteUn = notify.getNotice("username");
		lbNoteUn.setForeground(Color.RED);
		lbNoteUn.setBounds(37, 205, 126, 14);
		panel.add(lbNoteUn);
		lbNoteUn.setVisible(false);

		lbNotePw = notify.getNotice("password");
		lbNotePw.setForeground(Color.RED);
		lbNotePw.setBounds(38, 295, 126, 14);
		panel.add(lbNotePw);
		lbNotePw.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {

			if (notify.isFilled(tfUn.getText(), lbNoteUn)
					|| notify.isFilled(String.valueOf(pw.getPassword()), lbNotePw)) {
				return;
			}

			try {
				if (dao.checkLogin(tfUn.getText(), String.valueOf(pw.getPassword()))) {
					new HomeGUI().setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Incorrect account or password");
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
