<template>
  <div>
    <div class="profile-all">
      <div>
        <router-link
          :to="{ name: 'userDetail', params: { userId: store.user.userId } }"
        >
          <img
            :src="getUserImg(store.userProfile.profileImg)"
            :alt="store.userProfile.profileImg"
            style="width: 70px"
            id="profile-img"
          />
        </router-link>
      </div>
      <div class="profile-details">
        <div class="profile-nicname">
          <b>{{ store.user.userNicname }}&nbsp</b>
          <span v-if="store.userProfile.team === 1">🐻</span>
          <span v-if="store.userProfile.team === 2">👬</span>
          <span v-if="store.userProfile.team === 3">🦅</span>
          <span v-if="store.userProfile.team === 4">🐯</span>
          <span v-if="store.userProfile.team === 5">🦹‍♂️</span>
          <span v-if="store.userProfile.team === 6">🧙‍♂️</span>
          <span v-if="store.userProfile.team === 7">👶</span>
          <span v-if="store.userProfile.team === 8">🦕</span>
          <span v-if="store.userProfile.team === 9">🦁</span>
          <span v-if="store.userProfile.team === 10">🐶</span>
        </div>
        <div class="profile-msg">
          <p>{{ store.userProfile.statusMessage }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useUserStore } from "../../stores/user";

const store = useUserStore();

onMounted(() => {
  store.getUserProfile(store.user.userId);
});

const getUserImg = (path) => {
  // let path = path;
  if (path && !path.includes("../..")) {
    path = `../../assets/pic/userpic/${store.user.userId}/${path}`;
  }
  return new URL(`${path}`, import.meta.url).href;
};
</script>

<style scoped>
/* 프로필 전체 div */
.profile-all {
  /* border: 1px solid; */
  display: flex;
  padding: 15px 0px;
}

/* 프로필 이미지 */
#profile-img {
  border-radius: 70%;
  border: 1px solid black;
}

/* 프로필 닉네임 */
.profile-nicname {
  padding: 10px 15px 3px;
  font-weight: 900;
  font-size: 17px;
}

/* 프로필 상메 */
.profile-msg {
  padding: 0px 15px;
  font-size: 13px;
}
</style>
