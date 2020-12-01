/* file and serialization practice 
 * 
 * Demonstrates:
 * File and Buffered Read/Write methods
 * Serialization and Deserialization
 */

package serialize;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WillItSerialize {

	public static void main(String[] args) {
		//fileWriteExample();	/* writing to a file with a BufferedWriter */
		//fileReadExample();	/* reading a file with a BufferedReader */
		serializeExample();		/* serialization */
	}
	
	
	/* 
	 * demonstrates the following concepts:
	 * how to serialize objects 
	 * how to read serialized objects
	 */
	public static void serializeExample() {
		/* 
		 * TestClass1 implements serializable
		 * TestClass2 does not, and thus is NOT serializable
		 * TestClass3 shows how to serialize nested classes
		 * 
		 */
		TestClass1 testObject1 = new TestClass1();
		TestClass2 testObject2 = new TestClass2();
		TestClass3 testObject3 = new TestClass3();
		
		/* --- serialize objects --- */
		try {
			/*
			 * To implement serialization, use ObjectOutputStream
			 * ObjectOutputStream will serialize an object to an Output Stream
			 * That output stream is specified in the OOS constructor:
			 */
			FileOutputStream fos = new FileOutputStream("output.txt");
			ObjectOutputStream s = new ObjectOutputStream(fos);
			
			
			System.out.print("Object 1\t" + testObject1);
			s.writeObject(testObject1);						/* works */
			System.out.println("\tSerialized");
			
//			System.out.print("Object 2\t" + testObject2);
//			s.writeObject(testObject2);						/* fails */
//			System.out.println("\tSerialized");
			
			System.out.print("Object 3\t" + testObject3);
			s.writeObject(testObject3);						/* fails */
			System.out.println("\tSerialized");
			
			s.close();
			
		} catch (Exception e) {
			System.out.println("\tA Serialization Error Occurred");
		}
		
		/* --- deserialize objects --- */
		try {
			/* 
			 * Serialized objects are stored as bytes in a file
			 * Read these bytes using a FileInputStream
			 * Convert bytes->objects using ObjectInputStream
			 */
			FileInputStream fis = new FileInputStream("output.txt");
			ObjectInputStream s2 = new ObjectInputStream(fis);
			
			/* 
			 * readObject() returns a Java.Object
			 * it is important to typecast correctly 
			 */
			System.out.print("Object 1 Read\t");
			TestClass1 objectIn = (TestClass1)s2.readObject();
			System.out.println(objectIn);
			
			/* this will throw an exception */
//			System.out.print("Object 2 Read\t");
//			TestClass2 object2In = (TestClass2)s2.readObject();
//			System.out.println(object2In);
			
			/* 
			 * Some modification is needed in the TestClass3.toString() method
			 * 
			 * Since one of the nested class instances is transient,
			 * it is not included with the serialized data
			 * So, when we create an object with the serialized data,
			 * the nested class object is never created
			 */
			System.out.print("Object 3 Read\t");
			TestClass3 object3In = (TestClass3)s2.readObject();
			System.out.println(object3In);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error occurred reading serialization" + e);
		}
	}
	
	
	
	/* demonstrates how to write to a file with a BufferedWriter */
	public static void fileWriteExample() {
		try {
			/* create a file to write to */
			File file = new File("output.txt");
			
			/*
			 * FileWriter and BufferedWriter 
			 * FW writes directly to the file - this can be inefficient!
			 * BW writes to a buffer - more efficient for small/multiple writes 
			 */
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("hi");
			bw.newLine();	/* determined by system, not always newline char */
			bw.write("hello world");
			
			bw.close();		/* no writing occurs until BW is closed */
			//fw.close();	/* this is handled by BW.close() */
			
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}
	
	
	
	/* demonstrates reading a file with a BufferedReader */
	public static void fileReadExample() {
		try {
			
			/* create a new file object */
			File file = new File("output.txt");
			System.out.println("Name\t\t" + file.getName());
			
			/* get the last modified date */
			long lastModVal = file.lastModified();
			
			/* convert the long to a date */
			Date lastModDate = new Date(lastModVal);
			
			/* make it more readable */
			String lastMod = new SimpleDateFormat().format(lastModDate);
			System.out.println("Last Mod\t" + lastMod);
			
			/*
			 * FileReader and BufferedReader
			 * FR reads a single character at a time 
			 * BR uses FR to read a whole line and stores line in RAM
			 */
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			System.out.println("First Line\t" + br.readLine());
		
			br.close();		/* save resources: close readers */
			
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}
}
