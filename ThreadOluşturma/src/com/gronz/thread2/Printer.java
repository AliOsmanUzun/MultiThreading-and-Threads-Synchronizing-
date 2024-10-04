package com.gronz.thread2;

// Bir classı thread olarak kullanmak istiyorsam Runnable interface'ini implement etmem gerekiyor
public class Printer implements Runnable{
	
	// Threadlerim başlatıldığı zaman run metodum çalışmış olacak
	@Override
	public void run() {
		System.out.println(isim + " çalışıyor.");
		
		try {
			for(int i = 1 ; i<=5 ; i++) {
			System.out.println(isim + " yazıyor  : " + i);	
				
			Thread.sleep(3000);
			}
		}
		catch (InterruptedException ex) {
		ex.printStackTrace();
		}System.out.println(isim + "threadi kesintiye uğradı");
	}

	private String isim;

	public Printer(String isim) {
		this.isim = isim;
	}
	
	
	
	

}
