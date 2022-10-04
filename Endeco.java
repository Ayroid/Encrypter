import java.util.*;
public class Endeco { 
    String firstcode = "jHxLqYkiR4NI~!{;KflgnrAhd3", secondcode = "QvE^wG6C s}|:<PmuD?[obFgJZ";
    String symbols="`1234567890-=~!@#$%^&*()_+[]\\;',./{} |:\"<>?",
        thirdcode= "@+\"NtOcS>=a90-78',.\\5p1yUT]2eBX#$%_zV`&*()/";
    
    public String wordReverser(String s){
        if(s.trim().length()==1){
            return s;
        }
        else{
            int mid = s.length()/2;
            return wordReverser(s.substring(mid,s.length())) + wordReverser(s.substring(0, mid));
        }
    }
    
    public String encoder(String input){
        input+=" ";
        String encoded="";
        for(int i=0;i<input.length();){
            int index = input.indexOf(" ", i);
            String word = input.substring(i, index);
            word = wordReverser(word);
            for(int j=0;j<word.length();j++){
                int x = word.charAt(j);
                if(x>=65 && x<=90){
                    encoded += firstcode.charAt(x-65);
                }
                else if(x>=97 && x<=122){
                    encoded += secondcode.charAt(x-97);
                }
                else{
                    int id = symbols.indexOf(x);
                    char ch = thirdcode.charAt(id);
                    encoded += ch;
                }
            }
            encoded+='V';
            i=index+1;
        }
        // return encoded.substring(0,encoded.length()-1);
        return encoded;
    }

    public String decoder(String input){
        String halfdec="", decoded="";
        for(int i=0;i<input.length();i++){
            int pos=-1;
            while(pos==-1){
                pos=firstcode.indexOf(input.charAt(i));
                if(pos>-1){
                    halfdec+=(char)(pos+65);
                    break;
                }
                pos=secondcode.indexOf(input.charAt(i));
                if(pos>-1){
                    halfdec+=(char)(pos+97);
                    break;
                }
                pos=thirdcode.indexOf(input.charAt(i));
                if(pos>-1){
                    halfdec+=symbols.charAt(pos);
                    break;
                }
            }
        }
        for(int i=0;i<halfdec.length();){
            int index = halfdec.indexOf(" ", i);
            String word = halfdec.substring(i, index);
            decoded += wordReverser(word)+" ";
            i=index+1;
        }
        return decoded;
    }

    public static void main(String[] args) {
        Endeco ed = new Endeco();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Secret Message: ");
        String message = sc.nextLine();
        String encoded = ed.encoder(message);
        System.out.println("Encoded Message: "+encoded);

        encoded = ed.encoder(encoded);
        System.out.println("Encoded: "+encoded);

        String decoded = ed.decoder(encoded).trim();
        System.out.println("Decoded Message: "+decoded);

        decoded = ed.decoder(decoded).trim();
        System.out.println("Decoded: "+decoded);
        
        sc.close();
    }
}
