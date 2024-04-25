package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

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

		// 질문 수정
		Optional<Question> oq2 = this.questionRepository.findById(1);
		assertTrue(oq2.isPresent());
		Question q6 = oq2.get();
		q6.setSubject("수정된 제목");
		this.questionRepository.save(q6);

		// 질문 삭제
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq3 = this.questionRepository.findById(1);
		assertTrue(oq3.isPresent());
		Question q7 = oq3.get();
		this.questionRepository.delete(q7);
		assertEquals(1, this.questionRepository.count());

		// 답변 저장
		Optional<Question> oq4 = this.questionRepository.findById(2);
		assertTrue(oq4.isPresent());
		Question q8 = oq4.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q8);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);

		// 답변 조회
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a2 = oa.get();
		assertEquals(2, a2.getQuestion().getId());
	}
}
