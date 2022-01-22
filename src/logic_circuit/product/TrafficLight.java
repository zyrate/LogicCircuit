package logic_circuit.product;

import logic_circuit.base.event.PortEvent;
import logic_circuit.base.event.PortListener;
import logic_circuit.base.gate.AndGate;
import logic_circuit.base.gate.NotGate;
import logic_circuit.base.port.Port;
import logic_circuit.base.wire.Wire;
import logic_circuit.flipflop.counter.$74LS191;
import logic_circuit.flipflop.register.$74LS194;
import logic_circuit.tools.decoder.S7Panel;
import logic_circuit.tools.showpanel.ShowPanel;
import logic_circuit.tools.util.CompFactory;

import javax.swing.*;
import java.awt.*;

/**
 * 红绿灯
 */
public class TrafficLight extends JFrame {
    private JButton bRed, bYellow, bGreen;
    private Color red = new Color(224, 20, 40);
    private Color yellow = new Color(237, 201, 12);
    private Color green = new Color(5, 211, 52);
    private Color gray = new Color(168, 168, 168);

    public Port pRed, pYellow, pGreen, pCp;
    private NotGate n1 = new NotGate(),
                    n2 = new NotGate(),
                    n3 = new NotGate(),
                    n4 = new NotGate(),
                    n5 = new NotGate();
    private AndGate a1 = new AndGate(true),
                    a2 = new AndGate(true);
    public $74LS194 ls1 = new $74LS194(),
                     ls2 = new $74LS194();
    public $74LS191 ls191 = new $74LS191(); //计数器
    public S7Panel s7 = new S7Panel();//7段显示器

    public TrafficLight(){
        initPorts();
        initFrame();
        addListener();
        ShowPanel sp = new ShowPanel("191");
        sp.addInPorts(ls191.getCP(), ls191.get_UD(), ls191.get_LD(), ls191.get_CT(),
                ls191.getD3(), ls191.getD2(), ls191.getD1(), ls191.getD0());
        sp.addOutPorts(ls191.get_RC(), ls191.getCOBO(), ls191.getQ3(),
                ls191.getQ2(), ls191.getQ1(), ls191.getQ0());
        sp.showPanel();

        ShowPanel sp2 = new ShowPanel("7段");
        sp2.addInPorts(s7.getS7().getA3(), s7.getS7().getA2(), s7.getS7().getA1(), s7.getS7().getA0());
        sp2.addOutPorts(s7.getS7().getYa());
        sp2.showPanel();
    }
    public void initPorts(){
        pRed = new Port("Red");
        pYellow = new Port("Yellow");
        pGreen = new Port("Green");
        pCp = new Port("CP");//SignalCreater.getCpPort(4000);
        //连接
        new Wire(pCp, ls1.getCP());
        new Wire(pCp, ls2.getCP());

        new Wire(ls1.getQ3(), ls2.getDsr());
        new Wire(ls2.getQ0(), n5.inPort());
        new Wire(ls2.getQ0(), a2.inPort());
        new Wire(ls2.getQ1(), n4.inPort());
        new Wire(ls2.getQ1(), a1.inPort());
        new Wire(ls2.getQ1(), a2.inPort());
        new Wire(n5.outPort, a1.inPort());

        new Wire(n4.outPort, ls1.getDsr());
        new Wire(n4.outPort, n1.inPort());
        new Wire(a1.outPort, n2.inPort());
        new Wire(a2.outPort, n3.inPort());

        new Wire(n1.outPort, pRed);
        new Wire(n2.outPort, pYellow);
        new Wire(n3.outPort, pGreen);

        ls1.getM1().setV(0);
        ls1.getM0().setV(1);
        ls2.getM1().setV(0);
        ls2.getM0().setV(1);

        //连接计数器
        new Wire(pCp, ls191.getCP());
        new Wire(ls191.getQ0(), s7.getS7().getA0());
        new Wire(ls191.getQ1(), s7.getS7().getA1());
        new Wire(ls191.getQ2(), s7.getS7().getA2());
        new Wire(ls191.getQ3(), s7.getS7().getA3());


    }
    public void initFrame(){
        setSize(700, 300);
        setLayout(null);
        bRed = CompFactory.createButton1("");
        bYellow = CompFactory.createButton1("");
        bGreen = CompFactory.createButton1("");
        bRed.setBackground(gray);
        bYellow.setBackground(gray);
        bGreen.setBackground(gray);
        bRed.setBounds(10,20,150,200);
        bYellow.setBounds(170,20,150,200);
        bGreen.setBounds(330,20,150,200);
        this.add(bRed);
        this.add(bYellow);
        this.add(bGreen);
        s7.setBounds(540,5,130,250);
        this.add(s7);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void addListener(){
        pRed.addPortListener(new PortListener() {
            @Override
            public void portAffected(PortEvent e) {
                if(pRed.getV() == 1) bRed.setBackground(gray);
                else if(pRed.getV() == 0) bRed.setBackground(red);
            }
        });
        pYellow.addPortListener(new PortListener() {
            @Override
            public void portAffected(PortEvent e) {
                if(pYellow.getV() == 1) bYellow.setBackground(gray);
                else if(pYellow.getV() == 0) bYellow.setBackground(yellow);
            }
        });
        pGreen.addPortListener(new PortListener() {
            @Override
            public void portAffected(PortEvent e) {
                if(pGreen.getV() == 1) bGreen.setBackground(gray);
                else if(pGreen.getV() == 0) bGreen.setBackground(green);
            }
        });
    }

}
