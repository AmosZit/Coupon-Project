import { User } from './User';

export class Company {
 
    public constructor( 
                        public id ?: number , 
                        public companyName?: string, 
                        public contactePhone?: string,
                        public user ?:User,


                       
                     )
                    
    {

    }
}
