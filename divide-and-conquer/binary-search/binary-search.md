# 이분 탐색(Binary Search) 정리

## 1️⃣ 이분 탐색이란?

**이분 탐색(Binary Search)**이란

> 정렬된 배열이나 단조성을 가진 함수에서
> 탐색 범위를 절반씩 줄여가며 원하는 값을 찾는 알고리즘
> 을 의미한다.

즉, **"탐색 범위를 절반씩 줄여가며 효율적으로 탐색"**하는 알고리즘이다.

---

## 2️⃣ 언제 사용할까?

이분 탐색은 다음과 같은 상황에서 사용된다:

* **정렬된 배열에서 특정 값 찾기**
* **조건을 만족하는 최대/최소값 찾기** (파라메트릭 서치)
* **단조성을 가진 함수에서 최적값 찾기**

### 대표적인 예시

* **정렬된 배열에서 값 찾기** (기본 이분 탐색)
* **랜선 자르기** (파라메트릭 서치) - 예: 1654번 문제
* **나무 자르기** (파라메트릭 서치)
* **예산 배정** (파라메트릭 서치)

---

## 3️⃣ 이분 탐색의 핵심 개념

### 📌 핵심 아이디어

* 탐색 범위를 **절반씩 줄여가며** 탐색
* 각 단계에서 **중간값을 확인**하여 범위 축소
* **단조성**이 있어야 함 (값이 증가/감소하는 방향이 일정)

### 📐 이분 탐색의 구조

```
정렬된 배열에서 7 찾기: [1, 3, 5, 7, 9, 11, 13]

초기 범위: [0, 6] (전체 배열)
1단계: mid = 3, arr[3] = 7
  → 찾음! ✅

만약 7이 아니라면:
- arr[mid] < target → left = mid + 1 (오른쪽 탐색)
- arr[mid] > target → right = mid - 1 (왼쪽 탐색)
```

---

## 4️⃣ 이분 탐색 알고리즘의 구조

### 기본 구현 (정렬된 배열에서 값 찾기)

```java
int binarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left <= right) {
        int mid = (left + right) / 2;
        
        if (arr[mid] == target) {
            return mid;  // 찾음
        } else if (arr[mid] < target) {
            left = mid + 1;  // 오른쪽 탐색
        } else {
            right = mid - 1;  // 왼쪽 탐색
        }
    }
    
    return -1;  // 찾지 못함
}
```

### 파라메트릭 서치 (조건을 만족하는 최대값 찾기)

```java
long parametricSearch(long left, long right) {
    long answer = 0;
    
    while (left <= right) {
        long mid = (left + right) / 2;
        
        if (canMake(mid)) {
            // 조건을 만족함 → 더 큰 값 시도
            answer = mid;
            left = mid + 1;
        } else {
            // 조건을 만족하지 않음 → 더 작은 값 시도
            right = mid - 1;
        }
    }
    
    return answer;
}

// 조건 함수: mid 값이 조건을 만족하는가?
boolean canMake(long mid) {
    // 문제에 따라 구현
    return true;  // 예시
}
```

---

## 5️⃣ 이분 탐색의 특징

### ✅ 장점

* **매우 빠른 탐색**: O(log N)
* **단조성 활용**: 조건을 만족하는 최대/최소값 찾기 가능
* **파라메트릭 서치**: 최적화 문제를 결정 문제로 변환

### ❌ 단점

* **정렬 필요**: 배열이 정렬되어 있어야 함 (기본 이분 탐색)
* **단조성 필요**: 파라메트릭 서치의 경우 단조성이 있어야 함

---

## 6️⃣ 시간 복잡도 분석

### ⏱️ 탐색 횟수: `O(log N)`

* 매번 탐색 범위가 절반으로 줄어듦
* N → N/2 → N/4 → ... → 1
* 총 탐색 횟수: log₂(N)

### ⏱️ 각 반복의 연산: `O(1)` 또는 `O(K)`

* **기본 이분 탐색**: O(1) (중간값 확인만)
* **파라메트릭 서치**: O(K) (조건 함수 계산)

### ✅ 전체 시간 복잡도

**기본 이분 탐색**:
```
O(log N)
```

**파라메트릭 서치**:
```
O(K × log(범위))
```
* K: 조건 함수의 시간 복잡도
* 범위: 탐색 범위의 크기

---

## 7️⃣ 공간 복잡도

### ⏱️ 추가 메모리: `O(1)`

* 변수 몇 개만 사용 (left, right, mid, answer 등)
* 재귀 호출 없이 반복문으로 구현 가능

### ✅ 전체 공간 복잡도

```
O(1)
```

(입력 배열을 제외한 추가 메모리)

