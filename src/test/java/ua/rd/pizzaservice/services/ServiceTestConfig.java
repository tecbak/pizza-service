package ua.rd.pizzaservice.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.rd.pizzaservice.repository.OrderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:inMemoryRepoContextH2.xml")
public abstract class ServiceTestConfig extends AbstractTransactionalJUnit4SpringContextTests {

}

