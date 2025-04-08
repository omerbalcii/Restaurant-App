
# 🍽️ RestaurantApp

RestaurantApp, müşteri odaklı sipariş yönetimi sağlayan tam kapsamlı bir restoran yönetim sistemidir. Bu proje, daha önce .NET platformunda geliştirilmiş olup, **backend Java Spring Boot**, **frontend ise Angular** kullanılarak baştan yazılmıştır.


![Ekran görüntüsü 2025-04-07 225411](https://github.com/user-attachments/assets/95f62f58-47b9-47d8-85d3-fbfe448c8f97)
![Ekran görüntüsü 2025-04-07 225354](https://github.com/user-attachments/assets/32251a25-8943-43a9-acc0-939f914e94ed)

## 🔧 Teknolojiler

### Backend
- Java 17+
- Spring Boot
- JPA (Hibernate)
- PostgreSQL (veya tercihe göre başka bir RDBMS)
- RESTful API

### Frontend
- Angular 15+
- TypeScript
- Reactive Forms
- Bootstrap / Angular Material (varsa belirt)

## 📌 Özellikler

- 🧑‍🍳 **Müşteri Yönetimi (Customers)**  
  Yeni müşteri ekleme, mevcut müşterileri listeleme ve siparişlerle ilişkilendirme.

- 🧾 **Sipariş Yönetimi (Orders)**  
  - Sipariş oluşturma
  - Müşteriye bağlı sipariş listeleme
  - Toplam tutar, ödeme yöntemi, sipariş numarası gibi bilgilerin girilmesi

- 🍕 **Ürün Yönetimi (Items)**  
  - Menüdeki ürünlerin tanımı
  - Sipariş içerisinde ürün seçimi

- 🧮 **Sipariş Kalemleri (Order Items)**  
  - Siparişe birden fazla ürün ekleme
  - Her ürün için miktar ve toplam hesaplama

## 🚀 Kurulum

### Backend

```bash
cd RestaurantApp-Backend
./mvnw spring-boot:run
```

Varsayılan port: `http://localhost:8080`

### Frontend

```bash
cd RestaurantApp-Frontend
npm install
ng serve
```

Varsayılan port: `http://localhost:4200`

## 📬 API Uç Noktaları (Örnekler)

- `GET /orders` → Tüm siparişleri getirir  
- `POST /order/save` → Yeni sipariş oluşturur  
- `GET /customers` → Müşteri listesini getirir

## 🧪 Örnek Kullanım

Angular üzerinden sipariş oluşturulurken:
```ts
saveOrder(orderModel: Order) {
  var body = {
    orderSubDto: this.formData,
    orderItemModelDtoList: this.orderItemModel
  };
  return this.http.post(environment.apiUrl + 'order/save', body);
}
```

## ✍️ Notlar

- Tüm sipariş işlemleri `customerId`'ye göre gerçekleştirilir.
- Proje, bireysel olarak sıfırdan yeniden yazılmıştır.
- Geliştirme ortamı için uygun olacak şekilde yapılandırılmıştır.

## 📄 Lisans

Bu proje kişisel öğrenim ve portföy amaçlı geliştirilmiştir.
