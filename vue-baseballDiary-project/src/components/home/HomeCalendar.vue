<template>
  <div>
    <span>&nbsp&nbsp{{ currentMonth }}월</span>
    <div class="calendar">
      <table>
        <tr>
          <th>월</th>
          <th>화</th>
          <th>수</th>
          <th>목</th>
          <th>금</th>
          <th>토</th>
          <th>일</th>
        </tr>
        <tr>
          <td v-for="(daily, index) in week" :key="daily.day">
            <router-link
              :to="{
                name: 'homeDetail',
                params: {
                  daily: daily.year + '-' + daily.month + '-' + daily.day,
                },
              }"
            >
              <button
                class="calendar-button"
                @click="selectActive(index)"
                :class="[
                  { active: arrayActive[index].active.value },
                  { activeInitial: (currentDay == daily.day) & isDay },
                ]"
              >
                {{ daily.day }}
              </button>
            </router-link>
          </td>
        </tr>
        <tr>
          <td v-for="daily in week" :key="daily.day">
            <template v-for="sticker in store.stamp">
              <template v-if="daily.day === sticker">
                <img
                  src="../../assets/icon/icons8-basball-96.png"
                  alt=""
                  width="30"
                  class="home-calendar-img"
                />
              </template>
            </template>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useDiaryStore } from "@/stores/diary";
import { useUserStore } from "@/stores/user";

const store = useDiaryStore();
const storeU = useUserStore();

const currentMonth = ref("");
const currentDay = ref("");
const isDay = ref(false);

onMounted(() => {
  store.getWeek();
  currentMonth.value = new Date().getMonth() + 1;
  store.getStamp(storeU.user.userId);
  currentDay.value = new Date().getDate();
  isDay.value = true;
});

const week = store.week;
const stamp = store.stamp;

//날짜 클릭했을 때 active
const arrayActive = [];
for (let i = 0; i < 7; i++) {
  arrayActive.push({
    active: ref(false),
  });
}

const selectActive = function (index) {
  for (let i = 0; i < 7; i++) {
    arrayActive[i].active.value = false;
  }
  arrayActive[index].active.value = true;
  // console.log(arrayActive[index].active.value);

  isDay.value = false;
};
</script>

<style scoped>
/* 캘린더 전체 */
.calendar {
  border: 0.5px solid #878787;
  display: flex;
  justify-content: center;
  padding: 10px 0px;
  border-radius: 25px;
  /* 캘린더와 homedetail 사이의 간격 */
  margin-bottom: 7%;
}

/* 테이블 헤더 */
th {
  font-size: 17px;
  font-weight: 900;
  border-bottom: 2px solid;
  padding: 3px;
}

/* 테이블 내용 */
td {
  padding: 10px 10px 3px 10px;
  /* border: 1px solid; */
}

/* 테이블 간격 */
tr {
  text-align: center;
}

/* 테이블 그 자체 */
table {
  width: 93%;
}

/* 야구공 스탬프 이미지 */
.home-calendar-img {
  padding-bottom: 10px;
}

/* 캘린더 버튼 (날짜 선택) */
.calendar-button {
  /* border: 1px solid; */
  border: none;
  width: 2rem;
  height: 2rem;
  color: #bfbfbf;
  font-weight: 900;
  border-radius: 70%;
  align-items: center;
  background-color: transparent;
}

/* 클릭 할 때 활성화 될 css */
.active {
  background-color: #ebebeb;
  border-radius: 70%;
  width: 2rem;
  height: 2rem;
  align-items: center;
  color: #000000;
}

/* 초기에 활성화 되는 css */
.activeInitial {
  background-color: #ebebeb;
  border-radius: 70%;
  width: 2rem;
  height: 2rem;
  align-items: center;
  color: #000000;
}
</style>
