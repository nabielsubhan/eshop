# Eshop

### Nama   : Muhammad Nabiel Subhan
### NPM    : 2206081553
### Kelas  : AdPro B

# Tutorial-1
## Refleksi 1
Pada tutorial pertama ini, terdapat beberapa *clean code principles* yang sudah saya terapkan, yaitu `Meaningful Names` dengan memberikan penamaan variabel dan *function* yang digunakan pada tutorial ini dengan jelas, mudah dipahami, dan sesuai dengan konteks, seperti, `productId`, `productName`, `productQuantity`, `create`, `edit`, dan lain-lain.
Lalu, untuk aspek `function` juga sudah dituliskan sesuai dengan tujuan untuk menyelesaikan satu tugas dan penulisan code nya juga tidak panjang. Selain itu, penggunaan `Comments` sejauh ini juga belum diperlukan karena code yang dituliskan sudah mudah untuk dimengerti.

Untuk bagian yang dapat di-*improve* menurut saya adalah pada bagian `Error handling`, yaitu masih adanya penggunaan *return Null value* pada salah satu function. Mungkin sebagai cara untuk memperbaikinya adalah dengan menggunakan Exception sehingga jenis error yang akan diakibatkan bisa menjadi lebih eksplisit.

## Refleksi 2
1. Setelah membuat unit test, hal tersebut membuat saya menjadi lebih yakin dengan code yang telah saya buat karena program yang dibuat sudah sesuai dengan ekspektasi. Menurut saya, jumlah unit test pada suatu class dipengaruhi juga oleh banyaknya function yang ada pada class tersebut. Setidaknya untuk setiap function pada class tersebut perlu ada satu unit test untuk mengecek kebenaran kerja function tersebut sehingga *code coverage* nya bisa lebih tinggi. Meskipun begitu, tercapainya 100% pada *code coverage* masih belum bisa menjamin bahwa code kita terhindar dari error atau bug karena bisa saja terdapat logic error pada function kita.
2. Menurut saya, jika kita membuat class baru untuk membuat functional test tersebut akan membuat adanya *redundancy code* karena terdapat code yang duplikat pada bagian *setup procedures* dan *instance variables* yang digunakan. Hal ini akan menimbulkan masalah pada aspek *clean code* program kita. Oleh karena itu, menurut saya, cara yang dapat dilakukan untuk memperbaikinya adalah dengan menaruh test terbaru tersebut pada class yang sama karena antara kedua test tersebut masih terdapat hubungan yang sama, yaitu sama-sama menguji function createProduct. Dengan begitu, *redundancy code* bisa kita hindari sehingga kualitas code kita dalam aspek *clean code* masih bisa terjaga.

# Tutorial-2
## Refleksi
1. Code quality issues:
   - Unused import
     Menghilangkan semua import yang tidak digunakan di setiap file.
   - Class modifier
     Mengubah modifier class pada file ProductControllerTest yang sebelumnya `public` menjadi `default`.
   - Field Injection
     Mengubah inisialisasi objek yang sebelumnya menggunakan injeksi AutoWired menjadi inisialisasi objek secara langsung.

2. Menurut saya, implementasi sekarang sudah memenuhi kriteria CI/CD. Dengan adanya `ci.yml` akan membuat setiap kali kita melakukan push ke branch manapun akan dijalankan unit test secara otomatis sehingga Continuous Integration terpenuhi. Setelah berhasil melakukan unit test, code kita akan di cek kembali aspek *code security*-nya menggunakan actions pada `scorecard.yml` dan juga akan dicek
kualitas kode kita dengan actions di `sonarcloud.yml`. Setelah itu, dengan menggunakan Github Actions akan dilakukan deployment secara otomatis ke PaaS Koyeb sehingga Continuous Development juga terpenuhi. Dengan begitu, pada implementasi ini CI/CD sudah terpenuhi sehingga nantinya dapat dilakukan pengetesan dan proses deployment secara otomatis.

