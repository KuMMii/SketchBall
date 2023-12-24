<template>
  <div>
    <!-- <div v-if="isUserLoggedIn">
      <p>이 부분에 바로 HomeView로 가고 싶어</p>
    </div> -->
    <div class="start-container">
      <div class="start-divider">
        <div class="start-right">
          <span class="start-title">야구 일기</span>
          <p class="start-content">야구를 보는 모든 순간을 기록하기</p>
        </div>
        <div class="login-signup">
          <login />
          <router-link :to="{ name: 'signup' }">
            <button type="button" class="btn start-signup-btn">회원가입</button>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import Login from "../components/user/Login.vue";

const router = useRouter();
const isUserLoggedIn = JSON.parse(localStorage.getItem("loginUser"));

if (isUserLoggedIn) {
  router.push({ name: "home" });
}
</script>

<style scoped>
/* 전체 컨테이너 */
.start-container {
  height: 50vh;
  margin-top: 50%;
  margin-left: 7%;
  display: flex;
  flex-direction: column;
  /* border: 1px solid; */
  padding-top: 7%;
}

/* 세로 선 */
.start-divider {
  border-left: 1px solid #ccc;
  height: 100%;
  margin-right: 16px;
}

/* title, 내용 포함 div */
.start-right {
  margin-left: 9%;
}

/* 제목 */
.start-title {
  font-size: 170%;
}

/* 로그인 화면 & 회원가입 버튼 */
.login-signup {
  text-align: center;
  margin-top: 20%;
}

/* 로그인 & 회원가입 버튼 */
.login-signup button {
  margin-top: 5%;
  width: 80%;
  height: 50px;
}

/* 회원가입 버튼 */
.start-signup-btn {
  background-color: #000000;
  color: white;
}
</style>
