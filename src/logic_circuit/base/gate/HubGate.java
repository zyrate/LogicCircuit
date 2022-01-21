package logic_circuit.base.gate;

import logic_circuit.base.event.PortEvent;
import logic_circuit.base.event.PortListener;
import logic_circuit.base.port.Port;

/**
 * ������
 * �����ǰѶ������ϲ�Ϊһ�����룬�ĸ��˿ڱ䶯�ʹ����ĸ��ź�
 */
public class HubGate extends AbsMultiInputGate {
    private int out;

    public HubGate(boolean transformTo) {
        super(transformTo);
    }
    public HubGate(int n, boolean transformTo) {
        super(n, transformTo);
    }
    public HubGate(boolean transformTo, int delay) {
        super(transformTo, delay);
    }
    public HubGate(int n, boolean transformTo, int delay) {
        super(n, transformTo, delay);
    }

    protected void addListener() {
        for(Port p : inPorts) {
            p.addPortListener(new PortListener() {
                @Override
                public void portAffected(PortEvent e) {
                    if(e.getType().equals(PortEvent.SETV)) {
                        out = p.getV();
                        react();
                    }
                }
            });
        }
    }

    @Override
    protected int logic() {
        return out;
    }
}
