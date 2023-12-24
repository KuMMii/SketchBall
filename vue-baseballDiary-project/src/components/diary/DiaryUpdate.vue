<template>
  <div>
    <button class="btn-back" @click="$router.go(-1)">
      <img src="../../assets/icon/icons8-arrow-30.png" alt="" />
    </button>

    <!-- 입력 폼 -->
    <div class="div-fieldset">
      <fieldset>
        <!-- 제목 & 내용 -->
        <div class="div-input">
          <span>나의 기록</span>
          <div class="input-group mb-3">
            <input
              type="text"
              class="form-control"
              id="title"
              placeholder="제목을 입력해주세요"
              v-model="store.diary.title"
            />
          </div>
          <div class="input-group mb-3">
            <textarea
              class="form-control"
              id="content"
              cols="10"
              rows="5"
              placeholder="내용을 입력해주세요(선택)"
              v-model="store.diary.content"
            ></textarea>
          </div>
        </div>

        <!-- 이미지 추가 -->
        <div class="div-input div-img">
          <label for="img" class="label-img">
            <img
              src="../../assets/icon/icons8-camera-100.png"
              alt=""
              style="width: 50px"
            />
          </label>
          <input
            type="file"
            id="img"
            name="img"
            @change="imgUpload"
            hidden
            accept="image/*"
          />
          <div
            v-show="fileUploaded"
            class="preview"
            style="width: 70px; height: 70px; border: 1px dotted black"
          >
            <img :src="previewImage()" contain height="70" width="70" alt="" />
          </div>
          <div
            v-if="uploadedImage"
            class="preview"
            style="width: 70px; height: 70px; border: 1px dotted black"
          >
            <img :src="uploadedImage" contain height="70" width="70" alt="" />
          </div>
        </div>

        <!-- 날짜 & 시간 -->
        <div class="div-input date">
          <span>날짜</span>
          <div class="input-group mb-3">
            <input
              class="form-control"
              type="date"
              id="gamedate"
              name="gamedate"
              v-model="store.diary.gamedate"
            />
          </div>
          <div class="input-group mb-3">
            <input
              type="time"
              class="form-control"
              id="gametime"
              name="gametime"
              v-model="store.diary.gametime"
            />
          </div>
        </div>

        <!-- 직관여부&장소 -->
        <div class="div-input">
          <span>장소</span>
          <div class="div-isStadium">
            <input
              type="radio"
              id="radio_stadium"
              class="btn-check"
              autocomplete="off"
              value="1"
              v-model="store.diary.isStadium"
            />
            <label
              class="btn btn-outline-secondary btn-sm"
              for="radio_stadium"
              style="
                --bs-btn-padding-y: 0.25rem;
                --bs-btn-padding-x: 0.5rem;
                --bs-btn-font-size: 0.75rem;
                --bs-btn-border-radius: 10px;
              "
              >직관</label
            >
            <input
              type="radio"
              id="radio_home"
              class="btn-check"
              autocomplete="off"
              value="2"
              v-model="store.diary.isStadium"
            />
            <label
              class="btn btn-outline-secondary btn-sm"
              for="radio_home"
              style="
                --bs-btn-padding-y: 0.25rem;
                --bs-btn-padding-x: 0.5rem;
                --bs-btn-font-size: 0.75rem;
                --bs-btn-border-radius: 10px;
              "
              >집관</label
            >
            <input
              type="radio"
              id="radio_etc"
              class="btn-check"
              autocomplete="off"
              value="3"
              v-model="store.diary.isStadium"
            />
            <label
              class="btn btn-outline-secondary btn-sm"
              for="radio_etc"
              style="
                --bs-btn-padding-y: 0.25rem;
                --bs-btn-padding-x: 0.5rem;
                --bs-btn-font-size: 0.75rem;
                --bs-btn-border-radius: 10px;
              "
              >그 외</label
            >
          </div>

          <div class="input-group mb-3">
            <img
              src="../../assets/icon/icons8-location-64.png"
              style="height: 20px"
              alt=""
              id="icon-location"
            />
            <input
              class="form-control"
              type="text"
              id="location"
              name="location"
              placeholder="장소를 적어주세요(선택)"
              v-model="store.diary.location"
              style="background: none; margin: 0; padding-left: 33px"
            />
          </div>
        </div>

        <!-- 결과-->
        <div class="div-input result">
          <span>경기 결과</span>
          <div class="div-result">
            <input
              type="radio"
              id="radio_win"
              class="btn-check"
              autocomplete="off"
              value="1"
              v-model="store.diary.result"
            />
            <label
              class="btn btn-outline-secondary btn-sm"
              for="radio_win"
              style="
                --bs-btn-padding-y: 0.25rem;
                --bs-btn-padding-x: 0.5rem;
                --bs-btn-font-size: 0.75rem;
                --bs-btn-border-radius: 10px;
              "
              >승리</label
            >
            <input
              type="radio"
              id="radio_lose"
              class="btn-check"
              autocomplete="off"
              value="2"
              v-model="store.diary.result"
            />
            <label
              class="btn btn-outline-secondary btn-sm"
              for="radio_lose"
              style="
                --bs-btn-padding-y: 0.25rem;
                --bs-btn-padding-x: 0.5rem;
                --bs-btn-font-size: 0.75rem;
                --bs-btn-border-radius: 10px;
              "
              >패배</label
            >
            <input
              type="radio"
              id="radio_tie"
              class="btn-check"
              autocomplete="off"
              value="3"
              v-model="store.diary.result"
            />
            <label
              class="btn btn-outline-secondary btn-sm"
              for="radio_tie"
              style="
                --bs-btn-padding-y: 0.25rem;
                --bs-btn-padding-x: 0.5rem;
                --bs-btn-font-size: 0.75rem;
                --bs-btn-border-radius: 10px;
              "
              >무승부</label
            >
            <input
              type="radio"
              id="radio_nomatter"
              class="btn-check"
              autocomplete="off"
              value="4"
              v-model="store.diary.result"
            />
            <label
              class="btn btn-outline-secondary btn-sm"
              for="radio_nomatter"
              style="
                --bs-btn-padding-y: 0.25rem;
                --bs-btn-padding-x: 0.5rem;
                --bs-btn-font-size: 0.75rem;
                --bs-btn-border-radius: 10px;
              "
              >상관 없음</label
            >
          </div>
        </div>

        <!-- 스코어보드-->
        <div class="div-score">
          <span>스코어</span>
          <div>
            <!-- 내부 큰 박스 -->
            <div>
              <!-- 원정팀 박스 -->
              <div class="box">
                <!-- awaylogo&score-->
                <div class="logo">
                  <img :src="getAwayLogo" alt="" />
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
                <div class="logo">
                  <img :src="getHomeLogo" alt="" />
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 경기결과를 넣을지 말지 선택하게 하기 -->
        <div class="div-input">
          <div class="form-check form-switch" style="margin-top: 10px">
            <input
              class="form-check-input"
              type="checkbox"
              role="switch"
              id="flexSwitchCheckChecked"
              v-model="resultChk"
            />
            <label
              class="form-check-label"
              for="flexSwitchCheckChecked"
              style="font-weight: bold"
              >경기결과 기록하기</label
            >
          </div>

          <div
            v-show="resultChk"
            style="display: flex; justify-content: space-between"
          >
            <div class="div-write-score away">
              <div>
                <label for="awayTeam">원정팀</label>

                <select
                  class="form-select form-select-sm"
                  name="awayTeam"
                  id="awayTeam"
                  v-model="store.diary.awayTeam"
                >
                  <option selected disabled value="">팀을 선택해주세요</option>
                  <option value="0">선택 안함</option>
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
                <label for="awayScore">원정점수</label>
                <input
                  type="number"
                  class="form-control"
                  id="awayScore"
                  name="awayScore"
                  v-model="store.diary.awayScore"
                />
              </div>
            </div>
            <div class="div-write-score home">
              <div>
                <label for="homeTeam">홈팀</label>
                <select
                  class="form-select form-select-sm"
                  name="homeTeam"
                  id="homeTeam"
                  v-model="store.diary.homeTeam"
                >
                  <option selected disabled value="">팀을 선택해주세요</option>
                  <option value="0">선택 안함</option>
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
                <label for="homeScore">홈점수</label>
                <input
                  type="number"
                  class="form-control"
                  id="homeScore"
                  name="homeScore"
                  v-model="store.diary.homeScore"
                />
              </div>
            </div>
          </div>
        </div>
      </fieldset>
      <div class="div-regbtn">
        <div class="d-grid gap-2">
          <button
            class="btn btn-secondary"
            :disabled="disabled"
            type="button"
            @click="updateDiary"
          >
            수정하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useDiaryStore } from "@/stores/diary";
