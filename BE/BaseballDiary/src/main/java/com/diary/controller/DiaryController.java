package com.diary.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.diary.model.dto.Diary;
import com.diary.model.dto.User;
import com.diary.model.dto.VictoryFairy;
import com.diary.model.service.DiaryService;
import com.diary.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/diary")
@Api(tags = "일기 컨트롤러")
public class DiaryController {

	private static User user;
	private static String userId;
	private static String[] week = new String[7];

	private final DiaryService dService;
	private final UserService uService;
	private final HttpSession session;

	@Autowired
	public DiaryController(DiaryService dService, UserService uService, HttpSession session) {
		this.dService = dService;
		this.uService = uService;
		this.session = session;
	}

	// 날짜 보여주기
	@ApiOperation(value = "이번주를 출력")
	@GetMapping("/calendar")
	public ResponseEntity<String[]> getThisWeek() {

		// 세션 실행

		Calendar cal = Calendar.getInstance();
		int toYear = cal.get(Calendar.YEAR);
		int toMonth = cal.get(Calendar.MONTH);
		int toDay = cal.get(Calendar.DAY_OF_MONTH);

//        int toYear = 2023;
//        int toMonth = 4;
//        int toDay = 1;
		cal.set(toYear, toMonth, toDay);
		int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK);

		if (dayOfTheWeek != Calendar.SUNDAY) {
			dayOfTheWeek = dayOfTheWeek - 2;
		} else {
			dayOfTheWeek = 6;
		}

		toDay = toDay - dayOfTheWeek;

		for (int i = 0; i < 7; i++) {
			cal.set(toYear, toMonth, toDay + i);

			// Handle month and year rollover
			if (toDay + i < 1) {
				cal.add(Calendar.MONTH, -1);
				toMonth = cal.get(Calendar.MONTH);
				toYear = cal.get(Calendar.YEAR);

				int lastMonthMaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				toDay = lastMonthMaxDay + (toDay + i);
			}

			week[i] = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
					+ (cal.get(Calendar.DAY_OF_MONTH));
		}

		return new ResponseEntity<String[]>(week, HttpStatus.OK);

	}

	// 스탬프 보여주기
	@ApiOperation(value = "스탬프 출력")
	@GetMapping("/calendar/stamp/{userId}")
	public ResponseEntity<List<String>> getStamp(@PathVariable String userId) {
		String start = week[0].replace("-", "");
		String end = week[6].replace("-", "");
		this.userId = userId;
		List<String> stampList = dService.getThisWeekDiaryDate(userId, start, end);

		return new ResponseEntity<List<String>>(stampList, HttpStatus.OK);

	}

	// 선택날짜에 작성된 기록 보여주기 여기서 세션 실행
	@ApiOperation(value = "선택된 날짜의 일기 보여주기")
	@GetMapping("/calendar/{date}")
	public ResponseEntity<?> getDiary(@PathVariable String date) {
		// 선택 날짜 기록 가져오기

		List<Diary> list = dService.getTodaysDiary(userId, date.replace("-", ""));

		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Diary>>(list, HttpStatus.OK);
	}

	// 기록 상세보기
	@ApiOperation(value = "선택 기록 상세보기")
	@GetMapping("/{diaryId}")
	public ResponseEntity<?> diaryDetail(@PathVariable int diaryId) {
		Diary d = dService.getDiaryDetail(userId, diaryId);
		if (d == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Diary>(d, HttpStatus.OK);
	}

	// 기록 추가
	@ApiOperation(value = "일기 추가")
	@PostMapping(value = "", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Integer> writeDiary(@RequestPart("diary") Diary diary,
			@RequestPart(required = false) @RequestParam(value = "img", required = false) MultipartFile img) {
		try {
			if (img != null && img.getSize() > 0) {

				Path baseDirectory = Paths
						.get("C:/PJT-FINAL-E-KJE-JHA/vue-baseballDiary-project/src/assets/pic/userpic");

				// Extracting file name and creating a new directory with the same name
				String fileName = img.getOriginalFilename();
				String saveFileName = uuidFileName(fileName);
				Path newDirectory = baseDirectory.resolve(userId);

				try {
					Files.createDirectories(newDirectory);

					// Creating the target path within the new directory
					Path targetPath = newDirectory.resolve(saveFileName).normalize();

					// Transfer the file to the target path
					img.transferTo(targetPath.toFile());
					diary.setImg(saveFileName);

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		;

		return new ResponseEntity<Integer>(dService.writeDiary(userId, diary),HttpStatus.CREATED);
	}

	// 기록 수정
	@ApiOperation(value = "일기 수정")
	@PutMapping(value = "", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Integer> modifyDiary(@RequestPart("diary") Diary diary,
			@RequestPart(required = false) @RequestParam(value = "img", required = false) MultipartFile img) {
		
		int result=0;
		try {
			if (img != null && img.getSize() > 0) {

				Path baseDirectory = Paths
						.get("C:/PJT-FINAL-E-KJE-JHA/vue-baseballDiary-project/src/assets/pic/userpic");

				// Extracting file name and creating a new directory with the same name
				String fileName = img.getOriginalFilename();
				String saveFileName = uuidFileName(fileName);
				Path newDirectory = baseDirectory.resolve(userId);

				try {
					Files.createDirectories(newDirectory);

					// Creating the target path within the new directory
					Path targetPath = newDirectory.resolve(saveFileName).normalize();

					// Transfer the file to the target path
					img.transferTo(targetPath.toFile());
					diary.setImg(saveFileName);

					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return new ResponseEntity<Integer>(dService.modifyDiary(userId, diary), HttpStatus.OK);
	}

	// 기록 삭제
	@ApiOperation(value = "일기 삭제")
	@DeleteMapping("/{diaryId}")
	public ResponseEntity<Integer> deleteDiary(@PathVariable int diaryId) {
		return new ResponseEntity<Integer>(dService.removeDiary(userId, diaryId), HttpStatus.OK);
	}

	// 모아보기
	@ApiOperation(value = "모아보기 탭")
	@GetMapping("/list/{period}")
	public ResponseEntity<?> getDiaryList(@PathVariable String period) {
		List<Diary> list = dService.getDiaryList(userId, period);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Diary>>(list, HttpStatus.OK);
	}

	// 승요
	@ApiOperation(value = "승리요정 탭")
	@GetMapping("/victory")
	public ResponseEntity<?> getVictoryFairy() {
		User u = uService.getUser(userId);
		if (u.getTeam() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<VictoryFairy>(dService.getVictoryFairy(userId), HttpStatus.OK);
	}

	private String uuidFileName(String oriFileName) {
		UUID uuid = UUID.randomUUID();
		return uuid.toString() + '_' + oriFileName;
	}

}
