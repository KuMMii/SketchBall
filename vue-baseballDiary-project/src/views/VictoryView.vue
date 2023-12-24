<template>
  <div
    v-if="storeU.userMyPage.team && isLoadBackgroundVisible"
    class="load-background"
  >
    <div style="height: 48px">
      <span class="loader"></span>
    </div>
    <div
      style="color: white; font-size: 1rem; font-weight: bold; margin-top: 13px"
    >
      {{ fairy.nickname }}님의 기록을 분석 중입니다
    </div>
  </div>

  <!-- 팀이 있고 로딩 끝났을 때 -->
  <div class="div-vresult">
    <div
      v-if="storeU.userMyPage.team && !isLoadBackgroundVisible"
      class="vFairy"
    >
      <div id="capture" v-if="fairy.totalCount != 0">
        <div>
          <span class="data" id="nickname">{{ fairy.nickname }}</span>
          <span>님은</span>
        </div>
        <div class="div-right">
          <span class="data" id="year">{{ fairy.year }}년</span>
          <span>에 </span>
          <span style="font-size: 2.55rem; -webkit-text-stroke: 0.06rem black"
            >직관</span
          >
          <span>을</span>
        </div>
        <div>
          <span class="data" id="total">{{ fairy.totalCount }}회</span>
          <span> 하셨고, </span>
          <span
            style="
              font-size: 2.55rem;
              -webkit-text-stroke: 0.06rem #ffa505;
              color: #ffa505;
            "
            >승리</span
          >
          <span>는</span>
        </div>
        <div class="div-right">
          <span class="data" id="winning">{{ fairy.winningCount }}회 </span>
          <span>하셨어요!</span>
        </div>
        <div id="hide" v-if="!showResult" @click="show()">
          클릭으로 결과를 확인해보세요!
        </div>
        <div class="result-showing" v-show="showResult">
          <div>
            <span class="data" id="nickname">{{ fairy.nickname }}</span>
            <span>님의</span>
          </div>
          <div>
            <span>승률은 </span>
            <span class="data" id="percent">{{ fairy.percent }}%</span>
            <span>입니다</span>
          </div>
          <div class="fairy-result-vFairy" v-if="fairy.percent >= 70">
            <img src="@\assets\icon\fairy-w.png" alt="" />
            <p>
              <span>축하해요!</span>
              <span>승리요정입니다!</span>
            </p>
            <img src="@\assets\icon\fairy-m.png" alt="" />
          </div>
          <div class="fairy-result-vFairy" v-else>
            <p>
              패배요정이네요...
              <img src="@\assets\icon\crying-face.png" alt="" />
            </p>
          </div>
          <!-- 공유버튼 -->
          <div class="div-button-vFairy">
            <button
              id="downloadButton"
              class="btn btn-secondary"
              @click="downloadClicked"
            >
              결과 다운받기
            </button>
            <div id="kakao" @click="kakaoShare">
              <a id="kakaotalk-sharing-btn">
                <img
                  src="https://developers.kakao.com/assets/img/about/logos/kakaotalksharing/kakaotalk_sharing_btn_medium.png"
                  alt="카카오톡 공유 보내기 버튼"
                />
                <span>카카오톡 공유하기</span>
              </a>
            </div>
          </div>
        </div>
      </div>

      <div
        v-else-if="fairy.totalCount == 0 || fairy.totalCount == null"
        style="
          font-family: LINESeedKR-Bd;
          font-size: 1.4rem;
          text-align: center;
        "
      >
        <span
          >{{ fairy.nickname }}님의 {{ fairy.year }}년 직관 기록이 없네요.</span
        >
        <br />
        <span
          >직관 기록이 생기면 직관 승률은 물론,<br />
          내가 승리 요정인지도 알 수 있어요!🤗</span
        >
      </div>
    </div>
    <div v-else-if="!storeU.userMyPage.team" style="text-align: center">
      <span>승리 요정인지 궁금하시다면? 팀을 정해주세요!</span>
    </div>
  </div>
</template>

<script setup>
import { useDiaryStore } from "@/stores/diary";
import { useUserStore } from "@/stores/user";
import { onMounted, ref, watch, computed } from "vue";
import confetti from "https://cdn.skypack.dev/canvas-confetti";
import html2canvas from "html2canvas";
import axios from "axios";

