# Change Log

## 0.6.1 - 2021-02-22

### Improvements
- SDK 안정성 향상

## 0.6.0 - 2021-01-28

### Features
- 지도 심벌의 크기를 설정하는 API 추가
  - `InaviMap#getSymbolScale`, `InaviMap#setSymbolScale`, `InvMapOptions#getSymbolScale`, `InvMapOptions#symbolScale`,
- 지도의 기울기 각도 설정 시 건물 3D 효과 지원

## 0.5.3 - 2020-11-02

### Bug Fixes
- 백그라운드 진입 시 간헐적으로 비정상 종료되는 오류 수정

## 0.5.2 - 2020-09-22

### Features
- 지도 초기옵션 속성 추가
  - `InvMapOptions#minZoom`, `InvMapOptions#maxZoom`, `InvMapOptions#minTilt`, `InvMapOptions#maxTilt`, `InvMapOptions#zoomGesturesEnabled`, `InvMapOptions#scrollGesturesEnabled`, `InvMapOptions#rotateGesturesEnabled`, `InvMapOptions#tiltGesturesEnabled`, `InvMapOptions#logoClickEnabled`, `InvMapOptions#focalPointCenter`
- XML Attributes를 통한 지도 초기옵션 설정 지원

## 0.5.1 - 2020-08-24

### Features
- 마커와 지도상 심볼 겹침을 설정하는 API 추가
  - `InvMarker#isAllowOverlapSymbols`, `InvMarker#setAllowOverlapSymbols`

## 0.5.0 - 2020-07-28

### Features
- 경로 셰이프에 패턴 이미지 API 추가
  - `InvRoute#setPatternImage`, `InvRoute#getPatternImage`, `InvRoute#setPatternMargin`, `InvRoute#getPatternMargin`, `InvRoute#setPatternScale`, `InvRoute#getPatternScale`
  
## 0.4.8 - 2020-07-16

### Features
- 지도 렌더 뷰 TextureView 모드 지원
  - `InvMapOptions#textureViewMode`, `InvMapOptions#getTextureViewMode`

## 0.4.7 - 2020-07-01

### Bug Fixes
- 간헐적으로 마커가 표출되지 않는 오류 수정

## 0.4.6 - 2020-06-02

### Features
- 유효하지 않은 위치로 카메라 이동 요청 시 예외 발생하지 않도록 변경

### Bug Fixes
- 위치 아이콘의 위치 변경 시 간헐적으로 깜빡임 발생하는 오류 수정

### Improvements
- SDK 안정성 향상

## 0.4.5 - 2020-04-29

### Features
- 정보 창 표출 상태 변경 시 애니메이션 기본값 비활성화로 변경
- 정보 창 표출 상태 변경 시 애니메이션 설정 API 추가
  - `InvInfoWindow#setTransitionEnabled`, `InvInfoWindow#isTransitionEnabled`
- 줌 또는 회전 제스처 시 기준점을 지도 중심으로 설정하는 API 추가
  - `UiSettings#setFocalPointCenter`, `UiSettings#isFocalPointCenter`

## 0.4.4 - 2020-04-21

### Features
- 지도의 최소/최대 기울기 각도를 설정하는 API 추가
  - `InaviMap#setMinTilt`, `InaviMap#getMinTilt`, `InaviMap#setMaxTilt`, `InaviMap#getMaxTilt`

### Improvements
- SDK 안정성 향상

## 0.4.3 - 2020-03-30

### Improvements
- SDK 안정성 향상

## 0.4.2 - 2020-03-10

### Features
- 특정 영역 전체가 보이는 카메라 위치 정보를 반환하는 API 추가 
  - `InaviMap#getCameraFitBounds`

- 지도에 등록된 모든 셰이프를 지도에서 제거하는 API 추가
  - `InaviMap#clearShapes`

