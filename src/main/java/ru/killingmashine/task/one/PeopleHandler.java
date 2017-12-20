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

    public void printVariablesChangePeopleInDepartament(){
        Iterator<Departament> iterator = departamentSet.iterator();
//        ���� �� �������������
        while (iterator.hasNext()){
            Departament departament = iterator.next();
            List<People> peopleList = departament.getPeopleInDepartament();
            BigDecimal sumSalaryInDepartament = new BigDecimal(0);
            BigDecimal avgSalaryInDepartament;
//            ���� �� ����� � ���������� ������������, ������� ������� ��
            for (int i = 0; i <peopleList.size() ; i++) {
                People people = peopleList.get(i);
                sumSalaryInDepartament=sumSalaryInDepartament.add(people.getSalary());
            }
            avgSalaryInDepartament=sumSalaryInDepartament.divide(new BigDecimal(peopleList.size()),2,RoundingMode.HALF_EVEN);

//            ������������� ����������� �������� ����� �� ����� ������������
//            ��������� ����� �� ������� �� ������ ������ ��� �������� ����������
//            ��� ��� ���� �� �����������

            for (int i = 0; i < peopleList.size(); i++) {
                People people = peopleList.get(i);
                BigDecimal sumSalaryInDepartamentNew=new BigDecimal(0);
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
                            BigDecimal sumSalaryInNewDepartament=new BigDecimal(0);
                            BigDecimal avgSalaryInNewDepartament;
                            for (int j = 0; j < listPeopleSecond.size(); j++) {
                                People peopleSecond = listPeopleSecond.get(j);
                                sumSalaryInNewDepartament=sumSalaryInNewDepartament.add(peopleSecond.getSalary());
                            }
                            avgSalaryInNewDepartament=sumSalaryInNewDepartament.divide(new BigDecimal(listPeopleSecond.size()),2,RoundingMode.HALF_EVEN);
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
