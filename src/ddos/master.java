package ddos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class master extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField adr;
	private JTextField Port;
	JButton att;
	JButton stop;
	JLabel status;
	private JTextField ptime;
	private JLabel lblNewLabel;
	server s;
	JButton end;
	public master() {
		setVisible(true);
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);
		s = new server();
		new Thread(s).start();
		adr = new JTextField("127.0.0.1");
		adr.setColumns(10);

		att = new JButton("atack");

		Port = new JTextField("7777");
		Port.setColumns(10);

		stop = new JButton("stop");

		JLabel lblAddress = new JLabel("address");

		JLabel lblPort = new JLabel("port");

		status = new JLabel("status");

		ptime = new JTextField("100");
		ptime.setColumns(10);

		lblNewLabel = new JLabel("Time");
		att.addActionListener(this);
		stop.addActionListener(this);
		
		end = new JButton("end");
		end.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(adr, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(Port, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(ptime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(att)
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(stop)
									.addGap(38))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(end)
									.addContainerGap())))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblAddress)
							.addContainerGap(430, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblPort)
							.addContainerGap(458, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(status)
							.addContainerGap(442, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblAddress)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(adr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(att))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(9)
							.addComponent(lblPort))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(stop)
						.addComponent(ptime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(end)
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addComponent(status)
					.addGap(30))
		);
		getContentPane().setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == stop) {
			s.end=false;
			s.stop();
			status.setText("Status : Stop");
			
		}
		else
			if(e.getSource() == att)
		{
				s.end=false;
			String host=adr.getText();
			
			if(host.length()==0)
			{
				adr.setText("nhap lai");
				return ;
			}
			String tme=ptime.getText();
			if(tme.length()==0)
			{
				ptime.setText("nhap lai");
				return ;
			}
			String por=Port.getText();
			if(por.length()==0)
			{
				Port.setText("nhap lai");
				return ;
			}
			s.set(host, Integer.parseInt(tme), Integer.parseInt(por));
			status.setText("Status : Attack");
		}
			else if(e.getSource() == end)
			{
				s.end=true;
				status.setText("Status : End");
			}

	}

	public static void main(String[] args) {
		new master();
	}
}
