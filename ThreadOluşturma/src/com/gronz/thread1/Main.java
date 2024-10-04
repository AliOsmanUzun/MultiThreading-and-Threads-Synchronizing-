package com.gronz.thread1;

public class Main {
	public static void main(String[] args) {
	// main metodu çalıştırılırken aynı anda bir main thread oluşturuluyor ve otomatik olarak çalıştırılıyor 
	// java projemizde biz herhangi bir thread ister oluşturalım ister oluşturmayalım
	
	Printer printer1 = new Printer("thread1");
	Printer printer2 = new Printer("thread2");
	printer1.start(); // Bu threadlerimin başladığı anda sahip olduları run metoduda çalışmış olacak
	printer2.start();
	
		
	System.out.println("Main thread çalıştırılıyor...");
	}
}
