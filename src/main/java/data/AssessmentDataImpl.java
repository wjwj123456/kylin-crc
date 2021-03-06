package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataservice.AssessmentDataService;
import po.AssessmentPO;
import po.ReportPO;
import tools.CrcModule;
import vo.ReportVO;

public class AssessmentDataImpl implements AssessmentDataService {

    private CrcModule crc = null;
    private ResultSet rSet = null;
    private MergeDataImpl mergeDataImpl = new MergeDataImpl();

    @Override
    public int[] getAssessmentValue(String taskName, List<ReportVO> vos) throws SQLException, ClassNotFoundException {
        int[] assess = {0, 0};
        if (vos.size() != 0) {

            int[][] matrix = getMatix(taskName, vos);
            crc = new CrcModule(matrix);
            assess[0] = (int) crc.getMtCH();
            assess[1] = (int) crc.getMhCH();
            return assess;
        } else {
            return assess;
        }
    }

    private int[][] getMatix(String taskName, List<ReportVO> vos) throws SQLException, ClassNotFoundException {
        List<String> nameList = new ArrayList<String>();
        nameList = getReviewerNames(taskName);
        System.out.println(nameList);
        System.out.println(vos);
        int[][] matrix = new int[vos.size()][nameList.size()];

        int i = 0;
        int j = 0;
        for (i = 0; i < vos.size(); i++) {
            for (j = 0; j < nameList.size(); j++) {
                matrix[i][j] = 0;
            }
        }
        i = 0;
        j = 0;

        List<Integer> allFinalId = new ArrayList<Integer>();
        List<Integer> allIncludedId = new ArrayList<Integer>();
        List<String> allIncludedName = new ArrayList<String>();
        String sql1 = "SELECT * FROM merge ";
        Connection connection = DBManager.connect();
        PreparedStatement ps = connection.prepareStatement(sql1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            allFinalId.add(rs.getInt(1));
            allIncludedId.add(rs.getInt(2));
            allIncludedName.add(rs.getString(3));
        }

        for (ReportVO vo : vos) {

            j = nameList.indexOf(vo.getUserName());
            matrix[i][j] = 1;

            int id = mergeDataImpl.getID(new ReportPO(vo));

            String sql = "SELECT uname,included_id FROM merge where final_id = " + id;
            rSet = DBManager.getResultSet(sql);
            // if (!rSet.next()) {
            //
            // } else {
            // String name = "";
            // name = rSet.getString(1);
            // j = nameList.indexOf(name);
            // matrix[i][j] = 1;
            // while (rSet.next()) {
            // name = rSet.getString(1);
            // j = nameList.indexOf(name);
            // matrix[i][j] = 1;
            // }
            // }
            List<Integer> includedIdList = new ArrayList<Integer>();
            String name = "";
            while (rSet.next()) {
                includedIdList.add(rSet.getInt(2));

                name = rSet.getString(1);
                j = nameList.indexOf(name);
                matrix[i][j] = 1;
            }

            for (Integer integer : includedIdList) {
                int tempId = integer;
                int index = -1;
                while ((index = allFinalId.indexOf(tempId)) != -1) {
                    matrix[i][nameList.indexOf(allIncludedName.get(index))] = 1;
                    tempId = allIncludedId.get(index);
                }
            }

            i++;
            DBManager.closeConnection();
        }

        return matrix;
    }

    private List<String> getReviewerNames(String taskName) throws SQLException, ClassNotFoundException {

        List<String> nameList = new ArrayList<String>();
        List<String> nameList2 = new ArrayList<String>();
        List<String> nameList3 = new ArrayList<String>();
        // for (ReportVO vo : vos) {
        // String sql = "SELECT included_id FROM merge where final_id = " +
        // mergeDataImpl.getID(new ReportPO(vo));
        // rSet = DBManager.getResultSet(sql);
        //
        // while (rSet.next()) {
        // String name = getUNameById(rSet.getInt(1));
        // if (!nameList.contains(name))
        // nameList.add(name);
        // }
        // DBManager.closeConnection();
        // }
        String sql = "SELECT id,uname FROM review WHERE state = 'merged' and tname = '" + taskName + "' ORDER BY id";
        rSet = DBManager.getResultSet(sql);
        while (rSet.next())
            nameList.add(rSet.getString(2));
        DBManager.closeConnection();

        String sql1 = "SELECT id,uname FROM history WHERE  tname = '" + taskName + "' ORDER BY id";
        rSet = DBManager.getResultSet(sql1);
        while (rSet.next())
            nameList2.add(rSet.getString(2));
        DBManager.closeConnection();
        if (nameList2.size() != 0) {
            String firstMergeName = "";
            for (String string : nameList) {
                if (!nameList2.contains(string))
                    firstMergeName = string;
            }

            nameList3.add(firstMergeName);
            nameList3.addAll(nameList2);
        } else {
            nameList3.addAll(nameList);
        }
        return nameList3;
    }

