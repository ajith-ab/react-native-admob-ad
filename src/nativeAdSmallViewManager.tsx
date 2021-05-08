import { requireNativeComponent, ViewStyle } from 'react-native';

type AdmobAdProps = {
    style: ViewStyle;
    listnerName: string
    configs?: any
};

const AdmobAdViewManager = requireNativeComponent<AdmobAdProps>(
    'AdmobFeedViewManager'
  );

  export default AdmobAdViewManager;