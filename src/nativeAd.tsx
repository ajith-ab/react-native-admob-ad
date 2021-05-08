import  React, { useEffect, useState} from "react";
import { ViewStyle, DeviceEventEmitter, Platform } from 'react-native';
import AdmobAdViewManager from './AdmobAdViewManager';
import type { IButtonStyle, IHeadlineStyle, IImageView } from "./interfaces/nativeAd.interface";

interface NativeAdProps{
    style: ViewStyle;
    headlineStyle?: IHeadlineStyle,
    unitId: string,
    iconStyle?: IImageView,
    buttonStyle?: IButtonStyle
    mediaStyle?: IImageView,
    advertiserStyle?: IHeadlineStyle,
    descriptionStyle?: IHeadlineStyle,
    onAdLoaded?: () => void,
    onAdFailedToLoad?: () => void,
    onAdClosed?: () => void,
}



const NativeAd: React.FC <NativeAdProps> = (props) => {
    const { style } = props;
    const isIos: boolean = Platform.OS === "ios";
    const [adLoaded, setAdLoaded] = useState<boolean>(false);
    const [listnerName, setListnerName] = useState<string>(new Date().getTime().toString() + '-' + Math.random());

    useEffect(()=>{
        DeviceEventEmitter.addListener(`${listnerName}-onAdLoaded`, ()=>{
          if(!adLoaded){
            
            setAdLoaded(true);
            if(props.onAdLoaded instanceof  Function)props.onAdLoaded()
          }
        });
        DeviceEventEmitter.addListener(`${listnerName}-onAdFailedToLoad`, ()=>{
          if(props.onAdFailedToLoad instanceof  Function)props.onAdFailedToLoad()
        }); 
        DeviceEventEmitter.addListener(`${listnerName}-onAdClosed`, ()=>{
          if(props.onAdClosed instanceof  Function)props.onAdClosed()
        }); 

        return () => {
            DeviceEventEmitter.removeListener(`${listnerName}-AdLoaded`, ()=>{
                setListnerName("");
            });
            DeviceEventEmitter.removeListener(`${listnerName}-onAdFailedToLoad`, ()=>{
            }); 
            DeviceEventEmitter.removeListener(`${listnerName}-onAdClosed`, ()=>{
            }); 
        }
    },[])


    if(isIos)return <></>;
    return(
            <AdmobAdViewManager 
                configs={props}
                listnerName={listnerName}
                style={{...style, height: adLoaded ? style.height ? style.height : 250 : 250  }} 
            />
    )
};

export default NativeAd;