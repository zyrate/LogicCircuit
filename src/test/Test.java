package test;

import logic_circuit.msi.decoder.$74LS138;
import logic_circuit.tools.showpanel.ShowPanel;

public class Test {
	public static void main(String[] args) throws InterruptedException {
//		AndGate and1 = new AndGate(true);
//		AndGate and2 = new AndGate(true);
//		AndGate and3 = new AndGate(true);
//		OrGate or = new OrGate(true);
//		NotGate not = new NotGate();
//		//����
//		new Wire(and1.outPort, or.inPort(0));
//		new Wire(and2.outPort, or.inPort(1));
//		new Wire(or.outPort, not.inPort());
//		//����
//		and1.input(1, 0);
//		and2.input(1, 0);
//		//���
//		System.out.println(not.outPort);
		
		/*ͬ���߼�����*/
//		AndGate andNot1 = new AndGate(false),
//				andNot2 = new AndGate(false),
//				andNot3 = new AndGate(false),
//				andNot4 = new AndGate(false),
//				andNot5 = new AndGate(false);
//		Port A = new Port(),
//			 B = new Port(),
//			 F = new Port();
//		
//		new Wire(A, andNot1.inPort(0));
//		new Wire(B, andNot1.inPort(1));
//		new Wire(A, andNot2.inPort(0));
//		new Wire(B, andNot3.inPort(1));
//		new Wire(andNot1.outPort, andNot2.inPort(1));
//		new Wire(andNot1.outPort, andNot3.inPort(0));
//		new Wire(andNot2.outPort, andNot4.inPort(0));
//		new Wire(andNot3.outPort, andNot4.inPort(1));
//		new Wire(andNot4.outPort, andNot5.inPort(0));
//		new Wire(andNot4.outPort, andNot5.inPort(1));
//		new Wire(andNot5.outPort, F);
//
//		new4 ShowPanel(F);

		//2-4������
//		$74LS139 line24 = new $74LS139();
//		ShowPanel sp = new ShowPanel();
//		sp.addInPorts(line24.getST(), line24.getA1(), line24.getA0());
//		sp.addOutPorts(line24.getY3(), line24.getY2(), line24.getY1(), line24.getY0());
//		sp.showPanel();
//		line24.input(1, 1, 1);
//		Thread.sleep(1000);
//		line24.input(0, 0, 0);
//		Thread.sleep(1000);
//		line24.input(0, 0, 1);
//		Thread.sleep(1000);
//		line24.input(0, 1, 0);
//		Thread.sleep(1000);
//		line24.input(0, 1, 1);
//		Thread.sleep(1000);
		
		//3-8������
		$74LS138 line38 = new $74LS138();
		ShowPanel s = new ShowPanel("3-8������");
		s.addInPorts(line38.getSTA(), line38.getSTB(), line38.getSTC(),
				line38.getA2(), line38.getA1(), line38.getA0());
		s.addOutPorts(line38.getY0(), line38.getY1(), line38.getY2(), line38.getY3(),
				line38.getY4(), line38.getY5(), line38.getY6(), line38.getY7());
		s.showPanel();
//		line38.input(0, 0, 0, 0, 0, 0);
//		int time = 1000;
//		line38.input(1, 0, 0, 0, 0, 0);
//		Thread.sleep(time);
//		line38.input(1, 0, 0, 0, 0, 1);
//		Thread.sleep(time);
//		line38.input(1, 0, 0, 0, 1, 0);
//		Thread.sleep(time);
//		line38.input(1, 0, 0, 0, 1, 1);
//		Thread.sleep(time);
//		line38.input(1, 0, 0, 1, 0, 0);
//		Thread.sleep(time);
//		line38.input(1, 0, 0, 1, 0, 1);
//		Thread.sleep(time);
//		line38.input(1, 0, 0, 1, 1, 0);
//		Thread.sleep(time);
//		line38.input(1, 0, 0, 1, 1, 1);
//		Thread.sleep(time);

		//4ѡ1
//		Mux41 mux41 = new Mux41();
//		ShowPanel s = new ShowPanel("��ѡһѡ����");
//		s.addInPorts(mux41.getST(), mux41.getA1(), mux41.getA0(), mux41.getD0(),
//				mux41.getD1(), mux41.getD2(), mux41.getD3());
//		s.addOutPorts(mux41.getY());
//		s.showPanel();
//
//
//		//˫��ѡһ
//		$74LS153 dmux41 = new $74LS153();
//		ShowPanel s1 = new ShowPanel("˫��ѡһѡ����");
//		s1.addInPorts(dmux41.getA1(), dmux41.getA0(), dmux41.get_1ST(), dmux41.get_1D0(),
//				dmux41.get_1D1(), dmux41.get_1D2(), dmux41.get_1D3(), dmux41.get_2ST(),
//				dmux41.get_2D0(), dmux41.get_2D1(), dmux41.get_2D2(), dmux41.get_2D3());
//		s1.addOutPorts(dmux41.get_1Y(), dmux41.get_2Y());
//		s1.showPanel();
		
		//����RS������
//		RS rs = new RS();
//        ShowPanel sp = new ShowPanel("����RS������");
//        sp.addInPorts(rs.get_Sd(), rs.get_Rd());
//        sp.addOutPorts(rs.getQ(), rs.get_Q());
//        sp.showPanel();

		//ͬ��RS������
//		SyncRS srs = new SyncRS();
//		ShowPanel sp = new ShowPanel("ͬ��RS������");
//		sp.addInPorts(srs.getCP(), srs.getR(), srs.getS());
//		sp.addOutPorts(srs.getQ(), srs.get_Q());
//		sp.showPanel();

		//ͬ��D������
//		SyncD sd = new SyncD();
//		ShowPanel sp = new ShowPanel("ͬ��D������");
//		sp.addInPorts(sd.getCP(), sd.getD());
//		sp.addOutPorts(sd.getQ(), sd.get_Q());
//		sp.showPanel();

        //ͬ��JK������
//        SyncJK jk = new SyncJK();
//        ShowPanel sp = new ShowPanel("ͬ��JK������");
//        sp.addInPorts(jk.getCP(), jk.getJ(), jk.getK());
//        sp.addOutPorts(jk.getQ(), jk.get_Q());
//        sp.showPanel();

        //����JK������
//        EdgeJK jk = new EdgeJK();
//        ShowPanel sp = new ShowPanel("����JK������");
//        sp.addInPorts(jk.get_Rd(), jk.get_Sd(), jk.getCP(), jk.getJ(), jk.getK());
//        sp.addOutPorts(jk.getQ(), jk.get_Q());
//        sp.showPanel();

        //����D������
//        EdgeD d = new EdgeD();
//        ShowPanel sp = new ShowPanel("����D������");
//        sp.addInPorts(d.getRd(), d.getSd(), d.getCP(), d.getD());
//        sp.addOutPorts(d.getQ(), d.get_Q());
//        sp.showPanel();

        //4λ��ˮ��
//        EdgeD d1 = new EdgeD(),
//                d2 = new EdgeD();
//        $74LS139 dec = new $74LS139();
//        new Wire(d1.get_Q(), d1.getD());
//        new Wire(d1.getQ(), dec.getA0());
//        new Wire(d1.getQ(), d2.getCP());
//        new Wire(d2.get_Q(), d2.getD());
//        new Wire(d2.getQ(), dec.getA1());
//
//        Port L1 = new Port("L1");
//        Port L2 = new Port("L2");
//        Port L3 = new Port("L3");
//        Port L4 = new Port("L4");
//        new FWire(dec.getY0(), L1);
//        new FWire(dec.getY1(), L2);
//        new FWire(dec.getY2(), L3);
//        new FWire(dec.getY3(), L4);
//        ShowPanel sp = new ShowPanel("4λ��ˮ��");
//        sp.addInPorts(dec.getST(), d1.getCP());
//        sp.addOutPorts(L1, L2, L3, L4);
//        sp.showPanel();
	}
}
