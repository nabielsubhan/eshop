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