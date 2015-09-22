/*
 * file  there are many errors need to edit;
 */
package cn.commonhelp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

/*
 * file directory
 */
public class FileUtil
{
	private Context context;
	private String SDPATH;
	private String FILESPATH;

	public FileUtil(Context context)
	{
		this.context = context;
		SDPATH = Environment.getExternalStorageDirectory().getPath() + "\\";
		FILESPATH = this.context.getFilesDir().getPath() + "\\";
	}

	public boolean delSDFile(String fileName)
	{
		File file = new File(SDPATH + fileName);
		if (file == null || !file.exists() || file.isDirectory())
			return false;
		file.delete();
		return true;
	}

	public File creatSDDir(String dirName)
	{
		File dir = new File(SDPATH + dirName);
		dir.mkdir();
		return dir;
	}

	public boolean delSDDir(String dirName)
	{
		File dir = new File(SDPATH + dirName);
		return delDir(dir);
	}

	public boolean renameSDFile(String oldfileName, String newFileName)
	{
		File oleFile = new File(SDPATH + oldfileName);
		File newFile = new File(SDPATH + newFileName);
		return oleFile.renameTo(newFile);
	}

	public boolean copySDFileTo(String srcFileName, String destFileName)
			throws IOException
	{
		File srcFile = new File(SDPATH + srcFileName);
		File destFile = new File(SDPATH + destFileName);
		return copyFileTo(srcFile, destFile);
	}

	public boolean copySDFilesTo(String srcDirName, String destDirName)
			throws IOException
	{
		File srcDir = new File(SDPATH + srcDirName);
		File destDir = new File(SDPATH + destDirName);
		return copyFilesTo(srcDir, destDir);
	}

	public boolean moveSDFileTo(String srcFileName, String destFileName)
			throws IOException
	{
		File srcFile = new File(SDPATH + srcFileName);
		File destFile = new File(SDPATH + destFileName);
		return moveFileTo(srcFile, destFile);
	}

	public boolean moveSDFilesTo(String srcDirName, String destDirName)
			throws IOException
	{
		File srcDir = new File(SDPATH + srcDirName);
		File destDir = new File(SDPATH + destDirName);
		return moveFilesTo(srcDir, destDir);
	}

	public FileOutputStream writeSDFile(String fileName) throws IOException
	{
		File file = new File(SDPATH + fileName);
		FileOutputStream fos = new FileOutputStream(file);
		return (fos);
	}

	public FileOutputStream appendSDFile(String fileName) throws IOException
	{
		File file = new File(SDPATH + fileName);
		FileOutputStream fos = new FileOutputStream(file, true);
		return (fos);
	}

	public FileInputStream readSDFile(String fileName) throws IOException
	{
		File file = new File(SDPATH + fileName);
		FileInputStream fis = new FileInputStream(file);
		return (fis);
	}

	public File creatDataFile(String fileName) throws IOException
	{
		File file = new File(FILESPATH + fileName);
		file.createNewFile();
		return file;
	}

	public File creatDataDir(String dirName)
	{
		File dir = new File(FILESPATH + dirName);
		dir.mkdir();
		return dir;
	}

	public boolean delDataFile(String fileName)
	{
		File file = new File(FILESPATH + fileName);
		return delFile(file);
	}

	public boolean delDataDir(String dirName)
	{
		File file = new File(FILESPATH + dirName);
		return delDir(file);
	}

	public boolean renameDataFile(String oldName, String newName)
	{
		File oldFile = new File(FILESPATH + oldName);
		File newFile = new File(FILESPATH + newName);
		return oldFile.renameTo(newFile);
	}

	public boolean copyDataFileTo(String srcFileName, String destFileName)
			throws IOException
	{
		File srcFile = new File(FILESPATH + srcFileName);
		File destFile = new File(FILESPATH + destFileName);
		return copyFileTo(srcFile, destFile);
	}

	public boolean copyDataFilesTo(String srcDirName, String destDirName)
			throws IOException
	{
		File srcDir = new File(FILESPATH + srcDirName);
		File destDir = new File(FILESPATH + destDirName);
		return copyFilesTo(srcDir, destDir);
	}

	public boolean moveDataFileTo(String srcFileName, String destFileName)
			throws IOException
	{
		File srcFile = new File(FILESPATH + srcFileName);
		File destFile = new File(FILESPATH + destFileName);
		return moveFileTo(srcFile, destFile);
	}

