# FinanceFlow AI 📈💰

![FinanceFlow AI Banner](financeflow_ai_banner.png)

**FinanceFlow AI**, kişisel finans yönetimini yapay zeka ile birleştiren, modern ve akıllı bir Android uygulamasıdır. Banka ekstrelerinizi manuel olarak girmek yerine, yapay zekanın gücünü kullanarak saniyeler içinde analiz edin ve finansal koçunuzdan tavsiyeler alın.

---

## ✨ Özellikler | Features

### 🤖 Yapay Zeka Destekli Analiz (AI-Powered Analysis)
- **PDF Ekstre Analizi:** Banka ekstrelerinizi (PDF) yükleyin; Gemini veya OpenAI modelleri işlemleri otomatik olarak ayıklasın, kategorize etsin ve sisteme kaydetsin.
- **Kişisel Finans Koçu:** Harcamalarınızı analiz eden ve size özel, gerçekçi finansal tavsiyeler veren yerleşik bir yapay zeka asistanı.
- **Esnek AI Sağlayıcıları:** Gemini, OpenAI veya özel base URL desteği ile Groq, OpenRouter gibi OpenAI uyumlu herhangi bir servisi kullanabilme imkanı.

### 📊 Finansal Takip (Financial Tracking)
- **Hızlı Giriş:** İşlemleri manuel olarak saniyeler içinde ekleyin.
- **Kategori Tahmini:** Harcama yaptığınız mekan ismine göre yapay zeka kategoriyi (Market, Teknoloji, Eğlence vb.) sizin için tahmin eder.
- **Trend Analizi:** Harcama ve gelir trendlerinizi görsel olarak takip edin.

### 🎨 Modern UI/UX
- **Jetpack Compose:** Tamamen modern, hızlı ve akıcı kullanıcı arayüzü.
- **Karanlık Tema Desteği:** Şık ve göz yormayan tasarım.
- **Dinamik Renkler:** Modern estetik ve mikro-animasyonlar.

---

## 🛠️ Teknik Yığın | Tech Stack

- **Dil:** Kotlin
- **UI Framework:** Jetpack Compose
- **Veritabanı:** Room Persistence Library
- **Bağımlılık Enjeksiyonu:** Hilt (Dagger)
- **Asenkron Programlama:** Coroutines & Flow
- **Ağ:** Ktor Client & OkHttp
- **AI Entegrasyonu:** Google Generative AI SDK & OpenAI API (Custom implementation)
- **PDF İşleme:** iTextPDF

---

## 🚀 Kurulum | Setup

1. Bu depoyu klonlayın:
   ```bash
   git clone https://github.com/kullanici_adi/native.git
   ```
2. Projeyi Android Studio ile açın.
3. Uygulama içindeki **Command Center (Ayarlar)** kısmından:
   - Kullanmak istediğiniz AI sağlayıcısını seçin.
   - API anahtarınızı girin.
   - (Opsiyonel) Özel bir Model Adı veya Base URL tanımlayın.
4. Derleyin ve çalıştırın!

---

## 📸 Ekran Görüntüleri | Screenshots

*(Uygulamanıza ait ekran görüntülerini buraya ekleyebilirsiniz)*

---

## 🛡️ Gizlilik | Privacy

Tüm finansal verileriniz Android'in yerel **Room** veritabanında saklanır. Yapay zeka analizi için gönderilen veriler sadece analiz amaçlı kullanılır ve tarafımızca sunucularda depolanmaz.

---

## 🤝 Katkıda Bulunma | Contributing

Katkılarınızı bekliyoruz! Herhangi bir öneri veya hata bildirimi için lütfen bir `issue` açın veya `pull request` gönderin.

---

**FinanceFlow AI** - *Cebinizin Yapay Zeka Zekası.* 🚀
