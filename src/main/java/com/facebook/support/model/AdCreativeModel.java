package com.facebook.support.model;

import lombok.Getter;
import lombok.Setter;

import com.facebook.ads.sdk.AdCreativeObjectStorySpec;

/**
 * AdSet model class holds details on ad.
 */
@Getter
@Setter
public class AdCreativeModel {
   /**
    * Holds ad creative name.
    */
   private String name;
   /**
    * Holds ad creative object story id.
    */
   private String objectStoryId;
   /**
    * Holds ad creative object story spec instance.
    */
   private AdCreativeObjectStorySpec adCreativeObjectStorySpec;
}
