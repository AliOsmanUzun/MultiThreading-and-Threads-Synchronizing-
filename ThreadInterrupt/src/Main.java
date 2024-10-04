import java.util.LinkedList;
import java.util.List;

public class Main {

	
	public static void main(String[] args) {
		
		// Thread'leri yarıda kesmeyi öğreneceğiz 
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
			System.out.println("Thread Çalışıyor");
			
			for(int i = 1 ; i<=10; i++) {
				System.out.println("Thread Yazıyor : " + i);
				try {
					Thread.sleep(1000);
					System.out.println("Uykumun " + (i) + ". saniyesindeyim");
				} catch (InterruptedException e) {
				System.out.println("Uykum bölündü...");
				
				// Sleep metodu tamamlanmadan bir hata fırlatıldığında o hata InterruptedException hatası oluyor
				// Program catch bloğuna girip buradaki kodu çalıştıracak
				// InterruptedException fırlatsa bile bazı işlemlerde threadim çalışmaya devam edicek
				// ınterrupt yani kesinti sinyali gönderildiği durumlarda sadece exception fırltacak ve catch
				// bloğunda çalışacak
				}
			}
			}
		});
		
		thread.start();
		
		try {
			Thread.sleep(2000);// 2 saniye boyunca maint threadim uyusun
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt(); // interrupt ile uyuyan veya bekleleyen threadimizin bu işlemini kesintiye uğratmak	
							// istiyorum , bunun sonucundada bir InterruptedException fırlatacak threadim
		// İlla ki threadimin bir bekleme veya uyku durumunda olması gerekmiyor kesintiye uğratmak istiyorsam
		// Aktif olarak çalışma sırasındada threadimi kesintiye uğratabilir isInterrupted() metodu ilede
		// manuel olarak kontrol edebilirim
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	// Belirli bir Interrupt olduğu zaman threadimin çalışmasını tamamen kesmek istiyorsam
	List<Integer> list = new LinkedList<Integer>();
	 Thread thread2 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			System.out.println("Thread Çalışıyor");
			// List'imize 10 milyar değer eklicem
			for (int i = 1; i <=10000000 ; i++) {
			// Ama ilk önce manuel olarak bu threadime bir interrupt ( kesinti ) yapıldımı onu kontrol edicem
				
			if (Thread.currentThread().isInterrupted()) {
			// Eğer bir interrupt uygulandıysa burası true değer dönecek
			// uygulanmadıysa false değer dönecek
				
			System.out.println("Kesintiye Uğradı");
			return; // run metodunu yok ediyoruz
			}
			
			list.add(i);
			
			}
			System.out.println("Thread kesintiye uğramadan işini bitirdi");
			}
			
	});
		thread2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.interrupt();
		
		
	// currentThread() metodu şuan üzerinde bulunduğun thread anlamına geliyor
	
					
	/*
	InterrupttedException Hangi Zamanlarda Oluşabilir ? 
	 
	İki şekilde oluşabilir ilk olarak threadimizi bilgisiyarımız kendi sonlandırıyordur , yarıda kesiyordur
	Belirli sebeplerden dolayı 
	 
	Yada programcı belirli bir saniye threadi interrupted'a uğratmak kesmek istiyordur
	Kullandığımız join,wait,sleep metotları sırasında kesintiye uğratmak buna bir örnektir.
	  
	Interrup'lar bir threadin çalışmasını sonlandırmıyor , sadece çalışmasını kesintiye uğratıyor
		 
	InterruptedException, Java'da bir iş parçacığının (thread) bekleme durumundayken kesintiye uğradığını
	belirtmek için kullanılan bir kontrol edilen (checked) istisnadır.
	Bu istisna, bir iş parçacığı bekleme, uyuma veya bir kaynağı bekleme gibi belirli işlemler sırasında,
	bir başka iş parçacığı tarafından kesintiye uğratıldığında fırlatılır. 
	Kesintiye uğratma, iş parçacığını zorla durdurma değil, sadece ona "durman gerekebilir" sinyali göndermedir.	 
		 
	Kısaca Java'da bekleme durumunda veya uyku durumunda olan bir threadin başka bir thread tarafından
	kesintiye uğratılıp InterruptedException fırlatması	 
	
	Thread.sleep(): Bir iş parçacığını belirli bir süre uyutmaya çalıştığınızda ve o sırada kesintiye 
	uğratıldığında bu istisna fırlatılır.
	
	Object.wait(): Bir iş parçacığı belirli bir nesnenin kilidini beklerken (örneğin synchronized bloklarında)
	kesintiye uğrarsa bu istisna ortaya çıkar.
	
	Thread.join(): Başka bir iş parçacığının tamamlanmasını bekleyen bir iş parçacığı kesintiye uğradığında 
	bu istisna fırlatılır.
	
	BlockingQueue.put() veya BlockingQueue.take() gibi bloklama yapan veri yapılarında: Bir iş parçacığı, 
	belirli bir sıraya (queue) veri eklemeyi veya veri almayı beklerken kesintiye uğrarsa, bu istisna oluşur.
	
	
	Bu istisna, genellikle bir iş parçacığının bekleme (waiting) veya uyuma (sleeping) durumu sırasında, 
	başka bir iş parçacığının onu "kesinti sinyali" ile uyandırmak istemesi durumunda ortaya çıkar. 
	Yani, iş parçacığı bir kaynak ya da zaman dilimi boyunca bloke edilmişken, başka bir iş parçacığı onun 
	kesilmesi gerektiğini belirten bir sinyal gönderir. 
	Bu sinyale iş parçacığı tepki verir ve InterruptedException fırlatılır.
	*/
	}
}
