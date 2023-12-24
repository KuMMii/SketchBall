<template>
  <div class="btn-header">
    <button class="btn-back" @click="$router.go(-1)">
      <img src="../../assets/icon/icons8-arrow-30.png" alt="" />
    </button>
    <button class="btn-back" @click="goHome">
      <img
        src="../../assets/icon/icons8-home-48.png"
        alt=""
        style="width: 30px"
      />
    </button>
  </div>

  <div id="capture" class="div-detail">
    <article>
      <!-- 제목만 무조건 나오는거임 -->
      <div class="title">
        <p>{{ diary.title }}</p>
        <div class="dropdown">
          <i
            class="bi bi-three-dots-vertical"
            style="font-size: 1.5rem; color: #5e5c58"
            @click="toggleDropdown"
          ></i>
          <div v-if="isDropdownVisible" class="dropdown-content">
            <div @click="diaryUpdate">수정하기</div>
            <div @click="diaryDelete">삭제하기</div>
            <div @click="downloadClicked">저장하기</div>
            <div id="kakaotalk-sharing-btn" @click="kakaoShare">공유하기</div>
          </div>
        </div>
      </div>
      <!-- 사진 -->
      <div v-if="diary.img">
        <img :src="getImg(diary.img)" :alt="diary.img" style="width: 100%" />
      </div>

      <!-- 본문 -->
      <div class="content" v-if="diary.content">{{ diary.content }}</div>

      <!-- 날짜 시간 -->
      <div class="div-date-time">
        <div v-if="diary.gamedate">{{ diary.gamedate }}</div>
        <div v-if="diary.gametime">{{ diary.gametime }}</div>
      </div>

      <!-- 위치&직관 -->
      <div class="div-loc">
        <div v-if="diary.location">
          <img
            src="../../assets/icon/icons8-location-64.png"
            style="height: 20px"
            alt=""
            id="icon-location"
          />
          <!-- 위치 -->
          <div class="text-loc">{{ diary.location }}</div>
        </div>
        <!-- 직관 -->
        <div v-if="diary.isStadium">
          <div class="custom-badge">{{ isStadium(diary.isStadium) }}</div>
        </div>
      </div>

      <div>
        <div v-if="diary.result" class="result-title">
          <div style="font-size: 1.5rem; -webkit-text-stroke: 0.5px">
            경기 결과
          </div>
          <!-- 결과 -->
          <div class="custom-badge">
            {{ result(diary.result) }}
          </div>
        </div>
        <!-- 스코어보드 -->
        <div v-if="diary.awayTeamImg" class="div-score">
          <div>
            <!-- 내부 큰 박스 -->
            <div>
              <!-- 원정팀 박스 -->
              <div class="box">
                <!-- awaylogo&score-->
                <div class="logo-name">
                  <div class="logo">
                    <img
                      v-show="diary.awayTeamImg"
                      :src="getAwayImg('../..' + diary.awayTeamImg)"
                      alt=""
                    />
                  </div>
                  <div v-if="diary.awayTeamName">{{ diary.awayTeamName }}</div>
                </div>
                <div class="score">
                  <div
                    v-if="store.diary.awayScore || store.diary.awayScore == '0'"
                  >
                    {{ store.diary.awayScore }}
                  </div>
                  <div v-else>-</div>
                </div>
              </div>
              <!-- 콜론 박스 -->
              <div style="font-size: 3rem; color: rgb(133 133 133)">:</div>
              <!-- 홈팀 박스 -->
              <div class="box">
                <!-- homelogo&score -->
                <div class="score">
                  <div
                    v-if="store.diary.homeScore || store.diary.homeScore == '0'"
                  >
                    {{ store.diary.homeScore }}
                  </div>
                  <div v-else>-</div>
                </div>
                <div class="logo-name">
                  <div class="logo">
                    <img
                      v-show="diary.homeTeamImg"
                      :src="getHomeImg('../..' + diary.homeTeamImg)"
                      alt=""
                    />
                  </div>
                  <div v-if="diary.homeTeamName">{{ diary.homeTeamName }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </article>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { useDiaryStore } from "@/stores/diary";
import { onBeforeMount, ref, watch } from "vue";
import html2canvas from "html2canvas";

const route = useRoute();
const router = useRouter();
const store = useDiaryStore();

const diary = ref({});

//페이지 로딩하면 나오게 하기
watch(
  () => store.diary,
  (newDiary) => {
    diary.value = newDiary;
  }
);

// 홈으로 가기
const goHome = () => {
  router.push({
    name: "home",
  });
};

// 드롭다운
const isDropdownVisible = ref(false);

const toggleDropdown = () => {
  isDropdownVisible.value = !isDropdownVisible.value;
};

//직관 여부
const isStadium = function (stadium) {
  if (stadium == 1) return "직관";
  if (stadium == 2) return "집관";
  if (stadium == 3) return "그 외";
};

//결과
const result = function (result) {
  if (result == 1) return "승리";
  if (result == 2) return "패배";
  if (result == 3) return "무승부";
  if (result == 4) return "상관 없음";
};

//업로드한 사진 가져오기
const getImg = (path) => {
  path = `../../assets/pic/userpic/${store.diary.userId}/${path}`;
  console.log(path);
  return new URL(`${path}`, import.meta.url).href;
};

//팀 로고 사진
const getAwayImg = (path) => {
  return new URL(`${path}`, import.meta.url).href;
};
const getHomeImg = (path) => {
  return new URL(`${path}`, import.meta.url).href;
};

//읽어오기
onBeforeMount(() => {
  store.getDiaryDetail(route.params.diaryId);
});

const diaryUpdate = function () {
  router.push({ name: "diaryUpdate" });
};

const diaryDelete = function () {
  store.deleteDiary();
};

//다운받기
let picForKakao = "";

let captureDiv = document.getElementById("capture");

const downloadClicked = () => {
  html2canvas(document.querySelector("#capture")).then((canvas) => {
    canvas.toBlob((blob) =>
      navigator.clipboard.write([new ClipboardItem({ "image/png": blob })])
    );
    let uri = canvas.toDataURL("image/jpg");

    saveImg(uri, "image.jpg");
    // uploadImgur(uri.split(",")[1]);

    picForKakao = uri.split(",")[1];
  });
};

// 사진 저장
const saveImg = (uri, filename) => {
  let link = document.createElement("a");

  document.body.appendChild(link);

  link.href = uri;
  link.download = filename;
  link.click();

  document.body.removeChild(link);
};

// 카톡 공유
const kakaoShare = () => {
  Kakao.Share.createDefaultButton({
    container: "#kakaotalk-sharing-btn",
    objectType: "feed",
    content: {
      title: "SketchBall⚾",

      imageUrl:
        "https://kr-mb.theepochtimes.com/assets/uploads/2021/02/6331661e12fa5ad97b73fe3897eef0d6-395x400.jpg",
      link: {
        webUrl: "http://localhost:5173",
      },
    },
    social: {
      likeCount: 286,
      commentCount: 45,
      sharedCount: 845,
    },
    buttons: [
      {
        title: "웹으로 보기",
        link: {
          webUrl: "http://localhost:5173",
        },
      },
    ],
  });
};
</script>

<style scoped>
@import url("../../assets/css/diaryDetail.css");
</style>
