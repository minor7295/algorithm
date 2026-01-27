import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 1. 장르별 총 재생 횟수 계산
        Map<String, Integer> genreTotalPlays = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            genreTotalPlays.put(genres[i], 
                genreTotalPlays.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        // 2. 장르별 노래 정보 저장 (고유 번호, 재생 횟수)
        Map<String, List<int[]>> genreSongs = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            if (!genreSongs.containsKey(genres[i])) {
                genreSongs.put(genres[i], new ArrayList<>());
            }
            genreSongs.get(genres[i]).add(new int[]{i, plays[i]});
        }
        
        // 3. 장르를 총 재생 횟수 순으로 정렬
        List<String> sortedGenres = new ArrayList<>(genreTotalPlays.keySet());
        sortedGenres.sort((a, b) -> 
            genreTotalPlays.get(b) - genreTotalPlays.get(a));
        
        // 4. 각 장르에서 최대 2개씩 선택
        List<Integer> answer = new ArrayList<>();
        for (String genre : sortedGenres) {
            List<int[]> songs = genreSongs.get(genre);
            
            // 장르 내 노래를 재생 횟수 순으로 정렬 (같으면 고유 번호 오름차순)
            songs.sort((a, b) -> {
                if (a[1] != b[1]) {
                    return b[1] - a[1];  // 재생 횟수 내림차순
                }
                return a[0] - b[0];  // 고유 번호 오름차순
            });
            
            // 최대 2개 선택
            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                answer.add(songs.get(i)[0]);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}

