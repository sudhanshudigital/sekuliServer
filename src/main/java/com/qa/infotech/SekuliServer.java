package com.qa.infotech;

import static spark.Spark.post;

//------------------java libraries----------------
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

//------------------java IO libraries----------------
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

//------------------Sekuli libraries----------------
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

//------------------Jason libraries----------------
import org.json.simple.JSONObject;

//------------------Servlet libraries----------------
import javax.servlet.http.Part;
import javax.servlet.MultipartConfigElement;

//------------------Apache commons libraries----------------
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;



/*
 * 
 * Main class for Sekuli Server
 *
 */


public class SekuliServer {
	
	private static String currentDir = System.getProperty("user.dir");
	private static String imagegDir = currentDir + "/imagesDir/";
	
	private static Screen screen = new Screen();

	/*
	 * 
	 * Method to unTar the images
	 *
	 */
	
	public static void unTar(String tarFileLocation, String unTarFilesDir) throws IOException 
	{
		final int buffer = 2048;
		FileInputStream fin = new FileInputStream(tarFileLocation);
		BufferedInputStream in = new BufferedInputStream(fin);
		GzipCompressorInputStream gzIn = new GzipCompressorInputStream(in);
		TarArchiveInputStream tarIn = new TarArchiveInputStream(gzIn);

		TarArchiveEntry entry = null;

		while ((entry = (TarArchiveEntry) tarIn.getNextEntry()) != null) 
		{
			   System.out.println("Extracting: " + entry.getName());
			   if (entry.isDirectory()) 
			   {
				    File file = new File(unTarFilesDir + entry.getName());
				    file.mkdirs();
			   }
			   else 
			   {
				    int count;
				    byte data[] = new byte[buffer];
				    FileOutputStream fos = new FileOutputStream(unTarFilesDir+ entry.getName());
				    BufferedOutputStream dest = new BufferedOutputStream(fos,buffer);
				    while ((count = tarIn.read(data, 0, buffer)) != -1) 
				    {
				    	dest.write(data, 0, count);
				    }
				    dest.close();
			   }
		  }
		  tarIn.close();
		  System.out.println("untar completed successfully!!");
	}
	
	
	/*
	 * 
	 * Method to handle click functionality
	 *
	 */

