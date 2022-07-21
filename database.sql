CREATE DATABASE projek_kasir_java;
USE projek_kasir_java;
-- STATUS:
--  0 = TIDAK AKTIF
--  1 = AKTIF
CREATE TABLE kasir (
  ID VARCHAR(12) NOT NULL,
  password VARCHAR(30) NOT NULL,
  nama VARCHAR(50) NOT NULL,
  jk CHAR(1) NOT NULL,
  telp VARCHAR(14) NOT NULL,
  status INT(1) NOT NULL,
  CONSTRAINT pk_kasir PRIMARY KEY(ID)
);
-- STATUS:
--  0 = BUKAN MEMBER
--  1 = MEMBER
CREATE TABLE pelanggan (
  ID VARCHAR(12) NOT NULL,
  nama VARCHAR(50) NOT NULL,
  telp VARCHAR(14) NOT NULL,
  status INT(1) NOT NULL,
  alamat TEXT NOT NULL,
  CONSTRAINT pk_pelanggan PRIMARY KEY(ID)
);
-- STATUS:
--  0 = ADA
--  0 = TIDAK ADA
CREATE TABLE barang (
  ID VARCHAR(12) NOT NULL,
  nama VARCHAR(50) NOT NULL,
  harga INT(10) NOT NULL,
  deskripsi TEXT NOT NULL,
  status INT(1) NOT NULL,
  CONSTRAINT pk_barang PRIMARY KEY(ID)
);
CREATE TABLE pesanan (
  ID VARCHAR(12) NOT NULL,
  tanggal DATE NOT NULL,
  pelanggan VARCHAR(12) NOT NULL,
  CONSTRAINT pk_pesanan PRIMARY KEY(ID),
  CONSTRAINT fk_pelanggan FOREIGN KEY(pelanggan) REFERENCES pelanggan(ID)
);
CREATE TABLE detail_pesanan (
  id_pesanan VARCHAR(12) NOT NULL,
  barang VARCHAR(12) NOT NULL,
  jumlah INT(5) NOT NULL,
  CONSTRAINT fk_id_pesanan FOREIGN KEY(id_pesanan) REFERENCES pesanan(ID),
  CONSTRAINT fk_barang FOREIGN KEY(barang) REFERENCES barang(ID)
);

INSERT INTO kasir (ID, password, nama, jk, telp, status) VALUES
('memet', 'memet123', 'Memet', 'L', '081234567890', 1),
('ujang', 'ujang321', 'Ujang', 'L', '081234567891', 1);
INSERT INTO pelanggan (ID, nama, telp, status, alamat) VALUES
('A001', 'Anonymus', '-', 0, '');
INSERT INTO barang (ID,  nama, harga, deskripsi, status) VALUES
('A001', 'Nasi Goreng Spesial', 18000, 'Nasi goreng ditambah dengan telor ceplok dan sosis', 1),
('A002', 'Bakso', 15000, 'Bakso', 1);