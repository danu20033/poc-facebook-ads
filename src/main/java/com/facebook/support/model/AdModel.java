package com.facebook.support.model;

import lombok.Getter;
import lombok.Setter;

import com.facebook.ads.sdk.Ad;
import com.facebook.ads.sdk.AdCreative;

/**
 * AdSet model class holds details on ad.
 */
@Getter
@Setter
public class AdModel {

   /**
    * Holds ad name.
    */
   private String name;
   /**
    * Holds ad set id.
    */
   private String adSetId;

   /**
    * Holds ad status.
    */
   private Ad.EnumStatus status;

   /**
    * Holds creative add instance.
    */
   private AdCreative adCreative;

   /**
    * Holds add bit amount.
    */
   private Long bidAmount;
}