	public static int click(String image1) 
	{
        int flag = 0;
        try
        {
        		String image = imagegDir + image1;
        		flag = screen.click(image, 0);
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
        return flag;
	}
	
	
	
	/*
	 * 
	 * Method to handle double click functionality
	 *
	 */

	public static int dobuleClick(String image1) 
	{
        int flag = 0;
        try
        {
        		String image = imagegDir + image1;
        		flag = screen.doubleClick(image, 0);
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
        return flag;
	}
	
	
	/*
	 * 
	 * Method to handle type functionality
	 *
	 */
	
	public static int type(String myText) 
	{
        int flag = 0;
        try
        {
        		flag = screen.type(null, myText, 0);
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
        return flag;
	}

	
	/*
	 * 
	 * Method to handle exists functionality
	 *
	 */
	
	public static Match exists(String image1) 
	{
        Match match = null;;
        try
        {
        		String image = imagegDir + image1;;
                match = screen.exists(image);
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
        return match;
	}

	

	/*
	 * 
	 * Method to handle find functionality
	 *
	 */
	
	public static List<int[]> find(String image1) 
	{
        List<int[]> list = new ArrayList<int[]>();
        int[] coordinates = new int[4];
        Match match = null;
        try
        {
        		String image = imagegDir + image1;;
        		match = screen.find(image);
        		coordinates[0] = match.x;
        		coordinates[1] = match.y;
        		coordinates[2] = match.h;
        		coordinates[3] = match.w;
        		list.add(coordinates);
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
        return list;
	}
	
	

	/*
	 * 
	 * Method to handle find all functionality
	 *
	 */
	
	public static List<int[]> findAll(String image1) 
	{
        List<int[]> list = new ArrayList<int[]>();
        int[] coordinates = new int[4];
        Iterator<Match> iterator = null;
        Match match = null;
        try
        {
        		String image = imagegDir + image1;;
        		iterator = screen.findAll(image);
        		while(iterator.hasNext())
        		{
        			match = iterator.next();
        			coordinates[0] = match.x;
            		coordinates[1] = match.y;
            		coordinates[2] = match.h;
            		coordinates[3] = match.w;
            		list.add(coordinates);
        		}
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
        return list;
	}



	/*
	 * 
	 * Method to handle wait functionality
	 *
	 */
	
	public static Match wait(String image1, String time) 
	{
        Match match =null;
        try
        {
        		System.out.println("Image : " + image1 + ", time : " + time);
        		String image = imagegDir + image1;;
        		int timeout = Integer.parseInt(time);
        		match = screen.wait(image, timeout);
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
        return match;
	}


	/*
	 * 
	 * Method to handle waitVanish functionality
	 *
	 */

	public static Boolean waitVanish(String image1, String time) 
	{
        Boolean flag = false;
        try
        {
        		System.out.println("Image : " + image1 + ", time : " + time);
        		String image = imagegDir + image1;
    			int timeout = Integer.parseInt(time);
    			flag = screen.waitVanish(image, timeout);
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
        return flag;
	}


	/*
	 * 
	 * Method to handle dragDrop functionality
	 *
	 */

	public static int dragDrop(String image1, String image2) 
	{
        int flag = 0;
        try
        {
        		String imageA = imagegDir + image1;
        		String imageB = imagegDir + image2;
        		flag = screen.dragDrop(imageA, imageB);
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
        return flag;
	}
	


	
	/*
	 * 
	 * Main method which is responsible 
	 * for handling AllREST calls
	 * and uploading the tar file as well
	 *
	 */
	
    public static void main(String[] args) 
    {
    	post("/seleniumServer/*", (request, response) -> 
    	{
    		System.out.println("This is selenium server");
    		String uri = new String(request.uri());
    		String url = new String(request.url());
    		System.out.println("URI : " + request.uri());
    		System.out.println("URL : " + request.url());
    		String remoteIP = url.substring(7, request.url().lastIndexOf(':'));
    		String seleniumUri = uri.substring(16, request.uri().length());
    		System.out.println("Remote IP : " + remoteIP);
    		System.out.println("Selenium URI : " + seleniumUri);
    		String seleniumUrl = "http://" + remoteIP + ":4444/wd/hub/" + seleniumUri;
    		System.out.println("Selenium URL : " + seleniumUrl);
    		response.redirect(seleniumUrl);
    		return response;
    	});
    	
    	post("/sekuliServer", (request, response) -> 
    	{
    		
    		Set<String> set = request.queryParams();
    		String[] parameters = new String[10];
    		List<int[]> list = new ArrayList<int[]>();
    		int counter = 0;
    		for(String parameter : set){
    			parameters[counter] = request.queryParams(parameter);
    			//System.out.println(parameters[counter]);
    			counter++;
    		}
    		
    		String method = request.queryParams("command");
    		switch(method)
    		{
	    	    case "initilaiseImages":
		            {
		            	//------------Code to upload the Tar File--------------------
		            	String imagesDir = currentDir + "/imagesDir";
		                System.out.println("Current dir:"+ imagesDir);
	            		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(imagesDir);
	                    request.raw().setAttribute("org.eclipse.multipartConfig", multipartConfigElement);
	                    Part file = request.raw().getPart("imagesTarBall");
	                    file.write("images.tar.gz");
	                    String tarFileLocation = currentDir + "/imagesDir/images.tar.gz"; 
	                    String unTarFilesDir = currentDir + "/imagesDir/";
		            	
	                    //------------Code to unTar images Files----------------------	                    
	                    unTar(tarFileLocation, unTarFilesDir);
	            		
   	                    response.status(200);
	            		response.body("Command executed successfully...");
		            }
		            break;
		            
	    	    case "startSelenium":
	            {
	            	//------------Code to start the Selenium Server---------------
                    String path = currentDir + "/" + parameters[1]; 
                    ProcessBuilder pb = new ProcessBuilder("java", "-jar", path);
                    System.out.println("Selenium Jar Location : " + path);
                    Process p = pb.start();
                    
                    response.status(200);
            		response.body("Command executed successfully...");
	            }
	            break;
		           
	            case "click":
		            {
		            	if(click(parameters[0]) == 1)
		            	{
		            		response.status(200);
		            		response.body("Command executed successfully...");
		            	}
		            }
		            break;
		            
	            case "dobuleClick":
	            {
	            	if(dobuleClick(parameters[0]) == 1)
	            	{
	            		response.status(200);
	            		response.body("Command executed successfully...");
	            	}
	            }
	            break;
            
	            case "type" :
		            {
		            	if(type(parameters[0]) == 1)
		            	{
		            		response.status(200);
		            		response.body("Command executed successfully...");
		            	}
		            }
		            break;

	            case "exists" :
		            {
		            	if(exists(parameters[0]) != null)
		            	{
		            		response.status(200);
		            		response.body("Command executed successfully...");
		            	}
		            }
		            break;
	            
	            case "find" :
		            {
		            	list = find(parameters[0]);
		            	JSONObject jsonObject = new JSONObject();
		            	if(list != null)
		            	{
		            		jsonObject.put("key", list);
		            		response.status(200);
		            		response.type("application/json");
		            		response.body(jsonObject.toJSONString());
		            	}
		            }
		            break;
		           
	            case "findAll" :
		            {
		            	list = findAll(parameters[0]);
		            	JSONObject jsonObject = new JSONObject();
		            	if(list != null)
		            	{
		            		jsonObject.put("key", list);
		            		response.status(200);
		            		response.type("application/json");
		            		response.body(jsonObject.toJSONString());
		            	}
		            }
		            break;
		            
	            case "wait" :
		            {
		            	if(wait(parameters[0], parameters[2]) != null)
		            	{
		            		response.status(200);
		            		response.body("Command executed successfully...");
		            	}
		            }
		            break;
		            
	            case "waitVanish" :
		            {
		            	if(waitVanish(parameters[0], parameters[2]))
		            	{
		            		response.status(200);
		            		response.body("Command executed successfully...");
		            	}
		            }
		            break;
		            
	            case "dragDrop" :
		            {
		            	if(dragDrop(parameters[0], parameters[1]) == 1)
		            	{
		            		response.status(200);
		            		response.body("Command executed successfully...");
		            	}
		            }
		            break;
	    		}
    		return response.body();
    		}
    	);
    }
}