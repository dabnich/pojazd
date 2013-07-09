
public class suwak {
	
	double wartosc;
	double maxWartosc;
	
	int pozX, pozY;
	int wskX, wskY;
	
	int dlugosc;
	int dlugoscDraw;
	int szerokosc = 40;
	int uchwytWys = 20;
	
	public suwak(double maxWartosc, int pozX, int pozY, int dlugosc) {
		super();
		this.maxWartosc = maxWartosc;
		this.pozX = pozX;
		this.pozY = pozY;
		this.dlugosc = dlugosc;
		this.dlugoscDraw = dlugosc+uchwytWys;
		wskX = pozX;
		ustawWartosc(0);
	}
	
	public void ustawWartosc(double wartosc){
		this.wartosc = wartosc;
		wskY = (int)(pozY+dlugosc - ((double)((wartosc/maxWartosc)*dlugosc)));
	}
	
}
