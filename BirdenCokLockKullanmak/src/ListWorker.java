import java.util.ArrayList;
import java.util.Random;
public class ListWorker {
	
	// 2 tane anahtar oluşturalım
	// Bu anahtarlar herhangi bir class veya veri tipinde olabilir hiç farketmiyor
	// Burada tanımlayacağım objeleri synchronized kod bloklarında bir lock olarak kullanıyorum kısaca
	
	private  Object lock1 = new Object();
	private  Object lock2 = new Object();
	

	Random random = new Random();// Random class'ı random değerler üretmemizi sağlayan bir class'dır
	ArrayList<Integer> list1 = new ArrayList<Integer>();
	ArrayList<Integer> list2 = new ArrayList<Integer>();
	
	// İlk olarak sadece main threadi kullanıp bu list1 ve list2 ye 2000 tane değer ataması yapacağım
	
	public  void list1DegerEkle() {
		synchronized (lock1) { // Bu synchronized kod bloğuma özgü kilidim lock1 objem olmuş oldu
			
		
		// Elinde lock'ı hangi thread bulunduruyorsa ilk o girsin ve o çıkana kadar başka bir thread bu metoda giremez ve işlem yapamaz
		try {
			Thread.sleep(1);
		}
		catch (InterruptedException e) {
		}
		list1.add(random.nextInt(100));
		}
	}
	
	public  void list2DegerEkle() {
		// Buraya bir thread girdiyse başka bir thread girmesin ve bu threadin işlemini bitirmesini beklesin
		synchronized(lock2) {
		try {
			Thread.sleep(1);
		}
		catch (InterruptedException e) {
		}
		list2.add(random.nextInt(100));
		}
	}
	
	public  void degerAta() {
		for(int i = 0 ; i<1000 ;i++) {
			list1DegerEkle();
			list2DegerEkle();
		}
			// bu metotları 1000 defa çalıştırarak bu döngü ile iki arraylistimede 1000'er tane eleman
			// eklemiş olacağım random şekilde 
		}
		
	// Ama ben bu list1 ve list2 arraylerime toplam 2000 tane eleman eklemek istiyorum bu yüzden 
	//  degerAta metodunu 2 tane threadin çağırması gerekiyor
	
	public void calistir() {
		Thread thread1 = new Thread(() ->{
			// Lambda ifadesi ile daha kısa bir şekilde sanki bir Runnable nesnesiymiş gibi bu ifadeyi kullanarak
			// Ve tırnaklı parantezde bir run metoduymuş gibi direkt komutlarımı yazabiliyorum
			degerAta();
		});
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
			degerAta();
			}
		});
		// Toplamda 2 kez çağırarak 2000 tane değer atamış oldum list1 ve list2 arraylerime
		long baslangic = System.currentTimeMillis();
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
			// Threadlerim bitmeden bunlardan sonraki kodlar çalışmayacak
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long bitis = System.currentTimeMillis();
		// İki threadiminde çalışması bittikten sonra 
		
		System.out.println("Lİst1 Size : " + list1.size() + " List2 Size : " +list2.size() );
		
		
		
		System.out.println("Geçen Süre : " + ( bitis - baslangic));
		 	}
		
	}

