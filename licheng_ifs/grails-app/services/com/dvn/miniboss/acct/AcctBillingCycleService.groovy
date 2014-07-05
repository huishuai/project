package com.dvn.miniboss.acct
 
import com.miniboss.acct.utils.DateUtil
import com.miniboss.exception.BaseException
import org.apache.log4j.Logger

class AcctBillingCycleService {

    public static final String UNIT_MONTH = "month";
    private static final Logger logger = Logger.getLogger(AcctBillingCycleService.class);

    boolean transactional = false
    private static Map<String, AcctBillingCycle> cycleMap = new HashMap();

    public static void init() {
        try {
            List list = AcctBillingCycle.list();
            //生成初始化账期数据-暂定30年
            if (list == null || list.size() == 0) {
                genBatchBillingCycle(360, DateUtil.getPosDate(new Date(),-6,Calendar.MONTH))
            }
            for (AcctBillingCycle acctBillingCycle in list) {
                acctBillingCycle.setCycleBeginDate(new Date(acctBillingCycle.getCycleBeginDate().getTime()))
                acctBillingCycle.setCycleEndDate(new Date(acctBillingCycle.getCycleEndDate().getTime()))
                cycleMap.put(String.valueOf(acctBillingCycle.getId()), acctBillingCycle)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public static AcctBillingCycle getAcctBillingCycle(String billingCycleId) {
        if (cycleMap == null || cycleMap.size() == 0) {
            init();
        }
        AcctBillingCycle acctBillingCycle = cycleMap.get(billingCycleId);
        if (acctBillingCycle == null) {
            System.err.println("get AcctBillingCycle error id: " + billingCycleId)
        }
        return acctBillingCycle;
    }

    public static AcctBillingCycle getAcctBillingCycle(Date date) {
        String id = getBilingCycleId(date);
        AcctBillingCycle acctBillingCycle = getAcctBillingCycle(id);
        return acctBillingCycle;
    }

    public static AcctBillingCycle getAcctBillingCycle(int year, int month) {
        String id;
        if (month < 10) {
            id = year + "0" + month;
        } else {
            id = year + "" + month;
        }
        if (cycleMap == null || cycleMap.size() == 0) {
            init();
        }
        AcctBillingCycle acctBillingCycle = cycleMap.get(id);
        if (acctBillingCycle == null) {
            init();
            acctBillingCycle = cycleMap.get(id);
        }
        return acctBillingCycle;
    }

    /**
     * 获取当前时间以前的 count个帐务周期
     *
     * @param count 帐务周期个数
     * @return
     */
    //
    public static List<AcctBillingCycle> getPreBillingCycle(Date date, int count) throws Exception {
        List<AcctBillingCycle> list = new ArrayList<AcctBillingCycle>();
        AcctBillingCycle acctBillingCycle = getAcctBillingCycle(date);
        for (int i = 0; i < count; i++) {
            String preId = getPreAcctBillingCycleId(acctBillingCycle);
            AcctBillingCycle preAcctBillingCycle = cycleMap.get(preId);
            if (preAcctBillingCycle != null) {
                list.add(preAcctBillingCycle)
            } else {
                break;
            }
            acctBillingCycle = preAcctBillingCycle
        }
        return list;
    }

    public static AcctBillingCycle getPreBillingCycle(Date date) throws Exception {
        AcctBillingCycle acctBillingCycle = getAcctBillingCycle(date);
        String preId = getPreAcctBillingCycleId(acctBillingCycle);
        AcctBillingCycle preAcctBillingCycle = cycleMap.get(preId);
        return preAcctBillingCycle;
    }
    public static AcctBillingCycle getPreBillingCycle(AcctBillingCycle acctBillingCycle) throws Exception {
        String preId = getPreAcctBillingCycleId(acctBillingCycle);
        AcctBillingCycle preAcctBillingCycle = cycleMap.get(preId);
        return preAcctBillingCycle;
    }

    public static List<AcctBillingCycle> getPreBillingCycle(AcctBillingCycle acctBillingCycle, int count) throws Exception {
        List<AcctBillingCycle> list = new ArrayList<AcctBillingCycle>();
        for (int i = 0; i < count; i++) {
            String preId = getPreAcctBillingCycleId(acctBillingCycle);
            AcctBillingCycle preAcctBillingCycle = cycleMap.get(preId);
            if (preAcctBillingCycle != null) {
                list.add(preAcctBillingCycle)
            } else {
                break;
            }
            acctBillingCycle = preAcctBillingCycle
        }
        return list;
    }

    /**
     * 获取当前时间以后的 count个帐务周期
     *
     * @param count 帐务周期个数
     * @return
     */
    public static List<AcctBillingCycle> getNextBillingCycle(Date date, int count) throws Exception {
        List<AcctBillingCycle> list = new ArrayList<AcctBillingCycle>();
        AcctBillingCycle acctBillingCycle = getAcctBillingCycle(date);
        for (int i = 0; i < count; i++) {
            String nextId = getNextAcctBillingCyleId(acctBillingCycle);
            AcctBillingCycle nextAcctBillingCycle = cycleMap.get(nextId);
            if (nextAcctBillingCycle != null) {
                list.add(nextAcctBillingCycle)
            } else {
                break;
            }
            acctBillingCycle = nextAcctBillingCycle
        }
        return list;
    }


    public static List<AcctBillingCycle> getNextBillingCycle(AcctBillingCycle acctBillingCycle, int count) throws Exception {
        List<AcctBillingCycle> list = new ArrayList<AcctBillingCycle>();
        for (int i = 0; i < count; i++) {
            String nextId = getNextAcctBillingCyleId(acctBillingCycle);
            AcctBillingCycle nextAcctBillingCycle = cycleMap.get(nextId);
            if (nextAcctBillingCycle != null) {
                list.add(nextAcctBillingCycle)
            } else {
                break;
            }
            acctBillingCycle = nextAcctBillingCycle
        }
        return list;
    }

    public static AcctBillingCycle getNextBillingCycle(AcctBillingCycle acctBillingCycle) throws Exception {
        String nextId = getNextAcctBillingCyleId(acctBillingCycle);
        AcctBillingCycle nextAcctBillingCycle = cycleMap.get(nextId);
        return nextAcctBillingCycle;

    }
    public static AcctBillingCycle getNextBillingCycle(Date date) throws Exception {
       AcctBillingCycle acctBillingCycle1=getAcctBillingCycle(date);
        AcctBillingCycle nextAcctBillingCycle = getNextBillingCycle(acctBillingCycle1);
        return nextAcctBillingCycle;

    }

    public static String getNextAcctBillingCyleId(AcctBillingCycle acctBillingCycle) {
        String id = acctBillingCycle.getId();
        long year = Long.parseLong(id.substring(0, 4));
        long month = Long.parseLong(id.substring(4, 6))
        if (month == 12) {
            year = year + 1;
            month = 1;
        } else {
            month = month + 1;
        }
        String res = "";
        if (month < 10) {
            res = year + "0" + month;
        } else {
            res = year + "" + month;
        }
        return res

    }

    public static String getPreAcctBillingCycleId(AcctBillingCycle acctBillingCycle) {
        String id = acctBillingCycle.getId();
        long year = Long.parseLong(id.substring(0, 4));
        long month = Long.parseLong(id.substring(4, 6))
        if (month == 1) {
            year = year - 1;
            month = 12;
        } else {
            month = month - 1;
        }
        String res = "";
        if (month < 10) {
            res = year + "0" + month;
        } else {
            res = year + "" + month;
        }
        return res
    }

    /**
     * 从startDate开始生成 以后count月的帐期
     */
    public static void genBatchBillingCycle(int count, Date startDate) throws BaseException {
        List acctBillingCycleTypes = AcctBillingCycleType.list();

        if (acctBillingCycleTypes == null || acctBillingCycleTypes.size() == 0) {
            logger.warn("AcctBillingCycleType.NotFound");
            return
        }
        AcctBillingCycleType acctBillingCycleType = (AcctBillingCycleType) acctBillingCycleTypes[0];
        //得到上月开始日期，用来计算当月账期数据
        Date tmpDate = DateUtil.getPreMonthFirstDay(startDate);
        Date creatDate = new Date()
        for (int i = 0; i < count; i++) {
            try {
                AcctBillingCycle acctBillingCycle = new AcctBillingCycle();
                Date cycleStartDate = DateUtil.getNextMonthFirstDay(tmpDate);
                Date cycleEndDate = DateUtil.getPreDateByNum(DateUtil.getNextMonthFirstDay(cycleStartDate), 1);

                acctBillingCycle.setCreateDate(creatDate);
                acctBillingCycle.setCycleBeginDate(cycleStartDate);
                acctBillingCycle.setCycleEndDate(cycleEndDate);
                acctBillingCycle.setBillingCycleTypeId(acctBillingCycleType.getId());
                String id = getBilingCycleId(cycleStartDate)

                acctBillingCycle.setId(Long.parseLong(id));
                acctBillingCycle.save();
                tmpDate = cycleEndDate;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private static String getBilingCycleId(Date cycleStartDate) {
        int year = DateUtil.getYear(cycleStartDate);
        int month = DateUtil.getMonth(cycleStartDate);
        String id;
        if (month < 10) {
            id = year + "0" + month;
        } else {
            id = year + "" + month;
        }
        return id
    }
}