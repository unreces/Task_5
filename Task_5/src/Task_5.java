import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.SplittableRandom;
import java.util.Arrays;
import java.lang.String;
import java.util.*;

public class Task_5 {
    public static String translate(String str){
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        String result = "";
        int number = 0;
        for (int i = 0; i < str.length(); i++){
            char letter = str.charAt(i);
            if(map.keySet().contains(letter)){
                result += map.get(letter);  //получаем значение по ключу
            }
            else{
               map.put(letter, number);
               result += number;
               number++;
            }
        }
        return result;
    }

    public static boolean sameLetterPattern (String str1, String str2 ){
        str1 = translate(str1);
        str2 = translate(str2);
        return str1.equals(str2);
        }

    public static String[] options(String str) {
        int number = str.charAt(1)-48;
        char letter = str.charAt(0);

        // 1 -> [0, 2]; 4 -> [3], 3 -> [2, 4]

        if (number == 0) {
            return new String[]{"A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1"};
        }
        int[] numArr = new int[]{}; // nymArr = {2, 4};

        if (number > 0 && number < 4) {
            numArr = new int[]{number + 1, number - 1};
        }
        if (number == 4) {
            numArr = new int[]{3};
        }

        // H -> {A, H}; C -> {B, D}
        char[] charArr = new char[]{};
        if (letter == 'H'){
            charArr = new char[] {'A', 'G'};
        }
        if (letter == 'C'){
            charArr = new char [] {'B', 'D'};
        }
        if (letter == 'A'){
            charArr = new char [] {'H', 'B'};
        }
        if (letter == 'B'){
            charArr = new char [] {'A', 'C'};
        }
        if (letter == 'D'){
            charArr = new char [] {'C', 'E'};
        }
        if (letter == 'E'){
            charArr = new char [] {'D', 'F'};
        }
        if (letter == 'F'){
            charArr = new char [] {'E', 'G'};
        }
        if (letter == 'G'){
            charArr = new char [] {'F', 'H'};
        }

        String[] result = new String[2 + numArr.length]; //2 - всегда возможные пути + варианты, которые возможны исходя из точки
        int c = 0;
        for (char let : charArr) { // в let записываю все элементы массива charArr
            result[c] = let + "" + number;
            c++;
        }
        for (int num : numArr) {
            result[c] = letter + "" + num;
            c++;
        }

        return result;
    }

    public static double[] convertCoord (String str){
        int number = str.charAt(1) - '0';
        char letter = str.charAt(0) ;
        if (letter == 'A'){
            return new double[] {number, 0};
        }
        else if (letter == 'E'){
            return new double [] {-number, 0};
        }
        else if (letter == 'B'){
            return new double[] {number,number};
        }
        else if (letter == 'F'){
            return new double[] {-number,-number};
        }
        else if (letter == 'C'){
            return new double [] {0, number};
        }
        else if (letter == 'G'){
            return new double [] {0, -number};
        }
        else if (letter == 'H'){
            return new double[] {-number,number};
        }
        else if (letter == 'D'){
            return new double[] {-number,number};
        }

        return new double[] {0,0};

    }

    public static double distance(double[] dot0, double[] dot1){
        return Math.sqrt(Math.pow(dot0[0] - dot1[0], 2) + Math.pow(dot0[1] - dot1[1], 2));
    }

    public static String spiderVsFly(String startPoint, String endPoint){
        String res = "";
        int c = 0;
        while (!startPoint.equals(endPoint) && c++ < 7){
            double minDistance = Double.MAX_VALUE; //максимальное значение типа double
            String bestPoint = "";
            String ways[] = options(startPoint);
            for (int i = 0; i < ways.length; i++) {
                double cDistance = distance(convertCoord(ways[i]), convertCoord(endPoint));
                if (cDistance <= minDistance) {
                    minDistance = cDistance;
                    bestPoint = ways[i];
                }
            }
            res += startPoint + "-";
            startPoint = bestPoint;
        }
        return res + endPoint;
    }




    public static int digitsCount(int number){
        if (number > 0){
            return  1 + digitsCount(number / 10);
        }
        else
            return 0;
    }


    public static int totalPoints(String[] results, String str){
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i<str.length(); i++){
            char letter = str.charAt(i);
            if (map.keySet().contains(letter))
                map.put(letter, map.get(letter) + 1); //получаем значние по ключу
            else
                map.put(letter, 1);
        }

