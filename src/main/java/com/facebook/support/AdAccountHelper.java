/**
 * This package does the main ad account features.
 */

package com.facebook.support;

import java.io.File;
import java.util.List;
import java.util.Objects;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.APINode;
import com.facebook.ads.sdk.Ad;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.AdCreative;
import com.facebook.ads.sdk.AdImage;
import com.facebook.ads.sdk.Campaign;
import com.facebook.ads.sdk.CustomAudience;
import com.facebook.support.model.AdCreativeModel;
import com.facebook.support.model.AdModel;
import com.facebook.support.model.AdSetModel;
import com.facebook.support.model.CampaignModel;
import com.facebook.support.model.CustomAudienceModel;
import com.facebook.support.util.PropertyFileReader;

/**
 * Helper class to perform all ad account functionalities.
 */
public class AdAccountHelper {

   /**
    * Instance holder for AdAccount.
    */
   private static AdAccount account = null;

   /**
    * Get the authenticated ad account instance.
    *
    * @return ad account instance.
    *
    */
   private static AdAccount getAdAccount() {
      if (Objects.isNull(account)) {
         APIContext lApiContext = new APIContext(PropertyFileReader.getProperty("ACCESS_TOKEN"));
         account = new AdAccount(PropertyFileReader.getProperty("AD_ACCOUNT_ID"), lApiContext);
      }
      return account;
   }

   /**
    * Creates the campaign with given details.
    *
    * @param pCampaignModel
    *           Details which will use to create the campaign.
    * @return created campaign instance.
    * @throws FacebookAdSupportUtilityException
    *            when failed
    */
   public Campaign createCampaign(final CampaignModel pCampaignModel) {

      try {
         return getAdAccount().createCampaign().setName(pCampaignModel.getName()).setBuyingType(pCampaignModel.getBuyingType())
               .setObjective(pCampaignModel.getObjective()).setStatus(pCampaignModel.getStatus()).setParam("special_ad_categories", "[]").execute();
      } catch (APIException pApiException) {
         throw new FacebookAdSupportUtilityException("Exception occurred while creating campaign.", pApiException);
      }

   }

   /**
    * Create ad set based on given details.
    *
    * @param pAdSetModel
    *           adSet model.
    * @return Ad set instance.
    * @throws FacebookAdSupportUtilityException
    *            when failed.
    */
   public APINode createAdSet(final AdSetModel pAdSetModel) {
      try {
         return getAdAccount().createAdSet().setName(pAdSetModel.getName()).setOptimizationGoal(pAdSetModel.getOptimizationGoal())
               .setBillingEvent(pAdSetModel.getBillingEvent()).setBidAmount(pAdSetModel.getBidAmount()).setDailyBudget(pAdSetModel.getDailyBudget())
               .setCampaignId(pAdSetModel.getCampaignId()).setTargeting(pAdSetModel.getTargeting()).setStatus(pAdSetModel.getStatus()).execute();
      } catch (APIException pApiException) {
         throw new FacebookAdSupportUtilityException("Exception occurred while creating ad set.", pApiException);
      }

   }

   /**
    * Create ad set based on given details.
    *
    * @param pAdModel
    *           ad model.
    * @return Ad set instance.
    * @throws FacebookAdSupportUtilityException
    *            when failed.
    */
   public Ad createAd(final AdModel pAdModel) {
      try {
         return getAdAccount().createAd().setName(pAdModel.getName()).setAdsetId(pAdModel.getAdSetId()).setCreative(pAdModel.getAdCreative())
               .setStatus(pAdModel.getStatus()).setBidAmount(pAdModel.getBidAmount()).execute();
      } catch (APIException pApiException) {
         throw new FacebookAdSupportUtilityException("Exception occurred while creating an ad.", pApiException);
      }

   }

   /**
    * Create creative ad based on given details.
    *
    * @param pAdCreativeModel
    *           creative ad model.
    * @return AdCreative instance.
    * @throws FacebookAdSupportUtilityException
    *            when failed.
    */
   public AdCreative createAdCreative(final AdCreativeModel pAdCreativeModel) {

      try {
         return getAdAccount().createAdCreative().setName(pAdCreativeModel.getName()).setObjectStorySpec(pAdCreativeModel.getAdCreativeObjectStorySpec())
               .execute();
      } catch (APIException pApiException) {
         throw new FacebookAdSupportUtilityException("Exception occurred while creating ad creative.", pApiException);
      }
   }

   /**
    * Fetch audiences for authenticated account.
    *
    * @return list of audience instances.
    * @throws FacebookAdSupportUtilityException
    *            when failed.
    */
   public List<CustomAudience> fetchAudiences() {
      try {
         return getAdAccount().getCustomAudiences().execute();
      } catch (APIException pApiException) {
         throw new FacebookAdSupportUtilityException("Exception occurred while retrieving audiences.", pApiException);
      }
   }

   /**
    * Create an audience from the given details.
    *
    * @param pCustomAudienceModel
    *           model instance to be used on audience.
    * @return CustomAudience instance.
    * @throws FacebookAdSupportUtilityException
    *            when failed.
    */
   public CustomAudience createAudience(final CustomAudienceModel pCustomAudienceModel) {
      try {
         return getAdAccount().createCustomAudience().setName(pCustomAudienceModel.getName()).setSubtype(pCustomAudienceModel.getSubtype())
               .setDescription(pCustomAudienceModel.getDescription()).setCustomerFileSource(pCustomAudienceModel.getCustomerFileSource()).execute();
      } catch (APIException pApiException) {
         throw new FacebookAdSupportUtilityException("Exception occurred while creating custom audience", pApiException);
      }
   }

   /**
    * Create a lookalike audience from the given details.
    *
    * @param pCustomAudienceModel
    *           model instance to be used on audience.
    * @return CustomAudience instance.
    * @throws FacebookAdSupportUtilityException
    *            when failed.
    */
   public CustomAudience createLookalikeAudience(final CustomAudienceModel pCustomAudienceModel) {

      try {
         return getAdAccount().createCustomAudience().setName(pCustomAudienceModel.getName()).setSubtype(pCustomAudienceModel.getSubtype())
               .setDescription(pCustomAudienceModel.getDescription()).setLookalikeSpec(pCustomAudienceModel.getLookalikeSpec())
               .setOriginAudienceId(pCustomAudienceModel.getOriginalAudienceId()).setCustomerFileSource(pCustomAudienceModel.getCustomerFileSource()).execute();
      } catch (APIException pApiException) {
         throw new FacebookAdSupportUtilityException("Exception occurred while creating lookalike custom audience", pApiException);
      }
   }

   /**
    * Update an audience from the given details.
    *
    * @param pCustomAudience
    *           model instance to be used on audience.
    * @return CustomAudience instance.
    * @throws FacebookAdSupportUtilityException
    *            when failed.
    */
   public CustomAudience updateCustomAudience(final CustomAudience pCustomAudience) {
      try {

         return pCustomAudience.update().execute();

      } catch (APIException pApiException) {
         throw new FacebookAdSupportUtilityException("Exception occurred while updating custom audience", pApiException);
      }
   }

   /**
    * Create ad image based on given image file.
    *
    * @param pFile
    *           the image file.
    * @return the AdImage instance.
    * @throws FacebookAdSupportUtilityException
    *            when failed.
    */
   public AdImage createImage(final File pFile) {
      try {
         return getAdAccount().createAdImage().addUploadFile("test-image", pFile).execute();
      } catch (APIException pApiException) {
         throw new FacebookAdSupportUtilityException("Exception occurred while creating ad Image.", pApiException);
      }

   }

}
