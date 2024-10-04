package com.gronz.thread3;

public class Main {
	
	public static void main(String[] args) {
		
		/* Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Thread çalışıyor...");
				for(int i = 10 ; i<=50; i+=10) {
					
					System.out.println(i+"('a ) 'e kadar yazıyor threadim.");
					try {
					Thread.sleep(2000);
					}catch (InterruptedException e) {
					e.printStackTrace();
					}
				}
				
			}
		}); // Anonim class yöntemi ile bir thread oluşturmuş olduk
		thread.start();
		
		System.out.println("Main thread çalışıyor");*/
		
		// SADECE TEK BİR DEFA ÇALIŞABİLECEK BİR THREAD OLUŞTURALIM
		 new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("Thread çalışıyor...");
					for(int i = 10 ; i<=100; i+=10) {
						
						System.out.println(i+"('a ) 'e kadar yazıyor threadim.");
						try {
						Thread.sleep(1000);
						}catch (InterruptedException e) {
						e.printStackTrace();
						}
					}
					
				}
			}).start();
	}

}