### Bug Fixes
- 지도 초기화 전에 일부 셰이프 객체 생성 시 지도에 비정상 표출되는 오류 수정

## 0.4.1 - 2020-02-26

### Features
- 지도 로고 이미지 변경

### Bug Fixes
- Proguard 난독화 적용 시 비정상 종료되는 오류 수정
- 낮은 버전의 OS에서 지도가 표출되지 않는 오류 수정

### Improvements
- 기울기 조절 제스처 사용성 개선

## 0.4.0 - 2020-02-17

### Features
- 마커 클러스터링 기능 추가
  - `Cluster`, `ClusterItem`, `ClusterManager`, `ClusterManager#OnRenderListener`, `ClusterManager#OnClickListener`,  `ClusterIconGenerator`, `DefaultClusterIconGenerator`
- `InvRoute#lineWidth` 속성 기본값 `10`으로 변경
- `InvRouteLink#lineColor` 속성 기본값 `Color.WHITE`로 변경
- `InvRouteLink#strokeColor` 속성 기본값 `Color.BLACK`으로 변경

### Bug fixes
- `InvRoute#strokeWidth` 속성이 정상적으로 적용되지 않는 오류 수정
- `InvRoute` 표출 시 간헐적으로 외곽선이 비정상적으로 표출되는 오류 수정
- 아이콘 이미지 크기 변경 시 아이콘의 위치가 비정상적으로 표출되는 오류 수정

## 0.3.2 - 2020-01-16

### Features
 - 마커 표출 상태 변경 시 애니메이션 기본값 비활성화로 변경
 - 마커 표출 상태 변경 시 애니메이션 설정 API 추가
   - `InvMarker#setTransitionEnabled`, `InvMarker#isTransitionEnabled`

### Bug fixes
 - 마커 타이틀에 "^" 문자 포함 시 줄바꿈되는 오류 수정

## 0.3.1 - 2019-12-18

### Features
 - 지도 이동 영역 제한 API 추가
   - `InaviMap#setConstraintBounds`, `InaviMap#getConstraintBounds`
 - 로고 클릭 이벤트 활성화 여부 설정 API 추가
   - `UiSettings#setLogoClickEnabled`, `UiSettings#isLogoClickEnabled`
 - 오픈소스 라이선스, 법적 공지 Activity Intent 반환 API 추가
   - `InaviMapSdk#getIntentLicenseActivity`, `InaviMapSdk#getIntentLegalNoticeActivity`
 - 마커 아이콘과 타이틀 사이의 여백을 설정하는 기능 추가
   - `InvMarker#setTitleMargin`, `InvMarker#getTitleMargin`

### Bug fixes
 - 지도 줌 제스처 비활성화 시 지도 더블 탭 이벤트 콜백이 호출되지 않는 오류 수정
 - 초기 지도 로딩 중 `UserTrackingMode`가 설정되지 않는 오류 수정
 - 초기 지도 로딩 중 `CameraUpdate#setReason`이 무시되는 오류 수정
   - 초기 지도 위치는 카메라 변경 콜백이 호출되지 않게 변경

## 0.3.0 - 2019-11-26

### Features
 - `InvRoute` 추가 (지도 위에 경로를 표출하는 셰이프)
 - `InvMultiLine` *Deprecated* 적용 (`InvRoute`로 대체)

### Bug fixes
 - 지도 초기화 전에 `InvShape` 객체 생성 시 비정상 종료되는 오류 수정

## 0.2.0 - 2019-11-14

### Features
 - `LocationIcon` 추가 (위치 아이콘의 모양과 위치를 변경할 수 있는 기능)

### Bug fixes
 - `InvShape`에 `alpha` 값을 포함한 색상 설정 시 비정상인 색상으로 표출되는 오류 수정
 
### Improvements
 - 빠르게 지도 레벨 변경 시 지도 아이콘이 겹치는 현상 개선

## 0.1.0 - 2019-10-29
- Android SDK 초기 배포