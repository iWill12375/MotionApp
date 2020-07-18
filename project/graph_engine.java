package main_graph;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
public class graph_engine {
	public final static double Width = 1000;
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
		/*声明超级面板对象*/
		SuperPanel mp = new SuperPanel();
		/*声明控制面板对象*/
		ControlPanel mc = new ControlPanel();
		/*额外的面板留作他用*/
		JPanel external_panel = new JPanel();
		
		jd.setSize(200, 100);
		jd.setLayout(gbl);
		jd.setLocationRelativeTo(main_frame);
		jd.setResizable(false);
		main_frame.setSize((int)Width,(int)Height);		//主框架使用定义在本类里面的常数初始化
		main_panel.setLayout(gbl);						//为主面板添加布局管理器
		main_panel.setBackground(Color.orange);			//设置主面板的背景颜色
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
		main_panel.add(mc,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 40;
		gbc.weighty = 80;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		main_panel.add(mp,gbc);
		
		main_frame.setResizable(false);
		main_frame.setLocationRelativeTo(null);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setVisible(true);
		
		mp.fillXY();
		
		
		try {
			while(true)
			{
				mp.RunningTime();
				//bp.setColor(Color.blue);
				//bp.depictTrack(mp.getRelativeLocation(mp.getBall1()).getX(), mp.getRelativeLocation(mp.getBall1()).getY());
				//bp.setColor(Color.red);
				//bp.depictTrack(mp.getRelativeLocation(mp.getBall2()).getX(), mp.getRelativeLocation(mp.getBall2()).getY());
				mc.setContent(mp.getBall1(), mp.getBall2());
			}
		}
		catch(AWTException ex)
		{
			//jd.setLayout(new GridLayout(2,1));
			JLabel message = new JLabel("engine over flow!");
			JButton b1 = new JButton("Exit");
			b1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					System.exit(0);
				}
			});
			message.setSize(jd.getSize().width, 50);
			jd.add(message);
			jd.add(b1);
			jd.setVisible(true);
		}
		catch(RuntimeException et)
		{
			//jd.setLayout(new GridLayout(2,1));
			JLabel message = new JLabel("over esc speed!");
			JButton b1 = new JButton("Exit");
			b1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					System.exit(0);
				}
				
			});
			message.setSize(jd.getSize().width, 50);
			jd.add(message);
			jd.add(b1);
			jd.setVisible(true);
		}
		
	}
	//this is end of main
}
//this is end of public class

class TestPanel extends JPanel{
	private Ellipse2D.Double ee;
	private double r;
	public TestPanel(double x,double y,double rr)
	{
		r = rr;
		ee = new Ellipse2D.Double(x-r,y-r,2*r,2*r);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				ee.setFrame(e.getX()-r, e.getY()-r, 2*r, 2*r);
				repaint();
			}
		});
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(ee);
	}
}