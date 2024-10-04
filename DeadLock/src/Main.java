
public class Main {

	public static void main(String[] args) {
	
		DeadLockOrnegi deadlock = new DeadLockOrnegi();
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
			deadlock.thread1Fonksiyonu();
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
			deadlock.thread2Fonksiyonu();
			}
		});
		
		thread1.start();
		thread2.start();
		
		try {
		thread1.join();
		thread2.join();
		// Main thread sen thread1 ve 2 'nin işlerini bitirmesini bekle ondan sonra çalış
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		deadlock.threadOver();
		System.out.println("Thread'lerimin işlemleri bitti artık Main Thread'im çalışıyor...");
	}
}
