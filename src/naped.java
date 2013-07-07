public class naped {
	
	double moc;
	double sila;
	double maxMoc;
	double pozycja=100; //uzycie gdy nie uwzglednimy silnika
	
	public naped(double maxMoc){
		this.maxMoc = maxMoc;
	}
	
	public void kontroluj(){
		moc = maxMoc*(pozycja/100);
		//if(sila>maxSila) sila = maxSila;
		if(sila<0) sila = 0;  //w przyszlosci to sie zlikwiduje - zastosuje ci¹g wsteczny (BHP)
	}
	
	public void ustawPozycje(double pozycja){
		this.pozycja = pozycja;
	}

}
