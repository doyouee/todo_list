package com.doyouee.todo.todoList.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doyouee.todo.todoList.entity.TodoInfoEntity;

@Repository
public interface TodoInfoRepository extends JpaRepository<TodoInfoEntity, Long> {
    public List<TodoInfoEntity> findAllByMiSeq(Long miSeq); // 사용자 번호를 통해서 List 가져오기
                                    //By 다음에 첫머리 글자는 대문자 + 설정한 변수대소문자 그대로
                                    //                      (M)   +  (iSeq)
    public TodoInfoEntity findBySeq(Long seq);
    public void deleteBySeqAndMiSeq(Long seq, Long miSeq);
    public TodoInfoEntity findBySeqAndMiSeq(Long seq, Long miSeq);
    public List<TodoInfoEntity> findByEndDtBetweenAndMiSeq(Date start, Date end, Long miSeq); // select * from todo_info where ti_end_dt between [start] and [end] and ti_mi_seq = [miSeq]
}
