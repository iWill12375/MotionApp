package main_graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntegratePanel extends Thread {
	protected SuperPanel mp;
	protected StartPanel sp;
	protected SailingControl ssc;
	private String halt = "Halt";
	private boolean suspend;
	protected ControlPanel cp;
	
	public IntegratePanel() {
		mp = new SuperPanel();
		sp = new StartPanel();
		suspend = false;
		cp = new ControlPanel();
		
		sp.start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(!sp.isinitial) {
					ssc = new SailingControl();
				}
				if(sp.isoff) {
					sp.isoff = !sp.isoff;
					
				}
				setUp();
				sp.pause.setEnabled(true);
				sp.start.setEnabled(false);
			}
			//set response to clicking start button
		});
		sp.pause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(!sp.isoff) {
					sp.isoff = !sp.isoff;
					Halt();
					sp.start.setEnabled(true);
					sp.pause.setEnabled(false);
				}
			}
		});
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(halt) {
				if(!suspend)
				{
					try {
						halt.wait();
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				else {
					/*Your code set up the running*/
					try {
						ssc.runningMotion();
						cp.setContent(ssc.ball1, ssc.ball2, ssc.ball3);
						mp.repaint();
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}
	}
	public void setUp() {
		suspend = true;
		synchronized(halt) {
			halt.notifyAll();
		}
	}
	public void Halt() {
		suspend = false;
	}
}
