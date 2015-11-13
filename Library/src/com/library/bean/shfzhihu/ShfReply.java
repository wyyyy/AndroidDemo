package com.library.bean.shfzhihu;

public class ShfReply
{
  private String urlHtml;
  private String coentId;
  private String conent;
  private String contentImgUrl;
  private boolean haveImg;

  /**
   * @return the urlHtml
   */
  public String getUrlHtml()
  {
    return urlHtml;
  }

  /**
   * @param urlHtml
   *          the urlHtml to set
   */
  public void setUrlHtml(String urlHtml)
  {
    this.urlHtml = urlHtml;
  }

  /**
   * @return the coentId
   */
  public String getCoentId()
  {
    return coentId;
  }

  /**
   * @param coentId
   *          the coentId to set
   */
  public void setCoentId(String coentId)
  {
    this.coentId = coentId;
  }

  /**
   * @return the conent
   */
  public String getConent()
  {
    return conent;
  }

  /**
   * @param conent
   *          the conent to set
   */
  public void setConent(String conent)
  {
    this.conent = conent;
  }

  public String getContentImgUrl()
  {
    return contentImgUrl;
  }

  public void setContentImgUrl(String contentImgUrl)
  {
    this.contentImgUrl = contentImgUrl;
  }

  public boolean isHaveImg()
  {
    return haveImg;
  }

  public void setHaveImg(boolean haveImg)
  {
    this.haveImg = haveImg;
  }
}
