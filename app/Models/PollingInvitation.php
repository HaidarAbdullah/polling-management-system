<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class PollingInvitation extends Model
{
    protected $table = "pollinginvitations";
    protected $fillable=['room_id','user_id','availability','confirmation'];
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
