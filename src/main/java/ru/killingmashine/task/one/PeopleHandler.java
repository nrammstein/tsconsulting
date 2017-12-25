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
//         идем по списку людей в конкретном департаменте
        for (int i = 0; i < listPeople.size(); i++) {
            People people=listPeople.get(i);
//            кладем человека в отдельный список и дабавляем в общий список вариантов перевода. тк 1 человек одиин из возможных вариантов перевода
            ArrayList<People> tmp=new ArrayList<People>();
            tmp.add(people);
            list.add(tmp);
//            идем по общему списку и составляем возможные варианты перевода из тех списков что уже сформированы, на основе их формируем новые списки и так же добавляем к общему
            if (list.size()==1) continue; //тк сам с собой человек не может перевестись
            ArrayList<ArrayList<People>> tempList= new ArrayList<ArrayList<People>>(list);
            for (int j = 0; j <tempList.size() ; j++) {
//                получаем из общего списка, списки людей
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
//                    исклюаем перевод всех сотрудников из отдела, кто-то должен остаться
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
//        цикл про департаментам
        while (iterator.hasNext()){
            Departament departament = iterator.next();
            List<People> peopleList = departament.getPeopleInDepartament();
//            полуаем списки людей возможных переводов
            variablesChangeList=getVariablesList(peopleList);
//            считаем среднюю зп текущего отдела
            BigDecimal sumSalaryDepartament=calculationSumSalaryInDepartament(peopleList);
            BigDecimal avgSalaryDepartament=calculationAvgSalaryInDepartament(peopleList);
//            проверяем списки людей для перевода
            for (int i = 0; i < variablesChangeList.size(); i++) {
                ArrayList<People> peopleArrayList=variablesChangeList.get(i);
//                сумма зп людей из текущего списка для перевода
                BigDecimal sumSalaryPeoplesChange=calculationSumSalaryInDepartament(peopleArrayList);
//                считаем повысит ли перевод этого или этих пиплов зп в текущем отделе
                BigDecimal sumSalaryDepartamentNew=sumSalaryDepartament.subtract(sumSalaryPeoplesChange);
                BigDecimal avgSalaryDepartamentNew=sumSalaryDepartamentNew.divide(new BigDecimal(peopleList.size()-peopleArrayList.size()),2,RoundingMode.HALF_EVEN);
//                проверяем повысилась ли средняя в отделе
                if((avgSalaryDepartament.compareTo(avgSalaryDepartamentNew))==-1){
//                    тут мы если средняя зп увеличилась, далее ищем куда перевести
                    Iterator<Departament> iteratorNew = departamentSet.iterator();
                    while (iteratorNew.hasNext()){
                        Departament departamentNew = iteratorNew.next();
                        if (!departament.getDepartamentName().equals(departamentNew.getDepartamentName())){
//                            тут мы если имена департаментов не совпадают
                            List<People> peopleListNew = departamentNew.getPeopleInDepartament();
//                            считаем среднюю зп нового отдела до перевода
                            BigDecimal sumSalaryDepartamentChange=calculationSumSalaryInDepartament(peopleListNew);
                            BigDecimal avgSalaryDepartamentChange=calculationAvgSalaryInDepartament(peopleListNew);
//                            считаем среднюю зп отдела после перевода
                            BigDecimal sumSalaryDepartamentChangeNew=sumSalaryDepartamentChange.add(sumSalaryPeoplesChange);
                            BigDecimal avgSalaryDepartamentChangeNew=sumSalaryDepartamentChangeNew.divide(new BigDecimal(peopleListNew.size()+peopleArrayList.size()),2,RoundingMode.HALF_EVEN);
//                            сравниваем увеличил ли перевод среднюю зп в новом отделе
                            if ((avgSalaryDepartamentChange.compareTo(avgSalaryDepartamentChangeNew))==-1){
//                                средняя зп повышена у старого и нового отдела, сообщаем о варианте перевода
                                System.out.printf("Возможен перевод сотрудника - %s из отдела - %s, в отдел - %s%n" +
                                        "Средняя зп отдела %s до перевода - %s, средняя зп отдела после перевода - %s%n" +
                                        "Средняя зп отдела %s до перевода - %s, средняя зп после перевода - %s%n%n ",
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
//        цикл по департаментам
        while (iterator.hasNext()){
            Departament departament = iterator.next();
            List<People> peopleList = departament.getPeopleInDepartament();
            BigDecimal sumSalaryInDepartament = calculationSumSalaryInDepartament(peopleList);
            BigDecimal avgSalaryInDepartament=calculationAvgSalaryInDepartament(peopleList);
//            рассматриваем возможности перевода людей из этого департамента
//            проверяем будет ли средняя зп отдела больше при переводе сотрудника
//            еще раз цикл по сотрудникам
            for (int i = 0; i < peopleList.size(); i++) {
                People people = peopleList.get(i);
                BigDecimal sumSalaryInDepartamentNew;
                BigDecimal avgSalaryInDepartamentNew;
                sumSalaryInDepartamentNew=sumSalaryInDepartament.subtract(people.getSalary());
                avgSalaryInDepartamentNew=sumSalaryInDepartamentNew.divide(new BigDecimal(peopleList.size()-1),2,RoundingMode.HALF_EVEN);
                if ((avgSalaryInDepartament.compareTo(avgSalaryInDepartamentNew)) == -1){
//                    если мы тут, значит перевод сотрудника увеличил среднюю зп в отделе, дальше ищем куда его можно перевести
                    Iterator<Departament> iteratorSecond = departamentSet.iterator();
//                    присматриваем новый отдел для нашего сотрудника
                    while (iteratorSecond.hasNext()){
                        Departament departamentSecond = iteratorSecond.next();
//                        проверяем чтобы департамент был другой
                        if (!departament.getDepartamentName().equals(departamentSecond.getDepartamentName())){
//                            суда попадаем если имена отделов не совпадают, дальше считаем среднюю зп отдела куда переводим
                            List<People> listPeopleSecond = departamentSecond.getPeopleInDepartament();
                            BigDecimal sumSalaryInNewDepartament=calculationSumSalaryInDepartament(listPeopleSecond);
                            BigDecimal avgSalaryInNewDepartament=calculationAvgSalaryInDepartament(listPeopleSecond);
//                            тут высчитываем среднюю зп нового отдела после предпологаемого перевода сотрудника
                            BigDecimal sumSalaryInNewDepartamentSecond = sumSalaryInNewDepartament.add(people.getSalary());
                            BigDecimal avgSalaryInNewDepartamentSecond = sumSalaryInNewDepartamentSecond.divide(new BigDecimal(listPeopleSecond.size()+1),2,RoundingMode.HALF_EVEN);
//                            сравниваем среднюю зп после перевода
                            if ((avgSalaryInNewDepartament.compareTo(avgSalaryInNewDepartamentSecond))==-1){
//                                если мы тут, перевод сотрудника в этот отдел нас удовлетворяет
                                System.out.printf("Возможен перевод сотрудника %s из отдела - %s в отдел - %s%n  Средняя зп старого отдела до перевода - %s%n" +
                                        "Средняя зп старого отдела после перевода - %s%nСредняя зп нового отдела до перевода - %s%nСредняя зп нового отдела после перевода - %s%n%n",
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