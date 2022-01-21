package logic_circuit.tools.decoder;

import logic_circuit.base.gate.AndGate;
import logic_circuit.base.gate.HubGate;
import logic_circuit.base.gate.NotGate;
import logic_circuit.base.gate.OrGate;
import logic_circuit.base.port.Port;
import logic_circuit.base.wire.Wire;


/**
 * 7����ʾ������ - ʧ�ܣ�- �ɹ���
 * _BIRBO������������˫�ع��ܣ�_BIΪ������룬_RBOΪ�������
 *
 * 1.һ������(�����Ȧ)�����������Ҳ��Ȧʱֻ�Ǳ�ʾ�͵�ƽ��Ч�����ù�����- ���Ǵ�ģ������������˼�Ȧ�����൱��û����ţ�
 * 2.������ʱ����ֻ������˼�Ȧ
 * 3.�ŷ���a,b,c,d,e,f,G��Щ�м����ŵ�����˶�û������ӣ�����ʾ�Ź�
 * 4.�ư��ˣ��ҿ����߼�ͼû���Ѿ�����
 * 5._RBO������ר��Ϊ��λ������ʾʹ�õģ��ɲ�����
 * 6._BIRBO��˫�ع��ܺ���ʵ�ֲ��ˣ���ֻ�������
 */
public class S7Decoder {
    private Port A0 = new Port("A0"),
                 A1 = new Port("A1"),
                 A2 = new Port("A2"),
                 A3 = new Port("A3"),
                 _LT = new Port("_LT"),
                 _BIRBO = new Port("_BIRBO"),
                 _RBI = new Port("_RBI"),
                 Ya = new Port("Ya"),
                 Yb = new Port("Yb"),
                 Yc = new Port("Yc"),
                 Yd = new Port("Yd"),
                 Ye = new Port("Ye"),
                 Yf = new Port("Yf"),
                 Yg = new Port("Yg");

    private NotGate g1 = new NotGate(),
                    g8 = new NotGate();

    private AndGate g3 = new AndGate(6, false), //g3����Ϊ�˸�_BIRBO�˿������
                    g5 = new AndGate(false),
                    g6 = new AndGate(false),
                    g7 = new AndGate(false),
                    g9 = new AndGate(false),
                    g10 = new AndGate(false),
                    g11 = new AndGate(false),
                    g12 = new AndGate(false),
                    a1 = new AndGate(true),
                    a2 = new AndGate(true),
                    a3 = new AndGate(4,true),
                    b1 = new AndGate(true),
                    b2 = new AndGate(3,true),
                    b3 = new AndGate(3,true),
                    c1 = new AndGate(true),
                    c2 = new AndGate(3,true),
                    d1 = new AndGate(3,true),
                    d2 = new AndGate(3,true),
                    d3 = new AndGate(3,true),
                    e = new AndGate(true),
                    f1 = new AndGate(true),
                    f2 = new AndGate(true),
                    f3 = new AndGate(3,true),
                    G1 = new AndGate(3,true),
                    G2 = new AndGate(4,true);

    private OrGate g13 = new OrGate(3,false),
                    g14 = new OrGate(3,false),
                    g15 = new OrGate(false),
                    g16 = new OrGate(3,false),
                    g17 = new OrGate(false),
                    g18 = new OrGate(3,false),
                    g19 = new OrGate(false);

    //����Ҫ������
    //private HubGate h = new HubGate(false);

