# 20251216-GH300

Python Web 版本的 TODO 待辦事項網站

## 特色功能

- ✨ 使用 Bootstrap 5 打造現代化響應式介面
- 🎨 整合 FontAwesome 圖示庫
- ✅ 新增、完成、刪除待辦事項
- 📊 即時統計待辦事項狀態
- 🧹 一鍵清除已完成項目
- 🌈 漂亮的漸層背景和動畫效果

## 技術棧

- **後端**: Flask 3.0
- **前端**: Bootstrap 5.3.2
- **圖示**: FontAwesome 6.5.1

## 安裝步驟

1. 安裝相依套件：
```bash
pip install -r requirements.txt
```

2. 執行應用程式：
```bash
python app.py
```

若需要開啟除錯模式（僅供開發使用）：
```bash
FLASK_DEBUG=true python app.py
```

3. 開啟瀏覽器訪問：
```
http://localhost:5000
```

## 使用說明

1. **新增待辦事項**：在輸入框中輸入任務內容，點擊「新增」按鈕
2. **完成任務**：點擊綠色的勾選按鈕標記任務為已完成
3. **取消完成**：點擊黃色的復原按鈕取消已完成狀態
4. **刪除任務**：點擊紅色的垃圾桶按鈕刪除任務
5. **清除已完成**：點擊底部的「清除已完成項目」按鈕一次清除所有已完成的任務

## 專案結構

```
.
├── app.py              # Flask 應用程式主檔案
├── requirements.txt    # Python 相依套件
├── templates/
│   └── index.html     # 前端 HTML 模板
└── README.md          # 專案說明文件
```

## 授權

MIT License