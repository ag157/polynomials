import java.util.*;
public class Main {

    public static int[] polyToArr(String poly) {

        int arr[] = new int[100];
        String coeff = "", power = "";
        boolean flag = false;
        for (int i = 0; i < poly.length(); i++) {

            if (!flag) {
                if (poly.charAt(i) == 'x')
                    flag = true;
                else
                    coeff+=poly.charAt(i);
                    //System.out.println("coeff"+coeff);
            }
            else{
                if(poly.charAt(i)=='+'||poly.charAt(i)=='-'){
                    int temp=0;

                    if(power!="")
                        temp =Integer.parseInt(power);
                    arr[temp]=Integer.parseInt(coeff);
                    if(poly.charAt(i)=='-')
                        coeff="-";
                    else
                        coeff="";
                    power="";
                    flag=false;
                }
                else{
                    if(poly.charAt(i)!='^')
                        power+=poly.charAt(i);
                    //System.out.println("power"+ power);
                }
            }
        }
        int temp=0;
        if(power!="")
            temp =Integer.parseInt(power);
        arr[temp]=Integer.parseInt(coeff);



        return arr;
    }

    public static String arrToPloy(int a[]){
        String resultPoly="";
        for(int i=99;i>=0;i--){
            if(a[i]>0){
                resultPoly+="+"+a[i]+"x^"+i;
            }
            else if(a[i]<0){
                resultPoly+=a[i]+"x^"+i;
            }
        }
        return resultPoly;
    }

    public static String add(String Poly1,String Poly2){
        int poly1arr[] = new int[100];
        int poly2arr[] = new int[100];
        int sum[]=new int[100];
        poly1arr = polyToArr(Poly1);
        poly2arr = polyToArr(Poly2);
        for(int i=0;i<100;i++){
            sum[i]=poly1arr[i]+poly2arr[i];
        }
        return arrToPloy(sum);
    }
    public static String postProcess(String str){
        String final_string="";
        if(str.charAt(str.length()-1)=='0')
            final_string=str.substring(0,str.length()-3);
        else
            final_string=str.substring(0);
        if(str.charAt(0)=='+')
            final_string=final_string.substring(1);
        return final_string;
    }
    public static String subtraction(String Poly1,String Poly2){
        int poly1arr[] = new int[100];
        int poly2arr[] = new int[100];
        int sub[]=new int[100];
        poly1arr = polyToArr(Poly1);
        poly2arr = polyToArr(Poly2);
        for(int i=0;i<100;i++){
            sub[i]=poly1arr[i]-poly2arr[i];
        }
        return arrToPloy(sub);
    }
    public static String multiply(String poly1,String poly2)
    {
        int poly1arr[] = new int[100];
        int poly2arr[] = new int[100];
        int mult[]=new int[300];
        poly1arr = polyToArr(poly1);
        poly2arr = polyToArr(poly2);
        for(int i=0;i<100;i++) {
            for (int j=0;j<100;j++)
            {
                if(poly1arr[i]!=0)
                {

                    mult[i+j]+=poly1arr[i]*poly2arr[j];
                }
            }

        }
        return arrToPloy(mult);
    }
    public static void main(String []args)
    {
        String Poly1="3x^5+2x^3+4",Poly2="-5x^5+2x^3-1";
       // polyToArr(Poly2);
       String result=add(Poly1,Poly2);
       System.out.println("Addition : "+postProcess(result));
       result=subtraction(Poly1,Poly2);
        System.out.println("Subtraction : "+postProcess(result));
        result=multiply(Poly1,Poly2);
        System.out.println("Multiplication : "+postProcess(result));
    }
}
