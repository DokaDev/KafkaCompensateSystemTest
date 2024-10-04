CREATE TABLE tbl_order (
                           order_id BIGINT PRIMARY KEY AUTO_INCREMENT,  -- 주문 ID (고유 식별자)
                           user_id BIGINT NOT NULL,                     -- 사용자 ID
                           total_price BIGINT NOT NULL,                 -- 총 금액
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  -- 주문 생성 시간
);