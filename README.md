### 학습 목적
- 현업에서 동일한 프로덕션 코드를 여러 팀원의 손을 거치다보니, 여러 이슈를 직면 
    - 기존 코드의 동작 QA, 신규 기능에 대한 QA 진행하게되는 늦은 피드백 발생
    - 소프트웨어의 신뢰도가 낮아지게 됨
    - 이로 인해 일정 지연의 가능성 발생
  
### 학습 목표
- 테스트 코드와 친근해지기
  - 테스트 코드는 귀찮다 라는 생각을 멀리하기
- 테스트 코드로 부터 빠른 피드백
- 자동화
- 안정감

### 샘플 코드 요구사항
- 키오스크 주문
  - 주문 목록에 음료 추가/삭제 기능
  - 주문 목록 전체 지우기
  - 주문 목록 총 금액 계산
  - 주문 생성
  - 키오스크 주문을 위한 상품 후보 리스트 조회
  - 상품의 판매 상태: 판매중, 판매보류, 판매중지
    - 판매중, 판매보류인 상태의 상품을 화면에 노출
  - id, 상품 번호, 상품 타입, 판매 상태, 상품 이름, 가격
  - 상품 번호 리스트를 받아 주문 생성
  - 주문은 주문 상태, 주문 등록 시간을 가진다.
  - 주문의 총 금액을 계산할 수 있어야 한다.