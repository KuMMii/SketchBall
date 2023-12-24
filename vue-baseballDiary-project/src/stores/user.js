import { ref, computed, watchEffect } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_USER_API = `http://localhost:8081/diary/user`;

export const useUserStore = defineStore("user", () => {
	const user = ref({
		userNicname: "",
		isLogin: false,
		userId: "",
	});

	watchEffect(() => {
		const storedUser = JSON.parse(localStorage.getItem("loginUser"));
		if (storedUser) {
			user.value.userNicname = storedUser._value.userNicname;
			user.value.isLogin = storedUser._value.isLogin;
			user.value.userId = storedUser._value.userId;
		}
	});

	//로그인
	const login = function (loginUser) {
		axios
			.get(`${REST_USER_API}/login?id=${loginUser.id}&pw=${loginUser.pw}`)
			.then((res) => {
				if (res.data) {
					user.value.isLogin = true;
					user.value.userNicname = res.data; //닉네임 가져오기
					user.value.userId = loginUser.id; //지금 로그인한 아이디 저장

					localStorage.setItem("loginUser", JSON.stringify(user));
					router.push({ name: "home" });
				} else {
					alert("회원 정보가 없거나 아이디와 비밀번호가 일치하지 않습니다.");
				}
			})
			.catch((error) => {
				console.error(error);
			});
	};

	//로그아웃
	const logout = function () {
		axios
			.get(`${REST_USER_API}/logout`)
			.then(() => {
				user.value.userNicname = "";
				user.value.isLogin = false;
				user.value.userId = "";

				localStorage.removeItem("loginUser");
				router.push({ name: "start" });
			})
			.catch((error) => {
				console.error(error);
			});
	};

	//회원 삭제
	const userDelete = function () {
		axios
			.delete(`${REST_USER_API}/${user.value.userId}`)
			.then(() => {
				user.value.userNicname = "";
				user.value.isLogin = false;
				user.value.userId = "";
				localStorage.removeItem("loginUser");
				router.push({ name: "start" });
			})
			.catch((error) => {
				console.error(error);
			});
	};

	//유저 프로필 가져오기 - home
	const userProfile = ref({});
	const getUserProfile = function (userId) {
		axios.get(`${REST_USER_API}/profile/${userId}`).then((res) => {
			userProfile.value = res.data;
		});
	};

	//유저 프로필 가져오기 - 마이페이지
	const userMyPage = ref({});
	const getUserMyPage = function (userId) {
		axios.get(`${REST_USER_API}/${userId}`).then((res) => {
			userMyPage.value = res.data;
		});
	};

	//유저 리스트 가져오기
	const userList = ref([]);
	const getUserList = function () {
		axios.get(`${REST_USER_API}/userList`).then((res) => {
			userList.value = res.data;
		});
	};

	//회원가입
	const userSignup = ref({});
	const doSignup = function (formData) {
		axios({
			url: `http://localhost:8081/diary/user/`,
			method: "POST",
			headers: {
				"Content-Type": "multipart/form-data",
			},
			data: formData,
		})
			.then(() => {
				alert("회원가입 완료!");
				router.push({ name: "start" });
			})
			.catch((error) => {
				console.log(error);
			});
	};

	//회원 수정
	const userUpdate = function (updateUser, dataForm) {
		if (updateUser.value.pw.length >= 8 && /[!@#$%^&*(),.?":{}|<>]/.test(updateUser.value.pw)) {
			const existUserListNick = userList.value.filter((existingUser) => {
				return existingUser.nickname === updateUser.value.nickname;
			});
			if (existUserListNick.length === 0 || updateUser.value.id === existUserListNick[0].id) {
				axios({
					url: `http://localhost:8081/diary/user/`,
					method: "PUT",
					headers: {
						"Content-Type": "multipart/form-data",
					},
					data: dataForm,
				}).then(() => {
					router.push({
						name: "userDetail",
						params: { userId: updateUser.value.id },
					});
				});
			} else {
				alert("중복된 닉네임입니다. 다른 닉네임을 입력해주세요.");
			}
		} else {
			alert("비밀번호는 8자리 이상이며, 특수문자를 포함해야 합니다.");
		}
	};

	return {
		user,
		login,
		logout,
		userDelete,
		userProfile,
		getUserProfile,
		userMyPage,
		getUserMyPage,
		userList,
		getUserList,
		userSignup,
		doSignup,
		userUpdate,
	};
});
