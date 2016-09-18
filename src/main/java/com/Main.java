package com;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Created by Artemie on 18.09.2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DES des = new DES("12341234".getBytes());
        InputStream inputStream = Main.class.getResourceAsStream("/decrypt.txt");
        byte [] bytes = IOUtils.toByteArray(inputStream);
        int length = makeLengthDivBy8(bytes);
        byte[] bytesDivBy8 = new byte[length];
        System.arraycopy(bytes,0,bytesDivBy8,0,bytes.length);
        byte[] encrypt = des.encrypt(bytesDivBy8);
        File file = new File("./src/main/resources/encrypt.txt");
        OutputStream output = new FileOutputStream(file);
        output.write(encrypt);
        output.close();
        inputStream.close();

        DES des2 = new DES("12341234".getBytes());
        File file2 = new File("./src/main/resources/encrypt.txt");
        InputStream inputStream2 = new FileInputStream(file2);
        byte[] bytes2 = IOUtils.toByteArray(new FileInputStream(file2));
        int length2 = makeLengthDivBy8(bytes2);
        byte[] bytesDivBy82 = new byte[length2];
        System.arraycopy(bytes2,0,bytesDivBy82,0,bytes2.length);
        byte[] decrypt = des2.decrypt(bytesDivBy82);
        System.out.println(new String(decrypt));
        inputStream2.close();
    }

    private static int makeLengthDivBy8(byte[] bytes) {
        int length = bytes.length;
        while (length%8!=0)
            ++length;
        return length;
    }
}