const store = useDiaryStore();
const storeU = useUserStore();

const fairy = ref({});
watch(
  () => store.vFairy,
  (newdata) => {
    // fairy.value = newData;
    fairy.value = newdata;
    // console.log(fairy.value);
  }
);

const isLoadBackgroundVisible = ref(true);

onMounted(() => {
  store.getFairy();
  fairy.value = store.vFairy;
  storeU.getUserMyPage(storeU.user.userId);

  // Use setTimeout correctly to set isLoadBackgroundVisible to false after 2 seconds
  setTimeout(() => {
    isLoadBackgroundVisible.value = false;
  }, 1500);
});

const showResult = ref(false);

const show = () => {
  if (fairy.value.percent >= 70) confetti();
  showResult.value = true;
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

      imageUrl: "https://i.imgur.com/i85F4Hd.jpg",
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
.loader {
  position: relative;
  width: 108px;
  display: flex;
  justify-content: space-between;
}
.loader::after,
.loader::before {
  content: "";
  display: inline-block;
  width: 48px;
  height: 48px;
  background-color: #fff;
  background-image: radial-gradient(circle 14px, #0d161b 100%, transparent 0);
  background-repeat: no-repeat;
  border-radius: 50%;
  animation: eyeMove 2.5s infinite, blink 3.5s infinite;
}
@keyframes eyeMove {
  0%,
  10% {
    background-position: 0px 0px;
  }
  13%,
  40% {
    background-position: -15px 0px;
  }
  43%,
  70% {
    background-position: 15px 0px;
  }
  73%,
  90% {
    background-position: 0px 15px;
  }
  93%,
  100% {
    background-position: 0px 0px;
  }
}
@keyframes blink {
  0%,
  10%,
  12%,
  20%,
  22%,
  40%,
  42%,
  60%,
  62%,
  70%,
  72%,
  90%,
  92%,
  98%,
  100% {
    height: 48px;
  }
  11%,
  21%,
  41%,
  61%,
  71%,
  91%,
  99% {
    height: 18px;
  }
}

.load-background {
  background-color: black;
  opacity: 0.7;
  width: 495px;
  position: fixed;
  left: 0px;
  top: 0;
  height: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  right: 0;
  margin-left: auto;
  margin-right: auto;
}

.vFairy {
  font-family: KIMM_Bold;
  font-size: 2rem;
}

/* 분석 */
.div-right {
  /* border: 1px solid green; */
  text-align: end;
}
.result-showing {
  /* border: 1px solid blue; */
  margin-top: 30px;
}
.result-showing div:nth-child(2) {
  /* border: 1px solid purple; */
  text-align: center;
  font-size: 2.3rem;
}
.data {
  font-size: 3rem;
}

#nickname {
  color: #001f63;
  font-size: 3.2rem;
  -webkit-text-stroke: 0.06rem #001f63;
}
#total {
  color: #001f63;
  -webkit-text-stroke: 0.05rem #001f63;
}

#winning {
  /* color: #f8c038; */
  color: #ffa505;
  -webkit-text-stroke: 0.05rem #ffa505;
}

#percent {
  font-size: 3.2rem;
  color: #c30101;
  -webkit-text-stroke: 0.05rem #c30101;
}

/* 결과 */
#hide {
  height: 280px;
  text-align: center;
  margin-top: 40px;
  font-size: 1rem;
  line-height: 280px;
  cursor: pointer;
}
.fairy-result-vFairy {
  margin-top: 35px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.fairy-result-vFairy p {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 0;
}

/* .fairy-result-vFairy {
  display: flex;
} */
.fairy-result-vFairy img {
  width: 70px;
  height: 70px;
}

.div-button-vFairy {
  /* position: fixed;
  left: 0;
  right: 0;
  margin-left: auto;
  margin-right: auto;
  top: 762px; */
  width: 435px;
  display: flex;
  justify-content: space-evenly;
  margin-top: 50px;
  /* text-align: end; */
}

#kakao {
  font-size: 1rem;
  width: 185px;
  /* border: 1px solid black; */
  text-align: center;
  background-color: #fae100;
  border-radius: 11px;
  cursor: pointer;
}
#kakao a img {
  width: 40px;
}
</style>
