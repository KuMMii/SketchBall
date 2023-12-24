<template>
  <div>
    <!-- <p id="logo">SketchBall</p> -->
    <div class="list-header">
      <div style="height: 30px">
        <span class="list-title-name">{{ storeU.user.userNicname }}</span>
        <span class="list-title-name-plus">&nbsp님의 기록</span>
        <div class="list-filter">
          <button class="list-filter-button" @click="filterOn">
            <img src="../assets/icon/icons8-filter1-64.png" alt="" width="25" />
          </button>
        </div>
      </div>
      <div v-if="isFilter" class="search">
        <select class="search-year" name="year" id="year" v-model="year">
          <option value="" selected>전체</option>
          <option v-for="yearOp in yearOptions" :key="yearOp" :value="yearOp">
            {{ yearOp }}
          </option>
        </select>
        <span> 년&nbsp </span>
        <select
          v-if="year != ''"
          class="search-month"
          name="month"
          id="month"
          v-model="month"
        >
          <option value="" selected disabled>달</option>
          <option
            v-for="monthOp in monthOptions"
            :key="monthOp"
            :value="monthOp"
          >
            {{ monthOp }}
          </option>
        </select>
        <span v-if="year != ''"> 월&nbsp </span>
        <button class="search-button" @click="getFilterDiary">보기</button>
      </div>
    </div>
    <div v-if="groupedDiaries.length !== 0">
      <div
        class="list-year-month"
        v-for="group in groupedDiaries"
        :key="group.yearMonth"
      >
        <span class="yearMonth">{{ group.yearMonth }}의 기록</span>
        <div class="list-router-for-all">
          <div
            v-for="list in group.diaries"
            :key="list.diaryId"
            class="list-router-all"
          >
            <router-link
              :to="{ name: 'diaryDetail', params: { diaryId: list.diaryId } }"
              class="list-router-link"
            >
              <div v-if="list.img" class="list-card">
                <img
                  :src="getImg(list.img)"
                  :alt="list.img"
                  class="list-card-img"
                />
              </div>
              <div v-else class="list-card">
                <span class="list-card-text">{{ list.title }}</span>
              </div>
            </router-link>
          </div>
        </div>
      </div>
    </div>
    <div v-else class="list-else">
      <p>작성하신 일기가 없습니다!</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useDiaryStore } from "../stores/diary";
import { useUserStore } from "../stores/user";

const store = useDiaryStore();
const storeU = useUserStore();

const year = ref("");
const month = ref("");
const yearMonth = ref("");

const currentYear = new Date().getFullYear();
const yearOptions = Array.from(
  { length: currentYear - 2020 + 1 },
  (_, index) => 2020 + index
);
// const yearLen = yearOptions.length; //yearLen: yearOptions 배열 길이

const monthOptions = Array.from({ length: 12 }, (_, index) => index + 1);
// const monthLen = monthOptions.length;

//처음 로딩할 때 모든 다이어리 가져오고
onMounted(() => {
  if (store.listVeiwDiary.length == 0) {
    store.getViewDiaryList("all");
  }
  isFilter.value = false;
});

const getFilterDiary = function () {
  console.log(year.value);
  if (year.value == "") {
    yearMonth.value = "all";
  } else {
    if (month.value < 10) {
      yearMonth.value = year.value + "-" + "0" + month.value;
    } else {
      yearMonth.value = year.value + "-" + month.value;
    }
  }
  store.getViewDiaryList(yearMonth.value);
  // listVeiwDiary = store.listVeiwDiary;
};

//업로드한 사진 가져오기
const getImg = (path) => {
  path = `../assets/pic/userpic/${storeU.user.userId}/${path}`;
  return new URL(`${path}`, import.meta.url).href;
};

// 연도와 월에 따라 일기를 그룹화
const groupedDiaries = computed(() => {
  const grouped = [];
  store.listVeiwDiary.forEach((diary) => {
    const [year, month] = diary.gamedate.split("-");
    const yearMonth = `${year}년 ${month}월`;
    if (!grouped[yearMonth]) {
      grouped[yearMonth] = { yearMonth, diaries: [] };
    }
    grouped[yearMonth].diaries.push(diary);
  });
  return Object.values(grouped);
});

