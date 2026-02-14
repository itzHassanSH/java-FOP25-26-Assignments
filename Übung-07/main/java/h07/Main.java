package h07;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
//        PeanoNumber number = new PeanoNumber(new PeanoNumber(new PeanoNumber( new PeanoNumber())));
//        System.out.println(number.asIntRecursive());
//        System.out.println(number.asIntIterative());
//
//        PeanoNumber number2 = new PeanoNumber(new PeanoNumber( new PeanoNumber()));
//        System.out.println("\n" + number.addRecursive(number2).asIntRecursive());
//        System.out.println(number.addIterative(number2).asIntIterative());
//
//        System.out.println("\n" + number.multiplyRecursive(number2).asIntRecursive());
//        System.out.println(number.multiplyIterative(number2).asIntIterative());

        System.out.println("H7.3:");
        PeanoNumber num0 = PeanoNumber.fromInt(0);
        PeanoNumber num1 = PeanoNumber.fromInt(1);
        PeanoNumber num42 = PeanoNumber.fromInt2(42);

        //1:
        System.out.println("0, 1 and 42 in Int Recursive: " + num0.asIntRecursive() + ", " + num1.asIntRecursive() + ", " + num42.asIntRecursive());
        System.out.println("0, 1 and 42 in Int Iterative: " + num0.asIntIterative() + ", " + num1.asIntIterative() + ", " + num42.asIntIterative());
        //2
        PeanoNumber num6 = PeanoNumber.fromInt(6);
        PeanoNumber num7 = PeanoNumber.fromInt2(7);
        System.out.println("\n6 and 7 added Recursive: " + num6.addRecursive(num7).asIntRecursive());
        System.out.println("6 and 7 added Iterative: " + num6.addIterative(num7).asIntIterative());
        //3
        PeanoNumber num5 = PeanoNumber.fromInt(5);
        PeanoNumber num4 = PeanoNumber.fromInt(4);
        System.out.println("\n5 and 4 multiplied Recursive: " + num5.multiplyRecursive(num4).asIntRecursive());
        System.out.println("5 and 4 multiplied Iterative: " + num5.multiplyIterative(num4).asIntIterative());
        //4
        PeanoNumber num15 = PeanoNumber.fromInt(15);
        PeanoNumber num18 = PeanoNumber.fromInt2(18);
        System.out.println("\n15 * (18 + 4 * 4) + 1 = 511 (Recursive), calculated: " + num4.multiplyRecursive(num4).addRecursive(num18).multiplyRecursive(num15).addRecursive(num1).asIntRecursive());
        System.out.println("15 * (18 + 4 * 4) + 1 = 511 (Iterative), calculated: " + num4.multiplyIterative(num4).addIterative(num18).multiplyIterative(num15).addIterative(num1).asIntIterative());
        //5
        int[] array1 = {1, 2, 3, 4, 3, 2, 1};
        int[] array2 = {4, 4};
        System.out.println("\n{1, 2, 3, 4, 3, 2, 1} Palindrome Recursive? : " + PalindromeChecker.isPalindromeRecursive(array1));
        System.out.println("{1, 2, 3, 4, 3, 2, 1} Palindrome Iterative? : " + PalindromeChecker.isPalindromeIterative(array1));
        System.out.println("{4, 4} Palindrome Recursive? : " + PalindromeChecker.isPalindromeRecursive(array2));
        System.out.println("{4, 4} Palindrome Iterative? : " + PalindromeChecker.isPalindromeIterative(array2));
        //6
        array1 = new int[]{1, 2, 3};
        array2 = new int[]{1, 2, 3, 3, 1};
        System.out.println("\n{1, 2, 3} Palindrome Recursive? : " + PalindromeChecker.isPalindromeRecursive(array1));
        System.out.println("{1, 2, 3} Palindrome Iterative? : " + PalindromeChecker.isPalindromeIterative(array1));
        System.out.println("{1, 2, 3, 3, 1} Palindrome Recursive? : " + PalindromeChecker.isPalindromeRecursive(array2));
        System.out.println("{4, 2, 3, 3, 1} Palindrome Iterative? : " + PalindromeChecker.isPalindromeIterative(array2));
        //7
        array1 = new int[]{0};
        array2 = new int[]{};
        System.out.println("\n{0} Palindrome Recursive? : " + PalindromeChecker.isPalindromeRecursive(array1));
        System.out.println("{0} Palindrome Iterative? : " + PalindromeChecker.isPalindromeIterative(array1));
        System.out.println("{} Palindrome Recursive? : " + PalindromeChecker.isPalindromeRecursive(array2));
        System.out.println("{} Palindrome Iterative? : " + PalindromeChecker.isPalindromeIterative(array2));
        //8
        PeanoNumber num8 = PeanoNumber.fromInt(8);
        PeanoNumber num9 = PeanoNumber.fromInt2(9);
        PeanoNumber num11 = PeanoNumber.fromInt(11);

        PeanoNumber temp1 = num11.addRecursive(num8);
        PeanoNumber temp2 = temp1.multiplyRecursive(num9.multiplyRecursive(num8));
        PeanoNumber temp3 = temp2.addRecursive(num1);
        PeanoNumber ans1 = num9.multiplyRecursive(temp3);

        PeanoNumber ans2 = num11.addIterative(num8).multiplyIterative(num9).multiplyIterative(num8).addIterative(num1).multiplyIterative(num9);
        System.out.println("\n(((11 + 8) * 9 * 8) + 1) * 9 = 12321 (Recursive), calculated: " +  ans1.asIntIterative());
        System.out.println("(((11 + 8) * 9 * 8) + 1) * 9 = 12321 (Iterative), calculated: " +  ans2.asIntIterative());

        int[] digitArray = PalindromeChecker.toDigits(ans1.asIntIterative());
        System.out.println("12321 Palindrome? (Recursive) : " + PalindromeChecker.isPalindromeRecursive(digitArray));
        int[] digitArray2 = PalindromeChecker.toDigits(ans2.asIntIterative());
        System.out.println("12321 Palindrome? (Iterative) : " + PalindromeChecker.isPalindromeIterative(digitArray2));
    }
}
