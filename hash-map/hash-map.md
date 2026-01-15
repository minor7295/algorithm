# 해시맵(HashMap) 정리

## 1️⃣ 해시맵이란?

### 🎯 해시맵의 정의

**해시맵(HashMap)**은 키(Key)와 값(Value)의 쌍으로 데이터를 저장하는 자료구조입니다.

#### 기본 개념

**키-값 쌍(Key-Value Pair)**을 저장하고, 키를 통해 값을 빠르게 조회할 수 있습니다.

**예시**:
```java
Map<String, Integer> map = new HashMap<>();
map.put("apple", 3);    // 키: "apple", 값: 3
map.put("banana", 5);   // 키: "banana", 값: 5
map.get("apple");       // 3 반환
```

#### 왜 해시맵을 사용하는가?

**배열의 한계**: 정수 인덱스만 사용 가능
```java
int[] arr = new int[100];
arr[0] = 10;        // ✅ 가능: 정수 인덱스
arr["muzi"] = 10;  // ❌ 불가능: 문자열 인덱스 사용 불가
```

**해시맵의 해결책**: 다양한 타입의 키를 사용 가능
```java
Map<String, Integer> reportCount = new HashMap<>();
reportCount.put("muzi", 1);   // 문자열 키 사용 가능
reportCount.put("frodo", 2);  // 문자열 키 사용 가능
reportCount.get("muzi");      // O(1) 시간에 조회
```

**배열 vs 해시맵 비교**:

| 자료구조 | 검색 시간 | 키 타입 | 사용 예시 |
|---------|---------|--------|---------|
| 배열 | O(1) | 정수만 | `arr[0]`, `arr[5]` |
| 해시맵 | O(1) 평균 | 모든 타입 | `map.get("muzi")`, `map.get(userId)` |

---

## 2️⃣ 해시맵의 핵심 개념: 배열 기반 자료구조

### 💡 핵심 통찰: 해시맵은 내부적으로 배열을 사용합니다

**해시맵의 본질**: **배열 위에 해시 함수를 씌운 것**

해시맵이 다양한 타입의 키를 사용하면서도 O(1) 시간에 조회할 수 있는 이유는, 내부적으로 **배열(버킷 배열)**을 사용하고, **해시 함수**가 키를 배열 인덱스로 변환하기 때문입니다.

#### 핵심 통찰

**해시맵 = 배열 + 해시 함수**

```java
// 해시맵의 내부 구조 (간소화된 표현)
class HashMap<K, V> {
    Node<K, V>[] buckets;  // ← 이것이 배열입니다!
    int capacity;          // 배열의 크기
    int size;              // 저장된 원소 개수
}

class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;  // 체이닝을 위한 연결 리스트
}
```

**해시맵은 내부적으로 `Node[]` 배열을 사용**합니다:
- 각 배열 요소를 **버킷(Bucket)**이라고 부름
- 해시 함수가 계산한 인덱스에 해당하는 버킷에 값 저장
- 충돌이 발생하면 같은 버킷에 연결 리스트로 추가 (체이닝)

#### 배열과의 비교

**일반 배열**:
```java
int[] arr = new int[16];
arr[5] = 10;        // 인덱스 5에 직접 접근
int value = arr[5]; // O(1) 시간에 조회
```

**해시맵 (내부적으로는 배열)**:
```java
HashMap<String, Integer> map = new HashMap<>();
// 내부적으로는:
// Node[] buckets = new Node[16];
// buckets[hash("muzi") % 16] = new Node("muzi", 1);

map.put("muzi", 1);  // 내부: buckets[9]에 저장
map.get("muzi");      // 내부: buckets[9]에서 조회 → O(1) 평균
```

**차이점**:
- **일반 배열**: 인덱스를 직접 지정 (`arr[5]`)
- **해시맵**: 해시 함수로 인덱스를 계산 (`buckets[hash(key) % capacity]`)

**공통점**:
- 둘 다 **배열을 기반**으로 함
- 둘 다 **O(1) 시간에 접근** 가능 (해시맵은 평균적으로)

---

## 3️⃣ 해시맵의 동작 원리: 배열 인덱스 계산

### 해시 함수의 역할: 키를 배열 인덱스로 변환

해시맵이 O(1) 시간에 데이터를 조회할 수 있는 핵심은 **해시 함수가 키를 배열 인덱스로 변환**하는 것입니다.

