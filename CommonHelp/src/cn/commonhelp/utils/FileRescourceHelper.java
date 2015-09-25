package cn.commonhelp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.annotation.SuppressLint;
import android.content.Context;

/*
 * 	总结：

 1 apk中有两种资源文件，使用两种不同的方式进行打开使用。
 raw使用InputStream in = getResources().openRawResource(R.raw.test);
 asset使用InputStream in = getResources().getAssets().open(fileName);

 这些数据只能读取，不能写入。
 2 SD卡中的文件使用FileInputStream和FileOutputStream进行文件的操作。
 3 存放在数据区(/data/data/..)的文件只能使用openFileOutput和openFileInput进行操作。
 注意不能使用FileInputStream和FileOutputStream进行文件的操作。
 4 RandomAccess类仅限于文件的操作，不能访问其他IO设备。它可以跳转到文件的任意位置，从当前位置开始读写
 */

public class FileRescourceHelper
{
	Context context;

	// 资源文件的读取
	@SuppressWarnings("null")
	public String ReadRawFromResource(String strChartset)
	{

		String res = "";
		try
		{

			// 得到资源中的Raw数据流

			InputStream in = null;
			// context.getResources().openRawResource(R.raw.test);
			// InputStream in =
			// context.getResources().openRawResource(R.raw.test);
			// 得到数据的大小
			int length = in.available();
			byte[] buffer = new byte[length];

			// 读取数据

			in.read(buffer);

			// 依test.txt的编码类型选择合适的编码-如果不调整会乱码

			res = EncodingUtils.getString(buffer, "BIG5");

			// 关闭
			in.close();

		} catch (Exception e)
		{

			e.printStackTrace();

		}
		return res;
	}

	// 从resource的asset中读取文件数据

	public String ReadAssettFromResource()
	{
		String fileName = "test.txt"; // 文件名字

		String res = "";

		try
		{

			// 得到资源中的asset数据流

			InputStream in = context.getResources().getAssets().open(fileName);

			int length = in.available();

			byte[] buffer = new byte[length];

			in.read(buffer);

			res = EncodingUtils.getString(buffer, "UTF-8");

		} catch (Exception e)
		{

			e.printStackTrace();

		}
		return res;
	}

	// 二 读写/data/data/<应用程序名>目录上的文件:

	// 写数据

	@SuppressLint("WorldReadableFiles")
	public void writeFile(String fileName, String writestr)
	{
		try
		{
			@SuppressWarnings("deprecation")
			FileOutputStream fout = context.openFileOutput(fileName,
					Context.MODE_WORLD_READABLE);
			byte[] bytes = writestr.getBytes();
			fout.write(bytes);
			fout.close();
		} catch (Exception e)
		{

			e.printStackTrace();
		}
	}

	// 读数据

	public String readFile(String fileName)
	{

		String res = "";
		try
		{
			FileInputStream fin = context.openFileInput(fileName);
			int length = fin.available();
			byte[] buffer = new byte[length];
			fin.read(buffer);
			res = EncodingUtils.getString(buffer, "UTF-8");
			fin.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}

	// 三 读写SD卡中的文件 就是/mnt/sdcard/目录下面的文件

	// 写数据到SD中的文件

	public void writeFileSdcardFile(String fileName, String write_str)
	{

		try
		{

			FileOutputStream fout = new FileOutputStream(fileName);
			byte[] bytes = write_str.getBytes();

			fout.write(bytes);
			fout.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// 读SD中的文件

	public String readFileSdcardFile(String fileName)
	{

		String res = "";

		try
		{

			FileInputStream fin = new FileInputStream(fileName);

			int length = fin.available();

			byte[] buffer = new byte[length];

			fin.read(buffer);

			res = EncodingUtils.getString(buffer, "UTF-8");

			fin.close();

		}

		catch (Exception e)
		{

			e.printStackTrace();

		}

		return res;
	}

	// 读文件四 使用File类进行文件的读写

	public String readSDFile(String fileName) throws IOException
	{
		String res = "";
		File file = new File(fileName);
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int length = fis.available();
		byte[] buffer = new byte[length];
		fis.read(buffer);
		res = EncodingUtils.getString(buffer, "UTF-8");
		fis.close();
		return res;
	}

	// 写文件

	public void writeSDFile(String fileName, String write_str)
			throws IOException
	{

		File file = new File(fileName);

		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream(file);
			byte[] bytes = write_str.getBytes();
			fos.write(bytes);
			fos.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			fos.close();
			e.printStackTrace();
		}

	}

	//

	public void name()
	{

		/*
		 * String Name = File.getName(); //获得文件或文件夹的名称：
		 * 
		 * 
		 * 
		 * 
		 * String parentPath = File.getParent(); 获得文件或文件夹的父目录
		 * 
		 * 
		 * String path = File.getAbsoultePath();//绝对路经
		 * 
		 * 
		 * 
		 * String path = File.getPath();//相对路经
		 * 
		 * 
		 * File.createNewFile();//建立文件
		 * 
		 * 
		 * 
		 * 
		 * File.mkDir(); //建立文件夹
		 * 
		 * 
		 * 
		 * 
		 * File.isDirectory(); //判断是文件或文件夹
		 * 
		 * 
		 * 
		 * File[] files = File.listFiles(); //列出文件夹下的所有文件和文件夹名
		 * 
		 * 
		 * 
		 * 
		 * File.renameTo(dest); //修改文件夹和文件名
		 * 
		 * 
		 * 
		 * File.delete(); //删除文件夹或文件
		 */
	}

}
