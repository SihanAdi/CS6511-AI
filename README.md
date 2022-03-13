README
Sihan Adi
G30274634
First import the file, set landscape as a two-dimensional array, targets, and tiles as Hashmap, then divide the landscape into 4*4 variables according to the imported file and generate the value of each variable according to the type of tile (6 values, because EL has four different states), and then call the backtracking function. My variable ordering is horizontal, 0, 1, 2, 3. . .
In the backtracking function, determine whether the goal has been completed and return true if completed. MRV selects the variable with the least number of values and then loops through its values. The selection of the value order is through LCV. Choose the value that has the least effect on other variables, in this case, the value that covers the tile with the least number of digits. Then update the current state (including targets and tiles).
Then call the AC3 function, generate the arc queue, and then make a constraint judgment on the entire queue. For example, if the value of the variable x does not meet the constraints in the value range of the variable y, delete the value of the variable x. On the other hand, if x changes the range, then add all (k, x) to the queue.
If the return value of the AC3 function is true, then the backtracking function is called recursively. If it is false, the state is restored, and the next value of the variable is tried.
   
![image](https://user-images.githubusercontent.com/90162859/158077805-f3ab557c-6886-4543-a238-b4fd8a67aa3b.png)
