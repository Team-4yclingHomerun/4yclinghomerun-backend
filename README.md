## ⭐️ About Team
  - 팀명: 사이클링홈런
  - 인원: 백엔드1명
  - 프로젝트목표: "KT Wiz 팬 커뮤니티 플랫폼을 통해 팬들이 정보를 공유하고 소통할 수 있는 공간을 제공하며, 이를 통해 팬들의 커뮤니티 참여도를 높이는 것"
  - 백엔드목표:
    - RESTful API를 통해 프론트엔드 간의 원활한 데이터 통신을 지원합니다.
    - 안정적이고 확장 가능한 백엔드 시스템을 구축하며,
    - 최상의 사용자 경험을 위한 기술적 지원을 합니다.
## 🚀 Project

- (24.10~ 24.11) KTwiz 팬 커뮤니티 플랫폼: 정보 제공 및 사용자 관리 시스템
  - 기존 Kt wiz 정보제공 홈페이지에 백엔드 기능을 추가했습니다.
  - security 없이 인증/인가를 구현 했습니다.
  - 사용자 경험을 향상시키기 위해 카카오와 구글 OAuth 소셜 로그인 기능을 구현했습니다.
  - 팬들 간의 정보 공유와 소통을 위해 실시간 단체 채팅 기능을 구현했습니다.

## 💻 Preview
![image](https://github.com/user-attachments/assets/6213d4d1-c2ec-4cbe-b3cc-0f01a5e47616)

## :coffee:개발환경
<div align="center">

|      Type       |                                                                                                                  Tool                                                                                                                   |
| :-------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|    Language     |                                                        <img src="https://img.shields.io/badge/java%2021-007396?style=for-the-badge&logo=java&logoColor=white">                         |
|    Framework    | <img src="https://img.shields.io/badge/springboot%203.3.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">                                 
|       DB        |                     <img src="https://img.shields.io/badge/PostgreSQL%2014-4169E1?style=for-the-badge&logo=postgresql&logoColor=white">  <img src="https://img.shields.io/badge/flyway-CC0200?style=for-the-badge&logo=java&logoColor=white">
|     Server      |       <img src="https://img.shields.io/badge/AWS Ec2-FF9900?style=for-the-badge&logo=java&logoColor=white">    <img src="https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=java&logoColor=white">
|     DevOps      |     <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=java&logoColor=white"> 
|    WebSocket    | <img src="https://img.shields.io/badge/SockJS-000000?style=for-the-badge&logo=sockjs&logoColor=white"> <img src="https://img.shields.io/badge/STOMP-FF6699?style=for-the-badge&logo=stomp&logoColor=white">
|  Collaboration  |             ![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white) ![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)              |
</div>

## 🔦 기능
- #### 설계 및 구현 : 웹 사이트의 Kt Wiz 소개 페이지, 배너, 슬로건 
  -  KT Wiz의 데이터베이스 API 사용 기간 만료로 인해, 정적 JSON 파일을 생성하고 이를 기반으로 RESTful API를 제공하도록 백엔드 로직을 재구성했습니다.
<img src="https://github.com/user-attachments/assets/1ba51d82-b60e-48f8-9136-4d0832226411" alt="스크린샷" width="1000">

- #### 설계 및 구현 : 회원가입 구현
  - 시큐리티 없이 인증/인가를 구현 했습니다.
  - 사용자의 아이디와 비밀번호를 통해 회원 가입을 진행하고, JWT토큰을 사용하여 인증 및 인가 처리를 하였습니다.
  - 화이트리스트에 포함된 URI는 인증 없이 접근이 가능하도록 설정하였습니다.
  - 필터 기반 전역 토큰 처리: 인증이 필요한 API는 필터로 처리하여, 각 요청에 대한 JWT 토큰의 유효성을 검사하고 권한을 확인합니다. 
<img src="https://github.com/user-attachments/assets/78c5b94e-e727-4aa6-902b-b17889fea4fe" alt="스크린샷" width="1000">

- #### 설계 및 구현 : Oauth KAKAO, GOOGLE 구현
  - 사용자들은 별도로 새로운 계정을 만들 필요없이 기존 소셜계정으로 간편하게 로그인 할 수 있다.
  - OAuth 2.0 프로토콜 사용해 카카오와 구글 OAuth를 통해 인증을 처리하고, 외부 제공자의 사용자 정보를 받아와 서버에 저장합니다.
  - 소셜 로그인 성공 후, 사용자의 인증 정보를 바탕으로 자체 서비스를 위한 JWT 토큰을 발급하여, 이를 통해 서버와 클라이언트 간의 인증 상태를 유지합니다.
<img src="https://github.com/user-attachments/assets/2eb6bf11-593b-4c4a-b3c3-315e027cc077" alt="스크린샷" width="1000">
  
- #### 설계 및 구현 : 팬들과의 소통 단체 채팅방 구현
  - 웹소켓을 사용하여 실시간 단체 채팅방을 구현하였습니다.
  - 이를 통해 팬들이 서로 실시간으로 소통할 수 있는 환경을 제공하며, 메시지가 즉시 전달되고 업데이트됩니다.
  - 사용자가 스크롤을 내릴 때마다 데이터베이스에서 이전 메시지를 슬라이싱하여 불러오고 사용자에게 더 많은 메시지를 자연스럽게 표시할 수 있습니다.
<img src="https://github.com/user-attachments/assets/b3f0e254-2abb-4869-bb6f-2d76461e4cea" alt="스크린샷" width="1000">

 - #### 설계 및 구현: ERD 설계
   - 멤버 데이터의 식별자를 추측하기 어렵게 UUID를 사용했습니다. 이를 통해 보안적인 측면을 강화했습니다.
   - auth 스키마는 민감한 사용자 개인 정보 등을 포함하고 있기 때문에 스키마를 독립적으로 관리했습니다.
   - profile 스키마는 사용자 프로필과 관련된 정보로, 보안적인 측면에서 auth보다는 상대적으로 유연하게 접근할 수 있도록 관리할 수 있습니다.
   ![4yclinghomerun](https://github.com/user-attachments/assets/00d0c992-2659-4420-a247-78feda167a34)
 
 - #### 설계 및 구현: Flyway db 형성관리
   - Flyway를 사용함으로써 데이터베이스 스키마 버전관리 일관성 있게 유지합니다.
   - 프로젝트 초기에는 자주 데이터베이스 필드가 바뀌었기 때문에, 수동으로 데이터를 관리하는 것보다 Flyway를 사용하여 마이그레이션 스크립트를 작성하고 자동으로 실행하는 방식이 매우 유용했습니다.
<img width="702" alt="스크린샷 2024-11-27 01 35 49" src="https://github.com/user-attachments/assets/52266a0f-d3f7-4f44-b710-833d04fdbb44">

## 🔗 배포 주소

[4yclingHomerun 배포 주소](https://4yclinghomerun-deploy.vercel.app/)

