

public class pojazd {
	
	public double masa;
	public double wspTarcia = 0.013;
	double wspAero;
	double oporMech = 1.2;
	
	double szerokosc;
	double wysokosc;
	double zaokrPoj = 0.79;
	
	
	double predkosc=3;
	double przyspieszenie;
	double dystans;
	
	double opor;
	double oporT;
	double oporP;
	double mocOporow;
	
	silnik silnik;
	hamulec hamulec;
	naped naped;

	
	public void ustawSilnik(double rpm[], double[] moment){
		double max=0;
		for(int i=0; i<moment.length; i++){
			if(max<moment[i])max = moment[i];
		}
		this.silnik = new silnik((long)( (double)max/40 ));
		this.silnik.ustawMoment(rpm, moment);
	}
	
	public void ustawNaped(double maxSila){
		naped = new naped(maxSila);
	}
	
	public void ustawHamulec(double maxCisnienie, double maxPrzeplyw, double skutecznosc, long opoznienie){
		hamulec = new hamulec(maxCisnienie, maxPrzeplyw, skutecznosc, opoznienie);
	}
	
	
	public pojazd(long masa, double szerokosc, double wysokosc, double wspAero){
		this.masa = masa;
		this.szerokosc = szerokosc;
		this.wysokosc = wysokosc;
		this.wspAero = wspAero;
	}
	
	public double obliczOpor(){
		oporT = (double)masa*Constants.grawitacja*wspTarcia;
		oporP = wspAero*(Constants.gestPowietrza/2)*(szerokosc*wysokosc*zaokrPoj)*(predkosc*predkosc);
		mocOporow = oporP*predkosc+oporT*predkosc;
		return oporT+oporP;
	}
	
	public double obliczMoc(){
		return oporT*predkosc+oporP*predkosc+masa*przyspieszenie*oporMech*predkosc;
	}
	
	
	
	
	public void ustawGaz(double pozycja){
		naped.ustawPozycje(pozycja);
	}
	
	public void hamowanie(double pozycja){
		hamulec.ustawPozycje(pozycja);
	}
	
	
	
	public void kontroluj(){

		dystans += predkosc*((double)1/(double)Constants.FPS);
		naped.kontroluj();
		hamulec.kontroluj();
		//przyspieszenie = ((naped.sila)/oporMech - obliczOpor())/masa;
		przyspieszenie = (naped.moc - oporT*predkosc - oporP*predkosc)/(masa*oporMech*predkosc);
		przyspieszenie-=hamulec.sila;
		
		if(predkosc>0 || przyspieszenie>=0){
			predkosc+=(przyspieszenie/(double)Constants.FPS);
		}
		else{
			przyspieszenie=0;
			predkosc=0;
		}
	}
	
	public void wlaczSilnik(){
		silnik.uruchom();
	}
	

	

	

}
