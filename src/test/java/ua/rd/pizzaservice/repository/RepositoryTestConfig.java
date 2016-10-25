package ua.rd.pizzaservice.repository;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:inMemoryRepoContextH2.xml")
//@Transactional
//@Rollback
public abstract class RepositoryTestConfig extends AbstractTransactionalJUnit4SpringContextTests{

}
