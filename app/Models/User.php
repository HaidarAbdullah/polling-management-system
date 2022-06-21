<?php

namespace App\Models;

use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;

class User extends Authenticatable
{
    use HasApiTokens, HasFactory, Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $fillable = ['user_name','registeration_email','contact_email', 'password' ];

    /**
     * The attributes that should be hidden for serialization.
     *
     * @var array<int, string>
     */
    protected $hidden = [
        'password',
        'remember_token',
    ];

    /**
     * The attributes that should be cast.
     *
     * @var array<string, string>
     */
    protected $casts = [
        'email_verified_at' => 'datetime',
    ];
    
    ###################### Relations Begin  ######################
  
    public function pollingroom(){
           return $this -> hasMany('App\Models\PollingRoom','creator_id');
    }

    public function supervision(){
           return $this -> hasMany('App\Models\Supervision','user_id');
    }

    public function pollinginvitation(){
           return $this -> hasMany('App\Models\PollingInvitation','user_id');
    }

    public function supervisinginvitation(){
           return $this -> hasMany('App\Models\SupervisingInvitation','user_id');
    }
      
    public function checkcondition(){
           return $this -> hasMany('App\Models\CheckCondition','user_id');
    }

   ###################### Relations End  ######################
}
