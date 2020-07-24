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
		// TODO 自动生成的方法存根
		System.out.println(1.0/2);
		System.out.println(1);
		/*主框架*/
		JFrame main_frame = new JFrame("my_windows");
		/*盛放其余面板的主面板*/
		JPanel main_panel = new JPanel();
		/*用于布置面板的布局管理器*/
		GridBagLayout gbl = new GridBagLayout();
		/*网格包布局管理器的参数*/
		GridBagConstraints gbc = new GridBagConstraints();
		/*异常发生时弹出的对话框，提示用户退出*/
		JDialog jd = new JDialog(main_frame,"system unstable",true);
		/*底部绘制轨迹的面板*/
		ButtomPanel bp = new ButtomPanel();
		/*额外的面板留作他用*/
		JPanel external_panel = new JPanel();
		/*功能面板*/
		FuncPanel sc = new FuncPanel();
		/*集成面板*/
		IntegratePanel ip = new IntegratePanel();
		
		jd.setSize(200, 100);
		jd.setLayout(gbl);
		jd.setLocationRelativeTo(main_frame);
		jd.setResizable(false);
		bp.setLayout(new GridLayout(1,1));
		main_frame.setSize((int)Width,(int)Height);		//主框架使用定义在本类里面的常数初始化
		main_panel.setLayout(gbl);						//为主面板添加布局管理器
		main_frame.add(main_panel);						//向主框架添加主面板
		
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