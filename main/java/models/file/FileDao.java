package models.file;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.Connection;

public class FileDao {
	
	private static FileDao instance = new FileDao();
	
	/**
	 * 파일 정보 등록 
	 */
	public FileDto register(FileDto file) {
		
		SqlSession sqlSession = Connection.getSqlSession();
		
		sqlSession.insert("FileMapper.register", file);
		
		sqlSession.commit();
		sqlSession.close();
		
		return file;
	}
	
	/**
	 * 파일 정보 조회
	 */
	public FileDto get(int id) {
		SqlSession sqlSession = Connection.getSqlSession();
		
		FileDto param = new FileDto();
		param.setId(id);
		FileDto fileInfo = sqlSession.selectOne("FileMapper.file", param);
		
		sqlSession.close();
		
		return fileInfo;
	}
	
	/**
	 * 파일 그룹별 조회
	 */
	public List<FileDto> gets(String gid) {
		SqlSession sqlSession = Connection.getSqlSession();
		
		FileDto param = new FileDto();
		param.setGid(gid);
		List<FileDto> files = sqlSession.selectList("FileMapper.files", param);
		
		sqlSession.close();
		
		return files;
		
	}
	
	/**
	 * 파일 업로드 완료 처리된 파일 목록 조회
	 */
	public List<FileDto> getsDoneDesc(String gid) {
		SqlSession sqlSession = Connection.getSqlSession();
		
		FileDto param = new FileDto();
		param.setGid(gid);
		
		List<FileDto> files = sqlSession.selectList("FileMapper.filesDoneDESC", param);
		
		sqlSession.close();
		
		return files;
	}
	
	/**
	 * 파일 그룹별 완료 처리 
	 */
	public void updateDone(String gid) {
		SqlSession sqlSession = Connection.getSqlSession();
		FileDto param = new FileDto();
		param.setGid(gid);
		param.setIsDone(1);
		sqlSession.update("FileMapper.done", param);
		sqlSession.commit();
		sqlSession.close();
	}
	
	/**
	 * 파일 삭제
	 */
	public void delete(int id) {
		
		SqlSession sqlSession = Connection.getSqlSession();
		
		FileDto param = new FileDto();
		param.setId(id);

		sqlSession.delete("FileMapper.delete", param);
		
		sqlSession.commit();
		sqlSession.close();
	}
	
	/**
	 * 싱글톤
	 */
	public static FileDao getIntance() {
		if (instance == null) {
			instance = new FileDao();
		}
		
		return instance;
	}
}
