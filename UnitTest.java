
package a3;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;
/**
* Assignment 3
* @author Daniel Van Leusen
* Student id: 10064708
* E-mail: danvanleusen@yahoo.co.uk
* @version Nov 1, 2016
* <p>
* * This is the Unit Test file to test unit codes. 
*/
public class UnitTest {
    // tests object creator
	// if it works properly, then it prints string when relevant selection is made
	@Test
    public void testObjectCreator() {
        ObjectCreator objCreator=new ObjectCreator();
        String strReturn="";
        for (int i=1;i<=7;i++)
            strReturn+=objCreator.doAction(i)+"\n";
        assertEquals("An object that contains only primitives\n"+
                "An object that contains reference to other objects\n"+
                "An object that contains an array of primitives\n"+
                "An object that contains an array of object references\n"+
                "An object that uses an instance of collection classes\n"+
                "Exit\n"+
                "Invalid selection\n" ,strReturn);
    }
	
	// tests writing string to a file
	// tests if it writes properly by comparing the file content to the original string
    @Test  
    public void testOutput() {
        FileHandler fHandler=new FileHandler();
        String strFile="Output.txt";
        String strWrite="An object that contains only primitives"+System.lineSeparator()+
              "An object that contains reference to other objects";
        try{
            fHandler.fileWriteToFile(strFile,strWrite );
            fHandler.fileClose();
        }
        catch(Exception e){
            if (fHandler!=null)
                fHandler.fileClose();
        }
        
        //read file
        String  strReturn=fHandler.fileReader(strFile);
        assertEquals(strWrite ,strReturn);
    }
    