        int count = 0;
        for (int i = 0; i<results.length; i++){
            HashMap<Character, Integer> cMap = (HashMap<Character, Integer>) map.clone(); //склонировали map
            String word = results[i];
            boolean addScore = true;
            for (int k = 0; k < word.length(); k++){
                char letter = word.charAt(k);
                if (cMap.keySet().contains(letter)) {
                    cMap.put(letter, cMap.get(letter) - 1);
                    if (cMap.get(letter) < 0){
                        addScore = false;
                        break;
                    }
                }
            }
            if (addScore){
                if (word.length() == 3)
                    count += 1;
                if (word.length() == 4)
                    count += 2;
                if (word.length() == 5)
                    count += 3;
                if (word.length() == 6)
                    count += 54;
            }
        }
        return count;
    }


    public static int longestRun(int mas []) {
        int maxCount = 0;
        int count = 1;
        for (int i = 0; i < mas.length - 1; i++) {
            if (mas[i + 1] - mas[i] == 1) {
                count += 1;
                System.out.println(count);
            }
            else if (mas[i + 1] - mas[i] == -1){
                count +=1;
            }
            else {
                if(count>maxCount)
                    maxCount = count;
                count = 1;
            }
        }
        return maxCount;
    }

    public static String   takeDownAverage(String mas []) {
        double sum = 0;
        for (String str : mas) {  // в переменную str записываем преобразованные значения
            str = str.substring(0, str.length() - 1);  // удаляем последний символ
            double num = Integer.parseInt(str)/100.0;   //преобразует строку в число
            sum += num;
        }

        int count = mas.length;
        // int sum = Arrays.stream(mas).sum();  встроенный метод для нахождния суммы в массиве
        double classmates = sum/count;
        double withMe = classmates - 0.05;
        double itsme = ((withMe * (count + 1)) - sum) * 100;
        itsme = Math.round(itsme);   //округляем
        int m = (int) itsme; //double -> int
        return m +"%";

    }


    public static String rearrange(String str) {
        String[] mas = str.split(" ");
        String str2[] = new String[mas.length];
        for (int i = 0; i < mas.length; i++) {     // идем по словам
            for (int j = 0; j < mas[i].length(); j++) {     //идем по буквам в слове
                if (mas[i].charAt(j) > 47 && mas[i].charAt(j) < 58) {
                    str2[Integer.parseInt(mas[i].charAt(j) + "")-1] = mas[i];  // из кода в строку, а из строки в int (чтобы не было проблем с индексом)
                }

            }

        }
        String res = "";
        for (int k = 0; k < str2.length; k++) {    //проходимся по строке отсортированной
            String str3 = str2[k];                  // засунули слово и удалили цифру
            for (int i = 0; i < str3.length(); i++) {
                if (str3.charAt(i) > 47 && str3.charAt(i) < 58) {
                    str3 = removeCharAt(str3, i);  //удалили цифры в строке
                }
            }
            res += str3 + " ";
        }
        return res;
    }

    public static String removeCharAt(String str, int pos){      // метод для удаления символа
        return str.substring(0, pos) + str.substring(pos + 1);
        }
