# 분할정복(Divide and Conquer) 정리

## 1️⃣ 분할정복이란?

**분할정복(Divide and Conquer)** 이란

> 문제를 **더 작은 하위 문제들로 나누어(Divide)** 해결하고,
> 각 하위 문제의 해를 **결합(Combine)** 하여 원래 문제를 해결하는 알고리즘 설계 기법
> 을 의미한다.

즉, **"큰 문제를 작은 문제로 나누어 해결하고 합치기"** 전략을 사용하는 알고리즘이다.

---

## 2️⃣ 언제 사용할까?

분할정복은 다음과 같은 상황에서 사용된다:

* 문제를 독립적인 하위 문제로 나눌 수 있을 때
* 하위 문제의 해를 결합하여 원래 문제를 해결할 수 있을 때
* 재귀적으로 해결 가능한 문제
* 병렬 처리 가능한 문제

### 대표적인 예시

* **[병합 정렬 (Merge Sort)](./merge-sort/merge-sort.md)**
* **[퀵 정렬 (Quick Sort)](./quick-sort/quick-sort.md)**
* **[이진 탐색 (Binary Search)](./binary-search/binary-search.md)**
* **[거듭제곱 계산](./power/power.md)** (분할정복을 이용한 거듭제곱)
* **하노이 탑**
* **카라추바 알고리즘** (큰 수 곱셈)
* **최근접 점 쌍 문제**

---

## 3️⃣ 분할정복의 핵심 개념

### 📌 3단계 프로세스

분할정복 알고리즘은 일반적으로 다음 3단계로 구성된다:

1. **Divide (분할)**: 문제를 더 작은 하위 문제들로 나눈다
2. **Conquer (정복)**: 하위 문제들을 재귀적으로 해결한다
3. **Combine (결합)**: 하위 문제들의 해를 결합하여 원래 문제의 해를 구한다

### 📐 분할정복의 구조

```
원래 문제
    │
    ├─ Divide: 작은 문제들로 분할
    │
    ├─ Conquer: 각 하위 문제 해결
    │   ├─ 하위 문제 1
    │   ├─ 하위 문제 2
    │   └─ 하위 문제 3
    │
    └─ Combine: 해를 결합
```

---

## 4️⃣ 분할정복 알고리즘의 구조

### 기본 템플릿

```java
T divideAndConquer(Problem problem) {
    // 1. Base Case: 더 이상 나눌 수 없는 경우
    if (isBaseCase(problem)) {
        return solveDirectly(problem);
    }
    
    // 2. Divide: 문제를 하위 문제들로 분할
    SubProblem[] subProblems = divide(problem);
    
    // 3. Conquer: 각 하위 문제를 재귀적으로 해결
    T[] solutions = new T[subProblems.length];
    for (int i = 0; i < subProblems.length; i++) {
        solutions[i] = divideAndConquer(subProblems[i]);
    }
    
    // 4. Combine: 하위 문제들의 해를 결합
    return combine(solutions);
}
```

### 핵심 요소

1. **Base Case**: 더 이상 나눌 수 없는 가장 작은 문제
2. **Divide**: 문제를 작은 하위 문제들로 분할
3. **Conquer**: 하위 문제들을 재귀적으로 해결
4. **Combine**: 하위 문제들의 해를 결합

---

## 5️⃣ 분할정복의 시간 복잡도 분석

### 📊 마스터 정리 (Master Theorem)

분할정복 알고리즘의 시간 복잡도를 분석하는 일반적인 방법:

```
T(n) = a * T(n/b) + f(n)

여기서:
- a: 하위 문제의 개수
- b: 각 하위 문제의 크기 비율 (1/b)
- f(n): 분할과 결합에 드는 시간
```

### 마스터 정리의 세 가지 경우

1. **f(n) = O(n^c) where c < log_b(a)**
   - 해: `T(n) = Θ(n^(log_b(a)))`

2. **f(n) = Θ(n^c) where c = log_b(a)**
   - 해: `T(n) = Θ(n^c * log n)`

3. **f(n) = Ω(n^c) where c > log_b(a)**
   - 해: `T(n) = Θ(f(n))`

### 예시: 병합 정렬

