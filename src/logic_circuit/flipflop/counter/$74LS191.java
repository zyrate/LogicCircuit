package logic_circuit.flipflop.counter;

import com.sun.org.apache.xpath.internal.operations.Or;
import logic_circuit.base.gate.AndGate;
import logic_circuit.base.gate.NotGate;
import logic_circuit.base.gate.OrGate;
import logic_circuit.base.port.Port;
import logic_circuit.base.wire.FWire;
import logic_circuit.base.wire.Wire;
import logic_circuit.flipflop.edgeflipflop.EdgeJK;

/**
 * 4位二进制同步加/减法器
 * futurlec.com的逻辑图中_CE是_CT，_PL是_LD
 * 所有功能完美实现，各端口功能见教材
 */
public class $74LS191 {
    private Port D0 = new Port("D0"),
                 D1 = new Port("D1"),
                 D2 = new Port("D2"),
                 D3 = new Port("D3"),
                 Q0 = new Port("Q0"),
                 Q1 = new Port("Q1"),
                 Q2 = new Port("Q2"),
                 Q3 = new Port("Q3"),
                 _CT = new Port("_CT"),
                 _UD = new Port("_UD"),
                 _RC = new Port("_RC"),
                 _LD = new Port("_LD"),
                 CP = new Port("CP"),
                 COBO = new Port("COBO");
    private NotGate n1 = new NotGate(),
                    n2 = new NotGate(),
                    n3 = new NotGate(),
                    n4 = new NotGate(),
                    n5 = new NotGate();
    private AndGate a1 = new AndGate(true),
                    a2 = new AndGate(true),
                    a3 = new AndGate(5,true),
                    a4 = new AndGate(5,true),
                    a5 = new AndGate(false),
                    a6 = new AndGate(false),
                    a7 = new AndGate(true),
                    a8 = new AndGate(true),
                    a9 = new AndGate(false),
                    a10 = new AndGate(3,true),
                    a11 = new AndGate(3,true),
                    a12 = new AndGate(false),
                    a13 = new AndGate(4,true),
                    a14 = new AndGate(4,true),
                    a15 = new AndGate(false),
                    a16 = new AndGate(false),
                    a17 = new AndGate(false),
                    a18 = new AndGate(false),
                    a19 = new AndGate(3,false);
    private OrGate o1 = new OrGate(true),
                   o2 = new OrGate(true),
                   o3 = new OrGate(true),
                   o4 = new OrGate(true);
    private EdgeJK jk1 = new EdgeJK(),
                   jk2 = new EdgeJK(),
                   jk3 = new EdgeJK(),
                   jk4 = new EdgeJK();

