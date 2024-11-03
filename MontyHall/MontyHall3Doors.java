/**
 * This class simulates the Monty Hall 3 doors game. The game is played 1000000 times and the win and loss percentage is calculated.
 * The game is played twice, once with the strategy of always switching doors and once with the strategy of never switching doors.
 * The win and loss of the two strategies are compared.
 */
public class MontyHall3Doors 
{
    int NumberOfGames = 1000000;
    int NumberOfDoors = 3;
    int WinnerDoor;
    int SelectedDoor;
    int OpenedDoor;
    int NewSelectedDoor;
    

    /**
     * This method plays the Monty Hall 3 doors game 1000000 number of times and calculates the win and loss percentage
     * uses the strategy of always switching doors
     */
    public void PlaySwapGame()
    {
        int wins = 0;
        int losses = 0;
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

        System.out.println("Win Percentage always swapping: " + (double)wins / NumberOfGames * 100 + "%");
        System.out.println("Loss Percentage always swapping: " + (double)losses / NumberOfGames * 100 + "%");
    }

    /**
     * This method plays the Monty Hall 3 doors game 1000000 number of times and calculates the win and loss percentage.
     * utilizes the strategy of never switching doors
     */
    public void PlayRegularGame()
    {
        int wins = 0;
        int losses = 0;
        // Plays the game 1000000 times
        for(int i = 0; i < NumberOfGames; i++)
        {
            WinnerDoor = (int)(Math.random() * NumberOfDoors);
            SelectedDoor = (int)(Math.random() * NumberOfDoors);

            if(SelectedDoor == WinnerDoor)
            {
                wins++;
            }
            else
            { 
                losses++;
            }
        }

        System.out.println("Win Percentage no swapping : " + (double)wins / NumberOfGames * 100 + "%");
        System.out.println("Loss Percentage no swapping : " + (double)losses / NumberOfGames * 100 + "%");
    }

    
}
