package com.base.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class IOPattern {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("");
		new FileInputStream(file);
		new BufferedInputStream(new FileInputStream(file));

	}

}
