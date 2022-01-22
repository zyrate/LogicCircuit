package logic_circuit.tools.decoder;

import logic_circuit.base.port.Port;

import javax.swing.*;
import java.awt.*;

/**
 * 7¶ÎÏÔÊ¾Æ÷
 */
public class S7Displayer extends JFrame{
    private S7Panel p = new S7Panel();

    public S7Displayer(){
        setSize(500, 500);
        setLayout(new FlowLayout());
        add(p);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public Port getA0(){
        return p.getS7().getA0();
    }
    public Port getA1(){
        return p.getS7().getA1();
    }
    public Port getA2(){
        return p.getS7().getA2();
    }
    public Port getA3(){
        return p.getS7().getA3();
    }
//    public Port get_BIRBO(){
//        return p.getS7().get_BIRBO();
//    }
//    public Port get_LT(){
//        return p.getS7().get_LT();
//    }
//    public Port get_RBI(){
//        return p.getS7().get_RBI();
//    }public Port getYa(){
//        return p.getS7().getYa();
//    }
    public Port getYb(){
        return p.getS7().getYb();
    }
    public Port getYc(){
        return p.getS7().getYc();
    }
    public Port getYd(){
        return p.getS7().getYd();
    }
    public Port getYe(){
        return p.getS7().getYe();
    }
    public Port getYf(){
        return p.getS7().getYf();
    }
    public Port getYg(){
        return p.getS7().getYg();
    }

}
