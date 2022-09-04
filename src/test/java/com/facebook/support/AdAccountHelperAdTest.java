package com.facebook.support;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import com.facebook.ads.sdk.*;
import com.facebook.support.model.AdCreativeModel;
import com.facebook.support.model.AdModel;
import com.facebook.support.model.AdSetModel;
import com.facebook.support.model.CampaignModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Test class for AdAccountHelper ad related scenarios.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdAccountHelperAdTest {

   public static final String PAGE_ID = "109468761789070";
   /**
    * AdAccountHelper instance holder.
    */
   private static AdAccountHelper adAccountHelper;
   /**
    * Campaign id to be set.
    */
   private static String campaignId;
   /**
    * adSetId to be set.
    */
   private static String adSetId;

   /**
    *
    * adCreative instance to be set.
    */
   private static AdCreative adCreative;

   /**
    * The setup method.
    */
   @BeforeAll
   public static void setUp() {
      adAccountHelper = new AdAccountHelper();
   }

   /**
    * Test create campaign.
    */
   @Test
   @Order(1)
   public void testCreateCampaign() {

      CampaignModel campaignModel = new CampaignModel();
      campaignModel.setName("my campaign " + Math.random());
      campaignModel.setBuyingType("AUCTION");
      campaignModel.setObjective(Campaign.EnumObjective.VALUE_LINK_CLICKS);
      campaignModel.setStatus(Campaign.EnumStatus.VALUE_PAUSED);

      Campaign campaign = adAccountHelper.createCampaign(campaignModel);

      campaignId = campaign.getId();
      Assertions.assertNotNull(campaign);
      Assertions.assertNotNull(campaign.getId());

   }

   /**
    * Test create ad set.
    */
   @Test
   @Order(2)
   public void testCreateAdSet() {

      AdSetModel adSetModel = new AdSetModel();
      adSetModel.setName("my ad set " + Math.random());
      adSetModel.setOptimizationGoal(AdAccount.EnumOptimizationGoal.VALUE_REACH);
      adSetModel.setBillingEvent(AdAccount.EnumBillingEvent.VALUE_IMPRESSIONS);
      adSetModel.setBidAmount(4L);
      adSetModel.setDailyBudget(500L);
      adSetModel.setCampaignId(campaignId);
      adSetModel.setTargeting(new Targeting().setFieldGeoLocations(new TargetingGeoLocation().setFieldCountries(List.of("US"))));
      adSetModel.setStatus(AdAccount.EnumStatus.VALUE_PAUSED);

      APINode adSet = adAccountHelper.createAdSet(adSetModel);
      adSetId = adSet.getId();
      Assertions.assertNotNull(adSet);
      Assertions.assertNotNull(adSet.getId());

   }

   /**
    * Test create ad.
    */
   @Test
   @Order(4)
   public void testCreateAd() {

      // create ad
      AdModel adModel = new AdModel();
      adModel.setStatus(Ad.EnumStatus.VALUE_PAUSED);
      adModel.setName("Ad name " + Math.random());
      adModel.setAdSetId(adSetId);
      adModel.setAdCreative(adCreative);
      adModel.setBidAmount(100L);

      Ad ad = adAccountHelper.createAd(adModel);

      Assertions.assertNotNull(ad);
      Assertions.assertNotNull(ad.getId());
   }

   /**
    * Test create ad creative.
    */
   @Test
   @Order(3)
   public void testCreateCreativeAd() throws URISyntaxException {

      // create an image.

      AdImage image = adAccountHelper.createImage(new File(Objects.requireNonNull(getClass().getClassLoader().getResource("test-image.png")).toURI()));

      AdCreativeLinkData link = new AdCreativeLinkData().setFieldMessage("AdCreativeLinkData MSG").setFieldLink("www.facebook.com")
            .setFieldImageHash(image.getFieldHash()).setFieldCaption("www.facebook.com");

      AdCreativeObjectStorySpec spec = new AdCreativeObjectStorySpec().setFieldPageId(PAGE_ID).setFieldLinkData(link);

      // create ad creative
      AdCreativeModel adCreativeModel = new AdCreativeModel();
      adCreativeModel.setName("Creative add " + Math.random());
      adCreativeModel.setAdCreativeObjectStorySpec(spec);
      adCreative = adAccountHelper.createAdCreative(adCreativeModel);

      Assertions.assertNotNull(adCreative);
      Assertions.assertNotNull(adCreative.getId());

   }

   /**
    * Test all in one.
    */
   @Test
   @Disabled
   public void testAllInOne() throws APIException, URISyntaxException {

      // create campaign
      CampaignModel campaignModel = new CampaignModel();
      campaignModel.setName("my campaign " + Math.random());
      campaignModel.setBuyingType("AUCTION");
      campaignModel.setObjective(Campaign.EnumObjective.VALUE_LINK_CLICKS);
      campaignModel.setStatus(Campaign.EnumStatus.VALUE_PAUSED);

      Campaign campaign = adAccountHelper.createCampaign(campaignModel);

      // create ad set.

      AdSetModel adSetModel = new AdSetModel();
      adSetModel.setName("my ad set " + Math.random());
      adSetModel.setOptimizationGoal(AdAccount.EnumOptimizationGoal.VALUE_REACH);
      adSetModel.setBillingEvent(AdAccount.EnumBillingEvent.VALUE_IMPRESSIONS);
      adSetModel.setBidAmount(4L);
      adSetModel.setDailyBudget(500L);
      adSetModel.setCampaignId(campaign.getId());
      adSetModel.setTargeting(new Targeting().setFieldGeoLocations(new TargetingGeoLocation().setFieldCountries(List.of("US"))));
      adSetModel.setStatus(AdAccount.EnumStatus.VALUE_PAUSED);

      APINode adSet = adAccountHelper.createAdSet(adSetModel);

      // create an image.

      AdImage image = adAccountHelper.createImage(new File(Objects.requireNonNull(getClass().getClassLoader().getResource("test-image.png")).toURI()));

      AdCreativeLinkData link = new AdCreativeLinkData().setFieldMessage("AdCreativeLinkData MSG").setFieldLink("www.facebook.com")
            .setFieldImageHash(image.getFieldHash()).setFieldCaption("www.facebook.com");

      AdCreativeObjectStorySpec spec = new AdCreativeObjectStorySpec().setFieldPageId(PAGE_ID).setFieldLinkData(link);

      // create ad creative
      AdCreativeModel adCreativeModel = new AdCreativeModel();
      adCreativeModel.setName("Creative add " + Math.random());
      adCreativeModel.setAdCreativeObjectStorySpec(spec);
      AdCreative creative = adAccountHelper.createAdCreative(adCreativeModel);

      // create ad
      AdModel adModel = new AdModel();
      adModel.setStatus(Ad.EnumStatus.VALUE_PAUSED);
      adModel.setName("Ad name " + Math.random());
      adModel.setAdSetId(adSet.getId());
      adModel.setAdCreative(creative);
      adModel.setBidAmount(100L);

      Ad ad = adAccountHelper.createAd(adModel);

      Assertions.assertNotNull(campaign.getId());
      Assertions.assertNotNull(adSet.getId());
      Assertions.assertNotNull(ad.getId());
      Assertions.assertNotNull(creative.getId());

      System.out.println(campaign.fetch());
      System.out.println(ad.fetch());

      Assertions.assertNotNull(campaign.fetch());
      Assertions.assertNotNull(ad.fetch());

   }

}
