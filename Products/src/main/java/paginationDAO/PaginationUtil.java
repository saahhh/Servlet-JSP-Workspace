package paginationDAO;

public class PaginationUtil {
	//메서드 3가지
	//1. 시작할 페이지 2. 마지막데이터 3. 토탈데이터
	public static int paginationStart(int pageNumber, int pageSize) {
		return(pageNumber -1) * pageSize + 1;
	}
	/*
	 	paginationStart :
	 	 주어진 페이지 번호와 페이지 크기를 이용해서 조회를 시작할 때 데이터 인덱스(크기)를 계산하는 것
	 	 페이지 번호가 1부터 시작하고 각 페이지당 몇 개의 아이템을 보여줄지에 따라서 계산한다. 
	 */
	
	public static int paginationEnd(int pageNumber, int pageSize) {
		return pageNumber * pageSize;
	}
	/*
	 	주어진 페이지 번호와 페이지 크기를 이용해서 조회를 끝낼 데이터 인덱스를 계산
	 	페이지 번호가 1부터 시작하고 각 페이지당 몇 개의 아이템을 보여줄지에 따라서 달라진다.
	*/
	
	public static int paginationTotalPages(int totalItems, int pageSize) {
		return (int) Math.ceil((double)totalItems/pageSize); 
	/*
	 	전체 아이템 수와 페이지 크기를 이용해서 전체 페이지수를 계산하는 메소드
	 	전체 목록을 페이지 크기로 나누지만 (꼭 정수일거라는 보장X)
	 	소수점 이하의 값이 있을 경우에는 올림수행해서 계산처리하도록 설정해주는것
	 */ 
	}
	
}
