<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Checkcondition extends Model
{

    protected $table = "checkconditions";
    protected $fillable=['user_id','room_id','answer'];
    public $timestamps=false;
    
    

    


    
    #################### Relations Begin  ######################
    
    public function pollingroom(){
        return $this -> belongsTo('App\Models\PollingRoom','room_id');
    }


    public function user(){
        return $this -> belongsTo('App\User','user_id');
    }




    ###################    Relations End   #####################


    
}
