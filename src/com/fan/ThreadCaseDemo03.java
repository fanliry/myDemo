package com.fan;
class Info{	// ������Ϣ��
	private String name = "���˻�";	 // ����name����
	private String content = "JAVA��ʦ"  ;		// ����content����
	private boolean flag = false ;	// ���ñ�־λ
	public synchronized void set(String name,String content){
		if(flag){
			try{
				super.wait() ;
			}catch(InterruptedException e){
				e.printStackTrace() ;
			}
		}
		try{
			Thread.sleep(300) ;
		}catch(InterruptedException e){
			e.printStackTrace() ;
		}
		this.setName(name) ;	// ��������
		this.setContent(content) ;	// ��������
		flag  = true ;	// �ı��־λ����ʾ����ȡ��
		super.notify() ;
	}
	public synchronized void get(){
		if(!flag){
			try{
				super.wait() ;
			}catch(InterruptedException e){
				e.printStackTrace() ;
			}
		}
		try{
			Thread.sleep(300) ;
		}catch(InterruptedException e){
			e.printStackTrace() ;
		}
		System.out.println(this.getName() + 
			" --> " + this.getContent()) ;
		flag  = false ;	// �ı��־λ����ʾ��������
		super.notify() ;
	}
	public void setName(String name){
		this.name = name ;
	}
	public void setContent(String content){
		this.content = content ;
	}
	public String getName(){
		return this.name ;
	}
	public String getContent(){
		return this.content ;
	}
};
class Producer implements Runnable{	// ͨ��Runnableʵ�ֶ��߳�
	private Info info = null ;		// ����Info����
	public Producer(Info info){
		this.info = info ;
	}
	public void run(){
		boolean flag = false ;	// ������λ
		for(int i=0;i<50;i++){
			if(flag){
				this.info.set("���˻�","JAVA��ʦ") ;	// ��������
				flag = false ;
			}else{
				this.info.set("mldn","www.mldnjava.cn") ;	// ��������
				flag = true ;
			}
		}
	}
};
class Consumer implements Runnable{
	private Info info = null ;
	public Consumer(Info info){
		this.info = info ;
	}
	public void run(){
		for(int i=0;i<50;i++){
			this.info.get() ;
		}
	}
};
public class ThreadCaseDemo03{
	public static void main(String args[]){
		Info info = new Info();	// ʵ����Info����
		Producer pro = new Producer(info) ;	// ������
		Consumer con = new Consumer(info) ;	// ������
		new Thread(pro).start() ;
		new Thread(con).start() ;
	}
};