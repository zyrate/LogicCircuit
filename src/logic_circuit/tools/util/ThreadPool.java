package logic_circuit.tools.util;
/**
 * �̳߳�
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {
	/*��ȡ������(���幦��)���̳߳�*/
	private static ThreadPoolExecutor cachedThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//	private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	/*�½��߳�*/
	public static void newThread(Runnable r) {
		cachedThreadPool.execute(r);
	}
	/*�������ʱҪ�ر�*/
	public static void shutdown() {
		cachedThreadPool.shutdown();
	}
	/*�õ���Ծ�̵߳�����*/
	public static int getThreadCount() {
		return cachedThreadPool.getActiveCount();
	}
}
