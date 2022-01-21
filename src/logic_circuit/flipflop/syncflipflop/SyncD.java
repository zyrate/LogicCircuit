package logic_circuit.flipflop.syncflipflop;

import logic_circuit.base.gate.NotGate;
import logic_circuit.base.port.Port;
import logic_circuit.base.wire.Wire;

/**
 * ͬ��D������
 * ��RS�Ļ����ϱ�����ͬʱΪ1�����
 * �ֳƸ�����
 */
public class SyncD {
    private SyncRS srs = new SyncRS();
    private NotGate not = new NotGate();
    private Port D = new Port("D");

    public SyncD(){
        new Wire(D, srs.getS());
        new Wire(D, not.inPort());
        new Wire(not.outPort, srs.getR());
    }

    public void input(int d){
        D.setV(d);
    }

    public Port getD() {
        return D;
    }
    public Port getCP() {
        return srs.getCP();
    }
    public Port getQ() {
        return srs.getQ();
    }
    public Port get_Q() {
        return srs.get_Q();
    }
}
