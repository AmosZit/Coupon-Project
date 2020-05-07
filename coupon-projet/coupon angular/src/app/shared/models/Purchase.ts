import { Coupon } from './coupon';

export class Purchase {
 
    public constructor( 
                        public couponId ?: string , 
                        public customerId ?: string , 
                        public amounts ?: number , 
                        public coupon ?: Coupon , 
                        

                        
                        )
                    
    {

    }
}

