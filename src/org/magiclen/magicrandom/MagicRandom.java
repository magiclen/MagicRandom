/*
 *
 * Copyright 2015-2016 magiclen.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.magiclen.magicrandom;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * <p>
 * 隨機類別。特色如下：
 * </p>
 *
 * <ol>
 * <li>可以隨機產生任意型態的資料。</li>
 * <li>可以調整隨機出現的機率。</li>
 * </ol>
 *
 * @author Magic Len
 */
public class MagicRandom {

    // -----類別常數-----
    /**
     * 正規化權重值總和。
     */
    private static final double NORMAL_WEIGHT_SUM = 100000;

    // -----類別方法-----
    /**
     * 隨機取得一個長整數。
     *
     * @return 傳回隨機取得的長整數
     */
    public static long randomLong() {
        return (long) (Math.random() * Math.pow(2, 64) + Long.MIN_VALUE);
    }

    /**
     * 在a~b或是b~a的範圍內隨機取得一個長整數。
     *
     * @param a 傳入長整數範圍的下限(或上限)
     * @param b 傳入長整數範圍的上限(或下限)
     * @return 傳回隨機取得的長整數
     */
    public static long randomLong(final long a, final long b) {
        final double rnd = Math.random();
        if (b >= a) {
            if (b == Long.MAX_VALUE && a == Long.MIN_VALUE) {
                return randomLong();
            }
            return (long) (rnd * (b - a + 1) + a);
        }
        if (a == Long.MAX_VALUE && b == Long.MIN_VALUE) {
            return randomLong();
        }
        return (long) (rnd * (a - b + 1) + b);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組數字不重複的長整數數列。
     *
     * @param a 傳入長整數範圍的下限(或上限)
     * @param b 傳入長整數範圍的上限(或下限)
     * @return 傳回隨機產生的長整數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static long[] randomLongArray(final long a, final long b) {
        final double size = Math.abs((double) a - (double) b) + 1;
        if (size > Integer.MAX_VALUE) {
            throw new ArrayIndexOutOfBoundsException("Array is too long.");
        }
        return randomLongArray(a, b, (int) size, true);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組數字不重複的長整數數列。
     *
     * @param a 傳入長整數範圍的下限(或上限)
     * @param b 傳入長整數範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @return 傳回隨機產生的長整數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static long[] randomLongArray(final long a, final long b, final int length) {
        return randomLongArray(a, b, length, true);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組長整數數列。
     *
     * @param a 傳入長整數範圍的下限(或上限)
     * @param b 傳入長整數範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @param unique 傳入數列的數字是否能重複
     * @return 傳回隨機產生的長整數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static long[] randomLongArray(final long a, final long b, final int length, final boolean unique) throws ArrayIndexOutOfBoundsException {
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException("The length of an array can't be negative.");
        }
        final long[] array = new long[length];
        long max, min;
        if (b >= a) {
            max = b;
            min = a;
        } else {
            max = a;
            min = b;
        }
        double size = Math.abs((double) a - (double) b) + 1;
        if (unique) {
            if (length > size) {
                throw new ArrayIndexOutOfBoundsException("The length of this array can't larger than |a - b|.");
            }
            final TreeSet<Long> tempTreeSet = new TreeSet<>();
            for (int i = 0; i < length; ++i) {
                long value = (long) (Math.random() * size + min);
                while (tempTreeSet.contains(value)) {
                    if (value == max) {
                        value = min;
                    } else {
                        value++;
                    }
                }
                if (value == max) {
                    --max;
                    --size;
                }
                if (value == min) {
                    ++min;
                    --size;
                }
                tempTreeSet.add(value);
                array[i] = value;
            }
        } else {
            for (int i = 0; i < length; ++i) {
                array[i] = (byte) (Math.random() * size + min);
            }
        }
        return array;
    }

    /**
     * 隨機取得一個整數。
     *
     * @return 傳回隨機取得的整數
     */
    public static int randomInteger() {
        return (int) (Math.random() * 4294967296l + -2147483648);
    }

    /**
     * 在a~b或是b~a的範圍內隨機取得一個整數。
     *
     * @param a 傳入整數範圍的下限(或上限)
     * @param b 傳入整數範圍的上限(或下限)
     * @return 傳回隨機取得的整數
     */
    public static int randomInteger(final int a, final int b) {
        final double rnd = Math.random();
        if (b >= a) {
            if (b == Integer.MAX_VALUE && a == Integer.MIN_VALUE) {
                return randomInteger();
            }
            return (int) (rnd * (b - a + 1) + a);
        }
        if (a == Integer.MAX_VALUE && b == Integer.MIN_VALUE) {
            return randomInteger();
        }
        return (int) (rnd * (a - b + 1) + b);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組數字不重複的整數數列。
     *
     * @param a 傳入整數範圍的下限(或上限)
     * @param b 傳入整數範圍的上限(或下限)
     * @return 傳回隨機產生的整數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static int[] randomIntegerArray(final int a, final int b) {
        final double size = Math.abs((double) a - (double) b) + 1;
        if (size > Integer.MAX_VALUE) {
            throw new ArrayIndexOutOfBoundsException("Array is too long.");
        }
        return randomIntegerArray(a, b, (int) size, true);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組數字不重複的整數數列。
     *
     * @param a 傳入整數範圍的下限(或上限)
     * @param b 傳入整數範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @return 傳回隨機產生的整數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static int[] randomIntegerArray(final int a, final int b, final int length) {
        return randomIntegerArray(a, b, length, true);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組整數數列。
     *
     * @param a 傳入整數範圍的下限(或上限)
     * @param b 傳入整數範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @param unique 傳入數列的數字是否能重複
     * @return 傳回隨機產生的整數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static int[] randomIntegerArray(final int a, final int b, final int length, final boolean unique) throws ArrayIndexOutOfBoundsException {
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException("The length of an array can't be negative.");
        }
        final int[] array = new int[length];
        int max, min;
        if (b >= a) {
            max = b;
            min = a;
        } else {
            max = a;
            min = b;
        }
        double size = Math.abs((double) a - (double) b) + 1;
        if (unique) {
            if (length > size) {
                throw new ArrayIndexOutOfBoundsException("The length of this array can't larger than |a - b|.");
            }
            final TreeSet<Integer> tempTreeSet = new TreeSet<>();
            for (int i = 0; i < length; ++i) {
                int value = (int) (Math.random() * size + min);
                while (tempTreeSet.contains(value)) {
                    if (value == max) {
                        value = min;
                    } else {
                        value++;
                    }
                }
                if (value == max) {
                    --max;
                    --size;
                }
                if (value == min) {
                    ++min;
                    --size;
                }
                tempTreeSet.add(value);
                array[i] = value;
            }
        } else {
            for (int i = 0; i < length; ++i) {
                array[i] = (byte) (Math.random() * size + min);
            }
        }
        return array;
    }

    /**
     * 隨機取得一個短整數。
     *
     * @return 傳回隨機取得的短整數
     */
    public static short randomShort() {
        return (short) (Math.random() * 65536 + -32768);
    }

    /**
     * 在a~b或是b~a的範圍內隨機取得一個短整數。
     *
     * @param a 傳入短整數範圍的下限(或上限)
     * @param b 傳入短整數範圍的上限(或下限)
     * @return 傳回隨機取得的整數
     */
    public static short randomShort(final short a, final short b) {
        final double rnd = Math.random();
        if (b >= a) {
            return (short) (rnd * (b - a + 1) + a);
        }
        return (short) (rnd * (a - b + 1) + b);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組數字不重複的短整數數列。
     *
     * @param a 傳入短整數範圍的下限(或上限)
     * @param b 傳入短整數範圍的上限(或下限)
     * @return 傳回隨機產生的短整數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static short[] randomShortArray(final short a, final short b) {
        return randomShortArray(a, b, Math.abs(a - b) + 1, true);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組數字不重複的短整數數列。
     *
     * @param a 傳入短整數範圍的下限(或上限)
     * @param b 傳入短整數範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @return 傳回隨機產生的短整數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static short[] randomShortArray(final short a, final short b, final int length) {
        return randomShortArray(a, b, length, true);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組短整數數列。
     *
     * @param a 傳入短整數範圍的下限(或上限)
     * @param b 傳入短整數範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @param unique 傳入數列的數字是否能重複
     * @return 傳回隨機產生的短整數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static short[] randomShortArray(final short a, final short b, final int length, final boolean unique) throws ArrayIndexOutOfBoundsException {
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException("The length of an array can't be negative.");
        }
        final short[] array = new short[length];
        short max, min;
        if (b >= a) {
            max = b;
            min = a;
        } else {
            max = a;
            min = b;
        }
        int size = max - min + 1;
        if (unique) {
            if (length > size) {
                throw new ArrayIndexOutOfBoundsException("The length of this array can't larger than |a - b|.");
            }
            final TreeSet<Short> tempTreeSet = new TreeSet<>();
            for (int i = 0; i < length; ++i) {
                short value = (short) (Math.random() * size + min);
                while (tempTreeSet.contains(value)) {
                    if (value == max) {
                        value = min;
                    } else {
                        value++;
                    }
                }
                if (value == max) {
                    --max;
                    --size;
                }
                if (value == min) {
                    ++min;
                    --size;
                }
                tempTreeSet.add(value);
                array[i] = value;
            }
        } else {
            for (int i = 0; i < length; ++i) {
                array[i] = (byte) (Math.random() * size + min);
            }
        }
        return array;
    }

    /**
     * 隨機取得一個位元組。
     *
     * @return 傳回隨機取得的位元組
     */
    public static byte randomByte() {
        return (byte) (Math.random() * 256 + -128);
    }

    /**
     * 在a~b或是b~a的範圍內隨機取得一個位元組。
     *
     * @param a 傳入位元組範圍的下限(或上限)
     * @param b 傳入位元組範圍的上限(或下限)
     * @return 傳回隨機取得的位元組
     */
    public static byte randomByte(final byte a, final byte b) {
        final double rnd = Math.random();
        if (b >= a) {
            return (byte) (rnd * (b - a + 1) + a);
        }
        return (byte) (rnd * (a - b + 1) + b);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組數字不重複的位元組數列。
     *
     * @param a 傳入位元組範圍的下限(或上限)
     * @param b 傳入位元組範圍的上限(或下限)
     * @return 傳回隨機產生的位元組陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static byte[] randomByteArray(final byte a, final byte b) {
        return randomByteArray(a, b, Math.abs(a - b) + 1, true);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組數字不重複的位元組數列。
     *
     * @param a 傳入位元組範圍的下限(或上限)
     * @param b 傳入位元組範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @return 傳回隨機產生的位元組陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static byte[] randomByteArray(final byte a, final byte b, final int length) {
        return randomByteArray(a, b, length, true);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組位元組數列。
     *
     * @param a 傳入位元組範圍的下限(或上限)
     * @param b 傳入位元組範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @param unique 傳入數列的數字是否能重複
     * @return 傳回隨機產生的位元組陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static byte[] randomByteArray(final byte a, final byte b, final int length, final boolean unique) throws ArrayIndexOutOfBoundsException {
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException("The length of an array can't be negative.");
        }
        final byte[] array = new byte[length];
        byte max, min;
        if (b >= a) {
            max = b;
            min = a;
        } else {
            max = a;
            min = b;
        }
        int size = max - min + 1;
        if (unique) {
            if (length > size) {
                throw new ArrayIndexOutOfBoundsException("The length of this array can't larger than |a - b|.");
            }
            final TreeSet<Byte> tempTreeSet = new TreeSet<>();
            for (int i = 0; i < length; ++i) {
                byte value = (byte) (Math.random() * size + min);
                while (tempTreeSet.contains(value)) {
                    if (value == max) {
                        value = min;
                    } else {
                        value++;
                    }
                }
                if (value == max) {
                    --max;
                    --size;
                }
                if (value == min) {
                    ++min;
                    --size;
                }
                tempTreeSet.add(value);
                array[i] = value;
            }
        } else {
            for (int i = 0; i < length; ++i) {
                array[i] = (byte) (Math.random() * size + min);
            }
        }
        return array;
    }

    /**
     * 隨機取得一個雙倍精準浮點數。
     *
     * @return 傳回隨機取得的雙倍精準浮點數
     */
    public static double randomDouble() {
        return Double.longBitsToDouble(randomLong());
    }

    /**
     * 在a~b或是b~a的範圍內隨機取得一個雙倍精準浮點數。
     *
     * @param a 傳入雙倍精準浮點數範圍的下限(或上限)
     * @param b 傳入雙倍精準浮點數範圍的上限(或下限)
     * @return 傳回隨機取得的雙倍精準浮點數
     */
    public static double randomDouble(final double a, final double b) {
        final double rnd = Math.random();
        if (b >= a) {
            return rnd * (b - a) + a;
        } else {
            return rnd * (a - b) + b;
        }
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組數字不重複的雙倍精準浮點數數列。
     *
     * @param a 傳入雙倍精準浮點數範圍的下限(或上限)
     * @param b 傳入雙倍精準浮點數範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @return 傳回隨機產生的雙倍精準浮點數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static double[] randomDoubleArray(final double a, final double b, final int length) {
        return randomDoubleArray(a, b, length, true);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組雙倍精準浮點數數列。
     *
     * @param a 傳入雙倍精準浮點數範圍的下限(或上限)
     * @param b 傳入雙倍精準浮點數範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @param unique 傳入數列的數字是否能重複
     * @return 傳回隨機產生的雙倍精準浮點數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static double[] randomDoubleArray(final double a, final double b, final int length, final boolean unique) throws ArrayIndexOutOfBoundsException {
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException("The length of an array can't be negative.");
        }
        final double[] array = new double[length];
        final double max, min;
        if (b >= a) {
            max = b;
            min = a;
        } else {
            max = a;
            min = b;
        }
        final double size = max - min;
        if (unique) {
            if (max == min && length > 1) {
                throw new ArrayIndexOutOfBoundsException("The length of this array can't larger than 1.");
            }
            final TreeSet<Double> tempTreeSet = new TreeSet<>();
            for (int i = 0; i < length; ++i) {
                double value = Math.random() * size + min;
                while (tempTreeSet.contains(value)) {
                    value = Math.random() * size + min;
                }
                tempTreeSet.add(value);
                array[i] = value;
            }
        } else {
            for (int i = 0; i < length; ++i) {
                array[i] = Math.random() * size + min;
            }
        }
        return array;
    }

    /**
     * 隨機取得一個單倍精準浮點數。
     *
     * @return 傳回隨機取得的單倍精準浮點數
     */
    public static float randomFloat() {
        return Float.intBitsToFloat(randomInteger());
    }

    /**
     * 在a~b或是b~a的範圍內隨機取得一個單倍精準浮點數。
     *
     * @param a 傳入單倍精準浮點數範圍的下限(或上限)
     * @param b 傳入單倍精準浮點數範圍的上限(或下限)
     * @return 傳回隨機取得的單倍精準浮點數
     */
    public static float randomFloat(final float a, final float b) {
        final double rnd = Math.random();
        if (b >= a) {
            return (float) (rnd * (b - a) + a);
        } else {
            return (float) (rnd * (a - b) + b);
        }
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組數字不重複的雙倍精準浮點數數列。
     *
     * @param a 傳入雙倍精準浮點數範圍的下限(或上限)
     * @param b 傳入雙倍精準浮點數範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @return 傳回隨機產生的雙倍精準浮點數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static float[] randomFloatArray(final float a, final float b, final int length) {
        return randomFloatArray(a, b, length, true);
    }

    /**
     * 在a~b或是b~a的範圍內隨機產生一組雙倍精準浮點數數列。
     *
     * @param a 傳入雙倍精準浮點數範圍的下限(或上限)
     * @param b 傳入雙倍精準浮點數範圍的上限(或下限)
     * @param length 傳入數列的長度
     * @param unique 傳入數列的數字是否能重複
     * @return 傳回隨機產生的雙倍精準浮點數陣列
     * @throws ArrayIndexOutOfBoundsException 無法產生陣列的時候將拋出例外
     */
    public static float[] randomFloatArray(final float a, final float b, final int length, final boolean unique) throws ArrayIndexOutOfBoundsException {
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException("The length of an array can't be negative.");
        }
        final float[] array = new float[length];
        final float max, min;
        if (b >= a) {
            max = b;
            min = a;
        } else {
            max = a;
            min = b;
        }
        final float size = max - min;
        if (unique) {
            if (max == min && length > 1) {
                throw new ArrayIndexOutOfBoundsException("The length of this array can't larger than 1.");
            }
            final TreeSet<Float> tempTreeSet = new TreeSet<>();
            for (int i = 0; i < length; ++i) {
                float value = (float) (Math.random() * size + min);
                while (tempTreeSet.contains(value)) {
                    value = (float) (Math.random() * size + min);
                }
                tempTreeSet.add(value);
                array[i] = value;
            }
        } else {
            for (int i = 0; i < length; ++i) {
                array[i] = (float) (Math.random() * size + min);
            }
        }
        return array;
    }

    /**
     * 從陣列中隨機抽選出一個元素。
     *
     * @param <T> 陣列元素型態
     * @param array 傳入陣列
     * @return 傳回抽選出來的元素，若沒有元素被抽中，將傳回null
     */
    public static <T> T singlePickFromArray(final T[] array) {
        return singlePickFromArray(array, null);
    }

    /**
     * 從陣列中隨機抽選出一個元素。
     *
     * @param <T> 陣列元素型態
     * @param array 傳入陣列
     * @param weights 傳入抽選區域的權重值
     * @return 傳回抽選出來的元素，若沒有元素被抽中，將傳回null
     */
    public static <T> T singlePickFromArray(final T[] array, double... weights) {
        if (array == null) {
            return null;
        }

        if (weights == null) {
            weights = new double[]{1};
        }
        final int weightsLength = weights.length;
        double weightsSum = 0;
        for (int i = 0; i < weightsLength; ++i) {
            weights[i] = Math.abs(weights[i]);
            weightsSum += weights[i];
        }
        if (weightsSum == 0) {
            return null;
        }

        final int arrayLength = array.length;
        switch (arrayLength) {
            case 0:
                throw new RuntimeException("Array is empty.");
            case 1:
                return array[0];
        }

        final double indexScale = arrayLength * 1.0 / weightsLength;
        final double weightsScale = NORMAL_WEIGHT_SUM / weightsSum;

        final double rnd = randomDouble(0, NORMAL_WEIGHT_SUM);
        double temp = 0;
        for (int i = 0; i < weightsLength; ++i) {
            temp += weights[i] * weightsScale;
            if (temp > rnd) {
                final int index = (int) Math.floor(i * indexScale);
                return array[randomInteger(index, (int) Math.floor((i + 1) * indexScale) - 1)];
            }
        }
        return null;
    }

    // -----建構子-----
    /**
     * 私有的建構子，將無法被實體化。
     */
    private MagicRandom() {

    }

}
