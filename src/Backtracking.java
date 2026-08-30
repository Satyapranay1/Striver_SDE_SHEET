//Permutations of a String
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    backtrack(ans,temp,nums,0);
    return ans;
}

public void backtrack(List<List<Integer>> ans,List<Integer> temp,int[] nums,int idx){
    if (temp.size() == nums.length){
        ans.add(new ArrayList<>(temp));
    }

    for (int i = 0; i < nums.length; i++){
        if (temp.contains(nums[i])) continue;
        temp.add(nums[i]);
        backtrack(ans,temp,nums,i);
        temp.removeLast();
    }
}

//N - Queens
public List<List<String>> solveNQueens(int n) {
    List<List<String>> ans = new ArrayList<>();
    char[][] board = new char[n][n];
    for (int i = 0; i < n; i++){
        Arrays.fill(board[i],'.');
    }
    solve(0,board,ans,n);
    return ans;
}

public void solve(int col,char[][] board,List<List<String>> ans,int n){
    if (col == n){
        List<String> temp = new ArrayList<>();
        for (char[] out : board){
            temp.add(new String(out));
        }
        ans.add(temp);
        return;
    }

    for (int row = 0; row < n; row++){
        if (isSafe(board,row,col)){
            board[row][col] = 'Q';
            solve(col + 1,board,ans,n);
            board[row][col] = '.';
        }
    }
}

public boolean isSafe(char[][] board,int row,int col){
    for (int i = 0; i < col; i++){
        if (board[row][i] == 'Q') return false;
    }

    for (int i = row,j = col; i >= 0 && j >= 0; i--,j--){
        if (board[i][j] == 'Q') return false;
    }

    for (int i = row,j = col; i < board.length && j >= 0; i++,j--){
        if (board[i][j] == 'Q') return false;
    }

    return true;
}

//Sudoku Solver
public void solveSudoku(char[][] board) {
    solve(board);
}

public boolean solve(char[][] board){
    for (int i = 0; i < 9; i++){
        for (int j = 0; j < 9; j++){
            if (board[i][j] == '.'){
                for (char c = '1'; c <= '9'; c++){
                    if (isValid(board,i,j,c)){
                        board[i][j] = c;
                        if (solve(board)){
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
    }
    return true;
}

public boolean isValid(char[][] board,int row,int col,char c){
    //Row
    for (int i = 0; i < 9; i++){
        if (board[row][i] == c) return false;
        if (board[i][col] == c) return false;
        if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
    }
    return true;
}

//M - coloring Problem
boolean graphColoring(int v, int[][] edges, int m) {
    int[] color = new int[v];
    return backtrack(edges,color,0,v,m);
}

public boolean backtrack(int[][] edges,int[] color,int idx,int v,int m){
    if (idx == v){
        return true;//All Vertices are colored
    }

    for (int c = 1; c <= m; c++)
        if (isSafe(edges, color, c, idx)) {
            color[idx] = c;
            if (backtrack(edges, color, idx + 1, v, m)) {
                return true;
            }
            color[idx] = 0;
        }

    return false;
}

public boolean isSafe(int[][] edges,int[] color,int c,int node){
    for (int[] edge : edges) {
        if (edge[0] == node && color[edge[1]] == c) return false;
        if (edge[1] == node && color[edge[0]] == c) return false;
    }
    return true;
}

//Rat in a Maze
public ArrayList<String> ratInMaze(int[][] maze) {
    ArrayList<String> ans = new ArrayList<>();
    int n = maze.length;
    if (maze[0][0] == 0 || maze[n - 1][n - 1] == 0){
        return ans;
    }
    boolean[][] vis = new boolean[n][n];
    vis[0][0] = true;
    backtrack(maze,ans,0,0,new StringBuilder(),vis,n);
    return ans;
}

public void backtrack(int[][] maze,ArrayList<String> ans,int x,int y,StringBuilder temp,boolean[][] vis,int n){
    if (x == n - 1 && y == n - 1){
        ans.add(temp.toString());
        return;
    }

    //Down
    if (x < n - 1 && !vis[x + 1][y] && maze[x + 1][y] == 1){
        vis[x + 1][y] = true;
        temp.append('D');
        backtrack(maze,ans,x + 1,y,temp,vis,n);
        temp.deleteCharAt(temp.length() - 1);
        vis[x + 1][y] = false;
    }

    //Left
    if (y > 0 && !vis[x][y - 1] && maze[x][y - 1] == 1){
        vis[x][y - 1] = true;
        temp.append('L');
        backtrack(maze,ans,x,y - 1,temp,vis,n);
        temp.deleteCharAt(temp.length() - 1);
        vis[x][y - 1] = false;
    }

    //Right
    if (y < n - 1 && !vis[x][y + 1] && maze[x][y + 1] == 1){
        vis[x][y + 1] = true;
        temp.append('R');
        backtrack(maze,ans,x,y + 1,temp,vis,n);
        temp.deleteCharAt(temp.length() - 1);
        vis[x][y + 1] = false;
    }

    //Up
    if (x > 0 && !vis[x - 1][y] && maze[x - 1][y] == 1){
        vis[x - 1][y] = true;
        temp.append('U');
        backtrack(maze,ans,x - 1,y,temp,vis,n);
        temp.deleteCharAt(temp.length() - 1);
        vis[x - 1][y] = false;
    }
}

void main(){
    int[] nums = {1, 2, 3, 4};

    List<List<Integer>> result = permute(nums);

    System.out.println(result);

    int n = 4;

    List<List<String>> result1 = solveNQueens(n);

    for (List<String> board : result1) {
        for (String row : board) {
            System.out.println(row);
        }

        System.out.println();
    }

    char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    solveSudoku(board);

    for (char[] row : board) {
        System.out.println(Arrays.toString(row));
    }

    int v = 4;
    int[][] edges = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 0},
            {0, 2}
    };

    int m = 3;

    boolean result4 = graphColoring(v, edges, m);

    System.out.println("Graph can be colored: " + result4);

    int[][] maze = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}
    };

    ArrayList<String> result5 = ratInMaze(maze);

    System.out.println(result5);
}
