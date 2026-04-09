<template>
  <div class="container">
    <h2>게시글 작성</h2>

    <div class="form">
      <label>제목</label>
      <input v-model="title" type="text" placeholder="제목을 입력하세요" />

      <label>내용</label>
      <textarea v-model="content" rows="10" placeholder="내용을 입력하세요" />

      <div class="toolbar">
        <button class="cancel" @click="$router.push('/board/list')">취소</button>
        <button class="submit" @click="submit">저장</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '../api/index.js'

const router = useRouter()
const title = ref('')
const content = ref('')

async function submit() {
  if (!title.value.trim()) {
    alert('제목을 입력해주세요.')
    return
  }
  if (!content.value.trim()) {
    alert('내용을 입력해주세요.')
    return
  }

  try {
    await api.post('/board/reg', {
      title: title.value,
      content: content.value,
    })
    alert('게시글이 성공적으로 등록되었습니다.')
    router.push('/board/list')
  } catch (err) {
    alert('게시글 등록 중 오류가 발생했습니다.')
    console.error(err)
  }
}
</script>

<style scoped>
.container { max-width: 800px; margin: 40px auto; padding: 0 20px; }
.form { display: flex; flex-direction: column; gap: 8px; margin-top: 20px; }
label { font-weight: bold; }
input, textarea {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
  width: 100%;
  box-sizing: border-box;
}
textarea { resize: vertical; }
.toolbar { display: flex; justify-content: flex-end; gap: 8px; margin-top: 12px; }
button { padding: 9px 20px; cursor: pointer; border: none; border-radius: 4px; font-size: 14px; }
.submit { background: #4a90d9; color: white; }
.cancel { background: #aaa; color: white; }
</style>
