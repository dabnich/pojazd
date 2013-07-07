import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;


public class app extends Applet implements Runnable, KeyListener{
	
	char relased;
	char typed;
	char pressed;
	boolean pracuje;
	
	int ustHam;
	int ustGaz;
	
	Graphics gDC;
	
	
	pojazd pojazd;
	
	//trasa tr = new trasa();
	
	
	
	public void init(){
		addKeyListener(this);
		pracuje = false;
		gDC = getGraphics();
		resize(Constants.szerokoscOkna, Constants.wysokoscOkna);
		pojazd = new pojazd(1500, 1.755, 1.416, 0.32);
		
		double rpm[];
		double moment[];
		rpm = new double[6];
		moment = new double[6];
	
		rpm[0] = 1000;
		rpm[1] = 2000;
		rpm[2] = 3000;
		rpm[3] = 4000;
		rpm[4] = 5000;
		rpm[5] = 5800;
		
		moment[0] = 0;
		moment[1] = 20000;
		moment[2] = 38000;
		moment[3] = 50000;
		moment[4] = 59000;
		moment[5] = 63000;
		
		pojazd.ustawSilnik(rpm, moment);
		pojazd.wlaczSilnik();
		pojazd.ustawHamulec(1000, 250, 7, 0);
		//tr.generujKolo(100, 100, 100);
		//tr.BresenhamLine(50, 50, 150, 500);
		
	}
	
	public void start(){
		pracuje = true;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run(){
		
		while(pracuje){
			try{
				Thread.sleep(1000/Constants.FPS);
			}
			catch(InterruptedException exc){};
			
			if(ustHam<0) ustHam=0;
			if(ustHam>Constants.maxPozHam) ustHam=Constants.maxPozHam;
			if(ustGaz<0) ustGaz=0;
			if(ustGaz>Constants.maxPozGaz) ustGaz=Constants.maxPozGaz;
			pojazd.hamowanie(ustHam*Constants.pozHam1);
			
			pojazd.ustawGaz(ustGaz*Constants.pozGaz1);
			pojazd.kontroluj();
			
			
			draw();
			

			//gDC.drawString(Character.toString(relased), 50, 50);
		}
		
	}
	
	public void stop(){
		pracuje = false;
	}
	
	public void draw(){
		gDC.clearRect(0, 0, getWidth(), getHeight());
		
		gDC.drawString("Predkosc: "+String.format("%.1f", pojazd.predkosc*3.6), 300, 300);
		gDC.drawString("gaz: "+Double.toString(pojazd.silnik.pozycja), 300, 340);
		gDC.drawString("RPM: "+Double.toString(pojazd.silnik.obroty), 300, 380);
		gDC.drawString("hamulec sila: "+Double.toString(pojazd.hamulec.sila), 300, 420);
		gDC.drawString("hamulec: "+Double.toString(pojazd.hamulec.pozycja), 300, 460);
		gDC.drawString("Przyspieszenie: "+Double.toString(pojazd.przyspieszenie), 300, 500);
		//gDC.drawString(Double.toString(pojazd.moc), 300, 420);
		//gDC.drawString(Integer.toString(ustHam), 50, 50);
		//gDC.drawString(Long.toString(Hamulec.cisnienie), 100, 100);

	}
	
	public void paint(Graphics gd){

	}
	
	public void keyPressed(KeyEvent evt){
		pressed = evt.getKeyChar();
		//repaint();
	}
	
	public void keyTyped(KeyEvent evt){
		typed = evt.getKeyChar();
		if(typed=='s') ustHam+=1;
		if(typed=='a') ustHam-=1;
		if(typed=='+') ustGaz+=1;
		if(typed=='-') ustGaz-=1;
		//repaint();
	}

	
	public void keyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		relased = evt.getKeyChar();
		//repaint();
	}


	
	
	
}
