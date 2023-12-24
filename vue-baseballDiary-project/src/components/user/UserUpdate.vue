<template>
  <div class="signup-all">
    <h3>회원 정보 수정</h3>
    <div class="signup-container">
      <div>
        <label for="id">아이디</label>
        <span> * </span>
        <input
          type="text"
          id="id"
          placeholder="아이디"
          disabled
          v-model.trim="user.id"
          class="form-control"
        />
      </div>
      <div>
        <label for="pw">비밀번호</label>
        <span> * </span>
        <input
          type="password"
          id="pw"
          placeholder="비밀번호 입력"
          v-model.trim="pw"
          class="form-control"
        />
      </div>

      <div>
        <label for="pw2">비밀번호 확인</label>
        <span> * </span>
        <input
          type="password"
          id="pw2"
          placeholder="비밀번호 재입력"
          v-model.trim="pw2"
          class="form-control"
        />
      </div>

      <div>
        <label for="name">성명</label>
        <span> * </span>
        <input
          class="form-control"
          type="text"
          id="name"
          placeholder="이름"
          disabled
          v-model.trim="user.name"
        />
      </div>

      <div>
        <label for="nickname">닉네임</label>
        <span> * </span>
        <input
          class="form-control"
          type="text"
          id="nickname"
          placeholder="별명"
          v-model.trim="user.nickname"
        />
      </div>

      <div>
        <label for="team">응원 팀</label>
        <select class="form-select" name="team" id="team" v-model="user.team">
          <option value="0" selected>선택 안 함</option>
          <option value="1">두산</option>
          <option value="2">LG</option>
          <option value="3">한화</option>
          <option value="4">KIA</option>
          <option value="5">키움</option>
          <option value="6">kt</option>
          <option value="7">롯데</option>
          <option value="8">NC</option>
          <option value="9">삼성</option>
          <option value="10">SSG</option>
        </select>
      </div>

      <div>
        <label for="statusMessage">한 줄 메세지</label>
        <input
          class="form-control"
          type="text"
          id="statusMessage"
          placeholder="상태 메세지"
          v-model="user.statusMessage"
        />
      </div>

      <div>
        <label for="img">프로필 이미지</label>
        <input
          type="file"
          id="img"
          name="img"
          @change="imgUpload"
          accept="image/*"
        />
      </div>
      <div class="preview">
        <img :src="previewImage" width="110" />
      </div>
    </div>
    <div class="btns">
      <button @click="userDelete" type="button" class="btn userdelete-btn">
        탈퇴
      </button>
      <button type="button" class="btn signup-btn" @click="userUpdate">
        수정
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onBeforeMount } from "vue";
import { useUserStore } from "../../stores/user";

const store = useUserStore();

const user = ref(store.userMyPage);

const pw = ref("");
const pw2 = ref("");

onBeforeMount(() => {
  store.getUserList();
});

//사진업로드
const formData = new FormData();
const previewImage = ref("");

const imgUpload = function (e) {
  const file = e.target.files[0];

  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      previewImage.value = e.target.result;
    };
    reader.readAsDataURL(file);

    formData.append("img", file);
  }
};

const userUpdate = function () {
  if (!pw.value) {
    return alert("비밀번호를 입력해주세요. <필수>");
  }

  if (!pw2.value) {
    return alert("비밀번호 확인을 입력해주세요. <필수>");
  }

  if (pw.value != pw2.value) {
    return alert("비밀번호가 일치하지 않습니다.");
  }

  if (!nickname.value) {
    return alert("닉네임을 입력해주세요. <필수>");
  }

  user.value.pw = pw.value;

  formData.append(
    "user",
    new Blob([JSON.stringify(user.value)], { type: "application/json" })
  );
  console.log(formData);
  store.userUpdate(user, formData);
};

//회원 탈퇴
const userDelete = function () {
  store.userDelete();
};
</script>

<style scoped>
.signup-container {
  /* border: 1px solid; */
  display: flex;
  flex-direction: column;
  margin: 5% 5% 0% 5%;
}

.signup-container > div {
  margin-bottom: 3%;
}

.signup-container > div > input {
  margin-top: 2%;
}

.signup-container > div > span {
  color: red;
}

.btns {
  text-align: center;
  margin-bottom: 20px;
}

/* 수정 */
.signup-btn {
  background-color: #001f63;
  color: white;
  /* height: 50px; */
  width: 450px;
  /* position: fixed;
  bottom: 10%;
  left: 0;
  right: 0;
  margin-left: auto;
  margin-right: auto; */
}

/* 삭제 */
.userdelete-btn {
  /* border: 12px solid red; */
  color: #727272;
  position: fixed;
  width: 15%;
  bottom: 16%;
  right: 0;
  left: 400px;
  margin-left: auto;
  margin-right: auto;
}
</style>
