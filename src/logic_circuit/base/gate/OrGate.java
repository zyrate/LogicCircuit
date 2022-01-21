package logic_circuit.base.gate;

import logic_circuit.base.port.Port;

/**
 * ����
 */
public class OrGate extends AbsMultiInputGate {
	
	public OrGate(boolean transformTo) {
		super(transformTo);
	}
	public OrGate(int n, boolean transformTo) {
		super(n, transformTo);
	}
	//���ô����ӳ�
	public OrGate(boolean transformTo, int delay) {
		super(transformTo, delay);
	}
	public OrGate(int n, boolean transformTo, int delay) {
		super(n, transformTo, delay);
	}
	//���߼�
	@Override
	protected int logic() {
		int result = 0;
		for(Port port : inPorts) {
			if(port.getV() == 1) {
				result = 1;
				break;
			}
		}
		return result;
	}
	
}
