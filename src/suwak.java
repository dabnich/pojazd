
public class suwak {
	
	double wartosc;
	double maxWartosc;
	
	int pozX, pozY;
	int wskX, wskY;
	
	int dlugosc;
	int dlugoscDraw;
	int szerokosc = 40;
	int uchwytWys = 20;
	
	boolean ustawiany = false;
	
	public suwak(double maxWartosc, int pozX, int pozY, int dlugosc) {
		super();
		this.maxWartosc = maxWartosc;
		this.pozX = pozX;
		this.pozY = pozY;
		this.dlugosc = dlugosc;
		this.dlugoscDraw = dlugosc+uchwytWys;
		wskX = pozX;
		ustawWartosc((double)0);
	}
	
	public void ustawWartosc(double wartosc){
		this.wartosc = wartosc;
		wskY = (int)(pozY+dlugosc - ((double)((wartosc/maxWartosc)*dlugosc)));
	}
	
	public boolean czyKlikniety(int myszX, int myszY){
		if((myszX>=wskX && myszX<=wskX+szerokosc) && (myszY>=wskY && myszY<=wskY+uchwytWys)){
			
			return true;
		}
		return false;
	}
	
	public void ustawWartosc(int myszY){
		double wsp = (double)(pozY+dlugosc-myszY)/dlugosc;
		wskY = myszY;
		if(wsp>1){ 
			wsp=1;
			wskY = pozY;
		}
		if(wsp<=0){
			wsp=0;
			wskY = pozY+dlugosc;
		}
		wartosc = wsp*maxWartosc;
	}
	
}
