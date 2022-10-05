import java.util.*;
public class Endeco { 
    String firstcode = "L8BNhPsW_+{IS9}|:\"2HlAKmn5", secondcode = "67pqr`1TUJoOdef\\;',./~!tuv";
    String symbols="`1234567890-=~!@ #$%^&*()_+[]\\;',./{}|:\"<>?", thirdcode= "F]@#$%MDE0ZaXYwxyzVgb34cijGQR-= [k^&*()C<>?";

    public String stringReverser(String s){
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
        for(int i=0;i<input.length();i++){
                int x = input.charAt(i);
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
        return stringReverser(encoded.substring(encoded.length()/2,encoded.length())) + encoded.substring(0, encoded.length()/2);
    }

    public String decoder(String input){
        String decoded="";
        if(input.length()%2==1){
            input= input.substring((input.length()/2)+1,input.length()) + stringReverser(input.substring(0, (input.length()/2)+1));
        }
        else{
            input= input.substring(input.length()/2,input.length()) + stringReverser(input.substring(0, input.length()/2));
        }
        for(int i=0;i<input.length();i++){
            int pos=-1;
            while(pos==-1){
                pos=firstcode.indexOf(input.charAt(i));
                if(pos>-1){
                    decoded+=(char)(pos+65);
                    break;
                }
                pos=secondcode.indexOf(input.charAt(i));
                if(pos>-1){
                    decoded+=(char)(pos+97);
                    break;
                }
                pos=thirdcode.indexOf(input.charAt(i));
                if(pos>-1){
                    decoded+=symbols.charAt(pos);
                    break;
                }
            }
        }
        return decoded;
    }

    public static void main(String[] args) {
        Endeco ed = new Endeco();
        Scanner sc = new Scanner(System.in);
        String message="", encoded="", decoded="";
        int turns, copy;
        System.out.println("Press:\n1: Encode\n2: Decode");
        int ch = sc.nextInt();
        sc.nextLine();
        switch(ch){
            case 1:
                System.out.print("Enter Your Secret Message: ");
                message = sc.nextLine();
                Random rd = new Random();
                turns = rd.nextInt(0,10);
                copy = turns;
                while(copy>=0){
                    encoded = ed.encoder(message);
                    message = encoded;
                    copy--;
                }
                encoded+=turns;
                System.out.println("Encoded Message: "+encoded);
                break;

            case 2:
                System.out.print("Enter Your Secret Message: ");
                encoded = sc.nextLine();

                turns = Character.getNumericValue(encoded.charAt(encoded.length()-1));
                if(!(turns>=0 && turns<=9))
                turns=0;

                encoded = encoded.substring(0, encoded.length()-1);

                while(turns>=0){
                    decoded = ed.decoder(encoded);
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
