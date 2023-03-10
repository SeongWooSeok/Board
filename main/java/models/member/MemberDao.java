package models.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.Connection;
import models.member.MemberDto;
import models.member.MembersDto;

public class MemberDao {
	
	private static MemberDao instance = new MemberDao();
	
	/**'
	 * 회원 등록 
	 */
	public MemberDto register(MemberDto member) {
		try {
			SqlSession sqlSession = Connection.getSqlSession();
			int affectedRows = sqlSession.insert("MemberMapper.register", member);
			if (affectedRows <= 0) {
				throw new RuntimeException();
			}
			
			sqlSession.commit();
			sqlSession.close();
			
			return member;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 회원정보 조회 
	 */
	public MemberDto get(String memId) {
		MemberDto member = null;
		try {
			SqlSession sqlSession = Connection.getSqlSession();
			MemberDto param = new MemberDto();
			param.setMemId(memId);
			member = sqlSession.selectOne("MemberMapper.member", param);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	/**
	 * 회원목록 조회 
	 */
	public List<MemberDto> gets(int page, int limit) {
		page = (page == 0) ? page : 1;
		limit = (limit == 0) ? limit : 30;
		int offset = (page - 1) * limit;
		MembersDto param = new MembersDto(offset, limit);
		SqlSession sqlSession = Connection.getSqlSession();
		List<MemberDto> members = sqlSession.selectList("MemberMapper.members", param);
		
		return members;
	}
	
	public List<MemberDto> gets(int page) {
		return gets(page, 30);
	}
	
	public List<MemberDto> gets() {
		return gets(1);
	}
	
	/**
	 * 중복 아이이디 체크 
	 */
	public boolean checkDuplicateId(String memId) {
		SqlSession sqlSession = Connection.getSqlSession();
		MemberDto param = new MemberDto();
		param.setMemId(memId);
		int count = sqlSession.selectOne("MemberMapper.memberCount", param);
		System.out.println(count);
		return count > 0;
	}
	
	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		
		return instance;
	}
}