const isFilter = ref(false);
const filterOn = function () {
  isFilter.value = !isFilter.value;
};
</script>

<style scoped>
/* #logo {
		font-family: "Nanum DaSiSiJagHae";
		font-size: 3rem;
		font-weight: 900;
		text-align: center;
	} */
/* name */
.list-title-name {
  -webkit-text-stroke: 1px black;
  font-size: 150%;
  font-weight: 800;
  color: #000000;
  /* text-shadow: 1.5px 1.5px #c9c9c9; */
  margin: 0;
  /* text-decoration: underline #c9c9c9 3px; */
}

/* 이름 옆에 '님의 기록' */
.list-title-name-plus {
  font-size: 80%;
  font-weight: 500;
}

/* 누르면 필터 나옴 */
.list-filter {
  float: right;
}

/* 누르면 필터나오는 버튼 */
.list-filter-button {
  border: none;
  background-color: transparent;
}

/* 연도, 월, 찾기 하면 나오는 select 박스 */
.search {
  width: 100%;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin: 10px 0px 10px 0px;
}

/* 연도 선택 콤보상자 */
.search-year {
  -o-appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  width: 50px;
  text-align: center;
  border-radius: 7px;
  font-size: small;
  padding: 3px;
  border: none;
  background-color: #f3f3f3;
  color: #878787;
  font-weight: 500;
  box-shadow: 0px 4px 4px #878787b7;
  cursor: pointer;
}

/* 월 선택 콤보상자 */
.search-month {
  -o-appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  width: 30px;
  text-align: center;
  border-radius: 7px;
  font-size: small;
  padding: 3px;
  border: none;
  background-color: #f3f3f3;
  color: #878787;
  font-weight: 500;
  box-shadow: 0px 4px 4px #878787b7;
  cursor: pointer;
}

/* 콤보상자 버튼 */
.search-button {
  border: none;
  border-radius: 7px;
  font-size: small;
  padding: 5px 8px;
  background-color: #878787;
  color: white;
  font-weight: 700;
  box-shadow: 0px 4px 4px #878787b7;
}

/* 년, 월 글씨 */
.search span {
  font-size: small;
  padding: 4px;
  font-weight: 500;
}

/* 기록 전체 */
.list-year-month {
  margin-top: 25px;
}

/* 0000년 00월의 기록 */
.yearMonth {
  font-size: medium;
  /* padding-top: 10px; */
  font-weight: 500;
  margin-left: 10px;
}

/* 찐으로 라우터링크 전체는 얘임 */
.list-router-for-all {
  display: flex;
  flex-wrap: wrap;
  margin-top: 10px;
}

/* 라우터 링크 전체 */
.list-router-all {
  /* border: 1px solid; */
  margin: 5px 10px;
}

/* 라우터링크에 커서 올려놨을 때 */
.list-router-all:active {
  border-radius: 25px;
  box-shadow: 2px 3px 4px #878787b7;
}

/* 라우터 링크 하나만 */
.list-router-link {
  text-decoration: none; /* 링크의 밑줄 제거 */
  color: inherit; /* 링크의 색상 제거 */
}

/* 라우터 링크 크기 맞출 것 */
.list-card {
  border-radius: 25px;
  border: 1px solid #878787;
  /* margin-bottom: 20px; */
  width: 120px;
  height: 120px;
}

/* 라우터링크 안에 들어 있는 이미지 */
.list-card-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 25px;
}

/* 이미지 없을 경우 대신 들어가는 텍스트 */
.list-card-text {
  display: -webkit-box;

  -webkit-box-orient: vertical;
  overflow: hidden;
  -webkit-line-clamp: 2;
  text-align: center;
  padding: 20px;
  font-weight: 600;
}

/* 기록이 없을 경우 들어가는 텍스트 */
.list-else {
  margin-left: auto;
  margin-right: auto;
  position: fixed;
  top: 50%;
  width: 100%;
  /* border: 1px solid; */
  right: 0;
  left: 0;
}

.list-else p {
  text-align: center;
  color: #878787;
}
</style>
