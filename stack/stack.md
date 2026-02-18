# 스택 (Stack) 정리

## 1) 스택이란?
스택(Stack)은 **LIFO(Last In, First Out, 후입선출)** 구조입니다.

가장 나중에 들어온 원소가 가장 먼저 나갑니다.
삽입/삭제는 한쪽 끝(Top)에서만 일어납니다.

---

## 2) 핵심 연산과 복잡도

| 연산 | 설명 | 시간 복잡도 |
|------|------|-------------|
| `push(x)` | top에 원소 삽입 | O(1) |
| `pop()` | top 원소 제거 후 반환 | O(1) |
| `peek()` | top 원소 조회 | O(1) |
| `isEmpty()` | 비어 있는지 확인 | O(1) |
| `size()` | 원소 개수 확인 | O(1) |

공간 복잡도는 저장된 원소 수를 `N`이라 할 때 `O(N)`입니다.

---

## 3) 구현 방법

### 3-1) 배열(또는 리스트) 기반 구현
Top 인덱스를 관리하며 push/pop을 처리합니다.

```java
class IntStack {
    private final int[] data;
    private int top = -1;

    IntStack(int capacity) {
        this.data = new int[capacity];
    }

    void push(int x) {
        data[++top] = x;
    }

    int pop() {
        return data[top--];
    }

    int peek() {
        return data[top];
    }

    boolean isEmpty() {
        return top == -1;
    }
}
```

장점:
- 빠르고 메모리 예측이 쉬움

주의:
- 고정 크기라 capacity를 미리 알아야 함

### 3-2) Java 표준 라이브러리 사용(권장)
`ArrayDeque`를 스택처럼 사용하는 방식이 일반적으로 가장 실용적입니다.

```java
import java.util.ArrayDeque;

ArrayDeque<Integer> stack = new ArrayDeque<>();
stack.push(10);   // 삽입
stack.push(20);
int a = stack.peek(); // 20
int b = stack.pop();  // 20
boolean empty = stack.isEmpty();
```

권장 이유:
- `Stack` 클래스(legacy)보다 실무/코테에서 보통 `ArrayDeque`를 선호
- push/pop/peek가 모두 빠름

---

## 4) 스택의 대표 패턴

### 4-1) 괄호 검사
- 여는 괄호는 push
- 닫는 괄호가 나오면 top과 짝 비교 후 pop
- 중간에 불일치가 나거나 마지막에 스택이 남으면 실패

### 4-2) 단조 스택
- 스택 내부를 단조 증가/감소가 되도록 유지
- 각 원소가 스택에 최대 1번 push, 최대 1번 pop
- 총 연산이 선형(`O(N)`)으로 떨어지는 경우가 많음

### 4-3) 상태 복원/되돌리기
- 명령 이력 push
- 취소(undo) 시 pop하여 직전 상태 복원

### 4-4) 그룹별 독립 스택
- `BOJ 2841`처럼 그룹(기타 줄 1~6)마다 별도 스택 운용
- 그룹 간 간섭이 없을 때 모델링이 단순하고 정확함

---

## 5) 스택 vs 다른 자료구조

| 자료구조 | 접근 방식 | 삽입/삭제 특징 | 대표 용도 |
|----------|-----------|----------------|-----------|
| 스택 | 한쪽 끝(top) | top만 O(1) | LIFO, 되돌리기, 단조 처리 |
| 큐 | 양쪽 중 앞에서 삭제 | FIFO | 순차 처리, BFS |
| 덱 | 양쪽 끝 | 양끝 O(1) | 큐+스택 혼합 |
| 힙 | 우선순위 기준 | 최소/최대 원소 관리 | 우선순위 큐 |

선택 기준:
- "가장 최근 것부터 처리"면 스택
- "먼저 들어온 것부터 처리"면 큐
- "가장 큰/작은 값 우선"이면 힙

---

## 6) 구현 시 주의사항

- 빈 스택에서 `pop/peek` 호출 금지
- `java.util.Stack`보다 `ArrayDeque` 우선 고려
- `LinkedList`를 스택으로 쓰는 건 가능하지만 일반적으로 `ArrayDeque`가 더 적합
- 그룹별 문제에서는 배열 + 여러 스택(`ArrayDeque[]`)이 깔끔함
- 입력이 큰 문제는 빠른 입출력과 함께 사용

---

## 7) 이 저장소의 관련 문제

- `stack/boj-2841`: 줄별 6개 스택으로 최소 손가락 이동 횟수 계산

---

## 한 줄 요약
스택은 **"최근에 넣은 것을 먼저 꺼내는 LIFO 구조"**로,
괄호 검사, 단조 스택, 상태 복원, 그룹별 상태 관리 문제를 단순하고 효율적으로 해결할 때 핵심 도구입니다.
