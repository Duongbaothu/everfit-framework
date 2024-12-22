package testData;

import lombok.Getter;

@Getter
public class HabitTaskData {
    private String habitName;
    private String goalValue;
    private String habitUnit;
    private String habitFrequency;
    private String startDate;
    private String endDate;
    private String habitRepeat;
    private String reminderTime;
    private String startDateText;
    private String endDateText;

    public HabitTaskData(String habitName, String goalValue, String habitUnit, String habitFrequency, String startDate,
                         String endDate, String habitRepeat, String reminderTime, String startDateText, String endDateText) {
        this.habitName = habitName;
        this.goalValue = goalValue;
        this.habitUnit = habitUnit;
        this.habitFrequency = habitFrequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.habitRepeat = habitRepeat;
        this.reminderTime = reminderTime;
        this.startDateText = startDateText;
        this.endDateText = endDateText;
    }


}
