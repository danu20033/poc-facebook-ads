package com.facebook.support.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import lombok.Synchronized;

import com.facebook.support.FacebookAdSupportUtilityException;

/**
 * Property file reader utility class.
 */
public final class PropertyFileReader {
   /**
    * Instance holder for Properties.
    */
   private final Properties properties;
   /**
    * Singleton instance holder for PropertyFileReader.
    */
   private static PropertyFileReader instance;

   /**
    * Private constructor.
    *
    * @throws FacebookAdSupportUtilityException
    *            when
    */
   private PropertyFileReader() {
      InputStream lInputStream = PropertyFileReader.class.getClassLoader().getResourceAsStream("application.properties");
      try {
         properties = new Properties();
         properties.load(lInputStream);
      } catch (IOException pException) {
         throw new FacebookAdSupportUtilityException("Exception occurred while reading the application properties.", pException);
      }
   }

   /**
    * Get the PropertyFileReader instance.
    *
    * @return PropertyFileReader instance.
    */
   @Synchronized
   private static PropertyFileReader getInstance() {
      if (Objects.isNull(instance)) {
         instance = new PropertyFileReader();
      }

      return instance;
   }

   /**
    * Get the value of the given key.
    *
    * @param pKey
    *           the key.
    * @return the value of the given kye.
    */
   public static String getProperty(final String pKey) {
      return getInstance().properties.getProperty(pKey);
   }

}
