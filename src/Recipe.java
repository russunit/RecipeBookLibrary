import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Recipe {
	
	private String rName;
	private String rDesc;//description
	private ArrayList<Ingredient> rList;//ingredient name, ingredient
	//private ArrayList<String> rProps;//properties (vegan, low-carb, low-sodium, etc)
	private int[] rProps;
	//properties:
	//vegan,vegetarian,sugarfree,lowsalt,lowfat,hasnuts,glutenfree
	
	//private ArrayList<Ingredient> rArray;

	public Recipe() {
		setName("");
		setDesc("");
		rList = new ArrayList<Ingredient>();
		rProps = new int[7];
		for(int x = 0;x<7;x++){rProps[x]=0;}
	}
	
	public Recipe(String n, String d) {
		setName(n);
		setDesc(d);
		rList = new ArrayList<Ingredient>();
		rProps = new int[7];
		for(int x = 0;x<7;x++){rProps[x]=0;}
	}
	
	public Recipe(String fileString){/*TODO*/}//takes in string from file
	
	public void setName(String s){rName = s;}
	
	public void setDesc(String s){rDesc = s;}
	
	public void addIngredient(Ingredient i){
		rList.add(i);
	}
	
	private void addIngredient(String n, double q, String u){
		Ingredient i = new Ingredient(n, q, u);//name, quantity, unit
		rList.add(i);
	}
	
	private boolean addProperty(int i){
		if(rProps[i]==0){
			rProps[i]=1;
			return true;
		}
		return false;
	}
	
	private boolean removeProperty(int i){
		if(rProps[i]==1){
			rProps[i]=0;
			return true;
		}
		return false;
	}
	
	public String getName(){
		return rName;
	}
	
	public String getDesc(){
		return rDesc;
	}
	
	public int getSize(){
		return rList.size();
	}
	
	public String toString(){
		String s = rName+"\n";
		Iterator<Ingredient> it = rList.iterator();
		while(it.hasNext()){
			s += it.next().toString() + "\n";
		}
		return s;
	}
	
	public String toFile(){
		String s = rName + "|"+rDesc+"|"+rProps+"|";
		Iterator<Ingredient> it = rList.iterator();
		while(it.hasNext()){
			s += it.next().toFile() + "|";
		}
		return s;
	}
	//fileformat = rName|rDesc|<Ingredient1>|<Ingredient2>|
	
	
	
	
	

}
