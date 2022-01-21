package logic_circuit.base.gate;
import logic_circuit.base.port.Port;
/**
 * ����
 */
public class AndGate extends AbsMultiInputGate {
	public AndGate(boolean transformTo) {
		super(transformTo);
	}
	public AndGate(int n, boolean transformTo) {
		super(n, transformTo);
	}
	//���ô����ӳ�
	public AndGate(boolean transformTo, int delay) {
		super(transformTo, delay);
	}
	public AndGate(int n, boolean transformTo, int delay) {
		super(n, transformTo, delay);
	}
	//���߼�
	@Override
	protected int logic() {
        int result = 1;
        for(Port port : inPorts) {
            if(port.getV() != 1) {
                result = 0;
                break;
            }
        }
        return result;
	}
	
}
