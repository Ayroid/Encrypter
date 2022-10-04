public class Endeco { 
    static String secretcode = "jxoqksculimpzgafnrbvtwehyd";
    public String encoder(String input){
        String encoded="";
        for(int i=0;i<input.length();i++){
            int x=input.toUpperCase().charAt(i)-64, y=0;
            if(x%2!=0)
                y=(((x+30)%26)+30)%26;
            else
                y = (((x+31)%26)+31)%26;
            encoded+=secretcode.charAt(y-1);
        }
        return encoded;
    }
    public static void main(String[] args) {import java.util.Scanner;

        public class Endeco { 
            String firstcode = "jHxLqYk4NI~liRg!{;KfnrAhd3", secondcode = "QvE^wG6Cys}|:<PmuD?[obFgJZ";
            String symbols="`1234567890-=~!@#$%^&*()_+[]\\;',./{} |:\"<>?",
                thirdcode= "@+\"NtOcS>=a90-78',.\\5p1 UT]2eBX#$%_zV`&*()/";
            
            public String wordReverser(String s){
                if(s.length()==1){
                    return s;
                }
                else{
                    int mid = s.length()/2;
                    return wordReverser(s.substring(mid,s.length())) + wordReverser(s.substring(0, mid));
                }
            }
            
            public String encoder(String input){
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
                            encoded += thirdcode.charAt(symbols.indexOf(x));
                        }
                    }
                    i=index+1;
                }
                encoded = wordReverser(encoded.substring(0, encoded.length()/2))+encoded.substring(encoded.length()/2, encoded.length());
                return encoded;
            }
            public static void main(String[] args) {
                Endeco ed = new Endeco();
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter Your Secret Message: ");
                String message = sc.nextLine()+" ";
                String encoded = ed.encoder(message);
                System.out.println("Encoded Message: "+encoded);
                sc.close();
            }
        }
        
        Endeco en = new Endeco();
        System.out.println(en.encoder("abcd"));
    }
}
