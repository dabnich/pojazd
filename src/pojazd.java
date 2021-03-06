

public class pojazd {
	
	public double masa;
	public double wspTarcia = 0.012;
	double wspAero;
	double oporMech = 1.15;
	
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

	
	public void ustawSilnik(double rpm[], double[] moment){
		double max=0;
		for(int i=0; i<moment.length; i++){
			if(max<moment[i])max = moment[i];
		}
		this.silnik = new silnik((long)( (double)max/40 ));
		this.silnik.ustawMoment(rpm, moment);
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
		if(przyspieszenie<=0)
			return oporT*predkosc+oporP*predkosc;
		else
			return oporT*predkosc+oporP*predkosc+masa*przyspieszenie*oporMech;
	}
	
	public void ustawGaz(double pozycja){
		silnik.ustawPozycje(pozycja);
	}
	
	public void hamowanie(double pozycja){
		hamulec.ustawPozycje(pozycja);
	}
	
	
	
	public void kontroluj(){
		//przyspieszenie = (double)(silnik.pozycja/(double)100)/(double)Constants.FPS;
		dystans += predkosc*((double)1/(double)Constants.FPS);
		
		//opor = obliczOpor();
		//if(predkosc==0)opor=0;
		//if(silnik.obroty)
		silnik.kontroluj();
		hamulec.kontroluj();
		silnik.obroty = (long)(silnik.minObroty+(predkosc/(double)45)*(double)(silnik.maxObroty-silnik.minObroty));
		obliczOpor();
		//przyspieszenie = ( (double)silnik.obliczMoc()-oporT*predkosc-oporP*predkosc )/masa/oporMech;
		if(silnik.moc>0){
			przyspieszenie = (double)(silnik.maxSila/oporMech - obliczOpor())/masa;
			double wsp = (double)(silnik.maxMoc/obliczMoc())*(silnik.moc/silnik.maxMoc);
			przyspieszenie = (((silnik.pozycja/(double)100)*(double)silnik.maxSila)/oporMech - obliczOpor())/masa;
			if(przyspieszenie>0) przyspieszenie *= wsp;
		}
		else{
			przyspieszenie = 0-(obliczOpor()/masa);
		}
		if(predkosc>0 || przyspieszenie>=0){
			przyspieszenie-=hamulec.sila;
			predkosc+=(przyspieszenie/(double)Constants.FPS);
		}
		else{

		}
	}
	
	public void wlaczSilnik(){
		silnik.uruchom();
	}
	

	

	

}
