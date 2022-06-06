<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Pollingroom extends Model
{
   protected $table = "pollingrooms";
   protected $fillable=['room_identifier','user_id','open_time','close_time','title','is_conditional','condition_type','condition_desc'];
   
   public $timestamps=false;




    #################### Relations Begin  ######################

    public function user(){
      return $this -> belongsTo('App\User','creator_id');
   }
   
    public function pollingbox(){
       return $this -> hasOne('App\Models\PollingBox','room_id');
    }
   

    public function supervision(){
       return $this -> hasMany('App\Models\Supervision','room_id');
    }


    public function pollinginvitation(){
      return $this -> hasMany('App\Models\PollingInvitation','room_id');
   }


   public function supervisinginvitation(){
      return $this -> hasMany('App\Models\SupervisingInvitation','room_id');
   }

    public function checkcondition(){
      return $this -> hasMany('App\Models\CheckCondition','room_id');
   }

   
   //this relation create to link polling room with polling process directly
   public function choices(){
      return $this -> hasOneThrough('App\Models\Choice','App\Models\PollingBox','room_id','box_id');
   }


    


    ###################    Relations End   #####################
}
