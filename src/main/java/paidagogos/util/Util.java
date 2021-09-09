package paidagogos.util;

public class Util {
    public static String getAuthTime(int timeNum) {
        switch (timeNum) {
            case 1:
                return "08:00";
            case 2:
                return "09:00";
            case 3:
                return "10:00";
            case 4:
                return "11:00";
            case 5:
                return "12:00";
            case 6:
                return "13:00";
            case 7:
                return "14:00";
            case 8:
                return "15:00";
            case 9:
                return "16:00";
            case 10:
                return "17:00";
            case 11:
                return "18:00";
            case 12:
                return "19:00";
            case 13:
                return "20:00";
            case 14:
                return "21:00";
            case 15:
                return "22:00";
            case 16:
                return "23:00";
            case 17:
                return "00:00";
            case 18:
                return "01:00";
            case 19:
                return "02:00";
            case 20:
                return "03:00";
            case 21:
                return "04:00";
            case 22:
                return "05:00";
            case 23:
                return "06:00";
            case 24:
                return "07:00";
        }
        return "";
    }

    public static int getTimeNum(String time) {
        switch (time) {
            case "08:00" :
                return 1;
            case "09:00" :
                return 2;
            case "10:00" :
                return 3;
            case "11:00" :
                return 4;
            case "12:00" :
                return 5;
            case "13:00" :
                return 6;
            case "14:00" :
                return 7;
            case "15:00" :
                return 8;
            case "16:00" :
                return 9;
            case "17:00" :
                return 10;
            case "18:00" :
                return 11;
            case "19:00" :
                return 12;
            case "20:00" :
                return 13;
            case "21:00" :
                return 14;
            case "22:00" :
                return 15;
            case "23:00" :
                return 16;
            case "00:00" :
                return 17;
            case "01:00" :
                return 18;
            case "02:00" :
                return 19;
            case "03:00" :
                return 20;
            case "04:00" :
                return 21;
            case "05:00" :
                return 22;
            case "06:00" :
                return 23;
            case "07:00" :
                return 24;
        }
        return 0;
    }

}
