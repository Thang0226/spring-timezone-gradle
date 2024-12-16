package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {
	@GetMapping("/world-clock")
	public String getTimeByTimezone(ModelMap model, @RequestParam(name = "city", required = false,
			defaultValue = "Asia/Ho_Chi_Minh") String city) {

		// Lấy ra ngày hiện tại, của thành phố hiện tại
		Date date = new Date();
		// Lấy ra time zone hiện tại của thành phố hiện tại
		TimeZone local = TimeZone.getDefault();
		// Lấy ra time zone của thành phố cần xác định
		TimeZone locale = TimeZone.getTimeZone(city);
		// Tính thời gian hiện tại của thành phố cần xác định
		long localeTime = date.getTime() + (locale.getRawOffset() - local.getRawOffset());
		// Cài đặt lại thời gian cho biến date thành thời gian hiện tại của 1 thành phố cụ thể
		date.setTime(localeTime);

		model.addAttribute("date", date);
		model.addAttribute("city", city);
		return "index";
	}
}