#### 동작 과정 (3단계)

```
1단계: 키 → 해시 값
2단계: 해시 값 → 배열 인덱스
3단계: 배열 인덱스로 값 저장/조회
```

---

### 1단계: 키를 해시 값으로 변환

**해시 함수**는 키를 정수(해시 값)로 변환하는 함수입니다.

```
"muzi" → 해시 함수 → 12345 (해시 값)
"frodo" → 해시 함수 → 67890 (해시 값)
"apeach" → 해시 함수 → 23456 (해시 값)
```

**Java의 String 해시 함수**:
```java
String key = "muzi";
int hashCode = key.hashCode();  // 해시 값 계산
// 예: hashCode = 12345
```

**해시 함수의 특징**:
* **결정적(Deterministic)**: 같은 키는 항상 같은 해시 값을 반환
* **빠른 계산**: O(1) 시간에 계산 가능
* **균등 분포**: 가능한 한 고르게 분포시켜 충돌 최소화

**Java String의 해시 함수 구현**:
```java
public int hashCode() {
    int h = 0;
    for (int i = 0; i < length(); i++) {
        h = 31 * h + charAt(i);
    }
    return h;
}
```

**예시 계산**:
```
"muzi"의 해시 값 계산:
h = 0
h = 31 * 0 + 'm' = 109
h = 31 * 109 + 'u' = 3388
h = 31 * 3388 + 'z' = 105029
h = 31 * 105029 + 'i' = 3255990

최종 해시 값: 3255990
```

---

### 2단계: 해시 값을 배열 인덱스로 변환

해시 값은 보통 매우 큰 정수이므로, **배열 크기로 나머지 연산**하여 인덱스로 변환합니다:

```java
int hashCode = key.hashCode();        // 예: 12345
int bucketIndex = hashCode % capacity; // 예: 12345 % 16 = 9
```

**예시** (배열 크기 = 16):
```
"muzi" → hashCode = 12345 → 12345 % 16 = 9 → buckets[9]에 저장
"frodo" → hashCode = 67890 → 67890 % 16 = 2 → buckets[2]에 저장
"apeach" → hashCode = 23456 → 23456 % 16 = 0 → buckets[0]에 저장
```

**배열 인덱스로 변환** (배열 크기 = 16):
```
3255990 % 16 = 6
→ buckets[6]에 저장
```

---

### 3단계: 배열 인덱스로 값 저장 및 조회

**내부적으로는 배열에 직접 접근**합니다:

**저장**:
```java
map.put("muzi", 1);
// 내부 동작:
// 1. "muzi" → hashCode() → 12345
// 2. 12345 % 16 = 9
// 3. buckets[9] = new Node("muzi", 1)
```

**조회**:
```java
map.get("muzi");
// 내부 동작:
// 1. "muzi" → hashCode() → 12345
// 2. 12345 % 16 = 9
// 3. buckets[9]에서 조회 → Node("muzi", 1).value 반환
```

➡️ **배열의 직접 접근 장점을 활용하면서도 다양한 키 타입을 지원!**

---

## 4️⃣ 충돌 처리: 같은 배열 인덱스에 여러 값 저장

### 충돌이란?

**충돌(Collision)**: 서로 다른 키가 같은 해시 값을 가지는 경우

**예시**:
```
"muzi" → hashCode = 12345 → 12345 % 16 = 9
"frodo" → hashCode = 67890 → 67890 % 16 = 2
"apeach" → hashCode = 23456 → 23456 % 16 = 0
"neo" → hashCode = 98765 → 98765 % 16 = 13

// 충돌 발생 시:
"user1" → hashCode = 12345 → 12345 % 16 = 9  // "muzi"와 같은 버킷!
```

### 해결 방법: 체이닝 (Chaining)

**Java HashMap은 체이닝 방식 사용**

**체이닝**: 같은 버킷에 여러 키-값 쌍을 연결 리스트로 저장

```
buckets[9] → Node("muzi", 1) → Node("user1", 5) → null
```

**체이닝의 내부 구조**:
```java
// 충돌 발생 시:
buckets[9] = new Node("muzi", 1);
buckets[9].next = new Node("user1", 5);  // 연결 리스트로 추가

// 조회 시:
Node node = buckets[9];
while (node != null) {
    if (node.key.equals("muzi")) {
        return node.value;  // 찾음!
    }
    node = node.next;
}
```

