import java.util.Scanner;
import java.util.HashMap;

public class Main{
    private static HashMap<Character,Character> brackets = new HashMap<>();
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        String input = "";
        System.out.print("Enter pairs of brackets - ");
        String bracketsIn = in.nextLine();
        if(bracketsIn.length()%2!=0){
            System.out.println("\t\t\tYou haven't entered pairs!");
            return;
        }
        for(int i=0;i<bracketsIn.length();i+=2)
            brackets.put(bracketsIn.charAt(i),bracketsIn.charAt(i+1));
        String text = "";
        System.out.println("\t\tEnter 'q' for quit");
        do{
            text += input;
            System.out.print("Enter text - ");
            input = in.nextLine();
        }while(!input.equals("q"));
        String bracketsInText = "";
        for(int i=0;i<text.length();i++){
            if(isItBracket(text.charAt(i)))
                bracketsInText += text.charAt(i);
        }
        System.out.println(areCorrect(bracketsInText));
        System.out.println("Check this - " + bracketsInText);
    }
    private static boolean areCorrect(String br){
        int sameFound = 0;
        for(int i=1;i<br.length();i++){
            if(br.charAt(0) == br.charAt(i)){
                sameFound++;
                continue;
            }if(br.charAt(i) == getOpposite(br.charAt(0))){
                if( sameFound>0){
                    sameFound--;
                }else{
                    if(i==1){
                        if(br.length()==2) return true;
                        return areCorrect(br.substring(i+1,br.length()));
                    }else if(i==br.length()-1){
                        return areCorrect(br.substring(1,i));
                    }else{
                        return areCorrect(br.substring(1,i)) && areCorrect(br.substring(i+1,br.length()));
                    }
                }
            }
        }
        return false;
    }
    private static char getOpposite(char c){
        for(char i : brackets.keySet()){
            if( c == i ) return brackets.get(i);
        }
        return '0';
    }
    private static boolean isItBracket(char c){
        for(char i : brackets.keySet()){
            if(i==c) return true;
            if(c==brackets.get(i)) return true;
        }
        return false;
    }
}