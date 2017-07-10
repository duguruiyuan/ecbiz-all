package org.ferrari.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
/** 
 * <p>FileUtil.java</p> 
 * <p>File processing</p>
 * <Detail Description>
 * @author diff_i@hotmail.com
 */
public class FileUtils {
    /**
     * logger
     */
	private static Logger logger = Logger.getLogger(FileUtils.class.getName());
    private final static boolean SUCCESS = true;
    private final static boolean FAILURE = false;
    private FileUtils(){
    }
	/**
	 * the file can read?
	 * @param path
	 * @return
	 *  
	 */
    public static boolean canRead(String path) {
        File myFile = new File(path);
        if (myFile.canRead())
            return true;
        else
            return false;
    }
	/**
	 * the file can write?
	 * @param path
	 * @return
	 * 
	 */
    public static boolean canWrite(String path) {
        File myFile = new File(path);
        if (myFile.canWrite())
            return SUCCESS;
        else
            return FAILURE;
    }
	/**
	 * create new file.
	 * @param path
	 * @return
	 * @throws IOException
	 * 
	 */
    public static boolean createNewFile(String path) throws IOException {
        File myFile = new File(path);
        if (myFile.createNewFile())
            return SUCCESS;
        else
            return FAILURE;
    }
	/**
	 * delete file.
	 * @param path
	 * @return
	 * 
	 */
    public static boolean delete(String path) {
        File myFile = new File(path);
        if (myFile.delete())
            return SUCCESS;
        else
            return FAILURE;
    }
	/**
	 * file exists?
	 * @param path
	 * @return
	 * 
	 */
    public static boolean exists(String path) {
        File myFile = new File(path);
        if (myFile.exists())
            return SUCCESS;
        else
            return FAILURE;
    }
	/**
	 * is a directory?
	 * @param path
	 * @return
	 * 
	 */
    public static boolean isDirectory(String path) {
        File myFile = new File(path);
        if (myFile.isDirectory())
            return SUCCESS;
        else
            return FAILURE;
    }
	/**
	 * is a file?
	 * @param path
	 * @return
	 * 
	 */
    public static boolean isFile(String path) {
        File myFile = new File(path);
        if (myFile.isFile())
            return SUCCESS;
        else
            return FAILURE;
    }
	/**
	 * is a hidden file?
	 * @param path
	 * @return
	 * 
	 */
    public static boolean isHidden(String path) {
        File myFile = new File(path);
        if (myFile.isHidden())
            return SUCCESS;
        else
            return FAILURE;
    }
	/**
	 * 
	 * get last modified time.
	 * @param path
	 * @return
	 * 
	 */
    public static Timestamp lastModified(String path) {
        File myFile = new File(path);
        return new Timestamp(myFile.lastModified());
    }
	/**
	 * 
	 * file length
	 * @param path
	 * @return
	 * 
	 */
    public static long length(String path) {
        File myFile = new File(path);
        return myFile.length();
    }
	/**
	 * 
	 * file list with string return.
	 * @param path
	 * @return
	 * 
	 */
    public static String listString(String path) {
        File myFile = new File(path);
        String[] arrayList = myFile.list();

        return StringUtil.arrayToCsvString(arrayList).toString();
    }
    /**
     * 
     * list file.
     * @param path
     * @return
     * 
     */
    public static String[] list(String path) {
        File myFile = new File(path);
        String[] arrayList = myFile.list();

        return arrayList;
    }
    /**
     * 
     * make directory.
     * @param path
     * @return
     * 
     */
    public static boolean mkdir(String path) {
        File myFile = new File(path);
        if (myFile.mkdir())
            return SUCCESS;
        else
            return FAILURE;
    }
	/**
	 * make directories.
	 * @param path
	 * @return
	 * 
	 */
    public static boolean mkdirs(String path) {
        File myFile = new File(path);
        if (myFile.mkdirs())
            return SUCCESS;
        else
            return FAILURE;
    }
	/**
	 * 
	 * rename file to another name.
	 * @param fromPath
	 * @param toPath
	 * @return
	 * 
	 */
    public static boolean renameTo(String fromPath, String toPath) {
        File myFromFile = new File(fromPath);
        File myToFile = new File(toPath);
        if (myFromFile.renameTo(myToFile))
            return SUCCESS;
        else
            return FAILURE;
    }
    /**
     * set read only
     * @param path
     * @return
     *  
     * @see
     * @since 2007-1-7 15:30:06
     */
    public static boolean setReadOnly(String path) {
        File myFile = new File(path);
        if (myFile.setReadOnly())
            return SUCCESS;
        else
            return FAILURE;
    }
    /**
     * judge wether the file is blank.
     * @param fr
     * @return
     *  
     * @see
     * @since 2006-1-7 15:28:50
     */
    public static boolean isBlankFile(FileReader fr) {
        int ret = 0;
        try {
            ret = fr.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error("read file error");
            return false;
        }
        return (ret == -1);
    }
    public static String getLineSeparator(){
    	return System.getProperty("line.separator");
    }
    /**
     * write text file
     * @param fileName
     * @param fileContent
     * @throws IOException
     *  
     * @see
     * @since 2006-1-7 15:29:19
     */
    public static void writeTextFile(String fileName, String fileContent) throws IOException {

        File file = new File(fileName);
        File path = file.getParentFile();
        if (!path.exists()) {
            path.mkdirs();
        }
        FileWriter fw = new FileWriter(fileName);
        fw.write(fileContent);
        fw.flush();
        fw.close();
    }
    public static void writeTextFile(String fileName, List<String> lines) throws IOException {
    	String content = StringUtil.listToString(lines, getLineSeparator());
    	writeTextFile(fileName, content);
    }
    /**
     * write java file.
     * @param folder
     * @param className
     * @param fileContent
     * @throws IOException
     */
    public static void writeJavaFile(String folder, String className, String fileContent) throws IOException {
    	String fileName = mergeFileName(folder, className, "java");
        File file = new File(fileName);
        File path = file.getParentFile();
        if (!path.exists()) {
            path.mkdirs();
        }
        FileWriter fw = new FileWriter(fileName);
        fw.write(fileContent);
        fw.flush();
        fw.close();
    } 
    private static String mergeFileName(String folder, String className, String extName) {
		String[] dirs = StringUtil.stringToArray(className, ".");
		if (dirs.length == 1) {
			return folder + File.separator + className + "." + extName;
		}
		String path = folder;
		String lastChar = path.substring(path.length() - 1, path.length());
		if (lastChar.equals("/") || lastChar.equals("\\")) {
			path = path.substring(0, path.length() - 1);
		}
		for (int i = 0; i < dirs.length; i++) {
			path += File.separator + dirs[i];
		}
		return path + "." + extName;
	}
    /**
     * read text file
     * @param fileName
     * @return
     * @throws IOException
     *  
     * @see
     * @since 2007-1-7 15:30:22
     */
    public static StringBuffer readTextFile(String fileName) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            sb.append(line + StringUtil.crLf());
            line = br.readLine();
        }
        br.close();
        fr.close();
        return sb;
    }
    /**
     * get path
     * @param fileName
     * @return
     *  
     * @see
     * @since 2007-1-7 15:30:32
     */
    public static String getPath(String fileName) {
		File file = new File(fileName);
		if (file.isDirectory()) {
			return fileName;
		} else {
			return file.getParent();
		}
	}
    /**
     * List all files with sub directory.
     * @param directory
     * @return
     *  
     * @see
     * @since 2007-1-7 15:30:44
     */
    public static String[] listFiles(String directory) {
        ArrayList<String> list = new ArrayList<String>();
        File f = (new File(directory));
        if (f.isDirectory()) {
            File[] fe = f.listFiles();
            go_on : for (int i = 0; i < fe.length; i++) {
                if (fe[i].isDirectory()) {
                    File[] fe1 = fe[i].listFiles();
                    for (int j = 0; j < fe1.length; j++) {
                        if (fe1[j].isDirectory())
                            continue go_on;
                        list.add(fe1[j].toString());
                    }
                } else
                    list.add(fe[i].toString());
            }
        }
        String[] files = new String[list.size()];
        return (String[]) list.toArray(files);

    }
    public static List<String> listL1Directory(String directory) {
		ArrayList<String> list = new ArrayList<String>();
		File f = (new File(directory));
		if (f.isDirectory()) {
			File[] fe = f.listFiles();
			for (int i = 0; i < fe.length; i++) {
				if (fe[i].isDirectory()) {
					list.add(fe[i].getName());
				}
			}
		}
		return list;
	}    
    /**
     * get file from url.
     * @param urlStr
     * @param fileName
     * @throws IOException
     *  
     * @see
     * @since 2007-1-7 14:47:19
     */
    public static void getFileFromUrl(String urlStr, String fileName) throws IOException {
        int bytesum = 0;
        int byteread = 0;

        URL url = new URL(urlStr);
        URLConnection conn = url.openConnection();
        InputStream inStream = conn.getInputStream();
        FileOutputStream fs = new FileOutputStream(fileName);

        byte[] buffer = new byte[1444];
        while ((byteread = inStream.read(buffer)) != -1) {

            bytesum += byteread;
            System.out.println(bytesum);
            fs.write(buffer, 0, byteread);
        }

    }
    /**
     * create file
     * @param pathName
     * @return
     * @throws IOException
     *  
     * @see
     * @since 2007-1-7 14:47:31
     */
    public static File createFile(String pathName) throws IOException {
        File file = new File(pathName);
        try {
            File parent = file.getParentFile();
            if (!parent.exists())
                parent.mkdirs();
            if (file.exists()) {
                file.delete();
                logger.warn("file " + pathName + " exist!");
            }
            file.createNewFile();
        } catch (IOException e) {
            logger.error(e);
            throw e;
        }
        return file;
    }
    /**
     * create file
     * @param path
     * @param fileName
     * @return
     * @throws IOException
     *  
     * @see
     */
    public static File createFile(String path, String fileName) throws IOException {
        File file = new File(path, fileName);
        try {
            File parent = new File(path);
            if (!parent.exists())
                parent.mkdirs();
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            logger.error(e);
            throw e;
        }
        return file;
    }

    public static final void newFolder(String folderPath) {
		String filePath = folderPath;
		filePath = filePath.toString();
		java.io.File myFilePath = new java.io.File(filePath);
		if (!myFilePath.exists()) {
			myFilePath.mkdir();
		}
	}

    /**
	 * Create file
	 * 
	 * @param filePathAndName
	 *            String ??????? ?c:/fqf.txt
	 * @param fileContent
	 *            String ????
	 * @return boolean
	 * @throws IOException
	 */
    public static final synchronized void newFile(String filePathAndName, String fileContent) throws IOException {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            String strContent = fileContent;
            myFile.println(strContent);
            resultFile.close();
        } catch (IOException e) {
        	logger.error(e);
        	throw e;
        }
    }

    /**
     * Delete file
     * @param filePathAndName String dir, for example c:/fqf.txt
     * @param fileContent String
     * @return boolean
     */
    public static final synchronized void deleteFile(String filePathAndName) {
		String filePath = filePathAndName;
		filePath = filePath.toString();
		java.io.File myDelFile = new java.io.File(filePath);
		myDelFile.delete();
	}

    /**
	 * Delete directory
	 * 
	 * @param filePathAndName
	 *            String dir, for example:c:/fqf
	 * @param fileContent
	 *            String
	 * @throws IOException
	 */
    public static final synchronized void deleteFolder(String folderPath) {
            deleteAllFile(folderPath); 
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); 
    }

    /**
     * Delete all files of directory
     * @param path String directory for example c:/fqf
     */
    public static final synchronized void deleteAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                deleteAllFile(path + File.separator + tempList[i]); 
                deleteFolder(path + File.separator + tempList[i]); 
            }
        }
    }

    /**
     * Copy a file
     * @param oldPath String source dir for example?c:/fqf.txt
     * @param newPath String dest dir for example?f:/fqf.txt
     * @return boolean
     * @throws IOException 
     */
    public static final synchronized void copyFile(String oldPath, String newPath) throws IOException {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; 
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (IOException e) {
        	logger.error(e);
            throw e;
        }

    }

    /**
     * Copy the content of directory
     * @param oldPath String source dir for example?c:/fqf
     * @param newPath String dest dir for example?f:/fqf/ff
     * @return void
     * @throws IOException 
     */
    public static final synchronized void copyFolder(String oldPath, String newPath) throws IOException {

        try {
            (new File(newPath)).mkdirs();
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + File.separator + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {
                    copyFolder(oldPath + File.separator + file[i], newPath + File.separator + file[i]);
                }
            }
        } catch (IOException e) {
        	logger.error(e);
            throw e;
        }

    }

    /**
     * Move files to directory
     * @param oldPath String for example?c:/fqf.txt
     * @param newPath String for example?d:/fqf.txt
     * @throws IOException 
     */
    public static final synchronized void moveFile(String oldPath, String newPath) throws IOException {
        copyFile(oldPath, newPath);
        deleteFile(oldPath);

    }

    /**
     * Move directory to directory 
     * @param oldPath String for example?c:/fqf.txt
     * @param newPath String for example?d:/fqf.txt
     * @throws IOException 
     */
    public static final synchronized void moveFolder(String oldPath, String newPath) throws IOException {
        copyFolder(oldPath, newPath);
        deleteFolder(oldPath);

    }
    /**
     * parse text file to list.
     * @param fileName
     * @return
     * @throws IOException
     */
	public static final List<String> parseTextFile(String fileName) throws IOException {
		if (fileName == null)
			return null;
		List<String> list = new ArrayList<String>();
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			list.add(line);
			line = br.readLine();
		}
		br.close();
		fr.close();
		return list;
	}    
    /**
     * parse text file
     * @param fileName
     * @return
     * @throws IOException
     *  
     * @see
     * @since 2007-1-7 14:48:00
     */
    public static final List<String[]> parseTextFile(String fileName, String delimitor, String qualifier) throws IOException {
        if (fileName == null)
            return null;
        List<String[]> list = new ArrayList<String[]>();
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            if (delimitor == null) {
                list.add(new String[] { StringUtil.exTrim(line, qualifier)});
            } else {
                list.add(StringUtil.stringToArray(line, delimitor, qualifier));
            }
            line = br.readLine();
        }
        br.close();
        fr.close();
        return list;
    }
    public static final List<String> parseTextFile(String fileName, String linePat) throws IOException {
        if (fileName == null)
            return null;
        List<String> list = new ArrayList<String>();
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        StringBuffer buf = new StringBuffer();
        while (line != null) {
            if(line.indexOf(linePat)!=-1){
            	if(buf.length()>0){
            		list.add(buf.toString());
            	}
            	buf=new StringBuffer();
            	buf.append(line).append(" ");
            } else{
            	buf.append(line).append(" ");
            }
            line = br.readLine();
        }
    	if(buf.length()>0){
    		list.add(buf.toString());
    	}
        br.close();
        fr.close();
        return list;
    }
    /**
     * parse cvs text file
     * @param fileName
     * @return
     * @throws IOException
     *  
     * @see
     * @since 2007-1-7 15:26:29
     */
    @SuppressWarnings("unchecked")
	public static final List<?> parseCsvTextFile(String fileName) throws IOException {
        if (fileName == null)
            return null;
        List list = new ArrayList();
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            list.add(StringUtil.splitCsvStringToArray(line));
            line = br.readLine();
        }
        br.close();
        fr.close();
        return list;
    }
	public static final byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		// Get the size of the file
		long length = file.length();
		if (length > Integer.MAX_VALUE) { 
			throw new IOException("Can not read file,file size too big");       	
		}
		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];
		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Can not completely read file " + file.getName());
		}
		// Close the input stream and return bytes
		is.close();
		return bytes;
	}
	public static final byte[] getBytesFromFile(String fileName) throws IOException {
		if(fileName == null || fileName.length() == 0){
			throw new IOException("File name is null.");
		}
		File file = new File(fileName);
		return getBytesFromFile(file);
	}
	/**
	 * read text file
	 * @param fileName
	 * @return
	 * @throws IOException
	 *  
	 * @see
	 * @since 2007-1-7 15:30:22
	 */
	public static final String getTextFromFile(String fileName) throws IOException {
		StringBuffer sb = new StringBuffer();
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			sb.append(line).append(getLineSeparator());
			line = br.readLine();
		}
		br.close();
		fr.close();
		return sb.toString();
	}	
	/**
	 * get file extended name.
	 * @param fileName
	 * @return
	 */
	public static String getExtName(String fileName) {
		if (fileName == null || fileName.trim().length() == 0) {
			return "";
		}
		int index = fileName.lastIndexOf(".");
		return index == -1 ? "" : fileName.substring(index + 1);
	}	
	/**
	 * close outputstream without exception threw.
	 * @param os
	 */
	public static void close(OutputStream os) {
		try {
			os.close();
		} catch (Exception e) {
			logger.error("close outputstream error", e);
		}
	}	
	/**
	 * close inputstream without exception threw.
	 * @param is
	 */
	public static void close(InputStream is) {
		try {
			is.close();
		} catch (Exception e) {
			logger.error("close inputstream error", e);
		}
	}
	public static boolean makeDirectory(String fileName){
		if(fileName == null || fileName.length() == 0){
			throw new NullPointerException("fileNaem is null");
		}
		String path = getPath(fileName);
		System.out.println("path="+path);
		File file = new File(path);
		if(!file.exists()){
			return mkdirs(path);
		}
		return false;
	}
    public static void main(String[] args) throws IOException {

//    	File file = new File("d:/temp/ab1.jpg");
//    	System.out.println(getPath("d:/temp/ab1.jpg"));
    }
}

