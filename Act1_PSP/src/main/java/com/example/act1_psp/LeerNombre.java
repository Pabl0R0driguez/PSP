package com.example.act1_psp;

import java.io.IOException;
import java.util.Arrays;

public class LeerNombre {

    public static void main(String[] args) throws IOException {

        if (args.length <= 0)
        {
            System.err.println("Se necesita un programa a ejecutar");

            System.exit(-1);
        } else
        {
            System.out.print("El nombre es : " + Arrays.toString(args));
            System.exit(1);
        }



    }
}