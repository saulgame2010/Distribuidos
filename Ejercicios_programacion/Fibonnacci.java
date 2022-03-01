public class Fibonnacci {
    public static void main(String[] args) {
        int num1 = 0, num2 = 1, num3 = 2;
        System.out.print(num1 + ", " + num2 + ", ");
        for(int i = 3; i <= 20; i++) {
            System.out.print(num3 + ", ");
            num3 = num1 + num2 + num3;
            num2 = num3 - num2;
            num1 = (num3 - num2 - num1);
        }
    }
}