---

### 💡 왜 연결 리스트를 사용하는가?

**간단한 예시로 이해하기**

**문제 상황**: 1000개의 데이터를 저장해야 하는데, 배열 크기는 16으로 고정

**만약 배열만 사용한다면**:
```java
// 충돌 발생 시 문제!
buckets[9] = "muzi";      // 이미 저장됨
buckets[9] = "user1";     // 덮어쓰기! "muzi" 손실 ❌
```

**연결 리스트를 사용하면**:
```java
// 같은 버킷에 여러 값을 저장 가능
buckets[9] → Node("muzi") → Node("user1") → Node("user2") → null
```

**핵심 이유**:
1. **충돌 처리**: 서로 다른 키가 같은 배열 인덱스로 변환될 때, 같은 버킷에 여러 값을 저장할 수 있음
2. **메모리 효율**: 배열 크기는 작게 유지(16개)하면서도 많은 데이터(1000개)를 저장 가능

**예시**:
```
배열 크기 = 16 (고정)
저장할 데이터 = 1000개

연결 리스트 사용:
- 배열: 16개만 할당
- 노드: 실제 데이터 1000개만 생성
- 총 메모리: 작음 ✅

배열만 사용:
- 배열: 1000개 할당 필요
- 사용하지 않는 인덱스도 메모리 할당
- 총 메모리: 큼 ❌
```

---

**충돌이 많을수록**:
- 같은 버킷의 연결 리스트가 길어짐
- 조회 시간이 O(1)에서 O(N)으로 증가할 수 있음
- **좋은 해시 함수**는 충돌을 최소화하여 평균 O(1) 유지

**다른 해결 방법: 개방 주소법 (Open Addressing)**
```
buckets[9] = Node("muzi", 1)
buckets[10] = Node("user1", 5)  // 다음 빈 버킷에 저장
```
- 연결 리스트 대신 다른 빈 버킷을 찾아 저장
- 배열 크기를 키 개수보다 크게 유지해야 함 (메모리 낭비)

---

## 5️⃣ 해시맵의 시간 복잡도

### 기본 연산

| 연산 | 시간 복잡도 | 내부 동작 |
|------|-----------|---------|
| `put(key, value)` | O(1) 평균 | 해시 계산 → 배열 인덱스 → 배열 접근 |
| `get(key)` | O(1) 평균 | 해시 계산 → 배열 인덱스 → 배열 접근 |
| `containsKey(key)` | O(1) 평균 | 해시 계산 → 배열 인덱스 → 배열 접근 |
| `remove(key)` | O(1) 평균 | 해시 계산 → 배열 인덱스 → 배열 접근 |
| `size()` | O(1) | 변수 조회 |

**주의**: 최악의 경우 O(N)이 될 수 있지만, 평균적으로 O(1)입니다.
- 최악의 경우: 모든 키가 같은 버킷에 저장되어 연결 리스트가 길어짐
- 평균적인 경우: 해시 함수가 고르게 분포시켜 대부분의 버킷에 1-2개만 저장

---

## 6️⃣ 해시맵 구현 패턴

### 패턴 1: 카운팅 (Counting)

**사용 시나리오**: 각 항목의 출현 횟수를 세는 경우

```java
Map<String, Integer> count = new HashMap<>();

// 배열 순회하며 카운트
for (String item : items) {
    count.put(item, count.getOrDefault(item, 0) + 1);
}
```

**실제 예시**: 신고 횟수 카운트
```java
Map<String, Integer> reportCount = new HashMap<>();

// 신고 처리
for (String report : reports) {
    String[] parts = report.split(" ");
    String reported = parts[1];  // 신고당한 유저
    
    reportCount.put(reported, 
        reportCount.getOrDefault(reported, 0) + 1);
}
```

### 패턴 2: 그룹핑 (Grouping)

**사용 시나리오**: 키별로 여러 값을 그룹화하는 경우

```java
Map<String, List<String>> groups = new HashMap<>();

for (String item : items) {
    String key = getKey(item);
    groups.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
}
```

