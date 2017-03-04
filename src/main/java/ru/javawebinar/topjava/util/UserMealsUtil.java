package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        List<UserMealWithExceed> userMealWithExceeds =
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
        for (UserMealWithExceed userMealWithExceed : userMealWithExceeds) {
            System.out.println(userMealWithExceed.getDateTime() + " " + userMealWithExceed.getDescription()
            + " " + userMealWithExceed.isExceed());
        }
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate,Integer> datesAndCalories = new HashMap<>();
                for (UserMeal userMeal : mealList) {
                    int calories = userMeal.getCalories();
                    LocalDate localDate = userMeal.getDateTime().toLocalDate();
                    if (datesAndCalories.containsKey(localDate)) {
                        int totalCaloriesInDay = datesAndCalories.get(localDate) + calories;
                        datesAndCalories.put(localDate,totalCaloriesInDay);
                    }
                    else datesAndCalories.put(localDate,calories);

                }
                List<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();
                for (UserMeal userMeal : mealList) {
                    if (userMeal.getDateTime().toLocalTime().isAfter(startTime)&&
                            userMeal.getDateTime().toLocalTime().isBefore(endTime)) {
                        int totalCalories = datesAndCalories.get(userMeal.getDateTime().toLocalDate());
                        boolean isCaloriesExceeded = totalCalories>caloriesPerDay;

                        userMealWithExceeds.add(new UserMealWithExceed(userMeal.getDateTime(),
                                                                       userMeal.getDescription(),
                                                                       userMeal.getCalories(),
                                                                            isCaloriesExceeded));
                    }

                }
        return userMealWithExceeds;
    }
}
