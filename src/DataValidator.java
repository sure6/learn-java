public class DataValidator {
    public static void main(String[] args) {
        // [数据初始化]
        byte[] rawData = {10, 20, 30, 40, 50}; // 原始数据数组
        int secretKey = 8; // 加密密钥
        int sum = 0;       // 总和
        int count = 0;     // 计数器
        int i = 0;         // 循环变量

        System.out.println("=== 开始数据处理 ===");

        // [第一阶段：循环与条件判断]
        // 目标：遍历数组，若元素大于25且为偶数，则累加；否则跳过。
        do {
            byte val = rawData[i];

            // ❌ 陷阱点1：精度问题与关系运算
            // 意图：判断 val + 10 是否大于 35 (val是byte，运算后变int)
            if ((byte)(val + 10) > 35 && val % 2 == 0) {
                sum += val;
                count++;

                // ❌ 陷阱点2：流程控制
                // 意图：满足条件后直接结束本次循环，进入下一次
                // 错误：在 do-while 中，如果在 i++ 之前使用 break，会直接跳出整个循环，导致后续数据未处
            }
            i++;

        } while (i < rawData.length);

        // [第二阶段：复杂的逻辑与位运算]
        // 目标：根据sum的值决定最终校验码，并进行异或加密演示
        int checkCode;

        // ❌ 陷阱点3：三元运算符与类型转换
        // 意图：如果sum>50，checkCode为sum%10(byte型)，否则为0
        // 注意：sum是变量，sum%10结果是int。虽然数值小，但类型不匹配，必须强转
        // 如果去掉 (byte) 强制转换，编译器会报错
        byte temp = (sum > 50) ? (byte)(sum % 10) : 0;
        checkCode = temp;

        // ❌ 陷阱点4：短路逻辑与副作用
        // 意图：如果checkCode不为0，则执行加密逻辑，并打印"Encrypted"
        // 注意：这里利用 && 短路特性。如果左边为false，右边方法不会执行
        boolean isEncrypted = (checkCode != 0) && (modifyValue(rawData, 0) == 100);

        // ❌ 陷阱点5：Switch语句的穿透与类型限制
        // 意图：根据count的值输出等级
        switch (count) {
            case 1:
                System.out.println("等级：C");
                break;
            case 2:
                System.out.println("等级：B");
                break;
            case 3:
                System.out.println("等级：A");
                break;
            default:
                // 尝试使用long类型作为case标签（这是非法的，虽然注释掉了，但需理解原因）
                // case 10L: System.out.println("等级：S");
                System.out.println("等级：未知");
        }

        // [第三阶段：位运算加密输出]
        // 对第一个数据进行简单的异或加密演示
        // 注意：rawData[0] 可能在前面的 modifyValue 中被修改过
        int encryptedVal = rawData[0] ^ secretKey;

        System.out.println("最终总和: " + sum);
        System.out.println("有效个数: " + count);
        System.out.println("校验码: " + checkCode);
        System.out.println("是否加密成功: " + isEncrypted);
        System.out.println("首数据加密值: " + encryptedVal);
    }

    // 辅助方法：模拟副作用
    public static int modifyValue(byte[] arr, int index) {
        arr[index] = 100; // 修改数组内容
        return 100;
    }
}
