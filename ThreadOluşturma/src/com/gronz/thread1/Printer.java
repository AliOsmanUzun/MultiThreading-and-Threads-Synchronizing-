package com.gronz.thread1;

public class Printer extends Thread {
// Eğer biz bir class'ı Thread class'ından türetirsek yani extend edersek o class'da bir Thread classı gibi davranır
// Ve kendi threadlerimizi oluşturabiliriz bu class'tan
	
	private String isim;

public Printer(String isim) {
	this.isim = isim;
}

// Artık bu class'dan türettiğim objeler bir thread gibi çalışacak

// Thread'im çalıştğında run metodu içerisinde komutlar çalışacak
@Override
public void run() {
	System.out.println(isim + " Çalışıyor");
	
	for(int i = 1 ; i<11 ; i++ ) {
		
	// Thread'imin her 1 saniyede 1 çalışmasını istiyorum
	// Bunun için Thread sınıfındaki sleep metodunu kullanıyorum
	try {
		System.out.println(isim + " Yazıyor : " + i); 
		Thread.sleep(1000);// oluşturduğumuz thread buradaki slepp metodu yüzünden 1 saniye bekleyecek
							// ve sonra işleme devam edecek	
	} catch (InterruptedException e) {
	// Projede bir hata olduğu zaman threadimiz uyumasından kalkması gerekiyor 
	// ve herhangi bir ıntterupted'a girmesi gerekiyor.Thread'in çalışmasını durdurmul oluyoruz aslında
	System.out.println("Thread kesintiye uğradı.");
		e.printStackTrace();
	} 
		
	}System.out.println(isim + " işini bitirdi");
}


	
	
}

