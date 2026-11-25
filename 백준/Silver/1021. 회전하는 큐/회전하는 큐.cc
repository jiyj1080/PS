#include <stdio.h>

int q[451];

int main() {
	int head, tail, i, j, n, m, cnt=0, x;

	scanf("%d%d",&n,&m);
	for(i=1;i<=n;i++) 
        q[200+i]=200+i;
	head=201; tail=200+n;
	for(i=1;i<=m;i++) {
		scanf("%d",&x);
		x+=200;
		for(j=head;j<=tail;j++) {
			if(q[j]==x) {
                x=j; 
                break;
            }
		}

		if(x<=(head+tail)/2) {
			while(head!=x) {
				q[++tail]=q[head];
				cnt++; head++;
			}
			head++;
		}
		else {
			while(1) {
				q[--head]=q[tail];
				cnt++;
				if(tail==x) break;
				tail--;
			}
			tail--;
			head++;
		}
	}
	printf("%d",cnt);
}