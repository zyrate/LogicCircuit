package logic_circuit.tools.showpanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import logic_circuit.base.port.Port;

/**
 * ģ�⽻���ź�����
 */
class SignalArea extends JScrollPane{
	private int sleepTime;
	private HashMap<JCheckBox, Thread> signalThreads = new HashMap<>();
	private HashMap<JCheckBox, Port> ports = new HashMap<>();
	private JPanel main;
	private boolean on = false;//ͨ��
	private boolean input = true;//�Ƿ��������
	int hight = 110;
	
	public SignalArea(int sleepTime, String name) {
		this.sleepTime = sleepTime;
		this.setBorder(BorderFactory.createTitledBorder(name));
		
		main = new JPanel();
		main.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension(135, hight));
	
        this.setViewportView(main);
        this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
        this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	public void addPorts(Port ...ports) {
		for(Port port:ports) {
			JCheckBox check = new JCheckBox(port.getName());
			this.main.add(check);
			this.ports.put(check, port);//���뵽�˿ڱ���
			check.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					chosen(check);//��һ��������
				}
			});
		}
		main.setPreferredSize(new Dimension(40*ports.length/2, hight));//ʵʱ�仯
	}
	//ѡ��֮��
	private void chosen(JCheckBox check) {
		if(!on) {// - ��Դ��ʱ
			if(check.isSelected())
				signalThreads.put(check, null);//�źŶ˿����̶߳�Ӧ
			else
				signalThreads.remove(check);//ͨ����ķ�ʽ���Ƴ�ȡ���ļ�ֵ��
			return;
		}
		// - ��Դ��ʱ
		if(check.isSelected()){
			//Ҫ��
			Thread signal = new Thread() {
				@Override
				public void run() {
					Port temp = ports.get(check);
					while(true) {
						if(input)
							temp.setV(temp.getV()==1?0:1);//����
						else{
							//ֻ��ʾ�ƣ�������
							temp.setVOnly(temp.getV()==1?0:1);
						}
						try {Thread.sleep(sleepTime);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
				}
			};
			signalThreads.put(check, signal);//�źŶ˿����̶߳�Ӧ
			signal.start();
		}else {
			//Ҫ��
			shutdown(check);
			signalThreads.remove(check);//ͨ����ķ�ʽ���Ƴ�ȡ���ļ�ֵ��
		}
	}
	//�ر��ź�
	private void shutdown(JCheckBox check){
		Thread signal = signalThreads.get(check);//�õ��������е��߳�
		signal.stop();
		if(input)
			ports.get(check).setV(0);//��Ϊ0
		else
			ports.get(check).setVOnly(0);
		//��ȡ����ֵ��
	}

	//�������
	public void doInput(boolean input){
		this.input = input;
	}
	
	//���� - ����ȡ��
	public void ON() {
		this.on = true;

		for(Map.Entry<JCheckBox, Thread> e : signalThreads.entrySet()) {
			chosen(e.getKey());
		}
	}
	public void OFF() {
		this.on = false;

		for(Map.Entry<JCheckBox, Thread> e : signalThreads.entrySet()) {
			shutdown(e.getKey());
		}
	}
	//ȫ��ȡ��
    public void cancelAll(){
        for(Map.Entry<JCheckBox, Port> e : ports.entrySet()) {
            if(e.getKey().isSelected()){
                chosen(e.getKey());
                e.getKey().setSelected(false);
            }
        }
    }
}




