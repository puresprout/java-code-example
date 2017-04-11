package serialization;

import java.io.Serializable;

/**
 * 1번 download, execution 변수만 있을때 직렬화했다가 newVarialbe 변수를 추가한 다음 역직렬화하면
 * serialVersionUID가 맞지 않다고 예외 발생<br>
 * 2번 1번 문제를 해결하기 위해서 직접 serialVersionUID를 사용
 * 3번 serialVersionUID를 사용하더라도 문제가 발생하는 경우. 인스턴스 변수 타입 변경
 * 
 * @author 박성현
 * 
 */
public class Count implements Serializable {

	private int download;

	private int execution;
	
	/* 1번 시작 */
	//private int newVarialbe;
	/* 1번 끝 */
	
	/* 2번 시작 */
	static final long serialVersionUID = -1; 

	private int newVarialbe;
	/* 2번 끝 */

	/* 3번 시작. int를 String으로 바꾼다. */ 
	int remove;
	/* 3번 끝 */

	public int getDownload() {
		return download;
	}

	public void setDownload(int download) {
		this.download = download;
	}

	public int getExecution() {
		return execution;
	}

	public void setExecution(int execution) {
		this.execution = execution;
	}

}
