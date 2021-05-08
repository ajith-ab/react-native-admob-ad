package com.reactnativeadmobad;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import org.jetbrains.annotations.NotNull;

public class AdmobNativeAdView extends LinearLayout {
  private ThemedReactContext context;
  public String adMobUnitId;
  private RCTEventEmitter mEventEmitter;
  public String adListnerName;
  private ReadableMap config;


  public AdmobNativeAdView(ThemedReactContext context) {
    super(context);//ADD THIS
    this.context = context;
    inflate(context, R.layout.native_card_ad, this);
    mEventEmitter = context.getJSModule(RCTEventEmitter.class);
  }


  public void addListnerName(String lintnerName){
    adListnerName = lintnerName;
  }

  public void init(String admobId, ReadableMap object) {
    adMobUnitId = admobId;
    config = object;
    MobileAds.initialize(context, new OnInitializationCompleteListener() {
      @Override
      public void onInitializationComplete(InitializationStatus initializationStatus) {
        adBuild();
      }
    });
  }


  @SuppressLint("MissingPermission")
  public void adBuild(){
    AdLoader.Builder builder = new AdLoader.Builder(context, adMobUnitId);
    AdLoader adLoader =  builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener(){
      @Override
      public void onNativeAdLoaded(@NonNull @NotNull NativeAd nativeAd) {
          CardView cardView = findViewById(R.id.ad_container);
          NativeAdView nativeAdView = (NativeAdView)  context.getCurrentActivity().getLayoutInflater().inflate(R.layout.admob_native_layout, null);
          cardView.removeAllViews();
          populateNativeAd(nativeAd, nativeAdView, cardView);
          cardView.addView(nativeAdView);
      }
    }).withAdListener(new AdListener() {
      @Override
      public void onAdClosed() {
        super.onAdClosed();
        context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit(adListnerName.concat("-onAdClosed"), adMobUnitId);
      }

      @Override
      public void onAdFailedToLoad(@NonNull @NotNull LoadAdError loadAdError) {
        super.onAdFailedToLoad(loadAdError);
        context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit(adListnerName.concat("-onAdFailedToLoad"), loadAdError.toString());
      }
    }).build();
    adLoader.loadAd(new AdRequest.Builder().build());
  }

  public void populateNativeAd (NativeAd nativeAd, NativeAdView nativeAdView, CardView cardView){
      nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
      nativeAdView.setAdvertiserView(nativeAdView.findViewById(R.id.ad_advertiser));
      nativeAdView.setMediaView(nativeAdView.findViewById(R.id.media_viewss));
      nativeAdView.setIconView(nativeAdView.findViewById(R.id.adv_icon));
      nativeAdView.setStarRatingView(nativeAdView.findViewById(R.id.ad_rating));
      nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body_text));
      nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.call_to_action));

      nativeAdView.getMediaView().setImageScaleType(ImageView.ScaleType.FIT_XY);
      ((TextView) nativeAdView.getHeadlineView()).setText(nativeAd.getHeadline());
      ((TextView) nativeAdView.getAdvertiserView()).setText(nativeAd.getAdvertiser());

      if(nativeAd.getBody() == null)nativeAdView.getBodyView().setVisibility(View.INVISIBLE);
      else{
        ((TextView) nativeAdView.getBodyView()).setText(nativeAd.getBody());
        nativeAdView.getBodyView().setVisibility(View.VISIBLE);
      }

      if(nativeAd.getStarRating() == null)nativeAdView.getStarRatingView().setVisibility(View.INVISIBLE);
      else {
        ((RatingBar)nativeAdView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
        nativeAdView.getStarRatingView().setVisibility(View.VISIBLE);
      }
      if(nativeAd.getCallToAction() == null)nativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
      else{
        ((Button) nativeAdView.getCallToActionView()).setText(nativeAd.getCallToAction());
      }

      if(nativeAd.getIcon() == null)nativeAdView.getIconView().setVisibility(View.INVISIBLE);else{
        ((ImageView) nativeAdView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
        nativeAdView.getIconView().setVisibility(View.VISIBLE);
      }
      new ApplyStyle(config, nativeAdView, cardView, context, "large");
      nativeAdView.setVisibility(View.VISIBLE);
      nativeAdView.setNativeAd(nativeAd);
      context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(adListnerName.concat("-onAdLoaded"), adMobUnitId);
  }
}
