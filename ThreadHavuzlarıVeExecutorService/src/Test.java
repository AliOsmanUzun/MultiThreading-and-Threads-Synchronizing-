import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		
	    // Bir thread havuzu oluşturalım ve task atamalarını executor service  kullanarak yapalım
		// Bu executorservice referansına bir tane thread havuzu atamam gerekiyor
		ExecutorService executor = Executors.newFixedThreadPool(2); // 2 threadden oluşan bir havuz oluşturduk
		// newFixedThreadPool metodu yeni bir thread havuzu oluşturuyor
		// ve bu metod içerisine parametre olarak girdiğim değer en fazla o kadar threadim aynı anda 
		// çalışmasını istediğimi söylemiş oluyorum
		// Bu havuzun kontrolünüde buradaki exectuor referansına vermiş oluyoruz
		
		for(int i = 1 ; i<=5 ; i++) {
		// Burada bir metot kullanmam gerekiyor ve bu metod içerisinde bir thread oluşturmam gerekiyor
		executor.submit(new Worker(String.valueOf(i), i));
		// Bu submit metodu içerisinde bir Runnable Interface'ini implemente eden obje kullanmam gerekiyor
		}
		
		executor.shutdown();
		
		// Executor'ım iş atamalarını yapıktan sonra ve işlemler tamamlandıktan sonra shutdown metodunu kullanarak
		// Executor'ı kapatıyorum. Önemli bir metot kısaca threadlerimizin işlemlerini bittikten sonra kesinlikle
		// kapatmalıyız . Diyorum ki bu metot ile task işlemleri bittikten sonra yeni bir görev alma işlemleri sonlandır
		// Ama thread havuzundaki işlemlerin bitmesini bekleyip sonlandır

		// Eğer belirli bir süre geçtikten sonra threadlerimin işlemlerini durdurup kesmek istiyorsam
		// awaitTermination(0, null) metodunu kullanıyorum
		// Ben burada belirli bir süre beklerim anlamına geliyor ( threadler için ) 
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
			//Bu threadlerin işlerinin bitmesini en fazla 1 gün beklerim
			// Ardından taskler bitsin bitmesin işlemi direkt keserim programı bitiririm
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Bütün işler bitti...");
		/*
		Thread t1 = new Thread(new Worker("1", 1));
		
		Thread t2 = new Thread(new Worker("2", 2));

		Thread t3 = new Thread(new Worker("3", 3));

		Thread t4 = new Thread(new Worker("4", 4));
		
		Thread t5 = new Thread(new Worker("5", 5));
		
		// İlk önce t1 ve t2 thread objelerimin beraber çalışmasını
		// Ardından t3 ve t4 threadlerimin aynı anda çalışmasını
		// Ve son olarak t5 threadimin çalışmasını istiyorum
		
		System.out.println("Bütün işler teslim edildi!");
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t3.start();
		t4.start();
		try {
			t3.join();
			t4.join();	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t5.start();
		try {
			t5.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Bütün işlemler tamamlandı...");
		*/
		
		// Burada 5 taskımız var ve biz bunların bazılarının aynı anda bazılarının farklı zamanlarda 
		// çalışmasını istedik ama bu bizim kodumuzu gereksiz yere uzattı bu thread sayısı arttıkça daha da fazla
		// uzayacağı anlamına geliyor bu kodların
		// Biz bu işlemleri kısaltmak için thread havuzlarını ( thread pools ) ve Executor Service yapısını kullanıyoruz
		// Bu işlem bizim gereksiz sayıda thread oluşturmamızı ve belleği işgal etmemizin önüne geçiyor
		// Program ne kadar task var ise o task için thread havuzundan yeteri kadar thread alıp kullanıyor
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	/*	
	
	Thread havuzları, bir grup önceden oluşturulmuş thread’den oluşur. 
	Uygulama, ihtiyaç duydukça bu havuzdaki thread’leri kullanır, böylece her seferinde 
	yeni bir thread oluşturulmasına gerek kalmaz. 
	Bu, kaynakların daha verimli kullanılmasını sağlar ve aynı anda çok sayıda thread oluşturmanın 
	getirdiği maliyetleri azaltır.
	
	Thread Havuzunun Faydaları:
	Kaynak yönetimi: Thread havuzu, thread sayısını sınırlayarak sistem kaynaklarının aşırı kullanımını engeller.
	
	Thread yeniden kullanımı: Mevcut thread'ler, işlemler arasında yeniden kullanılır.
 	Bu, her iş için yeni bir thread oluşturmaktan daha verimlidir.
	
	Thread oluşturma maliyeti: Her yeni thread oluşturmanın maliyeti yüksektir. 
	Havuzdaki thread'ler tekrar tekrar kullanılabilir, bu da performansı artırır.
	
	Ölçeklenebilirlik: Havuzdaki thread sayısını ihtiyaca göre ayarlayarak performans optimize edilebilir.
	
	
	
Executor Service
ExecutorService, Java'nın java.util.concurrent paketinde bulunan ve thread yönetimi yapan bir framework’tür. Thread havuzlarını yönetmek ve thread’leri kontrol etmek için kullanılır. Bu yapı, thread yönetimini daha kolay ve esnek hale getirir. Bir ExecutorService kullanarak thread'leri başlatabilir, durdurabilir ve izleyebilirsin.

Executor Service’in Temel Metodları:
execute(Runnable task): Bir Runnable görevi alır ve bunu bir thread havuzuna gönderir.
Havuzdan boş bir thread varsa, bu thread görevi yerine getirir.

submit(Callable task): Bir görevi (Callable veya Runnable) havuza gönderir ve bir sonuç döndüren 
bir Future nesnesi ile çalışır.

shutdown(): ExecutorService’i kapatır. Mevcut görevler tamamlanır, fakat yeni görevler kabul edilmez.

shutdownNow(): Mevcut görevleri durdurur ve yeni görevleri kabul etmez.

awaitTermination(): Tüm görevlerin tamamlanmasını bekler.
	*/
		
	}

}
