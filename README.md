# ddos
demo  tool for ddos HTTP flood,slowloris

để http flood thi chỉ cần tạo 1 vòng lặp vô hạn mở các kết  nối đến nạn nhân và gửi các thông điệp

sử dụng đa luồng tăng khả năng tấn công tuy nhiên hạn chế mở quá nhiều luồng sẽ dẫn đến giảm hiệu năng

thông kê (lấy từ quá tình thực tập kĩ thuật kì trước)với máy tính HĐH win 7 64 bit , java 8 thì 2 đến 3 luông cho hiệu quả cao nhất

slowloris dùng 1 mảng chứa kết nối ,dùng 1 số trang web nhỏ để test với 1 kết nối (quá trình test ko thể gây ảnh hưởng đến trang web) 
thông thường có timeout  là 2-3s  tổng thời gian chờ phản hồi tối đa vẫn chưa kiểm tra được

bản báo cáo chuẩn trong link dưới

https://drive.google.com/open?id=1TM2f6YF8KMvOs9VzNBhm220H9SYmV5zD

LTU14 -Đặng Công Cẩn

