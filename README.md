# 📺 Exact Science Faculty TV

> A Smart TV display app for the Faculty of Exact Sciences – University of Bechar  
> Built using **Jetpack Compose** and **Firebase**, this app automatically showcases quotes, upcoming events, and institutional links on a dedicated display screen.

<p align="center">
  <img src="https://github.com/user-attachments/assets/df3af1f5-4695-44b7-94d4-be612fcac220" alt="App Preview" width="600"/>
</p>

---

## 🎯 Features

- 🔁 **Auto-scrolling Carousel** for upcoming events  
- 🕒 **Live Clock Display** (GMT+1)  
- 💬 **Inspirational Quotes** pulled from Firestore  
- 📅 **Dynamic Event Cards**: title, short description, and date  
- 📸 **Faculty Branding & Logos**  
- 📲 **QR Code Integration** to social media and university website  

---

## 🔧 Tech Stack

- [Jetpack Compose](https://developer.android.com/jetpack/compose)  
- [Firebase Firestore](https://firebase.google.com/docs/firestore)  
- [Firebase Storage](https://firebase.google.com/docs/storage)  
- [Kotlin](https://kotlinlang.org/)  
- Android Smart TV (Deployment)  

---

## 📂 Firebase Data Structure

### 🔹 `events` Collection

```json
{
  "current": false,
  "date": "2025-06-22",
  "description": "A seminar on the use of Artificial Intelligence in modern classrooms.",
  "id": "",
  "time": {
    "startingTime": "",
    "endingTime": ""
  },
  "title": "AI in Education"
}
````

### 🔹 `quotes` Collection

```json
{
  "text": "Great work needs passion."
}
```

---

## 🖥️ Usage & Deployment

This application runs on a Smart TV in a continuous display mode.
It's intended for **in-house usage** in faculty halls or entrance areas.
Content is updated live through Firestore without needing to recompile or redeploy the app.

---

## 📌 Notes

* 🕒 Time is synced to **GMT+1**
* 🔁 Carousel is built using **Jetpack Compose** and scrolls automatically
* 🔐 Firebase rules and environment variables are stored securely and not exposed in this repo
