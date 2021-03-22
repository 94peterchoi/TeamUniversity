package dokey_persistence;

import java.util.ArrayList;
import java.util.List;

import dokey_vo.GameVO;

public interface ManagerDAO {
	
	
	// 스토어 목록 게임 개수 구하기
	public int getGenreCount(String genre);
	
	// 스토어 목록 가져오기 
	public ArrayList<GameVO> getGenreList(String genre, int start, int end);

	// 스토어 목록에서 게임 삭제
	public int deleteGame(int code);
	
	// 스토어 목록에 게임 추가
	public int insertGame(GameVO vo);
	
	// 게임 상세정보 수정
	public GameVO updateGame(int code);
	
	// 게임 상세정보 수정처리
	public int updateGameAction(GameVO vo, int checkList);
	
	// 게임 통계 조회
	public ArrayList getStatistics(int code);
	
}
