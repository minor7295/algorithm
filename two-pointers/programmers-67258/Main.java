import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        // 전체 보석 종류 개수 파악
        Set<String> gemSet = new HashSet<>();
        for (String gem : gems) {
            gemSet.add(gem);
        }
        int totalTypes = gemSet.size();
        
        // 슬라이딩 윈도우 초기화
        Map<String, Integer> window = new HashMap<>();
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int[] answer = new int[2];
        
        // right 포인터로 구간 확장
        // 주의: 배열 인덱스는 0부터 시작하지만, 진열대 번호는 1부터 시작
        // 예: gems[0] = 진열대 1번, gems[2] = 진열대 3번
        for (int right = 0; right < gems.length; right++) {
            // 현재 보석을 윈도우에 추가
            window.put(gems[right], window.getOrDefault(gems[right], 0) + 1);
            
            // 모든 종류가 포함되었는지 확인
            while (window.size() == totalTypes) {
                // 현재 구간 길이 확인
                int currentLength = right - left + 1;
                if (currentLength < minLength) {
                    minLength = currentLength;
                    // 배열 인덱스(0-based)를 진열대 번호(1-based)로 변환
                    // 예: left=2, right=6 → 반환값 [3, 7]
                    answer[0] = left + 1;  // 진열대 시작 번호
                    answer[1] = right + 1; // 진열대 끝 번호
                }
                
                // left 포인터 이동하여 구간 축소
                window.put(gems[left], window.get(gems[left]) - 1);
                if (window.get(gems[left]) == 0) {
                    window.remove(gems[left]);
                }
                left++;
            }
        }
        
        return answer;
    }
}

