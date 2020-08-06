package com.kxw.hopesfire.service;

import com.kxw.hopesfire.biz.model.MealModel;
import com.kxw.hopesfire.biz.model.UserMealModel;
import com.kxw.hopesfire.biz.service.IMealService;
import com.kxw.hopesfire.biz.service.IUserMealService;
import com.kxw.hopesfire.web.HopesfireWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author kangxiongwei
 * @date 2020/8/6 11:02 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HopesfireWebApplication.class)
public class MealServiceTest {

    @Resource
    private IMealService mealService;
    @Resource
    private IUserMealService userMealService;

    @Test
    public void testSaveMeal() {
        MealModel model = new MealModel();
        model.setMealType(1);
        model.setMealName("面条");
        model.setUsername("kangxiongwei");
        this.mealService.save(model);
    }

    @Test
    public void testSaveUserMeal() {
        UserMealModel model = new UserMealModel();
        model.setUsername("kangxiongwei");
        model.setMainMeal("米饭,馒头");
        model.setMealName("红烧肉,红烧排骨,醋溜土豆丝");
        model.setMealType(1);
        this.userMealService.save(model);
    }

}
