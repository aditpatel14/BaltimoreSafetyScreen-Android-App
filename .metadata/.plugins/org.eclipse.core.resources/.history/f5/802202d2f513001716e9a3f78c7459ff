import java.util.Arrays;

public class MergeSort {

	public static Arrest[] mergeSort(Arrest[] temp){
		if(temp.length == 1)
			return (Arrest[]) temp;
		int mid = temp.length/2;
		Arrest[] left = mergeSort(Arrays.copyOfRange(temp, 0, mid) );
		Arrest[] right = mergeSort(Arrays.copyOfRange(temp, mid, temp.length));	
		Arrest[] answer = mergeSortedArr((Arrest[]) left, (Arrest[]) right);
		return answer;
	}
	
	public static Arrest[] mergeSortedArr(Arrest[] left, Arrest[] right){
		int len = left.length + right.length;
		Arrest[] mergedArr = new Arrest[len];
		
		int i = left.length - 1;
		int j = right.length - 1;
		int index = mergedArr.length - 1;
		
		while(0 <= index){
			if(j < 0 || 0<=i && right[j].compareTo(left[i]) < 0) 
				mergedArr[index] = left[i--];
			else
				mergedArr[index] = right[j--];
			index--;
		}
		return mergedArr;		
	}
	
}
