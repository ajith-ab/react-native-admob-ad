import * as React from 'react';

import {  StyleSheet, View} from 'react-native';
import { NativeAd, NativeAdSmall } from 'react-native-admob-ad';

export default function App() {
  return (
      <View style={{ backgroundColor: "green" }}>
         <NativeAd
               unitId="ca-app-pub-3829977736711812/5497814975"
               style={styles.box}
            />
            <NativeAdSmall
               unitId="ca-app-pub-3829977736711812/5497814975"
               style={styles.box}
               buttonStyle={{
                 height: 90,
                 backgroundColor: "#cccccc"
               }}
            />
      </View>

    
  );
}

const styles = StyleSheet.create({
  container: {
    // flex: 1,
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  box: {
    width: '100%',
    height: 400,
    elevation: 0,
  
    
    // marginVertical: 20,
  },
  headerStyle: {
      fontSize: 12,
      color: "#696969",
      fontWeight: "100",
      
  }
});