	public boolean moveDataFilesTo(String srcDirName, String destDirName)
			throws IOException
	{
		File srcDir = new File(FILESPATH + srcDirName);
		File destDir = new File(FILESPATH + destDirName);
		return moveFilesTo(srcDir, destDir);
	}

	@SuppressLint("WorldWriteableFiles")
	public OutputStream wirteFile(String fileName) throws IOException
	{
		@SuppressWarnings("deprecation")
		OutputStream os = context.openFileOutput(fileName,
				Context.MODE_WORLD_WRITEABLE);
		return (os);
	}

	public OutputStream appendFile(String fileName) throws IOException
	{
		OutputStream os = context.openFileOutput(fileName, Context.MODE_APPEND);
		return (os);
	}

	public InputStream readFile(String fileName) throws IOException
	{
		InputStream is = context.openFileInput(fileName);
		return (is);
	}

	public boolean copyFilesTo(File srcDir, File destDir) throws IOException
	{
		if (!srcDir.isDirectory() || !destDir.isDirectory())
			return false;// 判断是否是目录
		if (!destDir.exists())
			return false;// 判断目标目录是否存在
		File[] srcFiles = srcDir.listFiles();
		for (int i = 0; i < srcFiles.length; i++)
		{
			if (srcFiles[i].isFile())
			{
				// 获得目标文件
				File destFile = new File(destDir.getPath() + "\" "
						+ srcFiles[i].getName());
				copyFileTo(srcFiles[i], destFile);
			} else if (srcFiles[i].isDirectory())
			{
				File theDestDir = new File(destDir.getPath() + "\" "
						+ srcFiles[i].getName());
				copyFilesTo(srcFiles[i], theDestDir);
			}
		}
		return true;
	}

	public boolean delFile(File file)
	{
		if (file.isDirectory())
			return false;
		return file.delete();
	}

	public boolean delDir(File dir)
	{
		if (dir == null || !dir.exists() || dir.isFile())
		{
			return false;
		}
		for (File file : dir.listFiles())
		{
			if (file.isFile())
			{
				file.delete();
			} else if (file.isDirectory())
			{
				delDir(file);// 递归
			}
		}
		dir.delete();
		return true;
	}

	public boolean copyFileTo(File srcFile, File destFile) throws IOException
	{
		if (srcFile.isDirectory() || destFile.isDirectory())
			return false;// 判断是否是文件
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(destFile);
		int readLen = 0;
		byte[] buf = new byte[1024];
		while ((readLen = fis.read(buf)) != -1)
		{
			fos.write(buf, 0, readLen);
		}
		fos.flush();
		fos.close();
		fis.close();
		return true;
	}

	public boolean moveFileTo(File srcFile, File destFile) throws IOException
	{
		boolean iscopy = copyFileTo(srcFile, destFile);
		if (!iscopy)
			return false;
		delFile(srcFile);
		return true;
	}

	public boolean moveFilesTo(File srcDir, File destDir) throws IOException
	{
		if (!srcDir.isDirectory() || !destDir.isDirectory())
		{
			return false;
		}
		File[] srcDirFiles = srcDir.listFiles();
		for (int i = 0; i < srcDirFiles.length; i++)
		{
			if (srcDirFiles[i].isFile())
			{
				File oneDestFile = new File(destDir.getPath() + "\\"
						+ srcDirFiles[i].getName());
				moveFileTo(srcDirFiles[i], oneDestFile);
				delFile(srcDirFiles[i]);
			} else if (srcDirFiles[i].isDirectory())
			{
				File oneDestFile = new File(destDir.getPath() + "\\"
						+ srcDirFiles[i].getName());
				moveFilesTo(srcDirFiles[i], oneDestFile);
				delDir(srcDirFiles[i]);
			}
		}
		return true;
	}

	public static String convertStreamToString(InputStream is) throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null)
		{
			sb.append(line).append("\n");
		}
		return sb.toString();
	}

	public static String getStringFromFile(File file) throws Exception
	{
		FileInputStream fin = new FileInputStream(file);
		String ret = convertStreamToString(fin);
		// Make sure you close all streams.
		fin.close();
		return ret;
	}

	/*
	 * read file
	 */
	public static String readTxtFile(String filePath)
	{
		StringBuffer rtSt = new StringBuffer();
		try
		{
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists())
			{ // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null)
				{
					// System.out.println(lineTxt);

					rtSt.append(lineTxt);
				}
				read.close();
			} else
			{
				System.out.println("找不到指定的文件");
			}

		} catch (Exception e)
		{
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return rtSt.toString();
	}
}
