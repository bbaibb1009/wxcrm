package com.wxcrm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil 
{
	private FTPClient ftp = null;
	
	private String ip;
	
	private int port;
	
	private String username;
	
	private String password;
	
	private boolean autoMkdir = true;
	
	public FtpUtil() {
		
	}
	
	public FtpUtil(String ip, int port, String username, String password) {
		super();
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public void connect() throws Exception
	{
		if( ftp == null )
		{
			ftp = new FTPClient();
		}
		
		if( port ==  0 )
		{
			ftp.connect(ip);
		}
		else 
		{
			ftp.connect(ip, port);
		}
		
		if( ! FTPReply.isPositiveCompletion(ftp.getReplyCode()) ) 
		{
			ftp.disconnect();
			throw new Exception("FTP����ʧ��");
		}
		
		if( ! ftp.login(username, password) )
		{
			ftp.logout();
			throw new Exception("FTP��½ʧ��");
		}
	}
	
	/**
	 * @author wanglei
	 * created on Jul 26, 2012 10:18:24 PM
	 * @param pathLocal �����ļ�·��
	 * @param fileName �����ļ���
	 * @param pathRemote �������ļ�·��
	 * @throws IOException 
	 * @throws IOException 
	 * @throws Exception
	 */
	public void uploadFile(String pathLocal, String fileName, String pathRemote)
	{
		this.uploadFile(pathLocal, fileName, pathRemote, fileName);
	}
	
	/**
	 * @author wanglei
	 * created on Jul 26, 2012 10:41:22 PM
	 * @param pathLocal �����ļ�·��
	 * @param fileName �����ļ���
	 * @param pathRemote �������ļ�·��
	 * @param fileNewName ���ļ���
	 * @throws IOException 
	 */
	public void uploadFile(String pathLocal, String fileName, String pathRemote, String fileNewName)
	{
		try 
		{
			if( ! ftp.changeWorkingDirectory(pathRemote) )
			{
				throw new Exception(pathRemote + "Ŀ¼������");
			}
			InputStream is = new FileInputStream(pathLocal + fileName);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.storeFile(fileNewName, is);
			is.close();
			System.out.println("�ļ�" + pathLocal + fileName + "�ɹ��ϴ���" + pathRemote + fileNewName);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @author wanglei
	 * created on Jul 26, 2012 10:21:58 PM
	 * @param pathRemote �������ļ�·��
	 * @param fileName �������ļ���
	 * @param pathLocal �����ļ�·��
	 * @throws IOException 
	 */
	public void downloadFile(String pathRemote, String fileName, String pathLocal)
	{
		this.downloadFile(pathRemote, fileName, pathLocal, fileName);
	}
	
	/**
	 * @author wanglei
	 * created on Jul 26, 2012 10:44:31 PM
	 * @param pathRemote �������ļ�·��
	 * @param fileName �������ļ���
	 * @param pathLocal �����ļ�·��
	 * @param fileNewName ���ļ���
	 * @throws IOException
	 */
	public void downloadFile(String pathRemote, String fileName, String pathLocal, String fileNewName)
	{
		try 
		{
			if( ! ftp.changeWorkingDirectory(pathRemote) )
			{
				throw new Exception(pathRemote + "Ŀ¼������");
			}
			if( ! this.fileExists(fileName) )
			{
				throw new Exception(fileName + "�ļ�������");
			}
			File localDir = new File(pathLocal);
			if( ! localDir.exists() )
			{
				if( autoMkdir )
				{
					localDir.mkdir();
				}
				else 
				{
					throw new Exception(pathLocal + "Ŀ¼������");
				}
			}
			OutputStream os = new FileOutputStream(pathLocal + fileNewName);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.retrieveFile(fileName, os);
			os.close();
			System.out.println("�ļ�" + pathRemote + fileName + "�ɹ����ص�" + pathLocal + fileNewName);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		try 
		{
			if( ftp != null && ftp.isConnected() )
			{
				ftp.logout();
				ftp.disconnect();
				ftp = null;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private boolean fileExists(String fileName)
	{
		boolean result = false;
		try 
		{
			FTPFile[] files = ftp.listFiles();
			for( FTPFile file : files )
			{
				if( file.isFile() && file.getName().equals(fileName) )
				{
					result = true;
					break;
				}
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAutoMkdir() {
		return autoMkdir;
	}

	public void setAutoMkdir(boolean autoMkdir) {
		this.autoMkdir = autoMkdir;
	}

}
