package main_graph;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class graph_engine {
	public final static double Width = 1200;
	public final static double Height = 720;
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		System.out.println(1.0/2);
		System.out.println(1);
		/*�����*/
		JFrame main_frame = new JFrame("my_windows");
		/*ʢ���������������*/
		JPanel main_panel = new JPanel();
		/*���ڲ������Ĳ��ֹ�����*/
		GridBagLayout gbl = new GridBagLayout();
		/*��������ֹ������Ĳ���*/
		GridBagConstraints gbc = new GridBagConstraints();
		/*�쳣����ʱ�����ĶԻ�����ʾ�û��˳�*/
		JDialog jd = new JDialog(main_frame,"system unstable",true);
		/*�ײ����ƹ켣�����*/
		ButtomPanel bp = new ButtomPanel();
		/*����������������*/
		JPanel external_panel = new JPanel();
		/*�������*/
		FuncPanel sc = new FuncPanel();
		/*�������*/
		IntegratePanel ip = new IntegratePanel();
		
		jd.setSize(200, 100);
		jd.setLayout(gbl);
		jd.setLocationRelativeTo(main_frame);
		jd.setResizable(false);
		bp.setLayout(new GridLayout(1,1));
		main_frame.setSize((int)Width,(int)Height);		//�����ʹ�ö����ڱ�������ĳ�����ʼ��
		main_panel.setLayout(gbl);						//Ϊ�������Ӳ��ֹ�����
		main_frame.add(main_panel);						//���������������
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.ipadx = 10;
		gbc.ipady = 10;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 10;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		main_panel.add(external_panel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 40;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		main_panel.add(ip.cp,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 40;
		gbc.weighty = 80;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		main_panel.add(bp,gbc);
		
		external_panel.setLayout(gbl);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		external_panel.add(ip.sp,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 80;
		external_panel.add(sc,gbc);
		
		bp.add(ip.mp);
		ip.mp.putXY();
		
		main_frame.setResizable(false);
		main_frame.setLocationRelativeTo(null);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setVisible(true);
		
		ip.start();
		
		System.out.println("This is main thread!");
	}
	//this is end of main
}
//this is end of public class