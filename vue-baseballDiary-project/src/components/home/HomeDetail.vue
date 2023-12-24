<template>
  <div>
    <div class="homedetail">
      <!-- <h4>{{ month }}월 {{ day }}일 일기</h4> -->
      <div v-if="store.listDiary.length === 0" class="homedetail-none">
        <p>오늘의 경기는 어땠나요?</p>
      </div>
      <div v-else>
        <div
          v-for="list in store.listDiary"
          :key="list.diaryId"
          class="home-router-link-div"
        >
          <router-link
            :to="{ name: 'diaryDetail', params: { diaryId: list.diaryId } }"
            class="home-router-link"
          >
            <div class="home-card" style="width: 100%">
              <!-- 이미지 -->
              <div v-if="list.img">
                <div>
                  <img
                    :src="getImg(list.img)"
                    :alt="list.img"
                    class="home-card-img-top"
                  />
                </div>
              </div>
              <div class="home-content">
                <div class="left-content">
                  <span class="home-content-title">{{ list.title }}</span>
                  <span class="home-content-text">{{ list.content }}</span>
                </div>
                <div class="right-content" v-if="list.homeTeam">
                  <div class="home-teamname">
                    <div>
                      {{ list.awayTeamName.split(" ")[0] }}
                    </div>
                    :
                    <div>
                      {{ list.homeTeamName.split(" ")[0] }}
                    </div>
                  </div>
                  <div class="home-score">
                    <div>{{ list.awayScore }}</div>
                    <div>
                      {{ list.homeScore }}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useDiaryStore } from "../../stores/diary";
import { useUserStore } from "../../stores/user";

const router = useRouter();
const route = useRoute();

const today = ref(route.params.daily);
const year = ref(today.value.split("-")[0]);
const month = ref(today.value.split("-")[1]);
const day = ref(today.value.split("-")[2]);

const store = useDiaryStore();
const storeU = useUserStore();

watch(
  () => route.params.daily,
  (newDate) => {
    if (newDate != null) {
      // 변경된 날짜로 year, month, day 업데이트
      year.value = newDate.split("-")[0];
      month.value = newDate.split("-")[1];
      day.value = newDate.split("-")[2];
      store.getDiaryList(route.params.daily);
    }
  }
);

//업로드한 사진 가져오기
const getImg = (path) => {
  path = `../../assets/pic/userpic/${storeU.user.userId}/${path}`;
  console.log(path);
  return new URL(`${path}`, import.meta.url).href;
};
</script>

<style scoped>
.homedetail {
  /* border: 1px solid; */
  height: 100%;
}

.home-router-link {
  text-decoration: none; /* 링크의 밑줄 제거 */
  color: inherit; /* 링크의 색상 제거 */
}

.home-card-img-top {
  width: 100%;
  height: 180px;
  object-fit: cover;
  border-radius: 25px 25px 0px 0px;
  border-bottom: 2px solid;
}

.home-card {
  border-radius: 25px;
  border: 1px solid;
  margin-bottom: 20px;
}

.home-content {
  display: grid;
  grid-template-columns: 2fr 0.7fr;
  padding: 3px;
  /* align-items: center; */
}

.left-content {
  padding: 15px 20px 15px;
  align-items: center;
}

.home-content-title {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  -webkit-line-clamp: 1;
  /* border: 1px solid; */
  font-size: 16px;
  font-weight: bold;
  /* padding-bottom: 2px; */
  padding-bottom: 2px;
}

.home-content-text {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  -webkit-line-clamp: 1;
  /* border: 1px solid; */
  font-size: 12px;
}

.right-content {
  padding: 10px;
  display: flex;
  flex-direction: column;
  /* border: 1px solid; */
}

.home-teamname {
  /* border: 1px solid; */
  display: grid;
  grid-template-columns: 0.95fr 0.1fr 0.95fr;
  text-align: center;
  color: #999999;
}

.home-score {
  /* border: 1px solid; */
  display: grid;
  grid-template-columns: 1fr 1fr;
  text-align: center;
  font-size: 13px;
  color: #001f63;
  font-weight: bold;
}

.home-router-link-div {
  margin-top: 20px;
}

.homedetail-none {
  /* border: 1px solid; */
  text-align: center;
  margin-top: 200px;
  color: #656565;
  font-size: 15;
  font-weight: bold;
}
</style>