import { useUserStore } from "@/stores/user";
const store = useDiaryStore();
const storeU = useUserStore();

let userId = storeU.user.userId;

//사진 업로드
const fileUploaded = ref(store.diary.img);
const formData = new FormData();
const originalImg = (imgPath) => {
  return `../../assets/pic/userpic/${userId}/${imgPath}`;
};
const previewImage = function () {
  return new URL(`${originalImg(store.diary.img)}`, import.meta.url).href;
};

const uploadedImage = ref("");
const imgUpload = function (e) {
  const file = e.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      uploadedImage.value = e.target.result;
      fileUploaded.value = false;
    };
    reader.readAsDataURL(file);
    formData.append("img", file);
  }
};

//등록버튼 활성화

const disabled = computed(() => {
  return !(store.diary.title.length > 0);
});

//팀선택마다 로고 바꾸기
const getTeamPath = (url) => {
  const path = ref(`../../assets/icon/icons8-question-mark-96.png`);
  if (url == 0) {
    path.value = `../../assets/icon/icons8-question-mark-96.png`;
  } else if (url == 1) {
    path.value = `../../assets/pic/teamLogo/doosan.png`;
  } else if (url == 2) {
    path.value = `../../assets/pic/teamLogo/lg.png`;
  } else if (url == 3) {
    path.value = `../../assets/pic/teamLogo/hanhwa.png`;
  } else if (url == 4) {
    path.value = `../../assets/pic/teamLogo/kia.jpg`;
  } else if (url == 5) {
    path.value = `../../assets/pic/teamLogo/kiwoom.png`;
  } else if (url == 6) {
    path.value = `../../assets/pic/teamLogo/kt.jpg`;
  } else if (url == 7) {
    path.value = `../../assets/pic/teamLogo/lotte.png`;
  } else if (url == 8) {
    path.value = `../../assets/pic/teamLogo/nc.png`;
  } else if (url == 9) {
    path.value = `../../assets/pic/teamLogo/samsung.jpg`;
  } else if (url == 10) {
    path.value = `../../assets/pic/teamLogo/ssg.jpg`;
  }
  return path.value;
};

const getHomeLogo = computed(() => {
  let home = store.diary.homeTeam;

  return new URL(`${getTeamPath(home)}`, import.meta.url).href;
});
const getAwayLogo = computed(() => {
  let away = store.diary.awayTeam;

  return new URL(`${getTeamPath(away)}`, import.meta.url).href;
});

//경기결과 적을지 말지
const resultChk = ref(true);

const updateDiary = function () {
  // console.log(store.diary);
  const data = ref({});
  data.value = store.diary;
  formData.append(
    "diary",
    new Blob([JSON.stringify(data.value)], { type: "application/json" })
  );
  // console.log(formData);
  store.updateDiary(formData);
};
</script>

<style scoped>
@import url("../../assets/css/diaryWrite-Update.css");
</style>
