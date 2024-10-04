
public class Main {
	
	public static void main(String[] args) {
		
	ProducerConsumer pc = new ProducerConsumer();
	
	Thread thread1 = new Thread(new Runnable() {
		
		@Override
		public void run() {
		pc.producer();
		}
	});
	
	Thread thread2 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			pc.consumer();
		}
	});
	
	thread1.start();
	thread2.start();
	
	try {
		thread1.join();
		thread2.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}

}
