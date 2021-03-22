package dokey_persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dokey_vo.GameVO;

public class ManagerDAOImpl implements ManagerDAO {
	
	// 싱글톤
	private static ManagerDAOImpl instance = new ManagerDAOImpl();
	
	public static ManagerDAOImpl getInstance() {
		if (instance == null) {
			instance = new ManagerDAOImpl();
		} 
		return instance;
	}
	
	DataSource dataSource;
	
	private ManagerDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/GAME");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// 스토어 목록 게임 개수 구하기
	@Override
	public int getGenreCount(String genre) {
		int selectCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 입력받은 장르에 맞게끔 쿼리가 구성되게끔
			conn = dataSource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM gameinfo WHERE genre LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, genre);
			
			rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				selectCnt = rs.getInt("cnt");
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return selectCnt;
	}

	
	
	// 스토어 목록 가져오기 
	@Override
	public ArrayList<GameVO> getGenreList(String genre, int start, int end) {
		
		ArrayList<GameVO> dtos = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT * FROM ("
					+ "SELECT ROW_NUMBER() OVER (ORDER BY gi.publishing_date DESC) AS idenNum,"
						   + "gi.code,"
						   + "gi.num,"
						   + "gi.title,"
						   + "gi.publisher,"
						   + "gi.price,"
						   + "gi.thumbnail1"
						   + " FROM gameinfo gi WHERE genre LIKE ?)"
				    + "WHERE ? <= idenNum AND idenNum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, genre);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				dtos = new ArrayList<GameVO>();
				
				do {
					GameVO vo = new GameVO();
					vo.setCode(rs.getInt("code"));
					vo.setNum(rs.getInt("num"));
					vo.setTitle(rs.getString("title"));
					vo.setPublisher(rs.getString("publisher"));
					vo.setPrice(rs.getInt("price"));
					vo.setThumbnail1(rs.getString("thumbnail1"));
					
					
					dtos.add(vo);
				
				} while(rs.next());
			}
					
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	}


	// 스토어 목록에서 게임 삭제
	@Override
	public int deleteGame(int code) {
		int deleteCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		
		try {
			// 입력받은 장르에 맞게끔 쿼리가 구성되게끔
			conn = dataSource.getConnection();
			String sql = "DELETE gameinfo WHERE code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			
			deleteCnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return deleteCnt;
	}


	// 스토어 목록에 게임 추가
	@Override
	public int insertGame(GameVO vo) {
		
		int insertCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO gameinfo(num, code, genre, title, publisher, price, price2,"  
						+ " trailer, thumbnail1, thumbnail2, thumbnail3, thumbnail4, gamelogo,"
						+ " description, developer, rate, platform, publishing_date)"
						+ " VALUES(game_seq.nextval, ?, ?, ?, ?, ?, ?,"
						+ " ?, ?, ?, ?, ?, ?,"
						+ " ?, ?, ?, ?, ?)";
			
			// 출시일하고 디스카운티드 고민 좀 해야됨
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCode());
			pstmt.setString(2, vo.getGenre());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getPublisher());
			pstmt.setInt(5, vo.getPrice());
			pstmt.setInt(6, vo.getPrice2());
			
			pstmt.setString(7, vo.getTrailer());
			pstmt.setString(8, vo.getThumbnail1());
			pstmt.setString(9, vo.getThumbnail2());
			pstmt.setString(10, vo.getThumbnail3());
			pstmt.setString(11, vo.getThumbnail4());
			pstmt.setString(12, vo.getGamelogo());
			
			pstmt.setString(13, vo.getDescription());
			pstmt.setString(14, vo.getDeveloper());
			pstmt.setString(15, vo.getRate());
			pstmt.setString(16, vo.getPlatform());
			pstmt.setString(17, vo.getStr_publishing_date());
			
			insertCnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return insertCnt;
	}


	// 게임 상세정보 수정하기
	@Override
	public GameVO updateGame(int code) {
		
		GameVO vo = new GameVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM gameinfo WHERE code=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new GameVO();
				
				vo.setCode(rs.getInt("code"));
				
				vo.setTitle(rs.getString("title"));

				vo.setTrailer(rs.getString("trailer"));
				
				vo.setPrice(rs.getInt("price"));
				vo.setPrice2(rs.getInt("price2"));
				
				
				vo.setThumbnail1(rs.getString("thumbnail1"));
				vo.setThumbnail2(rs.getString("thumbnail2"));
				vo.setThumbnail3(rs.getString("thumbnail3"));
				vo.setThumbnail4(rs.getString("thumbnail4"));
				vo.setGamelogo(rs.getString("gamelogo"));
				
				vo.setDescription(rs.getString("description"));
				vo.setDeveloper(rs.getString("developer"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setPublishing_date(rs.getDate("publishing_date"));	// 년월일 방식으로 바꿔줘야 함
				vo.setGenre(rs.getString("genre"));
				vo.setRate(rs.getString("rate"));
				vo.setPlatform(rs.getString("platform"));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}


	// 게임 상세정보 수정처리
	@Override
	public int updateGameAction(GameVO vo, int checkList) {
		
		int updateCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			
			String sql = "";
			
			if (checkList == 1) {	// 첨부파일 5개 모두 들어간 경우에만 이미지 변경 처리
				sql = "UPDATE gameinfo SET code=?, genre=?, title=?, publisher=?, price=?, price2=?,"
							+ " trailer=?, thumbnail1=?, thumbnail2=?, thumbnail3=?, thumbnail4=?, gamelogo=?,"
							+ " description=?, developer=?, rate=?, platform=?, publishing_date=?"
							+ " WHERE code = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getCode());
				pstmt.setString(2, vo.getGenre());
				pstmt.setString(3, vo.getTitle());
				pstmt.setString(4, vo.getPublisher());
				pstmt.setInt(5, vo.getPrice());
				pstmt.setInt(6, vo.getPrice2());
				
				pstmt.setString(7, vo.getTrailer());
				pstmt.setString(8, vo.getThumbnail1());
				pstmt.setString(9, vo.getThumbnail2());
				pstmt.setString(10, vo.getThumbnail3());
				pstmt.setString(11, vo.getThumbnail4());
				pstmt.setString(12, vo.getGamelogo());
				
				pstmt.setString(13, vo.getDescription());
				pstmt.setString(14, vo.getDeveloper());
				pstmt.setString(15, vo.getRate());
				pstmt.setString(16, vo.getPlatform());
				
				pstmt.setString(17, vo.getStr_publishing_date());
				pstmt.setInt(18, vo.getCode());
				
				updateCnt = pstmt.executeUpdate();

			} else {	// 5개 모두 첨부하지 않았다면 기존 이미지 유지
				sql = "UPDATE gameinfo SET code=?, genre=?, title=?, publisher=?, price=?, price2=?,"
						+ " trailer=?,"
						+ " description=?, developer=?, rate=?, platform=?, publishing_date=?"
						+ " WHERE code = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getCode());
				pstmt.setString(2, vo.getGenre());
				pstmt.setString(3, vo.getTitle());
				pstmt.setString(4, vo.getPublisher());
				pstmt.setInt(5, vo.getPrice());
				pstmt.setInt(6, vo.getPrice2());
				
				pstmt.setString(7, vo.getTrailer());
				
				pstmt.setString(8, vo.getDescription());
				pstmt.setString(9, vo.getDeveloper());
				pstmt.setString(10, vo.getRate());
				pstmt.setString(11, vo.getPlatform());
				pstmt.setString(12, vo.getStr_publishing_date());
				
				pstmt.setInt(13, vo.getCode());
				
				updateCnt = pstmt.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return updateCnt;
	}

	
	// 게임 통계 조회
	@Override
	public ArrayList getStatistics(int code) {
		
		ArrayList dtos = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT pay_day "
						+ "FROM user_buy "
						+ "WHERE gamecode = ? "
						+ "ORDER BY pay_day desc";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				dtos = new ArrayList();
				
				do {
					Timestamp time = rs.getTimestamp("pay_day");
					dtos.add(time);
					
				} while(rs.next());
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dtos;

	}

	
}
