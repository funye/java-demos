package com.fun.annotation;

/**
 * @author huanye
 *         Date: 2017/9/19 下午12:26
 */
public class RunUnit {

    public static void main(String[] args) {

        TestClass t = new TestClass();

        /*JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        //获取java文件管理类
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        //获取java文件对象迭代器
        Iterable<? extends JavaFileObject> it = manager.getJavaFileObjects("");
        //设置编译参数
        ArrayList<String> ops = new ArrayList<String>();
        ops.add("-Xlint:unchecked");
        //设置classpath
        ops.add("-classpath");
        ops.add(CLASS_PATH);
        //获取编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, ops, null, it);
        //执行编译任务
        task.call();*/

    }
}
