package com.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingExample implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.trace("TRACE: Bắt đầu xử lý logic chi tiết từng bước...");
        log.debug("DEBUG: Giá trị biến x = {}", 42);
        log.info("INFO: Ứng dụng khởi động thành công");
        log.warn("WARN: Tài nguyên sắp hết dung lượng");
        log.error("ERROR: Gặp lỗi khi lưu dữ liệu vào database");
    }
}
