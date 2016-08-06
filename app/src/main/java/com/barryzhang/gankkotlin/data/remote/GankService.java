package com.barryzhang.gankkotlin.data.remote;

import com.barryzhang.gankkotlin.entities.DailyGankEntity;
import com.barryzhang.gankkotlin.entities.DayContent;
import com.barryzhang.gankkotlin.entities.History;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/12
 */
public interface GankService {

    @GET("history/content/day/{year}/{month}/{day}")
    Observable<DayContent> contentDay(
            @Path("year")String year,
            @Path("month") String month,
            @Path("day") String day
    );

    @GET("day/{year}/{month}/{day}")
    Observable<DailyGankEntity> dayByYearMonthDay(
            @Path("year")String year,
            @Path("month") String month,
            @Path("day") String day
    );

    @GET("day/history")
    Observable<History> history();

}
