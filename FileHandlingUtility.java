import java.io.*;
import java.util.Scanner;
public class FileHandlingUtility {
    
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Choose name of File operation");
        System.out.println("1)Read from file");
        System.out.println("2)Write in the  file");
        System.out.println("3)Modify the file");
        int str=sc.nextInt();
        if(str==1)
        {
            //System.out.println("To read");
            try
            {
            FileReader fr=new FileReader("file.txt");
            BufferedReader br=new BufferedReader(fr);
            System.out.println("Your choosen Reading from file");
            String line;
            while((line=br.readLine())!=null)
            {
            System.out.println(line);
            }
           
            fr.close();
           
            }
            catch(Exception e)
            {
            System.out.println("Error reading from file:"+e);
            }
            
        }
        if(str==2)
        {
           
            
            String filepath="file.txt";
           
            writeToFile(filepath);
         
        }
        if(str==3)
        {
         String filepath="file.txt";
         modifyFile(filepath);
        }
      sc.close();
    }
    public static void writeToFile(String filepath)
    {
        try( BufferedWriter fw=new BufferedWriter(new FileWriter(filepath))){
           
           
            fw.write(new String("java"));
            fw.flush();
            fw.close();
            System.out.println("Successfully wrote to the file.");

        }
        catch(Exception e)
        {
            System.out.println("Error writing to file:"+e);
        }
        
    }
    private static void modifyFile(String filepath)
    {
       try{
        FileWriter fw=new FileWriter(filepath);
        Scanner sc=new Scanner(System.in);
        String line2=sc.nextLine();
        fw.write(line2);
        fw.flush();
        sc.close();
        fw.close();
        System.out.println("Successfully modified in the file");
       } 
       catch(Exception e)
       {
        System.out.println("Error in modifying to the file:"+e);
       }
    }
}
