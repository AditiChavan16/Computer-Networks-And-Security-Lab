import java.util.*;

public class CRC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no of bits: ");
        int n = sc.nextInt();
        System.out.println("Enter data bits one by one: ");
        int bits[] = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter bit " + (n - i) + ": ");
            bits[i] = sc.nextInt();
        }
        System.out.println("Enter size of divisor array: ");
        int m = sc.nextInt();
        System.out.println("Enter divisor bit by bit: ");
        int div[] = new int[m];
        for (int i = 0; i < m; i++) {
            System.out.println("Enter bit " + (m - i) + ": ");
            div[i] = sc.nextInt();
        }
        System.out.println("Code appended: ");
        int rem[] = divwithdivisor(bits, div);
        for (int i = 0; i < rem.length - 1; i++) {
            System.out.print(rem[i]);
        }
        
        System.out.println("Generated CRC code is: ");
        
        for (int i = 0; i < bits.length; i++) {
            System.out.print(bits[i]);
        }
        
        for (int i = 0; i < rem.length - 1; i++) {
           System.out.print(rem[i]);
        }
        
        int sendcode[] = new int[bits.length + rem.length - 1];
        System.out.println("Enter bits you want to send: ");
        for (int i = 0; i < sendcode.length; i++) {
            System.out.println("Enter bit " + (sendcode.length - i) + " : ");
            sendcode[i] = sc.nextInt();
        }
        receiveData(bits, div);
        sc.close();

    }

    static int xor(int x, int y) {
        if (x == y) {
            return 0;
        } else {
            return 1;
        }
    }

    static int[] divwithdivisor(int olddata[], int divisor[]) {
        int rem[] = new int[divisor.length];
        int data[] = new int[olddata.length + divisor.length];

        System.arraycopy(olddata, 0, data, 0, olddata.length);
        System.arraycopy(data, 0, rem, 0, divisor.length);
        for (int i = 0; i < olddata.length; i++) {
            //System.out.print((i + 1) + ".) " + "Data bit " + (i + 1) + " is: " + rem[0]);
            //System.out.print("Remainder: ");
            if (rem[0] == 1) {
                for (int j = 1; j < divisor.length; j++) {
                    rem[j - 1] = xor(rem[j], divisor[j]);
                    //System.out.println(rem[j - 1]);
                }
            } else {
                for (int j = 1; j < divisor.length; j++) {
                    rem[j - 1] = xor(rem[j], 0);
                    //System.out.println(rem[j - 1]);
                }
            }
            rem[divisor.length - 1] = data[i + divisor.length];
            //System.out.println(rem[divisor.length - 1]);
        }
        return rem;
    }

    static void receiveData(int bits[], int divisor[]) {
        int rem[] = divwithdivisor(bits, divisor);
	int flag=0;
        for (int i = 0; i < rem.length; i++) {
            if (rem[i] != 0) {
		flag=1;
		break;
            }
        }
        if(flag == 0){
        System.out.println("Data received without any error..");
        }
        else
        {
        System.out.println("Corrupted");
        }
    }
}

/*
 * ALGORITHM FOR CRC ERROR DETECTION
 * 
 * 1. start
 * 2. Accept the data to be sent
 * 3. Accept the divisor
 * 4. Append (length(divisor)-1) zeros to data to be sent
 * 5. using binary division divide the data with the divisor
 * 6. now replace the appended zeros by the remainder of the division.
 * 7. Send the obtained data in step 6 along with divisor to the receiver.
 * 8. At the receiver's end again divide the received data with the
 * divisor
 * 9. if the remainder of this division is zero then the data received is
 * without any errors.
 * 10. Else if the remiander is not zero then an error has occured during
 * the
 * transmission.
 * 11. Stop
 */
