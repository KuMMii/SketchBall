<template>
  <div class="user-detail-box">
    <div class="user-detail-box-detail">
      <img
        :src="getUserImg(store.userMyPage.profileImg)"
        alt=""
        style="width: 180px"
      />
      <p class="user-nickname">{{ store.userMyPage.nickname }}</p>
      <p class="user-msg">{{ store.userMyPage.statusMessage }}</p>
      <div class="user-team">
        <p v-if="store.userMyPage.team === 1">
          🐻 {{ store.userMyPage.teamName }}
        </p>
        <p v-if="store.userMyPage.team === 2">
          👬 {{ store.userMyPage.teamName }}
        </p>
        <p v-if="store.userMyPage.team === 3">
          🦅 {{ store.userMyPage.teamName }}
        </p>
        <p v-if="store.userMyPage.team === 4">
          🐯 {{ store.userMyPage.teamName }}
        </p>
        <p v-if="store.userMyPage.team === 5">
          🦹‍♂️ {{ store.userMyPage.teamName }}
        </p>
        <p v-if="store.userMyPage.team === 6">
          🧙‍♂️ {{ store.userMyPage.teamName }}
        </p>
        <p v-if="store.userMyPage.team === 7">
          👶 {{ store.userMyPage.teamName }}
        </p>
        <p v-if="store.userMyPage.team === 8">
          🦕 {{ store.userMyPage.teamName }}
        </p>
        <p v-if="store.userMyPage.team === 9">
          🦁 {{ store.userMyPage.teamName }}
        </p>
        <p v-if="store.userMyPage.team === 10">
          🐶 {{ store.userMyPage.teamName }}
        </p>
      </div>
      <button @click="logout" type="button" class="btn btn-secondary">
        로그아웃
      </button>
    </div>
    <div class="user-detail-btn">
      &nbsp
      <button @click="userUpdate" type="button" class="btn btn-primary">
        수정
      </button>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";

const store = useUserStore();
const router = useRouter();

onMounted(() => {
  store.getUserMyPage(store.user.userId);
});

const getUserImg = (path) => {
  if (path && !path.includes("../..")) {
    path = `../../assets/pic/userpic/${store.user.userId}/${path}`;
  }
  return new URL(`${path}`, import.meta.url).href;
};

const logout = function () {
  store.logout();
};

const userUpdate = function () {
  router.push({ name: "userUpdate" });
};
</script>

<style scoped>
.user-detail-box {
  border: 1px solid white;
  height: 84vh;
  /* display: flex;
  flex-direction: column; */
}

/* 수정, 삭제 버튼 제외 */
.user-detail-box-detail {
  /* border: 1px solid; */
  text-align: center;
  align-items: center;
  margin: 120px 0px 210px;
}

/* 프로필 이미지 */
.user-detail-box-detail img {
  border-radius: 70%;
  margin: 0% 0% 5% 0%;
  /* border: 1px solid; */
}

/* 닉네임 */
.user-nickname {
  font-size: 200%;
  font-weight: bold;
  /* -webkit-text-stroke: 0.5px black; */
}

/* 한 줄 메세지 */
.user-msg {
  font-size: 110%;
}

/* 팀 */
.user-team {
  font-size: 150%;
  font-weight: bold;
  /* -webkit-text-stroke: 0.2px black; */
}

/* 수정, 삭제 버튼 */
.user-detail-btn {
  /* border: 1px solid; */
  position: fixed;
  right: 0;
  left: 58%;
  margin-left: auto;
  margin-right: auto;
  bottom: 10%;
}

.btn-primary {
  background-color: #001f63;
}
</style>
