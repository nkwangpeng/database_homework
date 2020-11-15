package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class MainPanel extends JPanel{  
    public void paintComponent(Graphics g){  
        super.paintComponent(g);  
        //����һ�ű���ͼƬ  2.jpg��ͼƬ��·��  �Լ��趨Ϊ�Լ���Ҫ��ӵ�ͼƬ  
        Image image = new ImageIcon("images//back.png").getImage();  
        g.drawImage(image, 0, 0, this);  
    }  
}
public class mainwin extends JFrame {
	MainPanel panel=new MainPanel();
	JButton B1=new JButton("����ѧ����Ϣ");
	JButton B2=new JButton("ɾ��ѧ����Ϣ");
	JButton B3=new JButton("����ѧ����Ϣ");
	JButton B4=new JButton("��ѧ�Ų�ѯѧ���ɼ�");
	JButton B5=new JButton("���γ̲�ѯѧ���ɼ�");
	public mainwin(){
		setTitle("����ѡ��");

		B1.setBounds(new Rectangle(120,40,170,30));
		B2.setBounds(new Rectangle(120,100,170,30));
		B3.setBounds(new Rectangle(120,160,170,30));
		B4.setBounds(new Rectangle(120,220,170,30));
		B5.setBounds(new Rectangle(120,280,170,30));
		B1.addActionListener(new insertListener());
		B2.addActionListener(new deleteListener());
		B3.addActionListener(new updateListener());
		B4.addActionListener(new findListener());
		B5.addActionListener(new queryListener());
		/*Container contpane=getContentPane();
		contpane.setLayout(null);
		contpane.add(B1);
		contpane.add(B2);
		contpane.add(B3);
		contpane.add(B4);*/
		panel.setLayout(null);
		panel.add(B1);
		panel.add(B2);
		panel.add(B3);
		panel.add(B4);
		panel.add(B5);
		this.add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 420);
		setLocationRelativeTo(null);
		setVisible(true);
		this.setResizable(false);
		
	}
	class insertListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new insert();
		}	
	}
	
	class deleteListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new delete();
		}	
	}
	class updateListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				dispose();
				new update();
			}	
		}
	class findListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				dispose();
				new find();
			}	
		}
	class queryListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new query();
		}	
	}
	public static void main(String args[]){
		new mainwin();
	}
	}
