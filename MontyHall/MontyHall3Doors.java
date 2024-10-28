public class MontyHall3Doors 
{
    int NumberOfGames = 1000000;
    int NumberOfDoors = 3;
    int WinnerDoor;
    int SelectedDoor;
    int OpenedDoor;
    int NewSelectedDoor;
    int wins = 0;
    int losses = 0;

    /**
     * This method plays the Monty Hall 3 doors game 1000000 number of times and calculates the win and loss percentage
     * uses the strategy of always switching doors to gain a higher win percentage
     */
    public void PlayDaGame()
    {
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

            NewSelectedDoor = (int)(Math.random() * NumberOfDoors);
            // To make sure the swapped door is never the same door or the door thats already opened
            while(NewSelectedDoor == OpenedDoor || NewSelectedDoor == SelectedDoor)
            {
                NewSelectedDoor = (int)(Math.random() * NumberOfDoors);
            }

            if(NewSelectedDoor == WinnerDoor)
            {
                
                wins++;
            }
            else
            {
                
                losses++;
            }
        }

        System.out.println("Win Percentage: " + (double)wins / NumberOfGames * 100 + "%");
        System.out.println("Loss Percentage: " + (double)losses / NumberOfGames * 100 + "%");
    }

    
}
