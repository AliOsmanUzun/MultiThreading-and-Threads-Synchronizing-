package com.gronz.thread2;

public class Main {

	public static void main(String[] args) {
		
		Thread thread1 = new Thread(new Printer("printer1")); 
		// Constructor içerisinde bir tane Runnable objesi referansı alıyor
		// Printer class'ımız ile Runnable Interface'ini implement ettiğim için
		// Runnable objesini tutan bir referans yerine geçebiliyor bu class'tan türettiğim objeler
		Thread thread2 = new Thread(new Printer("printer2")); 
		
		thread1.start();
		thread2.start();
		// sahip oldukları run metodu ve içerisindeki kodlar çalışacak
		// threadlerim beraber paralel şekilde çalışıyor sadece çalışma zamanları ve yerleri farklı oluyor
		
		
		
		
		
		System.out.println("Main thread çalışıyor...");
		
		// Kısaca bu yöntemde bir thread objesi oluşturuyorum ve bu objenin constructor'ına 
		// Runnable interface'ini implement eden bir sınıfımın objesini veya referansını direkt olarak giriyorum
		// Böyle oluşturmuş olduğum sınıftan türeyen objeler bir thread gibi davranıyor ve çalışıyor
	}
}
