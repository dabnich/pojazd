public class naped {
	
	double moc;
	double sila;
	double maxSila;
	double pozycja=100; //uzycie gdy nie uwzglednimy silnika
	
	public naped(double maxSila){
		this.maxSila = maxSila;
	}
	
	public void kontroluj(){
		sila = maxSila*(pozycja/100);
		if(sila>maxSila) sila = maxSila;
		if(sila<0) sila = 0;  //w przyszlosci to sie zlikwiduje - zastosuje ci¹g wsteczny (BHP)
	}
	
	public void ustawPozycje(double pozycja){
		this.pozycja = pozycja;
	}

}
