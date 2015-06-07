
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import sun.misc.Regexp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iramadan
 */
public class Test {
    
    static String LongestWord(String str){
        String[] temp = str.split(" ");
        int size=0;
        int index = -1;
        for( int i =0;i < temp.length; i ++ ){
            if (temp[i].length() > size){
                size = temp[i].length();
                index = i;
            }
        }
        return temp[index];
    }
    
    static boolean isPrime(int num){
        boolean result=  true;
        for( int i =num-1 ; i > 1; i-- ){
            if((num % i) == 0 )
                result =  false;
            
        }
        return result;
    }
    static String RLE(String str){
        String temp ="";
        int counter = 1;
        char c;
        c = str.charAt(0);
        for(int i =1 ; i < str.length() ; i++){
            if(c == str.charAt(i)){
                counter++;
                c = str.charAt(i);
            }else{
                
                
                temp += String.valueOf(counter) + String.valueOf(c);
                c = str.charAt(i);
                counter = 1;
            }
                
            
        }
        temp += String.valueOf(counter) + String.valueOf(c);
        return temp ;
    }
    static int PrimeMover(int index){
        boolean result=  true;
        int num = 1;
        int counter = 0;
        int prime = 0;
        while(index >= counter){
           
        
        for( int i =num-1 ; i > 1; i-- ){
            if((num % i) == 0 ){
                result =  false;                
            }
            
        }
        if( result != false){
            counter++;
            prime = num;
            num++;
        }else
            num++;
        
        result=  true;
        
        
        }
        return prime;
    }
    
    static boolean PalindromeTwo(String str){
        //Regexp rgs = new Regexp("[^a-zA-Z]");
       //str =Regex.Replace(str, "");
       str = str.toLowerCase().replaceAll("[^0-9a-zA-Z]", "");
        for(int i = str.length() - 1; i >= str.length()/2 ; i--){
            if(str.charAt(i)!= str.charAt((str.length()-1) - i))
                return false;
        }
        return true;
    }
    public static  int Division(int num1 , int num2){
        List <Integer> num1Factors = new ArrayList<>();
        List <Integer> num2Factors = new ArrayList<>();
        int maxFactor = 1;
        for ( int i =1 ; i <= num1; i++){
            if((num1 % i) == 0){
                num1Factors.add(i);
            }
        }
        for ( int i =1 ; i <= num2; i++){
            if((num2 % i) == 0){
                num2Factors.add(i);
            }
        }
        for (Iterator<Integer> it1 = num1Factors.iterator(); it1.hasNext();) {
            Integer integer1 = it1.next();
           for(Iterator<Integer> it2= num2Factors.iterator(); it2.hasNext();){
               Integer integer2 = it2.next();
               if(integer1 == integer2){
                  maxFactor = integer1; 
               }
           } 
        }   
        
        
        return maxFactor;
    }
    
      static boolean StringScramble(String str1, String str2) {
          boolean result = false;
          /*if(str1.toLowerCase().replaceAll("[^0-9a-zA-Z]", "").length() != str2.toLowerCase().replaceAll("[^0-9a-zA-Z]", "").length()){
              return false;
          }*/
          for(int i =0 ; i< str2.length(); i++){
              result =false;
              for(int j = 0 ; j < str1.length(); j++){
                  if(str1.charAt(j) == str2.charAt(i))
                      result = true;
              }
              if(result == false)
                  return false;
          }
          return true;
      }
      
      static public int DistinctList(int [] arr){
          List<Integer> intArr =  new ArrayList<>();
          Set arrSet = new HashSet();
          boolean exists = false;
          int counter = 0;
        for(int i = 0 ; i < arr.length; i++){
            exists = arrSet.add(arr[i]);
            if(exists == false)
                counter++;
        }
          
          
          return counter;
      }
      
      
      
      String convert(String str){
          if(str.toUpperCase().equals("AB"))
              return "AA";
          if(str.toUpperCase().equals("BA"))
              return "AA";
          if(str.toUpperCase().equals("CB"))
              return "CC";
          if(str.toUpperCase().equals("BC"))
              return "CC";
          if(str.toUpperCase().equals("AA"))
              return "A";
          if(str.toUpperCase().equals("CC"))
              return "C";
          
          return null;
      }
      
      int ruleNum(String str){
          if(str.toUpperCase().equals("AB"))
              return 1;
          if(str.toUpperCase().equals("BA"))
              return 2;
          if(str.toUpperCase().equals("CB"))
              return 3;
          if(str.toUpperCase().equals("BC"))
              return 4;
          if(str.toUpperCase().equals("AA"))
              return 5;
          if(str.toUpperCase().equals("CC"))
              return 6;
          
          return 0;
      }

      String ruleStr(int ruleNum){
          if(ruleNum == 1)
              return "AB";
          if(ruleNum == 2)
              return "BA";
          if(ruleNum == 3)
              return "CB";
          if(ruleNum == 4)
              return "BC";
          if(ruleNum == 5)
              return "AA";
          if(ruleNum == 6)
              return "CC";
          
          return null;
      }

      String transform(String originStr , String sub){
          String newStr = convert(sub);
          if(newStr == null)
              return null;
          
          return originStr.replaceFirst(sub, newStr);
      }
      
      static String setRule(){
          
          return "";
      }
      
      
      
     static int solution(String strNum){
          int counter = 0;
          
          int num = Integer.parseInt(strNum , 2);
          for( int i = 0 ; num > 0 ; i++){
          if((num % 2) == 0){
              num = num /2;
              counter++;
          }else{
              num = num - 1;
              counter++;
          }
          }
              
          return counter;
      }
     
     int findRules(String originStr){
         String temp="";
         int ruleNum=0;
         List<Integer> rules =  new ArrayList<>();
         for(int i = 0 ; i < originStr.length() -1 ; i++){
             ruleNum = ruleNum(originStr.substring(i, i+2));
             if(ruleNum != 0){
                 rules.add(ruleNum);
             }
         }
         Random random = new Random();
         
         return rules.get(random.nextInt(rules.size()));
     }
      
    public static void main(String args[]){
        //LongestWord("letter after letter!");
        //isPrime(8);
        //System.out.println(PrimeMover(100));
        //System.out.println(RLE("wwwwwhgjkkkjjfuuutttttyyii"));
        //System.out.println(PalindromeTwo("Anne - I vote more cars race Rome-to-Vienna"));
        //Division(12,16);
        //DistinctList(new int[]{1,2,2,4,2,6,8});
        //System.out.println(StringScramble("coodrebtqqkye","coderbyte"));
        Test t = new Test();
        int rule = 0;
        String subStr="";
        String output="";
        while(true)
        {
        rule = t.findRules("AABCC");
        if(rule == 0 )
            break;
         subStr = t.ruleStr(rule);
        output = t.transform("ABBCC", subStr);
        }
        output = output;
        //System.out.println(solution("00111110010100"));
    }
    
}
