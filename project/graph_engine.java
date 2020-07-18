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
		/*��������������*/
		SuperPanel mp = new SuperPanel();
		/*��������������*/
		ControlPanel mc = new ControlPanel();
		/*����������������*/
		JPanel external_panel = new JPanel();
		
		jd.setSize(200, 100);
		jd.setLayout(gbl);
		jd.setLocationRelativeTo(main_frame);
		jd.setResizable(false);
		main_frame.setSize((int)Width,(int)Height);		//�����ʹ�ö����ڱ�������ĳ�����ʼ��
		main_panel.setLayout(gbl);						//Ϊ�������Ӳ��ֹ�����
		main_panel.setBackground(Color.orange);			//���������ı�����ɫ
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
					// TODO �Զ����ɵķ������
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
					// TODO �Զ����ɵķ������
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