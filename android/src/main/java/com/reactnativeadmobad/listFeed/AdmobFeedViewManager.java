package com.reactnativeadmobad.listFeed;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.reactnativeadmobad.AdmobNativeAdView;

public class AdmobFeedViewManager extends SimpleViewManager<AdmobFeedView> {

  @NonNull
  @Override
  public String getName() {
    return "AdmobFeedViewManager";
  }

  @NonNull
  @Override
  protected AdmobFeedView createViewInstance(@NonNull ThemedReactContext reactContext) {
    return new AdmobFeedView(reactContext);
  }

  @ReactProp(name = "listnerName")
  public void setListner(AdmobFeedView admobFeedView, String listner) {
    admobFeedView.addListnerName(listner);
  }

  @ReactProp(name = "configs")
  public void setConfig(AdmobFeedView admobFeedView, ReadableMap object) {
    if(object.hasKey("unitId")){
      admobFeedView.init(object.getString("unitId"), object);
    }
  }


}
