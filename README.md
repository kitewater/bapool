# 밥풀
소셜 다이닝 앱서비스

## 정보
- **백엔드**: Spring Security, Spring Boot, OAuth 2.0, Kakao API, NAVER API
- **프론트엔드**: Kotlin, Firebase DB

## 기능 설명
1. **소셜 로그인 서비스 구현**
   - 카카오 로그인, 네이버 로그인을 OAuth 2.0을 사용하여 소셜 로그인 서비스 구현
2. **지도 API를 이용한 식당 검색 및 커스텀 마커 표시**
   - 네이버 지도 API를 사용해서 지도 렌더링
   - 카카오 REST API를 사용해서 식당 검색 가능
   - 검색 한 정보는 검색창에 출력 되거나, 지도에 커스텀 마커 형태로 렌더링
3. **근처 식당 검색 및 방만들기**
   - GPS 기능을 이용해서 내 주변에 있는 식당들 탐색 가능
   - 식당을 탐색한 후 식당을 주제로 채팅방을 생성 가능
   - 채팅방에서 메뉴,시간등을 정한 후 약속 확정 가능
   - 다른 사람들은 검색을 통해서 내가 만든 방에 들어 올 수 있음
4. **채팅 서비스 구현**
   - Firebase DB를 이용한 채팅 서비스 구현
5. **리뷰기능**
   - 식사 후 식당과 같이 먹은 사람들에 대해서 평가가능

## 스크린샷
<table>
  <tr>
    <td style="width: 50%;">
      <p align="center">
        <img src="https://github.com/kitewater/WEBIDE/assets/97283971/a690c3b3-cc88-46c3-906c-a9e65a176d07" width="100%">
      </p>
      <p align="center">소셜 로그인 서비스 구현</p>
    </td>
    <td style="width: 50%;">
      <p align="center">
        <img src="https://github.com/kitewater/WEBIDE/assets/97283971/68e26f4a-c174-430b-bd4b-6b22401ade03" width="100%">
      </p>
      <p align="center">지도 API를 이용한 식당 검색 및 커스텀 마커 표시</p>
    </td>
  </tr>
  <tr>
    <td style="width: 50%;">
      <p align="center">
        <img src="https://github.com/kitewater/WEBIDE/assets/97283971/ffaaff34-52d2-4340-aa85-5adbf9cb9416" width="100%">
      </p>
      <p align="center">근처 식당 검색</p>
    </td>
    <td style="width: 50%;">
      <p align="center">
        <img src="https://github.com/kitewater/WEBIDE/assets/97283971/8a9c2293-0d8f-4c0c-8441-f5deab408cf9" width="100%">
      </p>
      <p align="center">채팅 서비스 구현</p>
    </td>
  </tr>
  <tr>
    <td style="width: 50%;">
      <p align="center">
        <img src="https://github.com/kitewater/WEBIDE/assets/97283971/c9ae8afd-59ce-45ac-b9f7-3b80f0b8e351" width="100%">
      </p>
      <p align="center">식당에 대한 채팅방들 조회</p>
    </td>
    <td style="width: 50%;">
      <p align="center">
        <img src="https://github.com/kitewater/WEBIDE/assets/97283971/b32a23bd-bdf9-4f02-bbc7-2f093615cbdb" width="100%">
      </p>
      <p align="center">유저 평가 기능</p>
    </td>
  </tr>
</table>
