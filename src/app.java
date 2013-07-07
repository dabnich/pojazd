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
		pojazd.ustawNaped(1000);
		pojazd.ustawHamulec(1000, 250, 7, 0);
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
			
			pojazd.ustawGaz(ustGaz*Constants.pozGaz1);
			pojazd.hamowanie(ustHam*Constants.pozHam1);
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
		
		gDC.drawString("Predkosc: "+String.format("%.1f", pojazd.predkosc*3.6)+" km/h", 100, 100);
		gDC.drawString("Przyspieszenie: "+String.format("%.1f",pojazd.przyspieszenie)+" m/s", 100, 130);
		gDC.drawString("Opory: "+String.format("%.1f", pojazd.obliczOpor())+" N", 100, 160);
		gDC.drawString("Moc: "+String.format("%.1f", pojazd.obliczMoc())+" W", 100, 190);
		gDC.drawString("Pozycja: "+String.format("%.1f", pojazd.naped.pozycja)+" %", 300, 100);
		gDC.drawString("Sila napêdu: "+String.format("%.1f", pojazd.naped.sila)+" N", 300, 130);
		gDC.drawString("Hamulec-pozycja: "+String.format("%.1f", pojazd.hamulec.pozycja), 300, 190);
		gDC.drawString("hamulec-sila: "+String.format("%.1f", pojazd.hamulec.sila)+" %", 300, 220);
		
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