**실제 예시**: 유저별 신고한 유저 목록
```java
Map<String, Set<String>> userReports = new HashMap<>();

// 중복 신고 제거를 위해 Set 사용
for (String report : reports) {
    String[] parts = report.split(" ");
    String reporter = parts[0];   // 신고한 유저
    String reported = parts[1];   // 신고당한 유저
    
    userReports.computeIfAbsent(reporter, k -> new HashSet<>())
                .add(reported);
}
```

### 패턴 3: 인덱싱 (Indexing)

**사용 시나리오**: 키로 빠르게 값을 조회해야 하는 경우

```java
Map<String, Integer> index = new HashMap<>();

// 인덱스 생성
for (int i = 0; i < items.length; i++) {
    index.put(items[i], i);
}

// 빠른 조회
int position = index.get("target");  // O(1)
```

**실제 예시**: 유저 ID를 인덱스로 변환
```java
Map<String, Integer> userIndex = new HashMap<>();

// 유저 ID → 인덱스 매핑
for (int i = 0; i < id_list.length; i++) {
    userIndex.put(id_list[i], i);
}

// 결과 배열에 값 저장
int[] answer = new int[id_list.length];
answer[userIndex.get("muzi")] = 2;  // "muzi"의 메일 수
```

### 패턴 4: 중복 제거 (Deduplication)

**사용 시나리오**: 중복된 항목을 제거해야 하는 경우

```java
Set<String> unique = new HashSet<>();

for (String item : items) {
    unique.add(item);  // 중복 자동 제거
}
```

**실제 예시**: 중복 신고 제거
```java
Set<String> uniqueReports = new HashSet<>();

for (String report : reports) {
    uniqueReports.add(report);  // "muzi frodo" 중복 제거
}
```

---

## 7️⃣ 해시맵 vs 다른 자료구조: 언제 무엇을 사용할까?

### 📊 각 자료구조의 적합한 상황

#### 1️⃣ HashMap: 키-값 쌍을 빠르게 저장/조회할 때

**적합한 상황**:
- 문자열, 객체 등 다양한 타입의 키 사용
- 순서가 중요하지 않음
- 빠른 조회가 필요 (O(1) 평균)

**예시**:
```java
// 유저별 신고 횟수 저장
Map<String, Integer> reportCount = new HashMap<>();
reportCount.put("muzi", 1);
reportCount.get("muzi");  // O(1) - 빠른 조회
```

**특징**:
- 검색/삽입: O(1) 평균
- 순서 보장: ❌
- 키 타입: 모든 타입

---

#### 2️⃣ TreeMap: 키를 정렬된 순서로 유지해야 할 때

**적합한 상황**:
- 키를 정렬된 순서로 유지해야 함
- 범위 검색이 필요 (예: "a"부터 "m"까지)
- 키가 비교 가능한 타입 (String, Integer 등)

**예시**:
```java
// 점수별 등수 저장 (점수 순으로 정렬)
Map<Integer, String> ranking = new TreeMap<>();
ranking.put(95, "muzi");
ranking.put(87, "frodo");
ranking.put(92, "apeach");
// 자동으로 점수 순으로 정렬: {87=frodo, 92=apeach, 95=muzi}

// 범위 검색
ranking.subMap(90, 100);  // 90점 이상 100점 미만
```

**특징**:
- 검색/삽입: O(log N)
- 순서 보장: ✅ (정렬된 순서)
- 키 타입: 비교 가능한 타입만

---

#### 3️⃣ ArrayList: 인덱스로 접근하고 순서가 중요할 때

**적합한 상황**:
- 정수 인덱스로 접근 (0, 1, 2, ...)
- 순서가 중요함 (삽입 순서 유지)
- 중간에 삽입/삭제가 적음

**예시**:
```java
// 학생 명단 (등록 순서대로)
List<String> students = new ArrayList<>();
students.add("muzi");    // 인덱스 0
students.add("frodo");   // 인덱스 1
students.add("apeach"); // 인덱스 2

students.get(0);  // "muzi" - 인덱스로 직접 접근
```

**특징**:
- 검색: O(N) - 인덱스로 접근하면 O(1)
- 삽입: O(1) 평균 (끝에 추가)
- 순서 보장: ✅ (삽입 순서)
- 키 타입: 정수 인덱스만

---

#### 4️⃣ HashSet: 중복을 제거하고 존재 여부만 확인할 때

**적합한 상황**:
- 중복 제거
- 빠른 존재 여부 확인 (contains)
- 순서가 중요하지 않음

