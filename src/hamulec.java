
public class hamulec {
	
	double maxCisnienie;
	public double cisnienie;
	double maxPrzeplyw;
	long opoznienie;
	double pozycja;
	double skutecznosc;
	double sila=0;
	
	public hamulec(double maxCisnienie, double maxPrzeplyw, double skutecznosc, long opoznienie){
		this.maxCisnienie = maxCisnienie;
		this.maxPrzeplyw = maxPrzeplyw;
		this.opoznienie = opoznienie;
		this.skutecznosc = skutecznosc;
	}
	
	public boolean ustawPozycje(double pozycja){
		this.pozycja = pozycja;
		return true;
	}
	
	public void kontroluj(){
		//Date czas = new Date();
		//long ms = czas.getTime();
		
		double wsp;
		wsp = pozycja/(double)(100);
		
		
		if(cisnienie< (maxCisnienie*wsp)){
			if(cisnienie+maxPrzeplyw/Constants.FPS < (maxCisnienie*wsp))
				cisnienie = cisnienie+(maxPrzeplyw/Constants.FPS);
			else 
				cisnienie = (maxCisnienie*wsp);
		}
		else {
			if(cisnienie-maxPrzeplyw/Constants.FPS > (maxCisnienie*wsp)){
				cisnienie = cisnienie-(maxPrzeplyw/Constants.FPS);
			}
			else 
				cisnienie = (maxCisnienie*wsp);
		}
		
		this.sila = (double)(cisnienie/maxCisnienie) * (double)skutecznosc;
	}
	
	
}
