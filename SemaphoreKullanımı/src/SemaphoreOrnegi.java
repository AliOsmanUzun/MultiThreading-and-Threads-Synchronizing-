import java.util.concurrent.Semaphore;

public class SemaphoreOrnegi {

	private Semaphore sem = new Semaphore(1);
	// Semaphore constructor'ı içerisinde bizden parametre olarak permit istiyor yani izin
	// Bu izin aynı anda kaç tane threadin çalışacağına vereceğimiz izin sayısı 3 yazarsam aynı anda 3 
	// 2 yazarsam aynı anda 2 thread çalışacak belirtilen kod bloğunda
	public void threadFonksiyonu(int id) {
		
		try {
			sem.acquire();
			// Semaphoru'muzun izin sayısını kendi içinde tutan değişkenin değerini 1 azaltıyor
			// Eğer bir thread izin aldıysa 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}// thread bir izin almak için acquire metodunu çağırır var ise thread çalışmaya 
		// devam eder yok ise thread bekler ( kullanılan iznin boşa çıkmasını ) 
		
		System.out.println("Thread Başlıyor ID : " + id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread Çıkıyor ID : " + id);
		// daha sonra buradan çıkmak için alınan izni bırakması gerekiyor threadimin
		sem.release(); // release metodu ile aldığımız izni ( permit ) bırakıyoruz
		// Semaphoru'muzun izin sayısını kendi içinde tutan değişkenin değerini 1 arttırıyor
		// buda başka threadlerin izni almasını sağlıyor
	}
}
