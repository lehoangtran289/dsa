void main() {
    int[] arr = new int[]{2, 3, 1, 0};
    nextPermutation(arr);
    System.out.println(Arrays.toString(arr)); // 3 0 1 2
}

public void nextPermutation(int[] nums) {
    // Find pivot point, index where nums[i] < nums[i + 1] from the right
    int pivotIndex = nums.length - 2;
    while (pivotIndex >= 0 && nums[pivotIndex] >= nums[pivotIndex + 1]) {
        pivotIndex--;
    }

    // If such index found -> find smallest larger number in [pivot + 1, n - 1]
    if (pivotIndex >= 0) {
        int j = nums.length - 1;
        while (nums[j] <= nums[pivotIndex]) {
            j--;
        }
        swap(nums, pivotIndex, j);
    }

    // Sort the array [pivot + 1, n - 1] to achieve next lexicographical order
    reverse(nums, pivotIndex + 1);
}

private void reverse(int[] nums, int start) {
    int i = start, j = nums.length - 1;
    while (i < j) {
        swap(nums, i, j);
        i++;
        j--;
    }
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}