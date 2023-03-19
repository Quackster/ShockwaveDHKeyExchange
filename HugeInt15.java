package net.h4bbo.phobos.server.habbohotel.encryption;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HugeInt15 {
    public List<Integer> pData_NxIhNARqldyJyY2PfT03dK8t9OLUR;
    private boolean pNegative;
    private int pBase;
    private int pDigits;

    public HugeInt15() {
        pData_NxIhNARqldyJyY2PfT03dK8t9OLUR = new ArrayList<Integer>();
        pNegative = false;
        pBase = 10000;
        pDigits = Integer.toString(pBase).length() - 1;
    }

    public void neg() {
        if (pNegative) {
            pNegative = false;
        } else {
            pNegative = true;
        }
    }

    public void assign(Object tdata, Object tLimit, boolean tUseKey) {
        pData_NxIhNARqldyJyY2PfT03dK8t9OLUR = new ArrayList<Integer>();
        if (tdata instanceof String dataString) {
            if (dataString.charAt(0) == '-') {
                pNegative = true;
                dataString = dataString.substring(1);
            } else {
                pNegative = false;
            }
            int i = dataString.length();
            while (i > 0) {
                String tCoef = dataString.substring(Math.max(0, i - pDigits), i);
                i = i - tCoef.length();
                pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.add(Integer.valueOf(tCoef));
            }
        } else if (tdata instanceof int[] tIntArray) {
            List<Integer> dataList = new ArrayList<>();

            for (int j : tIntArray) {
                dataList.add(j);
            }

            pNegative = false;
            int tZeroes = 1;
            int limit = (tLimit == null) ? dataList.size() : Math.min((int) tLimit, dataList.size());

            for (int i = 0; i < limit; i++) {
                pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.add(null);
            }

            for (int i = 1; i <= limit; i++) {
                if (dataList.get(i - 1) != 0 || tZeroes == 0) {
                    if (!tUseKey) {
                        pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.set(limit - i, dataList.get(i - 1));
                    } else {
                        pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.set(limit - i, SecurityCode.decode(dataList.get(i - 1)));
                    }
                    tZeroes = 0;
                }
            }
        }
	}

    public int getIntValue(Object tLimit) {
        int limit = (tLimit == null) ? 100000000 : (int) tLimit;
        int tLimitLo = limit / pBase * 10;
        int tLength = pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size();
        int tInt = pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.get(tLength - 1);
        int tIndex = tLength - 2;
        while (tInt < limit && tIndex >= 0) {
            int tCoef = pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.get(tIndex);
            if (tInt < tLimitLo) {
                tInt = (tInt * pBase) + tCoef;
            } else {
                int tCoefMultiplier = 10;
                while ((tInt * tCoefMultiplier) < limit) {
                    tCoefMultiplier = tCoefMultiplier * 10;
                }
                int tCoefDivider = pBase / tCoefMultiplier;
                tInt = (tInt * tCoefMultiplier) + (tCoef / tCoefDivider);
            }
            tIndex--;
        }
        return tInt;
    }

    public String getString() {
        if (pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size() == 0) {
            return "0";
        }
        String tStr = "";
        for (int i = 0; i < pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size(); i++) {
            String tValue = String.valueOf(pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.get(i));
            if (i < pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size() - 1) {
                while (tValue.length() < pDigits) {
                    tValue = "0" + tValue;
                }
            }
            tStr = tValue + tStr;
        }
        if (pNegative) {
            tStr = "-" + tStr;
        }
        return tStr;
    }

    public void copyFrom(HugeInt15 tValue) {
        pNegative = tValue.pNegative;
        pData_NxIhNARqldyJyY2PfT03dK8t9OLUR = new ArrayList<>(tValue.pData_NxIhNARqldyJyY2PfT03dK8t9OLUR);
        trim();
    }

    public void trim() {
        for (int i = pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size() - 1; i >= 0; i--) {
            if (pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.get(i) == 0) {
                pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.remove(i);
            } else {
                break;
            }
        }
        if (pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.isEmpty()) {
            pNegative = false;
        }
    }

    public int[] getIntArray(boolean tUseKey) {
        ArrayList<Integer> tData = new ArrayList<>();
        for (int i = 0; i < pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size(); i++) {
            int tVal = pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.get(pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size() - (i + 1));
            if (tUseKey) {
                tVal = encode(tVal);
            }
            tData.add(tVal);
        }
        int[] tArray = new int[tData.size()];
        for (int i = 0; i < tData.size(); i++) {
            tArray[i] = tData.get(i);
        }
        return tArray;
    }

    private int encode(int value) {
        return (value < 0) ? (value + 256) : value;
    }


    public static int[] getByteArray(BigInteger tSharedKey) {
        byte[] arr = tSharedKey.toByteArray();
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                result[i] = 256 - Math.abs(arr[i]);
            } else {
                result[i] = arr[i];
            }
        }
        return result;
    }

    public boolean lessThan(HugeInt15 tValue, boolean tUseSign) {
        if (equals(tValue)) {
            return false;
        }
        return !greaterThan(tValue, tUseSign);
    }

    public boolean greaterThan(HugeInt15 tValue, Boolean tUseSign) {
        if (equals(tValue)) {
            return false;
        }
        if (tUseSign == null) {
            tUseSign = true;
        }
        if (tUseSign) {
            if (!pNegative && tValue.pNegative) {
                return true;
            }
            if (pNegative && !tValue.pNegative) {
                return false;
            }
        }
        if (pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size() > tValue.pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size()) {
            return tUseSign ? !pNegative : true;
        } else if (pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size() < tValue.pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size()) {
            return tUseSign ? pNegative : false;
        }
        for (int i = pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size() - 1; i >= 0; i--) {
            if (pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.get(i) > tValue.pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.get(i)) {
                return tUseSign ? !pNegative : true;
            } else if (pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.get(i) < tValue.pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.get(i)) {
                return tUseSign ? pNegative : false;
            }
        }
        return false;
    }


    public boolean isZero() {
        for (int i = 0; i < pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.size(); i++) {
            if (pData_NxIhNARqldyJyY2PfT03dK8t9OLUR.get(i) != 0) {
                return false;
            }
        }
        return true;
    }
}
