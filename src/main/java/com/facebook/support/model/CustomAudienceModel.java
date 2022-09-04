package com.facebook.support.model;

import lombok.Getter;
import lombok.Setter;

import com.facebook.ads.sdk.CustomAudience;

/**
 * Custom audience model class holds details on audience.
 */
@Getter
@Setter
public class CustomAudienceModel {
   /**
    * Holds audience name.
    */
   private String name;
   /**
    * Holds audience type.
    */
   private CustomAudience.EnumSubtype subtype;
   /**
    * Holds audience description.
    */
   private String description;
   /**
    * Holds audience file source.
    */
   private CustomAudience.EnumCustomerFileSource customerFileSource;
   /**
    * Holds lookalike audience spec.
    */
   private String lookalikeSpec;
   /**
    * Holds lookalike audience spec.
    */
   private String originalAudienceId;

}
