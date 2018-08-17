package com.hive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import javax.security.sasl.AuthenticationException;
import org.apache.hive.service.auth.PasswdAuthenticationProvider;

public class CoustomerUserPasswordAuth
  implements PasswdAuthenticationProvider
{
  private Properties p = null;
  
  public CoustomerUserPasswordAuth()
  {
    init();
  }
  
  public void Authenticate(String userName, String passwd)
    throws AuthenticationException
  {
    System.out.println("user: " + userName + " try login.");
    if (this.p == null)
    {
      String message = "load auth  properties file fail";
      System.out.println("load auth  properties file fail");
      throw new AuthenticationException(message);
    }
    if (!this.p.containsKey(userName))
    {
      String message = "user's ACL configration is not found. user:" + userName;
      System.out.println(message);
      throw new AuthenticationException(message);
    }
    String userpwd = this.p.getProperty(userName);
    if (userpwd == null)
    {
      String message = "user's ACL configration is not found. user:" + userName;
      System.out.println(message);
      throw new AuthenticationException(message);
    }
    if (!userpwd.equals(passwd))
    {
      String message = "user name and password is mismatch. user:" + userName;
      throw new AuthenticationException(message);
    }
    System.out.println("user " + userName + " login system successfully.");
  }
  
  public void init()
  {
    this.p = new Properties();
    String filepath = "";
    String hive_home = System.getenv("HIVE_HOME");
    if ((hive_home == null) || (hive_home.isEmpty())) {
      filepath = "prop.properties";
    } else {
      filepath = hive_home + File.separator + "conf" + File.separator + "prop.properties";
    }
    try
    {
      FileInputStream fis = new FileInputStream(filepath);
      this.p.load(fis);
      fis.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("user properties file not found ");
    }
    catch (IOException e)
    {
      System.out.println(" properties load error ");
    }
  }
  
  
}
