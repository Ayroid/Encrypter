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
    public static void main(String[] args) {
        Endeco en = new Endeco();
        System.out.println(en.encoder("abcd"));
    }
}
