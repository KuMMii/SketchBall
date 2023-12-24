<template>
  <div>
    <div class="btn-header">
      <button class="btn-back" @click="$router.go(-1)">
        <img src="../assets/icon/icons8-arrow-30.png" alt="" />
      </button>
    </div>
    <RouterView />
    <TheFooterNav />
  </div>
</template>

<script setup>
import TheFooterNav from "../components/common/TheFooterNav.vue";
</script>

<style scoped>
/*  버튼 */
.btn-header {
  display: flex;
  justify-content: space-between;
}

.btn-back {
  top: 0px;
  background: inherit;
  border: none;
  box-shadow: none;
  border-radius: 0;
  padding: 0;
  overflow: visible;
  cursor: pointer;
  height: 40px;
}
</style>
