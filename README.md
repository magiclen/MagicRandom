MagicRandom
=================================

# Introduction

**MagicRandom** is a Java library for producing random data. It is very convenient to get a random number or numbers array in a range and to pick one element from an array randomly.

# Usage

## MagicRandom Class

**MagicRandom** class is in the *org.magiclen.magicrandom* package.

### Initialize

You don't need to do initialize when you use **MagicRandom** class. Just use its static methods to do what you want.

### Get A Random Number

You can use **randomDouble**, **randomFloat**, **randomLong**, **randomInteger**, **randomShort**, **randomByte** static methods to get a random number. For example,

    System.out.println(randomByte((byte) 48, (byte) 56));
    System.out.println(randomShort((short)1000, (short)1234));
    System.out.println(randomInteger(2000, 1285000));
    System.out.println(randomLong(-9996611L, 965000L));
    System.out.println(randomFloat(0.5F, 3.0F));
    System.out.println(randomDouble(-888.777779999, 60000.20));

The result is,

    51
    1170
    940140
    529912
    1.0580332
    20332.112717123862

### Get An Array with Random Numbers

You can use **randomDoubleArray**, **randomFloatArray**, **randomLongArray**, **randomIntegerArray**, **randomShortArray**, **randomByteArray** static methods to get an array with random numbers. For example,

    System.out.println(Arrays.toString(randomByteArray((byte) 48, (byte) 56)));
    System.out.println(Arrays.toString(randomShortArray((short) 1000, (short) 1234, 10)));
    System.out.println(Arrays.toString(randomIntegerArray(2000, 1285000, 5)));
    System.out.println(Arrays.toString(randomLongArray(-9996611L, 965000L, 3)));
    System.out.println(Arrays.toString(randomFloatArray(0.5F, 3.0F, 4)));
    System.out.println(Arrays.toString(randomDoubleArray(-888.777779999, 60000.20, 2)));

The result is,

    [50, 49, 52, 53, 54, 56, 55, 48, 51]
    [1165, 1184, 1178, 1012, 1123, 1226, 1061, 1073, 1179, 1159]
    [559417, 117017, 349552, 776058, 619228]
    [-9459151, -2060062, -9066882]
    [0.87771237, 1.0171695, 1.0780405, 1.457837]
    [26002.26129895805, 55597.1289970967]

### Pick One Element from An Array

You can use **singlePickFromArray** static method to pick one element from an array randomly. And also, you can change the weights for elements. For example,

    String[] array = new String[]{"Hi", "Hello", "Yo", "Hiya"};
    for (int i = 0; i < 15; ++i) {
        System.out.print(singlePickFromArray(array, 2, 1) + " ");
    }

The result is,

    Hi Hi Hello Hi Hi Hi Yo Hello Hiya Yo Hello Hiya Hello Hiya Hello

"Hi" and "Hello" appear `10` times. "Yo" and "Hiya" appear `5` times. `10 : 5 = 2 : 1`.

# License

    Copyright 2015-2016 magiclen.org

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

# What's More?

Please check out our web page at

https://magiclen.org/magicrandom/
