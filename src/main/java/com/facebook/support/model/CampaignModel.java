package com.facebook.support.model;

import lombok.Getter;
import lombok.Setter;

import com.facebook.ads.sdk.Campaign;

/**
 * Campaign model class holds details on campaign.
 */
@Getter
@Setter
public class CampaignModel {
   /**
    * Holds campaign name.
    */
   private String name;
   /**
    * Holds campaign buying type.
    */
   private String buyingType;
   /**
    * Holds campaign objective.
    */
   private Campaign.EnumObjective objective;
   /**
    * Holds campaign status.
    */
   private Campaign.EnumStatus status;

}
