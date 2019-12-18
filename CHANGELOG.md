# Change Log

## 0.3.1 - 2019-12-18

### Features
 - 지도 이동 영역을 제한하는 I/F 추가
   - `InaviMap#setConstraintBounds`, `InaviMap#getConstraintBounds`
 - 로고 클릭 이벤트 활성화 여부 설정 I/F 추가
   - `UiSettings#setLogoClickEnabled`, `UiSettings#isLogoClickEnabled`
 - 오픈소스 라이선스, 법적 공지 Activity 호출 Intent I/F 추가
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