---

## 8️⃣ 이분 탐색의 구현 패턴

### 패턴 1: 기본 이분 탐색 (값 찾기)

```java
int binarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left <= right) {
        int mid = (left + right) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return -1;
}
```

### 패턴 2: Lower Bound (이상의 최소값)

```java
int lowerBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length;
    
    while (left < right) {
        int mid = (left + right) / 2;
        
        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return left;
}
```

### 패턴 3: Upper Bound (초과의 최소값)

```java
int upperBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length;
    
    while (left < right) {
        int mid = (left + right) / 2;
        
        if (arr[mid] <= target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return left;
}
```

### 패턴 4: 파라메트릭 서치 (조건을 만족하는 최대값)

```java
long parametricSearch(long left, long right) {
    long answer = 0;
    
    while (left <= right) {
        long mid = (left + right) / 2;
        
        if (canMake(mid)) {
            answer = mid;
            left = mid + 1;  // 더 큰 값 시도
        } else {
            right = mid - 1;  // 더 작은 값 시도
        }
    }
    
    return answer;
}
```

### 패턴 5: 파라메트릭 서치 (조건을 만족하는 최소값)

```java
long parametricSearchMin(long left, long right) {
    long answer = 0;
    
    while (left <= right) {
        long mid = (left + right) / 2;
        
        if (canMake(mid)) {
            answer = mid;
            right = mid - 1;  // 더 작은 값 시도
        } else {
            left = mid + 1;  // 더 큰 값 시도
        }
    }
    
    return answer;
}
```

---

## 9️⃣ 이분 탐색의 종류

### 1. 기본 이분 탐색

**목표**: 정렬된 배열에서 특정 값 찾기

**조건**: `arr[mid] == target`

**예시**: 정렬된 배열에서 7 찾기

### 2. 파라메트릭 서치 (Parametric Search)

**목표**: 조건을 만족하는 최대/최소값 찾기

**조건**: `canMake(mid)` (조건 함수)

**예시**: 
* 랜선 자르기 (최대 길이 찾기)
* 나무 자르기 (최대 높이 찾기)
* 예산 배정 (최대 예산 찾기)

---

## 🔟 이분 탐색 vs 다른 방법

### 탐색 방법 비교

| 방법 | 시간 복잡도 | 조건 | 설명 |
|------|------------|------|------|
| **선형 탐색** | O(N) | 없음 | 모든 원소 확인 |
| **이분 탐색** | O(log N) | 정렬 필요 | 절반씩 줄여가며 탐색 |
| **해시 테이블** | O(1) | 없음 | 해시 함수 사용 |

### 언제 이분 탐색을 사용할까?

* **정렬된 배열에서 값 찾기**
* **조건을 만족하는 최대/최소값 찾기** (파라메트릭 서치)
* **단조성을 가진 함수에서 최적값 찾기**

---

## 1️⃣1️⃣ 주의사항

### 📌 오버플로우 방지

큰 수를 다룰 때는 오버플로우를 주의해야 함:

```java
// 잘못된 예: 오버플로우 발생 가능
int mid = (left + right) / 2;

// 올바른 예: 오버플로우 방지
int mid = left + (right - left) / 2;
```

### 📌 경계 조건 처리

* `left <= right` vs `left < right`
* `mid + 1` vs `mid`
* 문제에 따라 다르게 처리해야 함

### 📌 단조성 확인

파라메트릭 서치의 경우 **단조성**이 있어야 함:

* 값이 작을수록 조건을 만족하기 쉬움 (또는 그 반대)
* 단조성이 없으면 이분 탐색 불가능

---

## 1️⃣2️⃣ 파라메트릭 서치의 핵심

### 조건 함수의 중요성

파라메트릭 서치에서 **조건 함수**가 핵심이다:

```java
// 조건 함수: mid 값이 조건을 만족하는가?
boolean canMake(long mid) {
    // 문제에 따라 구현
    // 예: 랜선 자르기에서 N개 이상 만들 수 있는가?
    long count = 0;
    for (long len : lengths) {
        count += len / mid;
    }
    return count >= N;
}
```

### 단조성 확인

조건 함수가 **단조성**을 가져야 함:

* `canMake(x)`가 true이면 `canMake(x-1)`도 true (또는 그 반대)
* 이 성질이 있어야 이분 탐색 가능

---

## ✨ 한 줄 요약

> 이분 탐색은 **"탐색 범위를 절반씩 줄여가며 효율적으로 탐색하는"** 알고리즘으로, O(log N) 시간에 정렬된 배열에서 값을 찾거나, 조건을 만족하는 최대/최소값을 찾을 수 있다.

