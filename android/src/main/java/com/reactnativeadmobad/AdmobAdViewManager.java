package com.reactnativeadmobad;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;


public class AdmobAdViewManager extends SimpleViewManager<AdmobNativeAdView> {
  @NonNull
  @Override
  public String getName() {
    return "AdmobAdView";
  }

  @NonNull
  @Override
  protected AdmobNativeAdView createViewInstance(@NonNull ThemedReactContext reactContext) {
    return new AdmobNativeAdView(reactContext);
  }

  @ReactProp(name = "listnerName")
  public void setListner(AdmobNativeAdView admobNativeAdView,  String listner) {
    admobNativeAdView.addListnerName(listner);
  }

  @ReactProp(name = "configs")
  public void setConfig(AdmobNativeAdView admobNativeAdView, ReadableMap object) {
    if(object.hasKey("unitId")){
      admobNativeAdView.init(object.getString("unitId"), object);
    }
  }
}
