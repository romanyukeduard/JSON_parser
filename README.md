# JSON Parser
In this parser I used Volley libarary(https://github.com/mcxiaoke/android-volley)

dependency for Gradle:
``` groovy
compile 'com.android.support:appcompat-v7:23.1.1'
compile 'com.mcxiaoke.volley:library:1.0.19'
```

# Attention!
If you want to use AppController you must update AndroidManifest
``` xml
<uses-permission android:name="android.permission.INTERNET"/>

<application    android:name="io.github.tripguider.jsonp.app.AppController"></application>
```
