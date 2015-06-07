
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author iramadan
 */
public class Solution {

    public int solution(int[] A) {
        List<Integer> equiList = new ArrayList<>();
        int eIndex = 0;
        int rhsSum = 0;
        int lhsSum = 0;
        for (int j = 0; j < A.length; j++) {
            eIndex = j;
            rhsSum = 0;
            lhsSum = 0;
            for (int i = eIndex + 1; i < A.length; i++) {
                rhsSum = rhsSum + A[i];
            }
            for (int i = eIndex - 1; i >= 0; i--) {
                lhsSum += A[i];
            }

            if (rhsSum == lhsSum) {
                return eIndex;
            }

        }
        return -1;

    }

    String getCharNumber(char c) {
        switch (c) {
            case 'a':
                return "1";
            case 'b':
                return "2";
            case 'c':
                return "3";
            case 'd':
                return "4";
            case 'e':
                return "5";
            case 'f':
                return "6";
            case 'g':
                return "7";
            case 'h':
                return "8";
            case 'i':
                return "9";
            case 'j':
                return "10";
            case 'k':
                return "11";
            case 'l':
                return "12";
            case 'm':
                return "13";
            case 'n':
                return "14";
            case 'o':
                return "15";
            case 'p':
                return "16";
            case 'q':
                return "17";
            case 'r':
                return "18";
            case 's':
                return "19";
            case 't':
                return "20";
            case 'u':
                return "21";
            case 'v':
                return "22";
            case 'w':
                return "23";
            case 'x':
                return "24";
            case 'y':
                return "25";
            case 'z':
                return "26";

        }
        return c + "";
    }

    String charEncoding(String str) {
        str = str.toLowerCase();
        String output = "";
        for (int i = 0; i < str.length(); i++) {
            output += getCharNumber(str.charAt(i));
        }
        return output;
    }

    String geoarith(int[] arr) {

        boolean notarth = false;
        boolean notgeo = false;
        int arth = arr[1] - arr[0];
        int geo = arr[1] / arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            if (arth != arr[i + 1] - arr[i]) {
                notarth = true;
            }
        }
        for (int i = 1; i < arr.length - 1; i++) {
            if (geo != arr[i + 1] / arr[i]) {
                notgeo = true;
            }
        }
        if (notarth == false && notgeo == true) {
            return "Arithmetic";
        } else if (notgeo == false && notarth == true) {
            return "Geometric";
        } else {
            return "-1";
        }

    }

    public static void main(String args[]) {
        Solution s = new Solution();
        /*
         int[] A = new int[8];
         A[0] = -1;
         A[1] = 3;
         A[2] = -4;
         A[3] = 5;
         A[4] = 2;
         A[5] = -6;
         A[6] = 1;
         A[7] = 1;

         s.solution(A);
         */

        //System.out.println(s.geoarith(new int[]{ -3,-4,-5,-6,-7}));
        System.out.println(s.charEncoding("bzf"));
    }

}