**예시**:
```java
// 중복 신고 제거
Set<String> uniqueReports = new HashSet<>();
uniqueReports.add("muzi frodo");
uniqueReports.add("muzi frodo");  // 중복 자동 제거
uniqueReports.add("apeach frodo");

uniqueReports.contains("muzi frodo");  // O(1) - 빠른 확인
```

**특징**:
- 검색/삽입: O(1) 평균
- 순서 보장: ❌
- 키 타입: 모든 타입

---

### 🔍 상황별 선택 가이드

#### 상황 1: 유저별 신고 횟수 저장
```java
// ✅ HashMap 사용
Map<String, Integer> reportCount = new HashMap<>();
// 이유: 문자열 키, 순서 불필요, 빠른 조회 필요
```

#### 상황 2: 점수별 등수 (점수 순으로 정렬)
```java
// ✅ TreeMap 사용
Map<Integer, String> ranking = new TreeMap<>();
// 이유: 점수 순으로 정렬 필요, 범위 검색 가능
```

#### 상황 3: 학생 명단 (등록 순서대로)
```java
// ✅ ArrayList 사용
List<String> students = new ArrayList<>();
// 이유: 인덱스로 접근, 삽입 순서 유지 필요
```

#### 상황 4: 중복 신고 제거
```java
// ✅ HashSet 사용
Set<String> uniqueReports = new HashSet<>();
// 이유: 중복 제거, 빠른 존재 여부 확인
```

---

### 📊 빠른 비교표

| 상황 | 추천 자료구조 | 이유 |
|------|------------|------|
| 문자열 키로 빠른 조회 | **HashMap** | O(1) 평균, 순서 불필요 |
| 키를 정렬된 순서로 유지 | **TreeMap** | 자동 정렬, 범위 검색 가능 |
| 인덱스로 접근, 순서 중요 | **ArrayList** | 인덱스 직접 접근, 삽입 순서 유지 |
| 중복 제거, 존재 여부 확인 | **HashSet** | 중복 자동 제거, O(1) 확인 |

---

## 8️⃣ Java HashMap 주요 메서드

### 기본 메서드

```java
Map<String, Integer> map = new HashMap<>();

// 추가/수정
map.put("key", 10);              // 키-값 쌍 추가
map.put("key", 20);              // 기존 값 수정 (10 → 20)

// 조회
int value = map.get("key");      // 값 조회 (없으면 null)
int value = map.getOrDefault("key", 0);  // 기본값 지정

// 존재 여부 확인
boolean exists = map.containsKey("key");  // 키 존재 여부
boolean hasValue = map.containsValue(10); // 값 존재 여부

// 삭제
map.remove("key");               // 키-값 쌍 삭제

// 크기
int size = map.size();           // 저장된 원소 개수
boolean isEmpty = map.isEmpty(); // 비어있는지 확인

// 초기화
map.clear();                     // 모든 원소 삭제
```

**내부 동작**: 모든 메서드는 해시 함수로 배열 인덱스를 계산한 후 배열에 접근합니다.

### 고급 메서드

```java
// computeIfAbsent: 키가 없으면 값 생성
map.computeIfAbsent("key", k -> new ArrayList<>())
   .add("value");

// computeIfPresent: 키가 있으면 값 수정
map.computeIfPresent("key", (k, v) -> v + 1);

// merge: 키가 있으면 병합, 없으면 추가
map.merge("key", 1, (old, newVal) -> old + newVal);

// forEach: 모든 원소 순회
map.forEach((key, value) -> {
    System.out.println(key + ": " + value);
});
```

---

## 9️⃣ 해시맵 사용 시 주의사항

### 1. null 처리

**문제**: `get()` 메서드는 키가 없으면 `null`을 반환

```java
Map<String, Integer> map = new HashMap<>();
Integer value = map.get("nonexistent");  // null 반환

// ❌ 잘못된 코드
int count = map.get("nonexistent");  // NullPointerException 발생!

// ✅ 올바른 코드
int count = map.getOrDefault("nonexistent", 0);  // 기본값 0
```

### 2. 키 타입 선택

**문제**: 가변 객체를 키로 사용하면 문제 발생

