# 🔎 HSCZ - HS CODE 추천 및 관세사 매칭 플랫폼 🔎

![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![python](https://img.shields.io/badge/Python-14354C?style=for-the-badge&logo=python&logoColor=white)
![js](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white)
![html](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![css](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)
![jquery](https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white)
![oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=black)

## ✏️ 프로젝트 소개
> *수출입에 필요한 무역정보를 통합하여 제공하는 무역전문 웹 플랫폼입니다.*
<img src="https://github.com/haneuljj/DIMA3_project2_HSCZwebsite/assets/146147196/080cdac6-f8d5-4cf0-a5c1-2b053fd57a96.png" width="780" height="400"/>      

- 사용자가 입력한 제품 설명을 기반으로 HS CODE를 추천하고 관세사의 도움을 받을 수 있는 웹 구현
- 사용자가 편리하게 제품 설명을 입력하고 추천 결과를 확인할 수 있는 인터페이스 설계 및 구현
- HS CODE 추천 결과 및 상세 정보와 함께 관세사를 매칭 받을 수 있는 서비스 제공
- 무역정보에 필요한 통계정보, FTA정보, 수출입정보, 관세환율정보, 무역뉴스정보 제공

## 🧑‍🤝‍🧑 개발 기간 / 인원
24.04 ~ 24.05(6주) / 5명

## 🖇️ 시스템 아키텍처
<img src="https://github.com/haneuljj/DIMA3_project2_HSCZwebsite/assets/146147196/5a784ede-9c1c-43a2-a8a9-89243286d975.png" width="780" height="400"/>   


## 📑 주요 기능

### HS CODE 추천 기능
- 파이썬으로 개발한 HS코드 예측 모델을 FastAPI 서버로 구현하여 스프링 서버와 연결
- 사용자가 상품명 입력시 예측 모델이 상위 3개의 HS코드 결과 시각화
- HS코드 선택시 선택한 코드(품목)에 대한 10자리 세번 정보, 품목별 통계 정보 시각화
- 상세 10자리 세번 선택시 선택한 세번에 대한 관세 정보, 수출입 정보 등 제공

### 관세사 정보 제공 및 간편 상담 기능
- 지역별로 필터링 할 수 있는 관세사 목록 제공
- 관세사 이름, 법인 위치, 전화번호 제공
- HS코드 검색에 대한 결과 외에 자세한 정보를 얻기 위해 관세사 상담 기능 구현
- 일반 회원은 관세사 답변에 채택가능 -> 채택수가 많은 상위 관세사 정보를 메인페이지에 노출 

### 부가 기능
- 무역 통계 정보 제공 : 한국 및 주요 수출입국의 무역 통계 정보를 amcharts 라이브러리를 사용하여 시각화
- 무역 관련 뉴스 게시판 : 네이버 Search API를 활용하여 무역관련 뉴스 정보 제공
- FTA정보 및 수출입 정보 제공
- 관세환율정보 제공 : 관세청 API 활용하여 주간 관세환율정보 제공

### 기본 기능
- 회원가입 / 로그인 / 마이페이지

## 💻 웹사이트 예시 화면
#### 화면 녹화 넣을것
