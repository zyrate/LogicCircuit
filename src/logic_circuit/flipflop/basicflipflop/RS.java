package logic_circuit.flipflop.basicflipflop;

import logic_circuit.base.gate.AndGate;
import logic_circuit.base.port.Port;
import logic_circuit.base.wire.CWire;
import logic_circuit.base.wire.Wire;

/**
 * ����RS������
 * �����ϲ��������˶���0������ʵ��Ҳһ�����̲�д����������˴�0ͬʱ��Ϊ1�󴥷���״̬���ȶ���
 * ��ʵ֤��ȷʵ��ˣ�
 */
public class RS {
    private Port Q = new Port("Q"),
                 _Q = new Port("_Q"),
                 _Sd = new Port("_Sd"),
                 _Rd = new Port("_Rd");
    private AndGate andNot1 = new AndGate(2, false),
                    andNot2 = new AndGate(2, false);

    public RS(){
        new Wire(_Sd, andNot1.inPort(0));
        new Wire(_Rd, andNot2.inPort(0));
        new CWire(andNot1.outPort, andNot2.inPort(1));//����Ҫ�ô��䰲ȫ�ĵ���
        new CWire(andNot2.outPort, andNot1.inPort(1));
        new Wire(andNot1.outPort, Q);
        new Wire(andNot2.outPort, _Q);
    }

    public void input(int _sd, int _rd){
        _Sd.setV(_sd);
        _Rd.setV(_rd);
    }

    public Port getQ() {
        return Q;
    }

    public Port get_Q() {
        return _Q;
    }

    public Port get_Sd() {
        return _Sd;
    }

    public Port get_Rd() {
        return _Rd;
    }
}
