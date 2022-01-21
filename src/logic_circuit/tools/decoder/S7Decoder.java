package logic_circuit.tools.decoder;

import logic_circuit.base.gate.AndGate;
import logic_circuit.base.gate.HubGate;
import logic_circuit.base.gate.NotGate;
import logic_circuit.base.gate.OrGate;
import logic_circuit.base.port.Port;
import logic_circuit.base.wire.Wire;


/**
 * 7段显示译码器 - 失败！- 成功！
 * _BIRBO具有输入和输出双重功能，_BI为灭灯输入，_RBO为灭零输出
 *
 * 1.一个非门(输出加圈)，当其输入端也加圈时只是表示低电平有效，不用管它。- 这是错的！！！非门两端加圈就是相当于没这个门！
 * 2.非门有时候是只在输入端加圈
 * 3.才发现a,b,c,d,e,f,G这些中间与门的输出端都没有往后接，能显示才怪
 * 4.破案了，我看的逻辑图没错，已经好了
 * 5._RBO好像是专门为多位数字显示使用的，可不管他
 * 6._BIRBO的双重功能好像实现不了，就只管输入吧
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

    private AndGate g3 = new AndGate(6, false), //g3就是为了给_BIRBO端口输出的
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

    //不需要集线门
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
        new Wire(g9.outPort, g17.inPort());//这里省略了一个单输入与门
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

        //与或非门忘记连成整体了
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

        //输出
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
