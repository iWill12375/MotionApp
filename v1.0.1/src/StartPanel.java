package main_graph;
/**
 * @author zjc
 * This class is responsible for starting
 * up the simulation.
 */
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel{
	
	protected static JButton start;
	protected static JButton pause;
	protected boolean isinitial = false;
	protected boolean isoff = false;
	
	public StartPanel() {
		
		start = new JButton("START");
		pause = new JButton("PAUSE");
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5,10,5,10);
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		start.setEnabled(false);
		pause.setEnabled(false);
		this.add(start,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(pause,gbc);
	}
	public static void setButtonsAvailable() {
		start.setEnabled(true);
	}
}
