package com.bsu.mvt.server.core.util;

import com.twmacinta.util.MD5;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.*;

public class FileUtil {
    public static String generateMD5Fast(String fileName) throws IOException {
        return MD5.asHex(MD5.getHash(new File(fileName)));
    }

    private static String generateMD5(String fileName) {
        FileInputStream inputStream = null;
        try {
            MessageDigest md;
            inputStream = new FileInputStream(fileName);
            md = MessageDigest.getInstance("MD5");
            FileChannel channel = inputStream.getChannel();
            ByteBuffer buff = ByteBuffer.allocate(2048);
            while (channel.read(buff) != -1) {
                buff.flip();
                md.update(buff);
                buff.clear();
            }
            byte[] hashValue = md.digest();
            return new String(hashValue);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (IOException e) {
            return null;
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {

            }
        }
    }

    public static String generateMD5Password(String password) {
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes("UTF-8"));
            return MD5.asHex(digest);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("generateMD5Password() = " + generateMD5Password("test"));
    }
}
