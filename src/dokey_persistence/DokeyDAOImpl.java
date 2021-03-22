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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dokey_vo.BuylistVO;
import dokey_vo.GameVO;
import dokey_vo.PointVO;
import dokey_vo.ReviewVO;
import dokey_vo.UserinfoVO;
import dokey_vo.WishlistVO;

public class DokeyDAOImpl implements DokeyDAO {

	// 싱글톤
	private static DokeyDAOImpl instance = new DokeyDAOImpl();
	
	public static DokeyDAOImpl getInstance() {
		if (instance == null) {
			instance = new DokeyDAOImpl();
		} 
		return instance;
	}
	
	DataSource dataSource;
	
	private DokeyDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/GAME");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	// 회원가입 아이디 중복확인
	@Override
	public int idCheck(String id) {
		
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT id FROM userinfo WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				selectCnt = 1;
			} else { // else문은 사실 필요없음.
				selectCnt = 0;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return selectCnt;
	}
	
	
	// 회원가입 처리
	@Override
	public int insertUser(UserinfoVO vo) {
		int insertCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO userinfo(id, pwd, nickname, email, hp) "
						+ "VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getNickname());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getHp());
			
			insertCnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return insertCnt;
	}

	
	// 로그인 처리 + 회원탈퇴, 회원정보 수정시 비밀번호 인증
	@Override
	public int idPwdCheck(String strId, String strPwd) {
		int selectCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT pwd FROM userinfo WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (strPwd.equals(rs.getString("pwd"))) {
					selectCnt = 1;	// 패스워드 일치
				} else {
					selectCnt = -1;	// 패스워드 불일치
				}
			} else {
				pstmt.close();
				rs.close();
				
				String sql2 = "SELECT m_pwd FROM managerinfo WHERE m_id=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, strId);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					if (strPwd.equals(rs.getString("m_pwd"))) {
						selectCnt = 7;		// 매니져 로그인
					} else {
						selectCnt = 0;		// 존재하지 않는 아이디
					}
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return selectCnt;	// 01.22 정상적으로 작동
	}

	
	// 스토어 장르별 개임 개수 구하기
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
	public ArrayList<GameVO> getGenreList(String genre, int start, int end, int order) {
		
		ArrayList<GameVO> dtos = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			
			conn = dataSource.getConnection();
			
			String orderString = "";
			if (order == 0) {
				orderString = "(ORDER BY gi.publishing_date DESC) AS idenNum,"; // 출시일 빠른순
			} else if (order == 1) {
				orderString = "(ORDER BY gi.publishing_date ASC) AS idenNum,";	// 출시일 느린순
			} else if (order == 2) {
				orderString = "(ORDER BY price DESC) AS idenNum,";			// 가격 높은순
			} else if (order == 3) {
				orderString = "(ORDER BY price ASC) AS idenNum,";			// 가격 낮은순
			}

			String sql = "SELECT * FROM ("
					+ "SELECT ROW_NUMBER() OVER "
						   + orderString
						   + "gi.code,"
						   + "gi.num,"
						   + "gi.title,"
						   + "gi.publisher,"
						   + "gi.price,"
						   + "gi.thumbnail1"
						   + " FROM gameinfo gi WHERE genre LIKE ?)"
				    + "WHERE ? <= idenNum AND idenNum <= ?";
			
			
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("order값: " + order);
			
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

	// 특정 게임 상세정보 조회
	@Override
	public GameVO getGameDetail(int num) {
		
		GameVO vo = new GameVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM gameinfo WHERE code=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new GameVO();
				
				vo.setNum(rs.getInt("num"));
				vo.setCode(rs.getInt("code"));
				
				vo.setTitle(rs.getString("title"));
				
				vo.setPrice(rs.getInt("price"));		// 할인가냐 정가냐 기능 구현해야 함
				
				vo.setTrailer(rs.getString("trailer"));
				vo.setThumbnail1(rs.getString("thumbnail1"));
				vo.setThumbnail2(rs.getString("thumbnail2"));
				vo.setThumbnail3(rs.getString("thumbnail3"));
				vo.setThumbnail4(rs.getString("thumbnail4"));
				vo.setGamelogo(rs.getString("gamelogo"));
				vo.setDescription(rs.getString("description"));
				vo.setDeveloper(rs.getString("developer"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setPublishing_date(rs.getDate("publishing_date"));
				vo.setGenre(rs.getString("genre"));
				vo.setRate(rs.getString("rate"));
				vo.setPlatform(rs.getString("platform"));
				
				vo.setGame_view(rs.getInt("game_view"));	// 01.31 추가 조회수 꺼내오기 (관리자용인데 DokeyDAOImpl에 있네 ㅠ)
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
	
	// 조회수 증가 메서드
	@Override
	public void increaseView(int num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE gameinfo SET game_view = game_view + 1 WHERE num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	// 특정 게임 전체 리뷰 보기
	@Override
	public ArrayList<ReviewVO> seeReview(int code) {
		
		ArrayList<ReviewVO> dtos = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT userid, gamecode, content, posting_date, nickname, starpoint, goodbad, u.profile_img, count_good, u.count_game"
					+ " FROM review r, userinfo u"
					+ " WHERE r.userid = u.id AND gamecode = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				dtos = new ArrayList<ReviewVO>();
				
				do {
					ReviewVO vo = new ReviewVO();
					vo = new ReviewVO();
					
					vo.setUserid(rs.getString("userid"));
					vo.setNickname(rs.getString("nickname"));
					vo.setPosting_date(rs.getTimestamp("posting_date"));
					vo.setStarpoint(rs.getInt("starpoint"));
					vo.setGoodbad(rs.getString("goodbad"));
					vo.setProfile(rs.getString("profile_img"));
					vo.setContent(rs.getString("content"));
					vo.setCount_good(rs.getInt("count_good"));
					vo.setCount_game(rs.getInt("count_game"));
				
					dtos.add(vo);
					
				} while(rs.next());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return dtos;
	}

	// 고객 포인트 조회 (게임구매 화면)
	@Override
	public int checkPoint(String userid) {
		int point = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT point FROM userinfo WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				point = rs.getInt("point");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return point;
	}
	
	// 고객 포인트 충전
	@Override
	public int addPoint(String userid, int point) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql1 = "UPDATE userinfo SET point=point+? "
											+ "WHERE id=?";
			pstmt = conn.prepareStatement(sql1);
			
			pstmt.setInt(1, point);
			pstmt.setString(2, userid);
			
			updateCnt = pstmt.executeUpdate();
			
			pstmt.close();
			
			String sql2 = "INSERT INTO user_point(userid, point, payment) "
						+ "VALUES(?, ?, '신용카드')";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, userid);
			pstmt.setInt(2, point);
			
			updateCnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateCnt;
	}


	
	// 고객 게임구매 (포인트로 게임 사기) -> 고객 : 게임개수 +, 포인트 - || 게임 : 판매개수 +
	@Override
	public int buyGame(int code, String userid) {
		int buyCnt = 0;	// 성공적으로 구매됐으면 1로 값 변경
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			
			String sql = "UPDATE userinfo SET point "
					+ "= (point - (SELECT price FROM gameinfo WHERE code = ?)), "
					+ "count_game = count_game + 1"
					+ "WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
			pstmt.close();
			
			String sql2 = "UPDATE gameinfo SET game_sell = game_sell + 1 WHERE code = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, code);
			pstmt.executeUpdate();
			pstmt.close();
			
			
			String sql3 = "INSERT INTO user_buy(userid, gamecode, pay_price) "
							+ "VALUES(?, ?, (SELECT price FROM gameinfo WHERE code = ?))";
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, userid);
			pstmt.setInt(2, code);
			pstmt.setInt(3, code);
			buyCnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return buyCnt;	// 성공적으로 구매됐으면 buyCnt 값은 1
	}

	
	// 고객이 구매한 게임 지금구매, 장바구니구매 버튼 막기
	@Override
	public int block(int num, String userid) {
		
		int buyornot = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			int gamecode = 0;
			
			conn = dataSource.getConnection();
			String sql1 = "SELECT code FROM gameinfo WHERE num=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			// 이 if문은 결과값이 무조건 있어야 함.
			if (rs.next()) {
				gamecode = rs.getInt("code");
			}
			pstmt.close();
			rs.close();
			
			System.out.println("게임코드출력: " + gamecode);
			
			String sql2 = "SELECT * FROM user_buy WHERE gamecode=? AND userid=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, gamecode);
			pstmt.setString(2, userid);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				buyornot = 1;
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return buyornot;	// 구매이력 있으면 1, 구매이력 없으면 0
		
	}
	

	
	// 고객 구매이력 조회
	@Override
	public ArrayList<BuylistVO> seeBuylist(String userId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<BuylistVO> dtos = null;

		try {
			conn = dataSource.getConnection();
			String sql1 = "SELECT gi.title, ub.gamecode, ub.pay_price, ub.pay_day "
							+ "FROM user_buy ub, gameinfo gi "
							+ "WHERE ub.gamecode = gi.code AND userid = ?"
							+ "ORDER BY ub.pay_day";
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dtos = new ArrayList<BuylistVO>();
				
				do {
					BuylistVO vo = new BuylistVO();
					
					vo.setTitle(rs.getString("title"));
					vo.setGamecode(rs.getInt("gamecode"));
					vo.setPrice(rs.getInt("pay_price"));
					vo.setPay_day(rs.getTimestamp("pay_day"));
					
					
					Timestamp now = new Timestamp(System.currentTimeMillis());
					int interval = now.getDay() - vo.getPay_day().getDay();
//					System.out.println(interval);
					vo.setInterval(interval);
					
					dtos.add(vo);
					
				} while(rs.next());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	}

	// 고객 게임 환불
	@Override
	public int refundGame(String userid, int code, int price) {
		int refundCnt = 0;
		
		// 2가지 작업이 이루어져야함
		// 1. user_info 테이블에서 고객 찾아서 포인트 환불 (update)
		// 2. user_buy 테이블에서 해당 고객 건 삭제 (delete)
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql1 = "UPDATE userinfo SET point = point + ?, count_game = count_game - 1 WHERE id = ?";
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, price);
			pstmt.setString(2, userid);
			refundCnt = pstmt.executeUpdate();
			pstmt.close();
			
			
			String sql2 = "DELETE FROM user_buy WHERE userid = ? AND gamecode = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, userid);
			pstmt.setInt(2, code);
			refundCnt= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return refundCnt;
	}

	// 고객 장바구니 담기
	@Override
	public int wishlist(String userid, int code, String url) {
		int insertCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		BuylistVO vo = new BuylistVO();
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO pick(userid, gamecode, detail_url) "
						+ "VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, code);
			pstmt.setString(3, url);
			
			insertCnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return insertCnt;
	}

	
	
	// 고객 - 이미 장바구니에 있는 상품입니다
	@Override
	public int picked(int code, String userid) {
		
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql1 = "SELECT * FROM pick WHERE userid=? AND gamecode=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, userid);
			pstmt.setInt(2, code);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				selectCnt = 1;
			}
			pstmt.close();
			rs.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return selectCnt;
	}

	// 고객 장바구니 보기
	@Override
	public ArrayList<WishlistVO> viewWishlist(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<WishlistVO> dtos = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT p.detail_url, g.gamelogo, g.title, g.price, p.pickdate, p.gamecode "
							+ "FROM pick p, gameinfo g "
							+ "WHERE g.code = p.gamecode "
							+ "AND userid = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dtos = new ArrayList<WishlistVO>();
				
				do {
					WishlistVO vo = new WishlistVO();
					
					vo.setUrl(rs.getString("detail_url"));
					vo.setLogo(rs.getString("gamelogo"));
					vo.setTitle(rs.getString("title"));
					vo.setPrice(rs.getInt("price"));
					vo.setPickdate(rs.getTimestamp("pickdate"));
					vo.setGamecode(rs.getInt("gamecode"));
					
					dtos.add(vo);
					
				} while(rs.next());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
		
	}

	
	// 고객 장바구니에서 상품 삭제
	@Override
	public int deleteWishlist(String userid, int code) {
		
		int deleteCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM pick WHERE userid = ? AND gamecode = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, code);
			
			deleteCnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deleteCnt;
	}

	
	// 고객 본인 프로필 조회
	@Override
	public UserinfoVO profile(String userid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		UserinfoVO vo = new UserinfoVO();
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM userinfo WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				vo.setId(rs.getString("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setEmail(rs.getString("email"));
				vo.setHp(rs.getString("hp"));
				vo.setProfile_img(rs.getString("profile_img"));
				vo.setPoint(rs.getInt("point"));
				vo.setCount_review(rs.getInt("count_review"));
				vo.setCount_game(rs.getInt("count_game"));
				vo.setLast_date(rs.getTimestamp("last_date"));	// 마지막으로 접속한 날짜

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	
	// 고객 포인트 충전내역(게시판) 개수 구하기 
	@Override
	public int getPointCount(String userid) {
		
		int selectCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM user_point WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
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
	
	
	// 고객 본인 포인트 충전내역 조회 (프로필에서 조회가능)
	@Override
	public ArrayList<PointVO> viewPoint(String userid, int start, int end) {
		
		ArrayList<PointVO> dtos = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT * FROM ("
					+ "SELECT ROW_NUMBER() OVER "
						   + "(ORDER BY up.pay_day DESC) AS idenNum,"
						   + "up.userid,"
						   + "up.pay_day,"
						   + "up.point,"
						   + "up.payment"
						   + " FROM user_point up WHERE userid=?)"
				    + "WHERE ? <= idenNum AND idenNum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				dtos = new ArrayList<PointVO>();
				
				do {
					PointVO vo = new PointVO();
					vo.setUserid(userid);
					vo.setPay_day(rs.getTimestamp("pay_day"));
					vo.setPoint(rs.getInt("point"));
					vo.setPayment(rs.getString("payment"));
					
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

	
	// 고객 본인 프로필 수정 - 회원정보 가져오기
	@Override
	public UserinfoVO getUserInfo(String userid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		UserinfoVO vo = new UserinfoVO();
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM userinfo WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setEmail(rs.getString("email"));
				vo.setHp(rs.getString("hp"));
//				vo.setProfile_img(rs.getString("profile_img"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	// 고객 본인 프로필 수정 - DB 업데이트
	@Override
	public int updateUserProfile(UserinfoVO vo) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE userinfo SET pwd=?, "
											+ "nickname=?, "
											+ "email=?, "
											+ "hp=? "
											+ "WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getNickname());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getHp());
			pstmt.setString(5, vo.getId());
			
			updateCnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateCnt;
	}

	// 고객 회원탈퇴 - 회원탈퇴 처리 및 탈퇴기록 관리자 DB에 저장
	@Override
	public int deleteProfile(String userid, String reason, String suggestion) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int deleteCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			String sql1 = "DELETE userinfo WHERE id = ?";
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, userid);
			
			deleteCnt = pstmt.executeUpdate();
			pstmt.close();
			
			String sql2 = "INSERT INTO deletelog(userid, reason, suggestion) "
						+ "VALUES(?, ?, ?) "; 
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, userid);
			pstmt.setString(2, reason);
			pstmt.setString(3, suggestion);
			
			deleteCnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deleteCnt;
	}





	
	
}
