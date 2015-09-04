package com.fan;

public class Mytest implements Runnable{
	
	//  RF生成作业锁(多人同时操作RF时，只能排队进行，一个完了，下一个才能保存)
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
				System.out.println("线程:"+Thread.currentThread().getName()+"——>"+this.intVAL);
			}
		}
		
	}
}
