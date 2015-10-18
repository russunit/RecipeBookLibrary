import java.util.ArrayList;
import java.util.Iterator;


public class RecipeBook {
	
	private String bookName;
	private String bookDesc;
	private ArrayList<Recipe> book;

	public RecipeBook() {
		setName("");
		setDescription("");
		book = new ArrayList<Recipe>();
	}
	
	public RecipeBook(String n, String d){
		setName(n);
		setDescription(d);
		book = new ArrayList<Recipe>();
	}
	
	public RecipeBook(String fileString){/*TODO*/};
	
	public void setName(String s){
		bookName = s;
	}
	
	public void setDescription(String s){
		bookDesc = s;
	}
	
	public void addRecipe(Recipe r){//package?
		book.add(r);
	}
	
	public Recipe getRecipe(int i){
		return book.get(i);
	}
	
	public String getName(){
		return bookName;
	}
	
	public String getDesc(){
		return bookDesc;
	}
	
	public int getSize(){
		return book.size();
	}
	
	public void deleteRecipe(int i){
		book.remove(i);
	}
	
	public String toString(){
		String s = bookName + "\n"+bookDesc+"\n-----------------\n";
		Iterator<Recipe> it = book.iterator();
		while(it.hasNext()){
			s += it.next().toString() + "\n\n";
		}
		
		return s;
	}
	
	public String toFile(){
		String s = bookName + "@" + bookDesc + "@";
		Iterator<Recipe> it = book.iterator();
		while(it.hasNext()){
			s += it.next().toFile() + "@";
		}
		
		return s;
	}
	//fileformat = bookName@bookDesc@<Recipe1>@<Recipe2>@

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
