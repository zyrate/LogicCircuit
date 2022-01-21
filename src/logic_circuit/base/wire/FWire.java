package logic_circuit.base.wire;

import logic_circuit.base.port.Port;
import logic_circuit.base.event.PortEvent;
import logic_circuit.base.event.PortListener;

/**
 * Flip-Wire
 * ��ת���ߣ������ڷ��Ź��ܣ��������ڲ��ԣ���ֹ�����������ڲ�
 * ��ѭ����ȫ�ĵ���
 */
public class FWire {
    private Port pA, pB;//���ߵ����� - ����������
    public FWire(Port pA, Port pB) {
        this.pA = pA;
        this.pB = pB;
        addListener();
    }
    //ע�������
    private void addListener() {
        pA.addPortListener(new PortListener() {
            @Override
            public void portAffected(PortEvent e) {
                if(e.getType().equals(PortEvent.SETV)) {
                    pB.setV(pA.getV() == 1 ? 0 : 1);//����
                }
            }
        });
    }
}
