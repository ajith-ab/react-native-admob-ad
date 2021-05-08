package com.reactnativeadmobad.listFeed;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.reactnativeadmobad.ApplyStyle;
import com.reactnativeadmobad.R;

import org.jetbrains.annotations.NotNull;

public class AdmobFeedView extends LinearLayout {
  private ThemedReactContext context;
  public String adMobUnitId;
  private RCTEventEmitter mEventEmitter;
  public String adListnerName;
  private ReadableMap config;

  public AdmobFeedView(ThemedReactContext context) {
    super(context);
    this.context = context;
    inflate(context, R.layout.admob_list_feed_card, this);
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
        CardView cardView = findViewById(R.id.ad_List_container);
        NativeAdView nativeAdView = (NativeAdView)  context.getCurrentActivity().getLayoutInflater().inflate(R.layout.admob_list_feed, null);
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
    nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_feed_headline));
    nativeAdView.setAdvertiserView(nativeAdView.findViewById(R.id.ad_feed_advertiser));
    nativeAdView.setIconView(nativeAdView.findViewById(R.id.adv_feed_icon));
    nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.call_to_feed_action));
    nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_feed_body));

    ((TextView) nativeAdView.getHeadlineView()).setText(nativeAd.getHeadline());
    ((TextView) nativeAdView.getAdvertiserView()).setText(nativeAd.getAdvertiser());

    if(nativeAd.getCallToAction() == null)nativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
    else{
      ((Button) nativeAdView.getCallToActionView()).setText(nativeAd.getCallToAction());
    }

    if(nativeAd.getBody() == null)nativeAdView.getBodyView().setVisibility(View.INVISIBLE);
    else{
      ((TextView) nativeAdView.getBodyView()).setText(nativeAd.getBody());
      nativeAdView.getBodyView().setVisibility(View.VISIBLE);
    }

    if(nativeAd.getIcon() == null)nativeAdView.getIconView().setVisibility(View.INVISIBLE);else{
      ((ImageView) nativeAdView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
      nativeAdView.getIconView().setVisibility(View.VISIBLE);
    }
    new ApplyStyle(config, nativeAdView, cardView, context, "small");
    nativeAdView.setVisibility(View.VISIBLE);
    nativeAdView.setNativeAd(nativeAd);
    context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(adListnerName.concat("-onAdLoaded"), adMobUnitId);
  }


}
