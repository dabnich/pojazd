public class naped {
	
	double moc;
	double sila;
	double maxSila;
	
	public naped(double maxSila){
		this.maxSila = maxSila;
	}
	
	public void kontroluj(){
		if(sila>maxSila) sila = maxSila;
		if(sila<0) sila = 0;  //w przyszlosci to sie zlikwiduje - zastosuje ci�g wsteczny (BHP)
	}
	
	
}
