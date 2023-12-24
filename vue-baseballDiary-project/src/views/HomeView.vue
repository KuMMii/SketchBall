<template>
	<div>
		<!-- <p id="logo">SketchBall</p> -->
		<HomeProfile />
		<HomeCalendar />
		<router-view></router-view>
		<div class="home-add-diary">
			<button @click="diaryWrite" class="home-add-button">
				<img src="../assets/icon/연필.png" width="26" alt="" />
				<span class="home-add-button-text">일기 적기</span>
			</button>
		</div>
	</div>
</template>

<script setup>
	import HomeCalendar from "../components/home/HomeCalendar.vue";
	import HomeDetail from "../components/home/HomeDetail.vue";
	import HomeProfile from "../components/home/HomeProfile.vue";

	import { onMounted, ref } from "vue";
	import { useRoute, useRouter } from "vue-router";
	import { useDiaryStore } from "../stores/diary";

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

		//default로 현재 날짜 뷰 나오게 하기기
		router.push({
			name: "homeDetail",
			params: { daily: year.value + "-" + month.value + "-" + day.value },
		});
	});

	const diaryWrite = function () {
		router.push({ name: "diaryWrite" });
	};
</script>

<style scoped>
	/* #logo {
		font-family: "Nanum DaSiSiJagHae";
		font-size: 3rem;
		font-weight: 900;
		text-align: center;
	} */
	.home-add-button {
		position: fixed;
		bottom: 100px;
		left: 310px;
		right: 0;
		margin-left: auto;
		margin-right: auto;
		/* left: 20%;
  bottom: 105px; */
		border: 1px solid #878787;
		padding: 5px 10px;
		border-radius: 20px;
		align-items: center;
		background-color: white;
		box-shadow: 2px 2px 4px #878787ba;
		height: 45px;
		width: 130px;
	}

	.home-add-button-text {
		font-weight: bold;
		padding: 3px 0px 0px 10px;
	}
</style>
