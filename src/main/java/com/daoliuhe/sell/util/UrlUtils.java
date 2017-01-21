package com.daoliuhe.sell.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUtils {

  public static String encode(String s) {
    try {
      return URLEncoder.encode(s, Config.ENC_UTF8);
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }
}
