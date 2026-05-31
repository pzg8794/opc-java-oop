public class jug{

/**
 * This class provides the universal traits of a water jug. 
 */
    // Capacity indicates the size of the jug.  
    //Gallons represents how many gallons of water are in the jug.
        protected int capacity;
        protected int gallons;

        //Constructor for a water jug
        public jug (int capacity)
        {
            this.capacity = capacity;
            this.gallons = 0;
        }

        //Return the size of the jug.
        public int getCapacity()
        {
            return capacity;
        }

        //dump all water in the jug.
        public int dump()
        {
            this.gallons = 0;
            return this.gallons;
        }

        //Fill a jug to the top
        public int fill()
        {
            this.gallons = this.capacity;
            return this.gallons;
        }

        //Transfer contents from one jug to the other.
        public int transfer(jug second)
        {
            while (this.gallons > 0)
            {
                //Fill the second jug one gallon at a time.
                //Second jug must not be full and first jug must not be empty.
                if (second.gallons < second.capacity && this.gallons > 0)
                {
                    //Remove one gallon from the first and put it in the second.
                    this.gallons--;
                    second.gallons++;
                    //If either jug reaches two gallons, stop immediately, the goal is reached.
                    if (second.gallons ==2 ||this.gallons ==2)
                        break;
                }
                else break;
            }

            return this.gallons;
        }

        //This function tries to figure out the solution on it's own.
                public void heuristic()
                {



                }

}
