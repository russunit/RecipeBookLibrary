
public class Ingredient {
	
	private String iName;
	private double iQty;
	private String iUnit;
	
	public Ingredient(){
		setName("");
		setQty(0);
		setUnit("");
	}

	public Ingredient(String n, double q, String u){//name, quantity, unit
		setName(n);
		setQty(q);
		setUnit(u);
	}
	
	public Ingredient(String fileString){/*TODO*/};//takes in string from file
	
	public String getName(){return iName;}
	
	public double getQty(){return iQty;}
	
	public String getUnit(){return iUnit;}
	
	private void setName(String s){iName = s;}
	
	private void setQty(double d){iQty = d;}
	
	private void setUnit(String s){iUnit = s;}
	
	public String toString(){
		String s = iQty+" "+iUnit+" "+iName;
		return s;
	}
	
	public String toFile(){
		String s = iQty+"~"+iUnit+"~"+iName;
		return s;
	}
	//fileformat = iQty~iUnit~iName~
	

}
