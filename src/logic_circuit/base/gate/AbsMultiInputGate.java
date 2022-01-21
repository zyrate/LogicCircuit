package logic_circuit.base.gate;
import java.util.ArrayList;
import logic_circuit.base.event.PortListener;
import logic_circuit.base.event.PortEvent;
import logic_circuit.base.port.Port;
/**
 * �������ų�����
 * ���Կ������ô����ӳ�ʱ��
 */
public abstract class AbsMultiInputGate implements MultiInputGate {
	protected ArrayList<Port> inPorts;	//����˿�
	private int nextIndex = 0;//�Զ���¼��һ���˿��±꣬������ֻ�ܽӣ������±�
	public Port outPort;	//����˿�
	private boolean isNot = false;		//�Ƿ�ת��
	private int delay = 0;//�����ӳ�ʱ��
	public AbsMultiInputGate(boolean transformTo) {//����������ת�����ͣ�falseΪX����
		init(2, transformTo);//Ĭ��Ϊ�����˿�
		addListener();
	}
	public AbsMultiInputGate(int n, boolean transformTo) {
		init(n, transformTo);
		addListener();
	}
	public AbsMultiInputGate(boolean transformTo, int delay) {//���ô����ӳ�
		this.delay = delay;
		init(2, transformTo);//Ĭ��Ϊ�����˿�
		addListener();
	}
	public AbsMultiInputGate(int n, boolean transformTo, int delay) {
		this.delay = delay;
		init(n, transformTo);
		addListener();
	}
	/**
	 * ע�������
	 * ��������ˣ�һ���ж˿ڱ仯�������̷�Ӧ
	 * �뵼����ϣ�ʵ�����ж˿ڵļ������Ӷ�ʵ������
	 */
	protected void addListener() {
		for(Port p : inPorts) {
			p.addPortListener(new PortListener() {
				@Override
				public void portAffected(PortEvent e) {
					if(e.getType().equals(PortEvent.SETV))
						react();
				}
			});
		}
	}
	/**
	 * ��Ҫ��д�ķ��� - �����߼�
	 * ʵ������Ҫ�����Լ��Ķ˿���������������� 0��1(�����Ƿ�)
	 * @return 0/1
	 */
	protected abstract int logic();
	/**
	 * 	�Ա仯�ķ�Ӧ
	 */
	protected void react() {
		int result = logic();
		//�����ӳ�
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) { e.printStackTrace(); }
		//���
		if(!isNot) {
			outPort.setV(result);
		}else {
			outPort.setV(result==1 ? 0 : 1);
		}
	}

	/**
	 * ��ʼ��
	 * @param n �˿���
	 * @param transformTo �Ƿ�ת��
	 */
	private void init(int n, boolean transformTo) {
		reset();
		isNot = !transformTo;
		//����˿���
		inPorts = new ArrayList<Port>(n);
		for(int i = 0; i < n; i++) {
			inPorts.add(new Port());
		}
	}


	@Override
	public void input(int ...ins) {
		if(this.inPorts == null)
			return;
		for(int i = 0; i < ins.length && i < inPorts.size(); i++) {
			inPorts.get(i).setVOnly(ins[i]);//����ȫ�����������ٷ�Ӧ��ģ��ͬʱ����
		}
		react();
	}

	@Override
	public void set(int index, int value) {
		if(this.inPorts == null)
			return;
		this.inPorts.get(index).setV(value);
	}
	

	@Override
	public Port inPort(int index) {
		nextIndex = index+1;
		return this.inPorts.get(index);
	}

	@Override
	public Port inPort(){
		if(nextIndex >= inPorts.size()){
            try {
                throw new Exception("�������� - ��ͼ�õ������ڵĶ˿ڣ�index:"+nextIndex);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
		}
		return this.inPorts.get(nextIndex++);
	}
	

	@Override
	public void reset() {
		this.inPorts = null;
		outPort = new Port();
	}
}
