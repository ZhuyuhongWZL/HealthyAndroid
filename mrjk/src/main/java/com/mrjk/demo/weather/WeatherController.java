package com.mrjk.demo.weather;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @RequestMapping("/weather")
    public String  getWeather(@RequestParam(value="city",defaultValue="北京")String cityName,
                              @RequestParam(value="town",defaultValue="北京")String townName ){
        final String[] s = {""};
        CityDao.queryCity(cityName, townName, new CityDao.CallBack() {
            @Override
            public void onGetObj(Object object) {
                if (!(object instanceof CityEntity)) {
                    throw new IllegalStateException("the obj is not a CityEntity");
                }
                s[0] = HttpHelper.getWeather(((CityEntity) object).getTownId());
            }
        });
        return s[0];
    }
}
