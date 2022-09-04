package com.facebook.support;

import java.util.List;

import com.facebook.ads.sdk.CustomAudience;
import com.facebook.support.model.CustomAudienceModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Test class for AdAccountHelper audience related scenarios.
 *
 * for test this use following :
 *
 *
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdAccountHelperAudienceTest {

   /**
    * AdAccountHelper instance holder.
    */
   private static AdAccountHelper adAccountHelper;

   /**
    * CustomAudience instance to be set.
     */
   private static CustomAudience customAudience;

   /**
    * The setup method.
    */
   @BeforeAll
   public static void setUp() {
      adAccountHelper = new AdAccountHelper();
   }

   /**
    * Test fetch audiences.
    */
   @Test
   @Order(50)
   public void testFetchAudiences() {
      List<CustomAudience> customAudienceList = adAccountHelper.fetchAudiences();

      Assertions.assertNotNull(customAudienceList);

   }

   /**
    * Test create custom audience.
    */

   @Test
   @Order(1)
   public void testCreateCustomAudience() {
      CustomAudienceModel customAudienceModel = new CustomAudienceModel();
      customAudienceModel.setName("My new Custom Audience " + Math.random());
      customAudienceModel.setDescription("People who purchased on my website");
      customAudienceModel.setSubtype(CustomAudience.EnumSubtype.VALUE_CUSTOM);
      customAudienceModel.setCustomerFileSource(CustomAudience.EnumCustomerFileSource.VALUE_USER_PROVIDED_ONLY);
      customAudience = adAccountHelper.createAudience(customAudienceModel);
      Assertions.assertNotNull(customAudience);
      Assertions.assertNotNull(customAudience.getId());
   }

   /**
    * Test create custom audience.
    */
   @Test
   @Order(2)
   @Disabled("Source is Too Small: Please choose a source that includes at least 100 people in the same country.")
   public void testCreateLookalikeCustomAudience() {
      CustomAudienceModel customAudienceModel = new CustomAudienceModel();
      customAudienceModel.setName("Value-Based lookalike" + Math.random());
      customAudienceModel.setDescription("People who purchased on my website");
      customAudienceModel.setSubtype(CustomAudience.EnumSubtype.VALUE_LOOKALIKE);
      customAudienceModel.setCustomerFileSource(CustomAudience.EnumCustomerFileSource.VALUE_USER_PROVIDED_ONLY);
      customAudienceModel.setLookalikeSpec("{\"type\":\"custom_ratio\",\"ratio\":0.20,\"country\":\"US\"}");
      customAudienceModel.setOriginalAudienceId(customAudience.getId());
      CustomAudience lLookalikeAudience = adAccountHelper.createLookalikeAudience(customAudienceModel);

      Assertions.assertNotNull(lLookalikeAudience);
      Assertions.assertNotNull(lLookalikeAudience.getId());
   }


   /**
    * Test update custom audience.
    */
   @Test
   @Order(3)
   public void testUpdateAudience(){
      customAudience.update().setName("updated.."+Math.random());
      customAudience= adAccountHelper.updateCustomAudience(customAudience);

      Assertions.assertNotNull(customAudience);
   }

}
