package net.h4bbo.phobos.server.habbohotel.encryption;

import java.util.ArrayList;
import java.util.List;

public class SecurityCode {
    public static int encode(int tPlain) {
        int tSeed = 5678;
        int[] tSBox = new int[]{7530, 6652, 4115, 1750, 3354, 3647, 5188, 2844, 818, 2026, 7133, 2592, 3578};
        int tIterations = 54;
        int tCipher = tPlain;
        for (int i = 1; i <= tIterations; i++) {
            tSeed = ((69069 * tSeed) + (139 * i) + 92541) % 10000;
            tSeed = tSeed + (int) Math.pow(i, 3);
            tSeed = ((tSBox[(i % tSBox.length)] * tSeed) + 2541) % 10000;
            tCipher = (tSeed ^ tCipher);
            tCipher = (1379 + tSBox[(i % tSBox.length)]) ^ tCipher;
            tCipher = (((14 * tSBox[(i % tSBox.length)]) + 13) % 10000) ^ tCipher;
            tCipher = tCipher * 2;
            int tHighBit = tCipher & 32768;
            tCipher = tCipher & 32767;
            tCipher = tCipher | (tHighBit != 0 ? 1 : 0);
        }
        tCipher = 7639 ^ tCipher;
        return tCipher;
    }


    public static int decode(int tInput) {
        int tSeed = 5678;
        int[] tSBox = new int[]{7530, 6652, 4115, 1750, 3354, 3647, 5188, 2844, 818, 2026, 7133, 2592, 3578};

        int tIterations = 54;
        int[] tSeedCycle = new int[tIterations + 1];

        for (int i = 1; i < tIterations + 1; i++) {
            tSeed = ((69069 * tSeed) + (139 * i) + 92541) % 10000;
            tSeed = tSeed + (int)Math.pow(i, 3);
            tSeed = (tSBox[i % tSBox.length] * tSeed + 2541) % 10000;
            tSeedCycle[i] = tSeed;
        }

        int tCipher = tInput;
        tCipher = 7639 ^ tCipher;

        for (int i = 1; i < tIterations + 1; i++) {
            int tLowBit = (tCipher & 1);
            tCipher = tCipher / 2;
            tLowBit = tLowBit * 16384;
            tCipher = (tCipher | tLowBit);
            int tOffset = tIterations - i + 1;
            tCipher = (tSeedCycle[tOffset ] ^ tCipher);
            tCipher = (1379 + tSBox[tOffset % tSBox.length] ^ tCipher);
            tCipher = ((14 * tSBox[tOffset % tSBox.length] + 13) % 10000 ^ tCipher);
        }

        return tCipher;
    }

    public static int[] getLoginParameter(String parameter) {
        if (parameter.equals("p")) {
            return new int[]{14613, 3915, 11064, 10568, 10591, 29566, 28070, 13112, 18207, 26958, 28957, 32685, 21847, 16745, 8025, 26953, 8635, 8056, 24872, 17688, 25451, 15717, 15199, 11532, 20815, 1337, 1351, 2347, 24427};
        }

        if (parameter.equals("g")) {
            return new int[]{17201, 25433, 16757, 18753, 6581, 20405, 22897, 26947};
        }

        return null;
    }
}
