<template>
  <div class="container">
    <div v-if="error" class="error">{{ error }}</div>

    <div v-else-if="board">
      <h2>{{ board.title }}</h2>
      <hr />
      <p class="content">{{ board.content }}</p>
    </div>

    <div v-else class="loading">불러오는 중...</div>

    <div class="toolbar">
      <button @click="$router.push('/board/list')">목록보기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import api from '../api/index.js'

const route = useRoute()
const router = useRouter()
const board = ref(null)
const error = ref('')

onMounted(async () => {
  const idx = route.params.idx
  try {
    const res = await api.get(`/board/${idx}`)
    board.value = res.data
  } catch (err) {
    if (err.response?.status === 404) {
      error.value = '게시글을 찾을 수 없습니다.'
      setTimeout(() => router.push('/board/list'), 2000)
    } else {
      error.value = '데이터를 불러오는 중 오류가 발생했습니다.'
    }
    console.error(err)
  }
})
</script>

<style scoped>
.container { max-width: 800px; margin: 40px auto; padding: 0 20px; }
h2 { margin-bottom: 8px; }
.content { white-space: pre-wrap; line-height: 1.7; margin: 20px 0; }
.toolbar { margin-top: 24px; }
button { padding: 8px 16px; cursor: pointer; background: #666; color: white; border: none; border-radius: 4px; }
.error { color: red; padding: 16px; text-align: center; }
.loading { text-align: center; color: #999; padding: 40px; }
</style>
