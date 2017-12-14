package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	// the following fails with a MockitoPostProcessor#inject exception
	// Bean named 'myRepositoryImpl' is expected to be of type 'com.example.demo.MyRepositoryImpl'
	// but was actually of type 'com.example.demo.$Proxy54'

	// @SpyBean
	// private MyRepositoryImpl sut;


	// the following works as expected: the key is only retrieve once
	// (verified by looking at the logging output when running the test)
	// but no Mockito.verify is possible on such an object

	// @Autowired
	// private MyRepositoryImpl sut;


	// the ideal, but non-working solution
	@SpyBean
	private MyRepository sut;


	@Test
	public void getTwice() {
		sut.putItem("key1", "value1");
		// retrieve key twice
		assertThat(sut.getItem("key1")).isEqualTo("value1");
		assertThat(sut.getItem("key1")).isEqualTo("value1");
		// due to caching, the repository method should have been called once
		// but this verify fails with TooManyActualInvocations!
		// somehow, the caching abstraction was removed by @SpyBean?!
		verify(sut, times(1)).getItem("key1");
	}

}
