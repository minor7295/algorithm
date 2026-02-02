import java.util.*;

class Solution {
    /**
     * 메뉴 리뉴얼
     * 
     * 백트래킹을 활용한 조합 생성 및 빈도 카운트 문제
     * 
     * 시간 복잡도: O(2^M × N × C × M)
     * - M: 주문의 최대 길이 (≤ 10)
     * - N: 주문 개수 (≤ 20)
     * - C: course 배열 크기 (≤ 10)
     * 
     * 공간 복잡도: O(2^M × N × M)
     * - 조합 저장 공간이 지배적
     */
    public String[] solution(String[] orders, int[] course) {
        // 1. 빈도 카운트를 위한 Map 초기화
        Map<String, Integer> countMap = new HashMap<>();
        
        // 2. 각 주문에서 조합 생성 및 빈도 카운트
        for (String order : orders) {
            // 알파벳 순으로 정렬
            char[] chars = order.toCharArray();
            Arrays.sort(chars);
            String sortedOrder = new String(chars);
            
            // 각 코스 크기에 대해 조합 생성
            for (int courseSize : course) {
                if (sortedOrder.length() >= courseSize) {
                    generateCombinations(sortedOrder, courseSize, 0, 
                                        new StringBuilder(), countMap);
                }
            }
        }
        
        // 3. 각 코스 크기별로 최대 빈도 조합 선택
        List<String> result = new ArrayList<>();
        for (int courseSize : course) {
            int maxCount = 0;
            List<String> candidates = new ArrayList<>();
            
            for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                String combination = entry.getKey();
                int count = entry.getValue();
                
                if (combination.length() == courseSize && count >= 2) {
                    if (count > maxCount) {
                        maxCount = count;
                        candidates.clear();
                        candidates.add(combination);
                    } else if (count == maxCount) {
                        candidates.add(combination);
                    }
                }
            }
            
            result.addAll(candidates);
        }
        
        // 4. 알파벳 순으로 정렬
        Collections.sort(result);
        return result.toArray(new String[0]);
    }
    
    /**
     * 백트래킹으로 조합 생성 및 빈도 카운트
     * 
     * @param order 원본 문자열 (알파벳 순으로 정렬됨)
     * @param courseSize 목표 조합 크기
     * @param start 현재 선택할 수 있는 시작 위치
     * @param current 현재까지 선택한 문자들
     * @param countMap 조합 빈도를 저장할 Map
     */
    static void generateCombinations(String order, int courseSize, int start, 
                                     StringBuilder current, Map<String, Integer> countMap) {
        // 종료 조건: 원하는 크기의 조합을 만들었을 때
        if (current.length() == courseSize) {
            String combination = current.toString();
            countMap.put(combination, countMap.getOrDefault(combination, 0) + 1);
            return;
        }
        
        // 가지치기: 남은 문자로는 원하는 크기를 만들 수 없을 때
        if (order.length() - start < courseSize - current.length()) {
            return;
        }
        
        // 선택지 탐색
        for (int i = start; i < order.length(); i++) {
            current.append(order.charAt(i));  // 선택
            generateCombinations(order, courseSize, i + 1, current, countMap);
            current.setLength(current.length() - 1);  // 상태 되돌리기
        }
    }
}

