# Kütüphane Yönetim Sistemi

Bu proje, bir kütüphanenin temel işlevlerini yerine getiren bir yönetim sistemidir. Sistem üç farklı kullanıcı tipi (Misafir, Üye ve Admin) için farklı yetkiler ve işlevler sunar.

## Özellikler

### Kitap Yönetimi
- ✅ Yeni kitap ekleme (Admin)
- ✅ Kitap bilgilerini güncelleme (Admin)
- ✅ Kitap silme (Admin)
- ✅ Kitap arama:
  - ID'ye göre
  - İsme göre
  - Yazara göre
  - Kategoriye göre

### Kategori Yönetimi
- ✅ Yeni kategori ekleme (Admin)
- ✅ Kategoriye göre kitap listeleme
- ✅ Tüm kategorileri görüntüleme

### Ödünç Alma Sistemi
- ✅ Kitap ödünç alma (Üye)
- ✅ Kitap iade etme (Üye)
- ✅ Kullanıcı başına maksimum 5 kitap limiti
- ✅ Otomatik fatura oluşturma:
  - Ödünç alma işleminde ücret tahsili
  - İade işleminde ücret iadesi

### Kullanıcı Yönetimi
- ✅ Farklı kullanıcı tipleri:
  - Misafir (sadece arama yapabilir)
  - Üye (ödünç alma ve iade işlemleri yapabilir)
  - Admin (tüm yönetim işlemlerini yapabilir)
- ✅ Kullanıcı bakiye takibi
- ✅ Ödünç alınan kitapların takibi

## Kullanım

### Misafir Olarak
- Kitap arama yapabilir.
- Mevcut kitapları görüntüleyebilir.
- Ödünç alma işlemi yapamaz.

### Üye Olarak
- Tüm arama işlemlerini yapabilir.
- Kitap ödünç alabilir (bakiye ve limit dahilinde).
- Kitap iade edebilir.
- Kendi ödünç aldığı kitapları listeleyebilir.
- Bakiye görüntüleyebilir.

### Admin Olarak
- Kitap ekleyebilir, güncelleyebilir, silebilir.
- Kategori ekleyebilir.
- Tüm kitap ve kategori listelerini görüntüleyebilir.
- Tüm arama işlemlerini yapabilir.

## Teknik Detaylar

### Fatura Sistemi
- Her ödünç alma işleminde otomatik fatura oluşturulur.
- Her iade işleminde iade faturası oluşturulur.
- Faturalar tarih, işlem tipi, kullanıcı bilgisi ve tutar bilgilerini içerir.

### Kısıtlamalar
- Bir kullanıcı maksimum 5 kitap ödünç alabilir.
- Kullanıcının bakiyesi kitap ücretini karşılamalıdır.
- Bir kitap aynı anda birden fazla kullanıcıya ödünç verilemez.

## Başlangıç

Sistem başlatıldığında örnek verilerle birlikte gelir:
- Temel kategoriler (Kurgu, Bilim, Tarih)
- Örnek kitaplar
- Test kullanıcısı (ID: 1, Bakiye: 100 TL) 