# 매장 내 상품 주문 및 재고 관리
Store stock management

## 목차
- [개발 배경](#개발-배경)
- [기능 설명](#기능-설명)
  - [사용 시나리오](#사용-시나리오)
  - [API 사용법](#api-사용법)
  - [Swagger](#swagger)
- [Key Summary](#key-summary)
- [Folder Structure](#folder-structure)
- [Backend Architecture](#backend-architecture)
- [DB Schema](#db-schema)
- [설치 방법](#설치-방법)
- [실행 방법](#실행-방법)
  - [Docker](#docker)
- [향후 추가할 기능](#향후-추가할-기능)
- [참고자료](#참고자료)


### 개발 배경
- 웹 페이지에 메뉴판을 구성하여 일반적인 실물 메뉴판과 다른 이점을 고객들에게 제공할 수 있다.
- 웹 페이지에서 메뉴 확인과 주문이 가능하다.
- 실물 메뉴판과 달리 메뉴를 영상으로 확인할 수 있어 메뉴에 대한 외형을 고객들이 더 쉽게 이해할 수 있다.
- 실물 메뉴판에 비해 많은 양의 정보를 제공하기 수월하다.
  - 알레르기 물질이나, 원산지 표시 정보 표기
  - 기존에는 의무적으로 정보를 고지했으나 각각의 메뉴에 대해 자세하게 알 수 없는 경우도 있었고, 일괄적으로 기입되어 재확인이 필요했다.
  - 등록된 레시피를 통해 개별 메뉴마다 자세한 정보를 제공할 수 있다.


### 기능 설명


#### 사용 시나리오

고객
- 기본 시나리오
  1. 고객이 매장에 방문한 후 자리에 앉는다.
  2. 고객의 스마트폰으로 테이블에 있는 QR코드를 촬영한다.
  3. QR코드의 정보로 고객은 웹페이지에 자동으로 로그인한다.
  4. 고객이 메뉴를 확인한다.
  5. 고객이 장바구니에 주문할 제품들을 추가한다.
  6. 고객이 장바구니 페이지에서 주문을 시작한다.
  7. 주문하고자 하는 메뉴와 수량을 다시 확인하고 결제페이지로 넘어간다.
  8. 결제페이지에서 결제가 완료된다.


#### API 사용법


#### Swagger

Swagger 3.0.0 사용을 위해서는 2.9.2 버전에서 변경되는 부분이 있다.

```gradle
implementation group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'
implementation group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
```

```gradle
implementation group: 'io.springfox', name: 'springfox-boot-starter', version: '3.0.0'
implementation group: 'io.springfox', name: 'springfox-swagger-ui', version: '3.0.0'
```

springfox-swagger2 대신에 springfox-boot-starter를 사용한다.

SwaggerConfig에서 @EnableSwagger2 대신 @EnableWebMVC를 사용한다.

Swagger 접속 URL이 localhost:8080/swagger-ui.html에서 localhost:8080/swagger-ui/index.html로 변경되었다.


### Key Summary

포트폴리오에서 강조하고 싶은 내용들을 적습니다. 예를 들어 유닛테스트 Code coverage 가 100%라면 적어도 됩니다.


### Folder Structure 소개

```bash
├── ../logs
├── stockManager
│    ├── api
│    │    └── dto
│    │         ├── customer
│    │         ├── duty
│    │         ├── ingredient
│    │         ├── item
│    │         ├── member
│    │         ├── menu
│    │         ├── order
│    │         └── recipe
│    ├── config
│    ├── controller
│    │    ├── dto
│    │    │    ├── duty
│    │    │    ├── item
│    │    │    ├── member
│    │    │    ├── order
│    │    │    └── recipe
│    │    └── management
│    ├── domain
│    │    ├── duty
│    │    ├── ingredient
│    │    ├── item
│    │    ├── member
│    │    ├── order
│    │    ├── recipe
│    │    └── search
│    ├── exception
│    ├── handler
│    ├── interceptor
│    ├── repository
│    │    ├── jpa
│    │    └── memory
│    ├── resolver
│    ├── service
│    └── testData
├── ../Dockerfile
├── ../build.gradle
└── ../README.md
```

### Backend Architecture

- Java 11
- Spring MVC / Spring Boot:2.6.1
  - data jpa
  - thymeleaf
  - validation
  - web
  - security
- Swagger:3.0.0
  - Swagger:2.9.2에서 바뀌는 부분들이 다음 버전의 Swagger에 적용될 것으로 판단되어 미리 적용하였다.
- Mysql 8.0


### DB Schema

![stockManager](https://user-images.githubusercontent.com/46220202/158642693-f1a28806-2e3b-4f9d-b986-b74a7c769c08.png)

### 설치 방법


#### docker


```docker
#현재 가지고 있는 이미지 확인
docker images

# mysql과 stockManager를 연결하기 위해 network를 생성 
docker network create stock-manager-network

```

```docker
# mysql을 가져온다
docker pull mysql

# mysql을 mysql-container라는 이름을 갖는 이미지로 생성한다.
# 동시에 위에서 생성한 stock-manager-network에 연결한다.
docker run -dit --name mysql-container --network stock-manager-network mysql

# mysql에 접속한다.
docker exec -it mysql-container bash

```

mysql 접속 상황
```mysql

mysql -u root -p

Enter password : 비밀번호 

# stockManager db를 생성한다.
create database stockManager;

```

```docker

docker pull daepoid/stock-manager

docker run -it --name app --network stock-manager-network -p 8080:8080 daepoid/stock-manager:0.1

docker network inspect stock-manager-network

```


### 향후 추가할 기능

- RestAPI 기준에 부합하도록 추가 및 수정
  - ~~Swagger 작성~~
  - API 예외처리 및 인증
- ~~cart 를 entity 에서 embedded 로 변경~~
- ~~customer 와 member 의 공통 부분을 추출하고 이를 상속하는 방식으로 변경~~
- 테이블에 있는 QR 코드를 이용하여 로그인 vs 개인 아이디를 통해 로그인
  - 매장 내의 테이블 마다 고유 아이디와 비밀번호를 통해 주문을 받는 것이 고객의 개인정보를 관리하는 것 보다 편리함
  - 그러나 외부 환경에서 허위 주문이나 주문 내역에 대한 부인방지 장치가 요구됨
  - 예) 주문과 동시에 결제하는 방식
- 이미지 불러오기
- 제품의 세부정보를 확인할 수 있는 페이지 추가
  - 제품의 사진, 알레르기 정보 등등..
- 제품 주문 시 관리자에게 알림 전송
- 관리자에게 제품 판매 정보를 제공 (판매량, 순수익, ..)
- ~~검색 기능 추가 (재고, 레시피, 메뉴, 고객, 직원, 직무, 판매 기록...)~~
  - ~~SQL 을 이용한 검색 기능으로 개발~~
  - 추후 변경
- 다양한 테스트 시행
  - 테스트 커버리지 100%
- ~~docker 사용하도록 설정~~
- ~~소셜 로그인~~
  - 구현한 로그인 기능을 이용하는 것이 목적에 맞다고 판단하여 추가 하지 않음
- ~~aws, 배포 자동화~~
- 댓글, 평점 등의 기능 추가
- 리팩토링
- 페이징
- Spring Data Jpa + QueryDSL 로 변경
- JPA 최적화


### 참고자료

[Swagger 2.9.2 -> 3.0.0 마이그레이션](https://nahwasa.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-Swagger-UI-292-300-%EB%A7%88%EC%9D%B4%EA%B7%B8%EB%A0%88%EC%9D%B4%EC%85%98-%EB%B0%A9%EB%B2%95-Spring-Boot-Swagger-UI)