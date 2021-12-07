package com.fun.spring.life;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TestFactoryBean implements FactoryBean<TestBean> , InitializingBean {

    private TestBean testBean;

    @Override
    public TestBean getObject() throws Exception {
        return testBean;
    }

    @Override
    public Class<?> getObjectType() {
        return TestBean.class;
    }

    // 这里使用 InitializingBean.afterPropertiesSet() 创建TestBean
    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.testBean == null) {
            TestBean tb = new TestBean();
            tb.setUserName("fun");
            tb.setAge(30);
            this.testBean = tb;
        }
    }
}
