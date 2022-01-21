package logic_circuit.base.wire;

import logic_circuit.base.port.Port;
import logic_circuit.base.event.PortEvent;
import logic_circuit.base.event.PortListener;

/**
 * Cycle-safe
 * ѭ����ȫ�ĵ��� - ��ֻ�������ú�B��״̬��֮ǰ��ͬ������²Ŵ��䣬������ѭ��
 */
public class CWire {
    private Port pA, pB;//���ߵ����� - ����������
    public CWire(Port pA, Port pB) {
        this.pA = pA;
        this.pB = pB;
        addListener();
    }
    //ע�������
    private void addListener() {
        pA.addPortListener(new PortListener() {
            @Override
            public void portAffected(PortEvent e) {
                if(e.getType().equals(PortEvent.SETV) && pB.getV() != pA.getV()) {//B�˿ڲ�����A�˿�ʱ
                    pB.setV(pA.getV());//����
                }
            }
        });
    }
}
