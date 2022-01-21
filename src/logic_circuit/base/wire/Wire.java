package logic_circuit.base.wire;

import logic_circuit.base.port.Port;
import logic_circuit.base.event.PortEvent;
import logic_circuit.base.event.PortListener;

/**
 * ���� - ���Ӷ˿�
 * ���ߵ���Ҫ������ΪA�˿���Ӽ��������˺�ֻҪA�˿ڱ仯����B�˿����̱仯
 * Ŀǰ��CWire������ͬ����ʱ��ȡ��CWire��������CWireΪѭ���ı�ʶ
 */
/**
 * Cycle-safe
 * ѭ����ȫ�ĵ��� - ��ֻ�������ú�B��״̬��֮ǰ��ͬ������²Ŵ��䣬������ѭ��
 */
public class Wire {
    private Port pA, pB;//���ߵ����� - ����������
    public Wire(Port pA, Port pB) {
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