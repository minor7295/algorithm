import java.util.*;

class Solution {
    /**
     * 다단계 칫솔 판매
     * 
     * 해시맵과 재귀를 활용한 트리 구조 이익 분배 문제
     * 
     * 시간 복잡도: O(N + M × H)
     * - N: enroll 길이 (≤ 10,000)
     * - M: seller 길이 (≤ 100,000)
     * - H: 조직 깊이 (최대 약 10,000)
     * 
     * 공간 복잡도: O(N + H)
     * - N: 조직 구조 맵과 이익 집계 맵
     * - H: 재귀 호출 스택
     */
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // 1. 조직 구조 저장 (해시맵 사용)
        // 각 판매원의 추천인을 저장하여 O(1) 평균 시간에 조회 가능
        Map<String, String> parentMap = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            parentMap.put(enroll[i], referral[i]);
        }
        
        // 2. 이익 집계 맵 초기화
        // 각 판매원의 이익을 누적하여 집계
        Map<String, Integer> profitMap = new HashMap<>();
        for (String name : enroll) {
            profitMap.put(name, 0);
        }
        
        // 3. 각 판매에 대해 이익 분배
        for (int i = 0; i < seller.length; i++) {
            int profit = amount[i] * 100;  // 칫솔 한 개당 100원
            distribute(parentMap, profitMap, seller[i], profit);
        }
        
        // 4. enroll 순서대로 이익금을 배열에 담아 반환
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = profitMap.getOrDefault(enroll[i], 0);
        }
        
        return answer;
    }
    
    /**
     * 재귀적으로 이익을 분배하는 함수
     * 
     * @param parentMap 조직 구조 맵 (판매원 → 추천인)
     * @param profitMap 이익 집계 맵 (판매원 → 이익금)
     * @param seller 현재 판매원
     * @param profit 분배할 이익금
     */
    static void distribute(Map<String, String> parentMap, 
                          Map<String, Integer> profitMap,
                          String seller, int profit) {
        // 자신이 90% 가짐
        int myProfit = profit - profit / 10;
        profitMap.put(seller, 
            profitMap.getOrDefault(seller, 0) + myProfit);
        
        // 10%를 추천인에게 배분
        int commission = profit / 10;  // 자동 절사 (정수 나눗셈)
        
        // 1원 미만이면 분배하지 않음
        if (commission < 1) {
            return;
        }
        
        // 추천인 조회
        String parent = parentMap.get(seller);
        
        // 추천인이 없으면 종료 (center에 도달)
        if (parent == null || parent.equals("-")) {
            return;
        }
        
        // 재귀 호출: 추천인에게 이익 분배
        distribute(parentMap, profitMap, parent, commission);
    }
}

