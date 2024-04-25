package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa() {

		// findAll
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q1 = all.get(0);
		assertEquals("sbb가 무엇인가요?", q1.getSubject());

		// findById
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()) {
			Question q2 = oq.get();
			assertEquals("sbb가 무엇인가요?", q2.getSubject());
		}

		// findBySubject
		Question q3 = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q3.getId());

		// findBySubjectAndContent
		Question q4 = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(1, q4.getId());

		// findBySubjectLike
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q5 = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q5.getSubject());
	}
}
