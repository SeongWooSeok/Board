package models.board.comment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.Connection;

public class CommentDao {
	
	private static CommentDao instance = new CommentDao();
	
	private CommentDao() {}
	
	/**
	 * 댓글 조회 
	 */
	public CommentDto get(int id) {
		SqlSession sqlSession = Connection.getSqlSession();
		
		CommentDto param = new CommentDto();
		param.setId(id);
		
		CommentDto comment = sqlSession.selectOne("CommentMapper.get", param);
				
		sqlSession.close();
		
		return comment;
	}
	
	/**
	 * 게시글 별 댓글 목록 
	 */
	public List<CommentDto> gets(int boardDataId) {
		SqlSession sqlSession = Connection.getSqlSession();
		
		CommentDto param = new CommentDto();
		param.setBoardDataId(boardDataId);
		List<CommentDto> rows = sqlSession.selectList("CommentMapper.gets", param);
		
		sqlSession.close();
		
		return rows;
	}
	
	/**
	 * 댓글 추가 
	 */
	public boolean register(CommentDto comment) {
		SqlSession sqlSession = Connection.getSqlSession();
		
		int affectedRows = sqlSession.insert("CommentMapper.register", comment);
		
		sqlSession.commit();
		sqlSession.close();
		
		return affectedRows > 0;
	}
	 
	/**
	 * 댓글 수정 
	 */
	public boolean update(CommentDto comment) {
		SqlSession sqlSession = Connection.getSqlSession();
		
		int affectedRows = sqlSession.update("CommentMapper.update", comment);
		
		sqlSession.commit();
		sqlSession.close();
		
		return affectedRows > 0;
	}
	
	/**
	 * 댓글 삭제
	 */
	public boolean delete(int id) {
		SqlSession sqlSession = Connection.getSqlSession();
		
		CommentDto param = new CommentDto();
		param.setId(id);
		
		int affectedRows = sqlSession.update("CommentMapper.delete", param);
		
		sqlSession.commit();
		sqlSession.close();
		
		return affectedRows > 0;
	}
	
	public static CommentDao getInstance() {
		if (instance == null) {
			instance = new CommentDao();
		}
		
		return instance;
	}
}