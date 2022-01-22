package test;

import logic_circuit.flipflop.counter.$74LS191;
import logic_circuit.flipflop.register.$74LS194;
import logic_circuit.product.TrafficLight;
import logic_circuit.tools.decoder.S7Displayer;
import logic_circuit.tools.showpanel.ShowPanel;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {

//        EdgeJK jk = new EdgeJK();
//        ShowPanel sp = new ShowPanel("±ßÑØJK´¥·¢Æ÷");
//        sp.addInPorts(jk.get_Rd(), jk.get_Sd(), jk.getCP(), jk.getJ(), jk.getK());
//        sp.addOutPorts(jk.getQ(), jk.get_Q());
//        sp.showPanel();

//        NotGate not = new NotGate();
//        not.input(1);
//        Thread.sleep(100);
//        System.out.println(not.outPort);

        //¼Ä´æÆ÷
//        $74LS175 reg = new $74LS175();
//        ShowPanel sp = new ShowPanel("¼Ä´æÆ÷");
//        sp.addInPorts(reg.get_CR(), reg.getCP(), reg.getD3(), reg.getD2(), reg.getD1(), reg.getD0());
//        sp.addOutPorts(reg.getQ3(), reg.getQ2(), reg.getQ1(), reg.getQ0());
//        sp.showPanel();

        //Ê§°Ü
//        S7Decoder s7 = new S7Decoder();
//        ShowPanel sp = new ShowPanel("7¶ÎÒëÂëÆ÷");
//        sp.addInPorts(s7.get_LT(), s7.get_RBI(), s7.get_BIRBO(), s7.getA3(), s7.getA2(),
//                s7.getA1(), s7.getA0());
//        sp.addOutPorts(s7.getYa(), s7.getYb(), s7.getYc(), s7.getYd(), s7.getYe(),
//                s7.getYf(), s7.getYg());
//        sp.showPanel();

//        A_S7Decoder s7 = new A_S7Decoder();
//        ShowPanel sp = new ShowPanel("7¶ÎÒëÂëÆ÷");
//        sp.addInPorts(s7.getA3(), s7.getA2(), s7.getA1(), s7.getA0());
//        sp.addOutPorts(s7.getYa(), s7.getYb(), s7.getYc(), s7.getYd(), s7.getYe(),
//                s7.getYf(), s7.getYg());
//        sp.showPanel();

        //7¶ÎÒëÂëÆ÷ - ³É¹¦
//        S7Displayer d = new S7Displayer();
//        ShowPanel sp = new ShowPanel();
//        sp.addInPorts(d.get_LT(),d.get_BIRBO(),d.getA3(), d.getA2(), d.getA1(), d.getA0());
//        sp.addOutPorts(d.getYa());
//        sp.showPanel();

        //Ë«ÏòÒÆÎ»¼Ä´æÆ÷
//        $74LS194 ls194 = new $74LS194();
//        ShowPanel sp = new ShowPanel("Ë«ÏòÒÆÎ»¼Ä´æÆ÷");
//        sp.addInPorts(ls194.get_CR(), ls194.getM1(), ls194.getM0(), ls194.getDsl(),
//                ls194.getDsr(), ls194.getCP(), ls194.getD0(), ls194.getD1(), ls194.getD2(), ls194.getD3());
//        sp.addOutPorts(ls194.getQ0(), ls194.getQ1(), ls194.getQ2(), ls194.getQ3());
//        sp.showPanel();

        //ºìÂÌµÆ
       TrafficLight light = new TrafficLight();
       ShowPanel sp = new ShowPanel("ºìÂÌµÆ");
       sp.addInPorts(light.pCp, light.ls1.get_CR(), light.ls2.get_CR());
       sp.addOutPorts(light.ls1.getQ0(), light.ls1.getQ1(), light.ls1.getQ2(), light.ls1.getQ3(),
               light.ls2.getQ0(), light.ls2.getQ1(), light.ls2.getQ2(), light.ls2.getQ3());
       sp.showPanel();

        //¼ÆÊýÆ÷74LS191
//        $74LS191 ls191 = new $74LS191();
//        ShowPanel sp = new ShowPanel();
//        sp.addInPorts(ls191.getCP(), ls191.get_UD(), ls191.get_LD(), ls191.get_CT(),
//                ls191.getD3(), ls191.getD2(), ls191.getD1(), ls191.getD0());
//        sp.addOutPorts(ls191.get_RC(), ls191.getCOBO(), ls191.getQ3(),
//                ls191.getQ2(), ls191.getQ1(), ls191.getQ0());
//        sp.showPanel();

    }
}
