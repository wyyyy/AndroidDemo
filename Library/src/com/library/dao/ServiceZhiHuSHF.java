package com.library.dao;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.library.bean.shfzhihu.ShfReply;
import com.library.bean.shfzhihu.ShfRoot;

public class ServiceZhiHuSHF
{
  /*
   * get data from zhihu shenhuifu
   */
  public static ArrayList<ShfRoot> getCountShfByStr(String respon)
  {
    Document doc;
    ArrayList<ShfRoot> listRtAll = new ArrayList<ShfRoot>();
    doc = Jsoup.parse(respon);

    ShfRoot rt = new ShfRoot();

    try
    {
      Elements ListItem = doc.getElementsByAttributeValue("class", "zm-item");

      for (Element element : ListItem)
      {
        rt = new ShfRoot();
        if (true)
        {
          String strDivCoent = element.outerHtml();
          Document docTitle = Jsoup.parse(strDivCoent);
          Elements eleTitle = docTitle.getElementsByAttributeValue("class", "zm-item-title");
          if (eleTitle != null && eleTitle.size() > 0)
          {
            getCountShfTitleandURl(eleTitle, rt);
            getMroeData(element.outerHtml(), rt);
            listRtAll.add(rt);
          } else
          {
            getMroeData(element.outerHtml(), listRtAll.get(listRtAll.size() - 1));
          }
        }
      }

      for (int i = 0; i < listRtAll.size(); i++)
      {
        if (true)
        {
          StringBuilder sBuilder = new StringBuilder();
          sBuilder.append(
              listRtAll.get(i).getTitle() + "Time:" + listRtAll.get(i).getUpdatetiem() + "  href:")
              .append(listRtAll.get(i).getTitleUrl());
          System.out.println(sBuilder.toString());
          if (listRtAll.get(i).get_listRtAll() == null)
          {
            System.out.println("listRtAll.get(i).get_listRtAll() == null");
          } else
          {

            for (int k = 0; k < listRtAll.get(i).get_listRtAll().size(); k++)
            {
              ShfReply sfReply = new ShfReply();
              sfReply = listRtAll.get(i).get_listRtAll().get(k);
              System.out.println(i + 1 + "--" + sBuilder.toString() + "--" + sfReply.getConent());
            }
          }
          System.out.println("------------------------");
        }

      }
    } catch (Exception e)
    {
      return null;
    }
    return listRtAll;

  }

  /*
   * for content
   */
  private static void getMroeData(String strDivCoent, ShfRoot rt)
  {
    if (rt == null)
    {
      rt = new ShfRoot();
    }
    Document docMroeData = Jsoup.parse(strDivCoent);
    // System.out.println(strDivCoent);
    Elements ListDiv23 = docMroeData.getElementsByClass("zm-item-rich-text");
    System.err.println("size():" + ListDiv23.size());
    for (Element element : ListDiv23)
    {
      String strDivCoenttext = element.outerHtml();
      getCountShfConet(strDivCoenttext, rt);
      getCountShfConetID(strDivCoent, rt);// OK listRtAll.add(rt);
    }
  }

  public static void getCountShfTitleandURl(Elements element, ShfRoot rt)
  {
    try
    {
      rt.setTitle(element.first().text());
      rt.setTitleUrl(element.first().getElementsByTag("a").first().attr("href"));
    } catch (Exception e)
    {
      // TODO: handle exception
    }

    // System.err.println(element.first().getElementsByTag("a").first().attr("href"));
    // 取回复内容-OK
    // System.out.println(strDivCoent);
    // Document dd = Jsoup.parse(strDivCoent);
    // Element dayFav = dd.getElementsByAttributeValue("class",
    // "content hidden").first();
  }

  public static void getCountShfConet(String strDivCoent, ShfRoot rt)
  {
    // 取回复内容-OK
    // System.out.println(strDivCoent);
    Document dd = Jsoup.parse(strDivCoent);
    Element dayFav = dd.getElementsByAttributeValue("class", "content hidden").first();
    getCountShfConetRemoveApan(dayFav.text(), rt);
    getCountShfConetIDurl(dayFav.text(), rt);
  }

  public static void getCountShfConetRemoveApan(String strDivCoent, ShfRoot rt)
  {

    Document doc = Jsoup.parse(strDivCoent);
    // System.err.println(doc.body().html());//doc.body().html()
    int start = strDivCoent.indexOf("<span");
    strDivCoent = strDivCoent.substring(0, start).trim();
    ShfReply shfReply = new ShfReply();
    getCountShfImggUrl(strDivCoent, shfReply);

    getCountShfConetRemoveApan(strDivCoent, shfReply);

    if (rt.get_listRtAll() == null)
    {
      // System.err.println("hui fu null");
      ArrayList<ShfReply> sfRLsit = new ArrayList<ShfReply>();
      sfRLsit.add(shfReply);
      rt.set_listRtAll(sfRLsit);
    } else
    {
      rt.get_listRtAll().add(shfReply);
    }

    Element dayFav = doc.select("span").first();
    rt.setUpdatetiem(start + dayFav.text());
  }

  public static void getCountShfConetRemoveApan(String strDivCoent, ShfReply rt)

  {

    Document docContent = Jsoup.parse(strDivCoent);
    rt.setConent(docContent.text());
    // System.err.println("55:" + docContent.text());
  }

  public static void getCountShfImggUrl(String strDivCoent, ShfReply rt)
  {
    // System.err.println(strDivCoent);
    Document docID = Jsoup.parse(strDivCoent);
    Element dayFav = docID.select("img[src]").first();
    if (dayFav != null)
    {
      String imgUrl = dayFav.attr("src");
      System.err.println(imgUrl);
      rt.setContentImgUrl(imgUrl);
      rt.setHaveImg(true);
    }

  }

  public static void getCountShfConetID(String strDivCoent, ShfRoot rt)
  {
    Document docID = Jsoup.parse(strDivCoent);
    Element dayFav = docID.select("div[data-resourceid]").first();
    ShfReply shfReply = new ShfReply();
    shfReply.setCoentId(dayFav.attr("data-resourceid"));
    rt.get_listRtAll().get(rt.get_listRtAll().size() - 1)
        .setCoentId(dayFav.attr("data-resourceid"));
  }

  public static void getCountShfConetIDurl(String strDivCoent, ShfRoot

  rt)
  {
    Document docurl = Jsoup.parse(strDivCoent);
    Element dayFav = docurl.select("a[href]").first();
    ShfReply shfReply = new ShfReply();
    shfReply.setUrlHtml(dayFav.attr("href"));
    rt.get_listRtAll().get(rt.get_listRtAll().size() - 1).setCoentId(dayFav.attr("href"));
  }

}
