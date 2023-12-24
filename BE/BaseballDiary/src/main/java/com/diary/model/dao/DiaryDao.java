package com.diary.model.dao;

import java.util.List;

import com.diary.model.dto.Diary;
import com.diary.model.dto.VictoryFairy;

public interface DiaryDao {


	// 하루 일기 가져오기
	List<Diary> selectTodaysDiary(String userId, String date);

	// 이번 주 안에 쓴 일기의 날짜 가져오기
	List<String> selectThisWeekDiaryDate(String userId, String startDate, String finishDate);

	// 일기 상세보기
	Diary selectDiaryDetail(String userId, int diaryId);

	// 일기 추가
	int insertDiary(String userId, Diary diary);

	// 일기 수정
	int updateDiary(String userId, Diary diary);

	// 일기 삭제
	int deleteDiary(String userId, int diaryId);

	// 모아보기에서 날짜 기준으로 사진 / 제목 / 날짜 만 나오게 가져오기(기본이 전체기간)
	List<Diary> selectDiaryList(String userId, String period);

	// 승요 계산
	VictoryFairy selectVictoryFairy(String userId);
}
