package com.facebook.support.model;

import lombok.Getter;
import lombok.Setter;

import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Targeting;

/**
 * AdSet model class holds details on ad set.
 */
@Getter
@Setter
public class AdSetModel {
   /**
    * Holds ad set name.
    */
   private String name;
   /**
    * Holds ad set optimization goal.
    */
   private AdAccount.EnumOptimizationGoal optimizationGoal;
   /**
    * Holds ad set billing event.
    */
   private AdAccount.EnumBillingEvent billingEvent;
   /**
    * Holds ad set bid amount.
    */
   private Long bidAmount;
   /**
    * Holds ad set daily budget.
    */
   private Long dailyBudget;
   /**
    * Holds ad set campaign id.
    */
   private String campaignId;
   /**
    * Holds ad set targeting.
    */
   private Targeting targeting;

   /**
    * Holds ad set status.
    */
   private AdAccount.EnumStatus status;

}
