
<center>
<img width="437px" src="https://i.hizliresim.com/mv4b6zv.png" alt="bEventTimer Logo">

# **bEventTimer**
*(Etkinlik Süre Sayaç Sistemi)*

## Konsept
Eklentimizin konsepti oldukça basit. Basit olmasına rağmen **güzel ve mantıklı** bir sistem olarak görüyorum. Bundan dolayı yapmak istedim. Yapmamız gereken tek ayar eklentimizin ayar dosyasına **etkinliklerimizin saat sürelerini** girmek. Eklentimiz otomatik olarak bir sonraki **etkinliğe** kalan süreyi hesaplar ve **placeholder** olarak bu süreyi döndürür. Ek olarak eklentide **bossbar** desteği de bulunmaktadır. Müşterilerimiz kullandıkları tab eklentisiyle beraber **bossbar** oluşturabilir veyahut eklentinin kendi bossbar desteğini kullanabilirler.
</center>

---

<center>

### Video
[![YouTube Video](https://i.hizliresim.com/4rvba7h.png)](https://youtube.com/CkLjeO0rHo4)

### Görseller
![1728027581687](https://i.hizliresim.com/4rvba7h.png)
![1728027647307](https://i.hizliresim.com/4rvba7h.png)

### Eklenti Örnek Ayar Dosyası
```yaml
settings:
  timeformat:
    v1: '%s% saniye'
    v2: '%m% dakika, %s% saniye'
    v3: '%h% saat, %m% dakika, %s% saniye'
  bossbar:
    status: true
    colorNow: RED
    colorTime: YELLOW
    style: SEGMENTED_20

events:
  '0':
    time: '10:40'
    textTime: '&eEjderha etkinliğine &f%time% &ekaldı!'
    textNow: '&eEjderha etkinliği &fbaşladı!'
    cmds:
      - 'say Ejderha Etkinliğini Başlatma Komutu'
  '1':
    time: '04:15'
    textTime: '&eBalık etkinliğine &f%time% &ekaldı!'
    textNow: '&eBalık etkinliği &fbaşladı!'
    cmds:
      - 'say Balık Etkinliğini Başlatma Komutu'
  '2':
    time: '04:17'
    textTime: '&eKasa etkinliğine &f%time% &ekaldı!'
    textNow: '&eKasa etkinliği &fbaşladı!'
    cmds:
      - 'say Kasa Etkinliğini Başlatma Komutu'
```
</center>

---

## Detaylar

- **Async timer** olarak kodlanmıştır. Herhangi bir **lag/optimizasyon sorunu/tick kaybına** yol açmaz.
- Kurulumu çok basittir. Yapılması gereken sadece **etkinlik süresinin** ayar dosyasına girilmesidir.
- Eklentinin kendi **bossbar** desteği vardır. Ek **bossbar** eklentisi kullanılmasına gerek yoktur.
- **PlaceholderAPI** desteği vardır. **Placeholder** değerlerini kullanabilirsiniz. (Menülerde vs.)
- Eklenti **1.16.5-1.21** sürümlerini desteklemektedir.
- **Bossbar** rengi/şekli ayarlanabilir. Etkinlik aktifken ve etkinliğe kalan süre modlarının bossbar renkleri değiştirilebilir.
- **Bossbar** yazıları değiştirilebilir.
- Etkinlik zamanı geldiğinde etkinliğin başlatılma komutunu ayar dosyasına girebilirsiniz. Etkinlik süresi geldiğinde başlayacaktır.

---

### Detaylı bilgi için discord sunucumuza katılabilirsiniz.

[Buhari Yazılım WebSite](https://xbuhari.com.tr/)  
[Buhari Yazılım Discord Sunucusu](https://discord.gg/buhari)
</center>
