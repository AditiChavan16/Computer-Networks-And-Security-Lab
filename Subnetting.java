import java.util.Scanner;

public class Subnetting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter IP Address: ");
        String ip = sc.next();
        String ipad[] = ip.split("\\.");
        int cc = Integer.parseInt(ipad[0]);
        String mask = "";
        if (cc > 0 && cc <= 127) {
            mask = "255.0.0.0";
            System.out.println("Class A IP Address");
        } else if (cc >= 128 && cc <= 191) {
            System.out.println("Class B IP Address");
            mask = "255.255.0.0";
        } else if (cc >= 192 && cc <= 223) {
            System.out.println("Class C IP Address");
            mask = "255.255.255.0";
        } else if (cc >= 224 && cc <= 239) {
            System.out.println("Class D IP Address, used for Multcast");
            mask = "255.0.0.0";
        } else if (cc >= 240 && cc <= 254) {
            System.out.println("Class E IP Address used for Experimental Use");
            mask = "255.0.0.0";
        } else {
            System.out.println("Invalid IP Address");
            System.exit(0);
        }
        System.out.println("Subnet Mask: ");
        System.out.println(mask);
        String networkaddr = "";
        String lastaddr = "";
        String[] ipaddr = ip.split("\\.");
        String msk[] = mask.split("\\.");
        for (int i = 0; i < 4; i++) {
            int x = Integer.parseInt(ipaddr[i]);
            int y = Integer.parseInt(msk[i]);
            int z = x & y;
            networkaddr += z + ".";
            int w = z | (y ^ 255);
            lastaddr += w + ".";
        }
        System.out.println("First IP of the block:\n " + networkaddr);
        System.out.println("Last IP of the block, i.e. BroadCast Address:\n " + lastaddr);

        sc.close();
    }

}

/*  ALGORITHM
 * 1. Start
 * 2. Accept IP address
 * 3. Based on the first part of the IP address classify it into
 * it's class.
 * 4. According to its class assign it's subnet mask.
 * 5. Now convert the IP address and Subnet mask into binary form
 * 6. Perform bitwise AND of all the bits.
 * 7. Convert the obtained binary number into its decimal equivalent.
 * 8. The obtained decimal number is the first IP of the block.
 * 9. Stop
 */
