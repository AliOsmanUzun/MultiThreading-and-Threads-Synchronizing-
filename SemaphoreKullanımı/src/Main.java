
public class Main {
	
		
	public static void main(String[] args) {
	// ReentrantLock yapılarında ve synchronized kod blokları , metotlarında biz sadece o kod blokları içerisine 
	// Sadece tek bir threadin girmesine izin veriyorduk ve diğer threadler giremiyordu giren thread çıkana kadar
	
	// Ancak semaphore kullanarak birden fazla threadin buralara girmesini sağlayabiliriz ve diğer threadlerin
	// buralara girmesini engelleyebiliriz
		
	// Kısaca aynı anda birden fazla threadin çalışmasını istiyorsak ve diğer threadlerinde onların işlemlerini
	// bitirmesini beklemek istiyorsak Semaphore yapısını kullanabilirim
		SemaphoreOrnegi semaphore = new SemaphoreOrnegi();
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
			semaphore.threadFonksiyonu(1);
				
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
			semaphore.threadFonksiyonu(2);
			}
		});
		
		Thread thread3 = new Thread(new Runnable() {
					
					@Override
					public void run() {
			semaphore.threadFonksiyonu(3);	
					}
				});
		
		Thread thread4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
			semaphore.threadFonksiyonu(4);	
			}
		});
		
		Thread thread5 = new Thread(new Runnable() {
			
			@Override
			public void run() {
			semaphore.threadFonksiyonu(5);		
			}
		});
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
				
		try {
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();
		// main thread bu threadlerin işlemlerinin bitmesini bekleyecek ve ondan sonra çalışacak
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Tüm threadlerimin işleri bitti artık main threadim çalışıyor...");
		
		
	/*
	 
	Semaphore, Java'da çoklu iş parçacıkları arasında eş zamanlı erişimi kontrol etmek için kullanılan bir 
	senkronizasyon aracıdır. Temelde, belirli sayıda thread'in bir kaynağa aynı anda erişebilmesini sağlar.
	Semaphore'un mantığı, belirli bir sayıda "izin" (permit) vermesidir ve her bir thread bu izni alarak 
	kaynağa erişir. Kaynağı kullanmayı bitiren thread izni geri verir, böylece başka bir thread bu izni alabilir 
	ve kaynağa erişebilir.

	Semaphore'un Çalışma Prensibi:
	Semaphore'da belirli bir izin (permit) sayısı belirlenir. Örneğin, 3 izinli bir Semaphore,
	en fazla 3 thread'in aynı anda bir kaynağa erişmesine izin verir.
	Eğer bir thread erişim için izin alırsa (acquires a permit), geri kalan thread'ler izinlerin serbest
    bırakılmasını bekler.
	İşini bitiren thread, izni serbest bırakır, böylece bekleyen bir başka thread bu izni alabilir ve 
	kaynağa erişebilir.
	
	Semaphore'un İki Temel Metodu:
	acquire(): Thread, bir izin almak için bu metodu çağırır. Eğer izin varsa, thread çalışmaya devam eder. 
	İzin yoksa, thread bekler.
	release(): Thread, işini bitirdiğinde izni serbest bırakmak için bu metodu çağırır,
	böylece başka bir thread bu izni alabilir.
	
	Semaphore Kullanım Alanları:
	Aynı anda sadece belirli sayıda thread'in erişmesi gereken kaynakların yönetiminde kullanılır
	(örneğin, birden fazla thread'in bir veritabanına ya da dosya sistemine erişmesi gibi).
	Thread sayısının sınırlandırılmak istendiği durumlarda kullanılır.
	Örneğin, bir uygulamanın en fazla 5 iş parçacığının aynı anda çalışmasına izin vermek istiyorsanız,
	Semaphore bu sınırlamayı sağlamak için kullanılabilir.
		  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	 
	 */
	}
}
