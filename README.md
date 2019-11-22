# iNaviMaps SDK for Android Demo
Android 플랫폼에서 아이나비 지도를 사용하기 위한 프로젝트 기본 설정 방법을 설명합니다.


### 사전 준비
- 아이나비 지도를 사용하기 위해서는 인증을 위한 **Appkey**가 필요합니다.

#### 서비스 활성화
- **[NHN Toast Console]** 에서 서비스 선택 후 Application Service > Maps를 클릭합니다

#### Appkey 확인
- **Appkey**는 **TOAST Console** 상단 **URL & Appkey** 메뉴에서 확인할 수 있습니다.


### Project 환경 구성
다음과 같이 Project 및 App 모듈 레벨의 build.gradle 파일에 아이나비 지도 저장소를 추가하고, 의존성을 설정합니다.

> `아이나비 지도 Android SDK는 Bintray 를 통해 배포되며, Beta 기간 종료 후에는 정책에 맞춰 변경될 수 있습니다. (사전 공지 예정)`

```gradle
/* Root Project build.gradle */

allprojects {
    repositories {
        google()
        ...
        // 아이나비 지도 저장소
        maven {
            url 'https://dl.bintray.com/inavi-systems/maps/'
        }
    }
}
```

```gradle
/* App Module build.gradle */

dependencies {
    implementation 'com.inavi.mapsdk:inavi-maps-sdk:0.3.0'
}
```


### Appkey 설정
발급받은 Appkey를 설정할 수 있도록 아래의 두 가지 방법을 제공합니다. 

> `Appkey가 설정되지 않으면 지도 초기화 단계에서 인증 오류가 발생합니다.`

#### 1. AndroidManifest.xml에서 설정
`AndroidManifest.xml`에 `<meta-data>`를 추가하여 Appkey를 설정할 수 있습니다.
```xml
<!-- AndroidManifext.xml -->

<manifest>
    <application>
        <meta-data
            android:name="com.inavi.mapsdk.AppKey"
            android:value="YOUR_APP_KEY" />
    </application>
</manifest>
```

#### 2. InaviMapSdk API 호출로 설정
Application 생성 시점에 동적으로 [InaviMapSdk] 싱글턴 객체의 함수를 호출하여 Appkey를 설정할 수 있습니다.

```kotlin
// Kotlin
InaviMapSdk.getInstance(context).appKey = "YOUR_APP_KEY"
```


## 주요 iNavi Maps SDK 안내
추가적인 iNavi Maps SDK 사용법은 [iNavi Maps API 센터](http://imapsapi.inavi.com/)를 참고하시기 바랍니다.


[InaviMapSdk]: <http://imapsapi.inavi.com/Android/com/inavi/mapsdk/maps/InaviMapSdk.html>
[NHN Toast Console]: <https://console.toast.com/>


## License
Copyright © 2019 iNavi Systems

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.