package com.diary.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.diary.model.dto.User;
import com.diary.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/diary/user")
@Api(tags = "유저 컨트롤러")
public class UserController {

	@Autowired
	private UserService uService;

	// 유저 프로필(사진, 상메, 팀) 가져오기
	@ApiOperation(value = "유저 프로필(사진, 상메, 팀) 가져오기")
	@GetMapping("/profile/{userId}")
	private ResponseEntity<User> doGetProfileUser(@PathVariable String userId) {
		User user = uService.getProfile(userId);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 유저 정보 보기
	@ApiOperation(value = "유저 정보 보기")
	@GetMapping("/{userId}")
	private ResponseEntity<User> doGetUser(@PathVariable String userId) {
		User user = uService.getUser(userId);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 유저 정보 수정
	@ApiOperation(value = "유저 수정")
	@PutMapping(value = "/", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	private ResponseEntity<Integer> doModifyUser(@RequestPart("user") User user,
			@RequestPart(required = false) @RequestParam(value = "img", required = false) MultipartFile img) {

		try {
			if (img != null && img.getSize() > 0) {

				Path baseDirectory = Paths
						.get("C:/PJT-FINAL-E-KJE-JHA/vue-baseballDiary-project/src/assets/pic/userpic");

				// Extracting file name and creating a new directory with the same name
				String fileName = img.getOriginalFilename();
				String saveFileName = uuidFileName(fileName);
				Path newDirectory = baseDirectory.resolve(user.getId());

				try {
					Files.createDirectories(newDirectory);

					// Creating the target path within the new directory
					Path targetPath = newDirectory.resolve(saveFileName).normalize();

					// Transfer the file to the target path
					img.transferTo(targetPath.toFile());
					user.setProfileImg(saveFileName);

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return new ResponseEntity<Integer>(uService.modifyUser(user), HttpStatus.OK);

	}

	// 유저 정보 삭제
	@ApiOperation(value = "유저 삭제")
	@DeleteMapping("/{userId}")
	private ResponseEntity<Integer> doRemoveUser(@PathVariable String userId) {

		int result = uService.removeUser(userId);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// 회원가입
	@ApiOperation(value = "회원가입")
	@PostMapping(value = "/", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	private ResponseEntity<Integer> doSignUp(@RequestPart("user") User user,
			@RequestPart(required = false) @RequestParam(value = "img", required = false) MultipartFile img) {

		try {
			if (img != null && img.getSize() > 0) {

				Path baseDirectory = Paths
						.get("C:/PJT-FINAL-E-KJE-JHA/vue-baseballDiary-project/src/assets/pic/userpic");

				// Extracting file name and creating a new directory with the same name
				String fileName = img.getOriginalFilename();
				String saveFileName = uuidFileName(fileName);
				Path newDirectory = baseDirectory.resolve(user.getId());

				try {
					Files.createDirectories(newDirectory);

					// Creating the target path within the new directory
					Path targetPath = newDirectory.resolve(saveFileName).normalize();

					// Transfer the file to the target path
					img.transferTo(targetPath.toFile());
					user.setProfileImg(saveFileName);

					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return new ResponseEntity<Integer>(uService.signUp(user),HttpStatus.OK);
	}

	// 로그인
	@ApiOperation(value = "로그인")
	@GetMapping("/login")
	private ResponseEntity<?> doLogin(User user, HttpSession session) {
		User tmp = uService.getUser(user.getId());

		if (tmp == null) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		}

		if (tmp.getPw().equals(user.getPw())) {
			session.setAttribute("loginUser", user);
			return new ResponseEntity<String>(tmp.getNickname(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("", HttpStatus.OK);
		}
	}

	// 로그아웃
	@ApiOperation(value = "로그아웃")
	@GetMapping("/logout")
	private ResponseEntity<Void> doLogout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// userList 가져오기
	@ApiOperation(value = "유저리스트 가져오기")
	@GetMapping("/userList")
	private ResponseEntity<List<User>> doGetUserList() {

		List<User> list = uService.getUserList();

		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	private String uuidFileName(String oriFileName) {
		UUID uuid = UUID.randomUUID();
		return uuid.toString() + '_' + oriFileName;
	}

}