```java
// ❌ 잘못된 코드
Map<List<String>, Integer> map = new HashMap<>();
List<String> key = new ArrayList<>();
map.put(key, 10);
key.add("new");  // 키 변경 → 해시 값 변경 → 조회 불가!

// ✅ 올바른 코드
Map<String, Integer> map = new HashMap<>();
String key = "immutable";  // 불변 객체 사용
map.put(key, 10);
```

**이유**: 해시 함수는 키의 값에 따라 해시 값을 계산하므로, 키가 변경되면 배열 인덱스도 변경되어 조회 불가능

### 3. 초기 용량 설정

**문제**: 대량의 데이터를 저장할 때 재해싱 비용 발생

```java
// ❌ 비효율적
Map<String, Integer> map = new HashMap<>();  // 기본 용량 16

// ✅ 효율적 (예상 크기 지정)
Map<String, Integer> map = new HashMap<>(1000);  // 초기 용량 1000
```

**이유**: 배열 크기가 부족하면 재할당 및 재해싱이 발생하여 성능 저하

---

## 🔟 해시맵 활용 문제 유형

### 유형 1: 카운팅 문제

**특징**: 각 항목의 출현 횟수를 세는 문제

**예시**:
- 신고 횟수 카운트
- 단어 빈도수 계산
- 숫자 출현 횟수

**패턴**:
```java
Map<String, Integer> count = new HashMap<>();
for (String item : items) {
    count.put(item, count.getOrDefault(item, 0) + 1);
}
```

### 유형 2: 그룹핑 문제

**특징**: 키별로 여러 값을 그룹화하는 문제

**예시**:
- 유저별 신고한 유저 목록
- 카테고리별 상품 목록
- 날짜별 이벤트 목록

**패턴**:
```java
Map<String, List<String>> groups = new HashMap<>();
for (String item : items) {
    groups.computeIfAbsent(key, k -> new ArrayList<>())
          .add(item);
}
```

### 유형 3: 인덱싱 문제

**특징**: 키로 빠르게 값을 조회해야 하는 문제

**예시**:
- 유저 ID → 인덱스 매핑
- 상품명 → 가격 조회
- 단어 → 번역 조회

**패턴**:
```java
Map<String, Integer> index = new HashMap<>();
for (int i = 0; i < items.length; i++) {
    index.put(items[i], i);
}
```

### 유형 4: 중복 제거 문제

**특징**: 중복된 항목을 제거해야 하는 문제

**예시**:
- 중복 신고 제거
- 고유한 항목 추출
- 방문한 노드 체크

**패턴**:
```java
Set<String> unique = new HashSet<>();
for (String item : items) {
    unique.add(item);  // 중복 자동 제거
}
```

---

## 1️⃣1️⃣ 정리

해시맵은 **배열을 기반으로 한 자료구조**입니다.

### ✅ 핵심 요약

1. **본질**: 해시맵은 내부적으로 배열(버킷 배열)을 사용
2. **해시 함수의 역할**: 다양한 타입의 키를 배열 인덱스로 변환
3. **동작 원리**: 키 → 해시 값 → 배열 인덱스 → 배열 접근
4. **시간 복잡도**: 평균 O(1), 최악 O(N)
5. **공간 복잡도**: O(N)

### 🎯 핵심 통찰

**해시맵 = 배열 + 해시 함수**

- 배열의 직접 접근 장점(O(1))을 활용
- 해시 함수로 다양한 키 타입 지원
- 충돌 발생 시 체이닝으로 해결

### 🎯 실전 팁

1. **카운팅**: `getOrDefault()` 사용하여 초기값 처리
2. **그룹핑**: `computeIfAbsent()` 사용하여 리스트/셋 생성
3. **null 처리**: `getOrDefault()` 또는 `containsKey()` 사용
4. **중복 제거**: `HashSet` 사용
5. **초기 용량**: 예상 크기를 지정하여 재해싱 비용 최소화

### 📚 학습 순서

1. **배열 이해**: 배열의 직접 접근 원리 이해
2. **해시 함수 이해**: 키를 배열 인덱스로 변환하는 방법
3. **기본 사용법**: `put()`, `get()`, `containsKey()`
4. **카운팅 패턴**: `getOrDefault()` 활용
5. **그룹핑 패턴**: `computeIfAbsent()` 활용
6. **복합 문제**: 여러 패턴 조합

**해시맵은 배열의 효율성과 유연한 키 타입 지원을 결합한 강력한 자료구조입니다!**
