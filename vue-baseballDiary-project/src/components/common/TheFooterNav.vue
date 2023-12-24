<template>
  <div id="footer">
    <div class="footer-child">
      <RouterLink :to="{ name: 'list' }">
        <button class="footer-button" @click="getList">
          <div>
            <img
              src="../../assets/icon/icons8-collage-100.png"
              alt=""
              class="footer-img12"
            />
            <p class="footer-text12">모아보기</p>
          </div>
        </button>
      </RouterLink>
    </div>
    <div class="footer-child">
      <RouterLink
        :to="{
          name: 'homeDetail',
          params: { daily: year + '-' + month + '-' + day },
        }"
      >
        <button class="footer-button">
          <div>
            <img
              src="../../assets/icon/icons8-집-104.png"
              alt=""
              class="footer-img12"
            />
            <p class="footer-text12">홈</p>
          </div>
        </button>
      </RouterLink>
    </div>
    <div class="footer-child">
      <RouterLink :to="{ name: 'victory' }">
        <button class="footer-button">
          <div>
            <img
              src="../../assets/icon/icons8-medal-96.png"
              alt=""
              class="footer-img3"
            />
            <p class="footer-text3">승리요정</p>
          </div>
        </button>
      </RouterLink>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useDiaryStore } from "../../stores/diary";

const router = useRouter();
const year = ref("");
const month = ref("");
const day = ref("");
const store = useDiaryStore();

onMounted(() => {
  year.value = new Date().getFullYear();
  month.value = new Date().getMonth() + 1;
  day.value = new Date().getDate();

  store.getDiaryList(year.value + "-" + month.value + "-" + day.value);
});

const getList = function () {
  store.getViewDiaryList("all");
};
</script>

<style scoped>
/* footer 전체 */
#footer {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  margin-left: auto;
  margin-right: auto;
  width: 495px;
  height: 80px;
  background-color: white;
  border-top: 1px solid #b8b8b8;
  display: flex;
}

/* footer의 세 개 (모아보기, 홈, 승리요정) */
.footer-child {
  border: 0px solid;
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* 모아보기, 홈 이미지 */
.footer-img12 {
  height: 30px;
}

/* 승리요정 이미지 */
.footer-img3 {
  height: 35px;
}

/* 모아보기, 홈, 승리요정 버튼(라우터 링크) */
.footer-button {
  border: none;
  align-items: center;
  background-color: transparent;
  height: 80px;
  width: 165px;
  padding: 12px 0px;
}

/* 모아보기, 홈 텍스트 */
.footer-text12 {
  padding: 5px 0px;
  font-size: 14px;
  font-weight: bold;
}

/* 승리요정 텍스트 */
.footer-text3 {
  font-size: 14px;
  font-weight: bold;
}
</style>
