package controller;

import java.util.concurrent.Semaphore;

public class Corredor extends Thread{
	
	private int pessoa;
	private int passo;
	private int corredor = 200;
	private int distancia;
	private Semaphore semaforo;
	int chegada = 0;
	
	public Corredor(int pessoa, Semaphore semaforo) {
		this.pessoa = pessoa;
		this.semaforo = semaforo;
	}
	@Override
	public void run() {
		while(distancia < corredor) {
			Andar();
		}
		Chegou();
		
	}
	private void Andar(){
		passo = (int)((Math.random()*3)+4);
		try {
			sleep(1000);
			distancia += passo;
			if(distancia > 200) {
				distancia = 200;
			}
			System.out.println("A pessoa #"+pessoa+" andou "+ passo+"m" +" Distancia total: "+distancia+"m");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void AbrirPorta() {
		try {
			sleep((int) ((Math.random()*2)+1));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	private void Chegou() {
//-----------------início da seção crítica---------------
		try {
			semaforo.acquire();
			System.out.println("A pessoa #"+pessoa+" está abrindo a porta!");
			AbrirPorta();
			chegada++;
			System.out.println("A pessoa #"+pessoa+" passou pela porta");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
//------------------ Fim da seção crítica-------------------
	}
}
