# java-lotto-precourse
## 요구사항
로또 번호의 숫자 범위는 1~45까지이다.
1개의 로또를 발행할 때 중복되지 않는 6개의 숫자를 뽑는다.
당첨 번호 추첨 시 중복되지 않는 숫자 6개와 보너스 번호 1개를 뽑는다.
당첨은 1등부터 5등까지 있다. 당첨 기준과 금액은 아래와 같다.
```
1등: 6개 번호 일치 / 2,000,000,000원
2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
3등: 5개 번호 일치 / 1,500,000원
4등: 4개 번호 일치 / 50,000원
5등: 3개 번호 일치 / 5,000원
```

로또 구입 금액을 입력하면 구입 금액에 해당하는 만큼 로또를 발행해야 한다.
로또 1장의 가격은 1,000원이다.
당첨 번호와 보너스 번호를 입력받는다.
사용자가 구매한 로또 번호와 당첨 번호를 비교하여 당첨 내역 및 수익률을 출력하고 로또 게임을 종료한다.

사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
Exception이 아닌 IllegalArgumentException, IllegalStateException 등과 같은 명확한 유형을 처리한다.

## 입출력 요구 사항
#### 입력
- 로또 구입 금액을 입력 받는다. 구입 금액은 1,000원 단위로 입력 받으며 1,000원으로 나누어 떨어지지 않는 경우 예외 처리한다.
- 당첨 번호를 입력 받는다. 번호는 쉼표(,)를 기준으로 구분한다.
- 보너스 번호를 입력 받는다.

```java
14000
1, 2, 3, 4, 5, 6
7
```

#### 출력
- 발행한 로또 수량 및 번호를 출력한다. 로또 번호는 오름차순으로 정렬하여 보여준다.
```java
8개를 구매했습니다.
[8, 21, 23, 41, 42, 43] 
[3, 5, 11, 16, 32, 38] 
[7, 11, 16, 35, 36, 44] 
[1, 8, 11, 31, 41, 42] 
[13, 14, 16, 38, 42, 45] 
[7, 11, 30, 40, 42, 43] 
[2, 13, 22, 32, 38, 45] 
[1, 3, 5, 14, 22, 45]
```

- 당첨 내역을 출력한다.
```java
3개 일치 (5,000원) - 1개
4개 일치 (50,000원) - 0개
5개 일치 (1,500,000원) - 0개
5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
6개 일치 (2,000,000,000원) - 0개
```
- 수익률은 소수점 둘째 자리에서 반올림한다. (ex. 100.0%, 51.5%, 1,000,000.0%)
- 예외 상황 시 에러 문구를 출력해야 한다. 단, 에러 문구는 "[ERROR]"로 시작해야 한다.
```java
[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.
```

## 프로그래밍 요구사항
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
- else 예약어를 쓰지 않는다.
- else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
> 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
- Java Enum을 적용하여 프로그램을 구현한다.
- 구현한 기능에 대한 단위 테스트를 작성한다. 단, UI(System.out, System.in, Scanner) 로직은 제외한다.
- 단위 테스트 작성이 익숙하지 않다면 LottoTest를 참고하여 학습한 후 테스트를 작성한다.

## 라이브러리
- camp.nextstep.edu.missionutils에서 제공하는 Randoms 및 Console API를 사용하여 구현해야 한다.
- Random 값 추출은 camp.nextstep.edu.missionutils.Randoms의 pickUniqueNumbersInRange()를 활용한다.
- 사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용한다.


```java
// 1에서 45 사이의 중복되지 않은 정수 6개 반환
Randoms.pickUniqueNumbersInRange(1, 45, 6);
```

#### Lotto 클래스
- 제공된 Lotto 클래스를 사용하여 구현해야 한다.
- Lotto에 numbers 이외의 필드(인스턴스 변수)를 추가할 수 없다.
- numbers의 접근 제어자인 private은 변경할 수 없다.
- Lotto의 패키지를 변경할 수 있다.
```java
public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현
}
```

## 기능 설계
- [ ] 구매한 로또 번호 관리 (기존 Lotto 클래스 사용)
  - [ ] 당첨 번호 저장
    - 기존 numbers 리스트 사용. 인풋 매니저에서 인스턴트 생성하여 전달
  - [ ] 구매할 로또 수 계산
  - [ ] 랜덤 구매 로또목록 생성
  - [ ] 유효성 판단
    - 아래의 경우 IllegalArgumentException 로 에러메세지 출력
      - [ ] 당첨 번호, 보너스번호로 1~45 외의 숫자를 입력한 경우
      - [ ] 구입금액이 1000원으로 나누어 떨어지지 않는 경우
      - [ ] 로또 번호 갯수가 6개가 아닌경우
      - [ ] 보너스 번호가 1개가 아닌경우
  - [ ] 번호 일치 판단
    - 자동 구매된 번호 전체를 탐색함.  
    해보다가 가능하면 쓰레드 나눠서 여러번호를 동시에 비동기 진행하도록 개선 시도
    - [ ] 예외) 사용자가 구매한 로또목록이 생성되지 않은경우 IllegalStateException처리
  - [ ] 보너스 번호 일치 판단
    - 6개중 5개만 일치하는 경우 보너스 번호 일치여부는 별도로 체크 진행

- [ ] 당첨 관리
  - [ ] 최초 투자비용 저장
  - [ ] 당첨 등수 저장
  - [ ] 수익률 계산
    - [ ] 예외) 모든 로또에 대해 당첨확인이 되지 않은 경우 IllegalStateException처리

- [ ] 입출력 관리
  - [ ] 입력 관리
  - [ ] 출력 관리 
