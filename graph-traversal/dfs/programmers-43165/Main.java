class Solution {
    /**
     * 방법 1: 간단한 버전 (가지치기 없음)
     * 구현이 간단하고, n ≤ 20이므로 충분히 빠름
     */
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
    
    static int dfs(int[] numbers, int target, int index, int sum) {
        // 종료 조건: 모든 숫자를 처리했을 때
        if (index == numbers.length) {
            return (sum == target) ? 1 : 0;
        }
        
        // 더하기 선택
        int add = dfs(numbers, target, index + 1, sum + numbers[index]);
        
        // 빼기 선택
        int subtract = dfs(numbers, target, index + 1, sum - numbers[index]);
        
        return add + subtract;
    }
    
    /**
     * 방법 2: 최적화 버전 (가지치기 포함)
     * 극단적인 케이스에서 유리하지만, 일반적으로는 큰 차이 없음
     * 
     * 가지치기 효용성:
     * - 효과적인 경우: 타겟이 매우 크거나 작은 경우, 숫자들이 큰 경우
     * - 효과가 제한적인 경우: 타겟이 숫자들의 합 범위 내에 있는 경우 (일반적인 케이스)
     * - 오버헤드: suffixSum 배열 생성 (O(n) 시간/공간), 각 노드에서 체크 (O(1))
     * 
     * 결론: n ≤ 20이므로 간단한 버전으로도 충분하지만, 
     *       극단적인 케이스를 고려한다면 가지치기 버전이 유리할 수 있음
     * 
     * 가지치기가 명확히 이득이 되는 구체적인 예시와 판단 기준은
     * 3.reasoning.md의 "7️⃣ 가지치기 최적화 판단 기준" 섹션을 참고하세요.
     */
    /*
    public int solution(int[] numbers, int target) {
        // 남은 숫자들의 합을 미리 계산 (가지치기 최적화)
        int[] suffixSum = new int[numbers.length + 1];
        suffixSum[numbers.length] = 0;
        for (int i = numbers.length - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + numbers[i];
        }
        
        return dfsWithPruning(numbers, target, 0, 0, suffixSum);
    }
    
    static int dfsWithPruning(int[] numbers, int target, int index, int sum, int[] suffixSum) {
        // 종료 조건: 모든 숫자를 처리했을 때
        if (index == numbers.length) {
            return (sum == target) ? 1 : 0;
        }
        
        // 가지치기: 남은 숫자들을 모두 더하거나 빼도 타겟에 도달할 수 없는 경우
        int maxSum = sum + suffixSum[index];
        int minSum = sum - suffixSum[index];
        
        if (target < minSum || target > maxSum) {
            return 0;  // 불가능한 경로 조기 종료
        }
        
        // 더하기 선택
        int add = dfsWithPruning(numbers, target, index + 1, sum + numbers[index], suffixSum);
        
        // 빼기 선택
        int subtract = dfsWithPruning(numbers, target, index + 1, sum - numbers[index], suffixSum);
        
        return add + subtract;
    }
    */
}
