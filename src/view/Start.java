package view;
import java.util.concurrent.Semaphore;
import controller.Corredor;

public class Start {

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		for(int pessoa = 0; pessoa < 5 ; pessoa++) {
			Thread Corrida = new Corredor(pessoa, semaforo);
			Corrida.start();
		}
	}
}
