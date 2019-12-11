package com.xiayiye.honorfirst.singleinstance;

/**
 * @author xiayiye
 * 安全高效的单例模式
 */
public class SingleInstanceObject {
    private SingleInstanceObject() {
    }

    public static SingleInstanceObject getInstance() {
        return CreateInstance.INSTANCE;
    }

    private static class CreateInstance {
        private static final SingleInstanceObject INSTANCE = new SingleInstanceObject();
    }
}
