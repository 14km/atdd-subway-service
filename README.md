<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-service">
</p>

<br>

# 지하철 노선도 미션
[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew bootRun
```
<br>

## ✏️ Code Review Process
[텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

<br>

## 🐞 Bug Report

버그를 발견한다면, [Issues](https://github.com/next-step/atdd-subway-service/issues) 에 등록해주세요 :)

<br>

## 📝 License

This project is [MIT](https://github.com/next-step/atdd-subway-service/blob/master/LICENSE.md) licensed.

## 1단계 - 인수 테스트 기반 리팩터링 TO-DO list

- [x] LineSectionAcceptanceTest 시나리오 테스트 작성
- [x] LineService 세부기능 단위 테스트 작성
- [x] Line 세부기능 단위 테스트 작성
- [x] LineService 리팩터링
- [x] Line 리팩터링
- [x] Sections 리팩터링

## 2단계 - 경로 조회 기능 TO-DO list

- [x] 최단 경로 조회 인수 테스트 만들기
- [x] 최단 경로 조회 기능 단위 테스트
- [x] 최단 경로 조회 기능 구현
- [x] 예외 상황 테스트
- [x] 예외 상황 구현
    - 출발역과 도착역이 같은 경우
    - 출발역과 도착역이 연결이 되어 있지 않은 경우
    - 존재하지 않은 출발역이나 도착역을 조회 할 경우

## 3단계 - 인증을 통한 기능 구현 TO-DO list

- [x] 토큰 발급 기능 (로그인) 인수 테스트 만들기
- [x] 유효하지 않은 토큰 인수 테스트
- [x] 유효하지 않은 토큰 예외 처리 작성
- [x] 인증 - 내 정보 조회 기능 완성하기
- [x] 인증 - 즐겨 찾기 기능 완성하기
- [x] 전체 리펙토링

## 4단계 - 요금 조회 TO-DO list

- [x] 경로 조회 시 거리 기준 요금 정보 포함 테스트 작성
- [x] 경로 조회 시 거리 기준 요금 정보 포함 코드 작성
- [x] 노선별 추가 요금 정책 테스트 작성
- [x] 노선별 추가 요금 정책 코드 작성
- [x] 연령별 할인 정책 테스트 작성
- [x] 연령별 할인 정책 코드 작성