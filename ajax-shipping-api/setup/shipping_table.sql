CREATE TABLE tbl_shipping (
                              shipping_id BIGINT PRIMARY KEY AUTO_INCREMENT,  -- 배송 ID (고유 식별자)
                              order_id BIGINT NOT NULL,                       -- 주문 ID (해당 배송과 연결된 주문)
                              status ENUM('PENDING', 'SHIPPED', 'DELIVERED', 'FAILED') NOT NULL,  -- 배송 상태 (ENUM 타입)
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- 배송 생성 시간
                              shipping_address VARCHAR(255) NOT NULL          -- 배송 주소
);