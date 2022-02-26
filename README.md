# 매장 내 상품 주문 및 재고 관리
Store stock management

- 목차

## 기능 설명



개발 기획 배경
- 웹 페이지에 메뉴판을 구성하여 일반적인 실물 메뉴판과 다른 이점을 고객들에게 제공할 수 있다.
- 웹 페이지에서 메뉴 확인과 주문이 가능하다.
- 실물 메뉴판과 달리 메뉴를 영상으로 확인할 수 있어 메뉴에 대한 외형을 고객들이 더 쉽게 이해할 수 있다.
- 실물 메뉴판에 비해 많은 양의 정보를 제공하기 수월하다.
  - 알레르기 물질이나, 원산지 표시 정보 표기
  - 기존에는 의무적으로 정보를 고지했으나 각각의 메뉴에 대해 자세하게 알 수 없는 경우도 있었고, 일괄적으로 기입되어 재확인이 필요했다.
  - 등록된 레시피를 통해 개별 메뉴마다 자세한 정보를 제공할 수 있다.

### API 사용법

## Key Summary

포트폴리오에서 강조하고 싶은 내용들을 적습니다. 예를 들어 유닛테스트 Code coverage 가 100%라면 적어도 됩니다.


## Folder Structure 소개

```bash

```

## Backend Architecture

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

## Routes / Endpoint 소개

## Controller, Service, Repository, Store Procedure 관계도

## DB Schema

## Frontend Architecture - React 라면 Redux pattern 의 역할 소개

## 설치 방법

## 실행 방법

### docker

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

### Swagger

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

## 향후 추가할 기능

- RestAPI 기준에 부합하도록 추가 및 수정
  - Swagger 작성
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
- 다양한 테스트 추가
  - 단위 테스트, 통합 테스트 등등..
- ~~docker 사용하도록 설정~~
- ~~소셜 로그인~~
  - 구현한 로그인 기능을 이용하는 것이 목적에 맞다고 판단하여 추가 하지 않음
- ~~aws, 배포 자동화~~
- 댓글, 평점 등의 기능 추가
- 리팩토링
- 페이징
- Spring Data Jpa + QueryDSL 로 변경
- JPA 최적화

## 참고자료

Swagger 2.9.2 -> 3.0.0 마이그레이션
https://nahwasa.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-Swagger-UI-292-300-%EB%A7%88%EC%9D%B4%EA%B7%B8%EB%A0%88%EC%9D%B4%EC%85%98-%EB%B0%A9%EB%B2%95-Spring-Boot-Swagger-UI