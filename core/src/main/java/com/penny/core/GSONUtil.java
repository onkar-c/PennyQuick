package com.penny.core;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class GSONUtil {

  public static Gson acceptGSONWithoutExposeAnnotationFields() {
    return new GsonBuilder()
        .addSerializationExclusionStrategy(serializationStrategy())
        .addDeserializationExclusionStrategy(deserializationStrategy())
        .create();
  }

  private static ExclusionStrategy serializationStrategy() {
    return new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        final Expose expose = fieldAttributes.getAnnotation(Expose.class);
        return expose != null && !expose.serialize();
      }

      @Override
      public boolean shouldSkipClass(Class<?> aClass) {
        return false;
      }
    };
  }

  private static ExclusionStrategy deserializationStrategy() {
    return new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        final Expose expose = fieldAttributes.getAnnotation(Expose.class);
        return expose != null && !expose.deserialize();
      }

      @Override
      public boolean shouldSkipClass(Class<?> aClass) {
        return false;
      }
    };
  }
}