    public $74LS191(){
        //从上到下一层一层接
        new Wire(CP, n1.inPort());
        new Wire(_UD, n2.inPort());
        new Wire(n1.outPort, a19.inPort());
        new Wire(n1.outPort, jk1.getCP());
        new Wire(n1.outPort, jk2.getCP());
        new Wire(n1.outPort, jk3.getCP());
        new Wire(n1.outPort, jk4.getCP());
        new Wire(n2.outPort, n4.inPort());
        new Wire(n2.outPort, a3.inPort());
        new FWire(n2.outPort, a2.inPort());//为了方便输入端加圈，用了翻转导线
        new Wire(n4.outPort, a4.inPort());
        new FWire(n4.outPort, a1.inPort());
        new FWire(_CT, a1.inPort());
        new FWire(_CT, a2.inPort());
        new Wire(_CT, n5.inPort());

        new Wire(D0, a5.inPort());
        new Wire(D1, a6.inPort());
        new Wire(D2, a9.inPort());
        new Wire(D3, a12.inPort());
        new Wire(_LD, n3.inPort());

        new Wire(a1.outPort, a8.inPort());
        new Wire(a1.outPort, a11.inPort());
        new Wire(a1.outPort, a14.inPort());
        new Wire(a2.outPort, a7.inPort());
        new Wire(a2.outPort, a10.inPort());
        new Wire(a2.outPort, a13.inPort());
        new Wire(n3.outPort, a5.inPort());
        new Wire(n3.outPort, a15.inPort());
        new Wire(n3.outPort, a6.inPort());
        new Wire(n3.outPort, a16.inPort());
        new Wire(n3.outPort, a9.inPort());
        new Wire(n3.outPort, a17.inPort());
        new Wire(n3.outPort, a12.inPort());
        new Wire(n3.outPort, a18.inPort());

        new Wire(a3.outPort, o1.inPort());
        new Wire(a4.outPort, o1.inPort());
        new Wire(a5.outPort, a15.inPort());
        new Wire(a5.outPort, jk1.get_Sd());//不知是Sd还是Rd
        new Wire(n5.outPort, jk1.getJ());
        new Wire(n5.outPort, jk1.getK());
        new Wire(n5.outPort, a19.inPort());
        new Wire(a6.outPort, a16.inPort());//以下相似
        new Wire(a6.outPort, jk2.get_Sd());
        new Wire(a7.outPort, o2.inPort());
        new Wire(a8.outPort, o2.inPort());
        new Wire(a9.outPort, a17.inPort());
        new Wire(a9.outPort, jk3.get_Sd());
        new Wire(a10.outPort, o3.inPort());
        new Wire(a11.outPort, o3.inPort());
        new Wire(a12.outPort, a18.inPort());
        new Wire(a12.outPort, jk4.get_Sd());
        new Wire(a13.outPort, o4.inPort());
        new Wire(a14.outPort, o4.inPort());

        new Wire(o1.outPort, a19.inPort());
        new Wire(o1.outPort, COBO);
        new Wire(a15.outPort, jk1.get_Rd());
        new Wire(o2.outPort, jk2.getJ());
        new Wire(o2.outPort, jk2.getK());
        new Wire(a16.outPort, jk2.get_Rd());
        new Wire(o3.outPort, jk3.getJ());
        new Wire(o3.outPort, jk3.getK());
        new Wire(a17.outPort, jk3.get_Rd());
        new Wire(o4.outPort, jk4.getJ());
        new Wire(o4.outPort, jk4.getK());
        new Wire(a18.outPort, jk4.get_Rd());

        new Wire(jk1.getQ(), Q0);
        new Wire(jk1.getQ(), a3.inPort());
        new Wire(jk1.getQ(), a8.inPort());
        new Wire(jk1.getQ(), a11.inPort());
        new Wire(jk1.getQ(), a14.inPort());
        new Wire(jk1.get_Q(), a4.inPort());
        new Wire(jk1.get_Q(), a7.inPort());
        new Wire(jk1.get_Q(), a10.inPort());
        new Wire(jk1.get_Q(), a13.inPort());

        new Wire(jk2.getQ(), Q1);
        new Wire(jk2.getQ(), a3.inPort());
        new Wire(jk2.getQ(), a11.inPort());
        new Wire(jk2.getQ(), a14.inPort());
        new Wire(jk2.get_Q(), a4.inPort());
        new Wire(jk2.get_Q(), a10.inPort());
        new Wire(jk2.get_Q(), a13.inPort());

        new Wire(jk3.getQ(), Q2);
        new Wire(jk3.getQ(), a3.inPort());
        new Wire(jk3.getQ(), a14.inPort());
        new Wire(jk3.get_Q(), a4.inPort());
        new Wire(jk3.get_Q(), a13.inPort());

        new Wire(jk4.getQ(), Q3);
        new Wire(jk4.getQ(), a3.inPort());
        new Wire(jk4.get_Q(), a4.inPort()); //越来越少

    }

    public Port getD0() {
        return D0;
    }

    public Port getD1() {
        return D1;
    }

    public Port getD2() {
        return D2;
    }

    public Port getD3() {
        return D3;
    }

    public Port getQ0() {
        return Q0;
    }

    public Port getQ1() {
        return Q1;
    }

    public Port getQ2() {
        return Q2;
    }

    public Port getQ3() {
        return Q3;
    }

    public Port get_CT() {
        return _CT;
    }

    public Port get_UD() {
        return _UD;
    }

    public Port get_RC() {
        return _RC;
    }

    public Port get_LD() {
        return _LD;
    }

    public Port getCP() {
        return CP;
    }

    public Port getCOBO() {
        return COBO;
    }
}