    // tests serialization 
    // tests serialized object to xml documents for 5 scenarios
    @Test
    public void testSerialize() throws IOException {       
        MySerializer ser=new MySerializer();
        // scenario 1
          
        String strReturn=ser.serialization(new Obj1());  
             
        String strWrite="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+System.lineSeparator() +
            "<serialized>"+System.lineSeparator() +
            "  <object class=\"a3.Obj1\" id=\"0\">"+System.lineSeparator() +
            "    <field name=\"customerID\" type=\"int\" declaringclass=\"a3.Obj1\">"+System.lineSeparator() +
            "      <value>0</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"check\" type=\"boolean\" declaringclass=\"a3.Obj1\">"+System.lineSeparator() +
            "      <value>false</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "  </object>"+System.lineSeparator() +
            "</serialized>"+System.lineSeparator() + System.lineSeparator();
        assertEquals(strWrite ,strReturn);
       
        // scenario 2 
        strReturn= ser.serialization(new Obj2());
        
        strWrite="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+System.lineSeparator() +
            "<serialized>"+System.lineSeparator() +
            "  <object class=\"a3.Obj2\" id=\"0\">"+System.lineSeparator() +
            "    <field name=\"owner\" type=\"a3.Obj1\" declaringclass=\"a3.Obj2\">"+System.lineSeparator() +
            "      <reference>1</reference>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"intAccount\" type=\"int\" declaringclass=\"a3.Obj2\">"+System.lineSeparator() +
            "      <value>0</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "  </object>"+System.lineSeparator() +
            "  <object class=\"a3.Obj1\" id=\"1\">"+System.lineSeparator() +
            "    <field name=\"customerID\" type=\"int\" declaringclass=\"a3.Obj1\">"+System.lineSeparator() +
            "      <value>10001</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"check\" type=\"boolean\" declaringclass=\"a3.Obj1\">"+System.lineSeparator() +
            "      <value>true</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "  </object>"+System.lineSeparator() +
            "</serialized>"+System.lineSeparator() + System.lineSeparator();
        assertEquals(strWrite ,strReturn);

        //scenario 3
        strReturn=ser.serialization(new Obj3());
       
        strWrite="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+System.lineSeparator() +
            "<serialized>"+System.lineSeparator() +
            "  <object class=\"a3.Obj3\" id=\"0\">"+System.lineSeparator() +
            "    <field name=\"newBooks\" type=\"[I\" declaringclass=\"a3.Obj3\">"+System.lineSeparator() +
            "      <reference>1</reference>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "  </object>"+System.lineSeparator() +
            "  <object class=\"[I\" id=\"1\" length=\"3\">"+System.lineSeparator() +
            "    <value>1</value>"+System.lineSeparator() +
            "    <value>2</value>"+System.lineSeparator() +
            "    <value>3</value>"+System.lineSeparator() +
            "  </object>"+System.lineSeparator() +
            "</serialized>"+System.lineSeparator() + System.lineSeparator();
        assertEquals(strWrite ,strReturn);

        //scenario 4
        strReturn=ser.serialization(new Obj4());
            strWrite="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+System.lineSeparator() +
            "<serialized>"+System.lineSeparator() +
            "  <object class=\"a3.Obj4\" id=\"0\">"+System.lineSeparator() +
            "    <field name=\"arrayBooks\" type=\"[La3.ObjBook;\" declaringclass=\"a3.Obj4\">"+System.lineSeparator() +
            "      <reference>1</reference>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "  </object>"+System.lineSeparator() +
            "  <object class=\"[La3.ObjBook;\" id=\"1\" length=\"4\">"+System.lineSeparator() +
            "    <value>"+System.lineSeparator() +
            "      <reference>2</reference>"+System.lineSeparator() +
            "    </value>"+System.lineSeparator() +
            "    <value>"+System.lineSeparator() +
            "      <reference>3</reference>"+System.lineSeparator() +
            "    </value>"+System.lineSeparator() +
            "    <value>"+System.lineSeparator() +
            "      <reference>4</reference>"+System.lineSeparator() +
            "    </value>"+System.lineSeparator() +
            "    <value>"+System.lineSeparator() +
            "      <reference>5</reference>"+System.lineSeparator() +
            "    </value>"+System.lineSeparator() +
            "  </object>"+System.lineSeparator() +
            "  <object class=\"a3.ObjBook\" id=\"2\">"+System.lineSeparator() +
            "    <field name=\"referenceID\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>ISBN 978-0-111-1</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"name\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>OH SHE GLOWS EVERY DAY</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"author\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>Angela Liddon</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"price\" type=\"double\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>32.0</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "  </object>"+System.lineSeparator() +
            "  <object class=\"a3.ObjBook\" id=\"3\">"+System.lineSeparator() +
            "    <field name=\"referenceID\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>ISBN 978-0-111-2</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"name\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>THE WHISTLER</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"author\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>John Grisham</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"price\" type=\"double\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>37.0</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "  </object>"+System.lineSeparator() +
            "  <object class=\"a3.ObjBook\" id=\"4\">"+System.lineSeparator() +
            "    <field name=\"referenceID\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>ISBN 978-0-111-3</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"name\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>COOKING FOR JEFFREY</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"author\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>Ina Garten</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"price\" type=\"double\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>45.0</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "  </object>"+System.lineSeparator() +
            "  <object class=\"a3.ObjBook\" id=\"5\">"+System.lineSeparator() +
            "    <field name=\"referenceID\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>ISBN 978-0-111-4</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"name\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>APPETITES: A COOKBOOK</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"author\" type=\"java.lang.String\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>Anthony Bourdain</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "    <field name=\"price\" type=\"double\" declaringclass=\"a3.ObjBook\">"+System.lineSeparator() +
            "      <value>46.5</value>"+System.lineSeparator() +
            "    </field>"+System.lineSeparator() +
            "  </object>"+System.lineSeparator() +
            "</serialized>"+System.lineSeparator() + System.lineSeparator();
        assertEquals(strWrite ,strReturn);

        //scenario 5
        strReturn=ser.serialization(new Obj5());
            strWrite="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+System.lineSeparator() +
                "<serialized>"+System.lineSeparator() +
                "  <object class=\"a3.Obj5\" id=\"0\">"+System.lineSeparator() +
                "    <field name=\"favorBooks\" type=\"java.util.ArrayList\" declaringclass=\"a3.Obj5\">"+System.lineSeparator() +
                "      <reference>1</reference>"+System.lineSeparator() +
                "    </field>"+System.lineSeparator() +
                "  </object>"+System.lineSeparator() +
                "  <object class=\"java.util.ArrayList\" id=\"1\" length=\"5\">"+System.lineSeparator() +
                "    <value>"+System.lineSeparator() +
                "      <reference>2</reference>"+System.lineSeparator() +
                "    </value>"+System.lineSeparator() +
                "    <value>"+System.lineSeparator() +
                "      <reference>3</reference>"+System.lineSeparator() +
                "    </value>"+System.lineSeparator() +
                "    <value>Gulliver Travels</value>"+System.lineSeparator() +
                "    <value>Clarissa</value>"+System.lineSeparator() +
                "    <value>Tom Jones</value>"+System.lineSeparator() +
                "  </object>"+System.lineSeparator() +
                "  <object class=\"a3.Obj1\" id=\"2\">"+System.lineSeparator() +
                "    <field name=\"customerID\" type=\"int\" declaringclass=\"a3.Obj1\">"+System.lineSeparator() +
                "      <value>0</value>"+System.lineSeparator() +
                "    </field>"+System.lineSeparator() +
                "    <field name=\"check\" type=\"boolean\" declaringclass=\"a3.Obj1\">"+System.lineSeparator() +
                "      <value>false</value>"+System.lineSeparator() +
                "    </field>"+System.lineSeparator() +
                "  </object>"+System.lineSeparator() +
                "  <object class=\"a3.Obj3\" id=\"3\">"+System.lineSeparator() +
                "    <field name=\"newBooks\" type=\"[I\" declaringclass=\"a3.Obj3\">"+System.lineSeparator() +
                "      <reference>4</reference>"+System.lineSeparator() +
                "    </field>"+System.lineSeparator() +
                "  </object>"+System.lineSeparator() +
                "  <object class=\"[I\" id=\"4\" length=\"3\">"+System.lineSeparator() +
                "    <value>1</value>"+System.lineSeparator() +
                "    <value>2</value>"+System.lineSeparator() +
                "    <value>3</value>"+System.lineSeparator() +
                "  </object>"+System.lineSeparator() +
                "</serialized>"+System.lineSeparator() + System.lineSeparator();
            assertEquals(strWrite ,strReturn); 
    }  
    