    public S7Decoder(){
        new Wire(A0, g5.inPort());
        new Wire(A1, g6.inPort());
        new Wire(A2, g7.inPort());
        new Wire(A3, g8.inPort());
        //new Wire(g3.outPort, _BIRBO);
        new Wire(_BIRBO, g9.inPort());
        new Wire(_BIRBO, g10.inPort());
        new Wire(_BIRBO, g11.inPort());
        new Wire(_BIRBO, g12.inPort());
        new Wire(_LT, g3.inPort());
        new Wire(_LT, g5.inPort());
        new Wire(_LT, g6.inPort());
        new Wire(_LT, g7.inPort());
        new Wire(_LT, G2.inPort());
        new Wire(_RBI, g1.inPort());
        new Wire(g1.outPort, g3.inPort());
        new Wire(g5.outPort, g9.inPort());
        new Wire(g6.outPort, g10.inPort());
        new Wire(g7.outPort, g11.inPort());
        new Wire(g8.outPort, g12.inPort());

        new Wire(g5.outPort, a2.inPort());
        new Wire(g5.outPort, b3.inPort());
        new Wire(g5.outPort, c2.inPort());
        new Wire(g5.outPort, d2.inPort());
        new Wire(g5.outPort, g3.inPort());

        new Wire(g6.outPort, a3.inPort());
        new Wire(g6.outPort, b2.inPort());
        new Wire(g6.outPort, d1.inPort());
        new Wire(g6.outPort, d2.inPort());
        new Wire(g6.outPort, e.inPort());
        new Wire(g6.outPort, g3.inPort());
        new Wire(g6.outPort, G2.inPort());

        new Wire(g7.outPort, a3.inPort());
        new Wire(g7.outPort, c2.inPort());
        new Wire(g7.outPort, d1.inPort());
        new Wire(g7.outPort, g3.inPort());
        new Wire(g7.outPort, f2.inPort());
        new Wire(g7.outPort, f3.inPort());
        new Wire(g7.outPort, G2.inPort());

        new Wire(g8.outPort, a3.inPort());
        new Wire(g8.outPort, g3.inPort());
        new Wire(g8.outPort, f3.inPort());
        new Wire(g8.outPort, G2.inPort());

        new Wire(g9.outPort, a3.inPort());
        new Wire(g9.outPort, b2.inPort());
        new Wire(g9.outPort, d1.inPort());
        new Wire(g9.outPort, d3.inPort());
        new Wire(g9.outPort, g17.inPort());//����ʡ����һ������������
        new Wire(g9.outPort, f1.inPort());
        new Wire(g9.outPort, f3.inPort());
        new Wire(g9.outPort, G1.inPort());

        new Wire(g10.outPort, a1.inPort());
        new Wire(g10.outPort, b1.inPort());
        new Wire(g10.outPort, b3.inPort());
        new Wire(g10.outPort, c2.inPort());
        new Wire(g10.outPort, d3.inPort());
        new Wire(g10.outPort, f1.inPort());
        new Wire(g10.outPort, f2.inPort());
        new Wire(g10.outPort, G1.inPort());

        new Wire(g11.outPort, a2.inPort());
        new Wire(g11.outPort, b2.inPort());
        new Wire(g11.outPort, b3.inPort());
        new Wire(g11.outPort, c1.inPort());
        new Wire(g11.outPort, d2.inPort());
        new Wire(g11.outPort, d3.inPort());
        new Wire(g11.outPort, e.inPort());
        new Wire(g11.outPort, G1.inPort());

        new Wire(g12.outPort, a1.inPort());
        new Wire(g12.outPort, b1.inPort());
        new Wire(g12.outPort, c1.inPort());

        //��������������������
        new Wire(a1.outPort, g13.inPort());
        new Wire(a2.outPort, g13.inPort());
        new Wire(a3.outPort, g13.inPort());
        new Wire(b1.outPort, g14.inPort());
        new Wire(b2.outPort, g14.inPort());
        new Wire(b3.outPort, g14.inPort());
        new Wire(c1.outPort, g15.inPort());
        new Wire(c2.outPort, g15.inPort());
        new Wire(d1.outPort, g16.inPort());
        new Wire(d2.outPort, g16.inPort());
        new Wire(d3.outPort, g16.inPort());
        new Wire(e.outPort, g17.inPort());
        new Wire(f1.outPort, g18.inPort());
        new Wire(f2.outPort, g18.inPort());
        new Wire(f3.outPort, g18.inPort());
        new Wire(G1.outPort, g19.inPort());
        new Wire(G2.outPort, g19.inPort());

        //���
        new Wire(g13.outPort, Ya);
        new Wire(g14.outPort, Yb);
        new Wire(g15.outPort, Yc);
        new Wire(g16.outPort, Yd);
        new Wire(g17.outPort, Ye);
        new Wire(g18.outPort, Yf);
        new Wire(g19.outPort, Yg);

    }

    public void input(int a3, int a2, int a1, int a0, int _lt, int _rbi, int _birbo){
        A0.setV(a0);
        A1.setV(a1);
        A2.setV(a2);
        A3.setV(a3);
        _BIRBO.setV(_birbo);
        _LT.setV(_lt);
        _RBI.setV(_rbi);
    }

    public Port getA0() {
        return A0;
    }

    public Port getA1() {
        return A1;
    }

    public Port getA2() {
        return A2;
    }

    public Port getA3() {
        return A3;
    }

    public Port get_LT() {
        return _LT;
    }

    public Port get_BIRBO() {
        return _BIRBO;
    }

    public Port get_RBI() {
        return _RBI;
    }

    public Port getYa() {
        return Ya;
    }

    public Port getYb() {
        return Yb;
    }

    public Port getYc() {
        return Yc;
    }

    public Port getYd() {
        return Yd;
    }

    public Port getYe() {
        return Ye;
    }

    public Port getYf() {
        return Yf;
    }

    public Port getYg() {
        return Yg;
    }
}
