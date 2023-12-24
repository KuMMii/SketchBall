package com.diary.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diary.model.dao.DiaryDao;
import com.diary.model.dto.Diary;
import com.diary.model.dto.VictoryFairy;

@Service
public class DiaryServiceImpl implements DiaryService{
	
	@Autowired
	private DiaryDao ddao;


	@Override
	public List<Diary> getTodaysDiary(String userId, String date) {
		return ddao.selectTodaysDiary(userId, date);
	}

	@Override
	public List<String> getThisWeekDiaryDate(String userId, String startDate, String finishDate) {
		return ddao.selectThisWeekDiaryDate(userId, startDate, finishDate);
	}

	@Override
	public Diary getDiaryDetail(String userId, int diaryId) {
		return ddao.selectDiaryDetail(userId, diaryId);
	}

	@Override
	public int writeDiary(String userId, Diary diary) {
		return ddao.insertDiary(userId, diary);
	}

	@Override
	public int modifyDiary(String userId, Diary diary) {
		return ddao.updateDiary(userId, diary);
	}

	@Override
	public int removeDiary(String userId, int diaryId) {
		return ddao.deleteDiary(userId, diaryId);
	}

	@Override
	public List<Diary> getDiaryList(String userId, String period) {
		return ddao.selectDiaryList(userId, period);
	}

	@Override
	public VictoryFairy getVictoryFairy(String userId) {
		return ddao.selectVictoryFairy(userId);
	}

	
	private String dir="/upload";
	
	private Path fileDir;
	
	
	@PostConstruct
	 public void postConstruct() {
        fileDir = Paths.get(dir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileDir);
        } catch (IOException e) {
        	System.out.println(e.getMessage());
        }
    }
	
//	@Override
//	public UploadFile uploadFile(MultipartFile file) {
//		String uploadFileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
//        } catch (IOException e) {
//        }
//
//        String realName = UUID.randomUUID().toString() + "_" + uploadFileName;
//        Path targetLocation = fileDir.resolve(realName);
//        try {
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//        	System.out.println(e.getMessage());
//        }
//
//        return UploadFile
//                .setDisplayName(uploadFileName)
//                .setSize(file.getSize())
//                .setCount(file.TargetCount)
//                .build();
//		
//		
//		
//		
//	}

}
