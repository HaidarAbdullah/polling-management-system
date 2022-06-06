<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Supervision extends Model
{
    protected $table = "supervisions";
    protected $fillable=['user_id','room_id'];
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
