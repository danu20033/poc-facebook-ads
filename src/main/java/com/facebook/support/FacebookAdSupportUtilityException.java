package com.facebook.support;

/**
 * Custom Exception type used in the application.
 */
public class FacebookAdSupportUtilityException extends RuntimeException {

   /**
    * Constructor for message.
    *
    * @param pMessage
    *           the exception message
    */
   public FacebookAdSupportUtilityException(final String pMessage) {
      super(pMessage);
   }

   /**
    * Constructor for specific message with stack trace.
    *
    * @param pMessage
    *           the exception message
    * @param pCause
    *           throwable stack trace.
    */
   public FacebookAdSupportUtilityException(final String pMessage, final Throwable pCause) {
      super(pMessage, pCause);
   }
}
