package cn.commonhelp.http_ansyn.entity;


import java.io.Serializable;
import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class Login implements Serializable{
	
	/** 
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = 1L;
	
	/**成功�?201；失败：'401'~'402'~'403'；禁用：'402'*/
	public String resultcode;
	/**no;yes*/
	public String forbidden;
	/**弹窗提示的内�?*/
	public String alertMsg;
	/**用户唯一标识*/
	public String user_id;
	/**通信授权验证字符�?*/
	public String encrypt;
	
	/**
	 * @Description: 
	 * @author vic
	 * @param context
	 * @param data
	 * @param isCache
	 * @return
	 */
	public Login converInfo(Context context,String data){
		String tempItem = null;
		try {
			Field[] fields = this.getClass().getDeclaredFields();
				JSONObject jsonObject = new JSONObject(data);
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					try {
						tempItem = fields[j].getName();
						if(tempItem.equals("serialVersionUID") || jsonObject.isNull(tempItem) || jsonObject.get(tempItem) == JSONObject.NULL) continue;
						field.set(this, jsonObject.get(tempItem).toString());
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public String toString() {
		return "Login [resultcode=" + resultcode + ", forbidden=" + forbidden
				+ ", alertMsg=" + alertMsg + ", user_id=" + user_id
				+ ", encrypt=" + encrypt + "]";
	}
}
