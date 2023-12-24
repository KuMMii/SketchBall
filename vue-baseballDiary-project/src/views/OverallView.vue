<template>
  <div>
    <p id="logo">
      <span style="margin-right: 6px">SketchBall</span>
      <img
        src="../assets/icon/logo/IMG_0859__4_-removebg-preview.png"
        alt=""
        class="favicon"
      />
    </p>
    <router-view></router-view>
    <TheFooterNav />
  </div>
</template>

<script setup>
import TheFooterNav from "../components/common/TheFooterNav.vue";
</script>

<style scoped>
#logo {
  font-family: "Nanum DaSiSiJagHae";
  font-size: 3rem;
  font-weight: 900;
  text-align: center;
}
.favicon {
  width: 30px;
  position: relative;
  top: -4px;
}
</style>
