package logic_circuit.base.port;

import java.util.ArrayList;

import logic_circuit.base.event.PortEvent;
import logic_circuit.base.event.PortListener;
import logic_circuit.tools.util.ThreadPool;

/**
 * �˿�
 */
public class Port {
	private int v = -1;
	private String name = "";
	private ArrayList<PortListener> listeners = new ArrayList<>();
	private boolean stateChage = false;//״̬�Ƿ�仯

	public Port() {}
	public Port(String name) {
		this.name = name;
	}
	public Port(int v) {
		setV(v);
	}
	public Port(int v, String name) {
		this.name = name;
		setV(v);
	}

    /**
     * Ϊ�˿���Ӽ�����
     * @param listener �˿ڼ�����
     */
	public void addPortListener(PortListener listener) {
		this.listeners.add(listener);
	}

	//set��������ͬ��
	public synchronized void setV(int v) {
		if(v == 1 || v == 0 || v == -1) {//-1����ϵ�
            stateChage = this.v == v ? false : true;
			this.v = v;
			//�㲥������
			for(PortListener listener : listeners) {
				//ÿ�ι㲥���ǵ������߳�
				ThreadPool.newThread(new Runnable() {
					public void run() {
						listener.portAffected(new PortEvent(PortEvent.SETV, stateChage));
					}
				});
			}
				
		}
	}
	//������ķ���
	public synchronized void setVOnly(int v) {
		if(v == 1 || v == 0 || v == -1) {
            stateChage = this.v == v ? false : true;
			this.v = v;
			//�㲥������
			for(PortListener listener : listeners) {
				//ÿ�ι㲥���ǵ������߳�
				ThreadPool.newThread(new Runnable() {
					public void run() {
						listener.portAffected(new PortEvent(PortEvent.SETV_ONLY, stateChage));
					}
				});
			}
		}
	}
	public int getV() {
		return this.v;
	}

	@Override
	public String toString() {
		return name + ":" + getV();
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