    public String getUNameById(int id) throws SQLException, ClassNotFoundException {
        String name = "";
        String sql = "SELECT uname FROM report where id = " + id;
        Connection connection = DBManager.connect();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            name = rs.getString(1);
        }
        DBManager.stopAll(rs, ps, connection);
        return name;
    }

    @Override
    public int[][] getHistoryValues(String taskName) throws SQLException, ClassNotFoundException {
        List<Integer> faults = new ArrayList<Integer>();
        List<Integer> assessfaults_mt = new ArrayList<Integer>();
        List<Integer> assessfaults_mh = new ArrayList<Integer>();
        String sql = "SELECT fault,assessfault_mt,assessfault_mh FROM history where tname = '" + taskName
                + "' ORDER BY id";
        rSet = DBManager.getResultSet(sql);
        while (rSet.next()) {
            faults.add(rSet.getInt(1));
            assessfaults_mt.add(rSet.getInt(2));
            assessfaults_mh.add(rSet.getInt(3));
        }
        DBManager.closeConnection();
        int[][] values = new int[3][faults.size()];
        for (int i = 0; i < faults.size(); i++) {
            values[0][i] = faults.get(i);
            values[1][i] = assessfaults_mt.get(i);
            values[2][i] = assessfaults_mh.get(i);
        }

        return values;
    }

    @Override
    public List<AssessmentPO> getAllAssessments(String taskName, List<ReportVO> vos)
            throws SQLException, ClassNotFoundException {
        List<AssessmentPO> pos = new ArrayList<AssessmentPO>();

        List<String> nameList = new ArrayList<String>();
        nameList = getReviewerNames(taskName);
        if (nameList.size() > 1) {
            if (vos.size() != 0) {
                int[][] matrix = getMatix(taskName, vos);
                CrcModule crcM = new CrcModule(matrix);
                int assessfault_mt = (int) crcM.getMtCH();
                int assessfault_mh = (int) crcM.getMhCH();

                int col = nameList.size();
                for (int i = 0; i < col; i++) {
                    String userName = nameList.get(i);
                    int findedfault = 0;
                    int uniquefault = 0;
                    double time = 0.0;
                    int faultperhour = 0;
                    int flag = 0;
                    for (int j = 0; j < matrix.length; j++) {
                        if (matrix[j][i] == 1) {
                            findedfault++;
                            for (int k = 0; k < col; k++) {
                                if (matrix[j][k] == 1)
                                    flag++;
                            }
                            if (flag == 1)
                                uniquefault++;
                            flag = 0;
                        }
                    }
                    String sql = "SELECT time FROM review where tname = '" + taskName + "' and uname = '" + userName
                            + "'";
                    ResultSet rSet = DBManager.getResultSet(sql);
                    if (rSet.next())
                        time = rSet.getDouble(1);
                    DBManager.closeConnection();
                    faultperhour = (int) ((double) findedfault / time);
                    pos.add(new AssessmentPO(userName, assessfault_mt, assessfault_mh, findedfault, uniquefault, time,
                            faultperhour));
                }
            } else {
                for (int i = 0; i < nameList.size(); i++) {
                    pos.add(new AssessmentPO(nameList.get(i), 0, 0, 0, 0, 0.0, 0));
                }
            }
        } else {
            for (int i = 0; i < nameList.size(); i++) {
                pos.add(new AssessmentPO(nameList.get(i), 0, 0, 0, 0, 0.0, 0));
            }
        }
        return pos;
    }

    @Override
    public List<List<String>> getAllFindedReviewerNames(List<ReportVO> vos)
            throws SQLException, ClassNotFoundException {
        List<List<String>> names = new ArrayList<>();

        if (vos.size() == 0) {
            return names;
        }

        String taskName = vos.get(0).getTaskName();

        List<String> nameList = getReviewerNames(taskName);

        int[][] matrix = getMatix(taskName, vos);

        for (int i = 0; i < vos.size(); i++) {
            List<String> list = new ArrayList<String>();
            for (int j = 0; j < nameList.size(); j++) {
                if (matrix[i][j] == 1) {
                    list.add(nameList.get(j));
                }
            }
            names.add(list);
        }

        return names;
    }

}
