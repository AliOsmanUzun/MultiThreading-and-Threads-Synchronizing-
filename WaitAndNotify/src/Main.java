
public class Main {
	
	public static void main(String[] args) {
	// Wait ve Notify Metodu
	// Bu metotlarımız synchronized kod bloklarında oldukça sık kullanılıyor
	// Belirli koşula göre bizim threadlerdeki lockları bırakmamızı sağlayan ve bunları daha sonra elde etmemizi 
	// sağlayan 2 tane metotlarımızdır.
	// Synchronized bloklarda threadimiz locka sahip oluyor ve işlemi bitene kadar kimseye devretmiyordu
	// Ancak kod bloğu bitmeden biz lock'un teslim edilmesini isteyebiliriz bu işlem için ise
	// wait ve notify metotlarını kullanıyoruz
		
		
	WaitNotify wn = new WaitNotify();
	
	Thread thread1 = new Thread(new Runnable() {
		
		@Override
		public void run() {
		wn.thread1Fonksiyonu();
		
		}
	});
	
	Thread thread2 = new Thread(new Runnable() {
		
		@Override
		public void run() {
		wn.thread2Fonksiyonu();
		}
	});
	
	thread1.start();
	thread2.start();
	
	try {
	thread1.join();
	thread2.join();
	}catch (InterruptedException e) {
	e.printStackTrace();
	}
	
	
	}

}
