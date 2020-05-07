
export class Coupon {
 
    
    public constructor( public  id ?: number , 
        public  compagnyId ?: String,
        public  category ?: String,
        public  title ?: String ,
        public  description ?:String ,
        public  startDate?: String , 
        public  endDate ?: String ,
        public  amount?: number,
        public  price ?: number,
        public  image ?:String ,
)
                    
{

}
}
