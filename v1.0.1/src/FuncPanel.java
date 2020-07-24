package main_graph;
/**
 * @author zjc
 * 2020.7.20
 * This is a functional panel
 * provides input, establishment,
 * error detect and choose object
 * functions. And it has a Layout of
 * complex GridBagLayout.
 */
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class FuncPanel extends JPanel implements DocumentListener{
	
	private boolean choosen;				//记录组合框选中状态的信号量
	private String ballname;				//标记当前选中的那个球
	protected Buffer buffer;				//读入输入数据的缓冲区
	private JComboBox<String> elect;		//组合框
	private static boolean item1 = true;	//标记三个待选项是否被选中
	private static boolean item2 = true;
	private static boolean item3 = true;
	private static boolean bus = false;		//忙状态信号量，作用与choosen相同
	private JLabel info;
	private JLabel title;					//头标题，无实际作用
	private JLabel X;						//提示X输入的标签
	private JLabel Y;
	private JLabel Vx;
	private JLabel Vy;
	private JLabel M;
	private JLabel R;
	private JTextField XX;					//读取与显示输入值的文本框
	private JTextField YY;
	private JTextField MM;
	private JTextField RR;
	private JTextField VXX;
	private JTextField VYY;
	private JButton set;					//“设置”按钮
	private JButton randomset;				//“随机生成”按钮，与“设置”按钮互斥
	private Pattern pattern;				//待匹配的正则表达式
	
	public FuncPanel() {
		choosen = false;
		ballname = "";
		buffer = new Buffer();
		elect = new JComboBox<String>();
		elect.addItem("ball1");
		elect.addItem("ball2");
		elect.addItem("ball3");
		
		elect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				ballname = elect.getItemAt(elect.getSelectedIndex()).toString();
				//非忙状态可进入具体选择执行代码
				if(!bus) {
					if(ballname.equals("ball1") && item1 && !choosen)
					{
						//这里使能初始化为 disable 的set按钮
						info.setText(info.getText()+"ball1");
						set.setEnabled(true);
						choosen = true;					//置选中状态
						ballname = "ball1";				//设置选中的为ball1，以通知后续的模块
						bus = true;						//置为“忙”状态
						System.out.println("1.#");
						makeTextFieldEditable();		//打开所有的初始关闭的文本框，获取输入
						item1 = false;					//置以选状态
						bus = false;					//结束忙状态
						randomset.setEnabled(false);	//disable随机按钮
					}
					if(ballname.equals("ball2") && item2 && !choosen)
					{
						info.setText(info.getText()+"ball2");
						set.setEnabled(true);
						choosen = true;
						ballname = "ball2";
						bus = true;
						makeTextFieldEditable();
						System.out.println("2.#");
						item2 = false;
						bus = false;
						randomset.setEnabled(false);
					}
					if(ballname.equals("ball3") && item3 && !choosen)
					{
						info.setText(info.getText()+"ball3");
						set.setEnabled(true);
						choosen = true;
						ballname = "ball3";
						bus = true;
						makeTextFieldEditable();
						System.out.println("3.#");
						item3 = false;
						bus = false;
						randomset.setEnabled(false);
					}
				}
			}
		}
		);
		
		info = new JLabel("is setting :");
		title = new JLabel("Initialize argus");
		X = new JLabel("X:");
		Y = new JLabel("Y:");
		Vx = new JLabel("Vx:");
		Vy = new JLabel("Vy:");
		M = new JLabel("M:");
		R = new JLabel("R:");
		XX = new JTextField(5);
		XX.setEditable(false);
		YY = new JTextField(5);
		YY.setEditable(false);
		MM = new JTextField(5);
		MM.setEditable(false);
		RR = new JTextField(5);
		RR.setEditable(false);
		VXX = new JTextField(5);
		VXX.setEditable(false);
		VYY = new JTextField(5);
		VYY.setEditable(false);
		set = new JButton("SET");
		randomset = new JButton("Random");
		pattern = Pattern.compile("^\\d+(\\.\\d+)?|^-\\d+(\\.\\d+)?");
		
		set.setEnabled(false);
		set.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int transflag;						//这个标志的每一个位都有其含义
				
				transflag = isLegal();				//判断输入是否合法的方法，规则是上面的正则式
				if(choosen) {						//如果有组合框的项被选中才进入执行
					if(transflag == 0)				//每个位为零才认为全都输入合法
					{
						info.setText("is setting:");
						transdata();				//先把合法数据传输出去
						clearTextField();			//再把文本框清零
						makeTextFieldUnefitable();	//设为关闭
						choosen = false;			//置为非选中状态，对此处的处理已经结束
						setExternalAvailable();
					}
					else
					{
						dealIlegal(transflag);		//处理异常的输入
					}
				}
			}
		});
		
		this.setLayout(new GridBagLayout());		//复杂的网格包布局
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.CENTER;
		
		//以下添加组件到指定位置
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(title,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(elect,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(info,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(X,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(XX,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(Y,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(YY,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(M,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(MM,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(R,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(RR,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(Vx,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(VXX,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(Vy,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(VYY,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(set,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		this.add(randomset,gbc);
		
		randomset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				setRandomDataItem("ball1");
				setRandomDataItem("ball2");
				setRandomDataItem("ball3");
				System.out.println("B1X = "+DeliverMan.B1X);
				System.out.println("B1Y = "+DeliverMan.B1Y);
				System.out.println("B1R = "+DeliverMan.B1R);
				System.out.println("B2X = "+DeliverMan.B2X);
				System.out.println("B2Y = "+DeliverMan.B2Y);
				System.out.println("B2R = "+DeliverMan.B2R);
				System.out.println("B3X = "+DeliverMan.B3X);
				System.out.println("B3Y = "+DeliverMan.B3Y);
				System.out.println("B3R = "+DeliverMan.B3R);
				randomset.setEnabled(false);
				
				item1 = false;
				item2 = false;
				item3 = false;
				setExternalAvailable();
			}
		});
		
		this.addTextListenerForAll();		//为每个文本框添加监听器
	}
	
	//这个方法让所有的文本框打开处于可编辑状态
	private void makeTextFieldEditable() {
		XX.setEditable(true);
		YY.setEditable(true);
		MM.setEditable(true);
		RR.setEditable(true);
		VXX.setEditable(true);
		VYY.setEditable(true);
	}
	//此方法让所有的文本框关闭处于不可编辑状态
	private void makeTextFieldUnefitable() {
		XX.setEditable(false);
		YY.setEditable(false);
		MM.setEditable(false);
		RR.setEditable(false);
		VXX.setEditable(false);
		VYY.setEditable(false);
	}
	
	//此方法负责为每个文本框添加监听器
	private void addTextListenerForAll() {
		//这里使用本类当作监听器，减少了参数传递
		XX.getDocument().addDocumentListener(this);
		YY.getDocument().addDocumentListener(this);
		MM.getDocument().addDocumentListener(this);
		RR.getDocument().addDocumentListener(this);
		VXX.getDocument().addDocumentListener(this);
		VYY.getDocument().addDocumentListener(this);
	}
	
	//此方法清零每个文本框的内容
	private void clearTextField() {
		XX.setText("");
		YY.setText("");
		MM.setText("");
		RR.setText("");
		VXX.setText("");
		VYY.setText("");
	}
	
	//此方法判断每个文本框的输入是否为合法的
	private int isLegal() {
		int flag = 63;
		//flag是一个标志数，它的每一位都代表着一个输入的合法性
		//顺序为：XX,YY,MM,RR,VXX,VYY
		//初始化flag为  0011,1111 = 63 : 代表每一个位都是非法的
		//XX :        0001,1111 = 31
		//YY :        0010,1111 = 47
		//MM :        0011,0111 = 55
		//RR :        0011,1011 = 59
		//VXX :       0011,1101 = 61
		//VYY :       0011,1110 = 62
		//返回值等于0是全正常
		
		String xx = new String(XX.getText());
		String yy = new String(YY.getText());
		String mm = new String(MM.getText());
		String rr = new String(RR.getText());
		String vxx = new String(VXX.getText());
		String vyy = new String(VYY.getText());
		
		Matcher m1 = pattern.matcher(xx);
		Matcher m2 = pattern.matcher(yy);
		Matcher m3 = pattern.matcher(mm);
		Matcher m4 = pattern.matcher(rr);
		Matcher m5 = pattern.matcher(vxx);
		Matcher m6 = pattern.matcher(vyy);
		
		if(xx == null || xx.equals("")) {
			//空串不做处理，认为非法
		}
		else {
			if(m1.matches())
				flag = flag & 31;	//XX
		}
		
		if(yy == null || yy.contentEquals("")) {
			//
		}
		else {
			if(m2.matches())
				flag = flag & 47;	//YY
		}
		
		if(mm == null || mm.equals("")) {
			//
		}
		else {
			if(m3.matches())
				flag = flag & 55;	//MM
		}
		
		if(rr==null || rr.equals("")) {
			//
		}
		else {
			if(m4.matches())
				flag = flag & 59;	//RR
		}
		
		if(vxx==null || vxx.equals("")) {
			
		}
		else {
			if(m5.matches())
				flag = flag & 61;	//VXX
		}
		
		if(vyy==null || vyy.equals("")) {
			
		}
		else {
			if(m6.matches())
				flag = flag & 62;	//VYY
		}
		
		return flag;
	}
	//此方法处理非法的输入
	private void dealIlegal(int flag) {
		//遵守顺序:XX,YY,MM,RR,VXX,VYY
		int flag_xx = flag & 32;
		int flag_yy = flag & 16;
		int flag_mm = flag & 8;
		int flag_rr = flag & 4;
		int flag_vxx = flag & 2;
		int flag_vyy = flag & 1;
		
		System.out.println("deal");
		
		if(flag_xx != 0) {
			XX.setText("*");
			XX.setForeground(Color.RED);
		}
		if(flag_yy != 0)
		{
			YY.setText("*");
			YY.setForeground(Color.RED);
		}
		if(flag_mm != 0) {
			MM.setText("*");
			MM.setForeground(Color.RED);
		}
		if(flag_rr != 0) {
			RR.setText("*");
			RR.setForeground(Color.RED);
		}
		if(flag_vxx != 0) {
			VXX.setText("*");
			VXX.setForeground(Color.RED);
		}
		if(flag_vyy != 0) {
			VYY.setText("*");
			VYY.setForeground(Color.RED);
		}
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
		System.out.println("insert");
		if(!XX.getText().equals("*"))
			XX.setForeground(Color.BLACK);
		if(!YY.getText().equals("*"))
			YY.setForeground(Color.BLACK);
		if(!MM.getText().equals("*"))
			MM.setForeground(Color.BLACK);
		if(!RR.getText().equals("*"))
			RR.setForeground(Color.BLACK);
		if(!VXX.getText().equals("*"))
			VXX.setForeground(Color.BLACK);
		if(!VYY.getText().equals("*"))
			VYY.setForeground(Color.BLACK);
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
		System.out.println("remove");
		if(!XX.getText().equals("*"))
			XX.setForeground(Color.BLACK);
		if(!YY.getText().equals("*"))
			YY.setForeground(Color.BLACK);
		if(!MM.getText().equals("*"))
			MM.setForeground(Color.BLACK);
		if(!RR.getText().equals("*"))
			RR.setForeground(Color.BLACK);
		if(!VXX.getText().equals("*"))
			VXX.setForeground(Color.BLACK);
		if(!VYY.getText().equals("*"))
			VYY.setForeground(Color.BLACK);
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO 自动生成的方法存根
	}
	//传输数据
	private void transdata() {
		buffer.getInBX(Double.valueOf(XX.getText()));
		buffer.getInBY(Double.valueOf(YY.getText()));
		buffer.getInVXX(Double.valueOf(VXX.getText()));
		buffer.getInVYY(Double.valueOf(VYY.getText()));
		buffer.getInMM(Double.valueOf(MM.getText()));
		buffer.getInRR(Double.valueOf(RR.getText()));
		
		buffer.pourIn(ballname);
	}
	private void setExternalAvailable() {
		if(item1||item2||item3)
		{
			//
		}
		else
		{
			StartPanel.setButtonsAvailable();
			set.setEnabled(false);
		}
	}
	private void setRandomDataItem(String bname) {
		Random rr = new Random();
		int temp;
		switch(bname)
		{
		case "ball1" :
			temp = rr.nextInt(500);
			while(temp<=350||temp>=400)
			{
				temp = rr.nextInt(500);
			}
			buffer.getInBX(temp);
			while(temp<=400||temp>=450)
			{
				temp = rr.nextInt(500);
			}
			buffer.getInBY(temp);
			break;
		case "ball2" :
			temp = rr.nextInt(60);
			while(temp<=0||temp>=50)
			{
				temp = rr.nextInt(60);
			}
			buffer.getInBX(temp);
			while(temp<=350||temp>=400)
			{
				temp = rr.nextInt(500);
			}
			buffer.getInBY(temp);
			break;
		case "ball3" :
			temp = rr.nextInt(300);
			while(temp<=200||temp>=250)
			{
				temp = rr.nextInt(300);
			}
			buffer.getInBX(temp);
			while(temp<=0||temp>=50)
			{
				temp = rr.nextInt(60);
			}
			buffer.getInBY(temp);
			break;
		default:break;
		}
		
		buffer.getInMM(10000);
		
		temp = rr.nextInt(20);
		while(temp == 0)
		{
			temp = rr.nextInt(20);
		}
		buffer.getInRR(temp);
		
		buffer.getInVXX(Math.pow(-1, rr.nextInt())*rr.nextInt(2));
		buffer.getInVYY(Math.pow(-1, rr.nextInt())*rr.nextInt(2));
		System.out.println(bname+" : "+buffer.getBVXX()+" , "+buffer.getBVYY());
		buffer.pourIn(bname);
	}
}

//缓冲区类
class Buffer {
	private double buffer_XX;
	private double buffer_YY;
	private double buffer_VXX;
	private double buffer_VYY;
	private double buffer_MM;
	private double buffer_RR;
	
	public Buffer() {
		System.out.println("buffer is here");
	}
	
	public double getBX() {
		return buffer_XX;
	}
	public double getBY() {
		return buffer_YY;
	}
	public double getBVXX() {
		return buffer_VXX;
	}
	public double getBVYY() {
		return buffer_VYY;
	}
	public double getBMM() {
		return buffer_MM;
	}
	public double getBRR() {
		return buffer_RR;
	}
	public void getInBX(double x) {
		buffer_XX = x;
	}
	public void getInBY(double y) {
		buffer_YY = y;
	}
	public void getInVXX(double vx) {
		buffer_VXX = vx;
	}
	public void getInVYY(double vy) {
		buffer_VYY = vy;
	}
	public void getInMM(double m) {
		buffer_MM = m;
	}
	public void getInRR(double r) {
		buffer_RR = r;
	}
	public void pourIn(String name) {
		switch(name)
		{
		case "ball1" :
			DeliverMan.Switch1 = true;
			DeliverMan.B1X = buffer_XX;
			DeliverMan.B1Y = buffer_YY;
			DeliverMan.B1M = buffer_MM;
			DeliverMan.B1R = buffer_RR;
			DeliverMan.V1X = buffer_VXX;
			DeliverMan.V1Y = buffer_VYY;
			break;
		case "ball2" :
			DeliverMan.Switch2 = true;
			DeliverMan.B2X = buffer_XX;
			DeliverMan.B2Y = buffer_YY;
			DeliverMan.B2M = buffer_MM;
			DeliverMan.B2R = buffer_RR;
			DeliverMan.V2X = buffer_VXX;
			DeliverMan.V2Y = buffer_VYY;
			break;
		case "ball3" :
			DeliverMan.Switch3 = true;
			DeliverMan.B3X = buffer_XX;
			DeliverMan.B3Y = buffer_YY;
			DeliverMan.B3M = buffer_MM;
			DeliverMan.B3R = buffer_RR;
			DeliverMan.V3X = buffer_VXX;
			DeliverMan.V3Y = buffer_VYY;
			break;
		default:break;
		}
	}
}
