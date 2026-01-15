import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 1. 중복 신고 제거 (Set 사용)
        // 같은 유저가 같은 유저를 여러 번 신고해도 1회로 처리
        Set<String> uniqueReports = new HashSet<>();
        for (String r : report) {
            uniqueReports.add(r);  // 중복 자동 제거
        }
        
        // 2. 신고 횟수 카운트 (Map 사용)
        // 각 유저가 신고당한 횟수를 세어야 함
        Map<String, Integer> reportCount = new HashMap<>();
        for (String r : uniqueReports) {
            String[] parts = r.split(" ");
            String reported = parts[1];  // 신고당한 유저
            
            // getOrDefault()를 사용하여 초기값 처리
            reportCount.put(reported, 
                reportCount.getOrDefault(reported, 0) + 1);
        }
        
        // 3. 정지된 유저 확인 (Set 사용)
        // k번 이상 신고된 유저는 게시판 이용이 정지됨
        Set<String> bannedUsers = new HashSet<>();
        for (Map.Entry<String, Integer> entry : reportCount.entrySet()) {
            if (entry.getValue() >= k) {
                bannedUsers.add(entry.getKey());
            }
        }
        
        // 4. 유저별 신고한 유저 목록 저장 (Map + Set 사용)
        // 각 유저가 신고한 유저 목록을 저장해야 함
        Map<String, Set<String>> userReports = new HashMap<>();
        for (String r : uniqueReports) {
            String[] parts = r.split(" ");
            String reporter = parts[0];   // 신고한 유저
            String reported = parts[1];   // 신고당한 유저
            
            // computeIfAbsent()를 사용하여 키가 없으면 새로운 Set 생성
            userReports.computeIfAbsent(reporter, key -> new HashSet<>())
                        .add(reported);
        }
        
        // 5. 메일 수 계산
        // 각 유저가 신고한 유저 중 정지된 유저의 수를 세어야 함
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            String user = id_list[i];
            // getOrDefault()를 사용하여 키가 없으면 빈 Set 반환
            Set<String> reported = userReports.getOrDefault(user, new HashSet<>());
            
            int mailCount = 0;
            for (String reportedUser : reported) {
                // 정지된 유저이면 메일 수 증가
                if (bannedUsers.contains(reportedUser)) {
                    mailCount++;
                }
            }
            answer[i] = mailCount;
        }
        
        return answer;
    }
}
