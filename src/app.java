import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class app extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	boolean pracuje;
	
	int ustHam, ustGaz;
	char relased;
	char typed;
	char pressed;
	
	int myszX, myszY;
	int myszXstart, myszYstart;
	
	boolean myszClick = false;
	boolean myszRelased = false;
	
	Graphics gDC;
	Graphics gPredkTxt;
	Graphics gPredkPlansza;
	Graphics gPredkWskazowka;
	Graphics gPrzyspPlansza;
	Graphics gPrzyspKrawedz;
	Graphics gPrzyspTxt;
	
	pojazd pojazd;
	
	suwak suwGaz = new suwak(100, 30, 300, 300);
	

	public void init(){
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		pracuje = false;
		gDC = getGraphics();
		gPredkPlansza = getGraphics();
		gPredkPlansza.setColor(new Color(250, 250, 0));
		gPredkWskazowka = getGraphics();
		gPredkTxt = getGraphics();
		gPredkTxt.setFont(new Font("Arial", 0, 10));
		gPrzyspPlansza = getGraphics();
		gPrzyspPlansza.setColor(new Color(0, 255, 0));
		gPrzyspKrawedz = getGraphics();
		gPrzyspTxt = getGraphics();
		gPrzyspTxt.setFont(new Font("Arial", 0, 9));
		
		resize(Constants.szerokoscOkna, Constants.wysokoscOkna);
		
		pojazd = new pojazd(1500, 1.755, 1.416, 0.32);
		pojazd.ustawNaped(100000);
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
			
			sterowanie();
			
			//pojazd.ustawGaz(ustGaz*Constants.pozGaz1);
			pojazd.ustawGaz(suwGaz.wartosc);
			pojazd.hamowanie(ustHam*Constants.pozHam1);
			pojazd.kontroluj();
			
			draw();
		}
		
	}
	
	public void stop(){
		pracuje = false;
	}
	
	public void draw(){
		gDC.clearRect(0, 0, getWidth(), getHeight());
		
		gDC.drawString("Predkosc: "+String.format("%.1f", pojazd.predkosc*3.6)+" km/h", 100, 100);
		gDC.drawString("Przyspieszenie: "+String.format("%.2f",pojazd.przyspieszenie)+" m/s", 100, 130);
		gDC.drawString("Opory: "+String.format("%.1f", pojazd.obliczOpor())+" N", 100, 160);
		gDC.drawString("Pozycja: "+String.format("%.1f", pojazd.naped.pozycja)+" %", 300, 100);
		gDC.drawString("Sila napêdu: "+String.format("%.2f", pojazd.naped.moc/1000)+" kW", 300, 130);
		gDC.drawString("Hamulec-pozycja: "+String.format("%.1f", pojazd.hamulec.pozycja), 300, 190);
		gDC.drawString("hamulec-sila: "+String.format("%.1f", pojazd.hamulec.sila)+" %", 300, 220);
		predkosciomierz(pojazd.predkosc, pojazd.maxPredkosc, 300, 400, 120);
		przyspniomierz(pojazd.przyspieszenie, pojazd.maxPrzyspieszenie, pojazd.hamulec.skutecznosc, 600, 200, 450);
		
		gDC.drawRect(suwGaz.pozX, suwGaz.pozY, suwGaz.szerokosc, suwGaz.dlugoscDraw);
		gDC.fillRect(suwGaz.wskX, suwGaz.wskY, suwGaz.szerokosc, suwGaz.uchwytWys);
		//gDC.drawString(Double.toString(pojazd.moc), 300, 420);
		//gDC.drawString(Integer.toString(ustHam), 50, 50);
		//gDC.drawString(Long.toString(Hamulec.cisnienie), 100, 100);
	}
	
	public void kontrolujMysz(){
		if(myszClick==true) {
			if(suwGaz.czyKlikniety(myszX, myszY)) suwGaz.ustawiany=true;
		}
		else {
			suwGaz.ustawiany=false;
		}
		
		if(suwGaz.ustawiany){
			suwGaz.ustawWartosc((int)myszY);
		}
		
	}
	
	public void sterowanie(){
		if(ustHam<0) ustHam=0;
		if(ustHam>Constants.maxPozHam) ustHam=Constants.maxPozHam;
		if(ustGaz<0) ustGaz=0;
		if(ustGaz>Constants.maxPozGaz) ustGaz=Constants.maxPozGaz;
		kontrolujMysz();
		//if(ustGaz*Constants.pozGaz1 
	}
	

	
	public void predkosciomierz(double predkosc, double maxPredkosc, int srodekX, int srodekY, double promien){
		
		int wX, wY, wX2, wY2;
        double wspOdstepu = 0.9;
        predkosc = predkosc*3.6;
		maxPredkosc = Math.ceil((maxPredkosc*3.6)/10)*10;
		int ilePodzialki = (int)(maxPredkosc/10);
        int tmpPredkosc=0;
        int textX=0, textY=0;
        
        gPredkPlansza.fillOval(srodekX-(int)promien, srodekY-(int)promien, (int)promien*2, (int)promien*2);
        
        for(int i=0; i<=ilePodzialki; i++){
    		wX = (int)(srodekX+(promien*wspOdstepu)*Math.cos(-1.25*Math.PI+(tmpPredkosc/maxPredkosc)*Math.PI*1.5));
    		wX2 = (int)(srodekX+(promien-1)*Math.cos(-1.25*Math.PI+(tmpPredkosc/maxPredkosc)*Math.PI*1.5));
    		wY = (int)(srodekY+(promien*wspOdstepu)*Math.sin(-1.25*Math.PI+(tmpPredkosc/maxPredkosc)*Math.PI*1.5));
    		wY2 = (int)(srodekY+(promien-1)*Math.sin(-1.25*Math.PI+(tmpPredkosc/maxPredkosc)*Math.PI*1.5));
    		gPredkTxt.drawLine(wX, wY, wX2, wY2);
    		
    		textX=(wX-wX2);
    		textY=(wY-wY2);
    		gPredkTxt.drawString(Integer.toString(tmpPredkosc), wX+textX-7, wY+textY+2);

    		tmpPredkosc+=10;
        }
		wX = (int)(srodekX+(promien*wspOdstepu)*Math.cos(-1.25*Math.PI+(predkosc/maxPredkosc)*Math.PI*1.5));
        wY = (int)(srodekY+(promien*wspOdstepu)*Math.sin(-1.25*Math.PI+(predkosc/maxPredkosc)*Math.PI*1.5));
        //gLicznikTxt.drawLine(srodekX, srodekY, wX, wY);
        gPredkWskazowka.drawLine(srodekX, srodekY, wX, wY);
	}
	
	public void przyspniomierz(double przyspieszenie, double maxPrzyspieszenie, double maxOpoznienie, int pozX, int pozY, int wielkosc){
		int wY, srodekY;
		if(wielkosc%2!=0) wielkosc+=1;
		double wspPrzysp = maxPrzyspieszenie/(maxPrzyspieszenie+maxOpoznienie);
		double wspOpozn = 1-wspPrzysp;
		srodekY = (int)(pozY+(wielkosc*(double)wspPrzysp));
		//wY = (int)(srodekY - przyspieszenie/maxPrzyspieszenie*(wielkosc/2));
		gPrzyspKrawedz.drawRect(pozX, pozY, 30, wielkosc);
		if(przyspieszenie>=0){
			wY = (int)(srodekY - przyspieszenie/maxPrzyspieszenie*(wielkosc*(double)wspPrzysp));
			gPrzyspPlansza.setColor(new Color(0, 255, 0));
			gPrzyspPlansza.fillRect(pozX+1, wY, 29, srodekY-wY);
		}
		else{
			wY = (int)(srodekY - przyspieszenie/maxOpoznienie*(wielkosc*wspOpozn));
			gPrzyspPlansza.setColor(new Color(255, 0, 0));
			gPrzyspPlansza.fillRect(pozX+1, srodekY, 29, wY-srodekY);
		}
		for(double i=0; i<maxPrzyspieszenie; i+=0.5){
			pozY = (int)(srodekY-wielkosc*wspPrzysp*(i/maxPrzyspieszenie));
			gPrzyspKrawedz.drawLine(pozX, pozY, pozX+30, pozY);
			gPrzyspTxt.drawString(Double.toString(i), pozX+35, pozY);
		}
	}

	
	public void paint(Graphics gd){
		
	}
	
	public void keyPressed(KeyEvent evt){
		//pressed = evt.getKeyChar();
	}
	
	public void keyTyped(KeyEvent evt){
		typed = evt.getKeyChar();
		if(typed=='s') ustHam+=1;
		if(typed=='a') ustHam-=1;
		if(typed=='+') ustGaz+=1;
		if(typed=='-') ustGaz-=1;
	}

	
	public void keyReleased(KeyEvent evt) {
		//relased = evt.getKeyChar();
	}
	
	public void mouseClicked(MouseEvent evt){

	}

	public void mouseEntered(MouseEvent evt){

	}
	
	public void mouseExited(MouseEvent evt){

	}
	
	public void mousePressed(MouseEvent evt){
		myszClick = true;
	}
	
	public void mouseReleased(MouseEvent evt){
		myszClick = false;

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		myszX = e.getX();
		myszY = e.getY();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//myszX = e.getX();
		//myszY = e.getY();
	}
}
