import java.util.*;
public class Endeco { 
    String firstcode = "L8BNhPsW_+{IS9}|:\"2HlAKmn5", secondcode = "67pqr`1TUJoOdef\\;',./~!tuv";
    String symbols="`1234567890-=~!@ #$%^&*()_+[]\\;',./{}|:\"<>?", thirdcode= "F]@#$%MDE0ZaXYwxyzVgb34cijGQR-= [k^&*()C<>?";

    public String stringReverser(String s){ // To reverse a String
        if(s.length()==1){
            return s;
        }
        else{
            int mid = s.length()/2;
            return stringReverser(s.substring(mid,s.length())) + stringReverser(s.substring(0, mid));
        }
    }

    public String encoder(String input){
        String encoded="";
        for(int i=0;i<input.length();i++){ // Traversing the input
                int x = input.charAt(i);  // Extracting characters
                if(x>=65 && x<=90){  // if character is capital alphabet, we encode it with first code
                    encoded += firstcode.charAt(x-65);
                }
                else if(x>=97 && x<=122){ // if character is small alphabet, we encode it with second code
                    encoded += secondcode.charAt(x-97);
                }
                else{ // if character is a symbol, we encode it with third code
                    int id = symbols.indexOf(x);
                    char ch = thirdcode.charAt(id);
                    encoded += ch;
                }
        }
        return stringReverser(encoded.substring(encoded.length()/2,encoded.length())) + encoded.substring(0, encoded.length()/2); // Reverse of 2nd half + 1st half of encoded text
    }

    public String decoder(String encoded){
        String decoded="";
        if(encoded.length()%2==1){ // rolling back changes made in line 32
            encoded= encoded.substring((encoded.length()/2)+1,encoded.length()) + stringReverser(encoded.substring(0, (encoded.length()/2)+1)); // if string length is odd
        }
        else{
            encoded= encoded.substring(encoded.length()/2,encoded.length()) + stringReverser(encoded.substring(0, encoded.length()/2)); // if string length is even
        }
        for(int i=0;i<encoded.length();i++){ // traversing the encoded message
            int pos=-1;
            while(pos==-1){
                pos=firstcode.indexOf(encoded.charAt(i)); // finding index of character from first code
                if(pos>-1){ // if found
                    decoded+=(char)(pos+65); // add it to decoded
                    break;
                }
                pos=secondcode.indexOf(encoded.charAt(i)); // finding index of character from second code
                if(pos>-1){ // if found
                    decoded+=(char)(pos+97); // add it to decoded
                    break;
                }
                pos=thirdcode.indexOf(encoded.charAt(i)); // finding index of character from third code
                if(pos>-1){ // if found
                    decoded+=symbols.charAt(pos); // add it to decoded
                    break;
                }
            }
        }
        return decoded; // returning decoded message
    }

    public static void main(String[] args) {
        Endeco ed = new Endeco();
        Scanner sc = new Scanner(System.in);
        String message="", encoded="", decoded="";
        int turns, copy;
        System.out.println("Press:\nE: Encode\nD: Decode");
        String ch = sc.next().toUpperCase();
        sc.nextLine();
        switch(ch){
            case "E":
                System.out.print("Enter Your Secret Message: ");
                message = sc.nextLine();
                Random rd = new Random();
                turns = rd.nextInt(0,10); // This is the number of times our message will be encoded
                copy = turns; // copying value for further use
                while(copy>=0){ // encoding message turns number of time
                    encoded = ed.encoder(message); // encoding
                    message = encoded;
                    copy--;
                }
                if(encoded.length()%2==0)
                encoded=encoded+turns; // adding number of times encoded to help in the decoding process
                else
                encoded=turns+encoded; // adding number of times encoded to help in the decoding process
                System.out.println("Encoded Message: "+encoded);
                break;

            case "D":
                System.out.print("Enter Your Secret Message: ");
                encoded = sc.nextLine();

                if((encoded.length()-1)%2==0){
                    turns = Character.getNumericValue(encoded.charAt(encoded.length()-1)); // Extracting number of times message has been encoded
                    encoded = encoded.substring(0, encoded.length()-1); // removing the number of turns from encoded message
                }
                else{
                    turns = Character.getNumericValue(encoded.charAt(0)); // Extracting number of times message has been encoded
                    encoded = encoded.substring(1, encoded.length()); // removing the number of turns from encoded message
                }

                if(!(turns>=0 && turns<=9)) // if the user does not include number of turns we assume 1 turn
                turns=0;

                while(turns>=0){
                    decoded = ed.decoder(encoded); // decoding
                    encoded = decoded;
                    turns--;
                }
                System.out.println("Decoded Message: "+decoded);
                break;

            default:
                System.out.println("Wrong Choice!");
        }
        sc.close();
    }
}
