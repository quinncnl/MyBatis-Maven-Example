import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cleartsai.mybatis.model.Pet;
import com.cleartsai.mybatis.model.PetExample;
import com.cleartsai.mybatis.persistence.PetMapper;

public class FirstTest {

	@Test
	public void GOTest() throws IOException {
		String resource = "MyBatisConf.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);

		SqlSession sqlSession = sqlSessionFactory.openSession();

		PetExample pet = new PetExample();
		pet.or().andSpeciesEqualTo("dog");

		try {
			PetMapper mapper = sqlSession.getMapper(PetMapper.class);
			List<Pet> allRecords = mapper.selectByExample(pet);
			for (Pet s : allRecords)
				System.out.println(s.getName());
		} finally {
			sqlSession.close();
		}

	}

}
