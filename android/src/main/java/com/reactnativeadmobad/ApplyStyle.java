package com.reactnativeadmobad;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.gms.ads.nativead.NativeAdView;

public class ApplyStyle {
  private ReadableMap config;
  private NativeAdView adView;
  private ThemedReactContext context;
  private String from;

  public ApplyStyle(ReadableMap object, NativeAdView nativeAdView, CardView cardView, ThemedReactContext themedReactContext, String from){
    config = object;
    adView = nativeAdView;
    context = themedReactContext;
    this.from = from;
    applyStyle(cardView);
  }

  public void applyStyle(CardView cardView){
    if(config.hasKey("style")){
      ReadableMap style = config.getMap("style");
      if(style.hasKey("elevation"))cardView.setElevation(style.getInt("elevation"));
      if(style.hasKey("backgroundColor"))adView.findViewById(from.equals("small") ? R.id.ad_feed_main_container : R.id.ad_main_container ).setBackgroundColor(Color.parseColor(style.getString("backgroundColor")));
    }
    if(config.hasKey("headlineStyle")){
      ReadableMap style = config.getMap("headlineStyle");
      if(style.hasKey("fontSize"))((TextView) adView.getHeadlineView()).setTextSize(style.getInt("fontSize"));
      if(style.hasKey("color"))((TextView) adView.getHeadlineView()).setTextColor(style.getInt("fontSize"));
      if(style.hasKey("backgroundColor"))((TextView) adView.getHeadlineView()).setBackgroundColor(Color.parseColor(style.getString("backgroundColor")));
      if(style.hasKey("display") && style.getString("display").equals("none")){
        adView.getHeadlineView().setVisibility(View.INVISIBLE);
      }
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
      );
      int left = 0, right = 0, bottom = 0, top = 0;
      if(style.hasKey("marginLeft"))left = style.getInt("marginLeft");
      if(style.hasKey("marginRight"))right = style.getInt("marginRight");
      if(style.hasKey("marginBottom"))bottom = style.getInt("marginBottom");
      if(style.hasKey("marginTop"))top = style.getInt("marginTop");
      params.setMargins(left, top, right, bottom);
      adView.getHeadlineView().setLayoutParams(params);

    }
    if(config.hasKey("advertiserStyle")){
      ReadableMap style = config.getMap("advertiserStyle");
      if(style.hasKey("fontSize"))((TextView) adView.getAdvertiserView()).setTextSize(style.getInt("fontSize"));
      if(style.hasKey("color"))((TextView) adView.getAdvertiserView()).setTextColor(style.getInt("fontSize"));
      if(style.hasKey("backgroundColor"))((TextView) adView.getAdvertiserView()).setBackgroundColor(Color.parseColor(style.getString("backgroundColor")));
      if(style.hasKey("display") && style.getString("display").equals("none")){
        adView.getAdvertiserView().setVisibility(View.INVISIBLE);
      }
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
      );
      int left = 0, right = 0, bottom = 0, top = 0;
      if(style.hasKey("marginLeft"))left = style.getInt("marginLeft");
      if(style.hasKey("marginRight"))right = style.getInt("marginRight");
      if(style.hasKey("marginBottom"))bottom = style.getInt("marginBottom");
      if(style.hasKey("marginTop"))top = style.getInt("marginTop");
      params.setMargins(left, top, right, bottom);
      adView.getAdvertiserView().setLayoutParams(params);
    }
    if(config.hasKey("descriptionStyle")){
      ReadableMap style = config.getMap("descriptionStyle");
      if(style.hasKey("fontSize"))((TextView) adView.getBodyView()).setTextSize(style.getInt("fontSize"));
      if(style.hasKey("color"))((TextView) adView.getBodyView()).setTextColor(style.getInt("fontSize"));
      if(style.hasKey("backgroundColor"))((TextView) adView.getBodyView()).setBackgroundColor(Color.parseColor(style.getString("backgroundColor")));
      if(style.hasKey("display") && style.getString("display").equals("none")){
        adView.getBodyView().setVisibility(View.INVISIBLE);
      }
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
      );
      int left = 0, right = 0, bottom = 0, top = 5;
      if(style.hasKey("marginLeft"))left = style.getInt("marginLeft");
      if(style.hasKey("marginRight"))right = style.getInt("marginRight");
      if(style.hasKey("marginBottom"))bottom = style.getInt("marginBottom");
      if(style.hasKey("marginTop"))top = style.getInt("marginTop");
      params.setMargins(left, top, right, bottom);
      adView.getBodyView().setLayoutParams(params);
    }

    if(config.hasKey("iconStyle")){
      ReadableMap style = config.getMap("iconStyle");
      if(style.hasKey("height"))adView.getIconView().getLayoutParams().height = style.getInt("height");
      if(style.hasKey("width"))adView.getIconView().getLayoutParams().width = style.getInt("width");
      if(style.hasKey("backgroundColor"))adView.getIconView().setBackgroundColor(Color.parseColor(style.getString("backgroundColor")));
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
      );
      int left = 0, right = 0, bottom = 0, top = 5;
      if(style.hasKey("marginLeft"))left = style.getInt("marginLeft");
      if(style.hasKey("marginRight"))right = style.getInt("marginRight");
      if(style.hasKey("marginBottom"))bottom = style.getInt("marginBottom");
      if(style.hasKey("marginTop"))top = style.getInt("marginTop");
      params.setMargins(left, top, right, bottom);
      adView.getIconView().setLayoutParams(params);
      if(style.hasKey("display") && style.getString("display").equals("none")){
        adView.getIconView().setVisibility(View.INVISIBLE);
      }

    }

    if(config.hasKey("mediaStyle")){
      ReadableMap style = config.getMap("mediaStyle");
      if(style.hasKey("height"))adView.getMediaView().getLayoutParams().height = style.getInt("height");
      if(style.hasKey("width"))adView.getMediaView().getLayoutParams().width = style.getInt("width");
      if(style.hasKey("backgroundColor"))adView.getMediaView().setBackgroundColor(Color.parseColor(style.getString("backgroundColor")));

      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
      );
      int left = 0, right = 0, bottom = 0, top = 5;
      if(style.hasKey("marginLeft"))left = style.getInt("marginLeft");
      if(style.hasKey("marginRight"))right = style.getInt("marginRight");
      if(style.hasKey("marginBottom"))bottom = style.getInt("marginBottom");
      if(style.hasKey("marginTop"))top = style.getInt("marginTop");
      params.setMargins(left, top, right, bottom);
      adView.getMediaView().setLayoutParams(params);
      if(style.hasKey("display") && style.getString("display").equals("none")){
        adView.getMediaView().setVisibility(View.INVISIBLE);
      }
    }

    if(config.hasKey("buttonStyle")){
      ReadableMap style = config.getMap("buttonStyle");
      if(style.hasKey("backgroundColor"))adView.getCallToActionView().setBackgroundColor(Color.parseColor(style.getString("backgroundColor")));
      if(style.hasKey("color"))((Button) adView.getCallToActionView()).setTextColor(Color.parseColor(style.getString("color")));
      if(style.hasKey("fontSize"))((Button) adView.getCallToActionView()).setTextSize(style.getInt("fontSize"));
      if(style.hasKey("textAlign")){
          if(style.getString("textAlign").equals("right")){
            ((Button) adView.getCallToActionView()).setPadding(0, 0, 30, 0);
            ((Button) adView.getCallToActionView()).setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
          }
          else if(style.getString("textAlign").equals("left")){
            ((Button) adView.getCallToActionView()).setPadding(30, 0, 0, 0);
            ((Button) adView.getCallToActionView()).setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            Drawable img = context.getResources().getDrawable(R.drawable.ic_baseline_arrow_forward_ios_24);
            img.setBounds(0, 0, 60, 60);
            ((Button) adView.getCallToActionView()).setCompoundDrawables(null, null, img, null);
          }
          else ((Button) adView.getCallToActionView()).setGravity(Gravity.CENTER);
      }
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
      );
      int left = 0, right = 0, bottom = 0, top = 5;
      if(style.hasKey("marginLeft"))left = style.getInt("marginLeft");
      if(style.hasKey("marginRight"))right = style.getInt("marginRight");
      if(style.hasKey("marginBottom"))bottom = style.getInt("marginBottom");
      if(style.hasKey("marginTop"))top = style.getInt("marginTop");
      params.setMargins(left, top, right, bottom);
      adView.getCallToActionView().setLayoutParams(params);
      if(style.hasKey("height"))adView.getCallToActionView().getLayoutParams().height = style.getInt("height");
      if(style.hasKey("width"))adView.getCallToActionView().getLayoutParams().width = style.getInt("width");
      if(style.hasKey("display") && style.getString("display").equals("none")){
        adView.getCallToActionView().setVisibility(View.INVISIBLE);
      }
    }
  }
}
