package com.github.republic5.bot;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {

    public static void transfer(InputStream in, OutputStream out) {
        try {
            in.transferTo(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
