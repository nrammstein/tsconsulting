package ru.killingmashine.task.one;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PeopleHandler {
    private Set<Departament> departamentSet;

    public PeopleHandler(Set<Departament> departamentSet)
    {
        this.departamentSet=departamentSet;
    }

    private BigDecimal calculationAvgSalaryInDepartament(List<People> peopleList){
        BigDecimal avgSalary;
        BigDecimal sumSalary= new BigDecimal(0);
        for (int i = 0; i < peopleList.size(); i++) {
            People people = peopleList.get(i);
            sumSalary=sumSalary.add(people.getSalary());
        }
        avgSalary=sumSalary.divide(new BigDecimal(peopleList.size()),2,RoundingMode.HALF_EVEN);
        return avgSalary;
    }
    private BigDecimal calculationSumSalaryInDepartament(List<People> peopleList){
        BigDecimal sumSalary= new BigDecimal(0);
        for (int i = 0; i < peopleList.size(); i++) {
            People people = peopleList.get(i);
            sumSalary=sumSalary.add(people.getSalary());
        }
        return sumSalary;
    }

    private ArrayList<ArrayList<People>> getVariablesList(List<People> listPeople){
         ArrayList<ArrayList<People>> list = new ArrayList<ArrayList<People>>();
//         ���� �� ������ ����� � ���������� ������������
        for (int i = 0; i < listPeople.size(); i++) {
            People people=listPeople.get(i);
//            ������ �������� � ��������� ������ � ��������� � ����� ������ ��������� ��������. �� 1 ������� ����� �� ��������� ��������� ��������
            ArrayList<People> tmp=new ArrayList<People>();
            tmp.add(people);
            list.add(tmp);
//            ���� �� ������ ������ � ���������� ��������� �������� �������� �� ��� ������� ��� ��� ������������, �� ������ �� ��������� ����� ������ � ��� �� ��������� � ������
            if (list.size()==1) continue; //�� ��� � ����� ������� �� ����� �����������
            ArrayList<ArrayList<People>> tempList= new ArrayList<ArrayList<People>>(list);
            for (int j = 0; j <tempList.size() ; j++) {
//                �������� �� ������ ������, ������ �����
                ArrayList<People> tmp2=new ArrayList<People>();
                boolean flag=false;
                for (int k = 0; k <tempList.get(j).size() ; k++) {
                    People p1 = tempList.get(j).get(k);
                    if (people!=p1) {
                        tmp2.add(p1);
                        flag=true;
                        continue;
                    }
                }
                if (flag) {
                    tmp2.add(people);
//                    �������� ������� ���� ����������� �� ������, ���-�� ������ ��������
                    if (tmp2.size()==listPeople.size()){
                        continue;
                    }
                    list.add(tmp2);
                }
            }
        }
        return list;
    }

    public void printManyPeopleVariablesChangeInDepartament(){
        List<ArrayList<People>> variablesChangeList;
        Iterator<Departament> iterator = departamentSet.iterator();
//        ���� ��� �������������
        while (iterator.hasNext()){
            Departament departament = iterator.next();
            List<People> peopleList = departament.getPeopleInDepartament();
//            ������� ������ ����� ��������� ���������
            variablesChangeList=getVariablesList(peopleList);
//            ������� ������� �� �������� ������
            BigDecimal sumSalaryDepartament=calculationSumSalaryInDepartament(peopleList);
            BigDecimal avgSalaryDepartament=calculationAvgSalaryInDepartament(peopleList);
//            ��������� ������ ����� ��� ��������
            for (int i = 0; i < variablesChangeList.size(); i++) {
                ArrayList<People> peopleArrayList=variablesChangeList.get(i);
//                ����� �� ����� �� �������� ������ ��� ��������
                BigDecimal sumSalaryPeoplesChange=calculationSumSalaryInDepartament(peopleArrayList);
//                ������� ������� �� ������� ����� ��� ���� ������ �� � ������� ������
                BigDecimal sumSalaryDepartamentNew=sumSalaryDepartament.subtract(sumSalaryPeoplesChange);
                BigDecimal avgSalaryDepartamentNew=sumSalaryDepartamentNew.divide(new BigDecimal(peopleList.size()-peopleArrayList.size()),2,RoundingMode.HALF_EVEN);
//                ��������� ���������� �� ������� � ������
                if((avgSalaryDepartament.compareTo(avgSalaryDepartamentNew))==-1){
//                    ��� �� ���� ������� �� �����������, ����� ���� ���� ���������
                    Iterator<Departament> iteratorNew = departamentSet.iterator();
                    while (iteratorNew.hasNext()){
                        Departament departamentNew = iteratorNew.next();
                        if (!departament.getDepartamentName().equals(departamentNew.getDepartamentName())){
//                            ��� �� ���� ����� ������������� �� ���������
                            List<People> peopleListNew = departamentNew.getPeopleInDepartament();
//                            ������� ������� �� ������ ������ �� ��������
                            BigDecimal sumSalaryDepartamentChange=calculationSumSalaryInDepartament(peopleListNew);
                            BigDecimal avgSalaryDepartamentChange=calculationAvgSalaryInDepartament(peopleListNew);
//                            ������� ������� �� ������ ����� ��������
                            BigDecimal sumSalaryDepartamentChangeNew=sumSalaryDepartamentChange.add(sumSalaryPeoplesChange);
                            BigDecimal avgSalaryDepartamentChangeNew=sumSalaryDepartamentChangeNew.divide(new BigDecimal(peopleListNew.size()+peopleArrayList.size()),2,RoundingMode.HALF_EVEN);
//                            ���������� �������� �� ������� ������� �� � ����� ������
                            if ((avgSalaryDepartamentChange.compareTo(avgSalaryDepartamentChangeNew))==-1){
//                                ������� �� �������� � ������� � ������ ������, �������� � �������� ��������
                                System.out.printf("�������� ������� ���������� - %s �� ������ - %s, � ����� - %s%n" +
                                        "������� �� ������ %s �� �������� - %s, ������� �� ������ ����� �������� - %s%n" +
                                        "������� �� ������ %s �� �������� - %s, ������� �� ����� �������� - %s%n%n ",
                                        peopleArrayList.toString(),departament.getDepartamentName(),departamentNew.getDepartamentName(),
                                        departament.getDepartamentName(),avgSalaryDepartament,avgSalaryDepartamentNew,
                                        departamentNew.getDepartamentName(),avgSalaryDepartamentChange,avgSalaryDepartamentChangeNew);
                            }
                        }
                    }
                }
            }
        }
    }

    public void printVariablesChangePeopleInDepartament(){
        Iterator<Departament> iterator = departamentSet.iterator();
//        ���� �� �������������
        while (iterator.hasNext()){
            Departament departament = iterator.next();
            List<People> peopleList = departament.getPeopleInDepartament();
            BigDecimal sumSalaryInDepartament = calculationSumSalaryInDepartament(peopleList);
            BigDecimal avgSalaryInDepartament=calculationAvgSalaryInDepartament(peopleList);
//            ������������� ����������� �������� ����� �� ����� ������������
//            ��������� ����� �� ������� �� ������ ������ ��� �������� ����������
//            ��� ��� ���� �� �����������
            for (int i = 0; i < peopleList.size(); i++) {
                People people = peopleList.get(i);
                BigDecimal sumSalaryInDepartamentNew;
                BigDecimal avgSalaryInDepartamentNew;
                sumSalaryInDepartamentNew=sumSalaryInDepartament.subtract(people.getSalary());
                avgSalaryInDepartamentNew=sumSalaryInDepartamentNew.divide(new BigDecimal(peopleList.size()-1),2,RoundingMode.HALF_EVEN);
                if ((avgSalaryInDepartament.compareTo(avgSalaryInDepartamentNew)) == -1){
//                    ���� �� ���, ������ ������� ���������� �������� ������� �� � ������, ������ ���� ���� ��� ����� ���������
                    Iterator<Departament> iteratorSecond = departamentSet.iterator();
//                    ������������� ����� ����� ��� ������ ����������
                    while (iteratorSecond.hasNext()){
                        Departament departamentSecond = iteratorSecond.next();
//                        ��������� ����� ����������� ��� ������
                        if (!departament.getDepartamentName().equals(departamentSecond.getDepartamentName())){
//                            ���� �������� ���� ����� ������� �� ���������, ������ ������� ������� �� ������ ���� ���������
                            List<People> listPeopleSecond = departamentSecond.getPeopleInDepartament();
                            BigDecimal sumSalaryInNewDepartament=calculationSumSalaryInDepartament(listPeopleSecond);
                            BigDecimal avgSalaryInNewDepartament=calculationAvgSalaryInDepartament(listPeopleSecond);
//                            ��� ����������� ������� �� ������ ������ ����� ��������������� �������� ����������
                            BigDecimal sumSalaryInNewDepartamentSecond = sumSalaryInNewDepartament.add(people.getSalary());
                            BigDecimal avgSalaryInNewDepartamentSecond = sumSalaryInNewDepartamentSecond.divide(new BigDecimal(listPeopleSecond.size()+1),2,RoundingMode.HALF_EVEN);
//                            ���������� ������� �� ����� ��������
                            if ((avgSalaryInNewDepartament.compareTo(avgSalaryInNewDepartamentSecond))==-1){
//                                ���� �� ���, ������� ���������� � ���� ����� ��� �������������
                                System.out.printf("�������� ������� ���������� %s �� ������ - %s � ����� - %s%n  ������� �� ������� ������ �� �������� - %s%n" +
                                        "������� �� ������� ������ ����� �������� - %s%n������� �� ������ ������ �� �������� - %s%n������� �� ������ ������ ����� �������� - %s%n%n",
                                        people.getName(),departament.getDepartamentName(),departamentSecond.getDepartamentName(),avgSalaryInDepartament,avgSalaryInDepartamentNew,
                                        avgSalaryInNewDepartament,avgSalaryInNewDepartamentSecond);
                            }
                        }
                    }
                }
            }
        }
    }
}