package performance;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ioTester {

	/**
	 * @param args
	 */
	
	public static int freq=5;
	public static String destinationFolder="c:\\temp\\";
	public static String outputFilename;
	
	public static SimpleDateFormat format = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss.SSS]");  
	
	public static void main(String[] args) throws IOException,InterruptedException {
	
		InputStream myInputStream = null;
	    DataInputStream myDataInputStream = null;
	    BufferedWriter outputBuffer=null;
	    Date startDate;
	    Date endDate;
	    int i;
	    
	    
		try{
			
			
			String timeStampStart,timeStampEnd;
			String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format( new Date() );
		    outputFilename="ioTester_"+timeStamp +"_freq"+freq+ ".txt";
		    FileWriter fstream = new FileWriter(outputFilename,true);
		    outputBuffer = new BufferedWriter(fstream);
		    
		    System.out.println("==================================================================");
		    System.out.println("Application to test a device IO performance: ");
		    System.out.println("It creates a new file, write some data inside and deletes the file");
		    System.out.println("at a given frequency and logs the results.");
		    System.out.println("The IO Test is running with " +freq+ " seconds frequency");
		    System.out.println("OUTPUT FILE ---> "+outputFilename);
		    System.out.println("===================================================================");
   
		    
	
			
			while(true)
			{
				
				startDate=new Date();
			    timeStampStart = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss.SSS]").format( startDate );
			    timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format( new Date() );
			    outputBuffer.write(timeStampStart+" create a file... ");
		         outputBuffer.newLine();
                 //create a file with  unique filename to avoid locking in case of multiple executions 
		         FileWriter fstream2 = new FileWriter(destinationFolder+"test_"+timeStamp +".txt",true);
				    BufferedWriter outputBuffer2 = new BufferedWriter(fstream2);
				    
				    //Write 10000 lines to a file
				    for(i=0;i<10000;i++)
				    {
				    outputBuffer2.write(timeStampStart+" test File Contents here... ");
				    outputBuffer2.newLine();
				    }
				  
				 
			         fstream2.flush();
			         fstream2.close();
			       
				    
	         System.out.print (".");
	         System.out.println(destinationFolder+"test_"+timeStamp +".txt "+ " is created!");
			
	         endDate=new Date();

	         timeStampEnd = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss.SSS]").format( endDate);
	         outputBuffer.write(timeStampEnd+" file created. ");
	         //delete the file 
	         File file = new File(destinationFolder+"test_"+timeStamp +".txt");
	     
	         
	         
	         
	         if(file.delete()){
	    		System.out.println(file.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}
	         
	         outputBuffer.newLine();
	         endDate=new Date();         
	         timeStampEnd = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss.SSS]").format( endDate );
	         outputBuffer.write(timeStampEnd+" file deleted. ");
	        //Calculate the time difference	        
	           long diff=   endDate.getTime()-startDate.getTime();
	         
	         outputBuffer.newLine();
	         outputBuffer.write(timeStampEnd+" Time duration : "+diff +" ms" );
	         outputBuffer.newLine();
	         //Alert when time diff is > 10ms
	         if (diff>10)
	         {
	        	 outputBuffer.write(timeStampEnd+" !!! ALERT !!! " );
	         outputBuffer.newLine();
			}
	         outputBuffer.write(" ----------------------------------------------------------");
	         outputBuffer.newLine();
	         outputBuffer.flush();
	         
	         
	         //sleep for a freq seconds
	         Thread.sleep(freq*1000);
	           
	         
			}//end while
	         
		}catch(Exception e){
	         // if any I/O error occurs
	         e.printStackTrace();
	}finally{
        
        // releases any associated system files with this stream
        if(myInputStream!=null)
        	myInputStream.close();
        if(myDataInputStream!=null)
        	myDataInputStream.close();
        
        if (outputBuffer!=null)
        outputBuffer.close();
     }   

}
}
