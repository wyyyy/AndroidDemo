package cn.commonhelp.http_ansyn.entity;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

/**
 * ��ҳ�����˵�
 *
 * ����ҳ��˵�
 * @author chen
 * @date 2012-11-5 ����11:32:52
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = -7459567002575163628L;
    /// <summary>
    /// ����Ŀ¼������
    /// </summary>
    public String ParentId;
    /// <summary>
    /// ����·��
    /// </summary>
    public String FamilyPath;
    /// <summary>
    /// ����������
    /// </summary>
    public String CategoryId;
    /// <summary>
    /// ����
    /// </summary>
    public String Title;
    /** �ּ�Tilte */
    public String CategoryTitle;
    /** �۸� */
    public Double Price;

    public String CreateDate;
    public Integer Sort;

	/**
	 * ������ݼ�
	 * @param data
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 * @date 2012-11-2 ����2:06:30
	 */
	public ArrayList<Menu> getListInfo(String data) throws XmlPullParserException, IOException {
		InputStream inStream = new ByteArrayInputStream(data.getBytes());

		ArrayList<Menu> munus = null;
		Menu menu = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inStream, "UTF-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:// �жϵ�ǰ�¼��Ƿ����ĵ���ʼ�¼�
				munus = new ArrayList<Menu>();// ��ʼ������
				break;
			case XmlPullParser.START_TAG:// �жϵ�ǰ�¼��Ƿ��Ǳ�ǩԪ�ؿ�ʼ�¼�
				if ("Item".equals(parser.getName())) {// �жϿ�ʼ��ǩԪ��
					menu = new Menu();
					// area.setId(Integer.parseInt(parser.getAttributeValue(0)));//�õ�book��ǩ������ֵ��������book��id
					// <book id="1">
				}
				if (menu != null) {
					if ("CategoryId".equals(parser.getName())) {// �жϿ�ʼ��ǩԪ��
						menu.CategoryId = parser.nextText().trim();
					} else if ("Title".equals(parser.getName())) {
						menu.Title = parser.nextText().trim();
					} else if ("ParentId".equals(parser.getName())) {
						menu.ParentId = parser.nextText().trim();
					} else if ("FamilyPath".equals(parser.getName())) {
						menu.FamilyPath = parser.nextText().trim();
					} else if ("CategoryTitle".equals(parser.getName())) {
						menu.CategoryTitle = parser.nextText().trim();
					} else if ("Price".equals(parser.getName())) {
						menu.Price = Double.parseDouble(parser.nextText().trim());
					} else if ("Sort".equals(parser.getName())) {
						menu.Sort = Integer.parseInt(parser.nextText().trim());
					} else if ("CreateDate".equals(parser.getName())) {
						menu.CreateDate = parser.nextText().trim();
					}
				}
				break;
			case XmlPullParser.END_TAG:// �жϵ�ǰ�¼��Ƿ��Ǳ�ǩԪ�ؽ����¼�
				if ("Item".equals(parser.getName())) {// �жϽ�����ǩԪ��
					munus.add(menu);// ��ӵ�����
					menu = null;
				}
				break;
			}
			eventType = parser.next();
		}
		inStream.close();
		return munus;
	}

	/**
	 * �����������
	 * @param data
	 * @return
	 * @date 2012-11-13 ����4:31:45
	 */
	public ArrayList<ArrayList<Menu>> getSortInfo(List<Menu> data){
//		List<String> s = new ArrayList<String>();

		// ����
		ComparatorUser comparator = new ComparatorUser();
		Collections.sort(data, comparator);

		// ����
		ArrayList<ArrayList<Menu>> lldata = new ArrayList<ArrayList<Menu>>();
		int id = -1;
		ArrayList<Menu> ldata = new ArrayList<Menu>();

		for (Menu m : data) {
			// System.out.println(m);
			if (id != m.Sort) {
				ldata = new ArrayList<Menu>();
//				s.add(m.CategoryTitle);
				lldata.add(ldata);
			}
			ldata.add(m);
			id = m.Sort;
		}
		for (ArrayList<Menu> item : lldata) {
			ComparatorDate cd = new ComparatorDate();
			Collections.sort(item, cd);
		}

		return lldata;
	}
}

/**
 * ��������
 * @author chen
 * @date 2012-11-13 ����4:27:36
 */
class ComparatorUser implements Comparator<Menu> {
	@Override
	public int compare(Menu object1, Menu object2) {
		return object2.Sort.compareTo(object1.Sort);
	}
}
/**
 * ��������
 * @author chen
 * @date 2012-11-13 ����4:27:36
 */
class ComparatorDate implements Comparator<Menu> {
	@Override
	public int compare(Menu object1, Menu object2) {
		return object2.CreateDate.compareTo(object1.CreateDate);
	}
}
