import java.util.ArrayList;

//import klawiatura.Constants;



public class silnik {
	
	double pozycja;
	public long maxSila;
	public double maxMoc;
	long obroty;
	long maxObroty;
	long minObroty;
	boolean wlaczony;
	double moc;
	ArrayList<Double> rpm = new ArrayList<Double>();
	ArrayList<Double> moment = new ArrayList<Double>();
	
	
	
	public silnik(long maxSila){
		this.maxSila = maxSila;
		wlaczony=false;
	}
	
	public void ustawPozycje(double pozycja){
		this.pozycja = pozycja;
	}
	
	public void ustawMoment(double[] rpm, double[] moment){
		this.maxMoc=0;
		for(int i=0; i<rpm.length; i++){
			if(moment[i]>maxMoc) this.maxMoc = moment[i];
			this.rpm.add(rpm[i]);
			this.moment.add(moment[i]);
		}
		minObroty = (long)rpm[0]+2;
		maxObroty = (long)rpm[rpm.length-1]-1;
	}
	
	public double obliczMoc(){
		if(obroty>=maxObroty || obroty<minObroty || wlaczony==false){ 
			moc=0;
			return (double) moc;
		}
		int n=1;
		for(int i=0; i<rpm.size(); i++){
			if(rpm.get(i)>obroty){ 
				n=i;
				break;
			}
		};
		moc = moment.get(n-1) + (moment.get(n)-moment.get(n-1))*((obroty-rpm.get(n-1))/(rpm.get(n)-rpm.get(n-1)));
		moc = moc*(pozycja/(double)100);
		return moc;
	}
	
	public void kontroluj(){
		obliczMoc();
		if(obroty>=maxObroty || obroty<minObroty){ 
			moc=0;
		}
		if(obroty<minObroty){
			wlaczony=false;
			obroty=0;
		}
	}
	
	public void uruchom(){
		wlaczony = true;
		obroty=minObroty;
	}
	

	
}
