package main_graph;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ControlPanel extends JPanel {
	private JTextField locationX_1;
	private JTextField locationY_1;
	private JTextField speedX_1;
	private JTextField speedY_1;
	private JTextField locationX_2;
	private JTextField locationY_2;
	private JTextField speedX_2;
	private JTextField speedY_2;
	private JLabel tag1;
	private JLabel tag2;
	private JLabel tag3;
	private JLabel tag4;
	private JLabel tag5;
	private JLabel tag6;
	private JLabel tag7;
	private JLabel tag8;
	public ControlPanel() {
		TitledBorder bt = BorderFactory.createTitledBorder("argus");
		bt.setTitleColor(Color.darkGray);
		bt.setTitleJustification(TitledBorder.TOP);
		this.setBorder(bt);
		this.setBackground(Color.lightGray);
		locationX_1 = new JTextField("0",5);
		locationX_1.setEditable(false);
		locationY_1 = new JTextField("0",5);
		locationY_1.setEditable(false);
		speedX_1 = new JTextField("0",5);
		speedX_1.setEditable(false);
		speedY_1 = new JTextField("0",5);
		speedY_1.setEditable(false);
		locationX_2 = new JTextField("0",5);
		locationX_2.setEditable(false);
		locationY_2 = new JTextField("0",5);
		locationY_2.setEditable(false);
		speedX_2 = new JTextField("0",5);
		speedX_2.setEditable(false);
		speedY_2 = new JTextField("0",5);
		speedY_2.setEditable(false);
		tag1 = new JLabel("Location X:");
		tag2 = new JLabel("Location Y:");
		tag3 = new JLabel("Speed X:");
		tag4 = new JLabel("Speed Y:");
		tag5 = new JLabel("Location X:");
		tag6 = new JLabel("Location Y:");
		tag7 = new JLabel("Speed X:");
		tag8 = new JLabel("Speed Y:");
		GridLayout gd = new GridLayout(2,8,5,5);
		this.setLayout(gd);
		add(tag1);
		add(locationX_1);
		add(tag3);
		add(speedX_1);
		add(tag5);
		add(locationX_2);
		add(tag7);
		add(speedX_2);
		add(tag2);
		add(locationY_1);
		add(tag4);
		add(speedY_1);
		add(tag6);
		add(locationY_2);
		add(tag8);
		add(speedY_2);
	}
	public void setContent(Ball b1,Ball b2) {
		String temp;
		temp = String.format("%.2f",Double.valueOf(b1.getLocation().getX()));
		locationX_1.setText(temp);
		temp = String.format("%.2f",Double.valueOf(b1.getLocation().getY()));
		locationY_1.setText(temp);
		temp = String.format("%.2f",Double.valueOf(b2.getLocation().getX()));
		locationX_2.setText(temp);
		temp = String.format("%.2f",Double.valueOf(b2.getLocation().getY()));
		locationY_2.setText(temp);
		temp = String.format("%.2f",Double.valueOf(b1.getSpeed().getX()));
		speedX_1.setText(temp);
		temp = String.format("%.2f",Double.valueOf(b1.getSpeed().getY()));
		speedY_1.setText(temp);
		temp = String.format("%.2f",Double.valueOf(b2.getSpeed().getX()));
		speedX_2.setText(temp);
		temp = String.format("%.2f",Double.valueOf(b2.getSpeed().getY()));
		speedY_2.setText(temp);
		//repaint();
	}
	public void setL1(String str)
	{
		locationX_1.setText(str);
	}
}
