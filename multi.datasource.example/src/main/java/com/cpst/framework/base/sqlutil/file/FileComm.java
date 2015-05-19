package com.cpst.framework.base.sqlutil.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileComm{
	
	private String userpath;
	
	public String getUserpath() {
		return userpath;
	}

	public void setUserpath(String userpath) {
		this.userpath = userpath;
	}
	
	public FileComm(String tuserpath) {
		this.userpath = tuserpath;
	}

	public  String commpath(){
		
		String tpath = this.getClass().getResource("/").getPath().replace("classes/", "").replace("WEB-INF/", "");
		
		return tpath;
	}
	
	public  void setadduserpath(String adduserpath){
		
		userpath = userpath + "/" + adduserpath;

	}

	public  String getallpath(){
		
		return commpath() + userpath + "/";

	}
	
	public void delALLFoder(String tpath,String tconf){
		
		System.out.println("delALLFoder:" + tpath);
		
		String setallpath = getallpath() + tpath;
		
		delFoder(setallpath,tconf);
		
	}
	
	public void delFoder(String tpath,String tconf){
		//System.out.println(tpath);
		
		//调用删除所有文件函数，删除该目录下的所有文件
		
		delAllFile(tpath,tconf);
		
		if(tconf.equals("FODER")){
			//创建文件对象，参数为欲删除的目录
			File foder = new File(tpath);
			
			if(foder.exists()){
			  foder.delete();   //调用删除目录函数
			}
		}

	}
	
	public void delAllFile(String path,String tconf){//定义并创建删除所有文件方法，参数为文件路径
		  File file = new File(path);    //创建文件对象，参数为文件路径
		  if(!file.exists()){            //如果文件不存在则跳出函数
		   return;
		  }
		  if(!file.isDirectory()){     //如果该File对象不是目录也跳出函数
		   return;
		  }
		  String[] tempList = file.list();  //取出目录下的文件名或目录名
		  File temp = null;
		  for(int i = 0;i < tempList.length;i++){
		   temp = new File(path + "/" + tempList[i]);
		   if(temp.isFile()){
		    temp.delete();
		   }
		   else if(temp.isDirectory()){
		    delAllFile(path + "/" + tempList[i],tconf);
		    delFoder(path + "/" + tempList[i],tconf);
		   }
		  }
	}
	
	public void mkdirFile(String tpath){
		
		String setallpath = getallpath() + tpath;
		
		System.out.println("mkdirFile:" + setallpath);
		
		File foder = new File(setallpath);
		
		if(foder.exists()==false){
			foder.mkdir();
	    }
	}
	
	public  void exportall(String tfile,String ttitle,List<Map<String, Object>> lmap){
		
		FileWriter fw = null;
		
		StringBuffer fw_str = new StringBuffer(ttitle + "\r\n");

		try {

			for (int i = 0; i < lmap.size(); i++) {
				
				fw_str.append(lmap.get(i).get("EXPORTALLSTR") + "\r\n");

	    	}
			
			fw = new FileWriter(getallpath() + "/" + tfile);
			
			fw.write(fw_str.toString());

			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
            try {
            	
                if(fw!=null) {
                	
                    fw.close();
                    
                }
                
            } catch (IOException e) {
            	
                e.printStackTrace();
                
            }
            
        }

	}
	
	 public void zip(String inputFileName, String zipFileName) throws Exception {
		 
		 	System.out.println("zip:" + inputFileName + "->" + zipFileName);
		 	
		 	ZipOutputStream out = null;
		 	
		 	try{
	        
		 		out = new ZipOutputStream(new FileOutputStream(getallpath() + zipFileName));
			 	
			 	File f = new File(getallpath() + inputFileName);
			 				 	
			 	zip(out, f, "");
			 	
		 	}catch (Exception e) {
				
				e.printStackTrace();
				
			}finally {
				
	         try {
	         	
	             if(out!=null) {
	             	
	            	 out.close();
	                 
	             }
	             
	         } catch (IOException e) {
	         	
	             e.printStackTrace();
	             
	         }
	         
	     }

	 }  

	 private void zip(ZipOutputStream out, File f, String base) throws Exception {   
	        if (f.isDirectory()) {  //判断是否为目录   
	        	
	        	File[] fl = f.listFiles();
	        	
	        	base = base.length() == 0 ? "" : base + "/"; // 注意，这里用左斜杠

	        	out.putNextEntry(new ZipEntry(base));
	        	
	        	for (int i = 0; i < fl.length; i++) {
	        		
	        		zip(out, fl[i], base + fl[i].getName());
	        		
	        	}
  
	        } else {                //压缩目录中的所有文件   
	        	out.putNextEntry(new ZipEntry(base));

	            BufferedInputStream in = null;
	            
		            try{
			            in = new BufferedInputStream(new FileInputStream(f));
			            
			            byte[] buffer = new byte[1024];
			            
			            int b = in.read(buffer);
			            
			            while (b != -1) {
			            	
			            	out.write(buffer, 0, b);
			            	
			            	b = in.read(buffer);
			            	
			            }

		            } catch (Exception e) {
						
						e.printStackTrace();
						
					}finally {
						
			         try {
			         	
			             if(in!=null) {
			             	
			            	 in.close();  
			                 
			             }
			             
			         } catch (IOException e) {
			         	
			             e.printStackTrace();
			             
			         }
		         
				}
	            
	        }   
	    } 

}
