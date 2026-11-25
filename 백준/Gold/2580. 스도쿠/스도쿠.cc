#include <stdio.h>
#include <stdlib.h>

int board[10][10] = {0,}; //sudoku board
int row[10][10] = {0,}; //행 
int col[10][10] = {0,}; //렬 
int squ[10][10] = {0,}; //정사각형 
//출력하는 함수
void print_sdk(){
	for(int i = 1; i <= 9; i++){
		for(int j = 1; j <= 9; j++){
			printf("%d ", board[i][j]);
		}
		printf("\n");
	}		
	exit(0);
}

void sdk(int x, int y){
	if(x == 10) print_sdk(); //x가 10이라면 즉 (9, 9)요소까지 다 채우고 넘어왔다면 출력

	//스도쿠 판 해당 자리에 숫자가 이미 채워져 있다면 다음 칸 호출	 
	if(board[x][y]){
		if(y == 9) sdk(x+1, 1);	
		else sdk(x, y+1);
	} 
	//해당 숫자가 채워져 있지 않다면(0이라면) 
	else{
		int s = 3*((x-1)/3) + ((y-1)/3)+1; //소속 사각형 계산
		for(int k = 1; k <= 9; k++){
			if(!row[x][k] && !col[y][k] && !squ[s][k]){ //사용할 수 있는 숫자가 나타난다면
				row[x][k] = col[y][k] = squ[s][k] = 1; // 해당 그룹에 사용했다고 기록
				board[x][y] = k; // 스도쿠 판에 숫자 기록
				if(y == 9) sdk(x+1, 1); // 다음 칸 호출 y가 9면 다음칸은 x+1, 1
				else sdk(x, y+1);
				board[x][y] = 0; //호출 후에는 기록 해제
				row[x][k] = col[y][k] = squ[s][k] = 0;
			}
		}
	}
}
	
int main(void){
	int s;
	for(int i = 1; i <= 9; i++){
		for(int j = 1; j <= 9; j++){
			scanf("%d", &board[i][j]);
			s = 3*((i-1)/3) + ((j-1)/3)+1; //소속 정사각형 계산
			row[i][board[i][j]] = 1; //입력 받은 숫자를
			col[j][board[i][j]] = 1; //각 소속 그룹에
			squ[s][board[i][j]] = 1; //사용 됐다고 표시		
		}
	}	
	sdk(1, 1);
	return 0;
}