```
T(n) = 2 * T(n/2) + O(n)

a = 2, b = 2, f(n) = O(n)
log_b(a) = log_2(2) = 1
c = 1 (f(n) = O(n^1))

c = log_b(a) 이므로 경우 2
→ T(n) = Θ(n log n)
```

---

## 6️⃣ 분할정복 vs 다른 접근 방법

### 알고리즘 설계 기법 비교

| 기법 | 특징 | 시간 복잡도 | 예시 |
|------|------|------------|------|
| **분할정복** | 문제를 나누어 해결 후 결합 | 보통 O(n log n) | 병합 정렬, 퀵 정렬 |
| **동적 계획법** | 중복되는 하위 문제를 메모이제이션 | 문제에 따라 다름 | 피보나치, LCS |
| **그리디** | 매 순간 최선의 선택 | 보통 O(n log n) | 다익스트라, 크루스칼 |
| **완전 탐색** | 모든 경우를 탐색 | 매우 높음 | 백트래킹, 브루트 포스 |

### 분할정복의 장점

* **효율성**: 문제 크기를 지수적으로 줄일 수 있음
* **병렬 처리**: 하위 문제들이 독립적이면 병렬 처리 가능
* **직관성**: 문제를 자연스럽게 나누어 생각할 수 있음
* **재사용성**: 하위 문제 해결 로직을 재사용

### 분할정복의 단점

* **재귀 오버헤드**: 함수 호출 비용
* **메모리 사용**: 스택 공간 필요
* **최적화 어려움**: 일부 문제는 동적 계획법이 더 효율적

---

## 7️⃣ 분할정복 구현 패턴

### 패턴 1: 배열 분할

```java
void divideAndConquer(int[] arr, int left, int right) {
    if (left >= right) return;
    
    int mid = (left + right) / 2;
    
    divideAndConquer(arr, left, mid);
    divideAndConquer(arr, mid + 1, right);
    
    combine(arr, left, mid, right);
}
```

### 패턴 2: 문제 크기 분할

```java
T solve(Problem p) {
    if (p.size() <= threshold) {
        return solveDirectly(p);
    }
    
    Problem[] subProblems = divide(p);
    T[] solutions = new T[subProblems.length];
    
    for (int i = 0; i < subProblems.length; i++) {
        solutions[i] = solve(subProblems[i]);
    }
    
    return combine(solutions);
}
```

### 패턴 3: 이진 분할

```java
T binaryDivideAndConquer(Problem p) {
    if (isBaseCase(p)) {
        return baseSolution(p);
    }
    
    Problem left = divideLeft(p);
    Problem right = divideRight(p);
    
    T leftResult = binaryDivideAndConquer(left);
    T rightResult = binaryDivideAndConquer(right);
    
    return combine(leftResult, rightResult);
}
```

---

## 8️⃣ 분할정복의 공간 복잡도

### ⏱️ 재귀 호출 스택

* 최대 재귀 깊이: `O(log n)` (이진 분할의 경우)
* 각 재귀 호출마다 스택 프레임 생성
* **공간 복잡도**: `O(log n)` (스택 공간)

### ⏱️ 추가 메모리

* 병합 정렬: `O(n)` (임시 배열)
* 퀵 정렬: `O(log n)` (스택만)
* 이진 탐색: `O(log n)` (스택만)

---

## 9️⃣ 분할정복 문제 해결 전략

### 문제 분석 단계

1. **문제를 하위 문제로 나눌 수 있는가?**
   - 독립적인 하위 문제들로 분할 가능한지 확인

2. **하위 문제의 해를 결합할 수 있는가?**
   - 하위 문제들의 해를 어떻게 합칠지 계획

3. **Base Case는 무엇인가?**
   - 더 이상 나눌 수 없는 가장 작은 문제 정의

4. **시간 복잡도가 효율적인가?**
   - 마스터 정리로 시간 복잡도 분석

### 구현 단계

1. Base Case 처리
2. 문제 분할 로직 작성
3. 재귀 호출
4. 결과 결합 로직 작성

---

## ✨ 한 줄 요약

> 분할정복은 **"큰 문제를 작은 문제로 나누어 해결하고 합치는"** 알고리즘 설계 기법이며, 병합 정렬, 퀵 정렬, 이진 탐색 등 많은 효율적인 알고리즘의 기반이 된다.

