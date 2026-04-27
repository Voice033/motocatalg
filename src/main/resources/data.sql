--バイクのデータ
INSERT INTO m_motocycle( moto_no, moto_name, seat_height, cylinder, cooling, price, comment, brand_id, version, ins_dt, upd_dt) VALUE ( 1, 'GB350', 800, 1, '空冷', 550000, 'エンジンのとことこ音がいい', '01', 1, now(), null); 
INSERT INTO m_motocycle( moto_no, moto_name, seat_height, cylinder, cooling, price, comment, brand_id, version, ins_dt, upd_dt) VALUE ( 2, 'Z900RS', 800, 4, '水冷', 1260000, 'エンジン音が低く紳士的', '02', 1, now(), null);
INSERT INTO m_motocycle( moto_no, moto_name, seat_height, cylinder, cooling, price, comment, brand_id, version, ins_dt, upd_dt) VALUE ( 3, 'W800 CAFE', 790, 2, '水冷', 1100000, 'エンジン音は思ったよりかっこいい', '02', 1, now(), null); 
INSERT INTO m_motocycle( moto_no, moto_name, seat_height, cylinder, cooling, price, comment, brand_id, version, ins_dt, upd_dt) VALUE ( 4, 'YZF-R1', 100, 4, '水冷', 1500000, '見た目がかっこいい。いかにもスーパースポーツ。', '03', 1, now(), null);
INSERT INTO m_motocycle( moto_no, moto_name, seat_height, cylinder, cooling, price, comment, brand_id, version, ins_dt, upd_dt) VALUE ( 5, 'Rebel250', 690, 1, '水冷', 599500, '見た目はクルーザー音はめっちゃ軽い。単気筒のとことこもない。', '01', 1, now(), null); 
INSERT INTO m_motocycle( moto_no, moto_name, seat_height, cylinder, cooling, price, comment, brand_id, version, ins_dt, upd_dt) VALUE ( 6, 'Rebel500', 790, 2, '水冷', 799700, 'まだよくわからない', '01', 1, now(), null);
INSERT INTO m_motocycle( moto_no, moto_name, seat_height, cylinder, cooling, price, comment, brand_id, version, ins_dt, upd_dt) VALUE ( 7, 'SR400 Final Edition', 800, 1, '空冷', 605000, 'エンジンのどこどこ感がいい。', '03', 1, now(), null); 
INSERT INTO m_motocycle( moto_no, moto_name, seat_height, cylinder, cooling, price, comment, brand_id, version, ins_dt, upd_dt) VALUE ( 8, 'Z900RS CAFE', 820, 4, '水冷', 1290000, '見た目かっこいいけど後部シート小さい', '02', 1, now(), null);
INSERT INTO m_motocycle( moto_no, moto_name, seat_height, cylinder, cooling, price, comment, brand_id, version, ins_dt, upd_dt) VALUE ( 9, 'V7 III Racer 10th ANNIVERSARY', 770, 2, '空冷', 1375000, '珍しい見た目', '05', 1, now(), null); 


--バイクメーカーのデータ
INSERT INTO m_brand (brand_id, brand_name) VALUES ('01', 'Honda');
INSERT INTO m_brand (brand_id, brand_name) VALUES ('02', 'Kawasaki');
INSERT INTO m_brand (brand_id, brand_name) VALUES ('03', 'Yamaha');
INSERT INTO m_brand (brand_id, brand_name) VALUES ('04', 'Suzuki');
INSERT INTO m_brand (brand_id, brand_name) VALUES ('05', 'moto guzzi');
