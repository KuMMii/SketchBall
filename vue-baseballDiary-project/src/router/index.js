import { createRouter, createWebHistory } from "vue-router";

import StartView from "@/views/StartView.vue";
import OverallView from "@/views/OverallView.vue";
import HomeView from "@/views/HomeView.vue";
import DiaryWrite from "@/components/diary/DiaryWrite.vue";
import DiaryUpdate from "@/components/diary/DiaryUpdate.vue";
import DiaryDetail from "@/components/diary/DiaryDetail.vue";
import ListView from "@/views/ListView.vue";
import VictoryView from "@/views/VictoryView.vue";
import UserView from "@/views/UserView.vue";
import UserDetail from "@/components/user/UserDetail.vue";
import UserUpdate from "@/components/user/UserUpdate.vue";
import SignUp from "@/components/user/SignUp.vue";
import HomeDetail from "@/components/home/HomeDetail.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "start",
      component: StartView,
    },
    {
      path: "/overall",
      name: "overall",
      component: OverallView,
      children: [
        {
          path: "home",
          name: "home",
          component: HomeView,
          children: [
            {
              path: ":daily",
              name: "homeDetail",
              component: HomeDetail,
            },
          ],
        },
        {
          path: "list",
          name: "list",
          component: ListView,
        },
        {
          path: "victory",
          name: "victory",
          component: VictoryView,
        },
      ],
    },
    {
      path: "/signup",
      name: "signup",
      component: SignUp,
    },

    {
      path: "/diaryWrite",
      name: "diaryWrite",
      component: DiaryWrite,
    },
    {
      path: "/diaryUpdate",
      name: "diaryUpdate",
      component: DiaryUpdate,
    },
    {
      // path: "/diaryDetail",
      //잘 가는지를 보기 위해 아래 코드 주석한 것
      path: "/diaryDetail/:diaryId",
      name: "diaryDetail",
      component: DiaryDetail,
    },
    {
      path: "/user",
      name: "user",
      component: UserView,
      children: [
        {
          // path: "userDetail",
          path: "userDetail/:userId",
          name: "userDetail",
          component: UserDetail,
        },
        {
          path: "userUpdate",
          name: "userUpdate",
          component: UserUpdate,
        },
      ],
    },
  ],
});

export default router;
