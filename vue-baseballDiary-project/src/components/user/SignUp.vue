<template>
  <div class="signup-all">
    <div class="btn-header">
      <button class="btn-back" @click="$router.go(-1)">
        <img src="../../assets/icon/icons8-arrow-30.png" alt="" />
      </button>
    </div>
    <br />
    <h3>회원가입</h3>
    <div class="signup-container">
      <div>
        <label for="id">아이디</label>
        <span> * </span>
        <input
          type="text"
          id="id"
          placeholder="아이디"
          v-model.trim="id"
          class="form-control"
        />
      </div>
      <div>
        <label for="pw">비밀번호</label>
        <span> * </span>
        <input
          class="form-control"
          type="password"
          id="pw"
          placeholder="비밀번호 입력"
          v-model.trim="pw"
        />
      </div>

      <div>
        <label for="pw2">비밀번호 확인</label>
        <span> * </span>
        <input
          class="form-control"
          type="password"
          id="pw2"
          placeholder="비밀번호 재입력"
          v-model.trim="pw2"
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
          v-model.trim="name"
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
          v-model.trim="nickname"
        />
      </div>

      <div>
        <label for="team">응원 팀</label>
        <select class="form-select" name="team" id="team" v-model="team">
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
          v-model="statusMessage"
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
        <div class="preview">
          <img :src="previewImage" width="110" />
        </div>
      </div>
      <div>
        <button type="button" class="btn signup-btn" @click="doSignup">
          가입 완료
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onBeforeMount } from "vue";
import { useUserStore } from "../../stores/user";

const store = useUserStore();

const id = ref("");
const pw = ref("");
const pw2 = ref("");
const name = ref("");
const nickname = ref("");
const team = ref(0);
const statusMessage = ref("");
const img = ref("");

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

const doSignup = function () {
  if (!id.value) {
    return alert("아이디를 입력해주세요. <필수>");
  }

  if (!pw.value) {
    return alert("비밀번호를 입력해주세요. <필수>");
  }

  if (!pw2.value) {
    return alert("비밀번호 확인을 입력해주세요. <필수>");
  }

  if (pw.value != pw2.value) {
    return alert("비밀번호가 일치하지 않습니다.");
  }

  if (!name.value) {
    return alert("이름을 입력해주세요. <필수>");
  }

  if (!nickname.value) {
    return alert("닉네임을 입력해주세요. <필수>");
  }

  if (!img.value) {
    img.value = "../../assets/icon/default-user.png";
  }

  let user = {
    id: id.value,
    pw: pw.value,
    name: name.value,
    nickname: nickname.value,
    team: team.value,
    statusMessage: statusMessage.value,
    profileImg: img.value,
  };

  doSingupGo(user);
};

const doSingupGo = function (user) {
  const existUserListID = store.userList.filter((existingUser) => {
    return existingUser.id === user.id;
  });
  if (existUserListID.length === 0) {
    if (user.id.length >= 6) {
      if (/^[a-zA-Z0-9]*$/.test(user.id)) {
        if (user.pw.length >= 8 && /[!@#$%^&*(),.?":{}|<>]/.test(user.pw)) {
          if (!user.name.includes(" ")) {
            const existUserListNick = store.userList.filter((existingUser) => {
              return existingUser.nickname === user.nickname;
            });
            if (existUserListNick.length === 0) {
              formData.append(
                "user",
                new Blob([JSON.stringify(user)], { type: "application/json" })
              );
              store.doSignup(formData);
            } else {
              alert("중복된 닉네임입니다. 다른 닉네임을 입력해주세요.");
            }
          } else {
            alert("이름에 공백이 들어가면 안 됩니다.");
          }
        } else {
          alert("비밀번호는 8자리 이상이며, 특수문자를 포함해야 합니다.");
        }
      } else {
        alert("아이디는 영문자와 숫자만 가능합니다.");
      }
    } else {
      alert("6자 이상의 아이디를 입력해주세요.");
    }
  } else {
    alert("중복된 아이디입니다. 다른 아이디를 입력해주세요.");
  }

  // formData.append(
  //   "user",
  //   new Blob([JSON.stringify(user)], { type: "application/json" })
  // );
  // store.doSignup(formData);
};
</script>

<style scoped>
.signup-container {
  /* border: 1px solid; */
  display: flex;
  flex-direction: column;
  margin: 5% 5% 0% 5%;
}

.btn-header {
  display: flex;
  justify-content: space-between;
}

.btn-back {
  top: 0px;
  background: inherit;
  border: none;
  box-shadow: none;
  border-radius: 0;
  padding: 0;
  overflow: visible;
  cursor: pointer;
  height: 40px;
}

.signup-container > div {
  margin-bottom: 5%;
}

.signup-container > div > input {
  margin-top: 2%;
}

.signup-container > div > span {
  color: red;
}

.signup-btn {
  background-color: #001f63;
  color: white;
  width: 430px;
  height: 50px;
  position: fixed;
  bottom: 5%;
  left: 0;
  right: 0;
  margin-left: auto;
  margin-right: auto;
}
</style>
