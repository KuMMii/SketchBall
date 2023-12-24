<template>
  <div class="page-container">
    <div class="app-container">
      <RouterView />
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.page-container {
  background-color: rgb(59, 59, 59);
}

.app-container {
  max-width: 495px;
  height: 100vh;
  margin: 0 auto;
  padding: 10px 30px 0px 30px;
  overflow-x: hidden;
  background-color: #ffffff;
  padding-bottom: 80px;
  /* position: relative; */
  border: 1px solid;
}

@font-face {
  font-family: "LINESeedKR-Bd";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2")
    format("woff2");
  font-weight: 700;
  font-style: normal;
}
@font-face {
  font-family: "Nanum DaSiSiJagHae";
  src: url("assets/fonts/Nanum-DaSiSiJagHae.ttf") format("truetype");
  font-weight: bold;
}
@font-face {
  font-family: "neurimboGothicRegular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/neurimboGothicRegular.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "KIMM_Bold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2212@1.0/KIMM_Bold.woff2")
    format("woff2");
  font-weight: 700;
  font-style: normal;
}

div {
  font-family: LINESeedKR-Bd;
}
</style>
