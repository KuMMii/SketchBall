import { ref, computed } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_DIARY_API = `http://localhost:8081/diary`;
const REST_FAIRY_API = `http://localhost:8081/diary/victory`;

export const useDiaryStore = defineStore("diary", () => {
  //일기 하나 상세보기
  const diary = ref({});
  const getDiaryDetail = function (diaryId) {
    axios.get(`${REST_DIARY_API}/${diaryId}`).then((res) => {
      diary.value = res.data;
    });
  };

  //오직 이미지
  const uploadImage = function (formData) {
    return axios
      .post(`${REST_DIARY_API}/imgUpload`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((response) => {
        // Assuming your backend returns the URL of the uploaded image
        return response.data;
      })
      .catch((error) => {
        console.error("Error uploading image:", error);
      });
  };

  //일기 추가
  const writeDiary = function (formData) {
    axios
      .post(REST_DIARY_API, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        router.push({
          name: "home",
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  //일기 수정
  const updateDiary = function (formData) {
    axios
      .put(REST_DIARY_API, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then(() => {
        router.push({
          name: "diaryDetail",
          params: { diaryId: diary.value.diaryId },
        });
      });
  };

  //일기 삭제
  const deleteDiary = function () {
    axios.delete(`${REST_DIARY_API}/${diary.value.diaryId}`).then((res) => {
      router.push({ name: "home" });
    });
  };

  //달력 가져오기
  const week = ref([]);
  const getWeek = function () {
    axios
      .get(`${REST_DIARY_API}/calendar`)
      .then((res) => {
        if (week.value.length === 0) {
          for (let i = 0; i < 7; i++) {
            week.value.push({
              year: res.data[i].split("-")[0],
              month: res.data[i].split("-")[1],
              day: res.data[i].split("-")[2],
            });
          }
        }
      })
      .then(() => {
        // console.log(week.value);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  //스탬프 가져오기
  const stamp = ref([]);
  const getStamp = function (userId) {
    axios
      .get(`${REST_DIARY_API}/calendar/stamp/${userId}`)
      .then((res) => {
        stamp.value = [];
        for (let i = 0; i < res.data.length; i++) {
          stamp.value.push(res.data[i].split("-")[2]);
        }
        // if (stamp.value.length === 0) {
        //   for (let i = 0; i < res.data.length; i++) {
        //     stamp.value.push(res.data[i].split("-")[2]);
        //   }
        // }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  //그날의 일기 가져오기
  const listDiary = ref([]);
  const getDiaryList = function (date) {
    // console.log(date);
    axios
      .get(`${REST_DIARY_API}/calendar/${date}`)
      .then((res) => {
        listDiary.value = [];
        for (let i = 0; i < res.data.length; i++) {
          // console.log(res.data[i]);
          listDiary.value.push(res.data[i]);
        }
        // console.log(listDiary.value);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  //승리요정 정보 받아오기
  const vFairy = ref({});
  const getFairy = function () {
    axios.get(`${REST_FAIRY_API}`).then((res) => {
      vFairy.value = res.data;
      // console.log(vFairy.value);
    });
  };

  //모아보기
  const listVeiwDiary = ref([]);
  const getViewDiaryList = function (period) {
    axios
      .get(`${REST_DIARY_API}/list/${period}`)
      .then((res) => {
        console.log(period);
        listVeiwDiary.value = [];
        for (let i = 0; i < res.data.length; i++) {
          listVeiwDiary.value.push(res.data[i]);
        }
        console.log(listVeiwDiary.value);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return {
    diary,
    getDiaryDetail,
    writeDiary,
    updateDiary,
    deleteDiary,
    getWeek,
    week,
    stamp,
    getStamp,
    listDiary,
    getDiaryList,
    vFairy,
    getFairy,
    listVeiwDiary,
    getViewDiaryList,
    uploadImage,
  };
});
