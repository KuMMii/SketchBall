<template>
  <div class="login-container">
    <div class="form-floating mb-3">
      <input
        type="text"
        id="id"
        placeholder="ID를 입력해주세요"
        v-model.trim="id"
        class="form-control form-input"
      />
      <label class="form-input-text" for="floatingInput"
        >ID를 입력해주세요</label
      >
    </div>
    <div class="form-floating">
      <input
        type="password"
        id="pw"
        placeholder="비밀번호를 입력해주세요"
        v-model.trim="pw"
        class="form-control form-input"
        @keyup.enter="login"
      />
      <label class="form-input-text" for="floatingPassword"
        >비밀번호를 입력해주세요</label
      >
    </div>
    <button @click="login" type="button" class="btn login-btn">로그인</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const store = useUserStore();

const id = ref("");
const pw = ref("");

const login = function () {
  let user = {
    id: id.value,
    pw: pw.value,
  };

  store.login(user);
};
</script>

<style scoped>
.login-container button {
  margin-top: 3%;
  width: 80%;
  height: 50px;
}

.form-input {
  width: 80%;
  margin-left: 10%;
}

.form-input-text {
  width: 80%;
  margin-left: 10%;
  text-align: center;
  color: rgb(153, 153, 153);
}

.login-btn {
  background-color: #001f63;
  color: white;
}
</style>
