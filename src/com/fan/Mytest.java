package com.fan;

public class Mytest implements Runnable{
	
	//  RF������ҵ��(����ͬʱ����RFʱ��ֻ���Ŷӽ��У�һ�����ˣ���һ�����ܱ���)
    public static byte[] job_lock = new byte[0]; 
	
	private int intVAL=0;

	public static void main(String[] args) {
		Mytest my = new Mytest();
		new Thread(my,"A").start();
		new Thread(my,"B").start();

	}


	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			synchronized (job_lock) {
				System.out.println("�߳�:"+Thread.currentThread().getName()+"����>"+this.intVAL);
			}
		}
		
	}
}
