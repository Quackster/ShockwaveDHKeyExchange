package net.h4bbo.phobos.server.habbohotel.encryption;

import java.math.BigInteger;
import java.util.Arrays;

public class EncryptionTest {
    public static void test() {
        String t_sServerPublicKey = "";
        var pClientSecret = createClientSecret("1337333");

        int tLength = 64;
        String tHexChars = "012345679";

        for (int tNo = 1; tNo <= tLength * 2; tNo++) {
            int tRandPos = (int)(Math.random() * tHexChars.length());
            if (tRandPos == 0 && tNo == 1) {
                tRandPos = 1 + (int)(Math.random() * (tHexChars.length() - 1));
            }
            t_sServerPublicKey += tHexChars.charAt(tRandPos);
        }

        t_sServerPublicKey = "79931532477694391553399106392062395046507293405797934413737523323990900525393426501765999417452706746730450491763913929427305376";

        var adobeClientP = new HugeInt15();
        adobeClientP.assign(SecurityCode.getLoginParameter("p"), null, true);

        var adobeClientBig = new HugeInt15();
        adobeClientBig.assign(t_sServerPublicKey, null, false);

        System.out.println("t_sServerPublicKey: " + t_sServerPublicKey);

        System.out.println("clientP int: " + adobeClientP.getIntValue(null));
        System.out.println("clientP str: " + adobeClientP.getString());

        System.out.println("clientBig int: " + adobeClientBig.getIntValue(null));
        System.out.println("clientBig str: " + adobeClientBig.getString());

        var tClientBig = new BigInteger(adobeClientBig.getString());
        var clientP = new BigInteger(adobeClientP.getString());

        var tSharedKey = tClientBig.modPow(pClientSecret, clientP);
        var byteArray = HugeInt15.getByteArray(tSharedKey);

        var encCrypto = new Cryptography(byteArray);
        var encoded = encCrypto.AkwGx8bHG2kc1xGG4xbdHPCV0fqvK("TEST123");
        System.out.println("Encoded: " + encoded);

        var crypto2 = new Cryptography(byteArray);
        System.out.println("test // " + crypto2.kg4R6Jo5xjlqtFGs1klMrK4ZTzb3R(encoded));



        //String byteString = Arrays.toString(adobeSharedKey.pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.toArray(Integer[]::new));

        //System.out.println("tSharedKey str " + tSharedKey.toString());
        //System.out.println("tSharedKey bytes " + byteString);
    }

    public static BigInteger createClientSecret(String tHex) {
        /*String tHex = "";

        int tLength = 24;
        String tHexChars = "012345679";

        for (int tNo = 1; tNo <= tLength * 2; tNo++) {
            int tRandPos = (int)(Math.random() * tHexChars.length());
            if (tRandPos == 0 && tNo == 1) {
                tRandPos = 1 + (int)(Math.random() * (tHexChars.length() - 1));
            }
            tHex += tHexChars.charAt(tRandPos);
        }*/

        var adobeClientG = new HugeInt15();
        adobeClientG.assign(SecurityCode.getLoginParameter("g"), null, true);

        var adobeClientP = new HugeInt15();
        adobeClientP.assign(SecurityCode.getLoginParameter("p"), null, true);

        var adobeClientSecret = new HugeInt15();
        adobeClientSecret.assign(tHex, null, false);

        var clientG = new BigInteger(adobeClientG.getString());
        var clientP = new BigInteger(adobeClientP.getString());

        if (tHex.startsWith("-"))
            adobeClientSecret.neg();

        var pClientSecret = new BigInteger(adobeClientSecret.getString());
        var tPublicKeyStr = clientG.modPow(pClientSecret, clientP).toString();

        /*
        System.out.println("tHex: " + tHex);
        System.out.println("clientG int: " + adobeClientG.getIntValue(null));
        System.out.println("clientP int: " + adobeClientP.getIntValue(null));
        System.out.println("clientSecret int: " + adobeClientSecret.getIntValue(null));
        System.out.println("clientG str: " + adobeClientG.getString());
        System.out.println("clientP str: " + adobeClientP.getString());
        System.out.println("clientSecret str: " + adobeClientSecret.getString());
        System.out.println("tPublicKeyStr: " + tPublicKeyStr);*/

        return pClientSecret;
    }
}
