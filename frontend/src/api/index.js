import axios from 'axios'

// 환경변수로 백엔드 URL 주입 (K8S 배포 시 .env 또는 ConfigMap으로 변경)
const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8080',
  timeout: 10000,
})

export default api
