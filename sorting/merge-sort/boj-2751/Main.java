import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static int[] temp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
        temp = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        // 병합 정렬 수행
        mergeSort(0, N - 1);
        
        // 결과 출력
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            answer.append(arr[i]).append('\n');
        }
        System.out.print(answer.toString());
    }
    
    // 병합 정렬: 분할정복 알고리즘
    static void mergeSort(int left, int right) {
        // Base Case: 더 이상 나눌 수 없는 경우 (원소가 1개 이하)
        if (left >= right) return;
        
        // Divide: 배열을 반으로 나눔
        int mid = (left + right) / 2;
        
        // Conquer: 각 부분을 재귀적으로 정렬
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        
        // Combine: 정렬된 두 부분을 병합
        merge(left, mid, right);
    }
    
    // 병합 함수: 정렬된 두 부분을 하나로 병합
    static void merge(int left, int mid, int right) {
        int i = left;      // 왼쪽 부분의 시작 인덱스
        int j = mid + 1;   // 오른쪽 부분의 시작 인덱스
        int k = left;      // 임시 배열의 인덱스
        
        // 두 부분을 비교하며 병합
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        
        // 남은 요소들 복사
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        
        // 임시 배열의 내용을 원본 배열로 복사
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
}

