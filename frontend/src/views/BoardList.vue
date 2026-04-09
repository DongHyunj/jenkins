<template>
  <div class="container">
    <h2>게시글 목록</h2>

    <div class="toolbar">
      <button @click="$router.push('/board/reg')">글쓰기</button>
    </div>

    <table>
      <thead>
        <tr>
          <th style="width: 80px">번호</th>
          <th>제목</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="boards.length === 0">
          <td colspan="2" class="empty">등록된 게시글이 없습니다.</td>
        </tr>
        <tr v-for="board in boards" :key="board.idx">
          <td>{{ board.idx }}</td>
          <td>
            <span class="title-link" @click="$router.push(`/board/${board.idx}`)">
              {{ board.title }}
            </span>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import api from '../api/index.js'

const boards = ref([])

onMounted(async () => {
  try {
    const res = await api.get('/board/list')
    boards.value = res.data
  } catch (err) {
    alert('게시글 목록을 불러오지 못했습니다.')
    console.error(err)
  }
})
</script>

<style scoped>
.container { max-width: 800px; margin: 40px auto; padding: 0 20px; }
.toolbar { text-align: right; margin-bottom: 12px; }
button { padding: 8px 16px; cursor: pointer; background: #4a90d9; color: white; border: none; border-radius: 4px; }
table { width: 100%; border-collapse: collapse; }
th, td { border: 1px solid #ddd; padding: 10px 14px; text-align: left; }
th { background: #f4f4f4; }
.title-link { color: #1a73e8; cursor: pointer; }
.title-link:hover { text-decoration: underline; }
.empty { text-align: center; color: #999; }
</style>
