package helpers;

public class Sorting {
  public static void quickSort(int[] nums) {
    if (nums == null) {
      return;
    }
    _quickSort(nums, 0, nums.length - 1);
  }

  private static void _quickSort(int[] nums, int start, int end) {
    if (start < end) {
      int pivotIndex = _partition(nums, start, end);
      _quickSort(nums, start, pivotIndex - 1);
      _quickSort(nums, pivotIndex + 1, end);
    }
  }

  private static int _partition(int[] nums, int start, int end) {
    int pivot = nums[start];
    int left = start + 1;
    int right = end;
    while (left <= right) {
      if (nums[left] <= pivot) {
        left++;
      } else if (nums[right] > pivot) {
        right--;
      } else {
        _swap(nums, left, right);
        left++;
        right--;
      }
    }
    _swap(nums, start, right);
    return right;
  }

  private static void _swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}