//НЕПРАВИЛЬНЫЙ НАШ ПЕРВЫЙ ВАРИАНТ
//       public static String maxPossible(int number1, int number2){
//          int length1 =  (String.valueOf(number1)).length();
//           int length2 =  (String.valueOf(number2)).length();
//           Integer mas [] = new Integer[length1 + length2];
//
//           while (number1>0){
//         for (int i = 0; i<length1; i++){
//            mas[i] = number1%10;
//             number1 /= 10;
//
//         }
//           }
//               while (number2>0) {
//                   for (int i = length1; i < (length2 + length1); i++) {
//                       mas[i] = number2 % 10;
//                       number2 /= 10;
//                   }
//               }
//           Arrays.sort(mas, Collections.reverseOrder());
//           for (int i = 0; i<(length2 + length1); i++){
//           }
//                   String str = "";
//                   for (int k = 0; k<length1; k++){
//                     str += mas[k];
//                   }
//return str;
//    }



    public static String  maxPossible(int number1, int number2){
        int length1 =  (String.valueOf(number1)).length();  //возвращаем строковое представление int аргумента
       int length2 =  (String.valueOf(number2)).length();
       int mas1 [] = new int[length1];
        Integer mas2 [] = new Integer[length2];

        while (number1>0) {
            for (int i = length1-1; i > -1; i--) {
                mas1[i] = number1 % 10;
                number1 /= 10;
            }

        }

        while (number2>0) {
                   for (int i = 0; i < length2; i++) {
                       mas2[i] = number2 % 10;
                       number2 /= 10;
                   }
               }

        Arrays.sort(mas2, Collections.reverseOrder());

           for (int i = 0; i<length1; i++){
               for (int j = 0; j<length2; j++)  {
                   if (mas1[i] < mas2[j]){
                       mas1[i] = mas2[j];
                       mas2[j] = 0;
                   }
               }
               System.out.print(mas1[i]);
           }
           return " ";
    }

    public static String timeDifference(String inpCity, String inpDate, String outCIty){
        String[] monthes_k = {"January", "Februrary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int[] monthes_v = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //массивы месяцев и кол-во дней в них
        Map<String, Integer[]> monthes = new HashMap<String, Integer[]>();

        int dayCounter = 0;
        for (int i = 0; i < monthes_k.length; i++){
            monthes.put(monthes_k[i], new Integer[] {i, dayCounter} );
            dayCounter += monthes_v[i];
        }
        int minutesAtYear = dayCounter * 24 * 60;

        String[] cities_k = {"Los Angeles", "New York", "Caracas", "Buenos Aires", "London", "Rome", "Moscow", "Tehran", "New Delhi", "Beijing", "Canberra"};
        double[] cities_v = {-8, -5, -4.5, -3, 0, 1, 3, 3.5, 5.5, 8, 10};    //массивы городов и разницы во времени
        Map<String, Double> cities= new HashMap<String, Double>();

        for (int i = 0; i < cities_k.length; i++){
            cities.put(cities_k[i], cities_v[i]);
        }

// переводим дату в минуты
        int totalMinutes = 0;
        String[] date = inpDate.split(" ");
        totalMinutes += ( monthes.get(date[0])[1] + Integer.parseInt(date[1].replace(",", "")) ) * 24 * 60;  //parseInt преобразует строку в число
        String[] time = date[3].split(":");
        totalMinutes += Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);

        totalMinutes += minutesAtYear * Integer.parseInt(date[2]);

        totalMinutes += (int)( ( cities.get(outCIty) - cities.get(inpCity) ) * 60 );

// считаем разницу во времени
        String res = "";
        res += totalMinutes / minutesAtYear + "-"; // added year
        totalMinutes -= ( (int)(totalMinutes / minutesAtYear) ) * minutesAtYear;

// считаем новую дату
        int monthCounter = 0, mDaysSum = 0;
        for (int i = 0; i < monthes_v.length; i++){
            mDaysSum += monthes_v[i];
            monthCounter++;
// System.out.println(mDaysSum + " - days");
            if (totalMinutes < mDaysSum * 24 * 60){
                mDaysSum -= monthes_v[i];
                break;
            }
        }

        res += monthCounter + "-"; // добавляем месяц

        totalMinutes -= mDaysSum * 24 * 60;

        res += totalMinutes / (24 * 60); // добавляем день

        totalMinutes -= ( (int)totalMinutes / (24 * 60) ) * 24 * 60;

        res += " " + ((totalMinutes / 60 < 10) ? "0" : "") + totalMinutes / 60; // добавляем часы

        totalMinutes -= ( (int)(totalMinutes / 60) ) * 60;

        res += ":" + totalMinutes; // добаляем минуты

        return res;
    }



    public static String  isNew(int number){
    int length =  (String.valueOf(number)).length();
        int mas[] = new int [length];
    while (number>0) {
        for (int i = 0; i<length; i++) {
            mas[i] = number % 10;
            number /= 10;
        }

    }
    int nulls = 0;
    for (int i = 0; i<length; i++){
        if(mas[i] ==0){
            nulls += 1;
        }
    }

    int notNulls = length - nulls;
    if (notNulls<2){
       return "true";

    }
//
//    else {
//        int mas2 [] = Arrays.copyOf(mas, mas.length);
//
//    }



//    System.out.print(Arrays.toString(mas));
for(int i =0; i < length-1; i++){
    if (mas[i] < mas[i+1]) {
        System.out.println("false");
        break;
        }
    else {
        System.out.println("true");
        break;
    }
}
return " ";
}



        public static void main (String[]args){
        System.out.println(sameLetterPattern("ABCBA","FFFF"));
        System.out.println(spiderVsFly("H3", "E2"));
        System.out.println(digitsCount(12345));
        System.out.println(totalPoints(new String[] {"cat", "create", "sat"}, "caster"));
        System.out.println(longestRun(new int []{3, 5, 7, 10, 15 }));
        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(rearrange("Tesh3 th5e 1I lov2e way6 she7 j4ust i8s."));
        System.out.println((maxPossible(8732, 91255)));
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra") );
        System.out.println(isNew(30));

        }

    }