## Refleksi 3
1. Principles that applied in the current project
- Single Responsibility Principle (SRP)
  Prinsip ini menyatakan bahwa suatu kelas harus memiliki hanya satu fungsionalitas saja dan function-function di dalamnya juga haruslah yang memiliki kaitan jelas dengan class tersebut. Contohnya seperti pemisahan class CarController dengan ProductController.
- Open-Closed Principle (OCP)
  Prinsip ini menyatakan bahwa suatu class harus terbuka untuk perluasan dan tertutup untuk modifikasi sehingga untuk menambahkan suatu fitur baru tidak perlu mengubah kode pada class sebelumnya tetapi hanya perlu meng-extend class tersebut dan menambahkan fitur yang baru di sana. Sejauh ini, proyek ini belum terdapat proses meng-inherit class lain sehingga prinsip OCP masih terjaga.
- Liskov Substitution Principle (LSP)
  Prinsip ini menyatakan bahwa suatu superclass dan subtypes-nya perlu memiliki behavior yang konsisten sehingga suatu subtype bisa menggantikan superclass-nya. Pada proyek ini belum dilakukan proses meng-inherit class lain sehingga prinsip LSP juga masih terjaga.
- Interface Segregation Principle (ISP)
  Prinsip ini menyatakan bahwa kita perlu untuk menghindari membuat suatu interface yang kompleks dengan membaginya menjadi interface yang lebih kecil dan spesifik. Dengan begitu, client yang nantinya meng-implement interface tersebut tidak perlu membuat function-function yang tidak relevan. Contohnya pada proyek ini, interface CarService dan ProductService sudah memiliki method-method yang relevan sehingga prinsip ini terpenuhi.
- Dependency Inversion Principle (DIP)
  Prinsip ini menyatakan bahwa high-level modules jangan bergantung kepada low-level modules, tetapi keduanya seharusnya bergantung pada abstraction. Contohnya seperti penggunaan class interface sebagai data type dibandingkan dengan class yang meng-implement interface-nya.

2. Advantages of applying SOLID principles
- Kode yang lebih mudah dimengerti, terstruktur, dan bersih.
- Kode menjadi lebih fleksibel sehingga mudah diperluas dan diubah kedepannya.
- Memudahkan dalam melakukan testing.
- Memiliki skalabilitas yang baik sehingga lebih mudah dalam berkembang.
- Memfasilitasi reusabilitas kode.

3. Disadvantages of not applying SOLID principles
- Kesulitan dalam mengembangkan atau memperluas kode sehingga perlu merombak program jika terdapat fitur baru yang akan diimplementasikan.
- Kesulitan dalam melakukan testing karena komponen-komponen pada program memiliki ketergantungan yang tinggi dengan komponen lainnya.

# Refleksi 4
1. Menurut saya, menggunakan alur TDD ini akan memberikan beberapa manfaat, diantaranya adalah desain kode yang lebih baik, alur yang sistematis, serta mengurangi bug yang dapat muncul di program kita nantinya. Meskipun di awal kita perlu berandai-andai tentang cara kerja program kita dan menentukan segalam macam edge case yang mungkin terjadi, tetapi hal tersebut nantinya akan menghasilkan kodingan program yang meyakinkan sehingga memudahkan kita dalam proses pengembangan program kedepannya karena ada tes yang dapat dijadikan acuan apakah perubahan yang kita lakukan merubah fungsionalitas asli program kita atau tidak.

2. Menurut saya, test-test tersebut sudah menerapkan F.I.R.S.T. principle. Test-test tersebut sudah didesain untuk dapat berjalan dengan cepat. Lalu, test tersebut juga dibuat terisolasi dengan test lain sehingga hasil suatu test tidak memengaruhi hasil test lainnya. Test tersebut juga memberikan hasil yang konsisten sehingga dapat memberikan keyakinan bahwa kebenaran suatu fungsionalitas program sudah tercapai. Selain itu, penggunaan method assert dan method validasi lainnya sudah membantu kita untuk mengetahui apakah pengujian yang kita lakukan sudah berhasil atau tidak. Terakhir, test-test tersebut juga sudah meng-cover semua happy dan unhappy path sekaligus dengan berbagai edge case yang mungkin terjadi.
