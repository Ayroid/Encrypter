import java.util.*;
public class Endeco { 
    String firstcode = "jHxLqYkiR4NI~!{;KflgnrAhd3", secondcode = "QvE^wG6C s}|:<PmuD?[obFgJZ";
    String symbols="`1234567890-=~!@#$%^&*()_+[]\\;',./{} |:\"<>?", thirdcode= "@+\"NtOcS>=a90-78',.\\5p1yUT]2eBX#$%_zV`&*()/";
    
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
        String message, encoded, decoded;
        System.out.println("Press:\n1: Encode\n2: Decode");
        int ch = sc.nextInt();
        sc.nextLine();
        switch(ch){
            case 1:
                System.out.print("Enter Your Secret Message: ");
                message = sc.nextLine();
                System.out.println(message.length());
                encoded = ed.encoder(message);
                System.out.println("Encoded Message: "+encoded);
                System.out.println(encoded.length());
                break;

            case 2:
                System.out.print("Enter Your Secret Message: ");
                encoded = sc.nextLine();
                decoded = ed.decoder(encoded);
                System.out.println("Decoded Message: "+decoded);
                break;

            default:
                System.out.println("Wrong Choice!");
        }
        sc.close();
    }
}
