package student;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class LoginPanel extends JPanel{  
    public void paintComponent(Graphics g){ 
        super.paintComponent(g);  
        //����һ�ű���ͼƬ  2.jpg��ͼƬ��·��  �Լ��趨Ϊ�Լ���Ҫ��ӵ�ͼƬ  
        Image image = new ImageIcon("images//back.png").getImage();  
        g.drawImage(image, 0, 0, this);  
    }  
}

public class login extends JFrame {
	LoginPanel panel=new LoginPanel();
	JLabel labelTitle = new JLabel("ѧ����Ϣ����ϵͳ");
	JLabel labelId = new JLabel("�û���:");
	JLabel labelKey = new JLabel("����:");
	JTextField textId = new JTextField();
	JPasswordField textKey = new JPasswordField();
	JButton buttonLogin = new JButton("��¼");

	public login() {
		labelTitle.setFont(new java.awt.Font("����", Font.BOLD, 40));
		labelId.setFont(new java.awt.Font("����", Font.PLAIN, 30));
		labelKey.setFont(new java.awt.Font("����", Font.PLAIN, 30));
		
		labelTitle.setForeground(Color.white);
		labelId.setForeground(Color.white);
		labelKey.setForeground(Color.white);
		
		labelTitle.setBounds(new Rectangle(220, 170, 500, 40));
		labelId.setBounds(new Rectangle(130, 240, 150, 30));
		labelKey.setBounds(new Rectangle(130, 320, 150, 30));
		textId.setBounds(new Rectangle(260, 240, 250, 30));
		textKey.setBounds(new Rectangle(260, 320, 250, 30));
		textKey.setEchoChar('*');

		buttonLogin.setBounds(310, 380, 140, 30);
		buttonLogin.addActionListener(new creatNewWindow());
		
		panel.setLayout(null);
		panel.add(labelTitle);
		panel.add(labelId);
		panel.add(labelKey);
		panel.add(textId);
		panel.add(textKey);
		panel.add(buttonLogin);
		this.add(panel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,700);
		setVisible(true);
		setLocationRelativeTo(null);
		this.setResizable(false);
	}

	class creatNewWindow implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (textId.getText().equals("nkwangpeng") && textKey.getText().equals("wp010214")) {
				dispose();
				new mainwin();
				
				String driverName = "com.mysql.cj.jdbc.Driver";
				String dbURL = "jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC";
				String userName = "root"; 
				String userPwd = "wp010214"; 
				try {
					Class.forName(driverName);
					Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
						System.out.println("���ӳɹ���"); 
				} catch (Exception ex) {
					System.out.println("����ʧ�ܣ�");
					ex.printStackTrace();
				}
			} 
			else {
				JOptionPane.showMessageDialog(null, "�������", "�������", JOptionPane.INFORMATION_MESSAGE);
				textKey.setText("");
			}

		}
	}

	public static void main(String args[]) {
		new login();
	}
}
