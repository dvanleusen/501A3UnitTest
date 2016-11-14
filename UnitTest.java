
package a3;
import java.util.ArrayList;
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

//tests objCreator to see if corresponding message is displayed when object type is selected
public class UnitTest {
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
    
    //tests if printing to file is working properly
    @Test  
    public void testOutput() {
        FileOutput fOutput=new FileOutput();
        String strFile="Output.txt";
        String strWrite="An object that contains only primitives"+System.lineSeparator()+
              "An object that contains reference to other objects";
        try{
            fOutput.fileWriteToFile(strFile,strWrite );
            fOutput.fileClose();
        }
        catch(Exception e){
            if (fOutput!=null)
                fOutput.fileClose();
        }
        //reads string from the file created to check if the string remains the same
        
        String  strReturn="";
        BufferedReader br =null;
        try{
            br = new BufferedReader(new FileReader(strFile));
            StringBuilder strBuilder = new StringBuilder();
            String strline = br.readLine();
            while (strline!=null){   
                if (strBuilder.length()!=0)  
                    strBuilder.append(System.lineSeparator());
                strBuilder.append(strline);              
                strline = br.readLine();
            }
            strReturn=strBuilder.toString();
        }
       catch(Exception e){}
       finally { 
            try{ br.close();}
            catch(Exception ce){}    
        }
//        System.out.println(strWrite.length());
//        System.out.println(strReturn.length());
//        System.out.println(strWrite);
//        System.out.println(strReturn);
        assertEquals(strWrite ,strReturn);
    }
}
