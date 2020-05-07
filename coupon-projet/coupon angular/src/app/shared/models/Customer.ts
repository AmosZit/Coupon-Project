import { User } from './User';


export class Customer {
 
    public constructor( 
                        public id ?: string , 
                        public firstName?: string,
                        public lastName?: string,
                        public user?:User, 

                        
                        )
                    
    {

    }
}
