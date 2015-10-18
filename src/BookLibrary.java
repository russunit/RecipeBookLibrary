import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class BookLibrary {
	
	private ArrayList<RecipeBook> library;
	Scanner scan = new Scanner(System.in);
	BufferedReader buff;
	
	private RecipeBook currBook = null;
	private Recipe currRecipe = null;
	private Ingredient currIngredient = null;

	public BookLibrary(){
		library = new ArrayList<RecipeBook>();
		
		String cmd = "";
		
		System.out.print("Type \"menu\" to display commands.");
		
		while(cmd!="exit"){
			cmd=cleanInput("");
			
			switch(cmd){
			
			case "menu":
				menu();
				break;
			case "quit":
			case "exit":
			case "q":
				cmd="exit";
				break;
				
			case "viewlibrary":
				viewLibrary();
				break;
			case "choosebook":
				chooseBook();
				break;
			case "addbook":
				addBook();
				break;
			case "viewbook":
				viewBook();
				break;
			case "editbook":
				editBook();
				break;
			case "deletebook":
				deleteBook();
				break;
			case "clearscreen":
				for(int clear = 0; clear < 100; clear++)
				  {
				     System.out.println() ;
				  }
				//http://stackoverflow.com/questions/2979383/java-clear-the-console
				//silly
				break;
				//
				
			//edit book
			case "editbookname":
				editBookName();
				break;
			case "editbookdescription":
				editBookDesc();
				break;
			case "addrecipe":
				addRecipe();
				break;
			case "deleterecipe":
				deleteRecipe();
				break;
			case "chooserecipe":
				chooseRecipe();
				break;
			case "editrecipe":
				editRecipe();
				break;
			
			//edit recipe
			case "editrecipename":
				editRecipeName();
				break;
			case "editrecipedescription":
				editRecipeDesc();
				break;
			case "addingredient":
				//addIngredient();TODO
				break;
			case "deleteingredient":
				//deleteIngredient();TODO
				break;
			case "editingredient":
				//editIngredient();TODO
				break;
			case "finish":
				return;
				
			
			default:
				System.out.println("Command "+cmd+" not found.");
			
			}
			
		}
		
	}

	public static void main(String[] args) {
		new BookLibrary();

	}
	
	private void addBook(RecipeBook b){
		library.add(b);
	}
	
	public RecipeBook getBook(int i){
		return library.get(i);
	}
	
	//command line functions
	private void menu(){
		System.out.println("viewlibrary\naddbook\nclearscreen\nchoosebook\nviewbook\neditbook\ndeletebook\n"
				+"\neditbookname\neditbookdescription\nchooserecipe\naddrecipe\neditrecipe\ndeleterecipe\n"
				+"\neditrecipename\neditrecipedescription\naddingredient\neditingredient\ndeleteingredient\n"
				
				
				
				+ "");
	}
	
	private void viewLibrary(){
		System.out.println();
		if(library.size() > 0){
			for (int x = 0; x < library.size(); x++){
				System.out.print(library.get(x).toString());
			}
		}
		else{
			System.out.println("Library is empty!");
		}
		
	}
	
	private void chooseBook(){
		if(library.size()==0){
			System.out.println("No books available.");
			addBook();
			//return false;
		}
		System.out.println("Books available:");
		for(int x = 0; x < library.size(); x++){
			System.out.println(getBook(x).getName());
		}
		String choice = cleanInput("Choose which book?");
		for(int x = 0; x < library.size(); x++){
			if(getBook(x).getName().equals(choice)){
				currBook=getBook(x);
				System.out.println("Book \""+choice+"\" chosen.");
				return;
			}
		}
		System.out.println("Book \""+choice+"\" not found.");
		chooseBook();//RECURSION
		//System.out.println("TESTTESTTESTTESTTEST");///////////////////////
		
	}
	
	private void addBook(){
		
		String n=cleanInput("Adding new book.\nBook name:");
		String d=cleanInput("Book description:");
		library.add(new RecipeBook(n,d));
		currBook=getBook(library.size()-1);
	}
	
	private void viewBook(){
		if(currBook!=null){
			System.out.print(currBook.toString());
		}
		else{
			chooseBook();
			viewBook();
		}
		
	}
	
	private void editBook(){
		
		if(currBook==null){
			if(library.size()==0){
				System.out.println("No books available.");
				addBook();
			}
			else{
				chooseBook();
			}
			;
		}
		System.out.println("Editing "+currBook.getName()+". \nOptions:\n");
		String option = cleanInput("editname\neditdescription\naddrecipe\ndeleterecipe\neditrecipe\nfinish\n");
		switch(option){
		case "editname":
			editBookName();
			break;
		case "editdescription":
			editBookDesc();
			break;
		case "addrecipe":
			addRecipe();
			break;
		case "deleterecipe":
			deleteRecipe();
			break;
		case "chooserecipe":
			chooseRecipe();
			break;
		case "editrecipe":
			editRecipe();
			break;
		case "finish":
			return;
		default:
			System.out.println("Option "+option+" not found.");
			editBook();
		}
		
	}

	private void deleteBook(){
		if(library.size()==0){
			System.out.println("No books available.");
			//addBook();
			//return;
		}
		else{
			for(int x = 0; x < library.size(); x++){
				System.out.println(getBook(x).getName());
			}
			String choice = cleanInput("Delete which book?");
			for(int x = 0; x < library.size(); x++){
				if(getBook(x).getName().equals(choice)){
					library.remove(x);//deletes it
					currBook=null;
					System.out.println("Book \""+choice+"\" deleted.");
					return;
				}
			}
			System.out.println("Book \""+choice+"\" not found.");
			deleteBook();//RECURSION
		}
	}
	
	//editbook options (currBook is chosen)
	private void editBookName(){
		
		currBook.setName(cleanInput("Rename \""+currBook.getName()+"\" to:"));
	}
	
	private void editBookDesc(){
		currBook.setDescription(cleanInput("New description:"));
	}
	
	private void addRecipe(){
		System.out.println("Adding new recipe.");
		String n = cleanInput("Recipe name:");
		String d = cleanInput("Recipe description:");
		currBook.addRecipe(new Recipe(n,d));
		currRecipe = currBook.getRecipe(currBook.getSize()-1);
		String c = "";
		while(!c.equals("done")){
			c = cleanInput("Ingredient name, or \"done\" to finish.");
			if(!c.equals("done")){
				String iU = cleanInput("Unit of measurement?");
				double iQ = cleanInputNum("Quantity(amount)?");
				currRecipe.addIngredient(new Ingredient(c, iQ, iU));
				//TODO to check for number, make method cleanInputNum()
			}
			
		}
		String done=cleanInput("Add another recipe? (Y/N)");
		done=done.toLowerCase();
		if(done.equals("y")|done.equals("yes")){
			addRecipe();
		}
	}
	
	private void deleteRecipe(){
		if(currBook.getSize()==0){
			System.out.println("No recipes available.");
			//return;
		}
		else{
			for(int x = 0; x < currBook.getSize(); x++){
				System.out.println(currBook.getRecipe(x).getName());
			}
			String choice = cleanInput("Delete which recipe?");
			for(int x = 0; x < currBook.getSize(); x++){
				if(currBook.getRecipe(x).getName().equals(choice)){
					currBook.deleteRecipe(x);//deletes it
					currRecipe=null;
					System.out.println("Recipe \""+choice+"\" deleted.");
					return;
				}
			}
			System.out.println("Recipe \""+choice+"\" not found.");
			deleteRecipe();//RECURSION
		}
	}
	
	private void chooseRecipe(){
		if(currBook.getSize()==0){
			System.out.println("No recipes available.");
			addRecipe();
			//return false;
		}
		System.out.println("Recipes available:");
		for(int x = 0; x < currBook.getSize(); x++){
			System.out.println(currBook.getRecipe(x).getName());
		}
		String choice = cleanInput("Choose which recipe?");
		for(int x = 0; x < currBook.getSize(); x++){
			if(currBook.getRecipe(x).getName().equals(choice)){
				currRecipe=currBook.getRecipe(x);
				System.out.println("Recipe \""+choice+"\" chosen.");
				return;
			}
		}
		System.out.println("Recipe \""+choice+"\" not found.");
		chooseRecipe();//RECURSION
		//System.out.println("TESTTESTTESTTESTTEST");///////////////////////
		
	}
	
	private void editRecipe(){
		if(currRecipe==null){
			if(currBook.getSize()==0){
				System.out.println("No recipes available.");
				addRecipe();
			}
			else{
				chooseRecipe();
			}
		}
		System.out.println("Editing "+currRecipe.getName()+". \nOptions:\n");
		String option2 = cleanInput("editname\neditdescription\naddingredient\ndeleteingredient\neditingredient\nfinish\n");
		switch(option2){
		case "editname":
			editRecipeName();
			break;
		case "editdescription":
			editRecipeDesc();
			break;
		case "addingredient":
			//addIngredient();TODO
			break;
		case "deleteingredient":
			//deleteIngredient();TODO
			break;
		case "editingredient":
			//editIngredient();TODO
			break;
		case "finish":
			return;
		default:
			System.out.println("Option "+option2+" not found.");
			editRecipe();
		}
	}
	
	//editrecipe options(currRecipe is set)
	private void editRecipeName(){
		currRecipe.setName(cleanInput("Rename \""+currRecipe.getName()+"\" to:"));
	}
	
	private void editRecipeDesc(){
		currRecipe.setDesc(cleanInput("New description:"));
	}
	
	//inner mechanics	
	private void LoadLibrary(){
		//TODO
	}
	
	
	private String cleanInput(String q){//question
		
		System.out.print(q+"\n>");
		String s = scan.nextLine();
		
		while(!isClean(s)){
			System.out.println("No special characters please.");
			System.out.println(q);
			s=scan.nextLine();
		}
		return s;
	}
	
	private double cleanInputNum(String q){//question
		double inNum = 0;
		System.out.print(q+"\n>");
			try
			{
				inNum = scan.nextDouble();
				scan.nextLine();
				
			}
			catch(InputMismatchException e)
			{
				System.out.println("Please input a number.");
				scan.nextLine();//clears scanner buffer
				inNum = cleanInputNum(q);
				//allDone = false;//loops back around
			}
		
		return inNum;
	}
	
	
	private boolean isClean(String str){
		
		if(str.contains("~")|str.contains("$")|str.contains(":")|str.contains("@")){
			return false;}
		return true;
	}

}
