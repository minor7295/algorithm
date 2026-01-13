import java.util.*;

class Solution {
    /**
     * 메뉴 리뉴얼 문제 해결
     * 
     * 접근 방법: 백트래킹을 사용하여 각 주문에서 가능한 모든 조합을 생성하고,
     *           빈도를 카운트하여 가장 많이 주문된 조합을 찾는다.
     */
    public String[] solution(String[] orders, int[] course) {
        // 각 조합의 빈도를 카운트하는 Map
        Map<String, Integer> countMap = new HashMap<>();
        
        // 각 주문에서 가능한 모든 조합 생성 및 빈도 카운트
        for (String order : orders) {
            // 각 주문을 알파벳 순으로 정렬 (조합 생성 시 자동으로 정렬됨)
            char[] chars = order.toCharArray();
            Arrays.sort(chars);
            String sortedOrder = new String(chars);
            
            // 각 코스 크기별로 조합 생성
            for (int courseSize : course) {
                // 주문 길이가 코스 크기보다 작으면 조합 생성 불가
                if (sortedOrder.length() >= courseSize) {
                    generateCombinations(sortedOrder, courseSize, 0, new StringBuilder(), countMap);
                }
            }
        }
        
        // 각 코스 크기별로 최대 빈도 찾기
        List<String> result = new ArrayList<>();
        for (int courseSize : course) {
            int maxCount = 0;
            List<String> candidates = new ArrayList<>();
            
            // 해당 코스 크기의 조합 중 최대 빈도 찾기
            for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                String combination = entry.getKey();
                int count = entry.getValue();
                
                // 최소 2명 이상의 손님으로부터 주문된 조합만 유효
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
        
        // 결과를 사전 순으로 정렬
        Collections.sort(result);
        
        return result.toArray(new String[0]);
    }
    
    /**
     * 백트래킹을 사용하여 주문에서 가능한 모든 조합을 생성
     * 
     * @param order 주문 문자열 (알파벳 순으로 정렬됨)
     * @param courseSize 생성할 조합의 크기
     * @param start 현재 선택할 수 있는 시작 인덱스
     * @param current 현재까지 선택한 문자들
     * @param countMap 조합의 빈도를 카운트하는 Map
     */
    static void generateCombinations(String order, int courseSize, int start, StringBuilder current, Map<String, Integer> countMap) {
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
        
        // 각 문자를 선택하거나 선택하지 않는 두 가지 경우
        for (int i = start; i < order.length(); i++) {
            // 선택: 현재 문자를 조합에 추가
            current.append(order.charAt(i));
            
            // 재귀 호출: 다음 문자로 진행
            generateCombinations(order, courseSize, i + 1, current, countMap);
            
            // 선택 취소: 상태 되돌리기 (백트래킹의 핵심)
            current.setLength(current.length() - 1);
        }
    }
}