    // tests deserialization 
    // tests deserialized object from xml documents for 5 scenarios
    @Test
    public void testDeserialize() throws IOException {
        MySerializer ser=new MySerializer();  
        String strReturn="";
        String strWrite="";
        boolean blnIsFile = false;  
        
        //scenario 1
        strReturn= ser.deserialization(ser.serialization(new Obj1()),blnIsFile); 
        strWrite="========== Object(ID: 0) ========== \n" +
            "Name: Obj1\n" +
            "Field: int customerID = 0\n" +
            "Field: boolean check = false\n"  ;
        assertEquals(strWrite ,strReturn);

        //scenario 2
        strReturn= ser.deserialization(ser.serialization(new Obj2()),blnIsFile);  
        strWrite="========== Object(ID: 0) ========== \n" +
            "Name: Obj2\n" +
            "Field: Obj1 owner\n" +
            "	Reference: 1\n" +
            "Field: int intAccount = 0\n" +
            "========== Object(ID: 1) ========== \n" +
            "Name: Obj1\n" +
            "Field: int customerID = 10001\n" +
            "Field: boolean check = true\n" ;
        assertEquals(strWrite ,strReturn);        

        //scenario 3
        strReturn= ser.deserialization(ser.serialization(new Obj3()),blnIsFile);  
        strWrite="========== Object(ID: 0) ========== \n" +
            "Name: Obj3\n" +
            "Field: int[] newBooks\n" +
            "	Reference: 1\n" +
            "========== Object(ID: 1) ========== \n" +
            "int[3]\n" +
            "Values:\n" +
            "	1\n" +
            "	2\n" +
            "	3\n" ;
       assertEquals(strWrite ,strReturn);

       //scenario 4
        strReturn= ser.deserialization(ser.serialization(new Obj4()),blnIsFile);  
        strWrite="========== Object(ID: 0) ========== \n" +
            "Name: Obj4\n" +
            "Field: ObjBook[] arrayBooks\n" +
            "	Reference: 1\n" +
            "========== Object(ID: 1) ========== \n" +
            "ObjBook[4]\n" +
            "Values:\n" +
            "	Reference: 2\n" +
            "	Reference: 3\n" +
            "	Reference: 4\n" +
            "	Reference: 5\n" +
            "========== Object(ID: 2) ========== \n" +
            "Name: ObjBook\n" +
            "Field: String referenceID\n" +
            "Field: String name\n" +
            "Field: String author\n" +
            "Field: double price = 32.0\n" +
            "========== Object(ID: 3) ========== \n" +
            "Name: ObjBook\n" +
            "Field: String referenceID\n" +
            "Field: String name\n" +
            "Field: String author\n" +
            "Field: double price = 37.0\n" +
            "========== Object(ID: 4) ========== \n" +
            "Name: ObjBook\n" +
            "Field: String referenceID\n" +
            "Field: String name\n" +
            "Field: String author\n" +
            "Field: double price = 45.0\n" +
            "========== Object(ID: 5) ========== \n" +
            "Name: ObjBook\n" +
            "Field: String referenceID\n" +
            "Field: String name\n" +
            "Field: String author\n" +
            "Field: double price = 46.5\n"  ;
        assertEquals(strWrite ,strReturn); 

        //scenario 5
        strReturn= ser.deserialization(ser.serialization(new Obj5()),blnIsFile);  
        strWrite="========== Object(ID: 0) ========== \n" +
            "Name: Obj5\n" +
            "Field: ArrayList favorBooks\n" +
            "	Reference: 1\n" +
            "========== Object(ID: 1) ========== \n" +
            "ArrayList\n" +
            "Length:5\n" +
            "Values:\n" +
            "	Reference ID: 2\n" +
            "	Reference ID: 3\n" +
            "	Gulliver Travels\n" +
            "	Clarissa\n" +
            "	Tom Jones\n" +
            "========== Object(ID: 2) ========== \n" +
            "Name: Obj1\n" +
            "Field: int customerID = 0\n" +
            "Field: boolean check = false\n" +
            "========== Object(ID: 3) ========== \n" +
            "Name: Obj3\n" +
            "Field: int[] newBooks\n" +
            "	Reference: 4\n" +
            "========== Object(ID: 4) ========== \n" +
            "int[3]\n" +
            "Values:\n" +
            "	1\n" +
            "	2\n" +
            "	3\n" ;
        assertEquals(strWrite ,strReturn);        
    }
    
    //tests network connection by testing client-server transfer
    //if server receives info from client, server returns "OK!"
    @Test
    public void testNetWorkConnection() {
        if(NetworkConnection.getNetworkConnection().isStartServer()){
                NetworkConnection.getNetworkConnection().startServer();
        }
        String strReturn=NetworkConnection.getNetworkConnection().startClient(new Obj1());
        assertEquals(strReturn ,"OK!");
    }  
}
