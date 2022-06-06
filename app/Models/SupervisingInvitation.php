<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Supervisinginvitation extends Model
{
    protected $table ="supervisinginvitations";
    protected $fillable=['room_id','creator_id','user_id','confirmation'];
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
