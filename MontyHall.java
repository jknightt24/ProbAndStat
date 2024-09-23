public class MontyHall 
{
    int NumberOfGames = 1000000;
    int NumberOfDoors = 3;
    int WinnerDoor;
    int SelectedDoor;
    int OpenedDoor;

    for(int i = 0; i < NumberOfGames; i++)
    {
        WinnerDoor = (int)(Math.random() * NumberOfDoors);
        SelectedDoor = (int)(Math.random() * NumberOfDoors);
        OpenedDoor = (int)(Math.random() * NumberOfDoors);
        
        // To make sure the program never selects the door that wins or the door the contestant picked
        while(OpenedDoor == WinnerDoor || OpenedDoor == SelectedDoor)
        {
            OpenedDoor = (int)(Math.random() * NumberOfDoors);
        }
    }
}
