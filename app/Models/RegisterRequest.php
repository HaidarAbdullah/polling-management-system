<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class RegisterRequest extends Model
{
   protected $table = "rigisterrequest";
   protected $fillable=['user_name','registeration_email','contact_email','password','photo_ref','national_id'];
   public $timestamps=false;
   



}
